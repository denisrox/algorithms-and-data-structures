import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class test {
    Stack<Integer> stack=new Stack<Integer>(16);
    Queue<Integer> queue=new Queue<Integer>(16);;
    Deque<Integer> deque=new Deque<Integer>(16);

    @Before
    public void InitArr(){
    }


    @Test
    public void StackAdd(){
        for(Integer i = 0;i<5;i++)
            stack.push(i);
        for(Integer i = 4;i>0;i--)
            Assert.assertEquals(i, stack.pop());
    }
    @Test
    public void QueueAdd(){
        for(Integer i = 0;i<5;i++)
            queue.insert(i);
        for(Integer i = 0;i<5;i++)
            Assert.assertEquals(i, queue.remove());
    }

    @Test
    public void DequeeAdd(){
        for(Integer i = 0;i<5;i++)
            deque.insertLeft(i);
        for(Integer i=5;i<10;i++)
            deque.insertRight(i);
        for(Integer i = 4;i>0;i--)
            Assert.assertEquals(i, deque.RemoveLeft());
        for(Integer i = 8;i>5;i--)
            Assert.assertEquals(i, deque.RemoveRight());

    }
}
