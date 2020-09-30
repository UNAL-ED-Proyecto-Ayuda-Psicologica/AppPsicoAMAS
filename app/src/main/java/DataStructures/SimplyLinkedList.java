package DataStructures;

public class SimplyLinkedList<T> extends List<T> {
    //TODO revisar implementación
    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value) {
            this.value = value;
            this.next = null;
        }
    }

    Node<T> first;
    Integer length;

    public SimplyLinkedList() {
        this.length = 0;
        this.first = null;
    }

    @Override
    T getK(Integer index) {
        if (index == 0) {
            return this.first.value;
        } else if (index >= length) {
            System.out.println("El Ã­ndice es mayor al tamaÃ±o de la lista");
            return null;
        } else {
            Node<T> aux = this.first;
            for (int i = 0; i < index; i++) {
                aux = aux.next;
            }
            return aux.value;
        }
    }

    @Override
    T getFirst() {
        if (this.length != 0) {
            return first.value;
        } else {
            return null;
        }
    }

    @Override
    void insertIndex(Integer index, T value) {
        if (index >= length) {
            System.out.println("El Ã­ndice es mayor al tamaÃ±o de la lista");
        } else {
            Node<T> aux = this.first;
            for (Integer i = 0; i < index; i++) {
                if (i.equals(index)) {
                    Node<T> auxNext = aux.next;
                    Node<T> newNode = new Node<T>(value);
                    newNode.next = auxNext;
                    aux.next = newNode;
                    this.length++;
                } else {
                    aux = aux.next;
                }
            }
        }

    }

    @Override
    void deleteFirst() {
        if (this.length != 0) {
            this.first = this.first.next;
            this.length--;
        } else {
            System.out.println("No puedo eliminar en una lista vacia");
        }
    }

    @Override
    void insertFirst(T element) {
        this.length++;
        Node<T> newFirst = new Node<T>(element);
        newFirst.next = this.first;
        this.first = newFirst;
    }

    @Override
    boolean isEmpty() {
        return this.length == 0;
    }

    @Override
    Integer length() {
        return this.length;
    }

    @Override
    void insert(T value) {
        if (this.length == 0) {
            this.first = new Node<T>(value);
            this.length++;
            return;
        }
        Node<T> aux = this.first;
        while (aux.next != null) {
            aux = aux.next;
        }
        aux.next = new Node<T>(value);
        this.length++;
    }

    @Override
    void delete(T value) {
        if (this.first == null) { // En caso de una lista vacía
            System.out.println("No puedo eliminar en una lista vacia");
        } else if (this.first.value.equals(value)) { // Eliminar el primer elemento de la lista
            this.deleteFirst();
        } else {
            Node<T> aux = this.first;
            while (!aux.next.value.equals(value)) {
                aux = aux.next;
                if (aux.next == null) {
                    System.out.println("El elemento no se encuentra en la lista");
                    return;
                }
            }
            aux.next = aux.next.next;
            this.length--;
        }
    }

    @Override
    Integer getIndex(T value) {
        if (this.first == null) {
            System.out.println("El elemento no hace parte de la lista");
            return -1;
        }
        Node<T> aux = this.first;
        int index = 0;
        while (aux.value != value) {
            if (aux.next == null) {
                System.out.println("El elemento no hace parte de la lista");
                return -1;
            }
            aux = aux.next;
            index++;
        }
        return index;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<T> aux = this.first;
        while (aux.next != null) {
            sb.append(aux.value + ", ");
            aux = aux.next;
        }
        sb.append(aux.value);
        sb.append("]");
        return sb.toString();
    }
}