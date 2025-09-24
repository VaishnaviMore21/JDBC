import java.sql.*;
import java.util.*;

class PreparedTest {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Connection conn = null;
        PreparedStatement ps = null;

        String url = "jdbc:mysql://localhost:3306/myDatabase";
        String user = "atul";
        String password = "infoplanet";

        try {
            // Register Driver
            Driver d = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(d);
            System.out.println("Driver registered successfully!");

            // Establish Connection
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Database connected!\n");

            // 1. Insert Record
            ps = conn.prepareStatement("INSERT INTO Employee VALUES (?, ?, ?, ?)");
            System.out.print("Enter EMP_ID: ");
            int eid = in.nextInt();

            System.out.print("Enter NAME: ");
            String name = in.next();

            System.out.print("Enter SALARY: ");
            double salary = in.nextDouble();

            System.out.print("Enter DEPARTMENT: ");
            String dept = in.next();

            ps.setInt(1, eid);
            ps.setString(2, name);
            ps.setDouble(3, salary);
            ps.setString(4, dept);

            int x = ps.executeUpdate();
            if (x >= 1)
                System.out.println("✅ Record inserted successfully!");
            System.out.println("-------------------------------------------------------\n");

            // 2. Search Record
            ps = conn.prepareStatement("SELECT * FROM Employee WHERE EMP_ID=?");
            System.out.print("Enter EMP_ID to search: ");
            eid = in.nextInt();
            ps.setInt(1, eid);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("EMP_ID: " + rs.getInt(1));
                System.out.println("EMP_NAME: " + rs.getString(2));
                System.out.println("EMP_SALARY: " + rs.getDouble(3));
                System.out.println("EMP_DEPT: " + rs.getString(4));
            } else {
                System.out.println("⚠️ Record not found.");
            }
            System.out.println("-------------------------------------------------------\n");

            // 3. Delete Record
            ps = conn.prepareStatement("DELETE FROM Employee WHERE EMP_ID=?");
            System.out.print("Enter EMP_ID to delete: ");
            eid = in.nextInt();
            ps.setInt(1, eid);

            x = ps.executeUpdate();
            if (x >= 1)
                System.out.println("🗑 Record deleted successfully!");
            else
                System.out.println("⚠️ Record not found.");

        } catch (Exception e) {
            System.out.println("Error: " + e);
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
                in.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
