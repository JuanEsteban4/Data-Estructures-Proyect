package DataEstructures;

import java.io.IOException;

public class Heap {

    // Clase MinHeap: Implementacion de un heap minimo k-ario
    public static class MinHeap {
        private int[] heap; // Array para almacenar los elementos del heap
        private int size;    // Tamano actual del heap
        private int k;       // Numero de hijos por nodo (k-ario)

        // Constructor que inicializa el heap con una capacidad y un valor de k dados
        public MinHeap(int capacity, int k) {
            heap = new int[capacity]; // Crear un array con la capacidad especificada
            size = 0;                 // Inicializar el tamano del heap en 0
            this.k = k;               // Inicializar k con el valor especificado
        }

        // Metodo que devuelve el elemento minimo (la raiz del heap)
        public int peek() {
            if (size == 0) throw new IllegalStateException(); // Lanzar excepcion si el heap esta vacio
            return heap[0]; // El elemento minimo esta en la raiz
        }

        // Metodo que extrae y elimina el elemento minimo del heap
        public int minPop() {
            if (size == 0) throw new IllegalStateException(); // Lanzar excepcion si el heap esta vacio
            int minItem = heap[0]; // Guardar el elemento minimo
            heap[0] = heap[size - 1]; // Reemplazar la raiz con el ultimo elemento
            size--; // Reducir el tamano del heap
            minHeapify(0); // Restaurar la propiedad del min-heap
            return minItem; // Devolver el elemento minimo
        }

        // Metodo que anade un nuevo elemento al heap
        public void add(int item) {
            if (size == heap.length) throw new IllegalStateException("El heap esta lleno"); // Lanzar excepcion si el heap esta lleno
            heap[size] = item; // Anadir el nuevo elemento al final del heap
            size++; // Incrementar el tamano del heap
            int currentIndex = size - 1; // Iniciar en el indice del nuevo elemento
            // Subir el elemento por el heap hasta que se restaure la propiedad del min-heap
            while (currentIndex > 0 && heap[currentIndex] < heap[parentIndex(currentIndex)]) {
                swap(currentIndex, parentIndex(currentIndex)); // Intercambiar con el padre si es menor
                currentIndex = parentIndex(currentIndex); // Moverse al indice del padre
            }
        }

        // Metodo que ajusta el heap para restaurar la propiedad del min-heap
        private void minHeapify(int i) {
            int smallest = i; // Inicializar el indice del mas pequeno como el nodo actual

            // Comparar con cada uno de los k hijos
            for (int j = 1; j <= k; j++) {
                int childIndex = childIndex(i, j); // Obtener el indice del j-ésimo hijo
                if (childIndex < size && heap[childIndex] < heap[smallest]) {
                    smallest = childIndex; // Actualizar el indice si el hijo es menor
                }
            }

            // Si el nodo actual no es el mas pequeno, intercambiar y continuar
            if (smallest != i) {
                swap(i, smallest); // Intercambiar el nodo actual con el mas pequeno
                minHeapify(smallest); // Recursivamente ajustar el subarbol afectado
            }
        }

        // Metodo para obtener el indice del j-ésimo hijo
        private int childIndex(int i, int j) {
            return k * i + j; // Calculo basado en la estructura de un heap k-ario
        }

        // Metodo para obtener el indice del padre
        private int parentIndex(int i) {
            return (i - 1) / k; // Calculo basado en la estructura de un heap k-ario
        }

        // Metodo para intercambiar dos elementos en el heap
        private void swap(int i, int j) {
            int temp = heap[i]; // Guardar el valor en la posicion i
            heap[i] = heap[j]; // Mover el valor de j a la posicion i
            heap[j] = temp; // Mover el valor guardado a la posicion j
        }
    }

    public static void main(String[] args) throws IOException {
    }
}