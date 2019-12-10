package library;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Lb_Admin extends JFrame {
	private String driverName = "com.mysql.cj.jdbc.Driver";  
    private String dbURL = "jdbc:mysql://localhost:3306/qc?useSSL=false&serverTimezone=UTC";
	private	String Name="root";
	private	String Pwd="123";
	private	Connection c;
	private Statement s;
	private JButton btn1,btn2,btn3;
	public void add_reader()
	{
		
		JFrame app=new JFrame("增加用户");
		app.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		app.setSize(300, 300);
		app.setVisible(true);
		app.setLocation(500, 300);
		Container frame4=app.getContentPane();
		frame4.setLayout(new FlowLayout());
		JTextField [] t= {  new JTextField("ReaderID:",12),new JTextField(12),
				new JTextField("FirstName:",12),new JTextField(12),
				 new JTextField("LastName:",12),new JTextField(12),
				 new JTextField("Address:",12),new JTextField(12),
				 new JTextField("PhoneNumber:",12),new JTextField(12),
				 new JTextField("Limits:",12),new JTextField(12)};
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
			        s.executeUpdate("insert into Reader(ReaderID,FirstName,LastName,Address,PhoneNumber,Limits)"
			        		+ "values('" +t[1].getText()  +"','" +t[3].getText()+ "','"+t[5].getText()+"','"+t[7].getText()+"','"+t[9].getText()+"','"+t[11].getText()+"')");
			        s.close( );
			        c.close( );
			        JOptionPane.showMessageDialog(null,"添加用户成功");        
			    }
			    catch (Exception m)
			    {
			        System.err.println("异常: " + m.getMessage( ));
			    } // try-catch结构结束
			}
		});
		for(int i=0;i<t.length;i++)
			frame4.add(t[i]);
		frame4.add(btn);
	}
	public void delete_reader() {
		JFrame app=new JFrame("删除用户");
		app.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		app.setSize(300, 100);
		app.setVisible(true);
		app.setLocation(500, 300);
		Container frame4=app.getContentPane();
		frame4.setLayout(new FlowLayout());
		JTextField [] t= {  new JTextField("ReaderID:",6),new JTextField(12)};
		for(int i=0;i<t.length;i=i+2)
			t[i].setEditable(false);
		JButton btn=new JButton("确认");
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try 
			    {
					Class.forName(driverName);
				 	c=DriverManager.getConnection(dbURL,Name,Pwd);
			        s=c.createStatement( ); 
			        s.executeUpdate(
							"delete from Reader where ReaderID='"+t[1].getText()+"'");
			        s.close( );
			        c.close( );
			        JOptionPane.showMessageDialog(null,"删除用户成功");        
			    }
			    catch (Exception m)
			    {
			        System.err.println("异常: " + m.getMessage( ));
			    } 
			}
		});
		for(int i=0;i<t.length;i++)
			frame4.add(t[i]);
		frame4.add(btn);
	}
	public void update_reader() {
		JFrame app=new JFrame("修改用户");
		app.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		app.setSize(300, 300);
		app.setVisible(true);
		app.setLocation(500, 300);
		Container frame4=app.getContentPane();
		frame4.setLayout(new FlowLayout());
		JTextField [] t= {  new JTextField("ReaderID:",12),new JTextField(12),
				new JTextField("FirstName:",12),new JTextField(12),
				 new JTextField("LastName:",12),new JTextField(12),
				 new JTextField("Address:",12),new JTextField(12),
				 new JTextField("PhoneNumber:",12),new JTextField(12),
				 new JTextField("Limits:",12),new JTextField(12)};
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
			        s.executeUpdate("update Reader set FirstName='"+t[3].getText()+"',LastName='"+t[5].getText()+"',Address='"+t[7].getText()+"',PhoneNumber='"+t[9].getText()+"',Limits='"+t[11].getText()+"'where ReaderID='"+t[1].getText()+"'");
			        s.close( );
			        c.close( );
			        JOptionPane.showMessageDialog(null,"修改用户成功");        
			    }
			    catch (Exception m)
			    {
			        System.err.println("异常: " + m.getMessage( ));
			    } 
			}
		});
		for(int i=0;i<t.length;i++)
			frame4.add(t[i]);
		frame4.add(btn);
	}
	public void add_book() {

		JFrame app=new JFrame("增加书籍");
		app.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		app.setSize(500, 400);
		app.setVisible(true);
		app.setLocation(500, 300);
		Container frame4=app.getContentPane();
		frame4.setLayout(new FlowLayout());
		JTextField [] t= {  new JTextField("ISBN:",12),new JTextField(12),
				new JTextField("Title:",12),new JTextField(12),
				 new JTextField("Authors:",12),new JTextField(12),
				 new JTextField("Publisher:",12),new JTextField(12),
				 new JTextField("EditionName:",12),new JTextField(12),
				 new JTextField("PublicationDate:",12),new JTextField(12),
				 new JTextField("Type:",12),new JTextField(12),
				 new JTextField("num:",12),new JTextField(12)};
		for(int i=0;i<t.length;i=i+2)
			t[i].setEditable(false);
		JButton btn=new JButton("确认");
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try 
			    {
					Class.forName(driverName);
				 	c=DriverManager.getConnection(dbURL,Name,Pwd);
			        s=c.createStatement( ); 
			        s.executeUpdate("insert into Books(ISBN,Title,Authors,Publisher,EditionName,PublicationDate,Type,num)"
			        		+ "values('" +t[1].getText()  +"','" +t[3].getText()+ "','"+t[5].getText()+"','"+t[7].getText()+"','"+t[9].getText()+"','"+t[11].getText()+"','"+t[13].getText()+"','"+t[15].getText()+"')");
			        s.close( );
			        c.close( );
			        JOptionPane.showMessageDialog(null,"添加书籍成功");        
			    }
			    catch (Exception m)
			    {
			        System.err.println("异常: " + m.getMessage( ));
			    } // try-catch结构结束
			}
		});
		for(int i=0;i<t.length;i++)
			frame4.add(t[i]);
		frame4.add(btn);
	}
	public void delete_book() {
		JFrame app=new JFrame("删除书籍");
		app.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		app.setSize(300, 100);
		app.setVisible(true);
		app.setLocation(500, 300);
		Container frame4=app.getContentPane();
		frame4.setLayout(new FlowLayout());
		JTextField [] t= {  new JTextField("ISBN:",6),new JTextField(12)};
		for(int i=0;i<t.length;i=i+2)
			t[i].setEditable(false);
		JButton btn=new JButton("确认");
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try 
			    {
					Class.forName(driverName);
				 	c=DriverManager.getConnection(dbURL,Name,Pwd);
			        s=c.createStatement( ); 
			        s.executeUpdate(
							"delete from Books where ISBN='"+t[1].getText()+"'");
			        s.close( );
			        c.close( );
			        JOptionPane.showMessageDialog(null,"删除书籍成功");        
			    }
			    catch (Exception m)
			    {
			        System.err.println("异常: " + m.getMessage( ));
			    } 
			}
		});
		for(int i=0;i<t.length;i++)
			frame4.add(t[i]);
		frame4.add(btn);
	}
	public void update_book() {
		JFrame app=new JFrame("修改书籍");
		app.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		app.setSize(500, 400);
		app.setVisible(true);
		app.setLocation(500, 300);
		Container frame4=app.getContentPane();
		frame4.setLayout(new FlowLayout());
		JTextField [] t= {  new JTextField("ISBN:",12),new JTextField(12),
				new JTextField("Title:",12),new JTextField(12),
				 new JTextField("Authors:",12),new JTextField(12),
				 new JTextField("Publisher:",12),new JTextField(12),
				 new JTextField("EditionName:",12),new JTextField(12),
				 new JTextField("PublicationDate:",12),new JTextField(12),
				 new JTextField("Type:",12),new JTextField(12),
				 new JTextField("num:",12),new JTextField(12)};
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
			        s.executeUpdate("update Books set Title='"+t[3].getText()+"',Authors='"+t[5].getText()+"',Publisher='"+t[7].getText()+"',EditionName='"+t[9].getText()+"',PublicationDate='"+t[11].getText()+"',Type='"+t[13].getText()+"'where ISBN='"+t[1].getText()+"'");
			        s.close( );
			        c.close( );
			        JOptionPane.showMessageDialog(null,"修改书籍成功");  
			        //todo删除时注意当前书本的借出情况
			    }
			    catch (Exception m)
			    {
			        System.err.println("异常: " + m.getMessage( ));
			    } 
			}
		});
		for(int i=0;i<t.length;i++)
			frame4.add(t[i]);
		frame4.add(btn);
	}
	public void set_book() {
		JFrame app=new JFrame("管理书籍");
		app.setLocation(500, 300);
		app.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		app.setSize(500, 400);
		app.setVisible(true);
		Container frame3=app.getContentPane();
		frame3.setBackground(Color.pink);
		JButton btn1,btn2,btn3,btn4;
		btn1=new JButton("增加书籍");
		btn1.setBounds(80, 90, 90, 30);
		frame3.add(btn1);
		btn1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				add_book();
			}
		});
		
		btn2=new JButton("修改书籍");
		btn2.setBounds(80, 150, 90, 30);
		frame3.add(btn2);
		btn2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				update_book();
			}
		});
		
		btn3=new JButton("删除书籍");
		btn3.setBounds(80, 210, 90, 30);
		frame3.add(btn3);
		frame3.setLayout(null);
		btn3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				delete_book();
			}
		});
		btn4=new JButton("查询所有书籍信息");
		btn4.setBounds(300, 110, 140, 90);
		frame3.add(btn4);
		btn4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				query_book();
			}
		});
	}
	public void query_book() {
		JFrame app=new JFrame("所有书籍查询");
		app.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		app.setSize(700,350);
		app.setLocation(500, 300);
		Container frame4=app.getContentPane();
		frame4.setLayout(new BorderLayout());
		try 
		{
			Class.forName(driverName);
			c=DriverManager.getConnection(dbURL,Name,Pwd);
			System.out.println("连接数据库成功");
			String sql="select *from Books";
		    s=c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE); 
		    ResultSet r=s.executeQuery(sql);  
		    r.last();
		    int i=r.getRow();
			if(i>0) 
			{
						
						Vector<Vector<String>> gdata=new Vector<Vector<String>>();
						Vector<String> gname=new Vector<String>();
						gname.add("ISBN");
						gname.add("Title");
						gname.add("Authors");
						gname.add("Publisher");
						gname.add("EditionName");
						gname.add("PublicationDate");
						gname.add("Type");
						r.beforeFirst();
						while(r.next())
						{
							Vector<String> gline=new Vector<String>();
							gline.add(r.getString("ISBN"));
							gline.add(r.getString("Title"));
							gline.add(r.getString("Authors"));
							gline.add(r.getString("Publisher"));
							gline.add(r.getString("EditionName"));
							gline.add(r.getString("PublicationDate"));
							gline.add(r.getString("Type"));
							gdata.add(gline);
						}
						DefaultTableModel m_data=new DefaultTableModel();
						m_data=new DefaultTableModel(gdata,gname);
						JTable m_view=new JTable(m_data);
						m_view.setPreferredScrollableViewportSize(new Dimension(700,300));
						m_view.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
						JScrollPane sPane=new JScrollPane(m_view);
						frame4.add(sPane);
					}
					else 
						JOptionPane.showMessageDialog(null, "无书籍信息");
		            s.close( );
		            c.close( );
		        }
		        catch (Exception m)
		        {
		            System.err.println("异常: " + m.getMessage( ));
		        } // try-catch结构结束
		app.setVisible(true);
	}
	public void query_reader() {
		JFrame app=new JFrame("所有用户查询");
		app.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		app.setSize(500,350);
		app.setLocation(500, 300);
		Container frame4=app.getContentPane();
		frame4.setLayout(new BorderLayout());
		try 
		{
			Class.forName(driverName);
			c=DriverManager.getConnection(dbURL,Name,Pwd);
			System.out.println("连接数据库成功");
			String sql="select *from Reader";
		    s=c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE); 
		    ResultSet r=s.executeQuery(sql);  
		    r.last();
		    int i=r.getRow();
			if(i>0) 
			{
						
						Vector<Vector<String>> gdata=new Vector<Vector<String>>();
						Vector<String> gname=new Vector<String>();
						gname.add("ReaderID");
						gname.add("FirstName");
						gname.add("LastName");
						gname.add("Address");
						gname.add("PhoneNumber");
						gname.add("Limits");
						r.beforeFirst();
						while(r.next())
						{
							Vector<String> gline=new Vector<String>();
							gline.add(r.getString("ReaderID"));
							gline.add(r.getString("FirstName"));
							gline.add(r.getString("LastName"));
							gline.add(r.getString("Address"));
							gline.add(r.getString("PhoneNumber"));
							gline.add(r.getString("Limits"));
							gdata.add(gline);
						}
						DefaultTableModel m_data=new DefaultTableModel();
						m_data=new DefaultTableModel(gdata,gname);
						JTable m_view=new JTable(m_data);
						m_view.setPreferredScrollableViewportSize(new Dimension(500,300));
						m_view.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
						JScrollPane sPane=new JScrollPane(m_view);
						frame4.add(sPane);
					}
					else 
						JOptionPane.showMessageDialog(null, "无学生信息");
		            s.close( );
		            c.close( );
		        }
		        catch (Exception m)
		        {
		            System.err.println("异常: " + m.getMessage( ));
		        } // try-catch结构结束
		app.setVisible(true);
	}
	public void set_reader() {
		JFrame app=new JFrame("管理用户");
		app.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		app.setSize(500, 400);
		app.setVisible(true);
		app.setLocation(500, 300);
		Container frame3=app.getContentPane();
		frame3.setBackground(Color.pink);
		JButton btn1,btn2,btn3,btn4;
		btn1=new JButton("增加用户");
		btn1.setBounds(80, 90, 90, 30);
		frame3.add(btn1);
		btn1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				add_reader();
			}
		});
		
		btn2=new JButton("修改用户");
		btn2.setBounds(80, 150, 90, 30);
		frame3.add(btn2);
		btn2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				update_reader();
			}
		});
		
		btn3=new JButton("删除用户");
		btn3.setBounds(80, 210, 90, 30);
		frame3.add(btn3);
		frame3.setLayout(null);
		btn3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				delete_reader();
			}
		});
		btn4=new JButton("查询所有用户信息");
		btn4.setBounds(300, 110, 140, 90);
		frame3.add(btn4);
		btn4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				query_reader();
			}
		});
	}
	public void query_information() {
		JFrame frame1=new JFrame("用户借阅信息查询");
		frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame1.setSize(300,150);
		frame1.setLocation(500, 300);
		Container C=frame1.getContentPane();
		C.setLayout(new FlowLayout());
		
		JTextField [] t= {  new JTextField("ReaderID:",6),new JTextField(12)
		};
		for(int i=0;i<t.length;i=i+2)
			t[i].setEditable(false);
		JButton btn=new JButton("确认");
		btn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try 
		        {
					Class.forName(driverName);
				 	c=DriverManager.getConnection(dbURL,Name,Pwd);
				 	System.out.println("连接数据库成功");
				 	String sql="select *from Record where ReaderID='"+t[1].getText()+"'";
		            s=c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE); 
		            ResultSet r=s.executeQuery(sql);  
		            r.last();
		           int i=r.getRow();
					if(i>0) 
					{
						JFrame frame2=new JFrame("查询结果");
						frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						frame2.setSize(700, 500);
						frame2.setVisible(true);
						frame2.setLocation(500,300);
						Container C1=frame1.getContentPane();
						C1.setLayout(new BorderLayout());
						
						
						Vector<Vector<String>> gdata=new Vector<Vector<String>>();
						Vector<String> gname=new Vector<String>();
						gname.add("RecordID");
						gname.add("ISBN");
						gname.add("ReaderID");
						gname.add("BorrwingDate");
						gname.add("ReturnDate");
						r.beforeFirst();
						while(r.next())
						{
							Vector<String> gline=new Vector<String>();
							gline.add(r.getString("ReaderID"));
							gline.add(r.getString("ISBN"));
							gline.add(r.getString("ReaderID"));
							gline.add(r.getString("BorrwingDate"));
							gline.add(r.getString("ReturnDate"));
							gdata.add(gline);
						}
						DefaultTableModel m_data=new DefaultTableModel();
						m_data=new DefaultTableModel(gdata,gname);
						JTable m_view=new JTable(m_data);
						m_view.setPreferredScrollableViewportSize(new Dimension(700,400));
						m_view.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
						JScrollPane sPane=new JScrollPane(m_view);
						frame2.add(sPane);
					}
					else 
						JOptionPane.showMessageDialog(null, "查找不到该用户的借阅记录");
		            s.close( );
		            c.close( );
		      
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
	public Lb_Admin()
	{
		this.setLocation(500, 300);
		this.setSize(700, 480);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setTitle("管理员系统");
		this.setLayout(new BorderLayout());
		ImageIcon img1=new ImageIcon("src/image/管理员.jpg");
		JLabel imageLabel1=new JLabel(img1);
		this.add(imageLabel1,BorderLayout.CENTER);
		
		
		btn1=new JButton("管理Reader");
		btn1.setBounds(80, 90, 120, 90);
		imageLabel1.add(btn1);
		btn1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				set_reader();
			}
		});
		btn2=new JButton("管理Book");
		btn2.setBounds(80, 210, 120, 90);
		imageLabel1.add(btn2);
		btn2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				set_book();
			
			}
		});
		btn3=new JButton("查询用户借阅信息");
		btn3.setBounds(500, 155, 140, 90);
		imageLabel1.add(btn3);
		btn3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				query_information();
			}
		});
		
	}
	
}
