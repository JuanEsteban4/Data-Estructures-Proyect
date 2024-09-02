package DataEstructures;

import modelo.Asignatura;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DisjoinSet {
    int[] rank, parent;
    int n;
    Map<String, ArrayList<Asignatura>> asignaturasPorEdificio; // Mapa de listas de asignaturas por edificio

    // Constructor
    public DisjoinSet(int n) {
        rank = new int[n];
        parent = new int[n];
        this.n = n;
        asignaturasPorEdificio = new HashMap<>(); // Inicializa el mapa de asignaturas
        makeSet(); // Inicializa los conjuntos
    }

    // Crea n conjuntos con un solo elemento en cada uno
    public void makeSet() {
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    // Retorna el representante del conjunto al que pertenece x
    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    // Une el conjunto que incluye a "x" con el conjunto que incluye a "y" (POR RANK)
    public void union(int x, int y) {
        int xRoot = find(x), yRoot = find(y);

        if (xRoot == yRoot) {
            return;
        }

        if (rank[xRoot] < rank[yRoot]) {
            parent[xRoot] = yRoot;
        } else if (rank[yRoot] < rank[xRoot]) {
            parent[yRoot] = xRoot;
        } else {
            parent[yRoot] = xRoot;
            rank[xRoot]++;
        }
    }

    // Convertir letra a índice
    public int letraAIndice(char letra) {
        return letra - 'A'; // Convierte 'A' a 0, 'B' a 1, etc.
    }

    // Convertir índice a letra
    public char indiceALetra(int indice) {
        return (char) (indice + 'A');
    }

    // Agregar asignatura y unir al edificio correspondiente
    public void agregarAsignatura(Asignatura asignatura) {
        String edificio = asignatura.getEdificio().toUpperCase(); // Obtener el nombre del edificio
        char primeraLetra = edificio.charAt(0); // Usar la primera letra del nombre del edificio
        int indiceEdificio = letraAIndice(primeraLetra);
        int indiceAsignatura = letraAIndice(asignatura.getNombre().toUpperCase().charAt(0)); // Usar la primera letra del nombre de la asignatura como su índice
        // Unir la asignatura al conjunto del edificio correspondiente
        union(indiceEdificio, indiceAsignatura);

        // Agregar la asignatura al conjunto de asignaturas del edificio
        asignaturasPorEdificio.putIfAbsent(edificio, new ArrayList<>()); // Crea una nueva lista para el edificio si no existe
        asignaturasPorEdificio.get(edificio).add(asignatura); // Adiciona la asignatura a la lista del edificio correspondiente
    }

    // Mostrar todos los edificios con sus asignaturas
    public void mostrarEdificiosConAsignaturas() {
        for (Map.Entry<String, ArrayList<Asignatura>> entry : asignaturasPorEdificio.entrySet()) {
            String edificio = entry.getKey();
            ArrayList<Asignatura> asignaturas = entry.getValue();
            System.out.println("Edificio: " + edificio);
            for (Asignatura asignatura : asignaturas) {
                System.out.println("  - " + asignatura);
            }
        }
    }
    
    public ArrayList<Asignatura> mostrarEdificiosConAsignaturas(String Letra) {
        return this.asignaturasPorEdificio.get(Letra);
    }

    /*public static void main(String[] args) throws IOException {
        DisjoinSet ds = new DisjoinSet(26);

        Asignatura asignatura1 = new Asignatura("Algebra", "MAT101", "Dr. Gomez", 3, "A");
        Asignatura asignatura2 = new Asignatura("Biologia", "BIO202", "Dr. Perez", 4, "B");
        Asignatura asignatura3 = new Asignatura("Analisis", "MAT201", "Dr. Lopez", 5, "A");
        Asignatura asignatura4 = new Asignatura("Quimica", "CHE101", "Dra. Martinez", 4, "Q");
        Asignatura asignatura5 = new Asignatura("Fisica", "PHY101", "Dr. Hernandez", 4, "F");
        Asignatura asignatura6 = new Asignatura("Algebra", "MAT101", "Dr. Gomez", 3, "A");
        Asignatura asignatura7 = new Asignatura("AAAAAA", "BIO202", "Dr. Perez", 4, "B");
        Asignatura asignatura8 = new Asignatura("Analisis", "MAT201", "Dr. Lopez", 5, "A");
        Asignatura asignatura9 = new Asignatura("Autoimmunidad", "CHE101", "Dra. Martinez", 4, "B");
        Asignatura asignatura10 = new Asignatura("AAAAAA", "PHY101", "Dr. Hernandez", 4, "F");

        ds.agregarAsignatura(asignatura1);
        ds.agregarAsignatura(asignatura2);
        ds.agregarAsignatura(asignatura3);
        ds.agregarAsignatura(asignatura4);
        ds.agregarAsignatura(asignatura5);
        ds.agregarAsignatura(asignatura6);
        ds.agregarAsignatura(asignatura7);
        ds.agregarAsignatura(asignatura8);
        ds.agregarAsignatura(asignatura9);
        ds.agregarAsignatura(asignatura10);

        // Mostrar los edificios con sus asignaturas
        ds.mostrarEdificiosConAsignaturas();
    }*/
}
