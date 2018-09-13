package contents;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.Statement;

@SuppressWarnings("serial")
public class StuffManagement extends JFrame {
	int currentNumber = 1;

	public StuffManagement(Statement sta) throws SQLException {
		setSize(1400, 900);
		setLocation(260, 100);
		setTitle("员工管理");
		setResizable(false);
		setLayout(null);
		Container contentPane = getContentPane();
		JPanel field = new JPanel();
		field.setPreferredSize(new Dimension(1400, 3000));

		ResultSet stuff = sta.executeQuery("select * from waiter");
		while (stuff.next()) {
			contentPane.add(new stuffDel(getWidth() - 160, 42 + 16 * (currentNumber - 1), stuff.getInt(1), sta, this));
			contentPane.add(new stuffUpdate(getWidth() - 100, 42 + 16 * (currentNumber - 1), stuff.getInt(1),
					stuff.getString(2), stuff.getString(3), stuff.getString(4), stuff.getString(5), sta, this));
			currentNumber++;
		}
		;
		add(new stuffAdd(650, 700, sta, this));
		stuff.first();
		DBTable b = new DBTable(stuff);
		JTable c = new JTable(b.getdata(), b.getcolumnNames());
		c.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		JScrollPane scrollPane = new JScrollPane(c);
		scrollPane.setBounds(20, 20, getWidth() - 200, getHeight() - 300);
		;
		// scrollPane.setViewportBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));

		contentPane.add(scrollPane, BorderLayout.NORTH);
		setVisible(true);
		setSize(1399, 900);
	}

	public class stuffDel extends JButton {
		public stuffDel(int x, int y, int number, Statement sta, JFrame father) {
			setBounds(x, y, 60, 16);
			setText("辞退");
			setFont(new Font("微软雅黑", Font.ITALIC, 10));
			addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					int o = JOptionPane.showConfirmDialog(new JButton(), "是否确认辞退");
					if (o == 0) {
						try {
							sta.execute("delete from waiter where stuffid =" + number);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						father.dispose();

						try {
							StuffManagement p = new StuffManagement(sta);
							JOptionPane.showMessageDialog(null, "删除成功！", "SUCCESS", JOptionPane.PLAIN_MESSAGE);
							p.requestFocus();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			});
		}
	}

