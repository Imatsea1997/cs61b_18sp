/**
 *
 * 1. last指向最后一个元素的后一个空位置。add:先添加元素，再往后移动；remove:先往前移动，再删除元素。
 * 2. first指向第一个元素的前一个空位置。add:先添加元素，再往前移动；remove:先往后移动，再删除元素。
 * */
public class ArrayDeque<T> {
    private T[] items;
    private int first;
    private int last;
    private int size;

    /** Creates an empty ArrayList deque. */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        first = 0;
        last = 1;
        size = 0;
    }

    /** easy way to create ArrayList deque with elements */
    public void of(T... args) {
        if (args.length <= 0) {
            return;
        }
        int k;
        for (k = 0; k < args.length; k += 1) {
            addLast(args[k]);
        }
    }

    /** Adds an item of type T to the front of the deque. */
    public void addFirst(T item) {
        if (isFull()) {
            resize(2 * items.length);
        }
        items[first] = item;
        first = minusOne(first);
        size += 1;                      //增加可以晚加，这样保持满空间，用时再扩容（eg.长期8/8）
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
            resize(items.length / 2);
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
            resize(items.length / 2);
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
        int p = plusOne(first);
        while (index > 0) {
            p = plusOne(p);
            index -= 1;
        }
        return items[p];
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
        for (int i = 0; i < size(); i++) {
            if (i == 0) {
                System.out.print(items[p]);
            } else {
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
        //先降低size，再判断是否shrink。先shrink，防止大量空闲空间。(eg, 3/16长期保持）
        return items.length >= 16 && ((double) (size - 1) / (double) items.length) < 0.25;
    }

    /** If full, expand it to twice the size;
     * if R(ratio of usage) is too low(<= 25%), shrink it to half the size */
    private void resize(int capacity) {
        T[] newItems = (T[]) new Object[capacity];
        int p = plusOne(first);
        for (int i = 0; i < size; i++) {
            newItems[i + 1] = items[p];     //空出[0]的位置，让first指向这个空位置
            p = plusOne(p);
        }
        items = newItems;
        //first和last是在newItems的位置，需要更新
        first = 0;
        last = plusOne(size); //有元素的后一个空位置
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
