package Modelo.JDBC;

import Modelo.Clases.Pasajero;
import Modelo.Clases.Tripulacion;
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
    private String columna;

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

    /**
     * *****************************OPERACIONES CON TODAS LAS
     * TABLAS************
     *
     */
    //CONSULTA DE UN DATO
    public boolean consultaUnDato(String tabla, String columna, String dato) {
        boolean resultado = true;
        try {
            PreparedStatement pstm = conn.prepareStatement("SELECT " + columna + " FROM " + tabla + " WHERE " + columna + "=" + "\"" + dato + "\"");
            ResultSet res = pstm.executeQuery();
            if (res.next() == true) {
                System.out.println("Hay datos");
                resultado = false;
            } else {
                System.out.println("No hay datos");
                resultado = true;
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return resultado;
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

    //ELIMINIAR UN DATO DE LA TABLA
    public void borrarFila(String tabla, String columna, String dato) {
        try {
            PreparedStatement pstm = conn.prepareStatement("DELETE  FROM " + tabla + " WHERE " + columna + "=" + "\"" + dato + "\"");
            int x = pstm.executeUpdate();
            System.out.println(x);
            pstm.close();
        } catch (Exception ex) {
            System.out.println("Error al elimnar datos");
        }
    }

    //CONSULTA EL ULTIMO DATO INDTRODUCIDO EN UNA FILA
    public int consultaUltimoRegistro(String tabla, String id) {
        int dato = 0;
        try {
            PreparedStatement pstm = conn.prepareStatement("SELECT " + id + " FROM " + tabla + " ORDER BY " + id + " DESC LIMIT 1 ");
            ResultSet res = pstm.executeQuery();
            res.next();
            dato = res.getInt(1);
        } catch (Exception ex) {
            System.out.println("ERROR EN EL LEER EL ULTIMO REGITRO");
        }
        return dato;
    }

    /**
     * ******************* OPERACIONES VUELO****************************
     */
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
    //METODO PARA INTRODUCIR VUELO EN LA BASE DE DATOS

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

    //ACTUALIZAR UNA FILA VUELO
    public void actulizaFila(Vuelo vuelo, String dato) {
        try {
            PreparedStatement pstm = conn.prepareStatement("UPDATE vuelo "
                    + "SET idVuelo = ? ,"
                    + "idAvion = ? ,"
                    + "Origen = ? ,"
                    + "Destino = ? ,"
                    + "HoraSalida = ? ,"
                    + "HoraLLegada = ? "
                    + " WHERE idVuelo = ? ");
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

    /**
     * ******************* OPERACIONES Tripulacion****************************
     */
    //Insertar datos en la tabla tripulacion
    public void ingresaDatosTripulacion(Tripulacion tri, String numero) {
        try {
            PreparedStatement pstm = conn.prepareStatement("INSERT INTO tripulacion (idtripulacion, dni, nombre, apellido1, apellido2, categoria, edad, telefono, correoElectronico, idVuelo)"
                    + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pstm.setInt(1, tri.getIdTripulacion());
            pstm.setString(2, tri.getDni());
            pstm.setString(3, tri.getNombre());
            pstm.setString(4, tri.getApellido1());
            pstm.setString(5, tri.getApellido2());
            pstm.setString(6, tri.getCategoria());
            pstm.setInt(7, tri.getEdad());
            pstm.setInt(8, tri.getTelefono());
            pstm.setString(9, tri.getCorreoElectronico());
            pstm.setString(10, numero);
            int coutn = pstm.executeUpdate();
            System.out.println(coutn);
            pstm.close();

        } catch (Exception ex) {
            System.out.println("ERROR AL INTRODUCIR DATOS TRIPULACION");
        }

    }
    //Insertar datos en la tabla tripulacion
    public void ingresaDatosPasajero(Pasajero pasajero) {
        try {
            // (`idpasajeros`, `dni`, `nombre`, `apellido1`, `apellido2`, `edad`, `telefono`, `idVuelo`, `clase`, `correoElectronico`)
            PreparedStatement pstm = conn.prepareStatement("INSERT INTO pasajeros (idpasajeros, dni, nombre, apellido1, apellido2, edad, telefono, idVuelo, clase, correoElectronico, direccion)"
                    + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pstm.setInt(1, pasajero.getIdPasajero());
            pstm.setString(2, pasajero.getDni());
            pstm.setString(3, pasajero.getNombre());
            pstm.setString(4, pasajero.getApellido1());
            pstm.setString(5, pasajero.getApellido2());
            pstm.setInt(6, pasajero.getEdad());
            pstm.setInt(7, pasajero.getTelefono());
            pstm.setString(8, String.valueOf(pasajero.getIdVuelo()));//pued fallar
            pstm.setString(9, pasajero.getClase());
            pstm.setString(10, pasajero.getCorreoElectronico());
            pstm.setString(11, pasajero.getDireccion());
            int coutn = pstm.executeUpdate();
            System.out.println(coutn);
            pstm.close();                                        
        } catch (Exception ex) {
            System.out.println("ERROR AL INTRODUCIR DATOS PASAJEROS");
        }

    }
    //CONSULTARA LOS CAMPOS NOMBRE Y CATEGORIA

    public ArrayList consultaDatosTripulacion(String dato, String dato2) {
        ArrayList lista = new ArrayList();
        try {
            PreparedStatement pstm = conn.prepareStatement("SELECT " + dato2 + " FROM tripulacion WHERE idVuelo =" + "\"" + dato + "\"");
            ResultSet res = pstm.executeQuery();
            while (res.next()) {
                lista.add(res.getString(1));
            }
            res.close();

        } catch (Exception ex) {
            System.out.println("Error en la consulta tripulacion");
        }
        return lista;
    }

    public ArrayList consultaDtosTripulacion(String dato) {
        ArrayList lista = new ArrayList();
        try {
            PreparedStatement pstm = this.conn.prepareStatement("SELECT nombre, apellido1, apellido2, dni, edad, telefono, correoElectronico FROM tripulacion WHERE dni=\"" + dato + "\"");
            ResultSet res = pstm.executeQuery();
            while (res.next()) {
                for (int i = 1; i <= 7; i++) {
                    lista.add(res.getString(i));
                    System.out.println(res.getString(i));
                }
            }
            res.close();
        } catch (Exception e) {
            System.out.println("ERROR EN LAS CONSULTA");
        }
        return lista;
    }

    public String consultaUnString(String entidad, String tabla, String dato) {
        String devolver = "";
        try {
            PreparedStatement pstm = this.conn.prepareStatement("SELECT " + entidad + " FROM " + tabla + " WHERE idtripulacion=\"" + dato + "\"");
            ResultSet res = pstm.executeQuery();
            res.next();
            devolver = res.getString(1);
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return devolver;

    }

    public Object[][] consultaTablaPasajero(String dato) {
        int registros = 0;
        String consulta1 = "SELECT idpasajeros, dni, nombre, apellido1, apellido2, edad, telefono, idVuelo FROM pasajeros WHERE idVuelo=\"" + dato + "\"";
        String consulta2 = "select count(*) as total from pasajeros WHERE idVuelo=\"" + dato + "\"";
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
        Object[][] datos = new String[registros][8];
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
                datos[i][6] = res.getString(7);
                datos[i][7] = res.getString(8);
                i++;
            }
            res.close();
        } catch (Exception e) {
            System.out.println("Error en la consulta de la tabla vuelo " + e.getMessage());
        }
        return datos;
    }
    public Object[][] consultaTablaEquipaje(){
        int registros = 0;
        String consulta1 = "SELECT idEquipaje, descripcion, peso, dimensiones FROM equipaje";
        String consulta2 = "select count(*) as total from equipaje";
        try {
            PreparedStatement pstm = conn.prepareStatement(consulta2);
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
        } catch (Exception e) {
            System.out.println("Error en contar los registros " + e.getMessage());
        }
        Object[][] datos = new String[registros][5];
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
                i++;
            }
            res.close();
        } catch (Exception e) {
            System.out.println("Error en la consulta de la tabla vuelo " + e.getMessage());
        }
        return datos;
    }
}
