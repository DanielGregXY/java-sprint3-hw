package manager.history;

import constructor.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InMemoryHistoryManager implements HistoryManager {
    CustomLinkedList.Node<Task> tail;
    CustomLinkedList.Node<Task> head;

    static CustomLinkedList<Task> linkedList = new CustomLinkedList<Task>();

    static Map<Integer, CustomLinkedList.Node<Task>> historyMap = new HashMap<>();

    @Override
    public ArrayList<Task> getHistory() {
        return linkedList.getTasks();
    }

    @Override
    public void setHistory(Task task) {
        if (linkedList.size < 10) {
            if (!historyMap.containsKey(task.getId())) {
                linkedList.linkLast(task);
                historyMap.put(task.getId(), linkedList.getNode());
            }
        } else {
            linkedList.removeFirst();
            linkedList.linkLast(task);
        }
    }

    @Override
    public void remove(int id) {
        if (historyMap.containsKey(id)) {
            linkedList.removeNode(historyMap.get(id));
            historyMap.remove(id);
        }
    }

    public static class CustomLinkedList<Task> {
        public Node<Task> head;
        public Node<Task> tail;
        private int size = 0;

        public void linkLast(Task task) {
            Node<Task> newNode = new Node<>(task, tail, null);
            if (tail == null) {
                head = newNode;
            } else {
                tail.next = newNode;
            }

            tail = newNode;

            size++;
        }

        public Node<Task> getNode() {
            if (tail.prev != null) {
                return tail.prev;
            } else {
                return tail;
            }
        }

        public ArrayList<Task> getTasks() {
            ArrayList<Task> list = new ArrayList<>();
            Node<Task> prev = tail;

            for (int i = 0; i < size; i++) {
                if (prev != null) {
                    list.add(prev.data);
                    prev = prev.prev;
                }
            }

            return list;
        }

        public void removeFirst() {
            Task element = head.next.data;
            head.next = head.next.next;
            size--;
        }

        public void removeNode(Node<Task> node) {
            if (node.equals(head)) {
                head = node.next;
                if (node.next != null) {
                    node.next.prev = null;
                }
            } else {
                node.prev.next = node.next;
                if (node.next != null) {
                    node.next.prev = node.prev;
                }
            }
        }

        static class Node<T> {

            public T data;
            public Node<T> next;
            public Node<T> prev;

            Node(T value, Node<T> prev, Node<T> next) {
                this.data = value;
                this.prev = prev;
                this.next = next;
            }
        }
    }

}
