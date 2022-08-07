public class EpicTask {

    String name;
    String description;
    int id;
    String status = "NEW"; // NEW | IN_PROGRESS | DONE

    public EpicTask(String name, String description, int id) {
        this.name = name;
        this.description = description;
        this.id = id;
    }

}
