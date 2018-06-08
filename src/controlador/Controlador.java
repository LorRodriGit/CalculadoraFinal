package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import vista.Vista;

public class Controlador {
	Vista vista;
	JButton [] aux;
	boolean operar=false;
	boolean comaActivada=false;
	
	public Controlador(Vista vista2) {
		vista=vista2;
		manejarbotonera();
		manejarOperadores();	
	}

	private void manejarOperadores() {
		aux=new JButton[vista.getBotoneraCompleta().getVistaOperadores().getBotonesOperadores().length];
		aux=vista.getBotoneraCompleta().getVistaOperadores().getBotonesOperadores();
		for (int i = 0; i < aux.length; i++) {
			
		}
	}

	private void manejarbotonera() {
		aux=new JButton[vista.getBotoneraCompleta().getVistaNumeros().getBotonesNumericos().length];
		aux=vista.getBotoneraCompleta().getVistaNumeros().getBotonesNumericos();
		vista.getTextoPantalla().requestFocus();
		for (int i = 0; i < aux.length; i++) {
			aux[i].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					modificarPantalla(e);
					
					
					
					
				}
				
			});
		}
	}

	protected void resultado() {
		// TODO Auto-generated method stub
		
	}

	protected void controlarComa() {
		if (vista.getTextoPantalla().getText().equals("0") && !comaActivada) {
			vista.getTextoPantalla().setText("0.");
			comaActivada=true;
		}else if (!vista.getTextoPantalla().getText().equals("0") && !comaActivada) {
			vista.getTextoPantalla().setText(vista.getTextoPantalla().getText()+".");
			comaActivada=true;
		}else if (comaActivada) {
			vista.getTextoPantalla().setText(vista.getTextoPantalla().getText());
		}
		
	}

	protected void modificarPantalla(ActionEvent e) {
		if (e.getActionCommand().equals(".")) {
			controlarComa();
		}else if (e.getActionCommand().equals("=")) {
			resultado();
		}else if(!vista.getTextoPantalla().getText().equals("0")) {
			vista.getTextoPantalla().setText(vista.getTextoPantalla().getText()+e.getActionCommand());
		}else {
			vista.getTextoPantalla().setText(e.getActionCommand());
		}
		
	}
}
