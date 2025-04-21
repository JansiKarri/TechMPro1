package package1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import com.mysql.cj.protocol.Resultset;

public class DynamicApplication {
	 private static  final String driver ="com.mysql.cj.jdbc.Driver";
	  // private static  final String url="jdbc:mysql://localhost:3307/db1";
	   private static  final String uname="root";
	   private static  final String pword="root";
	private static final String url = null;
	   private static PreparedStatement pmst;
	   private static Connection conn;
	   
	public static void main(String[] args) {
		int choice;
		do {
			Scanner sc = new Scanner(System.in);
			System.out.println("Choose your choice");
			DisplayMenu();
			choice = Integer.parseInt(sc.next());
			switch (choice) {
			case 1:
				CreateDatabase();
				break;
			case 2:
				DropDatabase();
				break;
			case 3:
				DataInsertion();
				break;
			case 4:
				DeleteById();
				break;
			case 5:
				UpdateData();
				break;
			case 6:
				GetById();
				break;
			case 7:
				GetAll();
				break;
			case 8:
				Exit();
				break;

			}

		}

		while (choice > 0);

	}

	private static void DropDatabase() {
		try {
			Class.forName(driver);
			String url="jdbc:mysql://localhost:3307/";
			conn=DriverManager.getConnection(url, uname, pword);
			System.out.println("Enter database name:");
			Scanner sc=new Scanner(System.in);
			String sql="drop database "+sc.next();
			pmst=conn.prepareStatement(sql);
			int i=pmst.executeUpdate();
			if(i==0) {
				System.out.println("Database is dropped");
			}else {
				System.out.println("database is not dropped");
			}
			conn.close();
			pmst.close();
		} catch (Exception e) {
			e.printStackTrace();		}
		
	}

	private static void Exit() {
		System.out.println("Exiting...");
		//System.out.println("Thank You for using our service 😊");
		System.exit(0);

	}

	private static void GetAll() {
		try {
			Class.forName(driver);
			String url="jdbc:mysql://localhost:3307/maindb";
			conn = DriverManager.getConnection(url,uname,pword);
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter table name");
			String sql = "select * from "+sc.next();
			pmst = conn.prepareStatement(sql);
			ResultSet rs = pmst.executeQuery();
			while (rs.next()) {
				System.out.println("o_id is:"+ rs.getInt("o_id")+", "+ 
						"o_name is:"+rs.getString("o_name")+" ,"+
						"o_pin is:"+rs.getString("o_pin")+" ,"+
						"o_address is:"+rs.getString("o_address"));
				}
			pmst.close();
			conn.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		

	}

	private static void GetById() {
		Scanner sc=new Scanner(System.in);
		try {
			Class.forName(driver);
			String url="jdbc:mysql://localhost:3307/maindb";
			conn=DriverManager.getConnection(url,uname,pword);
			System.out.println("Enter table name");
			String sql="Select * from " + sc.next() + " where o_id= ?";
			pmst=conn.prepareStatement(sql);
			System.out.println("Enter id");
			pmst.setInt(1, sc.nextInt());
			ResultSet rs=pmst.executeQuery();
			while(rs.next())
			{
				System.out.println("o_id is : "+rs.getInt("o_id"));
				System.out.println("o_name is : "+rs.getString("o_name"));
				System.out.println("o_pin is : "+rs.getInt("o_pin"));
				System.out.println("o_address is : "+rs.getString("o_address"));
			}
			pmst.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void UpdateData() {
		Scanner sc=new Scanner(System.in);
		try {
			Class.forName(driver);
			//System.out.println("Enter db to update data");
			String url="jdbc:mysql://localhost:3307/maindb";
			conn=DriverManager.getConnection(url, uname, pword);
			System.out.println("Enter  table name:");
			//sc.nextLine();
			String sql="update "+sc.next() + " set o_name=?,o_pin=?,o_address=? where o_id=?";
			pmst=conn.prepareStatement(sql);
			System.out.println("enter o_name");
			pmst.setString(1, sc.next());
			System.out.println("enter o_pin");
			pmst.setInt(2, sc.nextInt());
			sc.nextLine();
			System.out.println("enter o_address");
			pmst.setString(3, sc.nextLine());
			System.out.println("enter o_id");
			pmst.setInt(4, sc.nextInt());
			int i=pmst.executeUpdate();
			if(i>0) {
				System.out.println("updation sucesss");
			}else {
				System.out.println("failed updation");
			}
			conn.close();
			pmst.close();
		} catch (Exception e) {
			e.printStackTrace();		}

	}
	

	private static void DeleteById() {
		Scanner sc=new Scanner(System.in);
		try {
			Class.forName(driver);
			String url="jdbc:mysql://localhost:3307/maindb";
			conn=DriverManager.getConnection(url,uname,pword);
			System.out.println("Enter table name");
			String sql="delete from " + sc.next() + " where o_id= ?";
			pmst=conn.prepareStatement(sql);
			System.out.println("Enter id");
			pmst.setInt(1, sc.nextInt());
			int i = pmst.executeUpdate();
			if(i>0)
			{
				System.out.println("row is deleted successfully");
			}
			else
			{
				System.out.println("failure in deletion operation");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void DataInsertion() {
		Scanner sc=new Scanner(System.in);
		try {
			Class.forName(driver);
			//System.out.println("Enter db to insert data");
			String url="jdbc:mysql://localhost:3307/maindb";
			conn=DriverManager.getConnection(url, uname, pword);
			System.out.println("Enter  table name:");
			String sql="insert into "+sc.next() + "(o_id,o_name,o_pin,o_address) values (?,?,?,?)";
			pmst=conn.prepareStatement(sql);
			System.out.println("enter o_id");
			pmst.setInt(1, sc.nextInt());
			System.out.println("enter o_name");
			pmst.setString(2, sc.next());
			System.out.println("enter o_pin");
			pmst.setInt(3, sc.nextInt());
			System.out.println("enter o_address");
			pmst.setString(4, sc.next());
			
			int i=pmst.executeUpdate();
			if(i>0) {
				System.out.println("insertion sucesss");
			}else {
				System.out.println("failed insertion");
			}
			conn.close();
			pmst.close();
		} catch (Exception e) {
			e.printStackTrace();		}

	}

	private static void CreateDatabase() {
		try {
			Class.forName(driver);
			String url="jdbc:mysql://localhost:3307/";
			conn=DriverManager.getConnection(url, uname, pword);
			System.out.println("Enter database name:");
			Scanner sc=new Scanner(System.in);
			String sql="create database "+sc.next();
			pmst=conn.prepareStatement(sql);
			int i=pmst.executeUpdate();
			if(i>0) {
				System.out.println("Database is created");
			}else {
				System.out.println("database is not created");
			}
			conn.close();
			pmst.close();
		} catch (Exception e) {
			e.printStackTrace();		}
	}
		

	

	private static void DisplayMenu() {
		System.out.println("\t1.Db Creation");
		System.out.println("\t2.Db Deletion");
		System.out.println("\t3.Data Insertion");
		System.out.println("\t4.Delete by id");
		System.out.println("\t5.update data");
		System.out.println("\t6.Get by id");
		System.out.println("\t7.get all");
		System.out.println("\t8.exit");
		

	}
}
