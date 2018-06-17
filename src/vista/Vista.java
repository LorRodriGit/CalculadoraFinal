package vista;

import java.awt.Component;
import java.awt.GridLayout;
import java.util.Iterator;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import controlador.Controlador;
import controlador.ControladorDos;

public class Vista extends JFrame {
	private JPanel pantalla;
	private BotoneraCompleta botoneraCompleta=new BotoneraCompleta();
	private JTextField textoPantalla;
	
	public Vista() {
		this.setTitle("Calculadora");
		this.setVisible(true);
		this.setLayout(new BoxLayout(this.getContentPane(),BoxLayout.Y_AXIS));
		this.add(generarPantalla());
		this.add(botoneraCompleta);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		pack();
		
		
	}

	

	private Component generarPantalla() {
		pantalla=new JPanel();
		textoPantalla=new JTextField(40);
		textoPantalla.setText("0");
		textoPantalla.setEditable(false);
		textoPantalla.setHorizontalAlignment(JTextField.RIGHT);
		pantalla.add(textoPantalla);
		return pantalla;
	}


	public static void main(String[] args) {
		new ControladorDos(new Vista());
	}


	public JPanel getPantalla() {
		return pantalla;
	}


	public JTextField getTextoPantalla() {
		return textoPantalla;
	}

	public void setTextoPantalla(JTextField textoPantalla) {
		this.textoPantalla = textoPantalla;
	}



	public BotoneraCompleta getBotoneraCompleta() {
		return botoneraCompleta;
	}

}
