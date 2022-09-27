package manager;

import constructor.EpicTask;
import constructor.SubTask;
import manager.history.HistoryManager;
import manager.history.InMemoryHistoryManager;
import manager.task.InMemoryTaskManager;
import manager.task.TaskManager;

import java.io.IOException;

public class Managers {
//
    public static HistoryManager getDefaultHistory() {
        return new InMemoryHistoryManager();
    }

    public static TaskManager getDefaultTask() {
        return new InMemoryTaskManager() {
            @Override
            public int add(EpicTask epic) throws IOException {
                return 0;
            }

            @Override
            public int add(SubTask subtask) throws IOException {
                return 0;
            }
        };
    }


}
