package ua.nure.sidorovk.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class StackImpl implements Stack {
    private Node first;

    private static class Node {
        private Object item;
        private Node next;
    }

    StackImpl() {
        this.first = null;
    }

    public static void main(String[] args) {
        Class c = StackImpl.class;
        System.out.println(c.getInterfaces().length);
        System.out.println(c.getInterfaces()[0].getSimpleName());
        System.out.println(c.getInterfaces()[0].getInterfaces().length);
        System.out.println(c.getInterfaces()[0].getInterfaces()[0].getSimpleName());
        System.out.println(c.getInterfaces()[0].getInterfaces()[0].getInterfaces().length);
        System.out.println(c.getInterfaces()[0].getInterfaces()[0].getInterfaces()[0].getName());
    }


    @Override
    public void push(Object element) {
        Node oldFirst = first;
        first = new Node();
        first.item = element;
        first.next = oldFirst;
    }

    @Override
    public Object pop() {
        if (isEmpty()){
            throw new NoSuchElementException("Stack underflow");
        }
        Object item = first.item;
        first = first.next;
        return item;
    }

    private boolean isEmpty() {
        return first == null;
    }

    @Override
    public Object top() {
        return first.item;
    }

    @Override
    public void clear() {
        this.first = null;
    }

    @Override
    public int size() {
        Node tmp;
        int count = 0;
        if(first != null){
            tmp = first;
            count++;
            while (tmp.next != null){
                tmp = tmp.next;
                count++;
            }
        }
        return count;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        Node curr = first;
        while(curr != null){
            if(curr.next == null){
                s.append(curr.item);
                break;
            }
            if(curr.item != "") {
                s.append(curr.item).append(" ,");
            }
            curr = curr.next;
        }
        return "[" + s.reverse().toString() + "]";
    }

    @Override
    public Iterator<Object> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Object> {
        private Node cursor;
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public void remove() {
            Node node = first;
            if (cursor == null){
                throw new IllegalStateException();
            }
            if (cursor == first){
                first = first.next;
                cursor = null;
            }else {
                do {
                    if (cursor == node.next) {
                        node.next = node.next.next;
                        cursor = null;
                        break;
                    }
                    node = node.next;
                } while (cursor != node);
            }
        }

        @Override
        public Object next() {
            if (!hasNext()){
                throw new NoSuchElementException();
            }
            Object item = current.item;
            cursor = current;
            current = current.next;
            return item;
        }
    }


}
