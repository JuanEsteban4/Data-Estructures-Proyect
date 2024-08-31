package DataEstructures;



import java.io.IOException;
import java.util.Date;
import modelo.Recordatorio;

public class Heap {

    // Clase MinHeap: Implementación de un heap mínimo k-ario
    public static class MinHeap {
        private Recordatorio[] heap; // Array para almacenar los elementos del heap
        private int size;    // Tamaño actual del heap
        private int k;       // Número de hijos por nodo (k-ario)

        // Constructor que inicializa el heap con una capacidad y un valor de k dados
        public MinHeap(int capacity, int k) {
            heap = new Recordatorio[capacity]; // Crear un array de Recordatorio con la capacidad especificada
            size = 0;                 // Inicializar el tamaño del heap en 0
            this.k = k;               // Inicializar k con el valor especificado
        }

        // Método que devuelve el elemento mínimo (la raíz del heap)
        public Recordatorio peek() {
            if (size == 0) throw new IllegalStateException(); // Lanzar excepción si el heap está vacío
            return heap[0]; // El elemento mínimo está en la raíz
        }

        // Método que extrae y elimina el elemento mínimo del heap
        public Recordatorio minPop() {
            if (size == 0) throw new IllegalStateException(); // Lanzar excepción si el heap está vacío
            Recordatorio minItem = heap[0]; // Guardar el elemento mínimo
            heap[0] = heap[size - 1]; // Reemplazar la raíz con el último elemento
            size--; // Reducir el tamaño del heap
            minHeap(0); // Restaurar la propiedad del min-heap
            return minItem; // Devolver el elemento mínimo
        }

        // Método que añade un nuevo elemento al heap
        public void add(Recordatorio item) {
            if (size == heap.length) throw new IllegalStateException("El heap está lleno"); // Lanzar excepción si el heap está lleno
            heap[size] = item; // Añadir el nuevo elemento al final del heap
            size++; // Incrementar el tamaño del heap
            int currentIndex = size - 1; // Iniciar en el índice del nuevo elemento
            // Subir el elemento por el heap hasta que se restaure la propiedad del min-heap
            while (currentIndex > 0 && heap[currentIndex].compareTo(heap[parentIndex(currentIndex)]) < 0) {
                swap(currentIndex, parentIndex(currentIndex)); // Intercambiar con el padre si es menor
                currentIndex = parentIndex(currentIndex); // Moverse al índice del padre
            }
        }

        // Método que ajusta el heap para restaurar la propiedad del min-heap
        private void minHeap(int i) {
            int smallest = i; // Inicializar el índice del más pequeño como el nodo actual

            // Comparar con cada uno de los k hijos
            for (int j = 1; j <= k; j++) {
                int childIndex = childIndex(i, j); // Obtener el índice del j-ésimo hijo
                if (childIndex < size && heap[childIndex].compareTo(heap[smallest]) < 0) {
                    smallest = childIndex; // Actualizar el índice si el hijo es menor
                }
            }

            // Si el nodo actual no es el más pequeño, intercambiar y continuar
            if (smallest != i) {
                swap(i, smallest); // Intercambiar el nodo actual con el más pequeño
                minHeap(smallest); // Recursivamente ajustar el subárbol afectado
            }
        }

        // Método para obtener el índice del j-ésimo hijo
        private int childIndex(int i, int j) {
            return k * i + j; // Cálculo basado en la estructura de un heap k-ario
        }

        // Método para obtener el índice del padre
        private int parentIndex(int i) {
            return (i - 1) / k; // Cálculo basado en la estructura de un heap k-ario
        }

        // Método para intercambiar dos elementos en el heap
        private void swap(int i, int j) {
            Recordatorio temp = heap[i]; // Guardar el valor en la posición i
            heap[i] = heap[j]; // Mover el valor de j a la posición i
            heap[j] = temp; // Mover el valor guardado a la posición j
        }

        // Método de "vista previa" para obtener los primeros n elementos sin eliminarlos
        public Recordatorio[] peekMultiple(int n) {
            // Si n es mayor que el tamaño del heap, ajustar n al tamaño del heap
            n = Math.min(n, size);

            // Crear una copia del heap actual para manipular sin afectar el original
            MinHeap tempHeap = this.copy();

            // Array para almacenar los primeros n elementos
            Recordatorio[] topItems = new Recordatorio[n];

            for (int i = 0; i < n; i++) {
                topItems[i] = tempHeap.minPop(); // Extraer el mínimo de la copia del heap
            }

            return topItems; // Devolver los primeros n elementos
        }

        // Método para crear una copia del heap actual 
        public MinHeap copy() {
            MinHeap copyHeap = new MinHeap(heap.length, k);
            copyHeap.size = this.size;
            System.arraycopy(this.heap, 0, copyHeap.heap, 0, this.size);
            return copyHeap;
        }
    }

    public static void main(String[] args) throws IOException {
        // Crear un MinHeap con capacidad para 10 recordatorios y con 3 hijos por nodo.
        MinHeap minHeap = new MinHeap(10, 3);

        // Crear algunos recordatorios con diferentes fechas para agregar al heap
        Recordatorio r1 = new Recordatorio("Tarea 1", "Descripcion 1", new Date(2024, 8, 1));
        Recordatorio r2 = new Recordatorio("Tarea 2", "Descripcion 2", new Date(2024, 7, 1));
        Recordatorio r3 = new Recordatorio("Tarea 3", "Descripcion 3", new Date(2024, 9, 1));
        Recordatorio r4 = new Recordatorio("Tarea 4", "Descripcion 4", new Date(2024, 6, 1));
        Recordatorio r5 = new Recordatorio("Tarea 5", "Descripcion 5", new Date(2024, 10, 1));

        // Agregar los recordatorios al heap
        minHeap.add(r1);
        minHeap.add(r2);
        minHeap.add(r3);
        minHeap.add(r4);
        minHeap.add(r5);

        // Obtener los primeros 3 elementos en orden de prioridad sin eliminarlos
        Recordatorio[] topRecordatorios = minHeap.peekMultiple(5);

        // Mostrar los elementos obtenidos
        System.out.println("Los recordatorios con mayor prioridad son:");
        for (Recordatorio recordatorio : topRecordatorios) {
            System.out.println(recordatorio);
        }
        
    }
}
