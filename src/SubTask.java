public class SubTask extends Task {

    private int epicId;

    public SubTask(String name, String description, String status, int epicId, int id) {
        super(name, description, status, id);
        this.epicId = epicId;
    }

    public int getEpicId() {
        return epicId;
    }

}
