package Modelo.JDBC;

import Modelo.Clases.Vuelo;
import java.util.ArrayList;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

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
                //System.out.println("Base de datos " + bd + " conectada");
            }
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    public Connection getConnection() {
        return this.conn;
    }

    // METODO PARA REALIZAR UNA CONSULTA A LA BASE DE DATOS DEL LOGIN
    public ArrayList consultaLogin() {
        ArrayList lista = new ArrayList();
        try {
            PreparedStatement pstm = this.conn.prepareStatement("SELECT * FROM personal");
            ResultSet res = pstm.executeQuery();
            while (res.next()) {
                //System.out.println (res.getInt (1) + " " + res.getString (2)+ " " + res.getString(3));
                lista.add(res.getString(2));
                lista.add(res.getString(3));
            }
            res.close();
        } catch (Exception e) {
            System.out.println("ERROR EN LAS CONSULTA");
        }
        return lista;
    }

    //METODO PARA REALIZAR UNA CONSULTA A LA BASE DE DATOS, DEVUELVE UN OBJECT[][] CON LOS DATOS DE LA TABLA
    public Object[][] consultaTablaVuelo() {
        int registros = 0;
        String consulta1 = "SELECT idvuelo,idAvion,Origen,Destino,HoraSalida,HoraLLegada FROM vuelo";
        String consulta2 = "select count(*) as total from vuelo";
        //obtenemos la cantidad de registros existentes en la tabla
        try {
            PreparedStatement pstm = conn.prepareStatement(consulta2);
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
        } catch (Exception e) {
            System.out.println("Error en contar los registros " + e.getMessage());
        }
        //se crea una matriz con tantas filas y columnas
        Object[][] datos = new String[registros][6];
        //realizamos la consulta sql y llenamos los datos en la matriz "Object"
        try {
            PreparedStatement pstm = conn.prepareStatement(consulta1);
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while (res.next()) {
                datos[i][0] = res.getString(1);
                datos[i][1] = res.getString(2);
                datos[i][2] = res.getString(3);
                datos[i][3] = res.getString(4);
                datos[i][4] = res.getString(5);
                datos[i][5] = res.getString(6);
                i++;
            }
            res.close();
        } catch (Exception e) {
            System.out.println("Error en la consulta de la tabla vuelo " + e.getMessage());
        }
        return datos;
    }

    //METODO PARA INTRODUCIR EN LA BASE DE DATOS
    public void ingresaDatosVuelo(Vuelo vu) {
        try {
            PreparedStatement pstm = conn.prepareStatement("INSERT INTO vuelo (idvuelo, idAvion, Origen, Destino, HoraSalida, HoraLLegada)"
                    + "VALUES(?, ?, ?, ?, ?, ?)");
            pstm.setString(1, String.valueOf(vu.getIdVuelo()));
            pstm.setString(2, String.valueOf(vu.getIdAvion()));
            pstm.setString(3, vu.getOrigen());
            pstm.setString(4, vu.getDestino());
            pstm.setString(5, vu.getHoraSalida());
            pstm.setString(6, vu.getHoraLLegada());
            int count = pstm.executeUpdate();
            System.out.println("Se han insertado: " + count);
            pstm.close();

        } catch (Exception ex) {
            System.out.println("ERROR AL INTRODUCIR DATOS VUELO");
        }

    }

    //CONSULTA DE UN DATO
    public boolean consultaIdPrincipal(String tabla, String columna, String dato) {
        boolean resultado;
        try {
            PreparedStatement pstm = conn.prepareStatement("SELECT " + columna + " FROM " + tabla + " WHERE " + columna + "=" + dato);
            ResultSet res = pstm.executeQuery();
            res.next();
            String obtenido = res.getString(1);
            System.out.println(obtenido);
            resultado = true;
            pstm.close();
        } catch (Exception ex) {
            resultado = false;
        }
        return resultado;
    }

    //ELIMINIAR DATOS DE LA TABLA VUELO
    public void borrarFila(String tabla, String columna, String dato) {
        try {
            PreparedStatement pstm = conn.prepareStatement("DELETE  FROM " + tabla + " WHERE " + columna + "=" + dato);
            int x = pstm.executeUpdate();
            System.out.println(x);
            pstm.close();
        } catch (Exception ex) {
            System.out.println("Error al elimnar datos");
        }
    }

    //CONSULTA UNA FILA
    public ArrayList consultaFila(String tabla, String columna, String dato, int numeroEntidades) {
        ArrayList lista = new ArrayList();
        try {
            PreparedStatement pstm = this.conn.prepareStatement("SELECT * FROM " + tabla + " WHERE " + columna + "=" + dato);
            ResultSet res = pstm.executeQuery();
            while (res.next()) {
                for (int i = 1; i <= numeroEntidades; i++) {
                    lista.add(res.getString(i));
                }
            }
            res.close();
        } catch (Exception e) {
            System.out.println("ERROR EN LAS CONSULTA");
        }
        return lista;
    }
    
    //ACTUALIZAR UNA FILA VUELO
    public void actulizaFila(Vuelo vuelo, String dato){
        try {
            PreparedStatement pstm = conn.prepareStatement("UPDATE vuelo "+ 
                    "SET idVuelo = ? ,"+
                    "idAvion = ? ,"+
                    "Origen = ? ,"+
                    "Destino = ? ,"+
                    "HoraSalida = ? ,"+
                    "HoraLLegada = ? "+
                    " WHERE idVuelo = ? ");
            pstm.setString(1, String.valueOf(vuelo.getIdVuelo()));
            pstm.setString(2, String.valueOf(vuelo.getIdAvion()));
            pstm.setString(3, vuelo.getOrigen());
            pstm.setString(4, vuelo.getDestino());
            pstm.setString(5, vuelo.getHoraSalida());
            pstm.setString(6, vuelo.getHoraLLegada());
            pstm.setString(7, dato);
            pstm.executeUpdate();
            pstm.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
