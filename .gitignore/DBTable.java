package contents;

import java.sql.*;

public class DBTable {
	private int columns, rows;
	private String[] columnNames;
	private Object[][] data;

	public DBTable(ResultSet a) throws SQLException {
		a.last();
		rows = a.getRow();
		columns = a.getMetaData().getColumnCount();
		a.first();
		columnNames = new String[columns];
		data = new Object[rows][columns];
		for (int i = 0; i < columns; i++) {
			columnNames[i] = a.getMetaData().getColumnName(i + 1);
		}
		a.first();
		if (rows != 0) {
			do {
				int b = a.getRow();
				for (int i = 0; i < columns; i++) {
					data[b - 1][i] = a.getObject(i + 1);
				}
			} while (a.next());
		}
		a.close();
	}

	public String[] getcolumnNames() {
		return columnNames;
	}

	public Object[][] getdata() {
		return data;
	}

	public int addColumn(int column) {
		int add = 0;
		for (int i = 0; i < rows; i++) {
			add += (int) data[i][column - 1];
		}
		return add;
	}

	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columns;
	}

	public int getRowCount() {
		// TODO Auto-generated method stub
		return rows;
	}

	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return data[arg0][arg1];
	}

}
