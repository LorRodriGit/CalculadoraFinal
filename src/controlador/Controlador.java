package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import vista.Vista;

public class Controlador {
	private Vista vista;
	private JButton [] aux;
	private boolean operar=false;
	private boolean comaActivada=false;
	private String memoria;
	
	public Controlador(Vista vista2) {
		vista=vista2;
		manejarbotonera();
		manejarOperadores();	
		//Mensajeillo de clase
	}

	private void manejarOperadores() {
		aux=new JButton[vista.getBotoneraCompleta().getVistaOperadores().getBotonesOperadores().length];
		aux=vista.getBotoneraCompleta().getVistaOperadores().getBotonesOperadores();
		for (int i = 0; i < aux.length; i++) {
			aux[i].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if (e.getActionCommand().equals("M+")||e.getActionCommand().equals("M-")||e.getActionCommand().equals("MC")||e.getActionCommand().equals("MR")) {
						gestionarMemorias(e);
					}else if (e.getActionCommand().equals("/")||e.getActionCommand().equals("X")||e.getActionCommand().equals("+")||e.getActionCommand().equals("-")) {
						realizarOperacion(e);
					}else if (e.getActionCommand().equals("sqrt")) {
						realizarRaiz();
					}else if (e.getActionCommand().equals("+/-")) {
						cambiarSimbolo();
					}else if (e.getActionCommand().equals("<--")) {
						eliminarUltimo();
					}else if (e.getActionCommand().equals("AC")) {
						estadoInicial();
					}
					
				}
			});
		}
	}

	protected void estadoInicial() {
		// TODO Auto-generated method stub
		//comenrtario git
	}

	protected void eliminarUltimo() {
		// TODO Auto-generated method stub
		
	}

	protected void cambiarSimbolo() {
		// TODO Auto-generated method stub
		
	}

	protected void realizarRaiz() {
		// TODO Auto-generated method stub
		
	}

	protected void realizarOperacion(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	protected void gestionarMemorias(ActionEvent e) {
		String memo=e.getActionCommand();
		switch (memo) {
		case "M+":
			break;
		case "M-":
			break;
		case "MC":
			break;
		case "MR":
			break;
		
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
