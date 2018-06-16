package vista;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class VistaOperadores extends JPanel {
	private String operadores[] = { "◄", "/", "M+", "X", "√", "M-", "+", "-", "MC", "A/C", "+/-", "MR" };
	private JButton botonesOperadores[];

	public VistaOperadores() {
		this.setLayout(new GridLayout(4, 3,6,6));
		this.setBorder(new EmptyBorder(6, 6, 6, 6));
		botonesOperadores = new JButton[operadores.length];
		for (int i = 0; i < botonesOperadores.length; i++) {
			if (i == 9) {
				botonesOperadores[i] = new JButton(operadores[i]);
				botonesOperadores[i].setBackground(Color.RED);
				this.add(botonesOperadores[i]);

			} else {
				botonesOperadores[i] = new JButton(operadores[i]);
				this.add(botonesOperadores[i]);
			}

		}

	}

	public JButton[] getBotonesOperadores() {
		return botonesOperadores;
	}
	
}
