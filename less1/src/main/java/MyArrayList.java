import java.util.Arrays;
import java.util.Objects;

public class MyArrayList<E extends Object & Comparable<? super E>>  implements Array<E> {

    private int size;
    private E[] myArray;

    public MyArrayList() {
        this(DEFAULT_CAPACITY);
    }
    public MyArrayList(int maxCapacity) {
        myArray= (E[]) new Object[maxCapacity];
    }

    private void CheckOverflow() {
        if(size==myArray.length)
            myArray= Arrays.copyOf(myArray,size*2);
    }


    @Override
    public void add(E value) {
        CheckOverflow();
        myArray[size++]=value;
    }

    @Override
    public boolean remove(E value) {
        int index = indexOf(value);
        if (index != -1) {
            return removeByIndex(index);
        }
        return false;
    }

    @Override
    public boolean removeByIndex(int index) {

        if (index >= 0 && index < size) {

            E result = myArray[index];

            for (int i = index; i < size - 1; i++) {
                myArray[i] = myArray[i + 1];
            }

            myArray[--size] = null;
            return true;

        }
        return false;

    }


    @Override
    public int indexOf(E value) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(value, myArray[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void showInfoByArray() {
        for (int i = 0; i < size; i++) {
            System.out.print(myArray[i]+" ");
        }
        System.out.println("");
    }

    @Override
    public void sortBubble() {
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - 1 - i; j++) {
                if (myArray[j].compareTo(myArray[j + 1]) > 0) {
                    swap(j, j + 1);
                }
            }
        }
    }

    @Override
    public void sortSelect() {
        for (int i = 0; i < size - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < size; j++) {
                if (myArray[j].compareTo(myArray[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            swap(minIndex, i);
        }

    }

    @Override
    public void sortInsert() {
        for (int i = 1; i < size; i++) {
            E temp = myArray[i];
            int in = i;
            while (in > 0 && myArray[in - 1].compareTo(temp) > 0) {
                myArray[in] = myArray[in - 1];
                in--;
            }
            myArray[in] = temp;
        }
    }

    @Override
    public void sortQuick(int start, int end) {
        if(start==end) return;
        int i = start, j = end;
        int cur = i - (i - j) / 2;
        E curr=myArray[cur];
        while (i <= j)
        {
            while ((myArray[i].compareTo(curr) < 0)) {
                i++;
            }

            while ((myArray[j].compareTo(curr)>0)) {
                j--;
            }
            if (i <= j) {
                swap(i++,j--);
            }
        }
        if(start<j)
            sortQuick(start, j);
        if(end>i)
            sortQuick(i, end);
    }


    private void swap(int index1, int index2) {
        E temp = myArray[index1];
        myArray[index1] = myArray[index2];
        myArray[index2] = temp;
    }
    public int length()
    {
        return size;
    }
    public E[] getArr(){
        return Arrays.copyOf(myArray,size);
    }
}
