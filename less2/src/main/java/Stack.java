public class Stack<E> {
    private static final int DEFAULT_CAPACITY = 16;
    E[] DateBase;
    int size;

    public Stack(){
        this(DEFAULT_CAPACITY);
    }
    public Stack(int MaxCapacity){
        DateBase=(E[])new Object[MaxCapacity];
    }
    public boolean push(E value) {
        if (isFull())
            return false;

        DateBase[size++] = value;
        return true;
    }
    public E pop() {
        return isEmpty() ? null : DateBase[--size];
    }

    public E peek() {
        return isEmpty() ? null : DateBase[size - 1];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == DateBase.length;
    }
}
