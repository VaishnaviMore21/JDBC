import java.sql.*; 
 
class MySqlTest 
{ 
 public static void main(String args[]) throws Exception 
 { 
  Class.forName("com.mysql.cj.jdbc.Driver"); 
  System.out.println("Driver is registered"); 
 
 Connection con= 
DriverManager.getConnection("jdbc:mysql://localhost:3306/myDatabase", 
                                   "atul","infoplanet"); 
 System.out.println("Connected"); 
 
 Statement stmt=con.createStatement(); 
 
 ResultSet rs=stmt.executeQuery("select * from employee"); 
 
 System.out.println("EMP_ID\t\tNAME\t\tSALARY\t\tDEPT"); 
 System.out.println("--------------------------------------------------------------------------"); 
 while(rs.next()) 
 { 
System.out.println(rs.getInt(1)+"\t\t"+rs.getString(2)+"\t\t"+rs.getDouble(3
 )+"\t\t"+rs.getString(4)); 
} 
 
 rs.close(); 
 stmt.close(); 
 con.close(); 
  } 
 
} 
