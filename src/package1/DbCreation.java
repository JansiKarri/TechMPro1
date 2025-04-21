package package1;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class DbCreation {
   private static  final String driver ="com.mysql.cj.jdbc.Driver";
   private static  final String url="jdbc:mysql://localhost:3307/";
   private static  final String uname="root";
   private static  final String pword="root";
   private static PreparedStatement pmst;
   private static Connection conn;
   
	public static void main(String[] args) {
		try {
			Scanner sc = new Scanner(System.in);
			Class.forName(driver);
			conn = DriverManager.getConnection(url,uname,pword);
			System.out.println("Enter db name");
			String sql="Create database "+sc.nextLine();
			pmst=conn.prepareStatement(sql);
			int i = pmst.executeUpdate();
			if(i>0)
			{
				System.out.println("Db creation is succesfull");
				
			}
			else
			{
				System.out.println("Its a failure");
			}
			pmst.close();
			conn.close();
			sc.close();
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
