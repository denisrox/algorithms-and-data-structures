public class main {
    public static void main(String[] args) {
        String text = new String("Hello World!");
        Stack<Character> textEnd = new Stack<Character>();
        for(int i = 0;i<text.length();i++)
            textEnd.push(text.charAt(i));
        while (!textEnd.isEmpty())
            System.out.print(textEnd.pop());
        System.out.println("=========а теперь 2 сторонняя очередь=========");
        Deque<Integer>deque = new Deque<Integer>();
        deque.insertLeft(2);
        //deque.insertRight(4);
        deque.insertLeft(1);
        deque.insertLeft(0);

        //deque.insertRight(5);
        System.out.println(deque.RemoveLeft());
        System.out.println(deque.RemoveLeft());
        System.out.println(deque.RemoveLeft());

        System.out.println("proverka");
    }
}
