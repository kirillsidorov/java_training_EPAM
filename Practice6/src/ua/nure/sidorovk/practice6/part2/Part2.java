package ua.nure.sidorovk.practice6.part2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Part2 {

    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        ArrayList<Integer> arrayList = new ArrayList<>();
        int n=10000;
        int k=4;

        for (int i=0; i<n;i++){
            linkedList.add(i);
            arrayList.add(i);
        }

        System.out.println("ArrayList#Index: " + removeByIndex(arrayList, k) + " ms");
        System.out.println("LinkedList#Index: " + removeByIndex(linkedList, k) + " ms");

        linkedList = new LinkedList<>();
        arrayList = new ArrayList<>();

        for (int i=0; i<n;i++){
            linkedList.add(i);
            arrayList.add(i);
        }

        System.out.println("ArrayList#Iterator: " + removeByIterator(arrayList, k) + " ms");
        System.out.println("LinkedList#Iterator: " + removeByIterator(linkedList, k) + " ms");
    }

    public static long removeByIndex(List<Integer> list, int k) {
        long time = System.currentTimeMillis();

        int index = 0;
        while (list.size() > 1) {
            index += k-1;
            if (index >= list.size()) {
                index %=list.size();
            }
            list.remove(index);
        }

        return System.currentTimeMillis()-time;
    }

    public static long removeByIterator(List<Integer> list, int k) {
        long time = System.currentTimeMillis();

        int step =0;
        while(list.size()>1){
            ListIterator iterator = list.listIterator();
            while (iterator.hasNext()&&list.size()>1) {
                    iterator.next();
                    if (step == k-1) {
                        iterator.remove();
                        step = 0;
                    } else {
                        step++;
                    }
            }
        }
        return System.currentTimeMillis()-time;
    }
}


