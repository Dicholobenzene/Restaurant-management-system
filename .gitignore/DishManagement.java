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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.Statement;

@SuppressWarnings("serial")
public class DishManagement extends JFrame {
	int currentNumber = 1;

	public DishManagement(Statement sta) throws SQLException {
		setSize(1400, 900);
		setLocation(260, 100);
		setTitle("��Ʒ����");
		setResizable(false);
		setLayout(null);
		Container contentPane = getContentPane();
		JPanel field = new JPanel();
		field.setPreferredSize(new Dimension(1400, 3000));

		ResultSet stuff = sta.executeQuery("select * from dishes");
		while (stuff.next()) {
			contentPane.add(new stuffDel(getWidth() - 180, 42 + 16 * (currentNumber - 1), stuff.getInt(1), sta, this));
			contentPane.add(new stuffUpdate(getWidth() - 120, 42 + 16 * (currentNumber - 1), stuff.getInt(1),
					stuff.getString(2), stuff.getString(3), stuff.getString(4), stuff.getString(5), stuff.getString(6),
					stuff.getString(7), stuff.getString(8), stuff.getString(9), stuff.getString(10), sta, this));
			contentPane.add(new stuffPic(getWidth() - 60, 42 + 16 * (currentNumber - 1), stuff.getString(2)));
			currentNumber++;
		}
		;
		add(new stuffAdd(650, 700, sta, this));
		stuff.first();
		DBTable b = new DBTable(stuff);
		JTable c = new JTable(b.getdata(), b.getcolumnNames());
		c.setFont(new Font("΢���ź�", Font.PLAIN, 15));
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
			setText("ɾ��");
			setFont(new Font("΢���ź�", Font.ITALIC, 10));
			addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					int o = JOptionPane.showConfirmDialog(null, "�Ƿ�ȷ��ɾ����Ʒ");
					if (o == 0) {
						try {
							sta.execute("delete from dishes where dishid =" + number);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, "�п��˵�������ˣ��޷�ɾ����", "FAIL", JOptionPane.PLAIN_MESSAGE);
							return;
						}
						father.dispose();
						try {
							DishManagement p1 = new DishManagement(sta);
							JOptionPane.showMessageDialog(null, "ɾ���ɹ���", "SUCCESS", JOptionPane.PLAIN_MESSAGE);
							p1.requestFocus();
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
		public stuffUpdate(int x, int y, int number, String name, String price, String salty, String spicy,
				String sweet, String temperture, String makingtime, String profit, String kind, Statement sta,
				JFrame father) {
			setBounds(x, y, 60, 16);
			setText("����");
			setFont(new Font("΢���ź�", Font.ITALIC, 10));
			addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					JFrame update = new JFrame("��Ϣ����");
					update.setSize(600, 350);
					update.setLayout(null);
					update.setLocation(500, 400);
					update.setTitle("������Ϣ");
					String[] Salty = { "�嵭", "ƫ��", "����", "ƫ��", "����" };
					String[] Spicy = { "����", "΢��", "����", "����" };
					String[] Sweet = { "����", "����", "��" };
					String[] Temperture = { "�Ȳ�", "����" };
					String[] Kind = { "�ҳ���", "³��", "����", "����", "�ղ�", "���", "����", "���", "�ղ�" };
					JComboBox<?> pp = new JComboBox<Object>(Kind);
					pp.setSelectedItem(kind);
					TipTextField a = new TipTextField(name, 1);
					TipTextField b = new TipTextField(price, 1);
					JComboBox<String> c = new JComboBox<String>(Salty);
					c.setSelectedItem(salty);
					JComboBox<String> d = new JComboBox<String>(Spicy);
					d.setSelectedItem(spicy);
					JComboBox<String> e = new JComboBox<String>(Sweet);
					e.setSelectedItem(sweet);
					JComboBox<String> f = new JComboBox<String>(Temperture);
					f.setSelectedItem(temperture);
					TipTextField g = new TipTextField(makingtime, 1);
					TipTextField h = new TipTextField(profit, 1);
					a.setBounds(20, 20, 250, 50);
					b.setBounds(20, 80, 250, 50);
					g.setBounds(300, 20, 250, 50);
					h.setBounds(300, 80, 250, 50);
					e.setBounds(20, 140, 100, 50);
					f.setBounds(20, 200, 250, 50);
					c.setBounds(420, 140, 100, 50);
					d.setBounds(300, 200, 250, 50);
					pp.setBounds(220, 140, 100, 50);
					JButton confirm = new JButton("ȷ���޸�");
					confirm.setBounds(250, 270, 100, 40);

					update.add(a);
					update.add(b);
					update.add(c);
					update.add(d);
					update.add(e);
					update.add(f);
					update.add(g);
					update.add(h);
					update.add(pp);
					update.add(confirm);
					confirm.requestFocus();
					confirm.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e1) {
							// TODO Auto-generated method stub
							String i = a.getText();
							String j = b.getText();
							String k = (String) c.getSelectedItem();
							String l = (String) d.getSelectedItem();
							String m = (String) e.getSelectedItem();
							String n = (String) f.getSelectedItem();
							String o = g.getText();
							String p = h.getText();
							String ppp = (String) pp.getSelectedItem();
							try {
								sta.execute("update dishes set name = '" + i + "' , price = '" + j + "', salty = '" + k
										+ "', spicy = '" + l + "', sweet = '" + m + "', temperture = '" + n
										+ "', makingtime = '" + o + "', profit = ' " + p + "', kind = '" + ppp
										+ "' where dishid = " + number);
							} catch (SQLException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
							update.dispose();
							father.dispose();
							try {
								DishManagement p1 = new DishManagement(sta);
								JOptionPane.showMessageDialog(null, "���ĳɹ���", "SUCCESS", JOptionPane.PLAIN_MESSAGE);
								p1.requestFocus();
							} catch (SQLException e3) {
								// TODO Auto-generated catch block
								e3.printStackTrace();
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
			setText("��Ӳ�Ʒ");
			addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					JFrame update = new JFrame("��Ϣ����");
					update.setSize(600, 350);
					update.setLayout(null);
					update.setLocation(500, 400);
					update.setTitle("¼����Ϣ");
					String[] Salty = { "�嵭", "ƫ��", "����", "ƫ��", "����" };
					String[] Spicy = { "����", "΢��", "����", "����" };
					String[] Sweet = { "����", "����", "��" };
					String[] Temperture = { "�Ȳ�", "����" };
					String[] kind = { "�ҳ���", "³��", "����", "����", "�ղ�", "���", "����", "���", "�ղ�" };
					JComboBox<?> pp = new JComboBox<Object>(kind);
					TipTextField a = new TipTextField("�������Ʒ��", 1);
					TipTextField b = new TipTextField("������۸�", 1);
					JComboBox<?> c = new JComboBox<Object>(Salty);
					JComboBox<?> d = new JComboBox<Object>(Spicy);
					JComboBox<?> e = new JComboBox<Object>(Sweet);
					JComboBox<?> f = new JComboBox<Object>(Temperture);
					TipTextField g = new TipTextField("������������ʱ�����ӣ�", 1);
					TipTextField h = new TipTextField("�����������", 1);

					a.setBounds(20, 20, 250, 50);
					b.setBounds(20, 80, 250, 50);
					g.setBounds(300, 20, 250, 50);
					h.setBounds(300, 80, 250, 50);
					e.setBounds(20, 140, 160, 50);
					f.setBounds(20, 200, 250, 50);
					c.setBounds(420, 140, 160, 50);
					d.setBounds(300, 200, 250, 50);
					pp.setBounds(220, 140, 160, 50);

					JButton confirm = new JButton("ȷ��");
					confirm.setBounds(250, 270, 100, 40);

					update.add(a);
					update.add(b);
					update.add(c);
					update.add(d);
					update.add(e);
					update.add(f);
					update.add(g);
					update.add(h);
					update.add(pp);
					update.add(confirm);
					confirm.requestFocus();
					confirm.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e1) {
							// TODO Auto-generated method stub
							String i = a.getText();
							String j = b.getText();
							String k = (String) c.getSelectedItem();
							String l = (String) d.getSelectedItem();
							String m = (String) e.getSelectedItem();
							String n = (String) f.getSelectedItem();
							String o = g.getText();
							String p = h.getText();
							String ppp = (String) pp.getSelectedItem();
							try {
								sta.execute("insert into dishes values ( " + currentNumber + ",'" + i + "','" + j
										+ "','" + k + "','" + l + "','" + m + "','" + n + "','" + o + "','" + p + "','"
										+ ppp + "')");
								currentNumber++;
							} catch (SQLException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
							update.dispose();
							father.dispose();
							try {
								DishManagement p1 = new DishManagement(sta);
								JOptionPane.showMessageDialog(null, "¼��ɹ���", "SUCCESS", JOptionPane.PLAIN_MESSAGE);
								p1.requestFocus();
							} catch (SQLException e3) {
								// TODO Auto-generated catch block
								e3.printStackTrace();
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

	public class stuffPic extends JButton {
		public stuffPic(int x, int y, String name) {
			setBounds(x, y, 60, 16);
			setText("ͼƬ");
			setFont(new Font("΢���ź�", Font.ITALIC, 10));
			addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					String path = "./IMG/" + name + ".jpg";
					JFrame a = new JFrame();
					ImageIcon background = new ImageIcon(path);
					JLabel label = new JLabel(background);
					label.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());
					JPanel image = (JPanel) a.getContentPane();
					a.setTitle(name);
					a.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
					image.setOpaque(false);
					image.setLayout(null);
					a.getLayeredPane().setLayout(null);
					a.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					a.setLocation(260, 100);
					a.setSize(background.getIconWidth() + 5, background.getIconHeight() + 20);
					if (a.getHeight() == 19) {
						JOptionPane.showMessageDialog(null, "δ�ҵ�ͼƬ��", "FAIL", JOptionPane.PLAIN_MESSAGE);
						a.dispose();
						return;
					}
					a.setResizable(false);
					a.setVisible(true);
				}
			});

		}
	}
	// public static void main(String[] args) throws SQLException,
	// ClassNotFoundException{
	//// JFrame c = new JFrame();
	//// c.setVisible(true);
	//// c.setSize(1400, 900);
	//// Container contentPane=c.getContentPane();
	//// JPanel scrollPane = new JPanel();
	//// scrollPane.setPreferredSize(new Dimension(10000,2000));
	//// scrollPane.setBounds(0, 0, 1000, 800);
	//// JButton[] b = new JButton[20];
	//// for(int i =0 ; i<20 ; i++){
	//// b[i] = new JButton();
	//// b[i].setBounds(0,i*200,10000,200);
	//// scrollPane.add(b[i]);
	//// b[i].setText("DJ!DJ!");
	//// }
	//// JScrollPane e = new JScrollPane(scrollPane);
	//// contentPane.add(e,BorderLayout.CENTER);
	//// c.setVisible(true);
	// try{
	// //����MySql��������
	// Class.forName("com.mysql.jdbc.Driver") ;
	// }catch(ClassNotFoundException e){
	// System.out.println("�Ҳ������������� ����������ʧ�ܣ�");
	// e.printStackTrace() ;
	// }
	// Connection con =
	// DriverManager.getConnection("jdbc:mysql://localhost:3306/tavern?useSSL=false"
	// ,"root","123123") ;
	// Statement sta = (Statement)
	// con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
	// new DishManagement(sta);
	// }
	//
}
