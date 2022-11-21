package classes;

public class TaskDetail extends BaseClass {
    public String id;
    public String name;
    public String time;
    public String dueTime;
    public String description;
    public String status;
    public String row;
    public String idTask;
    public String creatorId;

    public TaskDetail() {}

    public TaskDetail(String id, String name, String time, String dueTime, String description, String status, String row, String idTask, String creatorId) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.dueTime = dueTime;
        this.description = description;
        this.status = status;
        this.row = row;
        this.idTask = idTask;
        this.creatorId = creatorId;
    }
}
