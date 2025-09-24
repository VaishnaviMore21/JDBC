import java.sql.*;

class JdbcTest1 {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stat = null;
        ResultSet set = null;

        try {
            // Load the UCanAccess driver
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            System.out.println("Driver registered successfully!");

            // Establish the connection
            conn = DriverManager.getConnection(
                    "jdbc:ucanaccess://C:/Advanced Java/JDBC/myDatabase.accdb");
            System.out.println("Connection established!");

            // Create statement
            stat = conn.createStatement();

            // Execute query
            set = stat.executeQuery("SELECT * FROM Employee");

            // Print header
            System.out.println("Emp ID\t\tNAME\t\tSALARY\t\tDEPARTMENT");
            System.out.println("-----------------------------------------------------------");

            // Iterate through result set
            while (set.next()) {
                System.out.println(
                        set.getString(1) + "\t\t" +
                        set.getString(2) + "\t\t" +
                        set.getString(3) + "\t\t" +
                        set.getString(4)
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (set != null) set.close();
                if (stat != null) stat.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
