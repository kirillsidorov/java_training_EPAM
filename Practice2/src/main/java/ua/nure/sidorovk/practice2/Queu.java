package ua.nure.sidorovk.practice2;
import java.util.Iterator;
import java.util.NoSuchElementException;
public class Queu implements Queue{
    private int n;
    private Node first;    // beginning of queue
    private Node last;     // end of queue

    private class Node {
        private Object item;
        private Node next;
    }


    public static void main(String[] args) {
        QueueImpl list = new QueueImpl();
        Class c = list.getClass();

// must be 1
        System.out.println(c.getInterfaces().length);
// must be Queue
        System.out.println(c.getInterfaces()[0].getSimpleName());
// must be 1
        System.out.println(c.getInterfaces()[0].getInterfaces().length);
// must be Container
        System.out.println(c.getInterfaces()[0].getInterfaces()[0].getSimpleName());
// must be 1
        System.out.println(c.getInterfaces()[0].getInterfaces()[0].getInterfaces().length);
// must be java.lang.Iterable
        System.out.println(c.getInterfaces()[0].getInterfaces()[0].getInterfaces()[0].getName());
    }

    public Queu() {
        first = null;
        last  = null;
        n = 0;
        assert check();
    }

    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public void clear() {
        n = 0;
        first = null;
        last = null;
    }

    public int size() {
        return n;
    }

    /*public Object peek() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        return first.item;
    }*/

    public void enqueue(Object item) {
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) {
            first = last;
        } else {
            oldlast.next = last;
        }
        n++;
        assert check();
    }

    public Object dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        Object item = first.item;
        first = first.next;
        n--;
        if (isEmpty()) {
            last = null;   // to avoid loitering
        }
        assert check();
        return item;
    }

    @Override
    public Object top() {
        return last.item;
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

    // check internal invariants
    private boolean check() {
        if (n < 0) {
            return false;
        }
        else if (n == 0) {
            if (first != null) return false;
            if (last  != null) return false;
        }
        else if (n == 1) {
            if (first == null || last == null) return false;
            if (first != last)                 return false;
            if (first.next != null)            return false;
        }
        else {
            if (first == null || last == null) return false;
            if (first == last)      return false;
            if (first.next == null) return false;
            if (last.next  != null) return false;

            // check internal consistency of instance variable n
            int numberOfNodes = 0;
            for (Node x = first; x != null && numberOfNodes <= n; x = x.next) {
                numberOfNodes++;
            }
            if (numberOfNodes != n) return false;

            // check internal consistency of instance variable last
            Node lastNode = first;
            while (lastNode.next != null) {
                lastNode = lastNode.next;
            }
            if (last != lastNode) return false;
        }

        return true;
    }

    public Iterator<Object> iterator()  {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Object> {
        private Node cursor;
        private Node current = first;

        public boolean hasNext()  { return current != null;                     }

        public void remove(){
            Node n = first;
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
                        break;
                    }
                    n = n.next;
                } while (cursor != n);
            }

        }

        public Object next() {
            if (!hasNext()) throw new NoSuchElementException();
            Object item = current.item;
            cursor = current;
            current = current.next;
            return item;
        }
    }


}
