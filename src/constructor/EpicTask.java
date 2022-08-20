package constructor;

import constructor.status.Status;

public class EpicTask extends Task {

    public EpicTask(String name, String description, int id) {
        super(name, description, Status.NEW, id);
    }

}
