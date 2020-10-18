package DataStructures;

public class DynamicArray {
    private int[] array;
    private int size;
    private int capacity;
    public static int number = 0;

    public DynamicArray(){
        this.array = new int[2];
        this.size = 0;
        this.capacity = 2;
        number++;
    }

    public int Size() {
        return this.size;
    }

    public int get(int index){
        if(index >= size || index < 0){
            System.out.println("índice no permitido");
            return Integer.MIN_VALUE;
        };
        return this.array[index];
    }

    public void insert(int index, int value){
        if(index >= size || index < 0)
            System.out.println("índice no permitido");
        if(this.size == this.capacity)
            this.resize(10);
        for(int i = this.size; i > index; i--)
            this.array[i] = this.array[i-1];
        this.array[index] = value;
        this.size++;
    }

    public void insert(int value){ //PUSHBACK
        if(this.size == this.capacity) this.resize(2);
        this.array[this.size++] = value;
    }

    public void set(int index, int value){
        if(index >= size || index < 0)
            System.out.println("índice no permitido");
        this.array[index] = value;
    }

    public void delete(int index){
        if(index >= size || index < 0){
            System.out.println("índice no permitido");
            return;
        }
        int[] newArray = new int[this.capacity];
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
        this.array[--this.size] = 0;
    }

    private void resize(int mult){
        int[] newArray = new int[mult*this.size];
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
}