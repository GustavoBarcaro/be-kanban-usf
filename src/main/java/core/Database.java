package core;

import java.sql.*;

public class Database {
    private final String url = "jdbc:postgresql://172.26.151.149/database";
    private final String user = "user_name";
    private final String password = "pass123";
    private Connection conn;

    public Database() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        this.conn = conn;
    }

    public ResultSet select(String query) throws SQLException {
        Statement st = this.conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        return rs;
    }

    public void executeQuery(String query) {
        Statement st;
        try {
            st = this.conn.createStatement();
            st.executeQuery(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public String insertReturningId(String query) {
        String id = "";
        try {
            ResultSet rs = this.select(query);
            rs.next();
            id = rs.getString(1);
            System.out.println(id);
        } catch (SQLException e) {
            id = "";
            e.printStackTrace();
        }
        return id;
    }
}
