package functions;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import modelo.Recordatorio;
import java.lang.reflect.Type;

public class RecordatoriosActivity{
    
    private ArrayList<Recordatorio> listado;

    public RecordatoriosActivity(){
        loadRecordatorios();
        if(listado == null){
            this.listado = new ArrayList();
        }
    }
    
    private void loadRecordatorios(){
        try (FileReader reader = new FileReader("resources\\recordatorios.json")) {
            Gson gson = new Gson();
            Type tipoLista = new TypeToken<ArrayList<Recordatorio>>(){}.getType();
            this.listado = gson.fromJson(reader, tipoLista);
        } catch (IOException e) {
            System.out.println("Error cargando records");
        }
    }
    
    public void saveRecordatorios(){
        try (FileWriter writer = new FileWriter("resources\\recordatorios.json")) {
            Gson gson = new Gson();
            gson.toJson(listado, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    public void addRecordatorio(Recordatorio record){
        listado.add(record);
    }
    
    public void deleteRecordatorio(Recordatorio record){
        listado.remove(record);
    }
    
    public void deleteRecordatorio(int index){
        listado.remove(index);
    }
    
    public void updateRecordatorio(Recordatorio record, Recordatorio nw){
        listado.set(listado.indexOf(record), nw);
    }
    
    public Recordatorio getRecordatorio(long index){
        return listado.get((int) index);
    }
    
    public void checkRecordatorio(Recordatorio record){
        record.setCompletado(true);
    }
    
    public void printRecordatorios(){
        if(listado.isEmpty()) {
            System.out.println("no print pq Esta vacio");
            return;
        }
        for(Recordatorio e: listado){
            System.out.println(e);
        }
    }
    
    public ArrayList<Recordatorio> getListado(){
        return this.listado;
    }
    
    public int listadoSize(){
        return listado.size();
    }
    
    public void ordenarPorTitulo(){
       listado.sort(Comparator.comparing(Recordatorio::getTitulo));
    }
    
    public void ordenarPorFecha(){
       listado.sort(Comparator.comparing(Recordatorio::getFechaHora));
    }
    
    public void ordenarPorFechaIngreso(){
       listado.sort(Comparator.comparing(Recordatorio::getFechaIngreso));
    }    
}
