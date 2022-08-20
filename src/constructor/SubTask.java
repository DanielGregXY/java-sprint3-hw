package constructor;

import constructor.status.Status;

public class SubTask extends Task {

    private int epicId;

    public SubTask(String name, String description, Status status, int epicId, int id) {
        super(name, description, status, id);
        this.epicId = epicId;
    }

    public int getEpicId() {
        return epicId;
    }

}
