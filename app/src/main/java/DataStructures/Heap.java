package DataStructures;
import java.util.Arrays;

public class Heap<T extends Comparable<? super T>> {

    private static final int defaultCapacity = 10;
    private int size;
    private T[] array;

    public Heap() {
        this(defaultCapacity);
    }

    public Heap(int capacity) {
        this.size = 0;
        this.array = (T[]) new Comparable[capacity+1];
    }

    public void insert(T element) {

        if(size == array.length - 1) {
            enlargeArray(array.length*2 + 1);
        }
        int hole = ++size;
        for(array[0] = element; element.compareTo(array[hole/2]) < 0; hole/=2) {
            array[hole] = array[hole/2];
        }
        array[hole] = element;
    }

    public T deleteMin() {
        if(isEmpty()) {
            return null;
        }else {
            T minElement = findMin();
            array[1] = array[size--];
            percolateDown(1);
            return minElement;
        }
    }

    private void percolateDown(int hole) {
        int child;
        T aux = array[hole];
        for(;hole*2 <= size; hole = child) {
            child = hole*2;
            if(child != size && array[child+1].compareTo(array[child]) < 0) {
                child++;
            }
            if(array[child].compareTo(aux) < 0) {
                array[hole] = array[child];
            }else {
                break;
            }
        }
        array[hole] = aux;

    }

    public T findMin() {
        if(isEmpty()) {
            return null;
        }else {
            return array[1];
        }
    }

    private void enlargeArray(int newCapacity) {
        T[] newArray = (T[]) new Comparable[newCapacity];
        for (int i = 0; i < this.size; i++) {
            newArray[i] = this.array[i];
        }
        this.array = newArray;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        return "Heap [size=" + size + ", array=" + Arrays.toString(array) + "]";
    }


    public int length() {
        return this.size;
    }
}