	public class stuffUpdate extends JButton {
		public stuffUpdate(int x, int y, int number, String name, String worktime, String salary, String sex,
				Statement sta, JFrame father) {
			setBounds(x, y, 60, 16);
			setText("更改");
			setFont(new Font("微软雅黑", Font.ITALIC, 10));
			addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					JFrame update = new JFrame("信息更改");
					update.setSize(600, 350);
					update.setLayout(null);
					update.setLocation(500, 400);
					update.setTitle("更改信息");
					TipTextField a = new TipTextField(name, 0);
					TipTextField b = new TipTextField(worktime, 1);
					TipTextField c = new TipTextField(salary, 1);
					JComboBox<String> d = new JComboBox<String>();
					d.addItem("男");
					d.addItem("女");
					a.setBounds(20, 20, 250, 100);
					b.setBounds(20, 150, 250, 100);
					c.setBounds(300, 20, 250, 100);
					d.setBounds(300, 150, 250, 100);

					JButton confirm = new JButton("确认修改");
					confirm.setBounds(250, 270, 100, 40);

					update.add(a);
					update.add(b);
					update.add(c);
					update.add(d);
					update.add(confirm);
					confirm.requestFocus();
					confirm.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							String f = a.getText();
							String g = b.getText();
							String h = c.getText();
							String i = (String) d.getSelectedItem();
							try {
								sta.execute("update waiter set name = '" + f + "' , worktime =" + g + ", salary = " + h
										+ ", sex = '" + i + "' where stuffid = " + number);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							update.dispose();
							father.dispose();
							try {
								StuffManagement p = new StuffManagement(sta);
								JOptionPane.showMessageDialog(null, "更改成功！", "SUCCESS", JOptionPane.PLAIN_MESSAGE);
								p.requestFocus();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}

					});
					update.addWindowListener(new WindowListener() {

						@Override
						public void windowActivated(WindowEvent arg0) {
							// TODO Auto-generated method stub
							confirm.requestFocus();
						}

						@Override
						public void windowClosed(WindowEvent arg0) {
							// TODO Auto-generated method stub

						}

						@Override
						public void windowClosing(WindowEvent arg0) {
							// TODO Auto-generated method stub

						}

						@Override
						public void windowDeactivated(WindowEvent arg0) {
							// TODO Auto-generated method stub

						}

						@Override
						public void windowDeiconified(WindowEvent arg0) {
							// TODO Auto-generated method stub

						}

						@Override
						public void windowIconified(WindowEvent arg0) {
							// TODO Auto-generated method stub

						}

						@Override
						public void windowOpened(WindowEvent arg0) {
							// TODO Auto-generated method stub

						}
					});
					update.setVisible(true);
				}

			});

		}
	}

	public class stuffAdd extends JButton {
		public stuffAdd(int x, int y, Statement sta, JFrame father) {
			setBounds(x, y, 100, 40);
			setText("添加员工");
			addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					JFrame update = new JFrame("信息更改");
					update.setSize(600, 350);
					update.setLayout(null);
					update.setLocation(500, 400);
					update.setTitle("录入信息");
					TipTextField a = new TipTextField("请输入姓名", 1);
					TipTextField b = new TipTextField("请输入每日工作时长", 1);
					TipTextField c = new TipTextField("请输入工资", 1);
					TipTextField d = new TipTextField("请输入性别", 1);
					a.setBounds(20, 20, 250, 100);
					b.setBounds(20, 150, 250, 100);
					c.setBounds(300, 20, 250, 100);
					d.setBounds(300, 150, 250, 100);

					JButton confirm = new JButton("确认");
					confirm.setBounds(250, 270, 100, 40);

					update.add(a);
					update.add(b);
					update.add(c);
					update.add(d);
					update.add(confirm);
					confirm.requestFocus();
					confirm.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							String f = a.getText();
							String g = b.getText();
							String h = c.getText();
							String i = d.getText();
							try {
								sta.execute("insert into waiter values ( " + currentNumber + ",'" + f + "'," + g + ","
										+ h + ",'" + i + "')");
								currentNumber++;
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							update.dispose();
							father.dispose();
							try {
								StuffManagement p = new StuffManagement(sta);
								JOptionPane.showMessageDialog(null, "录入成功！", "SUCCESS", JOptionPane.PLAIN_MESSAGE);
								p.requestFocus();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}

					});
					update.addWindowListener(new WindowListener() {

						@Override
						public void windowActivated(WindowEvent arg0) {
							// TODO Auto-generated method stub
							confirm.requestFocus();
						}

						@Override
						public void windowClosed(WindowEvent arg0) {
							// TODO Auto-generated method stub

						}

						@Override
						public void windowClosing(WindowEvent arg0) {
							// TODO Auto-generated method stub

						}

						@Override
						public void windowDeactivated(WindowEvent arg0) {
							// TODO Auto-generated method stub

						}

						@Override
						public void windowDeiconified(WindowEvent arg0) {
							// TODO Auto-generated method stub

						}

						@Override
						public void windowIconified(WindowEvent arg0) {
							// TODO Auto-generated method stub

						}

						@Override
						public void windowOpened(WindowEvent arg0) {
							// TODO Auto-generated method stub

						}
					});
					update.setVisible(true);
				}

			});
		}
	}

	// public static void main(String[] args) throws SQLException,
	// ClassNotFoundException{
	// JFrame c = new JFrame();
	// c.setVisible(true);
	// c.setSize(1400, 900);
	// Container contentPane=c.getContentPane();
	// JPanel scrollPane = new JPanel();
	// scrollPane.setPreferredSize(new Dimension(10000,2000));
	// scrollPane.setBounds(0, 0, 1000, 800);
	// JButton[] b = new JButton[20];
	// for(int i =0 ; i<20 ; i++){
	// b[i] = new JButton();
	// b[i].setBounds(0,i*200,10000,200);
	// scrollPane.add(b[i]);
	// b[i].setText("DJ!DJ!");
	// }
	// JScrollPane e = new JScrollPane(scrollPane);
	// contentPane.add(e,BorderLayout.CENTER);
	// c.setVisible(true);
	// try{
	// //加载MySql的驱动类
	// Class.forName("com.mysql.jdbc.Driver") ;
	// }catch(ClassNotFoundException e){
	// System.out.println("找不到驱动程序类 ，加载驱动失败！");
	// e.printStackTrace() ;
	// }
	// Connection con =
	// DriverManager.getConnection("jdbc:mysql://localhost:3306/tavern?useSSL=false"
	// ,"root","123123") ;
	// Statement sta = (Statement)
	// con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
	// new StuffManagement(sta);
	// }
	//

}
