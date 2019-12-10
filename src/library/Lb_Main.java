package library;
import java.sql.*;
import javax.swing.*;
public class Lb_Main {
	private String driverName = "com.mysql.jdbc.Driver";  
    private String dbURL = "jdbc:mysql://localhost:3306/qc?useSSL=false&serverTimezone=UTC";
    private String Name = "root";
    private String Pwd = "123";
	private	Connection c;
	private Statement s;
	public void UserLink(){
		try 
		{
        	Class.forName(driverName);
           	c=DriverManager.getConnection(dbURL,Name,Pwd);
            s=c.createStatement( ); 
            s.execute("create table Reader(ReaderID char(20),FirstName char(20),LastName char(20),Address char(20),PhoneNumber char(20),Limits char(20))");
            s.close( );
            c.close( );
            System.out.println("新建用户列表");
        }
        catch (Exception m)
        {
            System.err.println("异常: " + m.getMessage( ));
        } // try-catch结构结束
		
	}
	public void BookLink(){
		try 
        {
			Class.forName(driverName);
           	c=DriverManager.getConnection(dbURL,Name,Pwd);
            s=c.createStatement( ); 
            s.execute("create table Books(ISBN char(20),Title char(20),Authors char(20),Publisher char(20),EditionName char(20),PublicationDate char(20), Type char(20))");
            s.close( );
            c.close( );
            System.out.println("新建书籍列表");
        }
        catch (Exception m)
        {
            System.err.println("异常: " + m.getMessage( ));
        } // try-catch结构结束
	}
	
	public void BandRLink()
	{
		try 
        {
			Class.forName(driverName);
           	c=DriverManager.getConnection(dbURL,Name,Pwd);
            s=c.createStatement( ); 
            s.execute("create table Record(RecordID char(20),ISBN char(20),ReaderID char(20),BorrwingDate char(20),ReturnDate char(20))");
            s.close( );
            c.close( );
            System.out.println("新建记录列表");
        }
        catch (Exception m)
        {
            System.err.println("异常: " + m.getMessage( ));
        } // try-catch结构结束
	}

	public static void main(String arg[])
	{
	/*	Lb_Main a=new Lb_Main();
		a.UserLink();
		a.BookLink();
		a.BandRLink();//创建三个表
		*/Lb_Login frame1=new Lb_Login();
		frame1.setUndecorated(true);
		frame1.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		frame1.setLocation(500, 300);
		frame1.setTitle("图书管管理系统登录界面");
		frame1.setSize(420, 370);
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.setVisible(true);
	}

}
