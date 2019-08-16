public interface Array<E extends Object & Comparable<? super E>> {

    int DEFAULT_CAPACITY = 16;

    void add(E value);

    boolean remove(E value);
    boolean removeByIndex(int index);

    int indexOf(E value);

    void showInfoByArray();

    void sortBubble();

    void sortSelect();

    void sortInsert();
    void sortQuick(int start, int end);
    E[] getArr();
    int length();

}
