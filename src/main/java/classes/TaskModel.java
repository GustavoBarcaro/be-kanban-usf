package classes;

import java.time.LocalDate;

public class TaskModel {
    public String name;
    public String time;
    public String dueTime;
    public String description;
    public String row;

    public void debug()
    {
        System.out.println("name: " + this.name);
        System.out.println("time: " + this.time);
        System.out.println("dueTime: " + this.dueTime);
        System.out.println("description: " + this.description);
        System.out.println("row: " + this.row);
    }
}
