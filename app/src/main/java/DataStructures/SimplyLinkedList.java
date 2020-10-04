package DataStructures;

public class SimplyLinkedList<T> extends List<T> {
    // TODO Añadir documentación
    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value) {
            this.value = value;
            this.next = null;
        }
    }

    private Node<T> first;
    private Integer length;

    public SimplyLinkedList() {
        this.length = 0;
        this.first = null;
    }

    @Override
    public void insert(T value) {
        if (length == 0) {
            this.first = new Node<>(value);
            this.length++;
            return;
        }
        Node<T> aux = this.first;
        while (aux.next != null) {
            aux = aux.next;
        }
        aux.next = new Node<>(value);
        this.length++;
    }

    @Override
    public void insertFirst(T value) {
        this.length++;
        Node<T> newFirst = new Node<>(value);
        newFirst.next = this.first;
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
            System.out.println("No se puede agregar elemento: El índice es mayor al tamaño de la lista");
        } else if (index == 0) {
            insertFirst(value);
        } else {
            Node<T> aux = this.first;
            for (int i = 0; i < index; i++) {
                if (i == (index - 1)) {
                    Node<T> auxNext = aux.next;
                    Node<T> newNode = new Node<>(value);
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
    public void delete(T value) {
        if (this.first == null) { // En caso de una lista vacía
            System.out.println("No se puede eliminar: Lista vacía");
        } else if (this.first.value.equals(value)) { // Eliminar el primer elemento de la lista
            this.deleteFirst();
        } else {
            Node<T> aux = this.first;
            while (!aux.next.value.equals(value)) {
                aux = aux.next;
                if (aux.next == null) {
                    System.out.println("No se puede eliminar: El elemento no se encuentra en la lista");
                    return;
                }
            }
            aux.next = aux.next.next;
            this.length--;
        }
    }

    @Override
    public void deleteFirst() {
        if (this.length != 0) {
            this.first = this.first.next;
            this.length--;
        } else {
            System.out.println("No se puede eliminar: Lista vacía");
        }
    }

    @Override
    public void deleteLast() {
        if (this.first == null) { // En caso de una lista vacía
            System.out.println("No se puede eliminar: Lista vacía");
        } else if (this.length == 1) { // En caso de una lista de un solo elemento
            this.deleteFirst();
        } else {
            Node<T> aux = this.first;
            while (aux.next.next != null) {
                aux = aux.next;
            }
            aux.next = null;
            this.length--;
        }
    }

    @Override
    public void deleteIndex(int index) {
        if (index >= this.length) {
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
            Node<T> aux = this.first;
            while (aux.next != null) {
                aux = aux.next;
            }
            return aux.value;
        } else {
            System.out.println("Lista vacía");
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
            System.out.println("Lista vacía: El elemento no hace parte de la lista");
            return -1;
        } else {
            Node<T> aux = this.first;
            int index = 0;
            while (!aux.value.equals(value)) {
                if (aux.next == null) {
                    System.out.println("El elemento no hace parte de la lista");
                    return -1;
                }
                aux = aux.next;
                index++;
            }
            return index;
        }
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
