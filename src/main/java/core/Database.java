package core;

// import org.postgresql.*;
import java.sql.*;

public class Database {
    private final String url = "jdbc:postgresql://localhost/database";
    private final String user = "user";
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

    public Array select() {
        Statement st = this.conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM mytable WHERE columnfoo = 500");
        while (rs.next()) {
            System.out.print("Column 1 returned ");
            System.out.println(rs.getString(1));
        }
        rs.close();
        st.close();
    }
}
