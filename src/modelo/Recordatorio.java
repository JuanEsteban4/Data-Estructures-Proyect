package modelo;


public class Recordatorio implements Comparable<Recordatorio>{
  private String titulo;
    private String descripcion;
    private String fechaHora;
    private boolean Completado;

    // Constructor
    public Recordatorio(String titulo, String descripcion, String fechaHora) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaHora = fechaHora;
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

    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    public boolean isCompletado() {
        return Completado;
    }

    public void setCompletado(boolean Compleatado) {
        this.Completado = Compleatado;
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
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
