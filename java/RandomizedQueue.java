import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] buf;
    private int idx = 0;

    public RandomizedQueue() {
        buf = (Item[]) new Object[2];
    }

    public boolean isEmpty() {
        return idx == 0;
    }

    public int size() {
        return idx;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (idx == buf.length) {
            this.resize(buf.length * 2);
        }
        buf[idx++] = item;
    }

    private void resize(int capacity) {
        Item[] newbuf = (Item[]) new Object[capacity];
        for (int i = 0; i < idx; i++)
            newbuf[i] = buf[i];
        buf = newbuf;
    }

    public Item dequeue() {
        if (idx == 0) {
            throw new NoSuchElementException();
        }
        if (idx == buf.length / 4) {
            this.resize(buf.length / 2);
        }
        int random = StdRandom.uniform(idx);
        Item item = buf[random];
        buf[random] = buf[--idx];
        buf[idx] = null;
        return item;
    }

    public Item sample() {
        if (idx == 0) {
            throw new NoSuchElementException();
        }
        return buf[StdRandom.uniform(idx)];
    }


    private class RandomizedQueueIterator implements Iterator<Item> {
        private final Item[] iterbuf;
        private int idx = 0;

        public RandomizedQueueIterator(Item[] shuffledbuf) {
            this.iterbuf = shuffledbuf;
        }

        public boolean hasNext() {
            return idx < iterbuf.length;
        }

        public Item next() {
            if (idx == iterbuf.length) {
                throw new NoSuchElementException();
            }
            return iterbuf[idx++];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public Iterator<Item> iterator() {
        Item[] newbuf = (Item[]) new Object[idx];
        for (int i = 0; i < idx; i++)
            newbuf[i] = buf[i];
        StdRandom.shuffle(newbuf);
        return new RandomizedQueueIterator(newbuf);
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> rq = new RandomizedQueue<>();
        System.out.println(rq.isEmpty());

        rq.enqueue(1);
        System.out.println(!rq.isEmpty());
        System.out.println(rq.size() == 1);
        System.out.println(rq.dequeue() == 1);
        System.out.println(rq.isEmpty());

        rq.enqueue(2);
        rq.enqueue(2);
        System.out.println(!rq.isEmpty());
        System.out.println(rq.size() == 2);
        System.out.println(rq.dequeue() == 2);
        System.out.println(rq.sample() == 2);
        System.out.println(rq.dequeue() == 2);
        System.out.println(rq.isEmpty());
        System.out.println(rq.size() == 0);

        rq.enqueue(6);
        rq.enqueue(7);
        rq.enqueue(8);
        rq.enqueue(9);
        rq.enqueue(10);
        System.out.println(!rq.isEmpty());
        System.out.println(rq.size() == 5);
        System.out.println(rq.dequeue());
        System.out.println(rq.dequeue());
        System.out.println(rq.dequeue());
        System.out.println(rq.dequeue());
        System.out.println(rq.dequeue());
        System.out.println(rq.isEmpty());

        rq.enqueue(3);
        rq.enqueue(4);
        rq.enqueue(5);
        rq.enqueue(2);
        rq.enqueue(1);
        System.out.println(!rq.isEmpty());
        System.out.println(rq.size() == 5);

        for (Integer val : rq) {
            System.out.println(val);
        }
        for (Integer val : rq) {
            System.out.println(val);
        }
    }
}
