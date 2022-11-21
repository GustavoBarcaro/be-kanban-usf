package classes;

import java.sql.ResultSet;
import java.sql.SQLException;

import core.Token;

public class User extends BaseClass {
    protected String username;
    protected String password;
    protected String id;
    protected boolean isAdmin;

    public String store(UserModel user) {
        user.password = Token.sha256(user.password);
        String query = String.format("insert into user_account (username, password, is_admin) values ('%s', '%s', %s)",
            user.username,
            user.password,
            user.isAdmin
        );
        this.conn.executeQuery(query);
        ResultSet rs = this.conn.select("select max(id) from user_account");
        String id = "";
        try {
            rs.next();
            id = rs.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }

    public void getUserDatabase(String where) {
        ResultSet rs = this.conn.select(
            String.format("select id, username, password, is_admin from user_account where %s", where)
        );

        if (rs == null) {
            this.id = "";
            System.out.println("nao encontrei");
            return;
        }

        try {
            while (rs.next()) {
                this.id = rs.getString(1);
                this.username = rs.getString(2);
                this.password = rs.getString(3);
                this.isAdmin = rs.getBoolean(4);
            }
        } catch (SQLException e) {
            this.id = "";
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

    public String getId() {
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

    public void setId(String id) {
        this.id = id;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}
