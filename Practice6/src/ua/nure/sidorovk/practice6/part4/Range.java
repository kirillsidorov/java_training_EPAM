package ua.nure.sidorovk.practice6.part4;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Range implements Iterable{

    private int [] ints;
    private int size;

    public Range(int n, int m){
        this.size = getSize(n,m);
        this.ints = setRange(n);
    }

    Range(int n, int m, boolean reverse){
        this.size = getSize(n,m);
        if(!reverse){
            this.ints = setRange(n);
        }else{
            this.ints = setRangeReverse(m);
        }
    }

    public int[] getRange(){
        return this.ints;
    }

    private int [] setRange(int n){
        int [] result = new int[size];
        int number = n;

        for(int i = 0; i< size; i++){
            result[i] = number;
            ++number;
        }
        return result;
    }

    private static int getSize(int n, int m) {
        int result;
        if (n < 0) {
            result = Math.abs(n) + 1 + m;
        } else {
            result = m - n + 1;
        }
        return result;
    }

    private int [] setRangeReverse(int m){
        int [] result = new int[size];
        int number = m;

        for(int i = 0; i< size; i++){
            result[i] = number;
            --number;
        }

        return result;
    }

    @Override
    public Iterator iterator() {
        return new MyIterator();
    }


    private class MyIterator implements Iterator<Object> {
        private int position;

        @Override
        public boolean hasNext() {
            return position != size;
        }

        @Override
        public Object next() {
            int i = position;
            if (i >= size) {
                throw new NoSuchElementException();
            }
            int[] elements = Range.this.ints;
            position = i + 1;
            return elements[i];
        }
        }
    }

