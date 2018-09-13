package contents;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

@SuppressWarnings("serial")
public class TipTextField extends JTextField {
	int edited = 0;
	public TipTextField(String initial, int clicktimes) {
		edited = clicktimes;
		setForeground(Color.gray);
		setText(initial);
		setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 16));
		setHorizontalAlignment(CENTER);
		addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				if (edited == 1) {
					setText("");

				}
				setForeground(Color.BLACK);
				edited++;
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				setForeground(Color.blue);
			}
		});
	}

}