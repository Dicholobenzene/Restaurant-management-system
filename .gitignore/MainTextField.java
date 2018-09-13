package contents;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import UI.Manager.bench;

import com.mysql.jdbc.Statement;

@SuppressWarnings("serial")
public class MainTextField extends JPanel {
	boolean displaySQL = false;

	public MainTextField(Statement sta, bench father) {
		setBounds(316, 116, 770, 90);
		setOpaque(false);
		setLayout(null);
		TipTextField command = new TipTextField("��������������", 1);
		command.setBounds(0, 0, 530, 82);
		command.setFont(new Font("΢���ź�", Font.BOLD, 72));
		command.setOpaque(false);
		command.setBackground(new Color(0, 0, 255));
		command.setBorder(new EmptyBorder(0, 0, 0, 0));

		JButton confirm = new JButton();
		confirm.setBounds(615, 0, 150, 82);
		confirm.setOpaque(false);
		confirm.setBackground(new Color(255, 255, 255));
		confirm.setBorder(new EmptyBorder(0, 0, 0, 0));
		confirm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String total = command.getText();
				total.toLowerCase();
				// System.out.println(total.substring(i, i+1));
				if (total.contains("Ա������"))
					try {
						new StuffManagement(sta);
						return;
					} catch (SQLException e) {
						e.printStackTrace();
					}
				if (total.contains("��Ʒ����"))
					try {
						new DishManagement(sta);
						return;
					} catch (SQLException e) {
						e.printStackTrace();
					}
				if (total.contains("���")) {
					try {
						new DishOrder(sta, father);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return;
				}
				if (total.contains("sql") || total.contains("SQL")) {
					if (total.contains("��ʾ") || !total.contains("��"))
						displaySQL = true;
					else
						displaySQL = false;
					JOptionPane.showMessageDialog(null, "���óɹ���", "MESSAGE", JOptionPane.PLAIN_MESSAGE);
					return;
				}
				if (total.contains("���񱨱�") || total.contains("�����")) {
					if (!total.contains("2017")) {
						JOptionPane.showMessageDialog(null, "�����û�в�����Ϣ��", "WARNING", JOptionPane.PLAIN_MESSAGE);
						return;
					} else {
						try {
							if (total.contains("һ��") || total.contains("1��")) {
								new financialReport(sta, "2017", "01");
								return;
							}
							if (total.contains("����") || total.contains("2��")) {
								new financialReport(sta, "2017", "02");
								return;
							}
							if (total.contains("����") || total.contains("3��")) {
								new financialReport(sta, "2017", "03");
								return;
							}
							if (total.contains("����") || total.contains("4��")) {
								new financialReport(sta, "2017", "04");
								return;
							}
							if (total.contains("����") || total.contains("5��")) {
								new financialReport(sta, "2017", "05");
								return;
							}
							if (total.contains("����") || total.contains("6��")) {
								new financialReport(sta, "2017", "06");
								return;
							}
							if (total.contains("����") || total.contains("7��")) {
								new financialReport(sta, "2017", "07");
								return;
							}
							if (total.contains("����") || total.contains("8��")) {
								new financialReport(sta, "2017", "08");
								return;
							}
							if (total.contains("����") || total.contains("9��")) {
								new financialReport(sta, "2017", "09");
								return;
							}
							if (total.contains("ʮ��") || total.contains("10��")) {
								new financialReport(sta, "2017", "10");
								return;
							}
							if (total.contains("ʮһ��") || total.contains("11��")) {
								new financialReport(sta, "2017", "11");
								return;
							}
							if (total.contains("ʮ����") || total.contains("12��")) {
								new financialReport(sta, "2017", "01");
								return;
							}
							if (total.contains("��һ����") || total.contains("����")) {
								new financialReport(sta, "2017", 1);
								return;
							}
							if (total.contains("�ڶ�����") || total.contains("�ļ�")) {
								new financialReport(sta, "2017", 2);
								return;
							}
							if (total.contains("��������") || total.contains("�＾")) {
								new financialReport(sta, "2017", 3);
								return;
							}
							if (total.contains("���ļ���") || total.contains("����")) {
								new financialReport(sta, "2017", 4);
								return;
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}

				// ����
				if (total.contains("����") || total.contains("����") || total.contains("�г�") || total.contains("��ʾ")
						|| total.contains("��ѯ")) {
					String table = null, keyname = null, key = null;
					// total=total.replace("����", "");
					// total=total.replace("����", "");
					// total=total.replace("�г�", "");
					// total=total.replace("��ʾ", "");
					// total=total.replace("��", "");
					// total=total.replace("Ϊ", "");
					// total=total.replace("��", "");
					// total=total.replace("��", "");
					total = strDel(total, "����");
					total = strDel(total, "��ѯ");
					total = strDel(total, "����");
					total = strDel(total, "�г�");
					total = strDel(total, "��ʾ");
					total = strDel(total, "��");
					total = strDel(total, "Ϊ");
					total = strDel(total, "��");
					total = strDel(total, "��");
					total = strDel(total, "��");
					// �������
					if (total.contains("��")) {
						table = "dishorder natural join dishes";
						// total=total.replace("��", "");
						// total=total.replace("��", "");
						total = strDel(total, "��");
						total = strDel(total, "��");
						keyname = "name";
						key = "'" + total + "'";
						total = " ";

					}

					// Ա������
					if (total.contains("Ա��") || total.contains("����Ա")) {
						String ex = "";
						if (total.contains("����")) {
							if (total.contains("��Ա��")) {
								keyname = "sex";
								key = "'��'";
								total = " ";
							}
							if (total.contains("ŮԱ��")) {
								keyname = "sex";
								key = "'Ů'";
								total = " ";
							}

						}
						if (total.contains("��Ա��")) {
							ex = " and sex = '��'";
							total = strDel(total, "��");
						}
						if (total.contains("ŮԱ��")) {
							ex = " and sex = 'Ů'";
							total = strDel(total, "Ů");
						}
						total = strDel(total, "Ա��");
						total = strDel(total, "����Ա");
						table = "waiter";
						if (total.contains("����")) {
							total = strDel(total, "����");
							keyname = "name";
							key = "'" + total + "' " + ex;
							total = " ";
						}
						if (total.contains("����") || total.contains("id")) {
							total = strDel(total, "����");
							total = strDel(total, "id");
							keyname = "id";
							key = "'" + total + "'" + ex;
							total = " ";
						}
						if (total.contains("�Ա�")) {
							total = strDel(total, "�Ա�");
							keyname = "sex";
							key = "'" + total + "' " + ex;
							total = " ";
						}
						if (total.contains("��")) {
							total = strDel(total, "��");
							keyname = "name";
							key = total;
							try {
								new Search(sta, table, keyname, key, displaySQL);
								return;
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						if (total.contains("����")) {
							total = strDel(total, "����");
							total = strDel(total, "Ԫ");
							if (total.contains("����") || total.contains("����")) {
								total = strDel(total, "����");
								total = strDel(total, "g������");
								try {
									String SQL = "select * from waiter where salary > " + total + ex;
									ResultSet res = sta.executeQuery(SQL);
									if (displaySQL)
										JOptionPane.showMessageDialog(null, SQL, "SQL", JOptionPane.PLAIN_MESSAGE);
									new Search(res);
									return;
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							if (total.contains("С��") || total.contains("����")) {
								total = strDel(total, "С��");
								total.contains("����");
								try {
									String SQL = "select * from waiter where salary < " + total + ex;
									ResultSet res = sta.executeQuery(SQL);
									if (displaySQL)
										JOptionPane.showMessageDialog(null, SQL, "SQL", JOptionPane.PLAIN_MESSAGE);
									new Search(res);
									return;
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							if (total.contains("����")) {
								total = strDel(total, "����");
								try {
									String SQL = "select * from waiter where salary = " + total + ex;
									if (displaySQL)
										JOptionPane.showMessageDialog(null, SQL, "SQL", JOptionPane.PLAIN_MESSAGE);
									ResultSet res = sta.executeQuery(SQL);
									new Search(res);
									return;
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}

						}

					}

					// ��Ʒ����
					if (total.contains("��")) {
						String ex = null;
						table = "dishes";
						total = strDel(total, "��");
						total = strDel(total, "Ʒ");
						if (total.contains("����")) {
							if (total.contains("��")) {
								keyname = "temperture";
								key = "'�Ȳ�'";
								total = " ";
							}
							if (total.contains("��")) {
								keyname = "temperture";
								key = "'����'";
								total = " ";
							}
							if (total.contains("³")) {
								keyname = "kind";
								key = "'³��'";
								total = " ";
							}
							if (total.contains("��")) {
								keyname = "kind";
								key = "'����'";
								total = " ";
							}
							if (total.contains("��")) {
								keyname = "kind";
								key = "'����'";
								total = " ";
							}
							if (total.contains("��")) {
								keyname = "kind";
								key = "'���'";
								total = " ";
							}
							if (total.contains("��")) {
								keyname = "kind";
								key = "'����'";
								total = " ";
							}
							if (total.contains("��")) {
								keyname = "kind";
								key = "'���'";
								total = " ";
							}
							if (total.contains("��")) {
								keyname = "kind";
								key = "'�ղ�'";
								total = " ";
							}
							if (total.contains("�ҳ�")) {
								keyname = "kind";
								key = "'�ҳ���'";
								total = " ";
							}
							if (total.contains("��")) {
								keyname = "kind";
								key = "'�ղ�'";
								total = " ";
							}
							total = " ";
						}
						if (total.contains("��")) {
							ex = " and temperture = '�Ȳ�'";
							total = strDel(total, "��");
						}
						if (total.contains("��")) {
							ex = " and temperture = '����'";
							total = strDel(total, "��");
						}
						if (total.contains("³")) {
							ex = " and kind = '³��'";
							total = strDel(total, "³");
						}
						if (total.contains("��")) {
							ex = " and kind = '����'";
							total = strDel(total, "��");
						}
						if (total.contains("��")) {
							ex = " and kind = '����'";
							total = strDel(total, "��");
						}
						if (total.contains("��")) {
							ex = " and kind = '���'";
							total = strDel(total, "��");
						}
						if (total.contains("��")) {
							ex = " and kind = '����'";
							total = strDel(total, "��");
						}
						if (total.contains("��")) {
							ex = " and kind = '���'";
							total = strDel(total, "��");
						}
						if (total.contains("��")) {
							ex = " and kind = '�ղ�'";
							total = strDel(total, "��");
						}
						if (total.contains("�ҳ�")) {
							ex = " and kind='�ҳ���'";
							total = strDel(total, "�ҳ�");
						}
						if (total.contains("��")) {
							ex = " and kind = '�ղ�'";
							total = strDel(total, "��");
						}
						if (total.contains("����") || total.contains("��")) {
							total = strDel(strDel(total, "��"), "����");
							keyname = "name";
							key = "'" + total + "'" + ex;
							total = " ";
						}
						if (total.contains("�۸�")) {
							total = strDel(total, "�۸�");
							total = strDel(total, "Ԫ");
							if (total.contains("����") || total.contains("����")) {
								total = strDel(total, "����");
								total = strDel(total, "g������");
								try {
									String SQL = "select * from `tavern`.dishes where price  > " + total + ex;
									ResultSet res = sta.executeQuery(SQL);
									if (displaySQL)
										JOptionPane.showMessageDialog(null, SQL, "SQL", JOptionPane.PLAIN_MESSAGE);
									new Search(res);
									return;
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							if (total.contains("С��") || total.contains("����")) {
								total = strDel(total, "С��");
								total.contains("����");
								try {
									String SQL = "select * from dishes where price < " + total + ex;
									ResultSet res = sta.executeQuery(SQL);
									if (displaySQL)
										JOptionPane.showMessageDialog(null, SQL, "SQL", JOptionPane.PLAIN_MESSAGE);
									new Search(res);
									return;
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							if (total.contains("����")) {
								total = strDel(total, "����");
								try {
									String SQL = "select * from dishes where price = " + total + ex;
									ResultSet res = sta.executeQuery(SQL);
									if (displaySQL)
										JOptionPane.showMessageDialog(null, SQL, "SQL", JOptionPane.PLAIN_MESSAGE);
									new Search(res);
									return;
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}
						if (total.contains("��")) {
							keyname = "spicy";
							key = "'" + total + "'" + ex;
							total = " ";
						}
						if (total.contains("��")) {
							keyname = "sweet";
							key = "'" + total + "'" + ex;
							total = " ";
						}
						if (total.contains("��") || total.contains("��")) {
							keyname = "salty";
							key = "'" + total + "'" + ex;
							total = " ";
						}
					}

					if (total.contains("�˿�")) {
						table = "customers";
						total = strDel(total, "�˿�");
						if (total.contains("�绰") || total.contains("�ֻ�")) {
							total = strDel(strDel(total, "�绰"), "�ֻ�");
							keyname = "telephone";
							if (total.contains("β��") || total.contains("ǰ��λ") || total.contains("����")) {
								total = strDel(strDel(strDel(strDel(total, "β��"), "ǰ��λ"), "����"), "��");
								total = strDel(total, "��");
								key = total;
								total = " ";
								try {
									new Search(sta, table, keyname, key, displaySQL);
									return;
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							} else {
								total = strDel(strDel(strDel(strDel(total, "����"), "ǰ��λ"), "����"), "��");
								key = "'" + total + "'";
								total = " ";
							}
						}
						if (total.contains("��")) {
							total = strDel(total, "��");
							keyname = "familyname";
							key = "'" + total + "'";
							total = " ";
						}
					}

					try {
						new Search(sta, table, keyname, key, 1, displaySQL);
						return;
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, "���������д��ڲ������󣡣�", "�޷�ʶ��", JOptionPane.PLAIN_MESSAGE);
						return;
					}
				}
				JOptionPane.showMessageDialog(null, "�ݲ�֧�ִ˹��ܻ��޷�ʶ��\n�뻻һ�ֱ�����ϵ����\n֧�ֵĹ�����ο�˵���ļ���", "�޷�ʶ��",
						JOptionPane.PLAIN_MESSAGE);
			}
		});
		add(confirm);
		add(command);
		confirm.requestFocus();

	}

	public String strDel(String str, String toDel) {
		str = str.replace(toDel, "");
		return str;

	}

	// public static void main(String[] args) throws SQLException,
	// ClassNotFoundException{
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
	// Statement sta =
	// con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
	// JFrame tttt =new JFrame();
	// tttt.setSize(1400, 900);
	// Container ttttt = tttt.getContentPane();
	// ttttt.add(new MainTextField(sta));
	// tttt.setVisible(true);

	// }
}
