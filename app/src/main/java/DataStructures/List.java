package DataStructures;

public abstract class List<T> {

    abstract void insert(T value);

    abstract void delete(T value);

    abstract T getK(Integer index);

    abstract T getFirst();

    abstract void insertIndex(Integer index, T value);

    abstract void insertFirst(T element);

    abstract void deleteFirst();

    abstract boolean isEmpty();

    abstract Integer length();

    abstract Integer getIndex(T value);
}
