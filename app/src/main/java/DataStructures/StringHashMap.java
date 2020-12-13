package DataStructures;

public class StringHashMap<V>{

    private SimplyLinkedList<V>[] values;
    private SimplyLinkedList<String>[] keys;
    //private boolean[] usedPositions;
    private int DEFAULT_SIZE = 10000;
    private int capacity ;
    private int size;

    public StringHashMap(int size){
        this.capacity = size;
        this.keys = new SimplyLinkedList[size];
        this.values = new SimplyLinkedList[size];
        //this.usedPositions = new boolean[size];
        for(int i=0;i<size;i++){
            this.keys[i]=new SimplyLinkedList<>();
            this.values[i]=new SimplyLinkedList<>();
        }
    }

    public StringHashMap(){
        this.capacity = DEFAULT_SIZE;
        this.keys = new SimplyLinkedList[this.DEFAULT_SIZE];
        this.values = new SimplyLinkedList[this.DEFAULT_SIZE];
        //this.usedPositions = new boolean[this.DEFAULT_SIZE];
        for(int i=0;i<DEFAULT_SIZE;i++){
            this.keys[i]=new SimplyLinkedList<>();
            this.values[i]=new SimplyLinkedList<>();
        }
    }

    private int hash(String key){
        int index= hasheito(key);
        return index % this.capacity;
    }

    public void put(String key, V value){
        int index = hash(key);
        if(keys[index]==null || values[index]==null){
            this.keys[index]=new SimplyLinkedList<>();
            this.values[index]= new SimplyLinkedList<>();
        }
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

    private int hasheito(String key){
        int n=key.length()/5;
        if(n==0)n=1;
        int hasheito =0;
        for(int i=0; i<key.length() && i<5*n;i+=n){
            hasheito=13*hasheito + key.charAt(i);
        }
        return hasheito;
    }
}
