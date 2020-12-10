package DataStructures;

public class StringHashMap<V>{

    private SimplyLinkedList<V>[] values;
    private SimplyLinkedList<String>[] keys;
    //private boolean[] usedPositions;
    private int DEFAULT_SIZE = 101;
    private int capacity ;
    private int size;

    public StringHashMap(int size){
        this.capacity = size;
        this.keys = new SimplyLinkedList[size];
        this.values = new SimplyLinkedList[size];
        //this.usedPositions = new boolean[size];
    }

    public StringHashMap(){
        this.capacity = DEFAULT_SIZE;
        this.keys = new SimplyLinkedList[this.DEFAULT_SIZE];
        this.values = new SimplyLinkedList[this.DEFAULT_SIZE];
        //this.usedPositions = new boolean[this.DEFAULT_SIZE];
    }

    private int hash(String key){
        int index= key.hashCode();
        return index % this.capacity;
    }

    public void put(String key, V value){
        int index = hash(key);
        keys[index].insertFirst(key);
        values[index].insertFirst(value);
        size++;
        //usedPositions[index]=true;
    }

    public V get(String key){
        int index = hash(key);
        return values[index].getK(keys[index].getIndex(key));
    }

    public void delete(String key){

        int index = hash(key);
        values[index].deleteIndex(keys[index].getIndex(key));
        keys[index].delete(key);
        size--;
        //usedPositions[index]=false;
    }

    public boolean containsKey(String key){
        int index = hash(key);
        return keys[index].getIndex(key)>-1;
        //return usedPositions[index];
    }
}
