package ua.nure.sidorovk.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayImpl implements Array{
    private Object [] array;
    private int size;
    private static final String EXCEPTION = "exception";

    public static void main(String[] args) {
        Class c = ArrayImpl.class;

        System.out.println(c.getInterfaces().length);
        System.out.println(c.getInterfaces()[0].getSimpleName());
        System.out.println(c.getSuperclass().getName());
        System.out.println(c.getInterfaces()[0].getInterfaces().length);
        System.out.println(c.getInterfaces()[0].getInterfaces()[0].getSimpleName());
        System.out.println(c.getInterfaces()[0].getInterfaces()[0].getInterfaces().length);
        System.out.println(c.getInterfaces()[0].getInterfaces()[0].getInterfaces()[0].getName());
    }

    @Override
    public void add(Object element) {
        Object [] arrayNew = new Object[size+1];
        if(this.size == 0) {
            array = arrayNew;
            array[array.length-1] = element;
        }else if(size > 0){
            System.arraycopy(this.array, 0, arrayNew, 0, size);
            arrayNew[size] = element;
        }
        array = arrayNew;
        size++;
    }

    @Override
    public void set(int index, Object element) {
        array[index] = element;
    }

    @Override
    public Object get(int index) {
        return array[index];
    }

    @Override
    public int indexOf(Object element) {
        int index = -1;
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                if(array[i].equals(element)){
                    index = i;
                    break;
                }
            }
        }
        return index;
    }

    @Override
    public void remove(int index) {
        try {
        Object[] newArr = new Object[this.size-1];
        int elem = 0;
        for(int i = 0; i < newArr.length; i++){
            if(index != i){
                newArr[i] = array[i+elem];
            }else{
                ++elem;
                newArr[i] = array[i+elem];
            }
        }
        this.array = newArr;
        --size;
        } catch(NoSuchElementException n){
            System.out.println(EXCEPTION);
        }
    }

    @Override
    public void clear() {
        this.array = null;
        this.size = 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("");
        if ( this.size() == 0){
            return "[]";
        }else if (size == 1){
            return "[" + array[0] + "]";
        } else {
            for (int i = 0; i < size; i++) {
                if (i == size-1) {
                    s.append(this.array[i]);
                    break;
                }
                s.append(this.array[i]).append(", ");
            }
        }
        return "[" + s.toString() + "]";
    }

    @Override
    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {
        private int position;
        private int lastRet = -1;

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
            Object[] elements = ArrayImpl.this.array;
            position = i + 1;
            lastRet = i;
            return elements[lastRet];
        }

        @Override
        public void remove(){
            if (lastRet < 0){
                throw new IllegalStateException(EXCEPTION);
            }
            try{
                ArrayImpl.this.remove(lastRet);
                position = lastRet;
                lastRet = -1;
            }catch (NoSuchElementException n){
                System.out.println(EXCEPTION);
            }
        }
    }
}
