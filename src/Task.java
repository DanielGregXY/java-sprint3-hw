public class Task {
    String name;
    String description;
    String status; //NEW | IN_PROGRESS | DONE

    public Task(String name,
                String description,
                String status) {
        this.name = name;
        this.description = description;
        this.status = status;
    }
}

