package app;

import functions.RecordatariosActivity;
import java.util.Date;
import modelo.Recordatorio;

public class Main {

    public static void main(String[] args){
        //Prueba de recordatorios
        RecordatariosActivity a = new RecordatariosActivity();
        
        a.addRecordatorio(new Recordatorio("Tarea","Entregar algo",new Date()));
        a.addRecordatorio(new Recordatorio("Tarea2","Entregar algo2",new Date()));

        a.printRecordatorios();
        System.out.println("");
        a.printRecordatoriosOrdenados();
    }
    
}