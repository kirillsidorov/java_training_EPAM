package ua.nure.sidorovk.practice6.part2;

import java.util.ArrayList;
import java.util.LinkedList;

class Part2Test {

    public static void main(String[] args) {
        //test1();
        LinkedList<Integer> linkedList = new LinkedList<>();
        ArrayList<Integer> arrayList = new ArrayList<>();
        int n=17;
        int k=1;

        for (int i=0; i<n;i++){
            linkedList.add(i);
            arrayList.add(i);
        }

        Part2.removeByIterator(linkedList,k);
        Part2.removeByIterator(arrayList,k);

        System.out.println(arrayList.get(0));
        System.out.println(linkedList.get(0));


    }

    private static void test1() {
        LinkedList<Integer> linkedList = new LinkedList<>();
        ArrayList<Integer> arrayList = new ArrayList<>();
        int n=7;
        int k=3;

        for (int i=0; i<n;i++){
            linkedList.add(i);
            arrayList.add(i);
        }

        Part2.removeByIterator(linkedList,k);
        Part2.removeByIterator(arrayList,k);

        System.out.println(arrayList.get(0));
        System.out.println(linkedList.get(0));

        for (int i=0; i<n;i++){
            linkedList.add(i);
            arrayList.add(i);
        }

        Part2.removeByIndex(linkedList,k);
        Part2.removeByIndex(arrayList,k);

        System.out.println(arrayList.get(0));
        System.out.println(linkedList.get(0));
    }

}