/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataEstructures;

public class LinkedList<T> {
        
        public Node<T> head;
        public int size = 0;

        // Método para verificar si la lista está vacía
        public boolean isEmpty() {
            return head == null;
        }

        // Método para añadir un nodo al principio de la lista
        public void pushFront(T data) {
            size++;
            Node<T> newNode = new Node(data);
            if (isEmpty()) {
                head = newNode;
            } else {
                newNode.next = head;
                head = newNode;
            }
        }

        // Método para imprimir la lista
        public void printList() {
            int p= 0;
            if (isEmpty()) {
                System.out.println("Lista vacia");
                return;
            }
            Node<T> temp = head;
            while (temp != null) {
                System.out.println('(' + String.valueOf(p)+')' + temp.data);
                p++;
                temp = temp.next;
            }
        }

        // Método para eliminar un nodo de la lista
        public void remove(T data) {
            if (isEmpty()) {
                return;
            }
            if (data == head.data) {
                head = head.next;
                return;
            }
            Node<T> temp = head;
            while (temp.next != null) {
                if (temp.next.data == data) {
                    temp.next = temp.next.next;
                    return;
                }
                temp = temp.next;
            }
        }
        
        // Método para obtener un elemento de la lista por indice
        public T get(int index){
            if(index >= size){
                return null;
            }
            int i= 0;
            Node<T> temp = head;
            while(index>i){
                temp = temp.next;
                i++;
            }
            return temp.data;
        }
}

