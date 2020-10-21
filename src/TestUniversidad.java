/** To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import entidades.Alumno;
import entidades.Cursada;
import entidades.Materia;
import modelo.AlumnoData;
import modelo.Conexion;
import modelo.CursadaData;
import modelo.MateriaData;

/**
 *
 * @author GIANELLI
 */
public class TestUniversidad {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Conexion c = new Conexion();
        
        //Alumno
        AlumnoData a1 = new AlumnoData(c);
        AlumnoData a2 = new AlumnoData(c);
        Alumno ariel = new Alumno("Ariel Giannelli", 152797, true);
        Alumno fernando = new Alumno("Fernando Sosa", 172832, true);
        /*
        a1.guardarAlumno(ariel);
        a2.guardarAlumno(fernando);
        */
        
        // Materia
        MateriaData mt = new MateriaData(c);
        MateriaData mt2 = new MateriaData(c);
        MateriaData mt3 = new MateriaData(c);
        
        Materia matematica = new Materia("Matematica");
        Materia laboratorio = new Materia("Laboratorio");
        Materia web = new Materia("Programacion Web");
        /*
        mt.guardarMateria(matematica);
        mt2.guardarMateria(laboratorio);
        mt3.guardarMateria(web);
        mt.borrarMateria(1);
        mt2.borrarMateria(2);
        */
        
        // Cursada 
        CursadaData cd = new CursadaData(c);
        Cursada curso = new Cursada(ariel, matematica, 9.5);
        
        cd.guardarCalificacion(curso);
        
        
    }
    
}
