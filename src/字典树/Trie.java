package 字典树;

import java.util.HashMap;
import java.util.Stack;

public class Trie<V> {
    private int size;

    private Node<V> root;

    public Trie() {
        root = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        root = null;
        size = 0;
    }

    public V get(String key) {
        Node<V> node = node(key);
        return (node != null && node.isWord) ? node.value : null;
    }

    public boolean contains(String key) {
        Node<V> node = node(key);
        return node != null && node.isWord;
    }

    public V add(String key, V value) {
        if (root == null) {
            root = new Node<>();
        }

        keyCheck(key);

        Node<V> cur = root;
        for (int i = 0; i < key.length(); i++) {
            Character ch = key.charAt(i);

            if (cur.children == null) {
                cur.children = new HashMap<>();
            }

            if (!cur.children.containsKey(ch)) {
                cur.children.put(ch, new Node<V>());
            }

            cur = cur.children.get(ch);

            if (i == key.length() - 1) {
                cur.isWord = true;
                V oldValue = cur.value;
                cur.value = value;
                ++size;
                return oldValue;
            }
        }

        return null;
    }

    private void keyCheck(String key) {
        if (key == null || key.length() <= 0) {
            throw new IllegalArgumentException("Key must not be empty");
        }
    }

    private Node<V> node(String key) {
        keyCheck(key);

        Node<V> cur = root;
        for (int i = 0; i < key.length(); i++) {
            if (cur == null || cur.children == null || cur.children.isEmpty()) {
                return null;
            }

            Character ch = key.charAt(i);
            cur = cur.children.get(ch);
        }

        return cur;
    }

    public V remove(String key) {
        keyCheck(key);

        Stack<Node<V>> stack = new Stack<>();
        Node<V> cur = root;
        for (int i = 0; i < key.length(); i++) {
            Character ch = key.charAt(i);
            if (cur == null) {
                return null;
            }

            if (cur.children == null || cur.children.isEmpty()) {
                return null;
            }

            stack.push(cur);
            cur = cur.children.get(ch);
        }

        stack.push(cur);
        cur.isWord = false;

        V oldValue = cur.value;

        for (int i = key.length() - 1; i >= 0; i--) {
            Character ch = key.charAt(i);
            Node<V> node = stack.pop();
            if (node.children != null && !node.children.isEmpty()) {
                break;
            }

            Node<V> parent = stack.peek();
            parent.children.remove(ch);
        }

        --size;

        return oldValue;
    }

    public boolean startsWith(String prefix) {
        Node<V> node = node(prefix);
        return node != null;
    }

    private static class Node<V> {
        V value;
        boolean isWord;
        private HashMap<Character, Node<V>> children;
    }
}
