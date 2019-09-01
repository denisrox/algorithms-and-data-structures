import java.util.Stack;

public class TreeImpl<E extends Comparable<? super E>>  {

    private Node<E> root;
    private int size;
    private int depth;
    private static final int DEPTH=3;

    enum TraverseMode {
        IN_ORDER,
        PRE_ORDER,
        POST_ORDER
    }
    public TreeImpl(){
        this(DEPTH);
    }

    public TreeImpl(int depth){
        this.depth=depth;
    }

    public boolean find(E value) {
        return doFind(value).node != null;
    }

    public boolean add(E value) {
        Node<E> node = new Node<>(value);

        if (root == null) {
            this.root = node;
            return true;
        }

        NodeAndParent nodeAndParent = doFind(value);
        if (nodeAndParent.node != null|| nodeAndParent.depth>depth) {
            return false;
        }

        Node<E> previous = nodeAndParent.parent;

        if (previous.shouldBeLeft(value)) {
            previous.setLeftChild(node);
        } else {
            previous.setRightChild(node);
        }

        size++;
        return true;
    }

    private NodeAndParent doFind(E value) {
        Node<E> parent = null;
        Node<E> current = this.root;
        int depth=0;
        while (current != null) {
            if (current.getValue().equals(value)) {
                return new NodeAndParent(current, parent,depth);
            }

            parent = current;

            if (current.shouldBeLeft(value)) {
                current = current.getLeftChild();
            } else {
                current = current.getRightChild();
            }
            depth++;
        }

        return new NodeAndParent(null, parent,depth);
    }


    public boolean remove(E value) {
        NodeAndParent nodeAndParent = doFind(value);
        Node<E> removedNode = nodeAndParent.node;
        Node<E> parent = nodeAndParent.parent;

        if (removedNode == null) {
            return false;
        }

        if (removedNode.isLeaf()) {
            removeLeafNode(parent, removedNode);
        } else if (removedNode.hasOnlySingleChild()) {
            removeNodeWithSingleChild(parent, removedNode);
        } else {
            removeCommonNode(parent, removedNode);
        }

        size--;
        return true;
    }

    private void removeCommonNode(Node<E> parent, Node<E> removedNode) {
        Node<E> successor = getSuccessor(removedNode);
        if (removedNode == root) {
            root = successor;
        } else if (parent.getLeftChild() == removedNode) {
            parent.setLeftChild(successor);
        } else {
            parent.setRightChild(successor);
        }
        successor.setLeftChild(removedNode.getLeftChild());
    }

    private Node<E> getSuccessor(Node<E> removedNode) {
        Node<E> successor = removedNode;
        Node<E> successorParent = null;
        Node<E> current = removedNode.getRightChild();

        while (current != null) {
            successorParent = successor;
            successor = current;
            current = current.getLeftChild();
        }

        if (successor != removedNode.getRightChild()) {
            successorParent.setLeftChild(successor.getRightChild());
            successor.setRightChild(removedNode.getRightChild());
        }

        return successor;
    }

    private void removeNodeWithSingleChild(Node<E> parent, Node<E> removedNode) {
        Node<E> child = removedNode.getLeftChild() != null
                ? removedNode.getLeftChild()
                : removedNode.getRightChild();

        if (removedNode == root) {
            root = child;
        } else if (parent.getLeftChild() == removedNode) {
            parent.setLeftChild(child);
        } else {
            parent.setRightChild(child);
        }
    }

    private void removeLeafNode(Node<E> parent, Node<E> removedNode) {
        if (removedNode == root) {
            root = null;
        } else if (parent.getLeftChild() == removedNode) {
            parent.setLeftChild(null);
        } else {
            parent.setRightChild(null);
        }
    }


    public void display() {
        Stack<Node> globalStack = new Stack<>();
        globalStack.push(root);
        int nBlanks = 64;

        boolean isRowEmpty = false;
        System.out.println("................................................................");

        while (!isRowEmpty) {
            Stack<Node> localStack = new Stack<>();

            isRowEmpty = true;
            for (int i = 0; i < nBlanks; i++) {
                System.out.print(" ");
            }

            while (!globalStack.isEmpty()) {
                Node tempNode = globalStack.pop();
                if (tempNode != null) {
                    System.out.print(tempNode.getValue());
                    localStack.push(tempNode.getLeftChild());
                    localStack.push(tempNode.getRightChild());
                    if (tempNode.getLeftChild() != null || tempNode.getRightChild() != null) {
                        isRowEmpty = false;
                    }
                } else {
                    System.out.print("--");
                    localStack.push(null);
                    localStack.push(null);
                }
                for (int j = 0; j < nBlanks * 2 - 2; j++) {
                    System.out.print(" ");
                }
            }

            System.out.println();

            while (!localStack.isEmpty()) {
                globalStack.push(localStack.pop());
            }

            nBlanks /= 2;
        }
        System.out.println("................................................................");
    }


    public boolean isBalanced() {
        Node<E> node = root;
        if (node == null) {
            return false;
        }
        int maxDepth=getDepth(node);
        if (maxDepth==-1)
            return false;
        return true;
    }

    private int getDepth(Node<E> node){
        if (node == null) {
            return 0;
        }

        int DepthLeft = getDepth(node.getLeftChild())+1;
        if(DepthLeft==-1)
            return -1;
        int DepthRight = getDepth(node.getRightChild())+1;
        if(DepthRight==-1)
            return -1;
        if(Math.abs(DepthLeft-DepthRight)>1)
            return -1;
        else {
            return (Math.max(DepthLeft,DepthRight));
        }
    }


    public int size() {
        return size;
    }


    public boolean isEmpty() {
        return root == null;
    }


    public boolean isFull() {
        return false;
    }


    public void traverse(TraverseMode mode) {
        switch (mode) {
            case IN_ORDER:
                inOrder(root);
                break;
            case PRE_ORDER:
                preOrder(root);
                break;
            case POST_ORDER:
                postOrder(root);
                break;
            default:
                throw new IllegalArgumentException("Unknown mode: " + mode);
        }
    }

    private void postOrder(Node<E> node) {
        if (node == null) {
            return;
        }

        inOrder(node.getLeftChild());
        inOrder(node.getRightChild());
        System.out.println(node.getValue());
    }

    private void preOrder(Node<E> node) {
        if (node == null) {
            return;
        }

        System.out.println(node.getValue());
        inOrder(node.getLeftChild());
        inOrder(node.getRightChild());
    }

    private void inOrder(Node<E> node) {
        if (node == null) {
            return;
        }

        inOrder(node.getLeftChild());
        System.out.println(node.getValue());
        inOrder(node.getRightChild());
    }

    private class NodeAndParent {
        int depth;
        Node<E> node;
        Node<E> parent;

        public NodeAndParent(Node<E> node, Node<E> parent, int depth) {
            this.node = node;
            this.parent = parent;
            this.depth=depth;
        }
    }
}