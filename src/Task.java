public class Task {
    private String name;
    private String description;
    private String status; //NEW | IN_PROGRESS | DONE
    private int id;

    public Task(String name,
                String description,
                String status,
                int id) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}

