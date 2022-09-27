package manager.history;

import constructor.Task;

import java.util.ArrayList;

public interface HistoryManager {

    public ArrayList<Task> getHistory();

    public void setHistory(Task task);

    public void remove(int id);

    static String historyToString(HistoryManager manager) {
        StringBuilder sb = new StringBuilder();

        for (Task task : manager.getHistory()) {
            sb.append(task.getId()).append(",");
        }

        return sb.toString();
    }

}
