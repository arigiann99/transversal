
package modelo;

public class Materia {
    private int idMateria;
    private String nombreMateria;

    public Materia() {
    }

    public Materia(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public Materia(String nombreMateria, int idMateria) {
        this.idMateria = idMateria;
        this.nombreMateria = nombreMateria;
    }

    public int getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }    
}
