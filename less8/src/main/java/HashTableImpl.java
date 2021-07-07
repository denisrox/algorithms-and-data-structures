import java.util.LinkedList;

public class HashTableImpl<K, V> {

    private static class Node<K, V> {
        private K key;
        private V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }
    private class indexOfValue{
        public int indexOfHash;
        public int indexByLinkedArray;
        indexOfValue(int indexOfHash, int indexByLinkedArray){
            this.indexOfHash=indexOfHash;
            this.indexByLinkedArray=indexByLinkedArray;
        }
    }

    private LinkedList<Node<K, V>>[] data;// = new LinkedList<>();
    //private Node<K, V>[] data;
    private int size;
    private int maxSize;


    public HashTableImpl(int maxSize) {
        this.maxSize = maxSize;
        //this.data = new Node[maxSize * 2];
        this.data=new LinkedList[maxSize];
    }

    private int hashFunc(K key) {
        return key.hashCode() % data.length;
    }


    public boolean put(K key, V value) {
        if (isFull()) {
            return false;
        }

        int index = hashFunc(key);
        if(data[index]!=null){
            for (Node<K,V> node:data[index]) {
                if (node.key==key)
                    return false;
            }
        }
        if(data[index]==null)
            data[index]=new LinkedList<>();
        data[index].add( new Node<>(key, value));
        size++;
        return true;
    }


    public V get(K key) {
        indexOfValue index = indexOf(key);
        return index.indexOfHash!= -1 ? data[index.indexOfHash].get(index.indexByLinkedArray).getValue() : null;
    }


    public boolean contains(K key) {
        return get(key) != null;
    }

    public V remove(K key) {
        indexOfValue index = indexOf(key);
        if (index.indexOfHash == -1) {
            return null;
        }
        Node<K, V> node = data[index.indexOfHash].get(index.indexByLinkedArray);
        data[index.indexOfHash].remove(index.indexByLinkedArray);
        size--;
        return node.getValue();
    }

    private indexOfValue indexOf(K key) {
        int index = hashFunc(key);
        for (int i = 0;i<data.length;i++) {
            if (data[index].get(i).key==key)
                return new indexOfValue(index,i);
        }
        return new indexOfValue(-1,-1);
    }

    protected int getStep(K key) {
        return 1;
    }

    public void display() {
        System.out.println("-----------");
        for (int i = 0; i < data.length; i++) {
            System.out.printf("%d = [%s]", i, data[i]);
            System.out.println();
        }
        System.out.println("-----------");
    }

    public int size() {
        return size;
    }


    public boolean isEmpty() {
        return size != 0;
    }


    public boolean isFull() {
        return size == maxSize;
    }
}