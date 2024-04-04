package RecordatoriosTest;

import functions.RecordatoriosActivity;
import java.util.Scanner;
import modelo.Recordatorio;
import java.util.Random;

public class testRecord {
    
    int n;
    
    RecordatoriosActivity a = new RecordatoriosActivity();
    
    Random azar = new Random();
    
    public static void main(String[] args) {
        new testRecord().test();
    }
    
    public void test(){
        Scanner scan = new Scanner(System.in);
        System.out.print("numero de datos: ");
        n = scan.nextInt();
        System.out.println("""
                           (1) Create
                           (2) Read
                           (3) Update
                           (4) Delete
                           """);
        
        int o = (scan.nextInt());
        
        
        // Tiempo inicial
        long tiempoInicial = System.currentTimeMillis();
        
        if(o == 1) create();
        if(o == 2) read();
        if(o == 3) {
            read();
            tiempoInicial = System.currentTimeMillis();
            update();
        }
        if(o == 4) {
            read();
            tiempoInicial = System.currentTimeMillis();
            delete();
        }
        
        
        // Tiempo final
        long tiempoFinal = System.currentTimeMillis();
        long tiempoDeEjecucion = tiempoFinal - tiempoInicial;
        System.out.println("Tiempo de ejecucion: " + tiempoDeEjecucion + " milisegundos");
    }
    
    public void create(){
        //Create
        for(int i=0; i<n;i++){
           new Recordatorio(i);
        }
    }
    
    public void read(){
        //Read
        for(int i=0; i<n;i++){
            a.addRecordatorio(new Recordatorio(i));
        }
    }
    
    public void update(){
        //update
        int indice = azar.nextInt(0, (int) (n-1));
        System.out.println("Recordatorio #" + indice + " actualizado");
        
        a.updateRecordatorio(a.getRecordatorio(indice), new Recordatorio(-1));
    }
    
    public void delete(){
        //update
        int indice = azar.nextInt(0,(n-1));
        System.out.println("Recordatorio #" + indice + " eliminado");
        a.deleteRecordatorio((int) indice);
    }
    
}
