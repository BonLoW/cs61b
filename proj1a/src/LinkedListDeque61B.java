import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T>{
    private final Node sentinel;
    private int size;

    public class Node {
        private int size;
        public T item;
        public Node prev;
        public Node next;

        public Node (T item, Node p, Node n) {
            this.prev = p;
            this.next = n;
            this.item = item;
        }
    }

    @Override
    public void addFirst(T x) {
        Node first = sentinel.next;
        sentinel.next = new Node(x, sentinel, sentinel.next);
        first.prev = sentinel.next;
        size ++;
    }

    @Override
    public void addLast(T x) {
        Node last = sentinel.prev;
        sentinel.prev = new Node(x, last, sentinel);
        last.next = sentinel.prev;
        size ++;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        Node j = sentinel.next;
        while (j != sentinel) {
            returnList.add(j.item);
            j = j.next;
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size --;
        return null;
    }

    @Override
    public T removeLast() {
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size --;
        return null;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) return null;
        int i = 0;
        Node j = sentinel;
        while (i <= index) {
            j = j.next;
            i ++;
        }
        return j.item;
    }

    public T getHelper(Node now, int i, int index) {
        if (i == index) return now.item;
        return getHelper(now.next, i + 1, index);
    }

    @Override
    public T getRecursive(int index) {
        if (index < 0 || index > size) return null;
        return getHelper(sentinel.next, 0, index);
    }

    public LinkedListDeque61B() {
        // Do not use "new Node(null, sentinel, sentinel)" because sentinel is always null before you instantiate it.
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }
}
