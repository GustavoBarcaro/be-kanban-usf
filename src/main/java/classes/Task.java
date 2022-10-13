package classes;

import java.time.LocalDate;

public class Task {
    protected String name;
    protected LocalDate time;
    protected LocalDate dueTime;
    protected String description;
    protected String status;
    protected String row;
    protected int id;
    protected int creatorId;

    public void Task(int id) {
        this.id = id;
    }
}
