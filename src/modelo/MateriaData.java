

package modelo;

import entidades.Materia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.mariadb.jdbc.internal.util.dao.PrepareResult;


public class MateriaData {
    private Connection con = null;

    public MateriaData(Conexion conexion) {
        con = conexion.getConection();
    }
    
    public void guardarMateria(Materia materia){
        try {
            String sql = "INSERT INTO `materia`(`nombre`) VALUES (?)";
            PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, materia.getNombreMateria());
            
            statement.executeUpdate();
            
            ResultSet rs = statement.getGeneratedKeys();
            
            if(rs.next()){
                materia.getIdMateria();
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo obtener el id del alumno");
            }
        con.close();
            
        } catch (SQLException e) {
            System.err.print(e.getMessage());
            JOptionPane.showMessageDialog(null, "No se pudo guardar la materia");
        }   
    }
    
    public void actualizarMateria(Materia materia){
        try {
            String sql = " UPDATE `materia` SET nombre=?, WHERE id=?";
            PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, materia.getNombreMateria());
            statement.setInt(2, materia.getIdMateria());
            
            statement.executeUpdate();
            
        con.close();
            
        } catch (SQLException e) {
            System.err.print(e.getMessage());
            JOptionPane.showMessageDialog(null, " No se pudo buscar la materia");
        }       
    }
    
    public void borrarMateria(int id){
        String sql = "DELETE FROM materia WHERE idMateria=?";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(MateriaData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Materia buscarMateria(int id){
        Materia materia = new Materia();
        String sql = "SELECT * FROM materia WHERE idMateria=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
          
            ps.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoData.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return materia;       
    }
    
    public List<Materia> obtenerMaterias(){
        Materia materia = null;
        List<Materia> materias = new ArrayList<>();
        String sql = "SELECT * FROM materia";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                materia = new Materia();
                materia.setIdMateria(rs.getInt("idMateria"));
                materia.setNombreMateria(rs.getString("nombre"));
                
                
                materias.add(materia);            
            }           
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return materias;        
    }
    
}
