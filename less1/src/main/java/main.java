import java.util.Random;

public class main {
    public static void main(String[] args) {
        Array<Integer> arr1=new MyArrayList<>();
        Array<Integer> arr2=new MyArrayList<>();
        Array<Integer> arr3=new MyArrayList<>();
        Array<Integer> arr4=new MyArrayList<>();
        for(int i = 0;i<40000;i++)
        {
            Random random = new Random();
            int rnd= random.nextInt(100000);
            arr1.add(rnd);
            arr2.add(rnd);
            arr3.add(rnd);
            arr4.add(rnd);
        }
        long startTime = System.currentTimeMillis();
        arr1.sortBubble();
        long stopTime = System.currentTimeMillis();
        System.out.println("Время пузырьковой сортировки: "+(stopTime - startTime));

        startTime = System.currentTimeMillis();
        arr2.sortInsert();
        stopTime = System.currentTimeMillis();
        System.out.println("Время вставочной сортировки: "+(stopTime - startTime));

        startTime = System.currentTimeMillis();
        arr3.sortSelect();
        stopTime = System.currentTimeMillis();
        System.out.println("Время выборочной сортировки: "+(stopTime - startTime));

        startTime = System.currentTimeMillis();
        arr4.sortQuick(0, arr1.length()-1);
        stopTime = System.currentTimeMillis();
        System.out.println("Время быстрой сортировки: "+(stopTime - startTime));



    }
}
