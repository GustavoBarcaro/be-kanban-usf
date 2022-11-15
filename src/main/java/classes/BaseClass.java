package classes;

import java.sql.ResultSet;
import java.sql.SQLException;

import core.Database;

public abstract class BaseClass {
    protected Database conn;

    protected BaseClass() {
        this.conn = new Database();
    }

    public int auth(String authorization) {
		String token = authorization.substring(7);
        ResultSet rs = this.conn.select(String.format("select id_user from user_token where token = '%s'", token));
        int id_user = -1;
        try {
            while (rs.next()) {
                id_user = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            id_user = -1;
        }

        return id_user;
    }
}
