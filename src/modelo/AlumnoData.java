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
            statement.setBoolean(3, alumno.isActivo());
            
            statement.executeUpdate();
            
            ResultSet rs = statement.getGeneratedKeys();
            
            if(rs.next()){
                alumno.setIdAlumno(rs.getInt(1));
            } else {
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
            String sql = " UPDATE `alumno` SET nombre=? , legajo=?, activo=? WHERE idAlumno=?";
            PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, alumno.getNombre());
            statement.setInt(2,alumno.getLegajo());
            statement.setBoolean(3, alumno.isActivo());
            statement.setInt(4, alumno.getIdAlumno());
                    
            statement.executeUpdate();
            
        con.close();
            
        } catch (SQLException e) {
            System.err.print(e.getMessage());
            JOptionPane.showMessageDialog(null, " No se pudo actualizar el alumno");
        }       
    }
    
    public void borrarAlumno(int id){
        String sql = "DELETE FROM alumno WHERE idAlumno=?";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
             System.err.print(e.getMessage());
            JOptionPane.showMessageDialog(null, " No se pudo eliminar el alumno");
        }
    }
    
    public Alumno buscarAlumno(int id){
        Alumno alumno = new Alumno();
        String sql = "SELECT * FROM alumno WHERE idAlumno=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                  alumno.setIdAlumno(rs.getInt(1));
                  alumno.setNombre(rs.getString(2));
                  alumno.setLegajo(rs.getInt(3));
                  alumno.setActivo(rs.getBoolean(4));
                  System.out.println(alumno.getNombre());    
              }  
            
            ps.close();
            
        } catch (SQLException e) {
             System.err.print(e.getMessage());
            JOptionPane.showMessageDialog(null, " No se pudo encontrar el alumno");
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
                alumno.setLegajo(rs.getInt("legajo"));
                System.out.println(alumno.getNombre()+ " " + alumno.getLegajo());
                
                alumnos.add(alumno);            
            }    
            ps.close();
        } catch (SQLException e) {
            System.err.print(e.getMessage());
            JOptionPane.showMessageDialog(null, " No se pudo listar los alumnos");
        }
        return alumnos;        
    }
    
    
}
