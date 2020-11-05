/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

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

    public CursadaData(Conexion conexion) {
        con = conexion.getConection();
    }

    public void guardarCursada(Cursada c) {
        String sql = "INSERT INTO `cursada`(`id_alumno`, `id_materia`, `nota`) VALUES (?,?,?)";

        try {
            PreparedStatement st = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            st.setInt(1, c.getAlumno().getIdAlumno());
            st.setInt(2, c.getMateria().getIdMateria());
            st.setDouble(3, c.getCalificacion());

            st.executeUpdate();

            ResultSet rs = st.getGeneratedKeys();

            if (rs.next()) {
                c.setIdCursada(rs.getInt(1));
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo obtener el id de la cursada");
            }
            st.close();

        } catch (SQLException e) {
            System.err.print(e.getMessage());
            JOptionPane.showMessageDialog(null, "No se pudo guardar Nota");
        }
    }

    public void actualizarCalificacion(Cursada c) {

        String sql = "UPDATE `cursada` SET `nota`=? WHERE `id_alumno`=? AND `id_materia`=?";

        try {
            PreparedStatement st = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            st.setDouble(1, c.getCalificacion());
            st.setInt(2, c.getAlumno().getIdAlumno());
            st.setInt(3, c.getMateria().getIdMateria());

            st.executeUpdate();

            st.close();

        } catch (SQLException e) {
            System.err.print(e.getMessage());
            JOptionPane.showMessageDialog(null, "No se pudo Actualizar Nota");
        }

    }

    public void borrarCursada(Cursada c) {
        String sql = "DELETE FROM `cursada` WHERE `id_alumno`=? AND `id_materia`=?";

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

    public void buscarCalificacionxMateria(int id) {
        Cursada c = new Cursada();
        List<Cursada> cursada = new ArrayList<>();
        String sql = "SELECT `nota` FROM `cursada` WHERE `id_materia`=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                c.setCalificacion(rs.getInt("nota"));

                System.out.println(c.getCalificacion());

                cursada.add(c);
            }
            ps.close();

        } catch (SQLException e) {
            System.err.print(e.getMessage());
            JOptionPane.showMessageDialog(null, "No se pudo obtener Calificaciones de la materia");
        }
    }

    public List<Cursada> bucarCursadaXAlumno(int id) {
        Cursada c = new Cursada();
        Materia m = new Materia();
        List<Cursada> lista = new ArrayList<>();
        String sql = "SELECT DISTINCT materia.nombre_materia FROM `cursada`, `materia`, `alumno` WHERE cursada.id_materia = materia.id_materia AND cursada.id_alumno = alumno.id_alumno AND alumno.id_alumno=?";
        // String sql="SELECT DISTINCT materia.nombre_materia FROM `cursada`, `materia` WHERE cursada.id_alumno=?";

        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            //  while(lista.<0){
            while (rs.next()) {

                m.setNombreMateria(rs.getString("nombre_materia"));
                System.out.println(m.getNombreMateria());

                lista.add(c);
            }
            ps.close();
            //}
        } catch (SQLException e) {
            System.err.print(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al buscar alumnos");
        }
        return lista;
    }

    public List<Cursada> bucarAlumnoXCursada(int id) {
        Cursada c = new Cursada();
        Alumno a = new Alumno();
        List<Cursada> lista = new ArrayList<>();
        String sql = "SELECT DISTINCT alumno.nombre_alumno FROM `cursada`, `alumno` ,`materia`  WHERE cursada.id_materia = materia.id_materia AND cursada.id_alumno = alumno.id_alumno AND cursada.id_materia=?";
        //     String sql="SELECT DISTINCT alumno.nombre_alumno FROM `cursada`, `alumno` WHERE cursada.id_materia=?";

        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                a.setNombre(rs.getString("nombre_alumno"));
                System.out.println(a.getNombre());

                lista.add(c);
            }
            ps.close();
        } catch (SQLException e) {
            //System.err.print(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al buscar alumnos" + e.getMessage());
        }
        return lista;
    }
}
