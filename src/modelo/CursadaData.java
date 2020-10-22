/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;



import entidades.Cursada;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


public class CursadaData {
    private Connection con = null;
    
    public CursadaData(Conexion conexion){
        con = conexion.getConection();
    }
    
    public void guardarCalificacion(Cursada c){
        String sql = "INSERT INTO `cursada`(`idAlumno`, `idMateria`, `calificacion`) VALUES (?,?,?)";
   
        try {
            PreparedStatement st = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            st.setInt(1, c.getAlumno().getIdAlumno());
            st.setInt(2, c.getMateria().getIdMateria());
            st.setDouble(3, c.getCalificacion());
            
            st.executeUpdate();
            
            ResultSet rs = st.getGeneratedKeys();
            
            if(rs.next()){
                c.setIdCursada(rs.getInt(1));
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo obtener el id de la cursada");
            }
        con.close();
            
        } catch (SQLException e) {
            System.err.print(e.getMessage());
            JOptionPane.showMessageDialog(null, "No se pudo guardar Nota");
        }        
    }
    
    public void actualizarCalificacion(Cursada c){
      
        String sql = "UPDATE `cursada` SET `calificacion`=? WHERE `idAlumno`=? AND `idMateria`=?";
   
        try {
            PreparedStatement st = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            st.setDouble(1, c.getCalificacion());
            st.setInt(2, c.getAlumno().getIdAlumno());
            st.setInt(3, c.getMateria().getIdMateria());
            
            st.executeUpdate();
            
        con.close();
            
        } catch (SQLException e) {
            System.err.print(e.getMessage());
            JOptionPane.showMessageDialog(null, "No se pudo Actualizar Nota");
        }        
    
    }
    
    public void borrarCalificacion(Cursada c){
        String sql = "DELETE FROM `cursada` WHERE `idAlumno`=? AND `idMateria`=?";
        
        try {
             
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, c.getAlumno().getIdAlumno());
            ps.setInt(2, c.getMateria().getIdMateria());
            ps.executeUpdate();
            ps.close();
            
        } catch (SQLException e) {
            System.err.print(e.getMessage());
            JOptionPane.showMessageDialog(null, "No se pudo eliminar calificacion");
        }
    } 
         
    public void buscarCalificacionAlumno(int id){
        
        Cursada c = new Cursada();
        List<Cursada> cursada = new ArrayList<>();
        String sql = "SELECT `calificacion` FROM `cursada` WHERE `idAlumno`=?"; 
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                
                    c.setCalificacion(rs.getInt("calificacion"));
                  
                    System.out.println(c.getCalificacion());
                    
                    cursada.add(c);
              }  
            ps.close();
            
        } catch (SQLException e) {
            System.err.print(e.getMessage());
            JOptionPane.showMessageDialog(null, "No se pudo obtener Calificaciones del alumno solicitado");
        }
    }
    
    public void buscarCalificacionMateria(int id){
       Cursada c = new Cursada();
        List<Cursada> cursada = new ArrayList<>();
        String sql = "SELECT `calificacion` FROM `cursada` WHERE `idMateria`=?"; 
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                
                    c.setCalificacion(rs.getInt("calificacion"));
                  
                    System.out.println(c.getCalificacion());
                    
                    cursada.add(c);
              }  
            ps.close();
            
        } catch (SQLException e) {
            System.err.print(e.getMessage());
            JOptionPane.showMessageDialog(null, "No se pudo obtener Calificaciones de la materia");
        }
    }
    
    
}
    
   
