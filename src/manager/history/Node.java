package manager.history;

public class Node<T>{
    private Node previousNode;
    private T value;
    private Node nextNode;

    public Node(Node previousNode, T value, Node nextNode) {
        this.previousNode = previousNode;
        this.value = value;
        this.nextNode = nextNode;
    }

    public void setPreviousNode(Node previousNode) {
        this.previousNode = previousNode;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
    }

    public Node getPreviousNode() {
        return previousNode;
    }

    public T getValue() {
        return value;
    }

    public Node getNextNode() {
        return nextNode;
    }
}
