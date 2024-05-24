import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.lang.Math;

public class ArrayDeque61B<T> implements Deque61B<T>{
    private T[] Array;
    private int size;
    public int realSize = 100;
    private int front;
    private double RFACTOR = 1.2;
    private double DFACTOR = 0.25;

    public ArrayDeque61B() {
        Array = (T[]) new Object[realSize];
        size = 0;
        front = 0;
    }

    private void resizeHelper(T[] newArray) {
        for (int i = 0; i < size; i ++) {
            newArray[i] = Array[(front + i) % realSize];
        }
        Array = newArray;
    }
    private void Dresize() {
        int newSize = (int) (realSize * DFACTOR);
        resizeHelper((T[]) new Object[newSize]);
        realSize = newSize;
        front = 0;
    }
    private void resize() {
        int newSize = (int) (realSize * RFACTOR);
        resizeHelper((T[]) new Object[newSize]);
        realSize = newSize;
        front = 0;
    }

    /**
     * Add {@code x} to the front of the deque. Assumes {@code x} is never null.
     *
     * @param x item to add
     */
    @Override
    public void addFirst(T x) {
        if (size == realSize) {
            resize();
        }
        size ++;
        int index = Math.floorMod(front - 1, realSize);
        Array[index] = x;
        front = index;
    }

    /**
     * Add {@code x} to the back of the deque. Assumes {@code x} is never null.
     *
     * @param x item to add
     */
    @Override
    public void addLast(T x) {
        if (size == realSize) {
            resize();
        }
        Array[(size + front) % realSize] = x;
        size ++;
    }

    /**
     * Returns a List copy of the deque. Does not alter the deque.
     *
     * @return a new list copy of the deque.
     */
    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        for (int i = 0; i < size; i ++) {
            returnList.add(Array[(front + i) % realSize]);
        }
        return returnList;
    }

    /**
     * Returns if the deque is empty. Does not alter the deque.
     *
     * @return {@code true} if the deque has no elements, {@code false} otherwise.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the size of the deque. Does not alter the deque.
     *
     * @return the number of items in the deque.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Remove and return the element at the front of the deque, if it exists.
     *
     * @return removed element, otherwise {@code null}.
     */
    @Override
    public T removeFirst() {
        T frontier = Array[front];
        Array[front] = null;
        size --;
        front = (front + 1) % realSize;
        if (size < realSize * 0.1) {
            Dresize();
        }
        return frontier;
    }

    /**
     * Remove and return the element at the back of the deque, if it exists.
     *
     * @return removed element, otherwise {@code null}.
     */
    @Override
    public T removeLast() {
        T back = Array[(size + front) % realSize];
        Array[(size + front) % realSize] = null;
        size --;
        if (size < realSize * 0.1) {
            Dresize();
        }
        return back;
    }

    /**
     * The Deque61B abstract data type does not typically have a get method,
     * but we've included this extra operation to provide you with some
     * extra programming practice. Gets the element, iteratively. Returns
     * null if index is out of bounds. Does not alter the deque.
     *
     * @param index index to get
     * @return element at {@code index} in the deque
     */
    @Override
    public T get(int index) {
        if (index >= size || index < 0) return null;
        return Array[index + front];
    }

    /**
     * This method technically shouldn't be in the interface, but it's here
     * to make testing nice. Gets an element, recursively. Returns null if
     * index is out of bounds. Does not alter the deque.
     *
     * @param index index to get
     * @return element at {@code index} in the deque
     */
    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }
}
