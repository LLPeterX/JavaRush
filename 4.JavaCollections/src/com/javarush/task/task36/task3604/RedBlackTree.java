package com.javarush.task.task36.task3604;

public class RedBlackTree {
    private static final Node EMPTY = new Node(0);

    static {
        EMPTY.left = EMPTY;
        EMPTY.right = EMPTY;
    }

    protected Node current; // текущий узел
    private Node parent; // родитель
    private Node grand; // дедушка
    private Node great; // прадед
    private Node header; // root

    public RedBlackTree() {
        header = new Node(Integer.MIN_VALUE);
        header.left = EMPTY;
        header.right = EMPTY;
    }

    public boolean isEmpty() {
        return header.left == EMPTY && header.right==EMPTY;
    }

    public void clear() {
        header.right = EMPTY;
    }

    public void insert(int item) {
        current = grand = parent = header; // сначала все смотрят в корень (корень инциализирован в конструкторе)
        EMPTY.element = item;
        while (current.element != item) {
            great = grand;
            grand = parent;
            parent = current;
            current = item > current.element ? current.right : current.left;

            // новодобавляемый узел всегда RED, а потомки д.б. BLACK
            // https://ru.wikipedia.org/wiki/%D0%9A%D1%80%D0%B0%D1%81%D0%BD%D0%BE-%D1%87%D1%91%D1%80%D0%BD%D0%BE%D0%B5_%D0%B4%D0%B5%D1%80%D0%B5%D0%B2%D0%BE
            //if (current.left.color == Color.RED && current.right.color == Color.BLACK) {
            if (current.left.color == Color.RED && current.right.color == Color.RED) {
                reorient(item);
            }
        }

        if (current != EMPTY) {
            return;
        }

        current = new Node(item, EMPTY, EMPTY);

        if (item < parent.element) {
            parent.left = current;
        } else {
            parent.right = current;
        }

        reorient(item);
    }

    protected void reorient(int item) {
        current.color = Color.RED;
        current.left.color = Color.BLACK;
        current.right.color = Color.BLACK;

        if (parent.color == Color.RED) {
            grand.color = Color.RED;
            if (item < grand.element != item < parent.element) {
                parent = rotate(item, grand);
            }
            current = rotate(item, great);
            current.color = Color.BLACK;
        }

        header.right.color = Color.BLACK;
    }

    private Node rotate(int item, Node parent) {
        if (item < parent.element) {
            Node node = parent.left;
            Node resultNode = item < node.element ? rotateWithLeftNode(node) : rotateWithRightNode(node);
            parent.left = resultNode;
            return parent.left;
        } else {
            Node node = parent.right;
            Node resultNode = item < node.element ? rotateWithLeftNode(node) : rotateWithRightNode(node);
            parent.right = resultNode;
            return parent.right;
        }
    }

    private Node rotateWithLeftNode(Node element) {
        Node left = element.left;
        element.left = left.right;
        left.right = element;
        return left;
    }

    private Node rotateWithRightNode(Node element) {
        Node left = element.right;
        element.right = left.left;
        left.left = element;
        return left;
    }

    public static enum Color {
        BLACK,
        RED
    }

    public static class Node {
        private int element;
        private Node left;
        private Node right;
        private Color color;

        public Node(int element) {
            this(element, null, null);
        }

        public Node(int element, Node left, Node right) {
            this.left = left;
            this.right = right;
            this.element = element;
            this.color = Color.BLACK;
        }
    }
}
