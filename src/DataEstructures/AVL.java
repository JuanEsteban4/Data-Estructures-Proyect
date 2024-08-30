package DataEstructures;


public class AVL {
    
    // Clase Node representa un nodo en el árbol AVL
 class Node {

    int key;        // Valor clave del nodo
    int count;      // Contador de ocurrencias del nodo
    Node left, right;  // Referencias a los hijos izquierdo y derecho
    int height;     // Altura del nodo en el árbol

    // Constructor que inicializa un nodo con un valor específico
    Node(int k) {
        key = k;
        count = 1; // Inicialmente, el nodo aparece una vez
        left = right = null; // Los hijos son nulos al inicio
        height = 1; // La altura del nodo recién creado es 1
    }
}

    // Función auxiliar para obtener la altura de un nodo en el árbol
    static int height(Node N) {
        if (N == null) {
            return 0; // Si el nodo es nulo, la altura es 0
        }
        return N.height; // De lo contrario, devuelve la altura del nodo
    }

    // Función para realizar una rotación hacia la derecha en un subárbol
    static Node rightRotate(Node y) {
        Node x = y.left; // El hijo izquierdo de y se convierte en la nueva raíz
        Node T2 = x.right; // El subárbol derecho de x se convierte en el subárbol izquierdo de y

        // Realizar la rotación
        x.right = y;
        y.left = T2;

        // Actualizar las alturas de los nodos afectados
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        // Devolver la nueva raíz
        return x;
    }

    // Función para realizar una rotación hacia la izquierda en un subárbol
    static Node leftRotate(Node x) {
        Node y = x.right; // El hijo derecho de x se convierte en la nueva raíz
        Node T2 = y.left; // El subárbol izquierdo de y se convierte en el subárbol derecho de x

        // Realizar la rotación
        y.left = x;
        x.right = T2;

        // Actualizar las alturas de los nodos afectados
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        // Devolver la nueva raíz
        return y;
    }

    // Función para obtener el factor de balance de un nodo
    static int getBalance(Node N) {
        if (N == null) {
            return 0; // Si el nodo es nulo, el balance es 0
        }
        return height(N.left) - height(N.right); // El balance es la diferencia de alturas entre los hijos izquierdo y derecho
    }

    // Función para insertar un nuevo nodo en el árbol AVL
    public Node insert(Node node, int key) {
        // Caso base: si el nodo es nulo, crear un nuevo nodo
        if (node == null) {
            return new Node(key);
        }

        // Si la clave es menor, insertar en el subárbol izquierdo
        if (key < node.key) {
            node.left = insert(node.left, key);
        } // Si la clave es mayor, insertar en el subárbol derecho
        else if (key > node.key) {
            node.right = insert(node.right, key);
        } else {
            // Si la clave ya existe, incrementar el contador de ocurrencias
            node.count++;
            return node;
        }

        // Actualizar la altura del nodo antecesor
        node.height = Math.max(height(node.left), height(node.right)) + 1;

        // Obtener el factor de balance para verificar si el nodo se ha desbalanceado
        int balance = getBalance(node);

        // Caso Izquierda-Izquierda (LL): si el subárbol izquierdo está desbalanceado y la clave está en el subárbol izquierdo
        if (balance > 1 && key < node.left.key) {
            return rightRotate(node);
        }

        // Caso Derecha-Derecha (RR): si el subárbol derecho está desbalanceado y la clave está en el subárbol derecho
        if (balance < -1 && key > node.right.key) {
            return leftRotate(node);
        }

        // Caso Izquierda-Derecha (LR): si el subárbol izquierdo está desbalanceado y la clave está en el subárbol derecho del hijo izquierdo
        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Caso Derecha-Izquierda (RL): si el subárbol derecho está desbalanceado y la clave está en el subárbol izquierdo del hijo derecho
        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        // Retornar el nodo sin modificaciones si no hay desbalanceo
        return node;
    }

    // Función para obtener el nodo con el valor mínimo en el árbol
    public static Node minValueNode(Node node) {
        Node current = node;

        // Recorrer hacia la izquierda para encontrar el nodo más a la izquierda
        while (current.left != null) {
            current = current.left;
        }

        return current; // Retorna el nodo con el valor mínimo
    }

    // Función recursiva para eliminar un nodo con una clave dada
    public static Node deleteNode(Node root, int key) {
        // Realizar la eliminación estándar de un nodo en BST
        if (root == null) {
            return root;
        }

        // Si la clave a eliminar es menor que la clave de la raíz, va al subárbol izquierdo
        if (key < root.key) {
            root.left = deleteNode(root.left, key);
        } // Si la clave a eliminar es mayor que la clave de la raíz, va al subárbol derecho
        else if (key > root.key) {
            root.right = deleteNode(root.right, key);
        } // Si la clave es igual a la clave de la raíz, este es el nodo a eliminar
        else {
            // Si el nodo tiene más de una ocurrencia, disminuir el contador
            if (root.count > 1) {
                root.count--;
                return root; // Retorna el nodo con el contador decrementado
            }

            // Caso de nodo con un solo hijo o sin hijos
            if ((root.left == null) || (root.right == null)) {
                Node temp = root.left != null ? root.left : root.right;

                // Caso sin hijos
                if (temp == null) {
                    temp = root;
                    root = null;
                } else // Caso de un solo hijo
                {
                    root = temp; // Copiar los contenidos del hijo no nulo
                }
            } else {
                // Nodo con dos hijos: obtener el sucesor en orden (mínimo en el subárbol derecho)
                Node temp = minValueNode(root.right);

                // Copiar los datos del sucesor en orden a este nodo
                root.key = temp.key;
                root.count = temp.count;
                temp.count = 1;

                // Eliminar el sucesor en orden
                root.right = deleteNode(root.right, temp.key);
            }
        }

        // Si el árbol tenía solo un nodo, retornarlo
        if (root == null) {
            return root;
        }

        // Actualizar la altura del nodo actual
        root.height = Math.max(height(root.left), height(root.right)) + 1;

        // Obtener el factor de balance del nodo actual para verificar si está desbalanceado
        int balance = getBalance(root);

        // Realizar las rotaciones necesarias según el tipo de desbalanceo
        // Caso Izquierda-Izquierda (LL)
        if (balance > 1 && getBalance(root.left) >= 0) {
            return rightRotate(root);
        }

        // Caso Izquierda-Derecha (LR)
        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        // Caso Derecha-Derecha (RR)
        if (balance < -1 && getBalance(root.right) <= 0) {
            return leftRotate(root);
        }

        // Caso Derecha-Izquierda (RL)
        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root; // Retornar la raíz del subárbol modificado
    }

    // Función para imprimir el recorrido en preorden del árbol
    public static void preOrder(Node root) {
        if (root != null) {
            System.out.print(root.key + "(" + root.count + ") ");
            preOrder(root.left);
            preOrder(root.right);
        }
    }

}
