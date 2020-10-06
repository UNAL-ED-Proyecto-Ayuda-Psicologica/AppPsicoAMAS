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
        this.front = null;
        this.back = null;
        this.length = 0;
    }

    public int length() {
        return this.length;
    }

    public void enqueue(T value) {
        if (length != 0) {
            Node<T> newNode = new Node<>(value);
            this.back.next = newNode;
            this.back = newNode;
        } else {
            this.front = this.back = new Node<>(value);
        }
        this.length++;
    }

    public T dequeue() {
        if (length != 0) {
            Node<T> firstNode = this.front;
            this.front = front.next;
            this.length--;
            return firstNode.value;
        } else {
            System.out.println("Lista vacía, no se puede eliminar ningún elemento");
            return null;
        }
    }

    public T peek() {
        if (length != 0) {
            return this.front.value;
        } else {
            System.out.println("Lista vacía, no se puede ver ningún elemento");
            return null;
        }
    }

    public boolean isEmpty() {
        return this.length == 0;
    }

    public void makeEmpty(){
        this.length = 0;
        this.front = null;
        this.back = null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("front>> ");
        if (this.length != 0) {
            Node<T> aux = this.front;
            while (aux.next != null) {
                sb.append(aux.value + ", ");
                aux = aux.next;
            }
            sb.append(aux.value);
        }
        sb.append(" <<back");
        return sb.toString();
    }
}

