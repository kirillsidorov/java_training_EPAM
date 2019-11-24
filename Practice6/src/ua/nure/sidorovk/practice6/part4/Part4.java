package ua.nure.sidorovk.practice6.part4;

import java.util.Arrays;
import java.util.Iterator;

public class Part4 {
    public static void main(String[] args) {


        Range range1 = new Range(-1,5, false);
        System.out.println(Arrays.toString(range1.getRange()));

        Iterator it = range1.iterator();

        it.next();
        System.out.println(it.next());
        System.out.println(it.next());

        it = range1.iterator();

        it.next();
        System.out.println(it.next());
        System.out.println(it.next());



        }
    }

