public class Queue<E> {
    private static final int DEFAULT_TAIL = -1;
    private static final int DEFAULT_HEAD = 0;

    protected final E[] dataBase;
    protected int size;

    private int tail;
    private int head;

    public Queue(int maxCapacity) {
        this.dataBase = (E[]) new Object[maxCapacity];
        this.head = DEFAULT_HEAD;
        this.tail = DEFAULT_TAIL;
    }

    public boolean insert(E value) {
        if (isFull())
            return false;

        if (tail == dataBase.length - 1) {
            tail = DEFAULT_TAIL;
        }
        dataBase[++tail] = value;
        size++;
        return true;
    }
    public E remove() {
        if (isEmpty()) {
            return null;
        }
        if (head == dataBase.length) {
            head = DEFAULT_HEAD;
        }

        E value = dataBase[head++];
        size--;
        return value;
    }

    public E peek() {
        return dataBase[head];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == dataBase.length;
    }
}
