package Controlador;

import Archivos.Archivo;
import Modelo.Clases.ModeloLogin;
import Modelo.Clases.ModeloRegistro;
import Modelo.JDBC.BaseDatos;
import Vista.InterfazAvion;
import Vista.InterfazDatosPersona;
import Vista.Login;
import Vista.PrincipalVuelo;
import Vista.Registro;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.smartcardio.CommandAPDU;
import javax.swing.text.View;

public class Controlador implements ActionListener {

    private Archivo archivo = new Archivo();
    private Login login;
    private ModeloLogin modeloLogin;
    private ModeloRegistro modeloRegistro = new ModeloRegistro();
    private Registro registro = new Registro(login, true);
    private BaseDatos bd;
    private InterfazDatosPersona interfazDatosPersona = new InterfazDatosPersona(login, true);
    private PrincipalVuelo principalVuelo = new PrincipalVuelo();
    private InterfazAvion interfazAvion = new InterfazAvion(login, true);

    
    
   
    

    public Controlador(Login login, ModeloLogin modeloLogin) {

        this.login = login;
        this.modeloLogin = modeloLogin;
        iniciar();

    }

    public void iniciar() {

        this.login.btEntrar.setActionCommand("Entrar");
        this.login.btEntrar.addActionListener(this);
        this.login.btRegistrar.setActionCommand("Registrar");
        this.login.btRegistrar.addActionListener(this);
        this.registro.botonRegistroUsuario.setActionCommand("Registrar user");
        this.registro.botonRegistroUsuario.addActionListener(this);
        this.interfazDatosPersona.btAceptarPersona.setActionCommand("Aceptarpersona");
       this.interfazDatosPersona.btAceptarPersona.addActionListener(this);
        this.interfazAvion.btAceptarVuelo.setActionCommand("Aceptar vuelo");
        this.interfazAvion.btAceptarVuelo.addActionListener(this);
        this.principalVuelo.btLeer.setActionCommand("Lee");
        this.principalVuelo.btLeer.addActionListener(this);
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String comando = e.getActionCommand();

        //INTERFAZ LOGIN
        //Acceso al boton aceptar de la interfaz login
        if (comando.equals("Entrar")) {
           
            String nombre, password;
            Vector v = new Vector();
            int i = 0;
            nombre = login.textNombre.getText();
            password = login.textPassword.getText();
            v.addElement(nombre);
            v.addElement(password);
            
            /*for (int j = 0; j < v.size(); j++) {
                 System.out.println(v.get(j));
            }*/
            
           
           
            archivo.crearTxt("");
            modeloLogin.compruebaDatos(nombre, password);
            archivo.escribe(v);
            
           /*
            String nombre, password;
            nombre = login.textNombre.getText();
            password = login.textPassword.getText();
            bd = new BaseDatos();
            
            bd.consultaLogin();
            
            for (int i = 0; i < bd.consultaLogin().size(); i++) {
                System.out.println(bd.consultaLogin().get(i));
            
                if ((bd.consultaLogin().get(i).equals(nombre)) && (bd.consultaLogin().get(i+1).equals(password))) {
                    System.out.println("Primer nivel superado");
                    
                    i=bd.consultaLogin().size();
                    
               }
            
            }
            
            */

        }
        
        //Acceso al boton registrar de la interfaz login
        if (comando.equals("Registrar")) {
            
            registro.setVisible(true);

        }

        //INTERFAZ REGISTRO
        //Acceso a el boton aceptar de la interfaz registro
        if (comando.equals("Registrar user")) {

            String nombre, apellido1, apellido2, dni, telefono, direccion, correo, edad;
            Vector v = new Vector();
            
            
            nombre = registro.textNombre.getText();
            apellido1 = registro.textApellido1.getText();
            apellido2 = registro.textApellido2.getText();
            dni = registro.textDireccion.getText();
            telefono = registro.textTelefono.getText();
            direccion = registro.textDireccion.getText();
            edad = registro.textEdad.getText();
            correo = registro.textCorreo.getText();

            v.addElement(nombre);
            v.addElement(apellido1);
            v.addElement(apellido2);
            v.addElement(dni);
            v.addElement(telefono);
            v.addElement(direccion);
            v.addElement(edad);
            v.addElement(correo);
           
            
            archivo.crearTxt("");
            archivo.escribe(v);
            System.out.println("Entra");
            /*
            modeloRegistro.recogeDatos(nombre, apellido1, apellido2, direccion, dni, edad, correo, telefono);
            modeloRegistro = new ModeloRegistro();

            modeloRegistro.recogeDatos(nombre, apellido1, apellido2, direccion, dni, edad, correo, telefono);
            modeloRegistro.guardaDatos(modeloRegistro);
            login.setVisible(true);
            System.out.println("entra");
            registro.dispose();*/

        }
        
        //PARA LEER
        if (comando.equals("Lee")) {
            
            System.out.println(archivo.leer("archivo.txt"));
        }
        
        
        //INTERFAZ DATOS PERSONA
        
      
          if (comando.equals("Aceptarpersona")) {
              
              
            String nombre, apellido1, apellido2, dni, telefono, direccion, correo, edad;
            Vector v = new Vector();
            
            
            nombre = interfazDatosPersona.textNombre.getText();
            apellido1 = interfazDatosPersona.textApellido1.getText();
            apellido2 = interfazDatosPersona.textApellido2.getText();
            dni = interfazDatosPersona.textDireccion.getText();
            telefono = interfazDatosPersona.textTelefono.getText();
            direccion = interfazDatosPersona.textDireccion.getText();
            edad = interfazDatosPersona.textEdad.getText();
            correo = interfazDatosPersona.textCorreo.getText();

            v.addElement(nombre);
            v.addElement(apellido1);
            v.addElement(apellido2);
            v.addElement(dni);
            v.addElement(telefono);
            v.addElement(direccion);
            v.addElement(edad);
            v.addElement(correo);
           
            
            archivo.crearTxt("");
            archivo.escribe(v);
            System.out.println("Entraaaaaa");
            
          }
       
       //INTERFAZ AVION PARA AÑADIR VUELO
       if (comando.equals("Aceptar vuelo")) {
           
            String idVuelo, idAvion, Origen, Destino, Salida, Llegada;
            Vector v = new Vector();
            int i = 0;
            idVuelo = interfazAvion.textIdVuelo.getText();
            idAvion = interfazAvion.textIdAvion.getText();
            Origen = interfazAvion.textOrigen.getText();
            Destino = interfazAvion.textDestino.getText();
            Salida = interfazAvion.textSalida.getText();
            Llegada = interfazAvion.textLLegada.getText();
            
            v.addElement(idVuelo);
            v.addElement(idAvion);
            v.addElement(Origen);
            v.addElement(Destino);
            v.addElement(Salida);
            v.addElement(Llegada);
            
          
            
            archivo.crearTxt("");
            archivo.escribe(v);
            System.out.println("Saca vuelo");
       }
}
}