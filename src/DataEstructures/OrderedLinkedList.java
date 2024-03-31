package DataEstructures;

public class OrderedLinkedList<T extends Comparable<T>>{
        //atributo head de tipo nodo<T>
        private Node<T> head;

        //Set & Get de Head
        public Node<T> getHead() {
            return head;
        }

        public void setHead(Node<T> head) {
            this.head = head;
        }

        //Metodo contructor con head en null
        public OrderedLinkedList() {
            head = null;
        }

        //Clase node<T>
        private static class Node<T> {
            private T data;
            private Node<T> next;

            //Constructor
            public Node(T data) {
                this.data = data;
                this.next = null;
            }

            //Getters & Setters de atributos nodo
            public T getData() {
                return data;
            }

            public void setData(T data) {
                this.data = data;
            }

            public Node<T> getNext() {
                return next;
            }

            public void setNext(Node<T> next) {
                this.next = next;
            }
        }

        //Metodo Insert
        public void insert(T item) {
            Node<T> newNode = new Node<>(item);
            if (head == null || item.compareTo(head.getData()) < 0) {
                newNode.setNext(head);
                head = newNode;
            } else {
                Node<T> current = head;
                while (current.getNext() != null && item.compareTo(current.getNext().getData()) > 0) {
                    current = current.getNext();
                }
                newNode.setNext(current.getNext());
                current.setNext(newNode);
            }
        }


        public boolean delete(T item) {
        if (head == null) {
            return false; // 1 Caso: si head es null.
        }
        if (head.getData().equals(item)) {
            head = head.getNext();              // 2 Caso: Si el dato de la cabeza es igual al item a eliminar
            return true;                        // la cabeza va a apuntar al siguiente nodo y el garbage collector se encarga del que no apunta a nada (delete).
        }
        Node<T> current = head; // Establecemos un nodo en la posicion actual de la cabeza
        while (current.getNext() != null) {                     
            if (current.getNext().getData().equals(item)) {     // Caso 3: Mientras el nodo actual no sea nulo y si el valor del nodo
                current.setNext(current.getNext().getNext());  // siguiente es igual a mi item entonces mi current va a apuntar al siguiente del siguiente.
                return true;
            }
            current = current.getNext(); //Avanza 1 nodo.
        }
        return false;
    }


        //Metodo para imprimir recursivamente usando otro metodo printR.
        public String printRecursive() {
            return printR(head);
        }

        private String printR(Node<T> p) {
            if (p == null) {
                return "";
            }
            return p.getData() + "\n" + printR(p.getNext());
        }

        //Metodo para medir la dimension de la lista.
        public int size() {
        int count = 0;
        Node<T> current = head;
        while (current != null) {
            count++;
            current = current.getNext();
        }
        return count;
    }
    
}