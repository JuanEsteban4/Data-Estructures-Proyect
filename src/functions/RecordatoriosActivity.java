package functions;


import DataEstructures.OrderedLinkedList;
import DataEstructures.OrderedLinkedList.Node;
import DataEstructures.pila;
import modelo.Recordatorio;

public class RecordatoriosActivity{
    
    private pila<Recordatorio> listado;
    
    
    public RecordatoriosActivity(){
        //cambiar a getRecordatirios para persistencia
        this.listado = new pila<>();
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
    }
    
    public void checkRecordatorio(Recordatorio record){
        record.setCompletado(true);
    }
    
    public void printRecordatorios(){
        listado.printList();
    }
    
    public pila<Recordatorio> getListado(){
        return this.listado;
    }
    
    public void setListadoOrdenado(){
       
    }
    
    
    
    
}
