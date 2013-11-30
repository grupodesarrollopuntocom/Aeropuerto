/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Clases;


public class ModeloLogin {
    
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
        
        if(nombre.equals("Roberto") && password.equals("1234")){
           
            
            System.out.println("Entrar");
            insertado = true;
            
            
      }else{
            
        System.out.println("No entra");
        }
        return insertado;
       
   }
    
}
