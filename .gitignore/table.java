package contents;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import UI.Manager.bench;

@SuppressWarnings("serial")
public class table extends JButton {
	int number;
	bench father;
	int total = 0;
	int limit = 0;
	int totalPro = 0;
	JFrame go;
	table temp;

	public table(int limit, String condition, int number, bench father) {
		this.number = number;
		this.father = father;
		this.limit = limit;
		String url = "./IMG/" + limit + "_" + condition + ".png";
		ImageIcon Icon = new ImageIcon(url);
		setIcon(Icon);
		int x = (number % 6) * Icon.getIconWidth() + 100;
		int y = (int) (number / 6) * Icon.getIconHeight() + 270;
		setBounds(x, y, Icon.getIconHeight(), Icon.getIconWidth());
		setOpaque(false);
		setBackground(new Color(0, 0, 255));
		setToolTipText((number + 1) + "号桌");
		addActionListener(new tableListener(this));
		temp = this;
	}

	class tableListener implements ActionListener {
		table a;
		JButton check, refund, order, disorder;

		tableListener(table m) {
			a = m;
		}

		public void actionPerformed(ActionEvent arg0) {
			a.father.remove(a);
			int x = a.getBounds().x;
			int y = a.getBounds().y;
			int width = a.getBounds().width;
			int height = a.getBounds().height;
			check = new JButton("结 账");
			check.setBorder(BorderFactory.createRaisedBevelBorder());
			check.setBounds(x, y, width / 2, height / 2);
			check.addActionListener(new checkListener());
			check.setFont(new Font("微软雅黑", Font.TYPE1_FONT, 24));
			refund = new JButton("菜 单");
			refund.setBounds(x + width / 2, y, width / 2, height / 2);
			refund.addActionListener(new refundListener());
			refund.setFont(new Font("微软雅黑", Font.TYPE1_FONT, 24));
			order = new JButton("预 约");
			order.setBounds(x, y + height / 2, width / 2, height / 2);
			order.addActionListener(new orderListener());
			order.setFont(new Font("微软雅黑", Font.TYPE1_FONT, 24));
			disorder = new JButton("取 消");
			disorder.setBorder(BorderFactory.createRaisedSoftBevelBorder());
			disorder.setBounds(x + width / 2, y + height / 2, width / 2, height / 2);
			disorder.addActionListener(new disorderListener());
			disorder.setFont(new Font("微软雅黑", Font.TYPE1_FONT, 24));
			a.father.remove(a);
			a.father.add(check);
			a.father.add(refund);
			a.father.add(order);
			a.father.add(disorder);
			a.father.add(a);
			a.father.repaint();
			// System.out.println(x+"_"+y+""+width+"_"+height);
		}

		class checkListener implements ActionListener {
			public void actionPerformed(ActionEvent arg0) {
				a.father.remove(check);
				a.father.remove(refund);
				a.father.remove(order);
				a.father.remove(disorder);
				a.father.repaint();
				try {
					goCheck();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		class refundListener implements ActionListener {
			public void actionPerformed(ActionEvent arg0) {
				a.father.remove(check);
				a.father.remove(refund);
				a.father.remove(order);
				a.father.remove(disorder);
				a.father.repaint();
				go = new JFrame("菜单");
				go.setSize(600, 300);
				go.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				go.setResizable(false);
				go.setLayout(null);
				go.setLocation((int) (a.father.getLocationOnScreen().y + a.father.getWidth() / 2.5),
						a.father.getLocationOnScreen().x + a.father.getHeight() / 4);
				JPanel pan = (JPanel) go.getContentPane();
				pan.setLayout(null);
				int c = (a.number + 1);
				try {
					ResultSet a1 = father.getSta().executeQuery(
							"select name,price,dishid as '价格',ordertime as'点菜时间', salty as '咸淡' , spicy as '辣度', temperture as '温度'from dishorder natural join dishes where dishid = dish and tableid = "
									+ c);
					// a.next();
					int y = 0;
					while (a1.next()) {
						go.add(new dishDel(go.getWidth() - 60, 22 + 16 * y, a1.getInt(3), c, father.getSta(), a, go));
						y++;
					}
					ResultSet a = father.getSta().executeQuery(
							"select name,price as '价格',ordertime as'点菜时间', salty as '咸淡' , spicy as '辣度', temperture as '温度'from dishorder natural join dishes where dishid = dish and tableid = "
									+ c);
					DBTable b = new DBTable(a);
					if (b.getRowCount() == 0) {
						go.dispose();
						JOptionPane.showMessageDialog(null, "该桌没有点菜记录", "ERROR", JOptionPane.PLAIN_MESSAGE);
						return;
					}
					JScrollPane scrollPane = new JScrollPane(new JTable(b.getdata(), b.getcolumnNames()));
					scrollPane.setBounds(0, 0, go.getWidth() - 60, go.getHeight() - 80);
					;
					scrollPane.setViewportBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
					JButton confirm = new JButton("确认");
					confirm.setBounds(250, go.getHeight() - 60, 100, 30);
					confirm.setFont(new Font("微软雅黑", Font.BOLD, 16));
					confirm.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent arg0) {
							// TODO Auto-generated method stub
							go.dispose();
						}
					});
					pan.add(scrollPane);
					pan.add(confirm);
					a.close();
					go.setVisible(true);
				} catch (ArrayIndexOutOfBoundsException | SQLException e) {
					JOptionPane.showMessageDialog(null, "该桌没有点菜记录", "ERROR", JOptionPane.PLAIN_MESSAGE);
					go.dispose();
					e.printStackTrace();
				}
			}
		}

