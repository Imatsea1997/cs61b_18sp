/**
 * 1. empty时，first和last均指向[0]，空元素
 * 2. 不是empty时，last指向最后一个元素的后一个空位置。先添加元素，再往后移动；先往前移动，再删除元素。
 * 3。 first指向第一个元素的前一个空位置。先添加元素，再往前移动；先往后移动，再删除元素。
 * */
public class ArrayDeque<T> {
    private T[] items;
    private int first;
    private int last;
    private int size;

    /** Creates an empty ArrayList deque. */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        first = last = 0;
        size = 0;
    }

    /** Adds an item of type T to the front of the deque. */
    public void addFirst(T item) {
        if (isFull()) {
            resize(2 * items.length);
        }
        items[first] = item;
        first = minusOne(first);
        size += 1;
    }

    /**  Adds an item of type T to the back of the deque. */
    public void addLast(T item) {
        if (isFull()) {
            resize(2 * items.length);
        }
        items[last] = item;
        last = plusOne(last);
        size += 1;
    }

    /** Removes and returns the item at the front of the deque.
     * If no such item exists, returns null. */
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        if (isUsageLow()) {
            resize(1/2 * items.length);
        }
        first = plusOne(first);
        T firstItem = items[first];
        items[first] = null;
        size -= 1;
        return firstItem;
    }

    /** Removes and returns the item at the back of the deque.
     * If no such item exists, returns null. */
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        if (isUsageLow()) {
            resize(1/2 * items.length);
        }
        last = minusOne(last);
        T lastItem = items[last];
        items[last] = null;
        size -= 1;
        return lastItem;
    }

    /** Gets the item at the given index, where 0 is the front
     * If no such item exists, returns null. Must not alter the deque!*/
    public T get(int index) {
        //special case: index非法或deque为空
        if (index < 0 || (index + 1) > size() || isEmpty()) {
            return null;
        }
        return items[index];
    }

    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Returns the number of items in the deque. */
    public int size() {
        return size;
    }

    /**Prints the items in the deque from first to last,
     * separated by a space. */
    public void printDeque() {
        if (isEmpty()) {
            return;
        }
        int p = plusOne(first);
        for (int i = 0; i < size(); i ++) {
            if (i == 0) {
                System.out.print(items[p]);
            }
            else {
                System.out.print(" " + items[p]);
            }
            p = plusOne(p);
        }
    }

    /** Returns true if deque is full, false otherwise. */
    private boolean isFull() {
        return size == items.length;
    }

    /** Returns true if usage(size / items.length) is too low(<=25%), false otherwise. */
    private boolean isUsageLow() {
        return size > 16 && (size / items.length) <= 0.25;
    }

    /** If full, expand it to twice the size;
     * if R(ratio of usage) is too low(<= 25%), shrink it to half the size */
    private void resize(int capacity) {
        T[] newItems = (T[]) new Object[capacity];
        int p = plusOne(first);
        for (int i = 0; i < size; i ++) {
            newItems[i] = items[p];
            p = plusOne(p);
        }
        items = newItems;
    }

    /** computed the index immediately “before” a given index. */
    private int minusOne(int index) {
        return (index - 1 + items.length) % items.length;
    }

    /** computed the index immediately “after” a given index. */
    private int plusOne(int index) {
        return (index + 1) % items.length;
    }
}
