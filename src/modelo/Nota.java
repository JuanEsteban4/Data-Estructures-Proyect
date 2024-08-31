package modelo;

public class Nota {
    private float porcentaje;
    private float nota;
    private String info;
    
    public Nota(float score, float por, String info){
        this.porcentaje = por;
        this.nota = score;
        this.info = info;
    }

    public float getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(int porcentaje) {
        this.porcentaje = porcentaje;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
    
}
