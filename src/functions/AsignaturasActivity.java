package functions;

import java.util.ArrayList;
import modelo.Asignatura;
import DataEstructures.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;


public class AsignaturasActivity  {
    
    private ArrayList<Asignatura> listadoAsignaturas;
    private AVL asignaturasAVL = new AVL();
    public DisjoinSet set = new DisjoinSet(20);
    public Asignatura[] orden;
    

    public AsignaturasActivity() {
        loadAsignaturas();
        if (this.listadoAsignaturas == null) {
            this.listadoAsignaturas = new ArrayList<>();
        } else {
            for (Asignatura asignatura : this.listadoAsignaturas) {
                asignaturasAVL.insertAVL(asignatura);
                set.agregarAsignatura(asignatura);
            }
        }
    }
  
    
    
    public ArrayList<Asignatura> getListado(){
        return listadoAsignaturas;
    }
    //Metodo para agregar una asignatura
    public void agregarAsignatura(Asignatura asignatura) {
        listadoAsignaturas.add(asignatura);
        asignaturasAVL.insertAVL(asignatura);

    }

    //Metodo para eliminar una asignatura por su codigo
    public void eliminarAsignatura(String codigo) {
        Asignatura asignatura = this.buscarAsignaturaPorCodigo(codigo); // Creamos una instancia de Asignatura solo con el código
        listadoAsignaturas.remove(asignatura);

    }
    
    
    
    //Metodo para buscar una asignatura por su codigo
     public Asignatura buscarAsignaturaPorCodigo(String codigo) {
         return asignaturasAVL.findAVL(Integer.parseInt(codigo));
         }
    
    public void ordenarAsignaturaByCodigo(){
        Object[] objetos = this.listadoAsignaturas.toArray();
        Asignatura[] asignaturas = new Asignatura[objetos.length];

        for (int i = 0; i < objetos.length; i++) {
            asignaturas[i] = (Asignatura) objetos[i];
        }
        this.orden = Heap.heapSort(asignaturas, Asignatura.CompareCodigo);
    }
    
    public void ordenarAsignaturaByNombre(){
        Object[] objetos = this.listadoAsignaturas.toArray();
        Asignatura[] asignaturas = new Asignatura[objetos.length];

        for (int i = 0; i < objetos.length; i++) {
            asignaturas[i] = (Asignatura) objetos[i];
        }
        this.orden = Heap.heapSort(asignaturas, Asignatura.CompareNombre);
    }
    
    public ArrayList<Asignatura> OrdenarAsignaturasPorEdificio(String Letra){
        return set.mostrarEdificiosConAsignaturas(Letra);
    }
    public void saveAsignaturas(){
        try (FileWriter writer = new FileWriter("resources\\asignaturas.json")) {
            Gson gson = new Gson();
            gson.toJson(listadoAsignaturas, writer);
        } catch (IOException e) {
            System.out.println("Error al abrir el archivo");
        }
        
    }
    
    

    
    
    //Metodo para obtener una lista de asignaturas
    public ArrayList<Asignatura> obtenerAsignaturasPorProfesor(String nombreProfesor) {
    ArrayList<Asignatura> asignaturasPorProfesor = new ArrayList<>();
    for (Asignatura asignatura : listadoAsignaturas) {
        if (asignatura.getProfesor().equals(nombreProfesor)) {
            asignaturasPorProfesor.add(asignatura);
        }
    }
    return asignaturasPorProfesor;
    }
    
    //Metodo para obtener lista de profesores
    public ArrayList<String> obtenerProfesoresDisponibles() {
        ArrayList<String> profesores = new ArrayList<>();
        for (Asignatura asignatura : listadoAsignaturas) {
            String nombreProfesor = asignatura.getProfesor();
            if (!profesores.contains(nombreProfesor)) {
                profesores.add(nombreProfesor);  // Añadir si no está ya en la lista
            }
        }
        return profesores;
    }
    
    private void loadAsignaturas(){
        try (FileReader reader = new FileReader("resources\\asignaturas.json")) {
            Gson gson = new Gson();
            Type tipoLista = new TypeToken<ArrayList<Asignatura>>(){}.getType();
            this.listadoAsignaturas = gson.fromJson(reader, tipoLista);
        } catch (IOException e) {
            System.out.println("Error cargando Asignaturas");
        }

    }
    
    
     
}



