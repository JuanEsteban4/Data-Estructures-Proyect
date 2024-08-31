package modelo;

import java.util.Comparator;

public class Asignatura{
    
    private String nombre;
    private String codigo;
    private String profesor;
    private int creditos;
    private String edificio;

    public Asignatura(String nombre, String codigo, String profesor, int creditos,String edificio) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.profesor = profesor;
        this.creditos = creditos;
        this.edificio = edificio;
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
    
    @Override
    public String toString() {
        return "Asignatura{" +
                "nombre = '" + nombre + '\'' +
                ", codigo = '" + codigo + '\'' +
                ", profesor = '" + profesor + '\'' +
                ", creditos = " + creditos +
                '}';
    }

    //Compare para la comparacion por codigo
    public static Comparator<Asignatura> CompareCodigo = new Comparator<Asignatura>() {
        @Override
        public int compare(Asignatura a1, Asignatura a2) {
            return a1.codigo.compareTo(a2.codigo);
        }
    };

    //Compare para la comparacion de la primera letra del nombre de la asignatura
    public static Comparator<Asignatura> CompareNombre = new Comparator<Asignatura>() {
        @Override
        public int compare(Asignatura a1, Asignatura a2) {
        // Obtener la primera letra de cada nombre
        char letra1 = a1.nombre.charAt(0);
        char letra2 = a2.nombre.charAt(0);

        // Comparar las letras
        return Character.compare(letra1, letra2);
        }
    };
}
