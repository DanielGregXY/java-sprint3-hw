package constructor;

import constructor.status.Status;

public class Task {
    private final String name;
    private final String description;
    private String status; //NEW | IN_PROGRESS | DONE
    private final int id;

    public Task(String name,
                String description,
                Status status,
                int id) {
        this.name = name;
        this.description = description;
        this.status = status.toString();
        this.id = id;
    }
    //=================================================================================\\
    public String getName() {
        return name;
    }
    //=================================================================================\\
    public String getDescription() {
        return description;
    }
    //=================================================================================\\
    public int getId() {
        return id;
    }
    //=================================================================================\\
    public String getStatus() {
        return status;
    }
    //=================================================================================\\
    public void setStatus(String status) {
        this.status = status;
    }

}

