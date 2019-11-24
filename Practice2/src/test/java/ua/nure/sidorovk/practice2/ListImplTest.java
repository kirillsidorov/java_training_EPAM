package ua.nure.sidorovk.practice2;

import org.junit.Assert;
import org.junit.Test;

class ListImplTest {

    @Test
    public void test1(){
        String abc = "[A, B, C]";
        ListImpl list = new ListImpl();
        list.addLast("A");
        list.addLast("B");
        list.addLast("C");

        Assert.assertEquals(list.toString(),abc);
        Assert.assertEquals(list.size(), 3);
        list.clear();

        Assert.assertEquals(list.toString(),"");
        Assert.assertEquals(list.size(), 0);

        list.addLast("A");
        list.addLast("B");
        list.addLast("C");

        Assert.assertEquals(list.toString(),abc);
        Assert.assertEquals(list.size(), 3);
    }

}