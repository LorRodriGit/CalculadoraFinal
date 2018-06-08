package vista;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class VistaNumeros extends JPanel {
	private String numeros[]= {"7","8","9","4","5","6","1","2","3","0",".","="};
	private JButton botonesNumericos[];
	
	public VistaNumeros() {
		this.setLayout(new GridLayout(4, 3,6,6));
		this.setBorder(new EmptyBorder(6, 6, 6, 6));
		botonesNumericos=new JButton[numeros.length];
		for (int i = 0; i < botonesNumericos.length; i++) {
			botonesNumericos[i]=new JButton(numeros[i]);
			this.add(botonesNumericos[i]);
		}
	}

	public JButton[] getBotonesNumericos() {
		return botonesNumericos;
	}

	public void setBotonesNumericos(JButton[] botonesNumericos) {
		this.botonesNumericos = botonesNumericos;
	}

}
