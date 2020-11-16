

import modelo.Alumno;
import modelo.Cursada;
import modelo.Materia;
import modelo.AlumnoData;
import modelo.Conexion;
import modelo.CursadaData;
import modelo.MateriaData;


public class TestUniversidad {

    
    public static void main(String[] args) {
       
        Conexion c = new Conexion("jdbc:mysql://localhost:3306/universidadg2", "root", "");
        /*
        
        //ALUMNO DATA
        AlumnoData ad = new AlumnoData(c);
        
        //ALUMNO
        Alumno ariel = new Alumno("Ariel Giannelli", 152800, false);
        Alumno malco = new Alumno("Malco Grosso", 152801, true);
        Alumno nela = new Alumno("Marianela Garacciolo", 152799, true);
        
        //ad.guardarAlumno(ariel); 
        //ad.guardarAlumno(malco); // funciona ok
        //ad.guardarAlumno(nela);
        
        //malco.setIdAlumno(2);
        //ariel.setIdAlumno(1);
        //ad.actualizarAlumno(ariel); // funciona ok
        
        //ad.borrarAlumno(1); // funciona ok
        
        //ad.buscarAlumno(2); // funciona ok
        
        //ad.obtenerAlumnos(); // funciona ok
        
        
        */
         //MATERIA DATA
        MateriaData md = new MateriaData(c);
        
        // MATERIA
        //Materia mat = new Materia("Matematica");
        Materia mat = new Materia("Matematica 3.0"); // Se cambia a 3.0 para ver actualizacion.
        Materia lab = new Materia("Laboratorio 1");
        Materia web = new Materia("Programacion Web");
        
        //md.guardarMateria(mat);
        //md.guardarMateria(lab); // funciona ok
        //md.guardarMateria(web);
        
        //lab.setIdMateria(2);
        web.setIdMateria(3);
        //md.actualizarMateria(lab); //funciona ok
        
        //md.borrarMateria(1); // funciona ok
        
        //md.buscarMateria(3); // funciona ok
        
        System.out.println(md.obtenerMaterias());
        md.obtenerMaterias(); // funciona ok
        
       /*
        // CURSADA DATA
        CursadaData cd = new CursadaData(c);
        
        // CURSADA
        Cursada curso = new Cursada(ariel, mat, 9);
        Cursada curso2 = new Cursada(malco, web, 2);
      
      //  cd.guardarCalificacion(curso2); // funciona ok 
        //cd.guardarCursada(curso2);
        
        //cd.actualizarCalificacion(curso2); // funciona ok
        
        //cd.borrarCalificacion(curso2); //funciona ok
       // cd.buscarCalificacionxMateria(2);
        
        //cd.buscarCalificacionAlumno(2); //funciona ok // buscar cant de materia x alumno
        //cd.buscarCalificacionMateria(15); // funciona ok
        
       // cd.bucarCursadaXAlumno(2); // ingreso id de alumno y me lista las materias // funciona ok
        
        //cd.bucarAlumnoXCursada(2); // ingreso id de la materia de la cual quiero listar los alumnos // funciona ok
        cd.obtenerCursadas();
     */   
    }
    
}
