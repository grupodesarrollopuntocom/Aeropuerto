package Controlador;

import Modelo.Clases.ModeloLogin;
import Modelo.Clases.Vuelo;
import Modelo.JDBC.BaseDatos;
import Modelo.Tabla.ModeloTablaVuelo;
import Vista.InterfazVuelo;
import Vista.Login;
import Vista.Principal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Controlador implements ActionListener {
    //VARIABLES DE APOYO

    private String operacionBaseDatos;
    //INTERFACES
    private Login login;
    private Principal principal = new Principal();
    private InterfazVuelo iVuelo = new InterfazVuelo(principal, true);
    //MODELOS
    private ModeloLogin modeloLogin;
    private ModeloTablaVuelo modeloTablaVuelo;
    private Vuelo modeloVuelo;
    //CONNECCION A LA BASE DE DATOS
    private BaseDatos bd = new BaseDatos();

    public Controlador(Login login) {
        this.login = login;
        this.login.setLocationRelativeTo(null);
        this.login.setResizable(false);
        iniciar();
        login.setVisible(true);
    }

    public void iniciar() {
        //BOTONES LOGIN
        this.login.miBotonEntrar.setActionCommand("Entrar");
        this.login.miBotonEntrar.addActionListener(this);
        //BOTONES PRINCIPAL
        this.principal.botonAnyadirVuelo.setActionCommand("PricipalAceptar");
        this.principal.botonAnyadirVuelo.addActionListener(this);
        this.principal.botonEliminarVuelo.setActionCommand("PrincipalEliminar");
        this.principal.botonEliminarVuelo.addActionListener(this);
        this.principal.botonModificarVuelo.setActionCommand("PrincipalModificar");
        this.principal.botonModificarVuelo.addActionListener(this);
        //BOTONES INTERFAZ VUELO
        this.iVuelo.aceptarBvuelo.setActionCommand("AceptarVuelo");
        this.iVuelo.aceptarBvuelo.addActionListener(this);
        this.iVuelo.limpiarBvuelo.setActionCommand("BorrarDatosVuelo");
        this.iVuelo.limpiarBvuelo.addActionListener(this);
    }

    //METODOS DE APOYO
    public void borraInterfazVuelo() {
        this.iVuelo.idVueloI.setText(null);
        this.iVuelo.idAvionI.setText(null);
        this.iVuelo.origenI.setText(null);
        this.iVuelo.destinoI.setText(null);
        this.iVuelo.horaSalidaI.setText(null);
        this.iVuelo.horaLlegadaI.setText(null);
    }

    public void comprobarInterfazVuelo(String operacion) {
        operacion = this.operacionBaseDatos;
        System.out.println(operacion);
        if (this.iVuelo.idVueloI.getText().equals("")
                || this.iVuelo.idAvionI.getText().equals("")
                || this.iVuelo.origenI.getText().equals("")
                || this.iVuelo.destinoI.getText().equals("")
                || this.iVuelo.horaSalidaI.getText().equals("")
                || this.iVuelo.horaLlegadaI.getText().equals("")) {
            JOptionPane.showMessageDialog(iVuelo, "Asegúrate que todos los campos obligatorios estén llenos", "Faltan rellenar campos", JOptionPane.ERROR_MESSAGE);
        } else {
            int idVuelo, idAvion;
            String origen, destino, horaSalida, horaLLegada;
            idVuelo = Integer.parseInt(this.iVuelo.idVueloI.getText().trim());
            idAvion = Integer.parseInt(this.iVuelo.idAvionI.getText().trim());
            origen = this.iVuelo.origenI.getText();
            destino = this.iVuelo.destinoI.getText();
            horaSalida = this.iVuelo.horaSalidaI.getText();
            horaLLegada = this.iVuelo.horaLlegadaI.getText();
            this.modeloVuelo = new Vuelo(idVuelo, idAvion, origen, destino, horaSalida, horaLLegada);
            if (operacion.equals("anyadirBD")) {
                if (this.bd.consultaIdPrincipal("vuelo", "idVuelo", String.valueOf(this.modeloVuelo.getIdVuelo()))) {
                    JOptionPane.showMessageDialog(iVuelo, "La id vuelo ya esta asociado a atro vuelo Introducr una id difernete", "Id Vuelo", JOptionPane.ERROR_MESSAGE);
                    this.iVuelo.idVueloI.setText(null);
                    this.iVuelo.idVueloI.requestFocus();
                }
            }
            if (!this.modeloVuelo.esPalabraCaracteres(origen)) {
                JOptionPane.showMessageDialog(iVuelo, "El origen es incorrecto", "Origen", JOptionPane.ERROR_MESSAGE);
                this.iVuelo.origenI.setText(null);
                this.iVuelo.origenI.requestFocus();
            } else if (!this.modeloVuelo.esPalabraCaracteres(destino)) {
                JOptionPane.showMessageDialog(iVuelo, "El destino es incorrecto", "Destino", JOptionPane.ERROR_MESSAGE);
                this.iVuelo.destinoI.setText(null);
                this.iVuelo.destinoI.requestFocus();
            } else if (!this.modeloVuelo.compruebaHora(horaSalida)) {
                JOptionPane.showMessageDialog(iVuelo, "El formato de la hora es incorrecto", "Hora de Salida", JOptionPane.ERROR_MESSAGE);
                this.iVuelo.horaSalidaI.setText(null);
                this.iVuelo.horaSalidaI.requestFocus();
            } else if (!this.modeloVuelo.compruebaHora(horaLLegada)) {
                JOptionPane.showMessageDialog(iVuelo, "El formato de la hora es incorrecto", "Hora LLegada", JOptionPane.ERROR_MESSAGE);
                this.iVuelo.horaLlegadaI.setText(null);
                this.iVuelo.horaLlegadaI.requestFocus();
            } else {

                int num = JOptionPane.showConfirmDialog(iVuelo, "Atención esta apunto de introducir datos en tabla vuelo. ¿Desea continuar con la operacion?", "INFORMACION", JOptionPane.YES_NO_OPTION);
                if (num == 0) {
                    Object[] lista = new String[6];
                    lista[0] = String.valueOf(this.modeloVuelo.getIdVuelo());
                    lista[1] = String.valueOf(this.modeloVuelo.getIdAvion());
                    lista[2] = this.modeloVuelo.getOrigen();
                    lista[3] = this.modeloVuelo.getDestino();
                    lista[4] = this.modeloVuelo.getHoraSalida();
                    lista[5] = this.modeloVuelo.getHoraLLegada();
                    if (operacion.equals("anyadirBD")) {
                        this.modeloTablaVuelo.anadirFila(lista);
                        this.bd.ingresaDatosVuelo(this.modeloVuelo);
                    } else if (operacion.equals("actualizarBD")) {
                        this.bd.actulizaFila(this.modeloVuelo, String.valueOf(this.modeloVuelo.getIdVuelo()));
                    }
                    borraInterfazVuelo();
                    this.iVuelo.dispose();

                }
            }
        }

    }

    public void posicionVuelo() {
        iVuelo.setLocationRelativeTo(null);
        iVuelo.setResizable(false);
        iVuelo.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String comando = e.getActionCommand();
        //INTERFAZ LOGIN
        //Acceso al boton aceptar de la interfaz login
        if (comando.equals("Entrar")) {
            String nombre, contrasenia;
            nombre = this.login.miEntradaNombre.getText();
            contrasenia = this.login.miEntradaConstrasenia.getText();
            this.modeloLogin = new ModeloLogin(nombre, contrasenia);
            if (this.modeloLogin.compruebaDatosLogin() == true) {
                JOptionPane.showMessageDialog(login, "Bienvenido " + this.modeloLogin.getNombre(), "Error en validacion", JOptionPane.QUESTION_MESSAGE);
                this.principal.setLocationRelativeTo(null);
                this.principal.setResizable(false);
                this.modeloTablaVuelo = new ModeloTablaVuelo();
                this.principal.tablaVuelo.setModel(this.modeloTablaVuelo);
                this.login.dispose();
                this.principal.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(login, "Usuario y contraseña no validos. Por favor introduzca nuevamente los datos", "Error en validacion", JOptionPane.ERROR_MESSAGE);
            }
        }
        //ITERFAZ PRINCIPAL
        //Acceso al boton añadir vuelo en la interfaz pricipal
        if (comando.equals("PricipalAceptar")) {
            this.operacionBaseDatos = "anyadirBD";
            this.borraInterfazVuelo();
            this.posicionVuelo();
        }
        if (comando.equals("PrincipalEliminar")) {
            int seleccion = this.principal.tablaVuelo.getSelectedRow();
            if (seleccion == -1) {
                JOptionPane.showMessageDialog(principal, "Para modificar un vuelo tienes que seleccionar un vuelo", "Avertencia", JOptionPane.INFORMATION_MESSAGE);
            } else {
                Object dato = this.principal.tablaVuelo.getValueAt(seleccion, 0);
                System.out.println(dato);
                int opc = JOptionPane.showConfirmDialog(iVuelo, "Atención esta apunto de borrar un vuelo de la tabla. ¿Desea continuar con la operacion?", "INFORMACION", JOptionPane.YES_NO_OPTION);
                if (opc == 0) {
                    this.modeloTablaVuelo.borrarFila(seleccion);
                    this.bd.borrarFila("vuelo", "idVuelo", String.valueOf(dato));
                } else {
                }
            }

        }
        if (comando.equals("PrincipalModificar")) {
            int seleccion = this.principal.tablaVuelo.getSelectedRow();
            if (seleccion == -1) {
                JOptionPane.showMessageDialog(principal, "Para eliminar un vuelo tienes que seleccionar un vuelo", "Error al elimniar", JOptionPane.INFORMATION_MESSAGE);
            } else {
                Object dato = this.principal.tablaVuelo.getValueAt(seleccion, 0);
                ArrayList lista = new ArrayList();
                lista = bd.consultaFila("vuelo", "idVuelo", String.valueOf(dato), 6);
                //this.modeloVuelo = new Vuelo((Integer)lista.get(0),(Integer)lista.get(1),(String)lista.get(2),(String)lista.get(3),(String)lista.get(4),(String)lista.get(5));
                this.modeloVuelo = new Vuelo(
                        Integer.valueOf((String) lista.get(0)),
                        Integer.valueOf((String) lista.get(1)),
                        (String) lista.get(2),
                        (String) lista.get(3),
                        (String) lista.get(4),
                        (String) lista.get(5));
                System.out.println(this.modeloVuelo.getDestino());
                this.iVuelo.idVueloI.setText(String.valueOf(this.modeloVuelo.getIdVuelo()));
                this.iVuelo.idAvionI.setText(String.valueOf(this.modeloVuelo.getIdAvion()));
                this.iVuelo.origenI.setText(this.modeloVuelo.getOrigen());
                this.iVuelo.destinoI.setText(this.modeloVuelo.getDestino());
                this.iVuelo.horaLlegadaI.setText(this.modeloVuelo.getHoraLLegada());
                this.iVuelo.horaSalidaI.setText(this.modeloVuelo.getHoraSalida());
                this.operacionBaseDatos = "actualizarBD";
                this.posicionVuelo();
            }
        }
        //INTERFAZ VUELO
        if (comando.equals("AceptarVuelo")) {
            this.comprobarInterfazVuelo(this.operacionBaseDatos);
        }
        if (comando.equals("BorrarDatosVuelo")) {
            this.borraInterfazVuelo();
        }
    }
}
