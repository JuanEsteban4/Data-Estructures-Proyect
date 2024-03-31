package app;

import components.recordatorioVista;
import java.util.Date;
import modelo.Recordatorio;
import vista.vista;

public class Main {

    public static void main(String[] args){
        //Prueba de recordatorios
        vista a = new vista();
        a.init();
        
        Recordatorio b = new Recordatorio("Prueba 1","enter \nenter",new Date());
        Recordatorio c = new Recordatorio("Prueba 2","enter2 \nenter222",new Date(1994,12,24));
       
        recordatorioVista r = new recordatorioVista(b);
        r.addRecordatorio(a);
        
        recordatorioVista x = new recordatorioVista(c);
        x.addRecordatorio(a);
        
        a.reset();
        
    }
    
}