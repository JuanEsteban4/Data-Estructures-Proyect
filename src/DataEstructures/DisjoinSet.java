package DataEstructures;

class DisjoinSet {

    int[] rank, parent;
    int n;

    // Constructor 
    public DisjoinSet(int n) {
        rank = new int[n];
        parent = new int[n];
        this.n = n;
        makeSet();
    }

    // Crea n conjuntos con un solo elemento en cada uno 
    public void makeSet() {
        for (int i = 0; i < n; i++) {
            // Inicialmente, todos los elementos estan en su propio conjunto 
            parent[i] = i;
        }
    }

    // Retorna el representante del conjunto al que pertenece x 
    public int find(int x) {
        // Encuentra el representante del conjunto al que x pertenece 
        if (parent[x] != x) {
            // Si x no es el padre de si mismo, entonces no es el representante de su conjunto 
            parent[x] = find(parent[x]);
            // Llamada recursiva a find en su padre y mueve el nodo de x directamente bajo el representante de este conjunto 
        }
        return parent[x];
    }

    // Une el conjunto que incluye a "x" con el conjunto que incluye a "y" (POR RANK)
    public void union(int x, int y) {
        // Encuentra los representantes de los dos conjuntos 
        int xRoot = find(x), yRoot = find(y);

        // Si los elementos estan en el mismo conjunto, no es necesario unir nada 
        if (xRoot == yRoot) {
            return;
        }

        // Si el rango de x es menor que el rango de y 
        if (rank[xRoot] < rank[yRoot]) // Mueve x bajo y para que la profundidad del arbol permanezca menor 
        {
            parent[xRoot] = yRoot;
        } // Si el rango de y es menor que el rango de x 
        else if (rank[yRoot] < rank[xRoot]) // Mueve y bajo x para que la profundidad del arbol permanezca menor 
        {
            parent[yRoot] = xRoot;
        } else { // si los rangos son iguales 
            // Mueve y bajo x (no importa cual va debajo de cual) 
            parent[yRoot] = xRoot;
            // Incrementa el rango del arbol resultante en 1 
            rank[xRoot] = rank[xRoot] + 1;
        }
    }
}
