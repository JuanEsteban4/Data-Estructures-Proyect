package DataEstructures;

// Clase Stack que extiende de Lista para representar una pila, nombrando los metodos en el contexto de la pila
public class Stack<T> extends LinkedList<T> {

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
            if (isEmpty()) {
                return null;
            } else {
                T data = head.data;
                remove(data);
                return data;
            }
        }

        // Método para obtener el elemento superior de la pila sin eliminarlo
        public T top() {
            if (isEmpty()) {
                return null;
            } else {
                return head.data;
            }
        }
        
}