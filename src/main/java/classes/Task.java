package classes;

import java.time.LocalDate;

public class Task extends BaseClass {
    public int id;
    public String name;
    public LocalDate time;
    public LocalDate dueTime;
    public String description;
    public String status;
    public String row;
    public int creatorId;

    public int store(String authorization, Task json_body) {
        // String query = String.format("insert into user_token " +
        //     "(name, time, dueTime, description, status, row, creatorId) values " +
        //     "(%s, '%s')", user_id, token);
        System.out.println(json_body);
        return 1;
        // this.conn.executeQuery(query);
    }
}
