package functions;

import DataEstructures.LinkedList;
import java.util.ArrayList;
import modelo.Asignatura;
import DataEstructures.AVL; 
public class AsignaturasActivity  {
    
    private ArrayList<Asignatura> listadoAsignaturas;
    private AVL asignaturasAVL = new AVL();
    
    
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
         for(Asignatura i: listadoAsignaturas){
             asignaturasAVL.insertAVL(i);
             
         }
         System.out.println(codigo);
         return asignaturasAVL.findAVL(Integer.parseInt(codigo));
         }
    
     
   
}
