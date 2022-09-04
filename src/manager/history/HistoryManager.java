package manager.history;

import constructor.Task;

import java.util.ArrayList;

public interface HistoryManager {

    public ArrayList<Task> getHistory();

    public void setHistory(Task task);

    public void remove(int id);


}
