package library;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import library.Lb_Admin;

public class Lb_Login extends JFrame{
	private JButton btn1,btn2,btn3;
	private JTextField idField,idField2;
	private String driverName = "com.mysql.cj.jdbc.Driver";  
    private String dbURL = "jdbc:mysql://localhost:3306/qc?useSSL=false&serverTimezone=UTC";
	private	String Name="root";
	private	String Pwd="123";
	private	Connection c;
	private Statement s;
	public Lb_Login()
	{
		
		
	
		ImageIcon img1=new ImageIcon("src/image/登录背景.png");
		JLabel imageLabel1=new JLabel(img1);
		add(imageLabel1);
		imageLabel1.setBounds(0, 0, 420, 370);
		
		JLabel label1=new JLabel("登录");
		label1.setFont(new Font("黑体", Font.PLAIN, 24));
		label1.setBounds(175,50, 70, 70);
		imageLabel1.add(label1);
		
		JLabel label2=new JLabel("ReaderID:");
		label2.setFont(new Font("宋体", Font.PLAIN, 14));
		label2.setForeground(Color.white);
		label2.setBounds(70, 120, 70, 25);
		imageLabel1.add(label2);
		
		JLabel label4=new JLabel("AdminID:");
		label4.setFont(new Font("宋体", Font.PLAIN, 14));
		label4.setForeground(Color.white);
		label4.setBounds(70, 160, 70, 25);
		imageLabel1.add(label4);
		
		JLabel label3=new JLabel("<html>提示：如果你是管理员,请输入AdministratorID<br>   如果你是普通用户，请输入ReaderID<html>");
		label3.setFont(new Font("宋体", Font.ITALIC, 10));
		label3.setForeground(Color.white);
		label3.setBounds(70, 300, 250, 25);
		imageLabel1.add(label3);
		
		idField2=new JTextField();
		idField2.setBounds(150, 160, 150, 25);
		imageLabel1.add(idField2);
		idField2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(idField2.getText().length()!=0)
				{
					dispose();
					Lb_Admin frame2=new Lb_Admin();
				}
			}
		});
		
		idField=new JTextField();
		idField.setBounds(150, 120, 150, 25);
		imageLabel1.add(idField);
		idField.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
				 	Class.forName(driverName);
				 	c=DriverManager.getConnection(dbURL,Name,Pwd);
				 	System.out.println("登录成功");
				 	s=c.createStatement();
				 	String sql="select *from Reader where ReaderID='"+idField.getText()+"'";
				 	ResultSet rs=s.executeQuery(sql);
				 	 if(rs.next())
				 	 {
				 		 dispose(); 
				 		 Lb_Menu frame2=new Lb_Menu();
				 		 c.close();
				 		 s.close();
				 	 }
				 	 else {
						JOptionPane.showMessageDialog(null, "该用户不存在");
					}
				
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
				
			}
		});
		
		btn1=new JButton("登录");
		btn1.setBounds(125, 205, 150, 30);
		imageLabel1.add(btn1);
		btn1.addActionListener(new ActionListener() {//登录在数据库里查找信息
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(e.getSource()==btn1)
				{
					if (idField2.getText().length()==0)
					{
						try {
								Class.forName(driverName);
								c=DriverManager.getConnection(dbURL,Name,Pwd);
								System.out.println("登录成功");
								s=c.createStatement();
								String sql="select *from Reader where ReaderID='"+idField.getText()+"'";
								ResultSet rs=s.executeQuery(sql);
								if(rs.next())
								{
									dispose(); 
									Lb_Menu frame2=new Lb_Menu();
									c.close();
									s.close();
								}
								else {
									JOptionPane.showMessageDialog(null, "该用户不存在");
								}
						
						} catch (Exception e2) {
							// TODO: handle exception
							e2.printStackTrace();
						}
					}
					else//只要输入了东西 就进入管理员
					{ 
							dispose();
							Lb_Admin frame2=new Lb_Admin();
					}
				}
				
			}
		});
		
		btn2=new JButton("退出");
		btn2.setBounds(125, 245, 150, 30);
		btn2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(e.getSource()==btn2)
				{
					dispose();
				}
			}
		});
		
		imageLabel1.add(btn2);
	}

}
