package DataEstructures;

public class Node<T> {
        T data;
        Node<T> next;

        // Constructor de Node
        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }
