package functions;

import DataEstructures.LinkedList;
import java.util.ArrayList;
import modelo.Asignatura;

public class AsignaturasActivity  {
    
    private ArrayList<Asignatura> listadoAsignaturas;
    
    public AsignaturasActivity() {
        this.listadoAsignaturas = new ArrayList<>();
    }
    public ArrayList<Asignatura> getListado(){
        return listadoAsignaturas;
    }
    //Metodo para agregar una asignatura
    public void agregarAsignatura(Asignatura asignatura) {
        listadoAsignaturas.add(asignatura);
    }

    //Metodo para eliminar una asignatura por su codigo
    public void eliminarAsignatura(String codigo) {
        Asignatura asignatura = this.buscarAsignaturaPorCodigo(codigo); // Creamos una instancia de Asignatura solo con el código
        listadoAsignaturas.remove(asignatura);
    }
    
    //Metodo para buscar una asignatura por su codigo
     public Asignatura buscarAsignaturaPorCodigo(String codigo) {
       return null;
    }
}
