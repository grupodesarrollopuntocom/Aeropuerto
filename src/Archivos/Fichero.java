
package Archivos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;


public class Fichero {
    
    File f;
    
    public void lee(){
    
     //creo el flujo
    f = new File("C:\Documents and Settings\Roberto\Desktop");
    FileReader fr;
    
        try {
            
            fr = new FileReader(f);   //paso el flujo a f
            BufferedReader br = new BufferedReader(fr); //permito la lectura
            String linea = br.readLine(); //creo string para tomar primer valor de la linea
            
            while (linea!=null) {  //mientras haya datos en el archivo               
                linea = br.readLine();  //escribo
                System.out.println(linea);
            }
            
            fr.close();
            
        } catch (Exception e) {
            
            System.out.println("Error al acceder al archivo");
        }
    
    
    }
    
    public void escribe(){
        
        File f;              //creo el archivo
        FileWriter fw;
        PrintWriter pw;
        
        try {
            
            fw = new FileWriter("C:\Documents and Settings\Roberto\Desktop");  //creo el flujo hacia f
            pw = new PrintWriter(fw); //permite la escritura
            
            for (int i = 0; i < 10; i++) {
                pw.println(i);          //se introduce valor de i en el archivo
            }
            
            fw.close();
        } catch (Exception e) {
            
            System.out.println("Error al escribir");
        }
        
    }
   
    
}
