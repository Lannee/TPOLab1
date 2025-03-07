package task2;


import java.util.Optional;

class LeftistHeap {
    private class Node {
        int key;
        Node left;
        Node right;
        int dist;

        Node(int key) {
            this.key = key;
            this.left = null;
            this.right = null;
            this.dist = 0;
        }
    }

    private Node root;
    private int size;

    public LeftistHeap() {
        this.root = null;
        this.size = 0;
    }

    private Node merge(Node h1, Node h2) {
        if (h1 == null) return h2;
        if (h2 == null) return h1;

        if (h1.key > h2.key) {
            Node temp = h1;
            h1 = h2;
            h2 = temp;
        }

        h1.right = merge(h1.right, h2);

        if (h1.left == null || h1.left.dist < h1.right.dist) {
            Node temp = h1.left;
            h1.left = h1.right;
            h1.right = temp;
        }

        h1.dist = (h1.right == null) ? 0 : h1.right.dist + 1;

        return h1;
    }

    public void insert(int key) {
        Node newNode = new Node(key);
        this.root = merge(this.root, newNode);
        this.size++;
    }

    public Optional<Integer> extractMin() {
        if (root == null) {
            return Optional.empty();
        }
        int min = root.key;
        root = merge(root.left, root.right);
        this.size--;
        return Optional.of(min);
    }

    public int getSize() {
        return this.size;
    }

    public void clearHeap() {
        this.root = null;
        this.size = 0;
    }
}