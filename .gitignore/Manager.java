package UI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.lang.Class;
import java.sql.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import contents.DishManagement;
import contents.DishOrder;
import contents.MainTextField;
import contents.StuffManagement;
import contents.table;

import com.mysql.jdbc.Statement;

public class Manager {
	@SuppressWarnings("serial")
	public static class bench extends JFrame {
		private Connection database;
		private Statement sta;

		public bench(Connection con) throws SQLException, InterruptedException {
			setDatabase(con);
			setSta((Statement) getDatabase().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE));

			// 背景设置
			ImageIcon background = new ImageIcon("./IMG/main.png");
			JLabel label = new JLabel(background);
			label.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());
			JPanel image = (JPanel) getContentPane();
			bench ccc = this;
			getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
			image.setOpaque(false);
			image.setLayout(null);
			getLayeredPane().setLayout(null);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setLocation(260, 100);
			setSize(background.getIconWidth() + 5, background.getIconHeight() + 20);
			setResizable(false);
			setVisible(true);
			image.add(new MainTextField(getSta(), this));
			JButton aa = new JButton(), bb = new JButton(), cc = new JButton();
			aa.setBounds(324, 212, 141, 36);
			aa.setOpaque(false);
			aa.setBackground(new Color(0, 0, 255));
			aa.setBorderPainted(false);
			aa.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					try {
						new StuffManagement(sta);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			bb.setBounds(547, 212, 80, 38);
			bb.setOpaque(false);
			bb.setBackground(new Color(0, 0, 255));
			bb.setBorderPainted(false);
			bb.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					try {
						new DishOrder(sta, ccc);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			cc.setBounds(700, 212, 144, 38);
			cc.setOpaque(false);
			cc.setBackground(new Color(0, 0, 255));
			cc.setBorderPainted(false);
			cc.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					try {
						new DishManagement(sta);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			// 添加桌子
			add(aa);
			add(bb);
			add(cc);
			ResultSet tables = getSta().executeQuery("select * from tables");
			while (tables.next()) {
				int number = tables.getInt("tableid") - 1;
				int limit = tables.getInt("limit");
				String condition = tables.getString("condition");
				add(new table(limit, condition, number, this));
			}
			tables.close();
			setTitle("总经理，欢迎你");
			this.addWindowListener(new WindowListener() {

				@Override
				public void windowActivated(WindowEvent arg0) {
					// TODO Auto-generated method stub
					label.requestFocus();
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
		}

		public Connection getDatabase() {
			return database;
		}

		public void setDatabase(Connection database) {
			this.database = database;
		}

		public Statement getSta() {
			return sta;
		}

		public void setSta(Statement sta) {
			this.sta = sta;
		}
	}

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		try {
			// 加载MySql的驱动类
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "找不到驱动程序！", "大失败", JOptionPane.PLAIN_MESSAGE);
			return;
		}
		// Connection con =
		// DriverManager.getConnection("jdbc:mysql://localhost:3306/tavern?useSSL=false"
		// ,"root","123123") ;
		// Statement sta =
		// con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		// ResultSet a = sta.executeQuery("select * from tables");
		// a.last();
		// System.out.println(a.getRow() + "\n"
		// +a.getMetaData().getColumnCount());
		new Start();
	}
}

// 在搜索框里要实现的功能
// 1. 点菜 - DishOrder
// 2. 服务员信息（管理） - StuffManagement
// 3. 菜品信息（管理） - DishManagem
// 二和三都给做了
// 但是厨师信息真的和服务员信息一样，就不去管了
// 搜索框能搜索的东西
// ↑请参考桌面上的77889.txt
//
//
// Tip
// 这一堆简单的代码就不写注释了，注释比代码还麻烦
// 取消了点菜时匹配厨师的功能，因为加上那个的话需要数据库里的信息比较庞大（而我也嫌麻烦
// 考虑了一下在点菜的时候直接输菜名和从下拉菜单里面找的方便程度，果然直接输更方便一些（回想一下各种信息录入时候找“山东”的感觉吧
// 由于点菜的时候该系统不知道到底点菜的桌子是谁，所以投机取巧，点完菜直接重开一个系统
// 感觉非要在结账的时候知道客人的名字比较蠢，所以干脆不要了
// 服务员端只需要经理端的DishOrder（）方法和一个对桌子的update操作，比较简单，所以略去
// 本系统所有操作都是单次操作，即在获得结果之后立刻关闭访问（可能不需要再进行压力测试？
// 小类里面的main方法都是用来测试的，不用管了
// 测试用的数据库比较小，可能需要到时候加入一些“废信息”来演示
// 汉字标题的错误提示都是基本上不会发生的
//