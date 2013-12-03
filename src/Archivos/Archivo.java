
package Archivos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class Archivo {
 
    File f;
    FileReader lectorArchivo;
    FileWriter escritorArchivo;
    
    public void concatenar(String nombre, String texto) throws FileNotFoundException, IOException{
        
        String temp = this.leer(nombre);
        temp = temp + texto;
        this.crearTxt(nombre, temp);
        
    }
    
    
    
    public void crearTxt(String nombre, String texto){
        
        File f;
        FileWriter escritorArchivo;
        try {
            f = new File(nombre);
            escritorArchivo = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(escritorArchivo);
            PrintWriter salida = new PrintWriter(bw);
            
            
            salida.write(texto+"\n");
            
            salida.close();
            bw.close();
            
        } catch (Exception e) {
        }
        
   }
    
    public String leer(String nombre) throws FileNotFoundException, IOException{
        
        
        File f;
        FileReader lectorArchivo;
        
        try {
            
        f = new File(nombre);
        lectorArchivo = new FileReader(f);
        BufferedReader br = new BufferedReader(lectorArchivo);
        String l= "";
        String aux = "";
        
        while(true){
            
            aux = br.readLine();
            if(aux!=null)
                l = l+aux+"\n";
            else
                break;
            }

        br.close();
        lectorArchivo.close();
        return l;
        
         } catch (Exception e) {
             
             System.out.println("Error:"+e.getMessage());
        }
        return null;
        
    }
    
 }
