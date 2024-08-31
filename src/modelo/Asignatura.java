package modelo;
import java.util.ArrayList;

public class Asignatura implements Comparable<Asignatura>{
    
    private String nombre;
    private String codigo;
    private String profesor;
    private int creditos;
    private String edificio;
    private ArrayList<Nota> notas;

    public Asignatura(String nombre, String codigo, String profesor, int creditos,String edificio) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.profesor = profesor;
        this.creditos = creditos;
        this.edificio = edificio;
        this.notas = new ArrayList();
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public String getEdificio() {
        return edificio;
    }

    public void setEdificio(String edificio) {
        this.edificio = edificio;
    }

    public ArrayList<Nota> getNotas() {
        return notas;
    }

    public void setNotas(ArrayList<Nota> notas) {
        this.notas = notas;
    }

    @Override
    public String toString() {
        return "Asignatura{" +
                "nombre = '" + nombre + '\'' +
                ", codigo = '" + codigo + '\'' +
                ", profesor = '" + profesor + '\'' +
                ", creditos = " + creditos +
                '}';
    }

    @Override
    public int compareTo(Asignatura o) {
        if(this.creditos > o.getCreditos()){
            return 1;
        }else if(this.creditos < o.getCreditos()){
            return -1;
        }else{
            return 0;
        }
    }
}
