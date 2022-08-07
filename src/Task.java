public class Task {
    String name;
    String description;
    String status; //NEW | IN_PROGRESS | DONE
    int id;

    public Task(String name,
                String description,
                String status,
                int id) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.id = id;
    }
}

