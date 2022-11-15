package classes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Task extends BaseClass {
    public int id;
    public String name;
    public String time;
    public String dueTime;
    public String description;
    public String status;
    public String row;
    public int creatorId;

    public Task() {}

    public Task(int id, String name, String time, String dueTime, String description, String status, String row, int creatorId) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.dueTime = dueTime;
        this.description = description;
        this.status = status;
        this.row = row;
        this.creatorId = creatorId;
    }

    public int store(String authorization, TaskModel json_body) {
        if (this.auth(authorization) == -1) {
            return -1;
        }

        String query = String.format("insert into task (name, time, due_time, description, row) values ('%s', '%s', '%s', '%s', '%s')",
            json_body.name,
            json_body.time,
            json_body.dueTime,
            json_body.description,
            json_body.row
        );

        this.conn.executeQuery(query);
        ResultSet rs = this.conn.select("select max(id) from task");
        int id = -1;
        try {
            rs.next();
            id = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }

    public Task[] getAll(String authorization) {
        Task tasks[] = {};
        if (this.auth(authorization) == -1) {
            return tasks;
        }
        ResultSet rs = this.conn.select("select * from task");
        try {
            // id | name | time | due_time | description | status | row | creator_id
            rs.next();
            int id = rs.getInt(1);
            String name = rs.getString(2);
            String time = rs.getString(3);
            String due_time = rs.getString(4);
            String description = rs.getString(5);
            String status = rs.getString(6);
            String row = rs.getString(7);
            int creator_id = rs.getInt(8);
            Task task = new Task(id, name, time, due_time, description, status, row, creator_id);
            tasks = Task.push(tasks, task);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    private static Task[] push(Task[] array, Task push) {
        Task[] longer = new Task[array.length + 1];
        System.arraycopy(array, 0, longer, 0, array.length);
        longer[array.length] = push;
        return longer;
    }

    public boolean destroy(String authorization, int task_id) {
        if (this.auth(authorization) == -1) {
            return false;
        }
        String query = String.format("delete from task where id = %d", task_id);
        this.conn.executeQuery(query);
        return true;
    }

    public boolean update(String authorization, TaskModel task_json) {
        if (this.auth(authorization) == -1) {
            return false;
        }
        return true;
    }
}
