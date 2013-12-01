
package Controlador;

import Modelo.Clases.ModeloLogin;
import Modelo.Clases.ModeloRegistro;
import Vista.Login;
import Vista.Registro;
import Vista.prueba;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.smartcardio.CommandAPDU;
import javax.swing.text.View;


public class Controlador implements ActionListener{

    
    private Login login; 
    private ModeloLogin modeloLogin;
    private prueba pru;
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
            nombre = login.textNombre.getText();
            password = login.textPassword.getText();
            
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
