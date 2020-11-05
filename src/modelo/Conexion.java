
package modelo;

import java.sql.Connection;
import java.sql.DriverManager; 
import java.sql.SQLException;  
import javax.swing.JOptionPane;

public class Conexion {                 
    
    //private String base = "universidadg2";                   
    //private String url = "jdbc:mysql://localhost:3306/universidadg2"; 
    //private String user = "root";                             
    //private String pass = "";                                 
    private String url ; 
    private String user;                             
    private String pass;                                 
    private Connection con;      

    public Conexion(String url,String user,String pass) {
        this.url = url;
        this.user = user;
        this.pass = pass;
    }
    
    
    
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
