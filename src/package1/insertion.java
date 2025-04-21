package package1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class insertion {
	 private static  final String driver ="com.mysql.cj.jdbc.Driver";
	   private static  final String url="jdbc:mysql://localhost:3306/db1";
	   private static  final String uname="root";
	   private static  final String pword="Simha@123";
	   private static PreparedStatement pmst;
	   private static Connection conn;
	   public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,uname,pword);
			String sql="insert into login(id,email,pword) values (?,?,?)";
			pmst=conn.prepareStatement(sql);
			System.out.println("enter id");
			pmst.setString(1, sc.nextLine());
			System.out.println("enter email");
			pmst.setString(2, sc.nextLine());
			System.out.println("enter pword");
			pmst.setString(3, sc.nextLine());
			int i = pmst.executeUpdate();
			if(i>0)
			{
				System.out.println("insertion is succesfull");
				
			}
			else
			{
				System.out.println("Its a failure");
			}
		
		pmst.close();
		conn.close();
		sc.close();
		
		}
		catch (Exception e) {
			e.printStackTrace();
		
		
	}


	   }
}
