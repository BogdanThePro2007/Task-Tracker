package manager.history;

import model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CustomLinkedList<T> {

    Node<T> head;
    Node<T> tail;
    HashMap<Long, Node<T>> nodesMap = new HashMap<>();

    int size = 0;

    public void linkLast(T task) {
        Node<T> buf = tail;
        Node<T> newNode = new Node<>(buf, task, null);
        tail = newNode;
        if (buf == null) head = newNode;
        else buf.setNextNode(newNode);
        Task taskBuf = (Task) task;
        nodesMap.put(taskBuf.getId(), newNode);
        size += 1;
    }

    public void removeNode(Node<T> removableNode) {
        if (removableNode.getNextNode() == null) {
            tail = removableNode.getPreviousNode();
        } else if (removableNode.getPreviousNode() == null) {
            head = removableNode.getNextNode();
        } else if (removableNode.getPreviousNode() != null && removableNode.getNextNode() != null) {
            Node<T> nodePrev = removableNode.getPreviousNode();
            Node<T> nodeNext = removableNode.getNextNode();
            nodePrev.setNextNode(removableNode.getNextNode());
            nodeNext.setPreviousNode(removableNode.getPreviousNode());
        }
        nodesMap.remove(removableNode);
        size -= 1;
    }

    public List<T> getTasks() {
        List<T> taskList = new ArrayList<>();
        for (Node<T> node : nodesMap.values()) {
            taskList.add(node.getValue());
        }
        return taskList;
    }

    public Integer size() {
        return size;
    }

    public void removeTaskById(long taskId) {
        if (nodesMap.containsKey(taskId)) {
            removeNode(nodesMap.get(taskId));
        }
    }

    public boolean contains(Long taskId) {
        if (nodesMap.containsKey(taskId)) {
            return true;
        }
        return false;
    }

    public void removeHead() {
        for (Node<T> node : nodesMap.values()) {
            removeNode(node);
        }
        nodesMap.clear();
    }

    public void clear() {
        nodesMap.clear();
    }
}
