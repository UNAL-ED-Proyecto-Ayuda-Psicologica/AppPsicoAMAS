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
        this.top = null;
        this.length = 0;
    }

    public int length() {
        return this.length;
    }

    public void push(T value) {
        Node<T> newNode = new Node<>(value);
        newNode.next = this.top;
        this.top = newNode;
        this.length++;
    }

    public T pop() {
        if (length != 0) {
            Node<T> topNode = this.top;
            top = top.next;
            length--;
            return topNode.value;
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

    public boolean isEmpty() {
        return this.length == 0;
    }

    public void makeEmpty() {
        this.top = null;
        this.length = 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("top>> ");
        if (this.length != 0) {
            Node<T> aux = this.top;
            while (aux.next != null) {
                sb.append(aux.value + ", ");
                aux = aux.next;
            }
            sb.append(aux.value);
        }
        sb.append(" <<floor");
        return sb.toString();
    }
}

