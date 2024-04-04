package modelo;

import java.io.Serializable;
import java.util.Date;


public class Recordatorio implements Comparable<Recordatorio>,Serializable{
    private String titulo;
    private String descripcion;
    private Date fechaHora;
    private Date fechaIngreso;
    private boolean Completado;

    // Constructor
    public Recordatorio(String titulo, String descripcion, Date fechaHora) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaHora = fechaHora;
        this.fechaIngreso = new Date();
        this.Completado = false;
    }
    
    //Constructor para test
    public Recordatorio(int i){
        this.titulo = "Titulo " + i;
        this.descripcion = "Descriocion " + i;
        this.fechaHora = new Date();
        this.fechaIngreso = new Date();
        this.Completado = false;
    }

    // Métodos getter y setter
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public boolean isCompletado() {
        return Completado;
    }

    public void setCompletado(boolean Compleatado) {
        this.Completado = Compleatado;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    // Método para imprimir información del recordatorio
    @Override
    public String toString() {
        return "Recordatorio{" +
                "titulo = '" + titulo + '\'' +
                ", descripcion = '" + descripcion + '\'' +
                ", fechaHora = " + fechaHora +
                ", ischecked =" + Completado + '}';
    }

    //Comparar por fecha
    @Override
    public int compareTo(Recordatorio o) {
        return(this.fechaHora.compareTo(o.getFechaHora()));
    }
}
