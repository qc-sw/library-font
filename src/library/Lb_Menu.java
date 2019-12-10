package library;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Console;
import java.sql.Connection;
import java.util.Calendar;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;
public class Lb_Menu extends JFrame
{
	private String driverName = "com.mysql.cj.jdbc.Driver";  
    private String dbURL = "jdbc:mysql://localhost:3306/qc?useSSL=false&serverTimezone=UTC";
	private	String Name="root";
	private	String Pwd="123";
	private	Connection c;
	private Statement s;
	JButton btn1,btn2,btn3,btn4;
	public static String getRandomString(int length){//获取随机字符串的函数  借书时候用到
	     String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	     Random random=new Random();
	     StringBuffer sb=new StringBuffer();
	     for(int i=0;i<length;i++){
	       int number=random.nextInt(62);
	       sb.append(str.charAt(number));
	     }
	     return sb.toString();
	}
	public boolean someoneisborrowed(String s1,String s2)//检查一下有没有该用户有没有借某书
	{
		boolean is=false;
		try 
        {
			Class.forName(driverName);
		 	c=DriverManager.getConnection(dbURL,Name,Pwd);
		 	String sql="select *from Record where ISBN='"+s1+"' and ReaderID='"+s2+"'";
            s=c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE); 
            ResultSet r=s.executeQuery(sql);  
            r.beforeFirst();
			r.next();
			System.out.println(r.getString("ReaderID"));
			System.out.println(s2);
			System.out.println(r.getString("ReaderID").equals(s2));
			if(r.getString("ReaderID").equals(s2))
			{
				
				s.close( );
				c.close( );
				is=true;
			}
		
        }
        catch (Exception m)
        {	
        	System.err.println("异常: " + m.getMessage( ));
        
        } // try-catch结构结束
		return is;
	}
	public static String getBorrwDate() {//获取当前时间 且转化为字符串  主要负责借书时间
			Date currentTime =new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateString = formatter.format(currentTime);
			return dateString;
	}
	public static String getreturnDate() {//获取当前时间 且转化为字符串  主要负责还书时间     借阅时间长度为1周
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar nowTime = Calendar.getInstance();
		nowTime.add(Calendar.MINUTE, 10080);//要增加什么，在这里写
		String dateString=formatter.format(nowTime.getTime());
		return dateString;
}
	
