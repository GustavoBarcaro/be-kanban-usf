package classes;

import java.sql.ResultSet;
import java.sql.SQLException;

import core.Database;

public abstract class BaseClass {
    protected Database conn;

    protected BaseClass() {
        this.conn = new Database();
    }

    public String auth(String authorization) {
		String token = authorization.substring(7);
        System.out.println(String.format("select id_user from user_token where token = '%s'", token));
        String id_user = "";
        try {
            ResultSet rs = this.conn.select(String.format("select id_user from user_token where token = '%s'", token));
            while (rs.next()) {
                id_user = rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            id_user = "";
        }

        return id_user;
    }
}
