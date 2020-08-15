public class ArrayDeque <T> {
    private T[] items;
    private int size, cap;
    private int front;

    /** Creates an empty list. */
    public ArrayDeque() {
        cap = 8;
        items = (T[]) new Object[cap];
        size = 0;
        front = 0;
    }

    /** Creates a copy. */
    public ArrayDeque(ArrayDeque<T> other) {
        cap = other.cap;
        size = other.size;
        front = other.front;
        items = (T[]) new Object[cap];
        System.arraycopy(other.items, 0, items, 0, cap);
    }

    /** Inserts X into the front of the list. */
    public void addFirst(T x) {
        if (size == items.length) {
            resize(size * 2);
        }
        front -= 1;
        items[cap + front] = x;
        size = size + 1;
    }

    /** Inserts X into the back of the list. */
    public void addLast(T x) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[size + front] = x;
        size = size + 1;
    }

    /** Resizes the underlying array to the target capacity. */
    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        System.arraycopy(items, 0, a, 0, size + front);
        System.arraycopy(items, cap + front, a, capacity + front, -front);
        items = a;
        cap = capacity;
    }

    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty(){
        return size == 0;
    }

    /** Prints the items in the deque from first to last, separated by a space. */
    public void printDeque() {
        for (int ii = 0; ii < size; ii++) {
            System.out.print(get(ii) + " ");
        }
        System.out.print("\n");
    }

    /** Returns the item from the back of the list. */
    private T getLast() {
        return items[size - 1 + front];
    }

    /** Returns the item from the front of the list. */
    private T getFirst() {
        return items[cap + front];
    }

    /** Gets the ith item in the list (The first item is at cap + front). */
    public T get(int i) {
        if (-front >= i + 1)
            return items[cap + front + i];
        return items[i + front];
    }

    /** Returns the number of items in the list. */
    public int size() {
        return size;
    }

    /** Deletes item from back of the list and
     * returns deleted item. */
    public T removeLast() {
        T x = getLast();
        items[size + front - 1] = null;
        size -= 1;
        return x;
    }

    /** Deletes item from front of the list and
     * returns deleted item. */
    public T removeFirst() {
        T x = getFirst();
        items[cap + front] = null;
        size -= 1;
        front += 1;
        return x;
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> lld1 = new ArrayDeque();
        lld1.addLast(20);
        lld1.addFirst(10);
        lld1.addFirst(5);
        lld1.addLast(30);
        lld1.addLast(40);
        lld1.addFirst(1);
        lld1.addLast(100);
        System.out.println(lld1.size());
        lld1.printDeque();
        ArrayDeque<Integer> lld2 = new ArrayDeque(lld1);
        lld2.addFirst(108);
        lld2.removeLast();
        lld2.printDeque();
    }
}
