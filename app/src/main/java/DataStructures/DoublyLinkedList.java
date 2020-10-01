package DataStructures;

public class DoublyLinkedList<T> extends List<T> {
    //TODO revisar implementación
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

    Node<T> first;
    Node<T> last;
    Integer length;

    public DoublyLinkedList() {
        this.length = 0;
        this.first = null;
        this.last = null;
    }

    @Override
    T getK(Integer index) {
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
    T getFirst() {
        if (this.length != 0) {
            return first.value;
        } else {
            return null;
        }
    }
    T getLast() {
        if(this.length != 0) {
            return last.value;
        } else {
            System.out.println("Lista Vacía");
            return null;
        }
    }

    @Override
    void insertIndex(Integer index, T value) {
        if (index >= length) {
            System.out.println("El índice es mayor al tamaño de la lista");
        } else {
            Node<T> aux = this.first;
            for (Integer i = 0; i < index; i++) {
                if (i.equals(index)) {
                    Node<T> auxNext = aux.next;
                    Node<T> newNode = new Node<T>(value);
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
        if (length == 1) {
            this.first = this.last = new Node<T>(element);
            return;
        }
        Node<T> newFirst = new Node<T>(element);
        newFirst.next = this.first;
        this.first.prev = newFirst;
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
            this.first = this.last = new Node<T>(value);
            this.length++;
            return;
        }
        Node<T> newNode = new Node<T>(value);
        this.last.next = newNode;
        newNode.prev = this.last;
        newNode.next = null;
        this.last = newNode;
        this.length++;
    }

    @Override
    void delete(T value) {
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
            aux.prev.next = aux.next; //revisar si esa vaina funciona jajajaj
            aux.next.prev = aux.prev; //al parecer sí :v
            this.length--;
        }
    }
    void deleteLast() {
        if(this.length == 0) {
            System.out.println("Lista Vacía, no se puede eliminar");
        }else if(this.length == 1) {
            deleteFirst();
        }else {
            this.last = this.last.prev;
            this.last.next = null;
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

