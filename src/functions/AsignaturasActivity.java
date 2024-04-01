
package functions;

import DataEstructures.OrderedLinkedList;
import DataEstructures.OrderedLinkedList.Node;
import DataEstructures.pila;
import modelo.Asignatura;

public class AsignaturasActivity  {
    
    private OrderedLinkedList<Asignatura> listadoAsignaturas;
    private pila<String> codigosPila;
    
    public AsignaturasActivity() {
        this.listadoAsignaturas = new OrderedLinkedList<>();
        this.codigosPila = new pila<>();
    }

    //Metodo para agregar una asignatura
    public void agregarAsignatura(Asignatura asignatura) {
        listadoAsignaturas.insert(asignatura);
        codigosPila.push(asignatura.getCodigo());
    }

    //Metodo para eliminar una asignatura por su codigo
    public boolean eliminarAsignatura(String codigo) {
        Asignatura asignatura = this.buscarAsignaturaPorCodigo(codigo); // Creamos una instancia de Asignatura solo con el código
        codigosPila.remove(codigo);
        return listadoAsignaturas.delete(asignatura);
        
    }
    
    //Metodo para imprimir todas las asignaturas
    public void imprimirAsignaturas() {
        System.out.println(listadoAsignaturas.printRecursive());
    }

    //Metodo para saber la cantidad de asignaturas que existen
    public int cantidadAsignaturas() {
        return listadoAsignaturas.size();
    }

    //Metodo para buscar una asignatura por su codigo
     public Asignatura buscarAsignaturaPorCodigo(String codigo) {
        Node<Asignatura> current = listadoAsignaturas.getHead();
        while (current != null) {
            if (current.getData().getCodigo().equals(codigo)) {
                return current.getData();
            }
            current = current.getNext();
        }
        return null; 
    }
}
