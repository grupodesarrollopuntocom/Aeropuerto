package Modelo.JDBC;

import java.util.ArrayList;
import java.sql.*;
public class BaseDatos {

    private String bd = "aeropuerto";
    private String login = "root";
    private String password = "root";
    private String url = "jdbc:mysql://localhost/" + bd;
    private Connection conn = null;

    public BaseDatos() {
        try {
            //obtenemos el driver de para mysql
            Class.forName("com.mysql.jdbc.Driver");
            //obtenemos la conexión
            conn = DriverManager.getConnection(url, login, password);
            if (conn != null) {
                System.out.println("Base de datos " + bd + " conectada");
            }
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    public Connection getConnection() {
        return this.conn;
    }
    /* METODO PARA REALIZAR UNA CONSULTA A LA BASE DE DATOS
     * 
     */
    public ArrayList consultaLogin(){
        ArrayList lista = new ArrayList();
        try {
            PreparedStatement pstm = conn.prepareStatement("SELECT * FROM personal");
            ResultSet res = pstm.executeQuery();
            while(res.next()){
                //System.out.println (res.getInt (1) + " " + res.getString (2)+ " " + res.getString(3)); 
                lista.add(res.getString(2));
                lista.add(res.getString(3));
                
            }
        } catch (Exception e) {
            System.out.println("ERROR EN LAS CONSULTA");
        }
        return lista;  
    }
}
