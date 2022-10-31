package classes;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User extends BaseClass {
    protected String username;
    protected String password;
    protected int id;
    protected boolean isAdmin;

    public void getUserDatabase(String where) {
        System.out.println(String.format("Select id, username, password, is_admin from user_account where %s", where));
        ResultSet rs = this.conn.select(
            String.format("Select id, username, password, is_admin from user_account where %s", where)
        );

        if (rs == null) {
            this.id = -1;
            System.out.println("nao encontrei");
            return;
        }

        try {
            while (rs.next()) {
                this.id = rs.getInt(1);
                this.username = rs.getString(2);
                this.password = rs.getString(3);
                this.isAdmin = rs.getBoolean(4);
            }
        } catch (SQLException e) {
            this.id = -1;
            e.printStackTrace();
        }
    }

    public void debug() {
        System.out.println(this.id);
        System.out.println(this.username);
        System.out.println(this.password);
        System.out.println(this.isAdmin);
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public int getId() {
        return this.id;
    }

    public boolean getIsAdmin() {
        return this.isAdmin;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}
