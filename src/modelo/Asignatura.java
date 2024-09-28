package modelo;

import functions.AsignaturasActivity;
import java.util.ArrayList;
import java.util.Comparator;

public class Asignatura {
    
    private String nombre;
    private String codigo;
    private String profesor;
    private int creditos;
    private String edificio;
    private ArrayList<Nota> notas;
    private String dia;  
    private String horaInicio;
    private String horaFin;
    
    public Asignatura(String nombre, String codigo, String profesor, int creditos, String edificio, String dia, String horaInicio, String horaFin) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.profesor = profesor;
        this.creditos = creditos;
        this.edificio = edificio;
        this.notas = new ArrayList<>();
        this.dia = dia;  
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }
    
    // Getters y Setters
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

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }
    
    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    @Override
    public String toString() {
        return "Asignatura{" +
                "nombre='" + nombre + '\'' +
                ", codigo='" + codigo + '\'' +
                ", profesor='" + profesor + '\'' +
                ", creditos=" + creditos +
                ", dia=" + dia +
                ", horaInicio=" + horaInicio +
                ", horaFin=" + horaFin +
                '}';
    }

    
   
    
    // Comparadores
    public static Comparator<Asignatura> CompareCodigo = new Comparator<Asignatura>() {
        @Override
        public int compare(Asignatura a1, Asignatura a2) {
            return a1.codigo.compareTo(a2.codigo);
        }
    };

    public static Comparator<Asignatura> CompareNombre = new Comparator<Asignatura>() {
        @Override
        public int compare(Asignatura a1, Asignatura a2) {
            char letra1 = a1.nombre.charAt(0);
            char letra2 = a2.nombre.charAt(0);
            return Character.compare(letra1, letra2);
        }
    };
}
