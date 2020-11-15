package DataStructures;public class DynamicArray<T> {
    private T[] array;
    private int size;
    private int capacity;
    public static int number = 0;

    public DynamicArray(){

        this.array =(T[]) new Object[4];
        this.size = 0;
        this.capacity = 2;
        number++;
    }

    public int Size() {
        return this.size;
    }

    public T get(int index){
        if(index >= size || index < 0){
            System.out.println("índice no permitido");
            return null;
        };
        return this.array[index];
    }

    public void insert(int index, T value){
        if(index >= size || index < 0)
            System.out.println("índice no permitido");
        if(this.size == this.capacity)
            this.resize(10);
        for(int i = this.size; i > index; i--)
            this.array[i] = this.array[i-1];
        this.array[index] = value;
        this.size++;
    }

    public void insert(T value){ //PUSHBACK
        if(this.size == this.capacity) this.resize(2);
        this.array[this.size++] = value;
    }

    public void set(int index, T value){
        if(index >= size || index < 0)
            System.out.println("índice no permitido");
        this.array[index] = value;
    }

    public void delete(int index){
        if(index >= size || index < 0){
            System.out.println("índice no permitido");
            return;
        }
        T[] newArray =(T[]) new Object[this.capacity];
        for(int i = 0; i < index; i++)
            newArray[i] = this.array[i];
        for (int j = index+1; j < this.size;j++)
            newArray[j-1] = this.array[j];
        this.size--;
    }

    public void deleteV2(int index){
        if(index >= size || index < 0){
            System.out.println("índice no permitido");
            return;
        }
        for(int i = index; i < this.size-1; i++){
            this.array[i] = this.array[i+1];
        }
        this.array[--this.size] = null;
    }

    private void resize(int mult){
        T[] newArray =(T[]) new Object[mult*this.size];
        for(int i = 0; i < this.size; i++)
            newArray[i] = this.array[i];
        this.capacity *= mult;
        this.array = newArray;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        if(size != 0) {
            sb.append(this.array[0]);
        }

        for(int i = 1; i < this.size; i++) {
            sb.append(", ");
            sb.append(this.array[i]);
        }

        sb.append("]");
        return sb.toString();
    }

    public boolean isEmpty(){
        return this.size==0;
    }

    public int length(){
        return this.size;
    }
}

import java.lang.reflect.Array;

