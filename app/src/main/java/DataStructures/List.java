package DataStructures;

public abstract class List<T> {

    public abstract void insert(T value);

    public abstract void insertFirst(T value);

    public abstract void insertIndex(int index, T value);

    public abstract void delete(T value);

    public abstract void deleteFirst();

    public abstract void deleteLast();

    public abstract void deleteIndex(int index);

    public abstract void makeEmpty();

    public abstract T getFirst();

    public abstract T getLast();

    public abstract T getK(int index);

    public abstract int getIndex(T value);

    public abstract boolean isEmpty();

    public abstract Integer length();

}
