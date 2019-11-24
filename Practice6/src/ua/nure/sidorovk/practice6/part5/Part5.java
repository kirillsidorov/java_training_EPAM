package ua.nure.sidorovk.practice6.part5;

public class Part5 {
    public static void main(String[] args) {
        //test1();
        //test2();
        test3();


    }

    private static void test2() {
        Tree<Integer> tree = new Tree<>();
        Integer[] integers = new Integer []{8, 3, 10, 1, 6, 14, 4, 7, 13};
        for (Integer i: integers) {
            tree.add(i);
        }

        tree.print();

    }

    private static void test3() {
        Tree<Integer> tree = new Tree<>();
        Integer[] integers = new Integer []{8, 3, 10, 1, 6, 14, 4, 7, 13};
        for (Integer i: integers) {
            tree.add(i);
        }
        tree.print();

        tree.remove(14);

        tree.print();

    }

    private static void test1() {
        Tree<Integer> tree = new Tree<>();
        System.out.println(tree.add(3));
        System.out.println(tree.add(3));

        System.out.println("~~~~~~~");
        tree.add(new Integer[]{1, 2, 5, 4, 6, 0});
        tree.print();

        System.out.println("~~~~~~~");
        System.out.println(tree.remove(5));
        System.out.println(tree.remove(5));

        System.out.println("~~~~~~~");
        tree.print();
    }
}
