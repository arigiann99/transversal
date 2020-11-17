/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.*;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class CursadaData {

    private Connection connection = null;
    private Conexion conexion;

    public CursadaData(Conexion conexion) {
        this.conexion = conexion;
        connection = conexion.getConection();
    }

    public void guardarCursada(Cursada cursada) {
        PreparedStatement statement = null;
        try {

            String sql = "INSERT INTO cursada (id_alumno, id_materia, nota) VALUES ( ? , ? , ? );";

            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, cursada.getAlumno().getIdAlumno());
            statement.setInt(2, cursada.getMateria().getIdMateria());
            statement.setDouble(3, cursada.getCalificacion());

            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();

            if (rs.next()) {
                cursada.setIdCursada(rs.getInt(1));
            } else {
                System.out.println("No se pudo obtener el id luego de insertar un alumno");
            }

        } catch (SQLException ex) {
            System.out.println("Error al insertar un alumno: " + ex.getMessage());
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "La conexion no se pudo cerrar");
            }
        }
    }

    public List<Cursada> obtenerCursadas() {
        List<Cursada> cursadas = new ArrayList<>();

        try {
            String sql = "SELECT * FROM cursada;";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            Cursada cursada;
            while (resultSet.next()) {
                cursada = new Cursada();
                cursada.setIdCursada(resultSet.getInt("id_cursada"));

                Alumno a = buscarAlumno(resultSet.getInt("id_alumno"));
                cursada.setAlumno(a);

                Materia m = buscarMateria(resultSet.getInt("id_materia"));
                cursada.setMateria(m);
                cursada.setCalificacion(resultSet.getInt("nota"));

                cursadas.add(cursada);
            }
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Error al obtener los alumnos: " + ex.getMessage());
        }

        return cursadas;
    }

    public List<Cursada> obtenerCursadasXAlumno(int id) {
        List<Cursada> cursadas = new ArrayList<>();

        try {
            String sql = "SELECT * FROM cursada WHERE id_alumno = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            Cursada cursada;
            while (resultSet.next()) {
                cursada = new Cursada();
                cursada.setIdCursada(resultSet.getInt("id_cursada"));

                Alumno a = buscarAlumno(resultSet.getInt("id_alumno"));
                cursada.setAlumno(a);

                Materia m = buscarMateria(resultSet.getInt("id_materia"));
                cursada.setMateria(m);
                cursada.setCalificacion(resultSet.getInt("nota"));

                cursadas.add(cursada);
            }
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Error al obtener los alumnos: " + ex.getMessage());
        }

        return cursadas;
    }

    public Alumno buscarAlumno(int id) {

        AlumnoData ad = new AlumnoData(conexion);

        return ad.buscarAlumno(id);

    }

    public Materia buscarMateria(int id) {

        MateriaData md = new MateriaData(conexion);
        return md.buscarMateria(id);

    }

    public List<Materia> obtenerMateriasCursadas(int id) {
        List<Materia> materias = new ArrayList<>();

        try {
            String sql = "SELECT materia.id_materia, materia.nombre_materia FROM cursada, materia WHERE cursada.id_materia = materia.id_materia AND cursada.id_alumno = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            Materia materia;
            Cursada cursada;
            while (resultSet.next()) {
                materia = new Materia();
                materia.setIdMateria(resultSet.getInt("id_materia"));
                materia.setNombreMateria(resultSet.getString("nombre_materia"));
                
                materias.add(materia);
            }
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Error al obtener los alumnos: " + ex.getMessage());
        }

        return materias;

    }

    public List<Materia> obtenerMateriasNOCursadas(int id) {
        List<Materia> materias = new ArrayList<>();

        try {
            String sql = "Select * from materia where id_materia not in "
                    + "(select id_materia from cursada where id_alumno =?);";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            Materia materia;
            while (resultSet.next()) {
                materia = new Materia();
                materia.setIdMateria(resultSet.getInt("id_materia"));
                materia.setNombreMateria(resultSet.getString("nombre_materia"));
                materias.add(materia);
            }
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Error al obtener los alumnos: " + ex.getMessage());
        }

        return materias;

    }

    public void borrarCursadaDeUnaMateriaDeunAlumno(int idAlumno, int idMateria) {

        try {

            String sql = "DELETE FROM cursada WHERE id_alumno =? and id_materia =?;";

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, idAlumno);
            statement.setInt(2, idMateria);

            statement.executeUpdate();

            statement.close();

        } catch (SQLException ex) {
            System.out.println("Error al insertar un alumno: " + ex.getMessage());
        }

    }

    public void actualizarNotaCursada(int nota, int idAlumno, int idMateria) {

        try {

            String sql = "UPDATE cursada SET nota = ? WHERE id_alumno =? and id_materia =?;";

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, nota);
            statement.setInt(2, idAlumno);
            statement.setInt(3, idMateria);

            statement.executeUpdate();

            statement.close();

        } catch (SQLException ex) {
            System.out.println("Error al insertar un alumno: " + ex.getMessage());
        }

    }

}
