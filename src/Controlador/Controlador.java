package Controlador;

import Modelo.Clases.ModeloLogin;
import Modelo.Clases.Pasajero;
import Modelo.Clases.Tripulacion;
import Modelo.Clases.Vuelo;
import Modelo.JDBC.BaseDatos;
import Modelo.Lista.ModeloLista;
import Modelo.Tabla.ModeloTablaEquipaje;
import Modelo.Tabla.ModeloTablaPasajeros;
import Modelo.Tabla.ModeloTablaVuelo;
import Vista.IDatosTripulacion;
import Vista.InterfazDatosEquipaje;
import Vista.InterfazInfoPersona;
import Vista.InterfazPasajeros;
import Vista.InterfazTripulacion;
import Vista.InterfazVuelo;
import Vista.Login;
import Vista.Principal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.ListModel;

public class Controlador implements ActionListener {
    //VARIABLES DE APOYO

    private String operacionBaseDatos, dato;
    private boolean opcionTripulacio;
    //INTERFACES
    private Login login;
    private Principal principal = new Principal();
    private InterfazVuelo iVuelo = new InterfazVuelo(principal, true);
    private InterfazTripulacion iTripulacion = new InterfazTripulacion(principal, true);
    private IDatosTripulacion iDatosTripualcion = new IDatosTripulacion(principal, true);
    private InterfazPasajeros iPasajero = new InterfazPasajeros(principal, true);
    private InterfazInfoPersona iformacion = new InterfazInfoPersona(principal, true);
    private InterfazDatosEquipaje iEquipaje = new InterfazDatosEquipaje(principal, true);
    //MODELOS
    private ModeloLogin modeloLogin;
    private ModeloTablaVuelo modeloTablaVuelo;
    private ModeloTablaPasajeros modeloPasajero;
    private Vuelo modeloVuelo;
    private Tripulacion tripulacion;
    private Pasajero pasajero;
    private ModeloLista listaModelo = new ModeloLista();
    private ModeloTablaPasajeros modeloTablaPasajeros;
    private ModeloTablaEquipaje modeloTablaEquipaje;
    //private ModeloLista lista = new ModeloLista();
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
        this.principal.botonAyadirTripulacion.setActionCommand("PrincipalTripuacion");
        this.principal.botonAyadirTripulacion.addActionListener(this);
        this.principal.botonAyadirPasajero.setActionCommand("PrincipalPasajero");
        this.principal.botonAyadirPasajero.addActionListener(this);
        //BOTONES INTERFAZ VUELO
        this.iVuelo.aceptarBvuelo.setActionCommand("AceptarVuelo");
        this.iVuelo.aceptarBvuelo.addActionListener(this);
        this.iVuelo.limpiarBvuelo.setActionCommand("BorrarDatosVuelo");
        this.iVuelo.limpiarBvuelo.addActionListener(this);
        //BOTONES INTERFAZ TRIPULACION
        this.iTripulacion.botonAnadirT.setActionCommand("AñadirTripulacion");
        this.iTripulacion.botonAnadirT.addActionListener(this);
        this.iTripulacion.botonModifiarT.setActionCommand("ModificarTripulacion");
        this.iTripulacion.botonModifiarT.addActionListener(this);
        this.iTripulacion.botonBorrarT.setActionCommand("BorrarTripulante");
        this.iTripulacion.botonBorrarT.addActionListener(this);
        this.iTripulacion.botonVerT.setActionCommand("VerTripulacin");
        this.iTripulacion.botonVerT.addActionListener(this);
        //BOTONES INTERFAZ DATOS TRIPULACION
        this.iDatosTripualcion.botonAceptarT.setActionCommand("AceptarTripualcion");
        this.iDatosTripualcion.botonAceptarT.addActionListener(this);
        //BOTONES INTERFAZ PASAJEROS
        this.iPasajero.botonAñadirPasajero.setActionCommand("AñadirPasajero");
        this.iPasajero.botonAñadirPasajero.addActionListener(this);
        this.iPasajero.botonBorrarPasajero.setActionCommand("BorrarPasajero");
        this.iPasajero.botonBorrarPasajero.addActionListener(this);
        this.iPasajero.botonModificaraPasajero.setActionCommand("ModificarPasajero");
        this.iPasajero.botonModificaraPasajero.addActionListener(this);
    }

    //METODOS DE APOYO
    //limpira campos vuelo
    public void borraInterfazVuelo() {
        this.iVuelo.idVueloI.setText(null);
        this.iVuelo.idAvionI.setText(null);
        this.iVuelo.origenI.setText(null);
        this.iVuelo.destinoI.setText(null);
        this.iVuelo.horaSalidaI.setText(null);
        this.iVuelo.horaLlegadaI.setText(null);
    }
    //limpiar campos tripulacion

    public void borrasInterfazDatosTripulacion() {
        this.iDatosTripualcion.nombreT.setText(null);
        this.iDatosTripualcion.apellido1T.setText(null);
        this.iDatosTripualcion.apellido2T.setText(null);
        this.iDatosTripualcion.dniT.setText(null);
        this.iDatosTripualcion.edadT.setText(null);
        this.iDatosTripualcion.telefonoT.setText(null);
        this.iDatosTripualcion.coElectronicoT.setText(null);
        this.iDatosTripualcion.direccionT.setText(null);
    }

    public void eleccionTripulacion(Boolean selecion) {
        if (selecion) {
            if (compruebaDatosTripulacion()) {
                int numero = bd.consultaUltimoRegistro("tripulacion", "idtripulacion");
                String persona = bd.consultaUnString("nombre", "tripulacion", String.valueOf(numero));
                String dniper = bd.consultaUnString("dni", "tripulacion", String.valueOf(numero));
                String cateper = bd.consultaUnString("categoria", "tripulacion", String.valueOf(numero));
                int dato = this.principal.tablaVuelo.getSelectedRow();
                this.iDatosTripualcion.dispose();
                listaModelo.anyadirElemento(dniper + " " + persona + " " + cateper);
            }
        } else {
            if (compruebaDatosPasajeros()) {
                int num = JOptionPane.showConfirmDialog(iVuelo, "Atención esta apunto de introducir datos en tabla pasajeors. ¿Desea continuar con la operacion?",
                        "INFORMACION", JOptionPane.YES_NO_OPTION);
                if (num == 0) {
                    Object[] lista = new String[8];
                    lista[0] = String.valueOf(this.pasajero.getIdPasajero());
                    lista[1] = this.pasajero.getDni();
                    lista[2] = this.pasajero.getNombre();
                    lista[3] = this.pasajero.getApellido1();
                    lista[4] = this.pasajero.getApellido2();
                    lista[5] = String.valueOf(this.pasajero.getEdad());
                    lista[6] = String.valueOf(this.pasajero.getTelefono());
                    lista[7] = String.valueOf(this.pasajero.getIdVuelo());
                    this.modeloTablaPasajeros.anadirFila(lista);
                    bd.ingresaDatosPasajero(pasajero);
                    this.iDatosTripualcion.dispose();
                }

            }
        }
    }

    //comprobar campos
    public void comprobarInterfazVuelo(String operacion, String dato) {
        operacion = this.operacionBaseDatos;
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
                if (!this.bd.consultaUnDato("vuelo", "idVuelo", String.valueOf(this.modeloVuelo.getIdVuelo()))) {
                    JOptionPane.showMessageDialog(iVuelo, "La id del vuelo ya esta asociado a atro vuelo Introduce una  ID de vuelo diferente", "Id Vuelo", JOptionPane.ERROR_MESSAGE);
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
                        int seleccion = this.principal.tablaVuelo.getSelectedRow();
                        this.modeloTablaVuelo.borrarFila(seleccion);
                        this.bd.actulizaFila(this.modeloVuelo, this.dato);
                        this.modeloTablaVuelo.anadirFila(lista);
                        ///NO SE QUE HACER PARA ACTUALIZAR LA TABLA ?????????????????????
                    }
                    borraInterfazVuelo();
                    this.iVuelo.dispose();

                }
            }
        }

    }

    public boolean compruebaDatosTripulacion() {
        int seleccion = this.principal.tablaVuelo.getSelectedRow();
        Object dato = this.principal.tablaVuelo.getValueAt(seleccion, 0);
        boolean comprueba = false;
        String nombre, apellido1, apellido2, dni, correoElectronico, direccion, categoria, telefono, edad;
        int idTripulacion = 0;
        nombre = this.iDatosTripualcion.nombreT.getText();
        apellido1 = this.iDatosTripualcion.apellido1T.getText();
        apellido2 = this.iDatosTripualcion.apellido2T.getText();
        edad = this.iDatosTripualcion.edadT.getText();
        dni = this.iDatosTripualcion.dniT.getText();
        telefono = this.iDatosTripualcion.telefonoT.getText();
        correoElectronico = this.iDatosTripualcion.coElectronicoT.getText();
        direccion = this.iDatosTripualcion.direccionT.getText();
        categoria = "sin categoria";
        if (nombre.equals("") || apellido1.equals("") || apellido2.equals("") || dni.equals("") || telefono.equals("") || correoElectronico.equals("") || direccion.equals("")) {
            JOptionPane.showMessageDialog(iDatosTripualcion, "Asegúrate que todos los campos obligatorios estén llenos", "Faltan rellenar campos", JOptionPane.ERROR_MESSAGE);
        } else if (!this.iDatosTripualcion.buttonPiloto.isSelected()
                && !this.iDatosTripualcion.buttonCopiloto.isSelected()
                && !this.iDatosTripualcion.buttonAzafata.isSelected()) {
            JOptionPane.showMessageDialog(iDatosTripualcion, "Tienes que elegir una categoria para el  tripulante", "Un paso mas", JOptionPane.ERROR_MESSAGE);
        } else {
            if (this.iDatosTripualcion.buttonPiloto.isSelected()) {
                categoria = "piloto";
            } else if (this.iDatosTripualcion.buttonCopiloto.isSelected()) {
                categoria = "copiloto";
            } else if (this.iDatosTripualcion.buttonAzafata.isSelected()) {
                categoria = "azafata";
            }
            idTripulacion = this.bd.consultaUltimoRegistro("tripulacion", "idTripulacion") + 1;
            this.tripulacion = new Tripulacion(idTripulacion, categoria, nombre, apellido1, apellido2, Integer.valueOf(edad), Integer.valueOf(telefono), correoElectronico, direccion, dni);
            if (!this.tripulacion.esPalabraCaracteres(nombre)) {
                JOptionPane.showMessageDialog(iDatosTripualcion, "El nombre introducido es incorrecto", "Nombre", JOptionPane.ERROR_MESSAGE);
                this.iDatosTripualcion.nombreT.setText(null);
                this.iDatosTripualcion.nombreT.requestFocus();
            } else if (!this.tripulacion.esPalabraCaracteres(apellido1)) {
                JOptionPane.showMessageDialog(iDatosTripualcion, "El primer apellido introducido es incorrecto", "Primer Apellido", JOptionPane.ERROR_MESSAGE);
                this.iDatosTripualcion.apellido1T.setText(null);
                this.iDatosTripualcion.apellido1T.requestFocus();
            } else if (!this.tripulacion.esPalabraCaracteres(apellido2)) {
                JOptionPane.showMessageDialog(iDatosTripualcion, "El segundo apellido introducido es incorrecto", "Segundo Apellido", JOptionPane.ERROR_MESSAGE);
                this.iDatosTripualcion.apellido2T.setText(null);
            } else if (!this.tripulacion.comprobarEmail(correoElectronico)) {
                JOptionPane.showMessageDialog(iDatosTripualcion, "El correo introducido es incorrecto", "Email", JOptionPane.ERROR_MESSAGE);
                this.iDatosTripualcion.apellido2T.setText(null);
                this.iDatosTripualcion.apellido2T.requestFocus();
            } else if (!this.tripulacion.compruebaEdad(Integer.valueOf(edad))) {
                JOptionPane.showMessageDialog(iDatosTripualcion, "La edad introducida es incorrecta", "Edad", JOptionPane.ERROR_MESSAGE);
                this.iDatosTripualcion.edadT.setText(null);
                this.iDatosTripualcion.edadT.requestFocus();
            } else if (!this.tripulacion.compruebaNumeroTelefono(telefono)) {
                JOptionPane.showMessageDialog(iDatosTripualcion, "El telefono introducido es incorrecto", "Telefono", JOptionPane.ERROR_MESSAGE);
                this.iDatosTripualcion.telefonoT.setText(null);
                this.iDatosTripualcion.telefonoT.requestFocus();
            } else if (!this.tripulacion.comprobarDni(dni)) {
                JOptionPane.showMessageDialog(iDatosTripualcion, "El DNI introducido es incorrecto", "DNI", JOptionPane.ERROR_MESSAGE);
                this.iDatosTripualcion.dniT.setText(null);
                this.iDatosTripualcion.dniT.requestFocus();
            } else if (!bd.consultaUnDato("tripulacion", "dni", dni)) {
                JOptionPane.showMessageDialog(iDatosTripualcion, "El DNI que va a introducir ya esta en uso escriba otro DNI", "DNI", JOptionPane.INFORMATION_MESSAGE);
                this.iDatosTripualcion.dniT.setText(null);
                this.iDatosTripualcion.dniT.requestFocus();
            } else {
                int num = JOptionPane.showConfirmDialog(iDatosTripualcion, "Atención esta apunto de introducir un tripulante en un vuelo. ¿Desea continuar con la operacion?", "INFORMACION", JOptionPane.YES_NO_OPTION);
                if (num == 0) {
                    bd.ingresaDatosTripulacion(tripulacion, String.valueOf(dato));
                    comprueba = true;
                }
            }
        }
        return comprueba;
    }

    //comprueba pasajero
    public boolean compruebaDatosPasajeros() {
        int seleccion = this.principal.tablaVuelo.getSelectedRow();
        Object dato = this.principal.tablaVuelo.getValueAt(seleccion, 0);
        boolean comprueba = false;
        String nombre, apellido1, apellido2, dni, correoElectronico, direccion, clase, telefono, edad;
        int idPasajero = 0;
        nombre = this.iDatosTripualcion.nombreT.getText();
        apellido1 = this.iDatosTripualcion.apellido1T.getText();
        apellido2 = this.iDatosTripualcion.apellido2T.getText();
        edad = this.iDatosTripualcion.edadT.getText();
        dni = this.iDatosTripualcion.dniT.getText();
        telefono = this.iDatosTripualcion.telefonoT.getText();
        correoElectronico = this.iDatosTripualcion.coElectronicoT.getText();
        direccion = this.iDatosTripualcion.direccionT.getText();
        clase = "sin categoria";
        if (nombre.equals("") || apellido1.equals("") || apellido2.equals("") || dni.equals("") || telefono.equals("") || correoElectronico.equals("") || direccion.equals("")) {
            JOptionPane.showMessageDialog(iDatosTripualcion, "Asegúrate que todos los campos obligatorios estén llenos", "Faltan rellenar campos", JOptionPane.ERROR_MESSAGE);
        } else {
            if (this.iDatosTripualcion.comboT.getSelectedIndex() == 0) {
                clase = "primera";
            } else if (this.iDatosTripualcion.comboT.getSelectedIndex() == 1) {
                clase = "segunda";
            } else if (this.iDatosTripualcion.comboT.getSelectedIndex() == 2) {
                clase = "tercera";
            }
            idPasajero = this.bd.consultaUltimoRegistro("pasajeros", "idpasajeros") + 1;
            this.pasajero = new Pasajero(idPasajero, clase, Integer.valueOf(String.valueOf(dato)), nombre, apellido1, apellido2, Integer.valueOf(edad), Integer.valueOf(telefono), correoElectronico, direccion, dni);
            if (!this.pasajero.esPalabraCaracteres(nombre)) {
                JOptionPane.showMessageDialog(iDatosTripualcion, "El nombre introducido es incorrecto", "Nombre", JOptionPane.ERROR_MESSAGE);
                this.iDatosTripualcion.nombreT.setText(null);
                this.iDatosTripualcion.nombreT.requestFocus();
            } else if (!this.pasajero.esPalabraCaracteres(apellido1)) {
                JOptionPane.showMessageDialog(iDatosTripualcion, "El primer apellido introducido es incorrecto", "Primer Apellido", JOptionPane.ERROR_MESSAGE);
                this.iDatosTripualcion.apellido1T.setText(null);
                this.iDatosTripualcion.apellido1T.requestFocus();
            } else if (!this.pasajero.esPalabraCaracteres(apellido2)) {
                JOptionPane.showMessageDialog(iDatosTripualcion, "El segundo apellido introducido es incorrecto", "Segundo Apellido", JOptionPane.ERROR_MESSAGE);
                this.iDatosTripualcion.apellido2T.setText(null);
            } else if (!this.pasajero.comprobarEmail(correoElectronico)) {
                JOptionPane.showMessageDialog(iDatosTripualcion, "El correo introducido es incorrecto", "Email", JOptionPane.ERROR_MESSAGE);
                this.iDatosTripualcion.apellido2T.setText(null);
                this.iDatosTripualcion.apellido2T.requestFocus();
            } else if (!this.pasajero.compruebaEdad(Integer.valueOf(edad))) {
                JOptionPane.showMessageDialog(iDatosTripualcion, "La edad introducida es incorrecta", "Edad", JOptionPane.ERROR_MESSAGE);
                this.iDatosTripualcion.edadT.setText(null);
                this.iDatosTripualcion.edadT.requestFocus();
            } else if (!this.pasajero.compruebaNumeroTelefono(telefono)) {
                JOptionPane.showMessageDialog(iDatosTripualcion, "El telefono introducido es incorrecto", "Telefono", JOptionPane.ERROR_MESSAGE);
                this.iDatosTripualcion.telefonoT.setText(null);
                this.iDatosTripualcion.telefonoT.requestFocus();
            } else if (!this.pasajero.comprobarDni(dni)) {
                JOptionPane.showMessageDialog(iDatosTripualcion, "El DNI introducido es incorrecto", "DNI", JOptionPane.ERROR_MESSAGE);
                this.iDatosTripualcion.dniT.setText(null);
                this.iDatosTripualcion.dniT.requestFocus();
            } else if (!bd.consultaUnDato("pasajeros", "dni", dni)) {
                JOptionPane.showMessageDialog(iDatosTripualcion, "El DNI que va a introducir ya esta en uso escriba otro DNI", "DNI", JOptionPane.INFORMATION_MESSAGE);
                this.iDatosTripualcion.dniT.setText(null);
                this.iDatosTripualcion.dniT.requestFocus();
            } else {
                comprueba = true;
            }

        }
        return comprueba;
    }

    //posicionamiento del Jdialog
    public void posicionJdialog(JDialog jd) {
        jd.setLocationRelativeTo(null);
        jd.setResizable(false);
        jd.setVisible(true);

    }

    public void limpiarLista() {
        DefaultListModel model = new DefaultListModel();
        this.iTripulacion.listaTri.setModel(model);
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
                this.principal.tablaVuelo.getTableHeader().setReorderingAllowed(false);
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
            this.posicionJdialog(this.iVuelo);
        }
        if (comando.equals("PrincipalEliminar")) {
            int seleccion = this.principal.tablaVuelo.getSelectedRow();
            if (seleccion == -1) {
                JOptionPane.showMessageDialog(principal, "Para modificar un vuelo tienes que seleccionar un vuelo", "Avertencia", JOptionPane.INFORMATION_MESSAGE);
            } else {
                Object dato = this.principal.tablaVuelo.getValueAt(seleccion, 0);
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
                this.iVuelo.idVueloI.setText(String.valueOf(this.modeloVuelo.getIdVuelo()));
                this.iVuelo.idAvionI.setText(String.valueOf(this.modeloVuelo.getIdAvion()));
                this.iVuelo.origenI.setText(this.modeloVuelo.getOrigen());
                this.iVuelo.destinoI.setText(this.modeloVuelo.getDestino());
                this.iVuelo.horaLlegadaI.setText(this.modeloVuelo.getHoraLLegada());
                this.iVuelo.horaSalidaI.setText(this.modeloVuelo.getHoraSalida());
                this.operacionBaseDatos = "actualizarBD";
                this.dato = String.valueOf(this.modeloVuelo.getIdVuelo());
                this.posicionJdialog(this.iVuelo);
            }
        }
        if (comando.equals("PrincipalTripuacion")) {
            int seleccion = this.principal.tablaVuelo.getSelectedRow();
            if (seleccion != -1) {
                listaModelo = new ModeloLista();
                int dato = Integer.valueOf((String) this.principal.tablaVuelo.getValueAt(seleccion, 0));
                ArrayList listaTripulacion = bd.consultaDatosTripulacion(String.valueOf(dato), "nombre");
                ArrayList listaTripulacion2 = bd.consultaDatosTripulacion(String.valueOf(dato), "dni");
                ArrayList listaTripulacion3 = bd.consultaDatosTripulacion(String.valueOf(dato), "categoria");
                for (int i = 0; i < listaTripulacion.size(); i++) {
                    listaModelo.anyadirElemento(listaTripulacion2.get(i) + " " + listaTripulacion.get(i) + " " + listaTripulacion3.get(i));
                }
                this.iTripulacion.listaTri.setModel(listaModelo);
                this.posicionJdialog(this.iTripulacion);
            } else {
                JOptionPane.showMessageDialog(principal, "Para añadir tripulacion debes seleccionar un vuelo ", "Intetalo otra vez", JOptionPane.QUESTION_MESSAGE);
            }

        }
        if (comando.equals("PrincipalPasajero")) {
            int seleccion = this.principal.tablaVuelo.getSelectedRow();
            if (seleccion != -1) {
                Object dato = this.principal.tablaVuelo.getValueAt(seleccion, 0);
                ArrayList lista = new ArrayList();
                this.modeloTablaPasajeros = new ModeloTablaPasajeros((String) dato);
                this.iPasajero.tablaPasajero.setModel(this.modeloTablaPasajeros);
                this.iPasajero.tablaPasajero.getTableHeader().setReorderingAllowed(false);
                this.posicionJdialog(this.iPasajero);
            } else {
                JOptionPane.showMessageDialog(principal, "Para añadir tripulacion debes seleccionar un vuelo ", "Intetalo otra vez", JOptionPane.QUESTION_MESSAGE);
            }
        }
        if (comando.equals("BotonEquipaje")) {
            int seleccion = this.principal.tablaVuelo.getSelectedRow();
            if (seleccion != -1) {
                this.posicionJdialog(this.iEquipaje);
            }else {
                JOptionPane.showMessageDialog(principal, "Para ir a Equipaje tienes que seleccionar un vuelo ", "Intetalo otra vez", JOptionPane.QUESTION_MESSAGE);
            }
        }
        //INTERFAZ VUELO
        if (comando.equals("AceptarVuelo")) {
            this.comprobarInterfazVuelo(this.operacionBaseDatos, this.dato);
        }
        if (comando.equals("BorrarDatosVuelo")) {
            this.borraInterfazVuelo();
        }

        //INTERFAZ TRIPULACION
        if (comando.equals("AñadirTripulacion")) {
            opcionTripulacio = true;
            this.iDatosTripualcion.paneinvisible.setVisible(true);
            this.iDatosTripualcion.buttonAzafata.setVisible(true);
            this.iDatosTripualcion.buttonPiloto.setVisible(true);
            this.iDatosTripualcion.buttonCopiloto.setVisible(true);
            this.iDatosTripualcion.comboT.setVisible(false);
            this.iDatosTripualcion.textoClase.setVisible(false);
            this.borrasInterfazDatosTripulacion();
            this.posicionJdialog(this.iDatosTripualcion);
        }
        if (comando.equals("BorrarTripulante")) {
            int selList = this.iTripulacion.listaTri.getSelectedIndex();
            if (selList != -1) {
                int num = JOptionPane.showConfirmDialog(iTripulacion, "Atención esta apunto de eliminar un tripulante en un vuelo. ¿Desea continuar con la operacion?", "INFORMACION", JOptionPane.YES_NO_OPTION);
                if (num == 0) {
                    String pru = (String) this.iTripulacion.listaTri.getSelectedValue();
                    String total = pru.substring(0, pru.indexOf(" "));
                    bd.borrarFila("tripulacion", "dni", total.trim());
                    ((ModeloLista) (this.iTripulacion.listaTri.getModel())).eliminarElemento(selList);
                    this.iTripulacion.listaTri.repaint();
                    JOptionPane.showMessageDialog(iTripulacion, "El tripulante con el DNI " + total + " ha sido eliminado con exito. ", "Exito", JOptionPane.QUESTION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(iTripulacion, "Para eliminar un tripulante debes seleccionar a un tripulante ", "Intetalo otra vez", JOptionPane.QUESTION_MESSAGE);
            }
        }
        //INTERFAZ DATOS TRIPULACION
        if (comando.equals("AceptarTripualcion")) {
            eleccionTripulacion(opcionTripulacio);
        }
        if (comando.equals("ModificarTripulacion")) {
            ArrayList listaTripulacion = new ArrayList();
            int selList = this.iTripulacion.listaTri.getSelectedIndex();
            String pru = (String) this.iTripulacion.listaTri.getSelectedValue();
            String total = pru.substring(0, pru.indexOf(" "));
            this.borrasInterfazDatosTripulacion();
            listaTripulacion = bd.consultaDtosTripulacion(total);
            this.iDatosTripualcion.nombreT.setText((String) listaTripulacion.get(0));
            this.iDatosTripualcion.apellido1T.setText((String) listaTripulacion.get(1));
            this.iDatosTripualcion.apellido2T.setText((String) listaTripulacion.get(2));
            this.iDatosTripualcion.dniT.setText((String) listaTripulacion.get(3));
            this.iDatosTripualcion.edadT.setText((String) listaTripulacion.get(4));
            this.iDatosTripualcion.telefonoT.setText((String) listaTripulacion.get(5));
            this.iDatosTripualcion.coElectronicoT.setText((String) listaTripulacion.get(6));
//            this.iDatosTripualcion.direccionT.setText((String) listaTripulacion.get(5));
            this.posicionJdialog(this.iDatosTripualcion);
        }
        if (comando.equals("VerTripulacin")) {
            ArrayList listaTripulacion = new ArrayList();
            int selList = this.iTripulacion.listaTri.getSelectedIndex();
            if (selList != -1) {
                String pru = (String) this.iTripulacion.listaTri.getSelectedValue();
                String total = pru.substring(0, pru.indexOf(" "));
                listaTripulacion = bd.consultaDtosTripulacion(total);
                this.iformacion.nombre.setText((String) listaTripulacion.get(0));
                this.iformacion.apellido1.setText((String) listaTripulacion.get(1));
                this.iformacion.apellido2.setText((String) listaTripulacion.get(2));
                this.iformacion.dni.setText((String) listaTripulacion.get(3));
                this.iformacion.edad.setText((String) listaTripulacion.get(4));
                this.iformacion.telefono.setText((String) listaTripulacion.get(5));
                this.iformacion.correo.setText((String) listaTripulacion.get(6));
                this.posicionJdialog(this.iformacion);
            } else {
                JOptionPane.showMessageDialog(iTripulacion, "Para ver un tripulante debes seleccionar a un tripulante ", "Intetalo otra vez", JOptionPane.ERROR_MESSAGE);
            }
        }
        //INTERFAZ PASAJERO
        if (comando.equals("AñadirPasajero")) {
            opcionTripulacio = false;
            this.iDatosTripualcion.comboT.setVisible(true);
            this.iDatosTripualcion.paneinvisible.setVisible(false);
            this.borrasInterfazDatosTripulacion();
            this.posicionJdialog(this.iDatosTripualcion);
        }
        if (comando.equals("BorrarPasajero")) {
            int seleccion = this.iPasajero.tablaPasajero.getSelectedRow();
            if (seleccion == -1) {
                JOptionPane.showMessageDialog(iPasajero, "Para modificar un pasajero selecciona un pasajero", "Avertencia", JOptionPane.INFORMATION_MESSAGE);
            } else {
                Object dato = this.iPasajero.tablaPasajero.getValueAt(seleccion, 0);
                int opc = JOptionPane.showConfirmDialog(iPasajero, "Atención esta apunto de borrar a un pasajero de la tabla. ¿Desea continuar con la operacion?",
                        "INFORMACION", JOptionPane.YES_NO_OPTION);
                if (opc == 0) {
                    this.modeloTablaPasajeros.borrarFila(seleccion);
                    this.bd.borrarFila("pasajeros", "idpasajeros", String.valueOf(dato));
                }
            }
        }
        if (comando.equals("ModificarPasajero")) {
            int seleccion = this.iPasajero.tablaPasajero.getSelectedRow();
            if (seleccion == -1) {
                JOptionPane.showMessageDialog(iPasajero, "Para eliminar un pasajero tienes que seleccionar un pasajero", "Error al elimniar", JOptionPane.INFORMATION_MESSAGE);
            } else {
                Object dato = this.iPasajero.tablaPasajero.getValueAt(seleccion, 0);
                ArrayList lista = new ArrayList();
                lista = bd.consultaFila("pasajeros", "idpasajeros", String.valueOf(dato), 11);
                Pasajero pasajero = new Pasajero(
                        Integer.valueOf(String.valueOf(lista.get(0))),
                        (String) lista.get(1),
                        Integer.valueOf(String.valueOf(lista.get(0))),
                        (String) lista.get(3),
                        (String) lista.get(4),
                        (String) lista.get(5),
                        Integer.valueOf(String.valueOf(lista.get(6))),
                        Integer.valueOf(String.valueOf(lista.get(7))),
                        (String) lista.get(8),
                        (String) lista.get(9),
                        (String) lista.get(10));
                this.iDatosTripualcion.nombreT.setText(pasajero.getNombre());
                this.iDatosTripualcion.apellido1T.setText(pasajero.getApellido1());
                this.iDatosTripualcion.apellido2T.setText(pasajero.getApellido2());
                this.iDatosTripualcion.dniT.setText(pasajero.getDni());
                this.iDatosTripualcion.edadT.setText(String.valueOf(pasajero.getEdad()));
                this.iDatosTripualcion.telefonoT.setText(String.valueOf(pasajero.getTelefono()));
                this.iDatosTripualcion.coElectronicoT.setText(pasajero.getCorreoElectronico());
                this.iDatosTripualcion.direccionT.setText(pasajero.getDireccion());
                //this.dato = String.valueOf(this.pasajero.getIdPasajero());
                this.posicionJdialog(this.iDatosTripualcion);
            }
        }

    }
}
