
package modelo;

import java.sql.Connection;
import java.sql.DriverManager; 
import java.sql.SQLException;  
import javax.swing.JOptionPane;

public class Conexion {                 
    
    private final String base = "universidadg2";                   
    private final String url = "jdbc:mysql://localhost:3306/"+base; 
    private final String user = "root";                             
    private final String pass = "";                                 
    private Connection con;                                              
    
    public Connection getConection() { 
        try{
            Class.forName("org.mariadb.jdbc.Driver");                
            con = (Connection) DriverManager.getConnection(url, user, pass);                      
        }
        catch(SQLException | ClassNotFoundException e){             
            System.err.print(e.getMessage());
            JOptionPane.showMessageDialog(null, "No se pudo conectar");
        }
        return con;                                                 
    }
    
}
