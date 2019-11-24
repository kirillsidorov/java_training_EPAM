import org.junit.Assert;
import org.junit.Test;
import ua.nure.sidorovk.practice2.ListImpl;

import java.util.Iterator;


public class ListImplTest2 {

    @Test
    public void test4(){
        String abc = "[A, B, C]";
        ListImpl list = new ListImpl();
        list.addLast("A");
        list.addLast("B");
        list.addLast("C");

        Assert.assertEquals(list.toString(),abc);
        Assert.assertEquals(list.size(), 3);
        list.clear();

        Assert.assertEquals(list.toString(),"[]");
        Assert.assertEquals(list.size(), 0);

        list.addLast("A");
        list.addLast("B");
        list.addLast("C");

        Assert.assertEquals(list.toString(),abc);
        Assert.assertEquals(list.size(), 3);
    }
    @Test
    public   void test5(){
        ListImpl list = new ListImpl();
        list.addFirst("A");
        list.addLast("B");
        list.addFirst("C");

        Assert.assertEquals(list.toString(), "[C, A, B]");
        Assert.assertEquals(list.getFirst(), "C");
        Assert.assertEquals(list.getLast(), "B");

    }
    @Test
    public   void test6(){
        StringBuilder sb = new StringBuilder();
        ListImpl list = new ListImpl();
        list.addLast("A");
        list.addLast("B");
        list.addLast("C");
        for (Object s : list) {
            sb.append(s);
        }
        Assert.assertEquals(sb.toString(), "ABC");
    }

    @Test
    public   void test71(){
        ListImpl list = new ListImpl();
        list.addLast("A");
        list.addLast("B");
        list.addLast("C");
        System.out.println(list);
        list.remove("B");
        System.out.println(list);
    }
    @Test
    public   void test7(){
        ListImpl list = new ListImpl();
        list.addLast("A");
        list.addLast("B");
        list.addLast("C");

        Iterator it = list.iterator();
        System.out.println(it.next());
        it.remove();
        System.out.println(list);
        System.out.println(it.next());
        it.remove();
        System.out.println(list);
        System.out.println(it.next());
        it.remove();
        System.out.println(list);
    }
    @Test
    public   void test8(){
        ListImpl list = new ListImpl();
        list.addFirst("B");
        list.addLast("C");
        list.addFirst("A");

        System.out.println(list);//a b c
        list.removeLast();//del c
        System.out.println(list); //a b
        list.removeLast();//del b
        System.out.println(list);//a
        list.removeLast();
        System.out.println(list);//[]
    }
    @Test
    public   void test9(){
        ListImpl list = new ListImpl();
        list.addFirst(3);
        list.addFirst(2);
        list.addFirst(1);

        System.out.println(list);//[1, 2, 3]
        System.out.println(list.search(1));//[]
        System.out.println(list.search(2));//[]
        System.out.println(list.getLast());//[]
    }
    @Test
    public   void test10(){
        ListImpl list = new ListImpl();
        list.addLast(1);
        list.addLast(2);
        list.addLast(1);

        list.remove(1);
        System.out.println(list);//[2, 1]

        list.remove(2);
        System.out.println(list);//[1]

        Iterator it = list.iterator();
        System.out.println(it.next());//1

    }

//queue
}