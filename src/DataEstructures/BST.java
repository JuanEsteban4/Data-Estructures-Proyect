package DataEstructures;

import java.util.ArrayList;
import java.util.List;
import modelo.Asignatura;

public class BST {

    Node root;

    // Clase Node representa un nodo en el árbol BST
    class Node {
        Asignatura asignatura; // Objeto de tipo Asignatura
        Node left, right; // Referencias a los hijos izquierdo y derecho

        // Constructor que inicializa un nodo con un objeto Asignatura
        Node(Asignatura asignatura) {
            this.asignatura = asignatura;
            left = right = null; // Los hijos son nulos al inicio
        }
    }

    // Método para insertar un nodo en el árbol BST
    public void insertBST(Asignatura asignatura) {
        root = insert(root, asignatura);
    }

    // Función para insertar un nodo en el árbol BST
    private Node insert(Node node, Asignatura asignatura) {
        if (node == null) {
            return new Node(asignatura);
        }

        // Comparar por el código de Asignatura (como cadena)
        int compareResult = asignatura.getCodigo().compareTo(node.asignatura.getCodigo());
        if (compareResult < 0) {
            node.left = insert(node.left, asignatura);
        } else if (compareResult > 0) {
            node.right = insert(node.right, asignatura);
        } else {
            // Si el código ya existe, actualiza el nodo existente
            node.asignatura = asignatura;
        }

        return node;
    }

    // Método para eliminar un nodo del árbol BST
    public void deleteBST(String codigo) {
        root = deleteNode(root, codigo);
    }

    // Función para eliminar un nodo del árbol BST
    private Node deleteNode(Node root, String codigo) {
        if (root == null) {
            return root;
        }

        int compareResult = codigo.compareTo(root.asignatura.getCodigo());
        if (compareResult < 0) {
            root.left = deleteNode(root.left, codigo);
        } else if (compareResult > 0) {
            root.right = deleteNode(root.right, codigo);
        } else {
            // Nodo con solo un hijo o sin hijos
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            // Nodo con dos hijos
            Node temp = minValueNode(root.right);
            root.asignatura = temp.asignatura;
            root.right = deleteNode(root.right, temp.asignatura.getCodigo());
        }

        return root;
    }

    // Función para encontrar el nodo con el valor mínimo en el árbol
    private Node minValueNode(Node node) {
        Node current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    // Método para encontrar una Asignatura en el árbol BST
    public Asignatura findBST(String codigo) {
        Node node = find(root, codigo);
        return node != null ? node.asignatura : null; // Manejo del caso nulo
    }

    // Función para encontrar un nodo en el árbol BST
    public Node find(Node root, String codigo) {
        if (root == null) {
            return null;
        }

        int compareResult = codigo.compareTo(root.asignatura.getCodigo());
        if (compareResult < 0) {
            return find(root.left, codigo);
        } else if (compareResult > 0) {
            return find(root.right, codigo);
        } else {
            return root;
        }
    }

    // Método para obtener todos los códigos en el árbol
    public List<String> getAllCodes(Node root, List<String> codes) {
        if (root != null) {
            getAllCodes(root.left, codes);
            codes.add(root.asignatura.getCodigo());
            getAllCodes(root.right, codes);
        }
        return codes;
    }

    // Método para eliminar la mitad de los elementos
    public void removeHalfElements() {
        List<String> allCodes = new ArrayList<>();
        getAllCodes(root, allCodes);

        int halfSize = allCodes.size() / 2;
        for (int i = 0; i < halfSize; i++) {
            deleteBST(allCodes.get(i));
        }
    }

    // Método principal para probar la funcionalidad
    public static void main(String[] args) {
    
}

}
