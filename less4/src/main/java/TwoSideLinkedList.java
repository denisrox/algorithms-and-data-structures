import java.util.Iterator;

public class TwoSideLinkedList<E> implements Iterable<E> {

    private Node<E> lastElement;
    private Node<E> firstElement;
    protected int size;

    public void insertFirst(E value) {
        Node<E> node=new Node<E>(value);
        if(isEmpty()){
            firstElement=node;
            lastElement = firstElement;
        }else{
            firstElement.previous=node;
            node.next=firstElement;
            firstElement=node;
        }
        size++;
    }


    public void insertLast(E value) {
        Node<E> node=new Node<E>(value);
        if(isEmpty()){
            lastElement=node;
            firstElement=lastElement;
        }else{
            lastElement.next=node;
            node.previous=lastElement;
            lastElement=node;
        }
        size++;
    }


    public E removeFirst() {
        if (isEmpty())
            return null;

        E value = firstElement.value;
        if(size!=1)
            firstElement = firstElement.next;
        else {
            lastElement=null;
            firstElement = null;
        }
        //lastElement=null;    потом обработать вот эту хрень                                  !!!!
        size--;
        return value;
    }

    public E removeLast() {
        if (isEmpty())
            return null;
        E value = lastElement.value;
        if(size!=1)
            lastElement = firstElement.previous;
        else {
            lastElement=null;
            firstElement = null;
        }
        //lastElement=null;    потом обработать вот эту хрень                                   !!!!
        size--;
        return value;
    }


    public E remove(E value) {
        if (isEmpty())
            return null;

        Node<E> current = contains(value);
        current.previous.next=current.next;
        current.next.previous=current.previous;
        size--;
        return current.value;
    }




    public Node<E> contains(E value) {
        Node<E> current = firstElement;
        while (current != null) {
            if (current.value.equals(value)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    public void display() {
        System.out.println("----------");
        Node<E> current = firstElement;
        while (current != null) {
            System.out.println(current.value);
            current = current.next;
        }
        System.out.println("----------");
    }


    private boolean isEmpty() {
        return size==0;
    }


    public class Node<T> {
        public final T value;
        public Node<T> next;
        public Node<T>previous;

        public Node(T value) {
            this.value = value;
        }
    }
    @Override
    public Iterator<E> iterator(){
        Iterator<E> it=new Iterator<E>()
        {
            private int IndexIterator=0;
            private Node<E> TempElement=firstElement;
            @Override
            public boolean hasNext() {
                return (IndexIterator++)!=size;
            }

            @Override
            public E next() {
                if(TempElement.next!=null){
                    TempElement=TempElement.next;
                    return TempElement.previous.value;}
                else{
                    return TempElement.value;
                }

            }

            @Override
            public void remove() {

            }

            //@Override
            //public void forEachRemaining(Consumer<? super E> action) {

            //}
        };
        return it;
    }
}