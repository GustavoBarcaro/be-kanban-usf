package core;

// import org.postgresql.*;
import java.sql.*;

public class Database {
    private final String url = "jdbc:postgresql://localhost/database";
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

    public ResultSet select(String query) {
        Statement st;
        ResultSet rs = null;
        try {
            st = this.conn.createStatement();
            rs = st.executeQuery(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return rs;
        // while (rs.next()) {
        //     System.out.print("Column 1 returned ");
        //     System.out.println(rs.getString(1));
        // }
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
}
