package DataStructures;

public interface Queue<T>{
    void enqueue(T value);
    void enqueue(T value, int priority);
    T dequeue();
    T peek();
    boolean isEmpty();
    int length();
}
