import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * @author Sergey Klunniy
 */
public class MyHashMap<K, V> {
    private Node<K, V>[] objects;
    private int size = 0;
    private static final double LOAD_FACTOR = 0.75;

    public void put(K key, V value) {
        grow();
        Node<K, V> newNode = new Node<>(key, value);
        putNode(newNode);
    }


    public int size() {
        return size;
    }

    private void putNode(Node<K, V> node) {
        int index = getIndexFromHash(node.getKey());
        Node<K, V> сurrentNode = objects[index];
        if (сurrentNode == null) {
            objects[index] = node;
        } else {
            // у нас в бакете уже что-то есть и надо понимать что там
            // 1 нода или связный список?
            // Надо понять что там совпадения по ключу
            Node<K, V> tempNode = сurrentNode;
            while (tempNode != null) {
                if (Objects.equals(tempNode.key, node.key)) {
                    tempNode.value = node.value;
                    return;
                }
                сurrentNode = tempNode;
                tempNode = tempNode.next;
            }
            сurrentNode.next = node;
        }
        size++;
    }

    private void grow() {
        if (objects == null || objects.length == 0) {
            objects = new Node[16];
        } else if (size >= (int) (objects.length * LOAD_FACTOR)) {
            size = 0;
            int newCapacity = objects.length << 1;
            Node<K, V>[] tempObjects = objects;    //сохраняем ссылку на старый массив
            objects = new Node[newCapacity];
            for (Node<K, V> node : tempObjects) {
                if (node != null) {
                    putNode(node);
                    Node<K, V> tempNode = node.next;
                    while (tempNode != null) {
                        putNode(tempNode);
                        tempNode = tempNode.next;
                    }
                }
            }
        }
    }

    private int getIndexFromHash(K key) {
        int hashcode;
        if (key == null) {
            hashcode = 0;
        } else {
            hashcode = key.hashCode();
        }
        return Math.abs(hashcode % objects.length);
    }

    public V get(K key) {
        int index = getIndexFromHash(key); //мы находим ячейку массива где находится элемент
        Node<K, V> currentNode = objects[index];

        while (currentNode != null) {
            if (Objects.equals(currentNode.key, key)) {
                return currentNode.value;
            }
            currentNode = currentNode.next;
        }
        return null;
    }

    //сделать если налл что бы
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Node<K, V> node : objects) {
            if (node != null)
                sb = sb.append(node);
        }
        return sb.toString();
    }

    class Node<K, V> {
        K key;
        V value;

        Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<?, ?> node = (Node<?, ?>) o;
            return Objects.equals(key, node.key) && Objects.equals(value, node.value) && Objects.equals(next, node.next);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value, next);
        }

        @Override
        public String toString() {
            return "{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }
}
