public class LinkedListDeque<T> {
    /** meta data to record the info of Deque */
    private Node sentinel;
    private int size;

    /** raw data structure of the Deque */
    private class Node {
        T item;
        Node prev;
        Node next;

        public Node(T t, Node p, Node n) {
            item = t;
            prev = p;
            next = n;
        }
    }

    /** Creates an empty linked list deque. */
    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    /** Adds an item of type T to the front of the deque. */
    public void addFirst(T item) {
        Node firstNode = new Node(item, sentinel, sentinel.next);
        sentinel.next.prev = firstNode;
        sentinel.next = firstNode;
        size += 1;
    }

    /**  Adds an item of type T to the back of the deque. */
    public void addLast(T item) {
        Node lastNode = new Node(item, sentinel.prev, sentinel);
        sentinel.prev.next = lastNode;
        sentinel.prev = lastNode;
        size += 1;
    }

    /** Removes and returns the item at the front of the deque.
     * If no such item exists, returns null. */
    public T removeFirst() {
        // deque is empty
        if (isEmpty()) {
            return null;
        }
        T firstItem = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return firstItem;
    }

    /** Removes and returns the item at the back of the deque.
     * If no such item exists, returns null. */
    public T removeLast() {
        // deque is empty
        if (isEmpty()) {
            return null;
        }
        T lastItem = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
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

        Node p = sentinel;
        while (index >= 0) {
            p = p.next;
            index -= 1;
        }
        return p.item;
    }

    /** Same as get, but uses recursion. */
    public T getRecursive(int index) {
        if (index < 0 || (index + 1) > size() || isEmpty()) {
            return null;
        }
        return getRecursive(sentinel.next, index);
    }

    /** helper function for getRecursive(int index) */
    private T getRecursive(Node p, int index) {
        if (index == 0) {
            return p.item;
        }
        return getRecursive(p.next, index - 1);
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
        Node p = sentinel.next;
        for (int i = 0; i < size(); i ++) {
            if (i == 0) {
                System.out.print(p.item);
            }
            else {
                System.out.print(" " + p.item);
            }
            p = p.next;
        }
    }
}
