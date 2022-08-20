package manager.history;

import constructor.Task;

import java.util.ArrayList;

public class InMemoryHistoryManager implements HistoryManager {

    static ArrayList<Task> HistoryList = new ArrayList<>();

    @Override
    public ArrayList<Task> getHistory() {
        return HistoryList;
    }

    @Override
    public void setHistory(Task task) {
        if (HistoryList.size() < 10) {
            HistoryList.add(task);
        } else {
            HistoryList.remove(0);
            HistoryList.add(task);
        }
    }

}
