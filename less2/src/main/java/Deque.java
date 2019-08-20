public class Deque<E> {
    private static final int DEFAULT_CAPACITY = 16;
    int size;
    int head=-1;
    int tail;
    public E[] DateBase;

    public Deque(){
        this(DEFAULT_CAPACITY);
    }

    public Deque(int MaxCapacity){
        DateBase=(E[])new Object[MaxCapacity];
        size=MaxCapacity;
    }

    public void insertRight(E value){
        if (--head < 0)
            head = size - 1;
        DateBase[head] = value;
    }

    public void insertLeft(E value){
        if (++tail == size)
            tail = 0;
        DateBase[tail] = value;
    }

    public E RemoveRight(){
        if (++head == size)
            head = 0;
        return DateBase[head];
    }
    public E RemoveLeft(){
        E ret = DateBase[tail];
        if (--tail < 0)
            tail = size - 1;
        return ret;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == DateBase.length;
    }
}
