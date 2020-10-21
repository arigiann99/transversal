

package modelo;

import entidades.Materia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;



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
            String sql = " UPDATE `materia` SET nombre=? WHERE idMateria=?";
            PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, materia.getNombreMateria());
            statement.setInt(2, materia.getIdMateria());
            
            statement.executeUpdate();
         
        statement.close();
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
        } catch (SQLException e) {
            System.err.print(e.getMessage());
            JOptionPane.showMessageDialog(null, " No se pudo eliminar la materia");
        }
    }
    
    public Materia buscarMateria(int id){
        Materia materia = new Materia();
        String sql = "SELECT * FROM materia WHERE idMateria=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if(rs.equals(id)){
                materia.getNombreMateria();
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo encontrar materia");
            }
            ps.close();
            
        } catch (SQLException e) {
           System.err.print(e.getMessage());
            JOptionPane.showMessageDialog(null, " No se pudo encontrar la materia");
        }        
        return materia;       
    }
    
    public List<Materia> obtenerMaterias(){
        Materia materia = new Materia();
        List<Materia> materias = new ArrayList<>();
        String sql = "SELECT * FROM materia";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                materia.setIdMateria(rs.getInt("idMateria"));
                materia.setNombreMateria(rs.getString("nombre"));
                System.out.println(materia.getNombreMateria());
                materias.add(materia);            
            }           
        } catch (SQLException e) {
            System.err.print(e.getMessage());
            JOptionPane.showMessageDialog(null, "No se pudo listar las materias");
        }
        return materias;        
    }
    
}