		class orderListener implements ActionListener {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ResultSet tables = father.getSta()
							.executeQuery("select * from tables where tableid = " + (a.number + 1));
					tables.next();
					String condition = tables.getString("condition");
					tables.close();
					if (condition.equals("no")) {
						new orderConfirm(father.getSta());
//						father.remove(a);
					} else
						JOptionPane.showMessageDialog(null, "该桌现在无法预定", "ERROR", JOptionPane.PLAIN_MESSAGE);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				a.father.remove(check);
				a.father.remove(refund);
				a.father.remove(order);
				a.father.remove(disorder);
				a.father.repaint();

			}
		}

		class orderConfirm extends JFrame {
			public orderConfirm(Statement sta) {
				setSize(600, 250);
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				setResizable(false);
				setLayout(null);
				setTitle("请输入预约信息");
				setLocation((int) (a.father.getLocationOnScreen().y + a.father.getWidth() / 2.5),
						a.father.getLocationOnScreen().x + a.father.getHeight() / 4);
				TipTextField fimilyName = new TipTextField("请输入姓氏", 1);
				TipTextField telNumber = new TipTextField("请输入电话号码", 1);
				fimilyName.setBounds(45, 50, 220, 60);
				telNumber.setBounds(325, 50, 210, 60);
				JButton confirm = new JButton("确认");
				confirm.setBounds(250, 150, 100, 50);
				add(confirm);
				add(fimilyName);
				add(telNumber);
				setVisible(true);
				addWindowListener(new WindowListener() {

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
						int f = a.number + 1;
						ResultSet tables1;
						try {
							tables1 = father.getSta().executeQuery("select * from tables where tableid = " + f);

							while (tables1.next()) {
								father.remove(a);
								int number1 = tables1.getInt("tableid") - 1;
								int limit1 = tables1.getInt("limit");
								String condition1 = tables1.getString("condition");
								father.add(new table(limit1, condition1, number1, father));
								tables1.close();
								father.repaint();
								break;
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
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
				confirm.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						String name = fimilyName.getText(), tel = telNumber.getText();
						SimpleDateFormat df = new SimpleDateFormat("HH:mm");
						String currentTime = df.format(new Date());
						try {
							int f = a.number + 1;
							ResultSet t = sta
									.executeQuery("select count(*) from customers where telephone = '" + tel + "'");
							t.next();
							if (t.getInt(1) == 0)
								sta.execute("insert into customers values('" + name + "','" + tel + "')");
							sta.execute("insert into tableorder values(" + tel + "," + f + ",'" + currentTime + "')");
							sta.execute("update tables set `CONDITION` ='order' where tableid = " + f);
							t.close();
							ResultSet tables1 = father.getSta()
									.executeQuery("select * from tables where tableid = " + f);
							while (tables1.next()) {
								father.remove(a);
								int number1 = tables1.getInt("tableid") - 1;
								int limit1 = tables1.getInt("limit");
								String condition1 = tables1.getString("condition");
								father.add(new table(limit1, condition1, number1, father));
								tables1.close();
								break;
							}
							father.repaint();
							JOptionPane.showMessageDialog(null, "预定成功！", "SUCCESS", JOptionPane.PLAIN_MESSAGE);
							dispose();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
			}
		}

		class disorderListener implements ActionListener {
			public void actionPerformed(ActionEvent arg0) {
				int o = JOptionPane.showConfirmDialog(null, "确认退订吗?");
				if (o == 0) {
					try {
						ResultSet tables = father.getSta()
								.executeQuery("select * from tables where tableid = " + (a.number + 1));
						while (tables.next()) {
							int number = tables.getInt("tableid") - 1;
							int limit = tables.getInt("limit");
							String condition = tables.getString("condition");
							if (condition.equals("order")) {
								father.remove(a);
								father.getSta().execute(
										"update tables set `CONDITION` ='no' where tableid = " + (a.number + 1));
								father.add(new table(limit, "no", number, a.father));
								break;
							} else
								JOptionPane.showMessageDialog(null, "该桌没有预定记录", "ERROR", JOptionPane.PLAIN_MESSAGE);
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				a.father.remove(check);
				a.father.remove(refund);
				a.father.remove(order);
				a.father.remove(disorder);
				a.father.repaint();
			}
		}

		void goCheck() throws SQLException {
			go = new JFrame("结账");
			go.setSize(600, 200);
			go.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			go.setResizable(false);
			go.setLayout(null);
			go.setLocation((int) (a.father.getLocationOnScreen().y + a.father.getWidth() / 2.5),
					a.father.getLocationOnScreen().x + a.father.getHeight() / 4);
			JPanel pan = (JPanel) go.getContentPane();
			pan.setLayout(null);
			int c = (a.number + 1);
			try {
				ResultSet a = father.getSta().executeQuery(
						"select name,price,profit from dishorder natural join dishes where dishid = dish and tableid = "
								+ c);
				// a.next();
				DBTable b = new DBTable(a);
				JTable f = new JTable(b.getdata(), b.getcolumnNames());
				f.removeColumn(f.getColumn("profit"));
				JScrollPane scrollPane = new JScrollPane(f);
				scrollPane.setBounds(0, 0, go.getWidth(), go.getHeight() / 2);
				;
				scrollPane.setViewportBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
				JTextField add = new JTextField("总金额：" + b.addColumn(2) + "元");
				total = b.addColumn(2);
				totalPro = b.addColumn(3);
				if (total == 0) {
					go.dispose();
					JOptionPane.showMessageDialog(null, "该桌没有点菜记录", "ERROR", JOptionPane.PLAIN_MESSAGE);
					return;
				}
				add.setFont(new Font("黑体", Font.BOLD, 37));
				add.setEditable(false);
				add.setBounds(0, go.getHeight() / 2, go.getWidth() / 2, 50);
				add.setHorizontalAlignment(CENTER);
				JButton confirm = new JButton("CONFIRM");
				confirm.setBounds(go.getWidth() / 2, go.getHeight() / 2, go.getWidth() / 2, 50);
				confirm.setFont(new Font("Malgun Gothic", Font.BOLD, 16));
				confirm.addActionListener(new confirmListener());
				pan.add(scrollPane);
				pan.add(add);
				pan.add(confirm);
				go.setVisible(true);
			} catch (ArrayIndexOutOfBoundsException e) {
				JOptionPane.showMessageDialog(null, "该桌没有点菜记录", "ERROR", JOptionPane.PLAIN_MESSAGE);
				go.dispose();
			}
		}

		class confirmListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					father.getSta().execute("insert into tavern.check(tableid,price,profit) values(" + (a.number + 1)
							+ "," + total + ", " + totalPro + ")");
					father.getSta().execute("delete from tavern.dishorder where tableid = " + (a.number + 1));
					JOptionPane.showMessageDialog(null, "结账成功！", "m", JOptionPane.PLAIN_MESSAGE);
					go.dispose();
					ResultSet tables = father.getSta()
							.executeQuery("select * from tables where tableid = " + (a.number + 1));
					tables.next();
					int number = tables.getInt("tableid") - 1;
					int limit = tables.getInt("limit");
					father.remove(a);
					father.getSta().execute("update tables set `CONDITION` ='no' where tableid = " + (a.number + 1));
					a.father.add(new table(limit, "no", number, a.father));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public class dishDel extends JButton {
		public dishDel(int x, int y, int dish, int tableid, Statement sta, table a, JFrame wildfather) {
			setBounds(x, y, 60, 16);
			setText("退菜");
			setFont(new Font("微软雅黑", Font.ITALIC, 10));
			addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					int o = JOptionPane.showConfirmDialog(null, "是否确认删除菜品");
					if (o == 0) {
						try {
							sta.execute("delete from dishorder where dish =" + dish + " and tableid = " + tableid);
							ResultSet res = sta
									.executeQuery("select count(*) from dishorder where tableid = " + tableid);
							res.next();
							if (res.getInt(1) == 0) {
								father.remove(a);
								father.add(new table(limit, "no", number, father));
								father.repaint();
							}
						} catch (SQLException e) {
//							System.out.print("34");
							return;
						}
						wildfather.dispose();
						JOptionPane.showMessageDialog(null, "退菜成功！", "SUCCESS", JOptionPane.PLAIN_MESSAGE);
					}
				}
			});
		}
	}
}
