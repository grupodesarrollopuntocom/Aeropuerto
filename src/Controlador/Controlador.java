
package Controlador;

import Archivos.Archivo;
import Modelo.Clases.ModeloLogin;
import Modelo.Clases.ModeloRegistro;
import Vista.Login;
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


public class Controlador implements ActionListener{

    private Archivo archivo = new Archivo();
    private Login login; 
    private ModeloLogin modeloLogin;
    
    private ModeloRegistro modeloRegistro = new ModeloRegistro();
    
    
    private Registro registro = new Registro(login, true);;
    
   
    

    public Controlador(Login login, ModeloLogin modeloLogin) {
        
        this.login = login;
        this.modeloLogin = modeloLogin;
        iniciar();
        
     }
    
    public void iniciar(){
       
       this.login.btEntrar.setActionCommand("Entrar");
       this.login.btEntrar.addActionListener(this);
       this.login.btRegistrar.setActionCommand("Registrar");
       this.login.btRegistrar.addActionListener(this);
       this.registro.botonAceptar.setActionCommand("Aceptar");
       this.registro.botonAceptar.addActionListener(this);
      
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    
        String comando = e.getActionCommand();
        
        
        //Acceso al boton aceptar de la interfaz login
        if(comando.equals("Entrar")){
        
           String nombre, password;
            
            Vector v = new Vector();
            int i = 0;
           
            
            
            
            nombre = login.textNombre.getText();
            password = login.textPassword.getText();
            
            v.addElement(nombre);
            v.addElement(password);
            
            while (i < 2){                
                
                
             archivo.crearTxt("logins.txt", v.elementAt(i).toString()+"\n");
                
                
            }
            
            
            modeloLogin.compruebaDatos(nombre, password);
            
            
            
          }   
        
        //Acceso al boton registrar de la interfaz login
        if(comando.equals("Registrar")){
           registro.setVisible(true);
        
        }
        
        
        //Acceso a el boton aceptar de la interfaz registro
        if(comando.equals("Aceptar")){
            
          String nombre, apellido1, apellido2, dni, telefono, direccion, correo, edad;  
          
          nombre = registro.textNombre.getText();
          apellido1 = registro.textApellido1.getText();
          apellido2 = registro.textApellido2.getText();
          dni = registro.textDireccion.getText();
          telefono = registro.textTelefono.getText();
          direccion = registro.textDireccion.getText();
          edad = registro.textEdad.getText();
          correo = registro.textCorreo.getText();
          
          modeloRegistro.recogeDatos(nombre, apellido1, apellido2, direccion, dni, edad, correo, telefono);
          modeloRegistro = new ModeloRegistro();
          
            modeloRegistro.recogeDatos(nombre, apellido1, apellido2, direccion, dni, edad, correo, telefono);
            modeloRegistro.guardaDatos(modeloRegistro);
            login.setVisible(true);
            System.out.println("entra");
            registro.dispose();
    }
      
        
        
 }
    
    
}
