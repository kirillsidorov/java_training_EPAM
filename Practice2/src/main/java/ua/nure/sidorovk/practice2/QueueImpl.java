package ua.nure.sidorovk.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class QueueImpl implements Queue{
    private int size;
    private Node first;
    private Node last;

    private static class Node {
        private Object item;
        private Node next;
    }


    public static void main(String[] args) {
        Class c = QueueImpl.class;
        System.out.println(c.getInterfaces().length);
        System.out.println(c.getInterfaces()[0].getSimpleName());
        System.out.println(c.getInterfaces()[0].getInterfaces().length);
        System.out.println(c.getInterfaces()[0].getInterfaces()[0].getSimpleName());
        System.out.println(c.getInterfaces()[0].getInterfaces()[0].getInterfaces().length);
        System.out.println(c.getInterfaces()[0].getInterfaces()[0].getInterfaces()[0].getName());
    }

    private boolean isEmpty() {
        return first == null;
    }

    @Override
    public void clear() {
        size = 0;
        first = null;
        last = null;
    }

    public int size() {
        return size;
    }

    public void enqueue(Object item) {
        Node oldest = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) {
            first = last;
        } else {
            oldest.next = last;
        }
        size++;
    }

    public Object dequeue() {
        if (isEmpty()){
            throw new NoSuchElementException("Queue underflow");
        }
        Object item = first.item;
        first = first.next;
        size--;
        if (isEmpty()) {
            last = null;
        }
        return item;
    }

    @Override
    public Object top() {
        return first.item;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        Node curr = first;
        if (first == null) {
            return "[]";
        }
        while (curr != null) {
            if (curr.next == null) {
                s.append(curr.item);
                break;
            }
            if (curr.item != "") {
                s.append(curr.item).append(", ");
            }
            curr = curr.next;
        }

        return "[" + s.toString() + "]";
    }

    public Iterator<Object> iterator()  {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Object> {
        private Node cursor;
        private Node current = first;

        @Override
        public boolean hasNext()  {
            return current != null;
        }

        @Override
        public void remove(){
            Node n = first;
            if (cursor == null){
                throw new IllegalStateException();
            }
            if (cursor == first){
                first = first.next;
                cursor = null;
            }else {
                do {
                    if (cursor == n.next) {
                        if (cursor == last) {
                            last = n;
                        }
                        n.next = n.next.next;
                        cursor = null;
                        break;
                    }
                    n = n.next;
                } while (cursor != n);
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
