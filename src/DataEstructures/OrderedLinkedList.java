package DataEstructures;

public class OrderedLinkedList<T extends Comparable<T>>{
        //atributo head de tipo nodo<T>
        public Node<T> head;

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
        public static class Node<T> {
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

        
        //Metodo para encontrar un dato con busqueda binaria (BigO(Log(n)))
        public Node<T> find(OrderedLinkedList<T> list, T key) {
            Node<T> left = list.getHead();
            Node<T> right = findFinal(list);

            while (left != right && left != null && right != null && left.getNext() != right) {
                Node<T> mid = getMidNode(left, right);

                if (mid.getData().compareTo(key) == 0) {
                    return mid;
                } else if (mid.getData().compareTo(key) < 0) {
                    left = mid.getNext();
                } else {
                    right = mid;
                }
            }

            // Verificar si el elemento está en la posición left o right
            if (left != null && left.getData().compareTo(key) == 0) {
                return left;
            }
            if (right != null && right.getData().compareTo(key) == 0) {
                return right;
            }

            // Elemento no encontrado
            return null;
        }

        //Metodo para encontrar el nodo final de la lista
        private Node<T> findFinal(OrderedLinkedList<T> list) {
            Node<T> current = list.getHead();
            while (current != null && current.getNext() != null) {
                current = current.getNext();
            }
            return current;
        }

        //Metodo para encontrar el valor de la mitad de mi lista
        private Node<T> getMidNode(Node<T> left, Node<T> right) {
            Node<T> slow = left;
            Node<T> fast = left; // Cuando fast termina la iteración completa, slow está en la mitad de la lista
                                 // porque avanza de 2 en 2 el fast y slow avanza en 1
            while (fast != right && fast.getNext() != right) {
                slow = slow.getNext();
                fast = fast.getNext().getNext();
            }

            return slow;
        }
        
        
        //Metodo para borrar un item
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