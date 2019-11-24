package ua.nure.sidorovk.practice2;

import java.util.Iterator;
public class Test {
//Array
    public static void test1() {
        ArrayImpl array = new ArrayImpl();
        array.add("A");
        array.add("B");
        array.add("C");

        array.remove(2);
        System.out.println(array);

        array.remove(0);
        System.out.println(array);

        array.remove(0);
        System.out.println(array);
    }
    public static void test2(){
        ArrayImpl array = new ArrayImpl();
        array.add("A");
        array.add("B");
        array.add("C");

        for (Object e : array) {
            System.out.print(e);
        }
    }
    public static  void test3(){
        ArrayImpl array = new ArrayImpl();
        array.add("A");
        array.add("C");

        //(Object)array.iterator().


    }
//list
    public static void test4(){
        boolean check = true;
        String abc = "[A, B, C]";
        ListImpl list = new ListImpl();
        list.addLast("A");
        list.addLast("B");
        list.addLast("C");

        check = list.toString().equals(abc);
        System.out.println(list.toString());
        System.out. println(list.size());

        list.clear();
        System.out.println(list);
        System.out.println(list.size());

        list.addLast("A");
        list.addLast("B");
        list.addLast("C");

        System.out.println(list);
        System.out.println(list.size());

        if(check){
            System.out.println("Ok");
        }
    }
    private static  void test5(){
        ListImpl list = new ListImpl();
        list.addFirst("A");
        list.addLast("B");
        list.addFirst("C");

        System.out.println(list);
        System.out.print(list.getFirst());
        System.out.print(list.getLast());
    }
    private static  void test6(){
        ListImpl list = new ListImpl();
        list.addLast("A");
        list.addLast("B");
        list.addLast("C");
        for (Object s : list) {
            System.out.print(s);
        }
    }
    private static  void test71(){
        ListImpl list = new ListImpl();
        list.addLast("A");
        list.addLast("B");
        list.addLast("C");
        System.out.println(list);
        list.remove("B");
        System.out.println(list);
    }
    private static  void test7(){
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
    private static  void test8(){
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
    private static  void test9(){
        ListImpl list = new ListImpl();
        list.addFirst(3);
        list.addFirst(2);
        list.addFirst(1);

        System.out.println(list);//[1, 2, 3]
        System.out.println(list.search(1));//[]
        System.out.println(list.search(2));//[]
        System.out.println(list.getLast());//[]
    }
    private static  void test10(){
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
    private static  void test11(){

        QueueImpl queue = new QueueImpl();
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");

        System.out.println(queue);
        System.out.println(queue.size());

        queue.clear();
        System.out.println(queue);
        System.out.println(queue.size());

        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");

        System.out.println(queue);
        System.out.println(queue.size());
    }
    private static  void test12(){

        //QueueImpl queue = new QueueImpl();
        Queu queue = new Queu();
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        System.out.println(queue);

        System.out.print(queue.dequeue());
        System.out.print(queue.dequeue());
        System.out.print(queue.dequeue());
        System.out.println();
        System.out.print(queue);
    }
    private static void test13() {
       /*
        -- expected --
        A
        B
        C
        [A, B]
        A
        [B]
        B
        []
        -- actually --
        A
        B
        C
        [A, B]
        A
        [B]
        ?
        [B]
        */
    QueueImpl queue = new QueueImpl();
    //Queu queue = new Queu();
    queue.enqueue("A");
    queue.enqueue("B");
    queue.enqueue("C");
    Iterator it = queue.iterator();
    System.out.println(it.next());//print A
    System.out.println(it.next());//print B
    System.out.println(it.next());//print C
    it.remove();//remove C
    System.out.println(queue);//print [A, B]

    it = queue.iterator();
    System.out.println(it.next());//A
    it.remove();//remove A
    System.out.println(queue);//[B]

    it = queue.iterator();
    System.out.println(it.next());//B

    it.remove();//remove B

    System.out.println(queue);//[]
}
    private  static  void test14(){
    QueueImpl queue = new QueueImpl();
    queue.enqueue("A");
    queue.enqueue("B");
    queue.enqueue("C");


    Iterator it = queue.iterator();
    System.out.println(it.next());//A
    it.remove();//remove A
    it.remove();
}
    private  static  void test15(){
        QueueImpl queue = new QueueImpl();
        queue.enqueue("A");
        queue.enqueue("B");
        System.out.println(queue.top());
 }
 //Stack
    private static void test16(){
        StackImpl stack = new StackImpl();
        stack.push("A");
        stack.push("B");
        stack.push("C");

        System.out.println(stack);
        System.out.println(stack.size());

        stack.clear();
        System.out.println(stack);
        System.out.println(stack.size());

        stack.push("A");
        stack.push("B");
        stack.push("C");
        System.out.println(stack);
        System.out.println(stack.size());

    }
    private static void test17(){
        StackImpl stack = new StackImpl();

        stack.push("A");
        stack.push("B");
        stack.push("C");
        System.out.println(stack);
        System.out.println(stack.size());

        Iterator it = stack.iterator();
        System.out.println(it.next());
        System.out.println(it.next());
        System.out.println(it.next());
        it.remove();
        System.out.println(stack);
        System.out.println(stack.size());

        it = stack.iterator();
        System.out.println(it.next());
        it.remove();
        System.out.println(stack);
        System.out.println(stack.size());

        it = stack.iterator();
        System.out.println(it.next());
        it.remove();
        System.out.println(stack);
        System.out.println(stack.size());

    }

    public static void main(String[] args) {
        test1();
        test2();
        test3();
    }
}
