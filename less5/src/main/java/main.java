import java.lang.reflect.Array;
import java.util.ArrayList;

public class main {
    public static void main(String[] args) {
        System.out.println(pow(2,5));
        System.out.println(pow(0,5));
        System.out.println(pow(162,0));
        System.out.println(pow(2,-1));
        System.out.println(pow(2,-2));

        Bag bag = new Bag(7);
        Item[] items= new Item[]{new Item("ноутбук",5,60000),new Item("книга",3,3000),new Item("телефон",1,20000),
                new Item("внешний аккумулятор",2,3000)};
        bag.put(items);
    }
    static double pow(double value,int degree)
    {
        if (value==0)
            return 0;
        if(degree==0)
            return 1;
        if(degree==1)
            return value;
        if(degree==-1)
            return 1/value;
        if((degree)>1){
            value=value*pow(value,--degree);
            return value;
        }
        if(degree<-1){
            value=1/value*pow(value,++degree);
            return value;
        }
        return 0;
    }

}