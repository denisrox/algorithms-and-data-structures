import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class test {
    Array<Integer> arr;

    @Before
    public void InitArr(){
        arr=new MyArrayList<>();
        arr.add(1);
        arr.add(7);
        arr.add(3);
        arr.add(5);
        arr.add(2);
        arr.add(11);

    }


    @Test
    public void remove(){
        arr.remove(2);
        Assert.assertArrayEquals(new Integer[]{1,7,3,5,11}, arr.getArr());
    }
    @Test
    public void add(){
        arr.add(8);
        Assert.assertArrayEquals(new Integer[]{1,7,3,5,2,11,8}, arr.getArr());
    }
    @Test
    public void sortQ(){
        arr.sortQuick(0,arr.length()-1);
        Assert.assertArrayEquals(new Integer[]{1,2,3,5,7,11}, arr.getArr());
    }
    @Test
    public void sortB(){
        arr.sortBubble();
        Assert.assertArrayEquals(new Integer[]{1,2,3,5,7,11}, arr.getArr());
    }
    @Test
    public void sortI(){
        arr.sortInsert();
        Assert.assertArrayEquals(new Integer[]{1,2,3,5,7,11}, arr.getArr());
    }
    @Test
    public void sortS(){
        arr.sortSelect();
        Assert.assertArrayEquals(new Integer[]{1,2,3,5,7,11}, arr.getArr());
    }
}
