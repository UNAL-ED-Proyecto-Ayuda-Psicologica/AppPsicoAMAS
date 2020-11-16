package DataStructures;

public class PriorityQueue<T> implements Queue<T>{
    private Heap<PrioNode<T>> heap;

    public PriorityQueue(){
        this.heap= new Heap<>();
    }

    @Override
    public void enqueue(T value) {
        this.enqueue(value,99);
    }

    @Override
    public void enqueue(T value, int priority) {
        this.heap.insert(new PrioNode<T>(value,priority));
    }

    @Override
    public T dequeue() {
        return this.heap.deleteMin().data;
    }

    @Override
    public T peek() {
        return this.heap.findMin().data;
    }

    @Override
    public boolean isEmpty() {
        return this.heap.isEmpty();
    }

    @Override
    public int length() {
        return this.heap.length();
    }

    private static class PrioNode<T> implements Comparable<PrioNode<T>>{
        T data;
        int priority;

        PrioNode(T data,int priority){
            this.data = data;
            this.priority=priority;
        }

        @Override
        public int compareTo(PrioNode<T> o) {
            if(o==null){
                return this.priority;
            }else {
                return this.priority - o.priority;
            }
        }
    }
}
