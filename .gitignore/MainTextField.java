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
		TipTextField command = new TipTextField("请输入您的需求", 1);
		command.setBounds(0, 0, 530, 82);
		command.setFont(new Font("微软雅黑", Font.BOLD, 72));
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
				if (total.contains("员工管理"))
					try {
						new StuffManagement(sta);
						return;
					} catch (SQLException e) {
						e.printStackTrace();
					}
				if (total.contains("菜品管理"))
					try {
						new DishManagement(sta);
						return;
					} catch (SQLException e) {
						e.printStackTrace();
					}
				if (total.contains("点菜")) {
					try {
						new DishOrder(sta, father);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return;
				}
				if (total.contains("sql") || total.contains("SQL")) {
					if (total.contains("显示") || !total.contains("不"))
						displaySQL = true;
					else
						displaySQL = false;
					JOptionPane.showMessageDialog(null, "设置成功！", "MESSAGE", JOptionPane.PLAIN_MESSAGE);
					return;
				}
				if (total.contains("财务报表") || total.contains("利润表")) {
					if (!total.contains("2017")) {
						JOptionPane.showMessageDialog(null, "该年度没有财务信息！", "WARNING", JOptionPane.PLAIN_MESSAGE);
						return;
					} else {
						try {
							if (total.contains("一月") || total.contains("1月")) {
								new financialReport(sta, "2017", "01");
								return;
							}
							if (total.contains("二月") || total.contains("2月")) {
								new financialReport(sta, "2017", "02");
								return;
							}
							if (total.contains("三月") || total.contains("3月")) {
								new financialReport(sta, "2017", "03");
								return;
							}
							if (total.contains("四月") || total.contains("4月")) {
								new financialReport(sta, "2017", "04");
								return;
							}
							if (total.contains("五月") || total.contains("5月")) {
								new financialReport(sta, "2017", "05");
								return;
							}
							if (total.contains("六月") || total.contains("6月")) {
								new financialReport(sta, "2017", "06");
								return;
							}
							if (total.contains("七月") || total.contains("7月")) {
								new financialReport(sta, "2017", "07");
								return;
							}
							if (total.contains("八月") || total.contains("8月")) {
								new financialReport(sta, "2017", "08");
								return;
							}
							if (total.contains("九月") || total.contains("9月")) {
								new financialReport(sta, "2017", "09");
								return;
							}
							if (total.contains("十月") || total.contains("10月")) {
								new financialReport(sta, "2017", "10");
								return;
							}
							if (total.contains("十一月") || total.contains("11月")) {
								new financialReport(sta, "2017", "11");
								return;
							}
							if (total.contains("十二月") || total.contains("12月")) {
								new financialReport(sta, "2017", "01");
								return;
							}
							if (total.contains("第一季度") || total.contains("春季")) {
								new financialReport(sta, "2017", 1);
								return;
							}
							if (total.contains("第二季度") || total.contains("夏季")) {
								new financialReport(sta, "2017", 2);
								return;
							}
							if (total.contains("第三季度") || total.contains("秋季")) {
								new financialReport(sta, "2017", 3);
								return;
							}
							if (total.contains("第四季度") || total.contains("冬季")) {
								new financialReport(sta, "2017", 4);
								return;
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}

				// 搜索
				if (total.contains("查找") || total.contains("搜索") || total.contains("列出") || total.contains("显示")
						|| total.contains("查询")) {
					String table = null, keyname = null, key = null;
					// total=total.replace("查找", "");
					// total=total.replace("搜索", "");
					// total=total.replace("列出", "");
					// total=total.replace("显示", "");
					// total=total.replace("的", "");
					// total=total.replace("为", "");
					// total=total.replace("了", "");
					// total=total.replace("子", "");
					total = strDel(total, "查找");
					total = strDel(total, "查询");
					total = strDel(total, "搜索");
					total = strDel(total, "列出");
					total = strDel(total, "显示");
					total = strDel(total, "的");
					total = strDel(total, "为");
					total = strDel(total, "了");
					total = strDel(total, "子");
					total = strDel(total, "是");
					// 点菜搜索
					if (total.contains("点")) {
						table = "dishorder natural join dishes";
						// total=total.replace("桌", "");
						// total=total.replace("点", "");
						total = strDel(total, "桌");
						total = strDel(total, "点");
						keyname = "name";
						key = "'" + total + "'";
						total = " ";

					}

					// 员工搜索
					if (total.contains("员工") || total.contains("服务员")) {
						String ex = "";
						if (total.contains("所有")) {
							if (total.contains("男员工")) {
								keyname = "sex";
								key = "'男'";
								total = " ";
							}
							if (total.contains("女员工")) {
								keyname = "sex";
								key = "'女'";
								total = " ";
							}

						}
						if (total.contains("男员工")) {
							ex = " and sex = '男'";
							total = strDel(total, "男");
						}
						if (total.contains("女员工")) {
							ex = " and sex = '女'";
							total = strDel(total, "女");
						}
						total = strDel(total, "员工");
						total = strDel(total, "服务员");
						table = "waiter";
						if (total.contains("姓名")) {
							total = strDel(total, "姓名");
							keyname = "name";
							key = "'" + total + "' " + ex;
							total = " ";
						}
						if (total.contains("工号") || total.contains("id")) {
							total = strDel(total, "工号");
							total = strDel(total, "id");
							keyname = "id";
							key = "'" + total + "'" + ex;
							total = " ";
						}
						if (total.contains("性别")) {
							total = strDel(total, "性别");
							keyname = "sex";
							key = "'" + total + "' " + ex;
							total = " ";
						}
						if (total.contains("姓")) {
							total = strDel(total, "姓");
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
						if (total.contains("工资")) {
							total = strDel(total, "工资");
							total = strDel(total, "元");
							if (total.contains("大于") || total.contains("高于")) {
								total = strDel(total, "大于");
								total = strDel(total, "g、高于");
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
							if (total.contains("小于") || total.contains("低于")) {
								total = strDel(total, "小于");
								total.contains("低于");
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
							if (total.contains("等于")) {
								total = strDel(total, "等于");
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

					// 菜品搜索
					if (total.contains("菜")) {
						String ex = null;
						table = "dishes";
						total = strDel(total, "菜");
						total = strDel(total, "品");
						if (total.contains("所有")) {
							if (total.contains("热")) {
								keyname = "temperture";
								key = "'热菜'";
								total = " ";
							}
							if (total.contains("凉")) {
								keyname = "temperture";
								key = "'凉菜'";
								total = " ";
							}
							if (total.contains("鲁")) {
								keyname = "kind";
								key = "'鲁菜'";
								total = " ";
							}
							if (total.contains("川")) {
								keyname = "kind";
								key = "'川菜'";
								total = " ";
							}
							if (total.contains("粤")) {
								keyname = "kind";
								key = "'粤菜'";
								total = " ";
							}
							if (total.contains("浙")) {
								keyname = "kind";
								key = "'浙菜'";
								total = " ";
							}
							if (total.contains("闽")) {
								keyname = "kind";
								key = "'闽菜'";
								total = " ";
							}
							if (total.contains("湘")) {
								keyname = "kind";
								key = "'湘菜'";
								total = " ";
							}
							if (total.contains("徽")) {
								keyname = "kind";
								key = "'徽菜'";
								total = " ";
							}
							if (total.contains("家常")) {
								keyname = "kind";
								key = "'家常菜'";
								total = " ";
							}
							if (total.contains("苏")) {
								keyname = "kind";
								key = "'苏菜'";
								total = " ";
							}
							total = " ";
						}
						if (total.contains("热")) {
							ex = " and temperture = '热菜'";
							total = strDel(total, "热");
						}
						if (total.contains("凉")) {
							ex = " and temperture = '凉菜'";
							total = strDel(total, "凉");
						}
						if (total.contains("鲁")) {
							ex = " and kind = '鲁菜'";
							total = strDel(total, "鲁");
						}
						if (total.contains("川")) {
							ex = " and kind = '川菜'";
							total = strDel(total, "川");
						}
						if (total.contains("粤")) {
							ex = " and kind = '粤菜'";
							total = strDel(total, "粤");
						}
						if (total.contains("浙")) {
							ex = " and kind = '浙菜'";
							total = strDel(total, "浙");
						}
						if (total.contains("闽")) {
							ex = " and kind = '闽菜'";
							total = strDel(total, "闽");
						}
						if (total.contains("湘")) {
							ex = " and kind = '湘菜'";
							total = strDel(total, "湘");
						}
						if (total.contains("徽")) {
							ex = " and kind = '徽菜'";
							total = strDel(total, "徽");
						}
						if (total.contains("家常")) {
							ex = " and kind='家常菜'";
							total = strDel(total, "家常");
						}
						if (total.contains("苏")) {
							ex = " and kind = '苏菜'";
							total = strDel(total, "苏");
						}
						if (total.contains("名字") || total.contains("叫")) {
							total = strDel(strDel(total, "叫"), "名字");
							keyname = "name";
							key = "'" + total + "'" + ex;
							total = " ";
						}
						if (total.contains("价格")) {
							total = strDel(total, "价格");
							total = strDel(total, "元");
							if (total.contains("大于") || total.contains("高于")) {
								total = strDel(total, "大于");
								total = strDel(total, "g、高于");
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
							if (total.contains("小于") || total.contains("低于")) {
								total = strDel(total, "小于");
								total.contains("低于");
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
							if (total.contains("等于")) {
								total = strDel(total, "等于");
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
						if (total.contains("辣")) {
							keyname = "spicy";
							key = "'" + total + "'" + ex;
							total = " ";
						}
						if (total.contains("甜")) {
							keyname = "sweet";
							key = "'" + total + "'" + ex;
							total = " ";
						}
						if (total.contains("淡") || total.contains("咸")) {
							keyname = "salty";
							key = "'" + total + "'" + ex;
							total = " ";
						}
					}

					if (total.contains("顾客")) {
						table = "customers";
						total = strDel(total, "顾客");
						if (total.contains("电话") || total.contains("手机")) {
							total = strDel(strDel(total, "电话"), "手机");
							keyname = "telephone";
							if (total.contains("尾号") || total.contains("前三位") || total.contains("区号")) {
								total = strDel(strDel(strDel(strDel(total, "尾号"), "前三位"), "区号"), "号");
								total = strDel(total, "码");
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
								total = strDel(strDel(strDel(strDel(total, "号码"), "前三位"), "区号"), "号");
								key = "'" + total + "'";
								total = " ";
							}
						}
						if (total.contains("姓")) {
							total = strDel(total, "姓");
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
						JOptionPane.showMessageDialog(null, "您的命令中存在参数错误！！", "无法识别！", JOptionPane.PLAIN_MESSAGE);
						return;
					}
				}
				JOptionPane.showMessageDialog(null, "暂不支持此功能或无法识别\n请换一种表达或联系作者\n支持的功能请参考说明文件！", "无法识别！",
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
	// //加载MySql的驱动类
	// Class.forName("com.mysql.jdbc.Driver") ;
	// }catch(ClassNotFoundException e){
	// System.out.println("找不到驱动程序类 ，加载驱动失败！");
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
