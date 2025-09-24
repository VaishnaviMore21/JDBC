import java.sql.*;

class CompleteDatabaseMetaDataTest {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/myDatabase";
        String user = "atul";
        String password = "infoplanet";

        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            Connection conn = DriverManager.getConnection(url, user, password);

            // Get database metadata
            DatabaseMetaData meta = conn.getMetaData();

            // Print basic database info
            System.out.println("Driver Name: " + meta.getDriverName());
            System.out.println("Driver Version: " + meta.getDriverVersion());
            System.out.println("Logged User: " + meta.getUserName());
            System.out.println("Database Product Name: " + meta.getDatabaseProductName());
            System.out.println("Database Product Version: " + meta.getDatabaseProductVersion());
            System.out.println("--------------------------------------------------");

            // List all tables
            ResultSet tables = meta.getTables(null, null, "%", new String[]{"TABLE"});
            while (tables.next()) {
                String tableName = tables.getString("TABLE_NAME");
                System.out.println("Table: " + tableName);

                // List columns for this table
                ResultSet columns = meta.getColumns(null, null, tableName, "%");
                while (columns.next()) {
                    String colName = columns.getString("COLUMN_NAME");
                    String colType = columns.getString("TYPE_NAME");
                    int colSize = columns.getInt("COLUMN_SIZE");
                    System.out.println("    Column: " + colName + " | Type: " + colType + " | Size: " + colSize);
                }

                // List primary keys for this table
                ResultSet pkeys = meta.getPrimaryKeys(null, null, tableName);
                System.out.print("    Primary Keys: ");
                while (pkeys.next()) {
                    System.out.print(pkeys.getString("COLUMN_NAME") + " ");
                }
                System.out.println("\n--------------------------------------------------");
            }

            // Close connection
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