//	public void is_limited(String s1,String s2) {
//		try 
//        {
//			Class.forName(driverName);
//		 	c=DriverManager.getConnection(dbURL,Name,Pwd);
//		 	System.out.println("连接数据库成功");
//		 	String sql1="select * from Record where ReaderID='"+s1+"'";
//            s=c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE); 
//            ResultSet r1=s.executeQuery(sql1);  
//            r1.last();
//            int i=r1.getRow();
//            System.out.println("用户有"+i+"条记录");
//            s.close();
//            String sql2="select *from Reader where ReaderID='"+s1+"'";
//            String sql3="select *from books where ISBN='"+s2+"'";
//            s=c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE); 
//            ResultSet r2=s.executeQuery(sql2);
//            r2.last();
//        	String j=r2.getString("Limits");
//        	ResultSet r3=s.executeQuery(sql3);
//            r3.last();
//        	String j1=r3.getString("num");
//        	String j2=r3.getString("storage");
//        	int newj2 = Integer.parseInt(j2)-1;
//        	String demo=Integer.toString(newj2);
//            System.out.println("该书共有"+j1+"本"+"  馆藏数量"+j2);
//            System.out.println("用户最多可借"+j+"本书");
//            int k=Integer.parseInt(j);
//            if(Integer.parseInt(j2)==0) {
//            	s.close();
//				JOptionPane.showMessageDialog(null, "馆藏已全部出借！");
//            }else
//            if(i<k)
//            {
//            	k=k-1;
//            	String s3=Integer.toString(k);
//            	s.close();
//            	String ss="借出中";
//           	 	s=c.createStatement( ); 
//           	 	s.executeUpdate(
//	            	"update books set storage= '"+demo+"'where ISBN='"+s2+"'");
//           	    s.executeUpdate(
// 	            	"update reader set Limits= '"+s3+"'where ReaderID='"+s1+"'");
//           	 	s.executeUpdate(
//        			 " insert "+ 
//        			 " into Record(RecordID,ISBN,ReaderID,BorrwingDate,ReturnDate,status) "+//设置借阅信息
//        			 " values('"+getRandomString(6)+"','"+s2+"','"+s1+"','"+getBorrwDate()+"','"+getreturnDate()+"','"+ss+"')");
//           	 s.executeUpdate(
//        			 " insert "+ 
//        			 " into history(RecordID,ISBN,ReaderID,BorrwingDate,ReturnDate,status) "+//设置借阅信息
//        			 " values('"+getRandomString(6)+"','"+s2+"','"+s1+"','"+getBorrwDate()+"','"+getreturnDate()+"','"+ss+"')");
//           	 	s.close( );
//           	 	c.close( );
//           	 	
//            	JOptionPane.showMessageDialog(null, "借阅成功,请在一周后还书");
//            }
//            else {
//            	s.close();
//				JOptionPane.showMessageDialog(null, "已超过您的最大借阅数量!");
//			}
//        }
//        catch (Exception m)
//        {
//        	System.err.println("异常: " + m.getMessage( ));
//        } // try-catch结构结束
//	}
	public void is_borrowed(String s1,String s2)
	{
        try 
        {
        	Class.forName(driverName);
		 	c=DriverManager.getConnection(dbURL,Name,Pwd);
		 	System.out.println("连接数据库成功");
		 	String sql1="select * from Record where ReaderID='"+s1+"'";
            s=c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE); 
            ResultSet r1=s.executeQuery(sql1);  
            r1.last();
            int i=r1.getRow();
            System.out.println("用户有"+i+"条记录");
            s.close();
            String sql2="select *from Reader where ReaderID='"+s1+"'";
            String sql3="select *from books where ISBN='"+s2+"'";
            s=c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE); 
            ResultSet r2=s.executeQuery(sql2);
            r2.last();
        	String j=r2.getString("Limits");
        	ResultSet r3=s.executeQuery(sql3);
            r3.last();
        	String j1=r3.getString("num");
        	String j2=r3.getString("storage");
        	int newj2 = Integer.parseInt(j2)-1;
        	String demo=Integer.toString(newj2);
            System.out.println("该书共有 "+j1+" 本，"+"馆藏数量 "+j2);
            System.out.println("用户当前最多可借 "+j+" 本书");
            int k=Integer.parseInt(j);
            if(k==0) {
//            	s.close();
            	System.out.println("已超过用户的最大借阅数量!");
				JOptionPane.showMessageDialog(null, "已超过您的最大借阅数量!");
            }
            else
            if(Integer.parseInt(j2)==0) {
            	s.close();
//				JOptionPane.showMessageDialog(null, "   馆藏已全部出借！\n");
				showmsg(s1,s2);
            }
            else
            if(i<k)
            {
            	k=k-1;
            	String s3=Integer.toString(k);
            	s.close();
            	String ss="出借";
           	 	s=c.createStatement( ); 
           	 	s.executeUpdate(
	            	"update books set storage= '"+demo+"'where ISBN='"+s2+"'");
           	    s.executeUpdate(
 	            	"update reader set Limits= '"+s3+"'where ReaderID='"+s1+"'");
           	 	s.executeUpdate(
        			 " insert "+ 
        			 " into Record(RecordID,ISBN,ReaderID,BorrwingDate,ReturnDate,status) "+//设置借阅信息
        			 " values('"+getRandomString(6)+"','"+s2+"','"+s1+"','"+getBorrwDate()+"','"+getreturnDate()+"','"+ss+"')");
           	 s.executeUpdate(
        			 " insert "+ 
        			 " into history(ISBN,ReaderID,BorrwingDate,status) "+//设置借阅信息
        			 " values('"+s2+"','"+s1+"','"+getBorrwDate()+"','"+ss+"')");
           	 	s.close( );
           	 	c.close( );
           	 	
            	JOptionPane.showMessageDialog(null, "借阅成功,请在一周后还书");
            }
        }
        catch (Exception m)
        {
        	System.err.println("异常: " + m.getMessage( ));
        } // try-catch结构结束
	}
	public void showmsg(String s1,String s2) {
		
		try {
			
			Class.forName(driverName);
		 	c=DriverManager.getConnection(dbURL,Name,Pwd);
		 	System.out.println("连接数据库成功");
		 	String sql="select *from Record where ISBN='"+s2+"'";
            s=c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE); 
            ResultSet r=s.executeQuery(sql);  
            r.beforeFirst();
            String myString="   馆藏已全部出借！\n";
			while(r.next()) 
			{
					String date,reader;
					date=r.getString("ReturnDate");
					reader=r.getString("ReaderID");	
					myString+="该书已被"+reader+"借出,归还时间:"+date+"\n";
			}
            s.close( );
			c.close( );
            JOptionPane.showMessageDialog(null, myString);
		} catch (Exception m) {
			System.err.println("异常: " + m.getMessage( ));
		}
		
	 	
        
	}
	public void book_borrow()
	{
		JFrame frame1=new JFrame("借阅书籍");
		frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame1.setSize(300, 200);
		frame1.setLocation(500, 300);
		Container C=frame1.getContentPane();
		C.setLayout(new FlowLayout());
		JTextField [] t= {  new JTextField("ReaderID:",10),new JTextField(12),
							new JTextField("ISBN:",10),new JTextField(12),
		};
//		JLabel labelbr=new JLabel("<html><body>sd<br/></body></html>");
//		labelbr.setText("<html><body><p align=\"center\">&nbsq;&nbsq;&nbsq;&nbsq;</p></body</html>");
		for(int i=0;i<t.length;i=i+2)
			t[i].setEditable(false);
		JButton btn=new JButton("确认");
		Dimension preferredSize = new Dimension(100,30);//设置尺寸
		  btn.setPreferredSize(preferredSize );
		btn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String s1=t[1].getText();
				String s2=t[3].getText();
				is_borrowed(s1,s2);
			}});
		
		for(int i=0;i<4;i++)
		{
			C.add(t[i]);
//			if(i==1||i==3) {
//				C.add(labelbr);
//			}
		}
		C.add(btn);
		frame1.setVisible(true);
	}
	public void book_query()
	{
		JFrame frame1=new JFrame("书籍查询");
		frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame1.setSize(300,350);
		frame1.setLocation(500, 300);
		Container C=frame1.getContentPane();
		C.setLayout(new FlowLayout());
		
		JTextField [] t= {  new JTextField("ISBN：",10),new JTextField(12),
							new JTextField("书名：",10),new JTextField(12),
							new JTextField("作者：",10),new JTextField(12),
							new JTextField("出版社：",10),new JTextField(12),
							new JTextField("发布日期：",10),new JTextField(12),
							new JTextField("类别：",10),new JTextField(12)
		};
		for(int i=0;i<t.length;i=i+2)
			t[i].setEditable(false);
		String S;
		JButton btn=new JButton("确认");
		Dimension preferredSize = new Dimension(100,30);//设置尺寸
		  btn.setPreferredSize(preferredSize );
		btn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try 
		        {
					Class.forName(driverName);
				 	c=DriverManager.getConnection(dbURL,Name,Pwd);
				 	System.out.println("连接数据库成功");
//				 	Vector<String,String> a=new Vector<String,String>();
//				 	for(int i=1;i<11;i+=2) {
//				 		if(t[i].getText()!=null) {
//				 			a.add(t[i].getText());
//				 		}
//				 	}
//				 	for(int i=0;i<a.capacity();i++)
//				 	{
//				 		
//				 	}
//				 	for(int i=0;i<11;i+=2) {
//				 		if(i==1||t[i]!=null) sql+="or ISBN='"+t[i].getText();
//				 		else if(i==3||t[i]!=null) sql+="or Title='"+t[i].getText();
//				 		else if(i==5||t[i]!=null) sql+="or Author='"+t[i].getText();
//				 		else if(i==7||t[i]!=null) sql+="or Publisher='"+t[i].getText();
//				 		else if(i==9||t[i]!=null) sql+="or PublicationDate='"+t[i].getText();
//				 		else if(i==11||t[i]!=null) sql+="or Type'"+t[i].getText();
//				 	}
				 	
				 	String sql="select *from Books where ISBN='"+t[1].getText()+"'or Title='"+t[3].getText()+"'or Authors='"+t[5].getText()+"'or Publisher='"+t[7].getText()+
				 			"'or PublicationDate='"+t[9].getText()+"'or Type='"+t[11].getText()+"'";
		            s=c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE); 
		            ResultSet r=s.executeQuery(sql);  
		            r.last();
		           int i=r.getRow();
					System.out.print(i);
					if(i>0) 
					{
						JFrame frame2=new JFrame("查询结果");
						frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						frame2.setSize(500, 500);
						frame2.setVisible(true);
						frame2.setLocation(500,300);
						Container C1=frame1.getContentPane();
						C1.setLayout(new BorderLayout());
						
						
						Vector<Vector<String>> gdata=new Vector<Vector<String>>();
						Vector<String> gname=new Vector<String>();
						gname.add("ISBN");
						gname.add("书名");
						gname.add("作者");
						gname.add("出版社");
						gname.add("馆藏数");
						gname.add("可借阅数");
						r.beforeFirst();
						while(r.next())
						{
							Vector<String> gline=new Vector<String>();
							gline.add(r.getString("ISBN"));
							gline.add(r.getString("Title"));
							gline.add(r.getString("Authors"));
							gline.add(r.getString("Publisher"));
							gline.add(r.getString("num"));
							gline.add(r.getString("storage"));
							gdata.add(gline);
						}
						DefaultTableModel m_data=new DefaultTableModel();
						m_data=new DefaultTableModel(gdata,gname);
						JTable m_view=new JTable(m_data);
						m_view.setPreferredScrollableViewportSize(new Dimension(450,400));
						m_view.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
						JScrollPane sPane=new JScrollPane(m_view);
						frame2.add(sPane);
					}
					else 
						JOptionPane.showMessageDialog(null, "查找不到该书籍");
		            s.close( );
		            c.close( );
		            System.out.println("查找书籍成功");
		        }
		        catch (Exception m)
		        {
		            System.err.println("异常: " + m.getMessage( ));
		        } // try-catch结构结束
			}
		});
		for(int i=0;i<t.length;i++)
			C.add(t[i]);
		C.add(btn);
		frame1.setVisible(true);
	}
	public void book_return()
	{
		JFrame frame1=new JFrame("返还书籍");
		frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame1.setSize(300, 200);
		frame1.setLocation(500, 300);
		Container C=frame1.getContentPane();
		C.setLayout(new FlowLayout());
		JTextField [] t= {  new JTextField("ISBN:",10),new JTextField(12),
							new JTextField("ReaderID",10),new JTextField(12)
		};
		for(int i=0;i<t.length;i=i+2)
			t[i].setEditable(false);
		JButton btn=new JButton("确认");
		Dimension preferredSize = new Dimension(100,30);//设置尺寸
		  btn.setPreferredSize(preferredSize );
		btn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String s1=t[1].getText();
				String s2=t[3].getText();
				if(!someoneisborrowed(s1, s2))
					 JOptionPane.showMessageDialog(null,"该用户未借阅此书");
				else
				{
					try 
			        {
						Class.forName(driverName);
					 	c=DriverManager.getConnection(dbURL,Name,Pwd);
					    s=c.createStatement();
					    String ss="归还";
					    String sql2="select *from Reader where ReaderID='"+s2+"'";
					    String sql3="select * from books where ISBN='"+s1+"'";
			            s=c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE); 
			            ResultSet r2=s.executeQuery(sql2);
			            r2.last();
			            String j=r2.getString("Limits");
			            ResultSet r3=s.executeQuery(sql3);
			            r3.last();
			            String jj=r3.getString("storage");
			        	int kk=Integer.parseInt(jj);
			        	int k=Integer.parseInt(j);
			        	kk=kk+1;
			        	String s4=Integer.toString(kk);
			        	k=k+1;
			        	String s3=Integer.toString(k);
		            	//todotodo
//			        	s.close();
		           	 	s=c.createStatement( ); 
//		           	 	s.executeUpdate(
//			            	"update Reader set Limits= '"+s3+"'where ReaderID='"+s2+"'");
		           	 	s.executeUpdate(
		           	 		"update books set storage= '"+s4+"'where ISBN='"+s1+"'");
						JOptionPane.showMessageDialog(null, "还书成功！");
						s.executeUpdate(
									"delete from Record where ISBN='"+s1+"'and ReaderID='"+s2+"'");//还书删除记录   以及增加用户的可借阅数量
						s.executeUpdate("update reader set Limits='"+s3+"' where ReaderID='"+s2+"'");
						s.executeUpdate(
				        			 " insert "+ 
				        			 " into history(ISBN,ReaderID,ReturnDate,status) "+//设置借阅信息
				        			 " values('"+s1+"','"+s2+"','"+getBorrwDate()+"','"+ss+"')");
						s.close();
			        }
			        catch (Exception m)
			        {
			            System.err.println("异常: " + m.getMessage( ));
			        } // try-catch结构结束
				}
			}});
		
		for(int i=0;i<4;i++)
			C.add(t[i]);
		C.add(btn);
		frame1.setVisible(true);
	}
	public void reader_information() {
		JFrame app=new JFrame("用户信息修改");
		app.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		app.setSize(300, 300);
		app.setVisible(true);
		app.setLocation(500, 300);
		Container newframe=app.getContentPane();
		newframe.setLayout(new FlowLayout());
		JTextField [] t= {  new JTextField("ReaderID:",12),new JTextField(16),
				new JTextField("FirstName:",12),new JTextField(16),
				 new JTextField("LastName:",12),new JTextField(16),
				 new JTextField("Address:",12),new JTextField(16),
				 new JTextField("PhoneNumber:",12),new JTextField(16)};
		for(int i=0;i<t.length;i=i+2)
			t[i].setEditable(false);
		JButton btn=new JButton("确认");
		Dimension preferredSize = new Dimension(100,30);//设置尺寸
		  btn.setPreferredSize(preferredSize );
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try 
			    {
					Class.forName(driverName);
				 	c=DriverManager.getConnection(dbURL,Name,Pwd);
			        s=c.createStatement( ); 
			        s.executeUpdate("update Reader set FirstName='"+t[3].getText()+"',LastName='"+t[5].getText()+"',Address='"+t[7].getText()+"',PhoneNumber='"+t[9].getText()+"'where ReaderID='"+t[1].getText()+"'");
			        s.close( );
			        c.close( );
			        JOptionPane.showMessageDialog(null,"设置信息成功");        
			    }
			    catch (Exception m)
			    {
			        System.err.println("异常: " + m.getMessage( ));
			    } // try-catch结构结束
			}
		});
		for(int i=0;i<t.length;i++)	
			newframe.add(t[i]);
		newframe.add(btn);
	}
	public Lb_Menu()
	{
		this.setLocation(500, 300);
		this.setSize(750, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setTitle("图书管理系统");
		this.setLayout(new BorderLayout());
		ImageIcon img1=new ImageIcon("src/image/图书馆.jpg");
		JLabel imageLabel1=new JLabel(img1);
		this.add(imageLabel1,BorderLayout.CENTER);
		
		btn1=new JButton("书籍查询");
		btn1.setBounds(375, 100, 120, 30);
		imageLabel1.add(btn1);
		btn1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				book_query();
			}
		});
		
		
		btn2=new JButton("借书");
		btn2.setBounds(375, 160, 120, 30);
		imageLabel1.add(btn2);
		btn2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub	
				book_borrow();
			}
		});
		
		btn3=new JButton("还书");
		btn3.setBounds(520, 160, 120, 30);
		imageLabel1.add(btn3);
		btn3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				book_return();
			}
		});
		btn4=new JButton("用户信息录入");
		btn4.setBounds(520, 100, 120, 30);
		imageLabel1.add(btn4);
		btn4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				reader_information();
			}
		});
	}
}
