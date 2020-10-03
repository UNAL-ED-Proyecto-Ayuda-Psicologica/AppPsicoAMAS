package DataStructures;

public class Stack<T> {

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value) {
            this.value = value;
            this.next = null;
        }
    }

    private Node<T> top;
    private Integer length;

    public Stack() {
        top = null;
        length = 0;
    }

    public int getLength() {
        return length;
    }

    public void push(T value) {
        length++;
        Node<T> newNode = new Node<T>(value);
        newNode.next = top;
        top = newNode;
    }

    public T pop() {
        if (length != 0) {
            Node<T> node = top;
            top = top.next;
            length--;
            return node.value;
        } else {
            System.out.println("Pila vacía, no se pueden retirar elementos");
            return null;
        }
    }

    public T peek() {
        if (length != 0) {
            return top.value;
        } else {
            System.out.println("Pila vacía, no se puede ver ningún elemento");
            return null;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<T> aux = top;
        while (aux.next != null) {
            sb.append(aux.value + ", ");
            aux = aux.next;
        }
        sb.append(aux.value);
        sb.append("]");
        return sb.toString();
    }
}

