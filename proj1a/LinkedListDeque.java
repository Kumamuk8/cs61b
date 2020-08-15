public class LinkedListDeque<T> {
    private class StuffNode {
        public T item;
        public StuffNode prev, next;

        public StuffNode(StuffNode p, T i, StuffNode n) {
            item = i;
            prev = p;
            next = n;
        }
    }

    /* The first item (if it exists) is at sentinel.next. */
    private StuffNode sentinel;
    private int size;

    /** Creates an empty SLList. */
    public LinkedListDeque() {
        sentinel = new StuffNode(null,null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public LinkedListDeque(LinkedListDeque<T> other) {
        sentinel = new StuffNode(null,null,null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;

        for (int i = 0; i < other.size(); i++){
            addLast(other.get(i));
        }
    }

    /** Adds x to the front of the list. */
    public void addFirst(T x) {
        sentinel.next = new StuffNode(sentinel, x, sentinel.next);
        if (sentinel.prev == sentinel)
            sentinel.prev = sentinel.next;
        size += 1;
    }

    /** Adds x to the end of the list. */
    public void addLast(T x) {
        sentinel.prev.next = new StuffNode(sentinel.prev, x, sentinel);
        sentinel.prev = sentinel.prev.next;
        size += 1;
    }

    /** Returns the size of the list. */
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return sentinel.next == sentinel;
    }

    public void printDeque() {
        for (int ii = 0; ii < size; ii++) {
            System.out.print(getRecursive(ii) + " ");
        }
        System.out.print("\n");
    }

    public T get(int index) {
        StuffNode ptr = sentinel.next;
        if (index > size)
            return null;
        for (int ii = 0; ii < index; ii++) {
             ptr = ptr.next;
        }
        return ptr.item;
    }

    public T getRecursive(int index) {
        StuffNode ptr = lookup(sentinel.next, index);
        return ptr.item;
    }

    private StuffNode lookup(StuffNode temp, int num){
        if (num == 0) {
            return temp;
        }
        else {
            return lookup(temp.next, num - 1);
        }
    }

    public T removeLast() {
        T returnItem = sentinel.prev.item;
        sentinel.prev = sentinel;
        size -= 1;
        return returnItem;
    }

    public T removeFirst() {
        T returnItem = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        size -= 1;
        return returnItem;
    }

    public static void main(String[] args) {
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
        lld1.addLast(20);
        lld1.addFirst(10);
        lld1.addFirst(5);
        lld1.addLast(30);
        lld1.addLast(40);
        lld1.addFirst(1);
        System.out.println(lld1.size());
        lld1.printDeque();
        LinkedListDeque<Integer> lld2 = new LinkedListDeque<Integer>(lld1);
        lld2.addFirst(88);
        lld2.removeLast();
        lld2.printDeque();
    }
}
