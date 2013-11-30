
package Controlador;

import Modelo.Clases.ModeloLogin;
import Vista.Login;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.text.View;


public class Controlador implements ActionListener{

    
    private Login login; 
    private ModeloLogin modeloLogin;
    private String nombre, password;
    
    
    

    public Controlador(Login login, ModeloLogin modeloLogin) {
        
        this.login = login;
        this.modeloLogin = modeloLogin;
        iniciar();
        
     }
    
    public void iniciar(){
       
       this.login.btEntrar.setActionCommand("Entrar");
       this.login.btEntrar.addActionListener(this);
       
}
   
    @Override
    public void actionPerformed(ActionEvent e) {
    
        String comando = e.getActionCommand();
        
        if(comando.equals("Entrar")){
        
            nombre = login.textNombre.getText();
            password = login.textPassword.getText();
            
            modeloLogin.compruebaDatos(nombre, password);
            
            
       }    
     
  }
    
    
}
