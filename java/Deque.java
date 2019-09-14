import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first = null;
    private Node last = null;
    private int size = 0;

    private class Node {
        private Item item;
        private Node previous;
        private Node next;
    }

    // construct an empty deque
    public Deque() {

    }

    // is the deque empty?
    public boolean isEmpty() {
        return this.first == null;
    }

    // return the number of items on the deque
    public int size() {
        return this.size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node oldFirst = first;
        first = new Node();
        if (last == null) {
            last = first;
        }
        first.item = item;
        first.next = oldFirst;
        if (oldFirst != null) {
            oldFirst.previous = first;
        }
        size += 1;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node oldLast = last;
        last = new Node();
        if (first == null) {
            first = last;
        }
        last.item = item;
        last.previous = oldLast;
        if (oldLast != null) {
            oldLast.next = last;
        }
        size += 1;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        Item item = first.item;
        if (first.next != null) {
            first = first.next;
            first.previous = null;
        } else {
            first = null;
            last = null;
        }
        size -= 1;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        Item item = last.item;
        if (last.previous != null) {
            last = last.previous;
            last.next = null;
        } else {
            last = null;
            first = null;
        }
        size -= 1;
        return item;
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (current == null) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> dq = new Deque<Integer>();
        System.out.println(dq.isEmpty());

        dq.addFirst(1);
        System.out.println(!dq.isEmpty());
        System.out.println(dq.size == 1);
        System.out.println(dq.removeFirst() == 1);
        System.out.println(dq.isEmpty());

        dq.addLast(2);
        System.out.println(!dq.isEmpty());
        System.out.println(dq.size == 1);
        System.out.println(dq.removeLast() == 2);
        System.out.println(dq.isEmpty());
        System.out.println(dq.size == 0);

        dq.addFirst(3);
        System.out.println(!dq.isEmpty());
        System.out.println(dq.size == 1);
        System.out.println(dq.removeLast() == 3);
        System.out.println(dq.isEmpty());

        dq.addLast(4);
        System.out.println(!dq.isEmpty());
        System.out.println(dq.size == 1);
        System.out.println(dq.removeFirst() == 4);
        System.out.println(dq.isEmpty());

        dq.addLast(3);
        dq.addLast(4);
        dq.addLast(5);
        dq.addFirst(2);
        dq.addFirst(1);
        System.out.println(!dq.isEmpty());
        System.out.println(dq.size == 5);
        int expected = 1;
        for (Integer val: dq) {
            System.out.println(val == expected++);
        }
    }
}
