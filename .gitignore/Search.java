package contents;

import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import com.mysql.jdbc.Statement;

//在选择的表中模糊查找
public class Search {
	private String tableName;
	private String keyName;
	private String key;

	public Search(Statement sta, String table, String keyCol, String key, boolean display) throws SQLException {
		tableName = table;
		keyName = keyCol;
		this.key = key;
		String SQL = "select * from " + tableName + " where " + keyName + " like '%" + this.key + "%' ";
		ResultSet result = sta.executeQuery(SQL);
		JFrame searchResult = new JFrame("RESULT");
		searchResult.setSize(1400, 900);
		searchResult.setResizable(false);
		searchResult.setLayout(null);
		searchResult.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		DBTable b = new DBTable(result);
		if (display == true)
			JOptionPane.showMessageDialog(null, SQL, "SQL", JOptionPane.PLAIN_MESSAGE);
		if (b.getRowCount() == 0) {
			searchResult.dispose();
			JOptionPane.showMessageDialog(null, "找不到匹配信息", "FAIL", JOptionPane.PLAIN_MESSAGE);
			return;
		}
		JTable c = new JTable(b.getdata(), b.getcolumnNames());
		c.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		JScrollPane scrollPane = new JScrollPane(c);
		scrollPane.setBounds(0, 0, 1400, 900);
		searchResult.add(scrollPane);
		searchResult.setLocation(200, 100);
		searchResult.setVisible(true);

	}

	public Search(Statement sta, String table, String keyCol, String key, int o, boolean display) throws SQLException {
		tableName = table;
		keyName = keyCol;
		this.key = key;
		String SQL = "select * from " + tableName + " where " + keyName + " =  " + this.key + " ";
		ResultSet result = sta.executeQuery(SQL);
		JFrame searchResult = new JFrame("RESULT");
		searchResult.setSize(1400, 900);
		searchResult.setResizable(false);
		searchResult.setLayout(null);
		searchResult.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		DBTable b = new DBTable(result);
		if (display == true)
			JOptionPane.showMessageDialog(null, SQL, "SQL", JOptionPane.PLAIN_MESSAGE);
		if (b.getRowCount() == 0) {
			searchResult.dispose();
			JOptionPane.showMessageDialog(null, "找不到匹配信息", "FAIL", JOptionPane.PLAIN_MESSAGE);
			return;
		}
		JTable c = new JTable(b.getdata(), b.getcolumnNames());
		c.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		JScrollPane scrollPane = new JScrollPane(c);
		scrollPane.setBounds(0, 0, 1400, 900);
		searchResult.add(scrollPane);
		searchResult.setLocation(200, 100);
		searchResult.setVisible(true);

	}

	public Search(ResultSet result) throws SQLException {
		JFrame searchResult = new JFrame("RESULT");
		searchResult.setSize(1400, 900);
		searchResult.setResizable(false);
		searchResult.setLayout(null);
		searchResult.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		DBTable b = new DBTable(result);
		if (b.getRowCount() == 0) {
			searchResult.dispose();
			JOptionPane.showMessageDialog(null, "找不到匹配信息", "FAIL", JOptionPane.PLAIN_MESSAGE);
			return;
		}
		JTable c = new JTable(b.getdata(), b.getcolumnNames());
		c.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		JScrollPane scrollPane = new JScrollPane(c);
		scrollPane.setBounds(0, 0, 1400, 900);
		searchResult.add(scrollPane);
		searchResult.setLocation(200, 100);
		searchResult.setVisible(true);
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
	// Statement sta = (Statement)
	// con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
	// new Search(sta,"waiter","name","典");
	// }

}
