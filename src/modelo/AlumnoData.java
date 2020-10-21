/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import entidades.Alumno;
import java.sql.Connection;
//import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author GIANELLI
 */
public class AlumnoData {
    private Connection con = null;

    public AlumnoData(Conexion conexion) {
        con = conexion.getConection();
    }
    
    public void guardarAlumno(Alumno alumno){
        try {
            String sql = "INSERT INTO `alumno`(`nombre`, `legajo`, `activo`) VALUE (?,?,?) ";
            PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, alumno.getNombre());
            statement.setInt(2,alumno.getLegajo());
            //statement.setDate(3, Date.valueOf(alumno.getFn_alumno()));
            statement.setBoolean(3, alumno.isActivo());
            
            statement.executeUpdate();
            
            ResultSet rs = statement.getGeneratedKeys();
            
            if(rs.next()){
                alumno.setIdAlumno(rs.getInt(1));
            } else {
                //System.out.println("No se pudo obtener el id del alumno");
                JOptionPane.showMessageDialog(null, "No se pudo obtener el id del alumno");
            }
        con.close();
            
        } catch (SQLException e) {
            System.err.print(e.getMessage());
            JOptionPane.showMessageDialog(null, "No se pudo guardar alumno");
        }
        
    }
    
    public void actualizarAlumno(Alumno alumno){
        try {
            String sql = " UPDATE `alumno` SET nombre=? , legajo=?, activO=? WHERE id=?";
            PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, alumno.getNombre());
            statement.setInt(2,alumno.getLegajo());
           //statement.setDate(3, Date.valueOf(alumno.getFn_alumno()));
            statement.setBoolean(3, alumno.isActivo());
            statement.setInt(4, alumno.getIdAlumno());
                    
            statement.executeUpdate();
            
        con.close();
            
        } catch (SQLException e) {
            System.err.print(e.getMessage());
            JOptionPane.showMessageDialog(null, " No se pudo buscar el alumno");
        }       
    }
    
    public void borrarAlumno(int id){
        String sql = "DELETE FROM alumno WHERE idAlumno=?";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Alumno buscarAlumno(int id){
        Alumno alumno = new Alumno();
        String sql = "SELECT * FROM alumno WHERE idAlumno=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
          
            ps.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoData.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return alumno;       
    }
    
    public List<Alumno> obtenerAlumnos(){
        Alumno alumno = new Alumno();
        List<Alumno> alumnos = new ArrayList<>();
        String sql = "SELECT * FROM alumno";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){             
                alumno.setIdAlumno(rs.getInt("idAlumno"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setActivo(rs.getBoolean("activo"));
                System.out.println(alumno.getNombre());
                
                alumnos.add(alumno);            
            }           
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return alumnos;        
    }
    
    
}
