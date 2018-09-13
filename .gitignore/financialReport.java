package contents;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

public class financialReport {
	private ResultSet res;
	String year, month;

	public financialReport(Statement sta, String year, String month) throws SQLException {
		res = sta.executeQuery(
				"SELECT tableid,price,profit FROM tavern.`check` where date like '" + year + "-" + month + "%'");
		this.year = year;
		this.month = month + "月";
		go();
	}

	public financialReport(Statement sta, String year, int season) throws SQLException {
		switch (season) {
		case 1:
			res = sta.executeQuery("SELECT tableid,price,profit FROM tavern.`check` where date like '" + year + "-04"
					+ "%' or date like '" + year + "-02" + "%' or date like '" + year + "-03" + "%'");
			this.year = year;
			this.month = "第一季度";
			break;
		case 2:
			res = sta.executeQuery("SELECT tableid,price,profit FROM tavern.`check` where date like '" + year + "-07"
					+ "%' or date like '" + year + "-05" + "%' or date like '" + year + "-06" + "%'");
			this.year = year;
			this.month = "第二季度";
			break;
		case 3:
			res = sta.executeQuery("SELECT tableid,price,profit FROM tavern.`check` where date like '" + year + "-10"
					+ "%' or date like '" + year + "-08" + "%' or date like '" + year + "-09" + "%'");
			this.year = year;
			this.month = "第三季度";
			break;
		case 4:
			res = sta.executeQuery("SELECT tableid,price,profit FROM tavern.`check` where date like '" + year + "-01"
					+ "%' or date like '" + year + "-11" + "%' or date like '" + year + "-12" + "%'");
			this.year = year;
			this.month = "第四季度";
			break;
		}
		go();
	}

	private void go() throws SQLException {
		DBTable c = new DBTable(res);
		JFrame cool = new JFrame(year + "年" + month + "利润表");
		cool.setBounds(450, 50, 1000, 800);
		cool.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		cool.setLayout(null);
		Container contentPane = cool.getContentPane();
		JTable d = new JTable(c.getdata(), c.getcolumnNames());
		JScrollPane ScrollPane = new JScrollPane(d);
		ScrollPane.setBounds(10, 10, 980, 400);
		ScrollPane.setViewportBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		contentPane.add(ScrollPane);
		JTextField income = new JTextField();
		JTextField profit = new JTextField();

		income.setText(" " + month + "营业额为" + c.addColumn(2) + "元。");
		profit.setText(" " + month + "总利润为" + c.addColumn(3) + "元。");
		income.setBounds(50, 450, 900, 50);
		income.setEditable(false);
		income.setHorizontalAlignment(JTextField.CENTER);
		income.setOpaque(false);
		income.setBackground(new Color(0, 0, 255));
		income.setBorder(new EmptyBorder(0, 0, 0, 0));
		income.setFont(new Font("微软雅黑", Font.BOLD, 37));
		profit.setBounds(50, 550, 900, 50);
		profit.setEditable(false);
		profit.setHorizontalAlignment(JTextField.CENTER);
		profit.setOpaque(false);
		profit.setBackground(new Color(0, 0, 255));
		profit.setBorder(new EmptyBorder(0, 0, 0, 0));
		profit.setFont(new Font("微软雅黑", Font.BOLD, 37));
		contentPane.add(income);
		contentPane.add(profit);

		JButton confirm = new JButton("确 认");
		confirm.setFont(new Font("黑体", Font.ROMAN_BASELINE, 30));
		confirm.setBounds(410, 650, 180, 50);
		confirm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				cool.dispose();
			}
		});
		contentPane.add(confirm);

		cool.setVisible(true);
	}
	// public static void main(String[] args) throws SQLException {
	// // TODO Auto-generated method stub
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
	// new financialReport(sta,"2017",1);
	// }

}
