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
import java.util.logging.Level;
import java.util.logging.Logger;
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
      
        String sql = "\"UPDATE `cursada` SET `idAlumno`=?, `idMateria`=?, `calificacion`=? WHERE 1";
   
        try {
            PreparedStatement st = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            st.setInt(1, c.getAlumno().getIdAlumno());
            st.setInt(2, c.getMateria().getIdMateria());
            st.setDouble(3, c.getCalificacion());
            
            st.executeUpdate();
            
        con.close();
            
        } catch (SQLException e) {
            System.err.print(e.getMessage());
            JOptionPane.showMessageDialog(null, "No se pudo Actualizar Nota");
        }        
    
    }
    
    public void borrarCalificacion(int idAlumno, int idMateria){
        Cursada c = new Cursada();
        String sql = "DELETE FROM `cursada` WHERE idAlumno=?, idMateria=?";
        
        try {
             
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, c.getAlumno().getIdAlumno());
            ps.setInt(2, c.getMateria().getIdMateria());
            ps.executeUpdate();
            ps.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoData.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
         
    public void buscarCalificacionAlumno(int id){
        Cursada c = new Cursada();
        String sql = "SELECT * FROM cursada WHERE idAlumno=?"; 
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            ps.close();
            
        } catch (SQLException e) {
            System.err.print(e.getMessage());
            JOptionPane.showMessageDialog(null, "No se pudo obtener Calificacion");
        }
    }
    
    public void buscarCalificacionMateria(int id){
        Cursada c = new Cursada();
        String sql = "SELECT * FROM cursada WHERE idMateria=?"; 
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            ps.close();
            
        } catch (SQLException e) {
            System.err.print(e.getMessage());
            JOptionPane.showMessageDialog(null, "No se pudo obtener Calificacion");
        }
    }
    
    
}
    
   
