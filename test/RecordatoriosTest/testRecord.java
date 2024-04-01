
package RecordatoriosTest;

import DataEstructures.pila;
import functions.RecordatoriosActivity;
import modelo.Recordatorio;


public class testRecord {

    
    public static void main(String[] args) {
        // Tiempo inicial
        

        //Create
        RecordatoriosActivity a = new RecordatoriosActivity();
        long n = 100000000;
        for(int i=0; i<n;i++){
           
        }
        long tiempoInicial = System.currentTimeMillis();
        
        //read
        pila p = a.getListado();
        Recordatorio read;
        
        
        while(!p.Empty()){
            read = (Recordatorio) p.pop();
        }
        // Tiempo final
        long tiempoFinal = System.currentTimeMillis();
        long tiempoDeEjecucion = tiempoFinal - tiempoInicial;
        System.out.println("Tiempo de ejecución: " + tiempoDeEjecucion + " milisegundos");
    }
    
}
