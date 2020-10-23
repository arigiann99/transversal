
package entidades;


public class Cursada {
    private int idCursada;
    private Alumno alumno;
    private Materia materia;
    private double calificacion;

    public Cursada() {
    }

    public Cursada(Alumno alumno, Materia materia, double calificacion) {
        this.alumno = alumno;
        this.materia = materia;
        this.calificacion = calificacion;
    }

    public Cursada(int idCursada, Alumno alumno, Materia materia, double calificacion) {
        this.idCursada = idCursada;
        this.alumno = alumno;
        this.materia = materia;
        this.calificacion = calificacion;
    }

    public int getIdCursada() {
        return idCursada;
    }

    public void setIdCursada(int idCursada) {
        this.idCursada = idCursada;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }   
}

