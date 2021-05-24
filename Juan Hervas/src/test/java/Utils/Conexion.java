package Utils;

import java.sql.*;
import java.sql.DriverManager;


public class Conexion {
    public java.sql.Connection conectar () throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String ip =  ReadProperties.readFromConfig("Propiedades.properties").getProperty("ip_bdd");
        String puerto =  ReadProperties.readFromConfig("Propiedades.properties").getProperty("puerto");
        String bdd =  ReadProperties.readFromConfig("Propiedades.properties").getProperty("bdd");
        String usuario =  ReadProperties.readFromConfig("Propiedades.properties").getProperty("Usuario");
        String clave =  ReadProperties.readFromConfig("Propiedades.properties").getProperty("Clave");
        String sURL = "jdbc:mysql://"+ip+":"+puerto+"/"+bdd;
        return DriverManager.getConnection(sURL,usuario,clave);
    }
}
