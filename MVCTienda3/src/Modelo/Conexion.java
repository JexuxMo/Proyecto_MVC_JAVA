
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Conexion {
    Connection conectar = null;
    Statement sentencia;
    ResultSet resultado;
    
    String Driver = "com.mysql.cj.jdbc.Driver";
    String bd = "tiendajpa";
    String usuario = "root";
    String contrasena = "";
    String url = "jdbc:mysql://localhost:3306/";
    
    public Connection conexion()//Esta es la getConexion del profe
    {
        try
        {
            // Nombre del dirver que utilizara para conectarse "forName"
            Class.forName(Driver);
            conectar = DriverManager.getConnection(url+bd,usuario,contrasena);
            sentencia = conectar.createStatement();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,"ERROR EN LA CONEXION: "+e);    
        }
        // Conexion entre netbeans y mysql
        return conectar;
    }
}
