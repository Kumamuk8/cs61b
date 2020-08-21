import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyTrieSet implements TrieSet61B {
    private Node root;

    private class Node {
        private boolean isKey;
        private HashMap<Character, Node> map;
        Node(boolean b) {
            isKey = b;
            map = new HashMap<>();
        }
    }

    public MyTrieSet() {
        root = new Node(false);
    }

    /** Clears all items out of Trie */
    @Override
    public void clear() {
        root = new Node(false);
    }

    /** Returns true if the Trie contains KEY, false otherwise */
    @Override
    public boolean contains(String key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        Node temp = get(root, key, 0);
        return (temp != null) && (temp.isKey);
    }

    private Node get(Node x, String key, int d) {
        if (x == null) return null;
        if (d == key.length()) return x;
        char c = key.charAt(d);
        return get(x.map.get(c), key, d+1);
    }

    /** Inserts string KEY into Trie */
    @Override
    public void add(String key) {
        if (key == null || key.length() < 1) {
            return;
        }
        Node curr = root;
        for (int i = 0, n = key.length(); i < n; i++) {
            char c = key.charAt(i);
            if (!curr.map.containsKey(c)) {
                curr.map.put(c, new Node(false));
            }
            curr = curr.map.get(c);
        }
        curr.isKey = true;
    }

    /** Returns a list of all words that start with PREFIX */
    @Override
    public List<String> keysWithPrefix(String prefix) {
        List<String> result = new ArrayList<>();
        Node curr = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (!curr.map.containsKey(c)) return null;
            curr = curr.map.get(c);
        }
        colHelp(prefix, result, curr);
        return result;
    }

    private void colHelp(String s, List<String> x, Node n) {
        if (n == null) return;
        if (n.isKey) x.add(s);
        for (char c : n.map.keySet()) {
            colHelp(s+c, x, n.map.get(c));
        }
    }
    /** Returns the longest prefix of KEY that exists in the Trie
     * Not required for Lab 9. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    @Override
    public String longestPrefixOf(String key) {
        throw new UnsupportedOperationException();
    }
}
