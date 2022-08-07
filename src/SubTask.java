public class SubTask extends Task {

    int epicId;

    public SubTask(String name, String description, String status, int epicId, int id) {
        super(name, description, status, id);
        this.epicId = epicId;
    }

}
