package DataStructures;

public class DoublyLinkedList<T extends Comparable> extends List<T> {
    // TODO Añadir documentación
    private static class Node<T> {
        T value;
        Node<T> next;
        Node<T> prev;

        public Node(T value) {
            this.value = value;
            this.next = null;
            this.prev = null;
        }
    }

    private Node<T> first;
    private Node<T> last;
    private Integer length;

    public DoublyLinkedList() {
        this.length = 0;
        this.first = null;
        this.last = null;
    }

    @Override
    public void insert(T value) {
        if (this.length == 0) {
            this.first = this.last = new Node<>(value);
            this.length++;
            return;
        }
        Node<T> newNode = new Node<>(value);
        this.last.next = newNode;
        newNode.prev = this.last;
        newNode.next = null;
        this.last = newNode;
        this.length++;
    }

    @Override
    public void insertFirst(T value) {
        this.length++;
        if (length == 1) {
            this.first = this.last = new Node<>(value);
            return;
        }
        Node<T> newFirst = new Node<>(value);
        newFirst.next = this.first;
        this.first.prev = newFirst;
        this.first = newFirst;
    }

    /**
     * insertIndex inserta un elemento en el índice index sin eliminar el que estaba
     * en esa posición El primer elemento de la lista tiene índice cero y el último
     * length - 1 Ejemplo: {1,2,3,5,6} después de insertIndex(3,4) quedará
     * {1,2,3,4,5,6}
     *
     * @param index índice donde se quiere insertar el elemento value
     * @param value nuevo elemento de la lista
     */
    @Override
    public void insertIndex(int index, T value) {
        if (index >= length) {
            System.out.println("El índice es mayor o igual al tamaño de la lista");
        } else if (index == 0) {
            insertFirst(value);
        } else {
            Node<T> aux = this.first;
            for (int i = 0; i < index; i++) {
                if (i == (index - 1)) {
                    Node<T> auxNext = aux.next;
                    Node<T> newNode = new Node<>(value);
                    newNode.next = auxNext;
                    newNode.prev = aux;
                    aux.next = newNode;
                    auxNext.prev = newNode;
                    this.length++;
                } else {
                    aux = aux.next;
                }
            }
        }

    }

    @Override
    public void delete(T value) {
        if (this.first == null) { // En caso de una lista vacía
            System.out.println("No puedo eliminar en una lista vacia");
        } else if (this.first.value == value) { // Eliminar el primer elemento de la lista
            this.deleteFirst();
        } else {
            Node<T> aux = this.first;
            while (!aux.value.equals(value)) {
                if (aux.next == null) {
                    System.out.println("El elemento no se encuentra en la lista");
                    return;
                }
                aux = aux.next;
            }
            aux.prev.next = aux.next;
            aux.next.prev = aux.prev;
            this.length--;
        }
    }

    @Override
    public void deleteFirst() {
        if (this.length != 0) {
            this.first = this.first.next;
            this.length--;
        } else {
            System.out.println("No puedo eliminar en una lista vacia");
        }
    }

    @Override
    public void deleteLast() {
        if (this.length == 0) {
            System.out.println("Lista Vacía, no se puede eliminar");
        } else if (this.length == 1) {
            deleteFirst();
        } else {
            this.last = this.last.prev;
            this.last.next = null;
            this.length--;
        }
    }

    @Override
    public void deleteIndex(int index) {
        if (this.length == 0) {
            System.out.println("No se puede eliminar elemento: El índice es mayor o igual al tamaño de la lista");
        } else if (index == 0) {
            deleteFirst();
        } else if (index == (this.length - 1)) {
            deleteLast();
        } else {
            Node<T> aux = this.first;
            int counter = 0;
            while (aux.next != null) {
                if (counter == (index - 1)) {
                    aux.next = aux.next.next;
                    aux.next.prev = aux;
                    this.length--;
                    return;
                }
                aux = aux.next;
                counter++;
            }
        }
    }

    @Override
    public void makeEmpty() {
        this.length = 0;
        this.first = null;
        this.last = null;
    }

    @Override
    public T getFirst() {
        if (this.length != 0) {
            return first.value;
        } else {
            System.out.println("Lista vacía");
            return null;
        }
    }

    @Override
    public T getLast() {
        if (this.length != 0) {
            return last.value;
        } else {
            System.out.println("Lista Vacía");
            return null;
        }
    }

    @Override
    public T getK(int index) {
        if (index == 0) {
            return this.first.value;
        } else if (index >= length) {
            System.out.println("El índice es mayor al tamaño de la lista");
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
    public int getIndex(T value) {
        if (this.first == null) {
            System.out.println("El elemento no hace parte de la lista: Lista vacía");
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
    public boolean isEmpty() {
        return this.length == 0;
    }

    @Override
    public Integer length() {
        return this.length;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        if (this.first != null) {
            Node<T> aux = this.first;
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
