package DataStructures;

public class AVLTree<T extends Comparable<? super T>> {
    private static class AVLNode<T> {
        public T value;
        public AVLNode<T> leftSon;
        public AVLNode<T> rightSon;
        public int maxHeight;

        public AVLNode(T value) {
            this.value = value;
            this.maxHeight = 0;
            this.leftSon = null;
            this.rightSon = null;
        }
    }

    public AVLNode<T> root;

    public AVLTree(){
        this.root = null;
    }

    public void insert(T value){
        this.root = this.insert(value, this.root);
    }

    private AVLNode<T> insert(T value, AVLNode<T> node){
        if(node == null){
            return new AVLNode<>(value);
        }else{
            int resultCompare = value.compareTo(node.value);
            if(resultCompare < 0) {
                node.leftSon = this.insert(value, node.leftSon);
            }
            else if(resultCompare > 0) {
                node.rightSon = this.insert(value, node.rightSon);
            }
            else {}
            return balance(node);
        }
    }

    public void remove(T value){
        this.root = remove(value,this.root);
    }

    private AVLNode<T> remove(T value, AVLNode<T> node){
        if(node == null) {
            return null;
        }
        int compareResult = value.compareTo(node.value);
        if(compareResult < 0) {
            node.leftSon = remove(value,node.leftSon);
        }else if(compareResult > 0) {
            node.rightSon = remove(value,node.rightSon);
        }else if(node.leftSon != null && node.rightSon != null) {
            node.value = findMin(node.rightSon).value;
            node.rightSon = remove(node.value,node.rightSon);
        }else {
            node = (node.leftSon != null) ? node.leftSon : node.rightSon;
        }
        return balance(node);
    }

    private AVLNode<T> balance(AVLNode<T> node){
        if(node == null) return null;
        if(height(node.leftSon)-height(node.rightSon) > 1){
            if(height(node.leftSon.leftSon)>=height(node.leftSon.rightSon)) node=simpleRotationL(node);
            else node=doubleRotationLR(node);
        }else if(height(node.rightSon)-height(node.leftSon) > 1){
            if(height(node.rightSon.rightSon)>=height(node.rightSon.leftSon)) node=simpleRotationR(node);
            else node=doubleRotationRL(node);

        }
        node.maxHeight=Math.max(height(node.leftSon),height(node.rightSon))+1;
        return node;
    }

    private int height(AVLNode<T> node){
        if(node == null) return -1;
        else return node.maxHeight;
    }

    private AVLNode<T> simpleRotationL(AVLNode<T> node){
        AVLNode<T> aux = node.leftSon;
        node.leftSon = aux.rightSon;
        aux.rightSon = node;
        node.maxHeight= Math.max(height(node.leftSon),height(node.rightSon))+1;
        aux.maxHeight= Math.max(height(aux.leftSon),height(node))+1;
        return aux;
    }

    private AVLNode<T> simpleRotationR(AVLNode<T> node){
        AVLNode<T> aux = node.rightSon;
        node.rightSon = aux.leftSon;
        aux.leftSon = node;
        node.maxHeight= Math.max(height(node.leftSon),height(node.rightSon))+1;
        aux.maxHeight = Math.max(height(aux.rightSon),height(node))+1;
        return aux;
    }

    private AVLNode<T> doubleRotationLR(AVLNode<T> node){
        node.leftSon=simpleRotationR(node.leftSon);
        return simpleRotationL(node);
    }

    private AVLNode<T> doubleRotationRL(AVLNode<T> node){
        node.rightSon=simpleRotationL(node.rightSon);
        return simpleRotationR(node);
    }

    public T findMin(){
        return this.findMin(this.root).value;
    }

    private AVLNode<T> findMin(AVLNode<T> node){
        if(node.leftSon == null)
            return node;
        else
            return this.findMin(node.leftSon);
    }

    public T findMax(){
        return this.findMax(this.root).value;
    }

    private AVLNode<T> findMax(AVLNode<T> node){
        if(node.rightSon == null)
            return node;
        else
            return this.findMax(node.rightSon);
    }

    public boolean contains(T value){
        return contains(value, root);
    }

    private boolean contains(T value, AVLNode<T> node) {
        if(node == null) {
            return false;
        }

        int compareResult = value.compareTo(node.value);

        if(compareResult < 0) {
            return contains(value, node.leftSon);
        }else if(compareResult > 0) {
            return contains(value, node.rightSon);
        }else {
            return true;
        }
    }

    public T getElement(T value){
        return getElement(value, this.root);
    }

    private T getElement(T value, AVLNode<T> node){
        if(node == null){
            return null;
        }
        int compareResult = value.compareTo(node.value);
        if(compareResult < 0){
            return getElement(value, node.leftSon);
        }else if(compareResult > 0){
            return getElement(value, node.rightSon);
        }else{
            return node.value;
        }
    }

    public void makeEmpty(){
        this.root = null;
    }

    public boolean isEmpty(){
        return this.root == null;
    }

    public void printInOrder() {
        inOrder(this.root);
        System.out.println();
    }

    private void inOrder(AVLNode<T> node) {
        if(node != null) {
            inOrder(node.leftSon);
            System.out.print(node.value + " ");
            inOrder(node.rightSon);
        }
    }

    public void printPreOrder() {
        preOrder(this.root);
        System.out.println();
    }

    private void preOrder(AVLNode<T> node) {
        if(node != null) {
            System.out.print(node.value + " ");
            preOrder(node.leftSon);
            preOrder(node.rightSon);
        }
    }

    public void printPostOrder() {
        postOrder(this.root);
        System.out.println();
    }

    private void postOrder(AVLNode<T> node) {
        if(node !=null) {
            postOrder(node.leftSon);
            postOrder(node.rightSon);
            System.out.print(node.value + " ");
        }
    }

    public void printLevelOrder() {
        levelOrder(this.root);
        System.out.println();
    }

    private void levelOrder(AVLNode<T> node) {
        Queue<AVLNode<T>> cola = new QueueList<>();

        cola.enqueue(node);

        while(!cola.isEmpty()) {
            AVLNode<T> aux = cola.dequeue();
            System.out.print(aux.value + " ");

            if(aux.leftSon != null) {
                cola.enqueue(aux.leftSon);
            }
            if(aux.rightSon != null) {
                cola.enqueue(aux.rightSon);
            }
        }

    }
}