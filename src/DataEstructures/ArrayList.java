package DataEstructures;

import java.util.Arrays;

public class ArrayList<T> {
    private static final int DEFAULT_CAPACITY = 10; // Capacidad inicial por defecto
    private Object[] elements; // Array de elementos
    private int size = 0; // Número de elementos en la lista

    // Constructor sin parámetros
    public ArrayList() {
        elements = new Object[DEFAULT_CAPACITY]; // Inicializar con capacidad por defecto
    }

    // Constructor con capacidad inicial especificada
    public ArrayList(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("La capacidad inicial debe ser mayor a 0.");
        }
        elements = new Object[initialCapacity]; // Inicializar con capacidad especificada
    }

    // Método para agregar un elemento al final de la lista
    public void add(T element) {
        if (size == elements.length) {
            resize(); // Redimensionar el array si está lleno
        }
        elements[size++] = element; // Agregar el elemento y aumentar el tamaño
    }

    // Método para obtener un elemento en una posición específica
    public T get(int index) {
        checkIndex(index); // Verificar si el índice es válido
        return (T) elements[index]; // Devolver el elemento en la posición especificada
    }

    // Método para eliminar un elemento en una posición específica
    public T remove(int index) {
        checkIndex(index); // Verificar si el índice es válido
        T removedElement = (T) elements[index]; // Guardar el elemento a eliminar
        int numMoved = size - index - 1; // Número de elementos a mover
        if (numMoved > 0) {
            System.arraycopy(elements, index + 1, elements, index, numMoved); // Mover elementos
        }
        elements[--size] = null; // Eliminar el último elemento y reducir el tamaño
        return removedElement; // Devolver el elemento eliminado
    }

    // Método para obtener el número de elementos en la lista
    public int size() {
        return size; // Devolver el tamaño de la lista
    }

    // Método para verificar si la lista está vacía
    public boolean isEmpty() {
        return size == 0; // Devolver true si la lista está vacía, de lo contrario, false
    }

    // Método privado para redimensionar el array cuando está lleno
    private void resize() {
        int newCapacity = elements.length * 2; // Duplicar la capacidad
        elements = Arrays.copyOf(elements, newCapacity); // Copiar los elementos al nuevo array
    }

    // Método privado para verificar si un índice es válido
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Índice fuera de los límites: " + index);
        }
    }

    // Método para imprimir los elementos de la lista 
    public void printList() {
        for (int i = 0; i < size; i++) {
            System.out.print(elements[i] + " ");
        }
        System.out.println();
    }


    public static void main(String[] args) {
    }
}