/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Clases;

import Vista.Login;
import Vista.PrincipalVuelo;
import javax.swing.JOptionPane;


public class ModeloLogin {
    
    Login login = new Login();
    PrincipalVuelo principalVuelo = new PrincipalVuelo();
    private String nombre, password;
    String nombreR = "Roberto",passwordP = "hola";

   
    public ModeloLogin() {
        
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public boolean compruebaDatos(String nombre, String password){
        
        boolean insertado = false;
        
        if(nombre.equals("r") && password.equals("1")){
           
            principalVuelo.setVisible(true);
            System.out.println("Entrar");
            insertado = true;
            
            
      }else{
        JOptionPane.showMessageDialog(login, "Usuario y contraseña no validos. Por favor introduzca nuevamente los datos", "Error en validacion", JOptionPane.ERROR_MESSAGE);
           
        System.out.println("No entra");
        }
        return insertado;
       
   }
    
}
