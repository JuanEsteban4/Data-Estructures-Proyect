package DataEstructures;

// Clase Stack que extiende de Lista para representar una pila, nombrando los metodos en el contexto de la pila
public class pila<T> extends List<T> {

        // Método para vaciar la pila
        public void clear() {
            head = null;
        }

        // Método para añadir un elemento a la pila (equivalente a addFirst de Lista)
        public void push(T data) {
            pushFront(data);
        }

        // Método para eliminar y devolver el elemento superior de la pila (equivalente a remove de Lista)
        public T pop() {
            if (Empty()) {
                return null;
            } else {
                T data = head.data;
                remove(data);
                return data;
            }
        }

        // Método para obtener el elemento superior de la pila sin eliminarlo
        public T top() {
            if (Empty()) {
                return null;
            } else {
                return head.data;
            }
        }
        
        
        public pila<T> clone() {
            return this;
        }
}


    // Clase Node para representar un nodo en la pila
    class Node<T> {
        T data;
        Node<T> next;

        // Constructor de Node
        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    // Clase Lista para tener clase padre con todos los atributos
    class List<T> {
        
        Node<T> head;

        // Método para verificar si la lista está vacía
        public boolean Empty() {
            return head == null;
        }

        // Método para añadir un nodo al principio de la lista
        public void pushFront(T data) {
            Node<T> newNode = new Node(data);
            if (Empty()) {
                head = newNode;
            } else {
                newNode.next = head;
                head = newNode;
            }
        }

        // Método para imprimir la lista
        public void printList() {
            if (Empty()) {
                System.out.println("Lista vacia");
                return;
            }
            Node<T> temp = head;
            while (temp != null) {
                System.out.println(temp.data);
                temp = temp.next;
            }
        }

        // Método para eliminar un nodo de la lista
        public void remove(T data) {
            if (Empty()) {
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
    }

    
    



