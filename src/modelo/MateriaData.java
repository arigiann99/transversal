

package modelo;

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
            String sql = "INSERT INTO `materia`(`nombre_materia`) VALUES (?)";
            PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, materia.getNombreMateria());
            
            statement.executeUpdate();
            
            ResultSet rs = statement.getGeneratedKeys();
            
            if(rs.next()){
                materia.getIdMateria();
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo obtener el id del alumno");
            }
        statement.close();
            
        } catch (SQLException e) {
            System.err.print(e.getMessage());
            JOptionPane.showMessageDialog(null, "No se pudo guardar la materia");
        }   
    }
    
    public void actualizarMateria(Materia materia){
        try {
            String sql = " UPDATE `materia` SET nombre_materia=? WHERE id_materia=?";
            PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, materia.getNombreMateria());
            statement.setInt(2, materia.getIdMateria());

            statement.executeUpdate();

            statement.close();
            
            
            } catch (SQLException e) {
                System.err.print(e.getMessage());
                JOptionPane.showMessageDialog(null, " No se pudo buscar la materia");
        }       
    }
    
    public void borrarMateria(int id){
        String sql = "DELETE FROM materia WHERE id_materia=?";
        
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
        String sql = "SELECT * FROM materia WHERE id_materia=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
           if(rs.next()){
                  materia.setIdMateria(rs.getInt(1));
                  materia.setNombreMateria(rs.getString(2));
                 
                  System.out.println(materia.getNombreMateria());    
              }  
            
            ps.close();
            
        } catch (SQLException e) {
            System.err.print(e.getMessage());
            JOptionPane.showMessageDialog(null, " No se pudo encontrar la materia");
        }        
        return materia;       
    }
    
    public List<Materia> obtenerMaterias(){
        List<Materia> materias = new ArrayList<Materia>(); //para el listado
        try {
            String sql = "Select * from materia";   
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();  //conjunto registros
            Materia materia;
            while(rs.next()){
               materia = new Materia();
               materia.setIdMateria(rs.getInt("id_materia"));
               materia.setNombreMateria(rs.getString("nombre_materia"));
               materias.add(materia);
            }
            ps.close();
        } catch (SQLException ex) {
            System.err.print(ex.getMessage());
            JOptionPane.showMessageDialog(null, " No se pudo encontrar la materia");
        }
        return materias;
    }
}
