package com.emergentes.utiles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexionDB {
    static String driver = "com.mysql.jdbc.Driver";
    static String url = "jdbc:mysql://localhost:3306/ingresos_gastos_db";
    static String usuario="root";
    static String password ="";
    
    protected Connection conn = null;
    
    public conexionDB(){
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, usuario, password);
            if (conn != null) {
                System.out.println("Conexion Exitosa");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Error en el Driver"+ e.getMessage());
        } catch (SQLException e){
            System.out.println("Error en la conexion"+ e.getMessage());
        }
    }
    public Connection conectar(){
        return conn;
    }
    public void desconectar(){
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error al cerrar la Base de Datos "+ e.getMessage());
        }
    }          
}
