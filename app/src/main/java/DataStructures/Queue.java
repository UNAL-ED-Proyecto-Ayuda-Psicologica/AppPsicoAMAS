package DataStructures;

public class Queue<T> {

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value) {
            this.value = value;
            this.next = null;
        }
    }

    private Node<T> front;
    private Node<T> back;
    private Integer length;

    public Queue() {
        front = null;
        back = null;
        length = 0;
    }

    public void enqueue(T value) {
        length++;
        if (length != 0) {
            Node<T> node = new Node<>(value);
            back.next = node;
            back = node;
        } else {
            front = back = new Node<>(value);
        }
    }

    public T peek() {
        if (length != 0) {
            return front.value;
        } else {
            System.out.println("Lista vacía, no se puede ver ningún elemento");
            return null;
        }
    }

    public T dequeue() {
        if (length != 0) {
            Node<T> node = front;
            front = front.next;
            length--;
            return node.value;
        } else {
            System.out.println("Lista vacía, no se puede eliminar ningún elemento");
            return null;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<T> aux = this.front;
        if (front == null) {


        } else {
            while (aux.next != null) {
                sb.append(aux.value + ", ");
                aux = aux.next;
            }
            sb.append(aux.value);

        }
        sb.append("]");
        return sb.toString();
    }
}

