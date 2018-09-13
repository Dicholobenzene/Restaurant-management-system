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

			// ��������
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
			// �������
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
			setTitle("�ܾ�����ӭ��");
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
			// ����MySql��������
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "�Ҳ�����������", "��ʧ��", JOptionPane.PLAIN_MESSAGE);
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

// ����������Ҫʵ�ֵĹ���
// 1. ��� - DishOrder
// 2. ����Ա��Ϣ������ - StuffManagement
// 3. ��Ʒ��Ϣ������ - DishManagem
// ��������������
// ���ǳ�ʦ��Ϣ��ĺͷ���Ա��Ϣһ�����Ͳ�ȥ����
// �������������Ķ���
// ����ο������ϵ�77889.txt
//
//
// Tip
// ��һ�Ѽ򵥵Ĵ���Ͳ�дע���ˣ�ע�ͱȴ��뻹�鷳
// ȡ���˵��ʱƥ���ʦ�Ĺ��ܣ���Ϊ�����Ǹ��Ļ���Ҫ���ݿ������Ϣ�Ƚ��Ӵ󣨶���Ҳ���鷳
// ������һ���ڵ�˵�ʱ��ֱ��������ʹ������˵������ҵķ���̶ȣ���Ȼֱ���������һЩ������һ�¸�����Ϣ¼��ʱ���ҡ�ɽ�����ĸо���
// ���ڵ�˵�ʱ���ϵͳ��֪�����׵�˵�������˭������Ͷ��ȡ�ɣ������ֱ���ؿ�һ��ϵͳ
// �о���Ҫ�ڽ��˵�ʱ��֪�����˵����ֱȽϴ������Ըɴ಻Ҫ��
// ����Ա��ֻ��Ҫ����˵�DishOrder����������һ�������ӵ�update�������Ƚϼ򵥣�������ȥ
// ��ϵͳ���в������ǵ��β��������ڻ�ý��֮�����̹رշ��ʣ����ܲ���Ҫ�ٽ���ѹ�����ԣ�
// С�������main���������������Եģ����ù���
// �����õ����ݿ�Ƚ�С��������Ҫ��ʱ�����һЩ������Ϣ������ʾ
// ���ֱ���Ĵ�����ʾ���ǻ����ϲ��ᷢ����
//