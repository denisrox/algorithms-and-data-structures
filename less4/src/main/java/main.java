public class main {
    public static void main(String[] args) {
        TwoSideLinkedList<Integer> linkedList = new TwoSideLinkedList<Integer>();
        linkedList.insertFirst(1);
        linkedList.insertFirst(0);
        linkedList.insertLast(2);
        linkedList.insertLast(3);
        linkedList.insertLast(4);

        for (Integer temp:linkedList) {
            System.out.println(temp);
        }


        linkedList.display();
        linkedList.remove(new Integer(3));
        linkedList.display();
        System.out.println("-------");
        for (Integer temp:linkedList) {
            System.out.println(temp);
        }

    }
}
