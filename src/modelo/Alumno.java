
package modelo;

public class Alumno {
    private int idAlumno;
    private String nombre;
    private boolean activo;
    private int legajo;

    public Alumno() {
    }

    public Alumno(String nombre, int legajo , boolean activo) {
        this.nombre = nombre;
        this.legajo = legajo;
        this.activo = activo;
    }

    public Alumno( int idAlumno, String nombre, int legajo , boolean activo) {
        this.idAlumno = idAlumno;
        this.nombre = nombre;
        this.activo = activo;
        this.legajo = legajo;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public int getLegajo() {
        return legajo;
    }

    public void setLegajo(int legajo) {
        this.legajo = legajo;
    }

    
}