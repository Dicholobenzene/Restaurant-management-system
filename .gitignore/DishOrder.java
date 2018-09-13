package contents;

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

import com.mysql.jdbc.Statement;

import UI.Manager.bench;

@SuppressWarnings("serial")
public class DishOrder extends JFrame {
	public DishOrder(Statement sta, bench father) throws SQLException {
		setSize(600, 250);
		setLocation(600, 400);
		setTitle("点菜");
		setResizable(false);
		setLayout(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		TipTextField a = new TipTextField("请输入桌号", 1);
		JComboBox<String> b = new JComboBox<String>();
		ResultSet res = sta.executeQuery("select name from dishes");
		while (res.next()) {
			b.addItem(res.getString(1));
		}
		res.close();
		JButton confirm = new JButton("点菜");
		a.setBounds(45, 50, 220, 60);
		b.setBounds(325, 50, 210, 60);
		confirm.setBounds(250, 150, 100, 50);
		add(a);
		add(b);
		add(confirm);
		confirm.requestFocus();
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
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String table = a.getText();
				String name = (String) b.getSelectedItem();
				String number = null;
				try {
					ResultSet c = sta.executeQuery("select dishid from dishes where name = '" + name + "'");
					c.next();
					try {
						number = c.getString(1);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "菜品不存在！", "FAIL", JOptionPane.PLAIN_MESSAGE);
						return;
					}
					sta.execute("insert into dishorder(dish,tableid) values ( " + number + "," + table + ")");

					JOptionPane.showMessageDialog(null, "点菜成功！", "SUCCESS", JOptionPane.PLAIN_MESSAGE);
					new bench(father.getDatabase());
					father.dispose();
					c.close();
					dispose();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		});
	}
}
