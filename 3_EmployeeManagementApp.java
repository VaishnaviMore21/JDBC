import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;

class MyFrame extends JFrame {
    // Input fields
    JTextField tf1 = new JTextField(15);  // EMP_ID
    JTextField tf2 = new JTextField(15);  // EMP_NAME
    JTextField tf3 = new JTextField(15);  // EMP_SALARY
    JTextField tf4 = new JTextField(15);  // EMP_DEPT

    // Buttons
    JButton ins = new JButton("Add Record");
    JButton del = new JButton("Delete Record");
    JButton modify = new JButton("Update Record");
    JButton search = new JButton("Search Record");

    // Display area
    JTextArea ta = new JTextArea();

    Connection conn;
    Statement stmt;

    MyFrame() {
        setSize(600, 500);
        setTitle("Employee Management");

        // TabbedPane
        JTabbedPane tp = new JTabbedPane();
        add(tp);

        // Panel 1 (Record Management)
        JPanel p1 = new JPanel(new BorderLayout());

        JPanel center = new JPanel(new GridLayout(4, 2, 10, 20));
        center.add(new JLabel("EMP_ID:", JLabel.RIGHT));
        center.add(tf1);
        center.add(new JLabel("EMP_NAME:", JLabel.RIGHT));
        center.add(tf2);
        center.add(new JLabel("EMP_SALARY:", JLabel.RIGHT));
        center.add(tf3);
        center.add(new JLabel("EMP_DEPT:", JLabel.RIGHT));
        center.add(tf4);
        p1.add(center, "Center");

        JPanel south = new JPanel();
        south.add(ins);
        south.add(del);
        south.add(modify);
        south.add(search);
        p1.add(south, "South");

        tp.addTab("Record Management", p1);

        // Panel 2 (Display Records)
        JPanel p3 = new JPanel(new BorderLayout());
        JScrollPane sp = new JScrollPane(ta);
        p3.add(sp);
        ta.setEditable(false);
        ta.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        tp.addTab("Display Records", p3);

        // Database Connection
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver is registered");

            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/myDatabase",
                    "atul", "infoplanet"
            );
            stmt = conn.createStatement();
        } catch (Exception e) {
            System.out.println("Connection Error: " + e);
        }

        // Display initial records
        display_records();

        // Add record
        ins.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String sql = "INSERT INTO employee VALUES (" +
                        tf1.getText() + ",'" + tf2.getText() + "'," +
                        tf3.getText() + ",'" + tf4.getText() + "');";
                try {
                    stmt.execute(sql);
                    JOptionPane.showMessageDialog(null, "Record Inserted Successfully");
                    display_records();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });

        // TODO: Similarly, add action listeners for delete, update, and search
    }

    // Function to display records in TextArea
    void display_records() {
        ta.setText("");

        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM employee");
            ta.append("EMP_ID\tNAME\tSALARY\tDEPT\n");
            ta.append("------------------------------------------------------------\n");

            while (rs.next()) {
                ta.append(
                        rs.getInt(1) + "\t" +
                        rs.getString(2) + "\t" +
                        rs.getDouble(3) + "\t" +
                        rs.getString(4) + "\n"
                );
            }
            ta.append("------------------------------------------------------------\n");
        } catch (Exception e) {
            System.out.println("Display Error: " + e);
        }
    }

    public static void main(String[] args) {
        MyFrame f = new MyFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}
