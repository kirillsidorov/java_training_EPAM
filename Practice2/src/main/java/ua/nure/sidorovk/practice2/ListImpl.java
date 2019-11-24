package ua.nure.sidorovk.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListImpl implements List{
    private Node first;
    private Node last;

    public static void main(String[] args) {
        Class c = ListImpl.class;
        System.out.println(c.getInterfaces().length);
        System.out.println(c.getInterfaces()[0].getSimpleName());
        System.out.println(c.getSuperclass().getName());
        System.out.println(c.getInterfaces()[0].getInterfaces().length);
        System.out.println(c.getInterfaces()[0].getInterfaces()[0].getSimpleName());
        System.out.println(c.getInterfaces()[0].getInterfaces()[0].getInterfaces().length);
        System.out.println(c.getInterfaces()[0].getInterfaces()[0].getInterfaces()[0].getName());
    }

    @Override
    public void addFirst(Object element) {
        Node curr = new Node();
        if(first == null){
            first = curr;
            first.item = element;
            this.last = first;
        }else{
            curr.next = first;
            first = curr;
            curr.item = element;
            curr.prev = null;
        }
    }

    @Override
    public void addLast(Object element) {
        Node curr = new Node();
        if(first == null){
            first = curr;
            first.item = element;
            last = first;
        }
        last.next = curr;
        curr.item = element;
        curr.prev = last;
        last = curr;
    }

    @Override
    public void removeFirst() {
        if(first != null){
            first = first.next;
            first.prev = null;
        }
    }

    @Override
    public void removeLast() {
        if(last != null) {
            Node prev = last.prev;
            last.item = null;
            last.prev = null;
            last = prev;
            if (prev == null) {
                first.next = null;
            } else {
                prev.next = null;
            }
        }
    }

    @Override
    public Object getFirst() {
        return first.getItem();
    }

    @Override
    public Object getLast() {
        return last.getItem();
    }

    @Override
    public Object search(Object element) {
        Node curr = first;
        while(curr != null){
            if(curr.item.equals(element)){
                return element;
            }
            curr = curr.next;
        }
        return null;
    }

    @Override
    public boolean remove(Object element) {
        if (element == null) {
            for (Node x = first; x != null; x = x.next) {
                if (x.item == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (Node x = first; x != null; x = x.next) {
                if (element.equals(x.item)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void clear() {
        first = null;
        last = null;
    }


    @Override
    public int size() {
        Node curr = this.first;
        int count = 0;
        while(curr != null){
            count++;
            curr = curr.next;
        }
        return count;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("");
        Node curr = first;

        if(first == null){
            return "[]";
        }

        while(curr != null){
            if(curr.next == null){
                s.append(curr.item);
                break;
            }
            if(curr.item != "") {
                s.append(curr.item).append(", ");
            }
            curr = curr.next;
        }

        return "[" + s.toString() + "]";
    }

    @Override
    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private void unlink(Node x) {
        x.item = "";
        if (x.prev == null) {
            this.first = x.next;
        } else {
            x.prev.next = x.next;
            x.prev = null;
        }

        if (x.next == null) {
            this.last = null;
        } else {
            x.next.prev = x.prev;
        }
    }

    private static class Node {
        private Object item;
        private Node next;
        private Node prev;


        Object getItem() {
            return item;
        }

    }


    private class IteratorImpl implements Iterator<Object> {
        private Node lastReturned;
        private Node next = first;

        @Override
        public boolean hasNext() {
            return next != null;
        }
        @Override
        public Object next() {

            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            lastReturned = next;
            next = next.next;
            return lastReturned.item;
        }

        @Override
        public void remove() {
            if (lastReturned == null){
                throw new IllegalStateException();
        }
            Node lastNext = lastReturned.next;
            unlink(lastReturned);
            if (next == lastReturned){
                next = lastNext;
            }
            lastReturned = null;

        }

    }

}
