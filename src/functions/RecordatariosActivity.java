package functions;

import DataEstructures.OrderedLinkedList;
import DataEstructures.pila;
import modelo.Recordatorio;

public class RecordatariosActivity{
    
    private pila<Recordatorio> listado;
    private OrderedLinkedList<Recordatorio> listadoOrdenado;
    
    public RecordatariosActivity(){
        //cambiar a getRecordatirios para persistencia
        this.listado = new pila<>();
        this.listadoOrdenado = new OrderedLinkedList();
    }
    
    private pila<Recordatorio> getRecordatorios(){
        //Implementacion con base de datos
        //Implementar en constructor
        return null;
    }
    
    private OrderedLinkedList<Recordatorio> getRecordatoriosOrdenados(){
        //Implementacion con base de datos
        //Implementar en constructor
        return null;
    }
    
    public void addRecordatorio(Recordatorio record){
        listado.push(record);
        listadoOrdenado.insert(record);
    }
    
    public void checkRecordatorio(Recordatorio record){
        record.setCompletado(true);
    }
    
    public void printRecordatorios(){
        if(listado.Empty()){
            System.out.println("No hay registros");
        }
        while(!listado.Empty()){
            System.out.println(listado.pop());
        }
    }
    
    public void printRecordatoriosOrdenados(){
        System.out.println(listadoOrdenado.printRecursive());
    }
    
    
    public pila<Recordatorio> getListado(){
        return this.listado;
    }
    
    public void setListadoOrdenado(){
       
    }
    
    
    
    
}
