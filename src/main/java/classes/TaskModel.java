package classes;

public class TaskModel {
    public String id;
    public String name;
    public String time;
    public String dueTime;
    public String description;
    public String row;
    public String id_task_detail;
    public String status;

    public void debug()
    {
        System.out.println("id: " + this.id);
        System.out.println("name: " + this.name);
        System.out.println("time: " + this.time);
        System.out.println("dueTime: " + this.dueTime);
        System.out.println("description: " + this.description);
        System.out.println("row: " + this.row);
        System.out.println("status: " + this.status);
        System.out.println("id_task_detail: " + this.id_task_detail);
    }
}
