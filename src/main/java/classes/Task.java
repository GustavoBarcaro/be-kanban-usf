package classes;

import java.sql.ResultSet;
import java.sql.SQLException;

import core.Helper;

public class Task extends BaseClass {
    public String id;
    public String name;
    public TaskDetail[] tasks;

    public Task() {}

    public Task(String id, String name) {
        this.id = id;
        this.name = name;
        this.tasks = new TaskDetail[0];
    }

    public Task(String id, String name, String time, String dueTime, String description, String status, String row, String id_task, String creatorId) {
        this.id = id;
        this.name = name;
        this.tasks[0] = new TaskDetail(id, name, time, dueTime, description, status, row, id_task, creatorId);
    }

    public String store(String authorization, TaskModel json_body) {
        String id_user = this.auth(authorization);
        if (id_user.equals("")) {
            return "sem autorização";
        }

        String id_task = json_body.id;
        if(json_body.id.equals("") || json_body.id == null) {
            String query = String.format("insert into task (name, id_user) values ('%s', '%s') returning id",
                json_body.name, id_user
            );

            id_task = this.conn.insertReturningId(query);
        }

        String query = String.format("insert into task_detail (id_task, name, time, due_time, description, row, id_user) values ('%s', '%s', '%s', '%s', '%s', '%s', '%s') returning id",
            id_task, json_body.name, json_body.time, json_body.dueTime, json_body.description, json_body.row, id_user
        );

        String[] keys = {"task_detail", "task"};
        String[] values= {this.conn.insertReturningId(query), id_task};

        return Helper.simpleJson(keys, values);
    }

    public Task[] getAll(String authorization) {
        Task tasks[] = {};
        String id_user = this.auth(authorization);

        if (id_user.equals("")) {
            return tasks;
        }

        try {
            ResultSet rs = this.conn.select("select * from task");
            while (rs.next()) {
                String id = rs.getString(1);
                String name = rs.getString(2);
                Task task = new Task(id, name);
                tasks = Task.push(tasks, task);

                ResultSet rsd = this.conn.select("select * from task_detail where id_task = '" + id + "'");
                while (rsd.next()) {
                    String idd = rsd.getString(1);
                    String named = rsd.getString(2);
                    String time = rsd.getString(3);
                    String due_time = rsd.getString(4);
                    String description = rsd.getString(5);
                    String status = rsd.getString(6);
                    String row = rsd.getString(7);
                    String id_task = rsd.getString(8);
                    TaskDetail taskd = new TaskDetail(idd, named, time, due_time, description, status, row, id_task, id_user);
                    for (int i = 0; i < tasks.length; i++) {
                        if (tasks[i].id.equals(id_task)) {
                            tasks[i] = Task.push(tasks[i], taskd);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    private static Task push(Task task, TaskDetail push) {
        TaskDetail[] longer = new TaskDetail[task.tasks.length + 1];
        System.arraycopy(task.tasks, 0, longer, 0, task.tasks.length);
        longer[task.tasks.length] = push;
        task.tasks = longer;
        return task;
    }

    private static Task[] push(Task[] array, Task push) {
        Task[] longer = new Task[array.length + 1];
        System.arraycopy(array, 0, longer, 0, array.length);
        longer[array.length] = push;
        return longer;
    }

    public boolean destroy(String authorization, String task_id) {
        if (this.auth(authorization) == "") {
            return false;
        }
        String query = String.format("delete from task where id = '%d'", task_id);
        this.conn.executeQuery(query);
        return true;
    }

    public boolean update(String authorization, TaskModel task_json, String id_task_detail) {
        String id_user = this.auth(authorization);

        if (id_user.equals("")) {
            return false;
        }

        if (task_json.id.equals("")) {
            return false;
        }

        this.destroy(authorization, id_task_detail);
        this.store(authorization, task_json);

        return true;
    }
}
