public class EpicTask {

    private String name;
    private String description;
    private int id;
    public String status = "NEW"; // NEW | IN_PROGRESS | DONE

    public EpicTask(String name, String description, int id) {
        this.name = name;
        this.description = description;
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
