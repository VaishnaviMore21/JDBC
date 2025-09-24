import java.sql.*;

class ResultSetMetaDataTest {
    public static void main(String[] args) throws Exception {
        String url = "jdbc:mysql://localhost:3306/myDatabase";
        String user = "atul";
        String password = "infoplanet";

        // Load MySQL JDBC driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Establish connection
        Connection conn = DriverManager.getConnection(url, user, password);

        // Create statement and execute query
        Statement stmt = conn.createStatement();
        ResultSet set = stmt.executeQuery("SELECT * FROM employee");

        // Get ResultSet metadata
        ResultSetMetaData m = set.getMetaData();

        // Print number of columns
        System.out.println("No. of columns = " + m.getColumnCount());
        System.out.println("Column Names and Types:");

        // Loop through columns
        for (int i = 1; i <= m.getColumnCount(); i++) {
            System.out.println("Column " + i + ": " + m.getColumnName(i));
            System.out.println("Type: " + m.getColumnTypeName(i));
            System.out.println("---------------------------------------------");
        }

        // Close connection
        conn.close();
    }
}
