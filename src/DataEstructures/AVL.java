package DataEstructures;

import modelo.Asignatura;

public class AVL {

    Node root;

    // Clase Node representa un nodo en el árbol AVL
    class Node {

        Asignatura asignatura; // Objeto de tipo Asignatura
        int count; // Contador de ocurrencias del nodo
        Node left, right; // Referencias a los hijos izquierdo y derecho
        int height; // Altura del nodo en el árbol

        // Constructor que inicializa un nodo con un objeto Asignatura
        Node(Asignatura asignatura) {
            this.asignatura = asignatura;
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

    // Método para insertar un nodo en el árbol AVL
    public int insertAVL(Asignatura key) {
        root = insert(root, key); // Actualiza la raíz con el resultado de la inserción
        return Integer.parseInt(key.getCodigo());
    }

    // Función para insertar un nodo en el árbol AVL
    public Node insert(Node node, Asignatura asignatura) {
        if (node == null) {
            return new Node(asignatura);
        }

        // Comparar por el código de Asignatura
        if (Integer.parseInt(asignatura.getCodigo()) < Integer.parseInt(node.asignatura.getCodigo())) {
            node.left = insert(node.left, asignatura);
        } else if (Integer.parseInt(asignatura.getCodigo()) > Integer.parseInt(node.asignatura.getCodigo())) {
            node.right = insert(node.right, asignatura);
        } else {
            node.count++;
            return node;
        }

        node.height = Math.max(height(node.left), height(node.right)) + 1;
        int balance = getBalance(node);

        if (balance > 1 && Integer.parseInt(asignatura.getCodigo()) < Integer.parseInt(node.left.asignatura.getCodigo())) {
            return rightRotate(node);
        }

        if (balance < -1 && Integer.parseInt(asignatura.getCodigo()) > Integer.parseInt(node.right.asignatura.getCodigo())) {
            return leftRotate(node);
        }

        if (balance > 1 && Integer.parseInt(asignatura.getCodigo()) > Integer.parseInt(node.left.asignatura.getCodigo())) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && Integer.parseInt(asignatura.getCodigo()) < Integer.parseInt(node.right.asignatura.getCodigo())) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // Método para eliminar un nodo del árbol AVL
    public Node deleteNode(Node root, int codigo) {
        if (root == null) {
            return root;
        }

        if (codigo < Integer.parseInt(root.asignatura.getCodigo())) {
            root.left = deleteNode(root.left, codigo);
        } else if (codigo > Integer.parseInt(root.asignatura.getCodigo())) {
            root.right = deleteNode(root.right, codigo);
        } else {
            if (root.count > 1) {
                root.count--;
                return root;
            }

            if ((root.left == null) || (root.right == null)) {
                Node temp = root.left != null ? root.left : root.right;
                if (temp == null) {
                    temp = root;
                    root = null;
                } else {
                    root = temp;
                }
            } else {
                Node temp = minValueNode(root.right);
                root.asignatura = temp.asignatura;
                root.count = temp.count;
                temp.count = 1;
                root.right = deleteNode(root.right, Integer.parseInt(temp.asignatura.getCodigo()));
            }
        }

        if (root == null) {
            return root;
        }

        root.height = Math.max(height(root.left), height(root.right)) + 1;
        int balance = getBalance(root);

        if (balance > 1 && getBalance(root.left) >= 0) {
            return rightRotate(root);
        }

        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        if (balance < -1 && getBalance(root.right) <= 0) {
            return leftRotate(root);
        }

        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    // Método para eliminar un nodo del árbol AVL
    public void deleteAVL(int codigo) {
        root = deleteNode(root, codigo); // Actualiza la raíz después de la eliminación
    }

    // Función para encontrar un nodo en el árbol AVL
    public Node find(Node root, int codigo) {
        if (root == null) {
            return null;
        }

        if (codigo < Integer.parseInt(root.asignatura.getCodigo())) {
            return find(root.left, codigo);
        } else if (codigo > Integer.parseInt(root.asignatura.getCodigo())) {
            return find(root.right, codigo);
        } else {
            return root;
        }
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

    // Función para imprimir el recorrido en preorden del árbol
    public static void preOrder(Node root) {
        if (root != null) {
            System.out.print(root.asignatura.getCodigo() + "(" + root.count + ") ");
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    // Método para encontrar una Asignatura en el árbol AVL
    public Asignatura findAVL(int key) {
        Node codigo = find(root, key);
        return codigo != null ? codigo.asignatura : null; // Manejo del caso nulo
    }
}
