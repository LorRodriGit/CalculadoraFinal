package controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import vista.Vista;

public class Controlador {
	private Vista vista;
	private JButton[] aux;
	private boolean operar = false;
	private boolean nuevoDigito = false;
	private ActionEvent operacionAnterior;
	private double subtotal = 0, memoria = 0, total = 0, otro = 0;

	public Controlador(Vista vista2) {
		vista = vista2;
		manejarbotonera();
		manejarOperadores();
	}

	private void manejarOperadores() {
		aux = new JButton[vista.getBotoneraCompleta().getVistaOperadores().getBotonesOperadores().length];
		aux = vista.getBotoneraCompleta().getVistaOperadores().getBotonesOperadores();
		for (int i = 0; i < aux.length; i++) {
			aux[i].addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (e.getActionCommand().equals("M+") || e.getActionCommand().equals("M-")
							|| e.getActionCommand().equals("MC") || e.getActionCommand().equals("MR")) {
						gestionarMemorias(e);
					} else if (e.getActionCommand().equals("/") || e.getActionCommand().equals("X")
							|| e.getActionCommand().equals("+") || e.getActionCommand().equals("-")) {
						if (operacionAnterior == null) {
							subtotal = Double.valueOf(vista.getTextoPantalla().getText());
							nuevoDigito=true;
						} else {
							vista.getTextoPantalla().setText(realizarOperacion(operacionAnterior,
									Double.valueOf(vista.getTextoPantalla().getText())));
						}
						operacionAnterior = e;

					} else if (e.getActionCommand().equals("√")) {
						realizarRaiz();
					} else if (e.getActionCommand().equals("+/-")) {
						cambiarSimbolo();
					} else if (e.getActionCommand().equals("◄")) {
						eliminarUltimo();
					} else if (e.getActionCommand().equals("A/C")) {
						estadoInicial();
					} else if (e.getActionCommand().equals("=")) {
						if (operacionAnterior != null) {
							vista.getTextoPantalla().setText(realizarOperacion(operacionAnterior,
									Double.valueOf(vista.getTextoPantalla().getText())));
							operacionAnterior = null;
						}
					}
				}
			});
		}
	}

	protected void estadoInicial() {
		vista.getTextoPantalla().setText("0");
		memoria = 0;
		subtotal = 0;
		nuevoDigito = false;
		total = 0;

	}

	protected void eliminarUltimo() {
		if (vista.getTextoPantalla().getText().length() == 1) {
			vista.getTextoPantalla().setText("0");
		} else {
			vista.getTextoPantalla().setText(
					vista.getTextoPantalla().getText().substring(0, vista.getTextoPantalla().getText().length() - 1));
		}
	}

	protected void cambiarSimbolo() {
		double valor = Double.parseDouble(vista.getTextoPantalla().getText());
		if (valor > 0) {
			// pantalla.setText("-" + pantalla.getText());
			vista.getTextoPantalla().setText("-" + vista.getTextoPantalla().getText());
			vista.getTextoPantalla().setForeground(Color.RED);
		} else if (valor < 0) {/// aqui puede tener un simbolo menos
			if (valor < 0 && vista.getTextoPantalla().getText().contains("-")) {
				String[] aux = vista.getTextoPantalla().getText().split("-");
				vista.getTextoPantalla().setText(aux[1]);
			}
			vista.getTextoPantalla().setText("" + vista.getTextoPantalla().getText());
			vista.getTextoPantalla().setForeground(Color.BLACK);
		}
	}

	protected void realizarRaiz() {
		subtotal = Math.sqrt(Double.parseDouble(vista.getTextoPantalla().getText()));
		vista.getTextoPantalla().setText(String.valueOf(subtotal));
		nuevoDigito = true;
	}

	protected String realizarOperacion(ActionEvent e, Double operando) {
		if (operacionAnterior != null) {
			getResult(operacionAnterior.getActionCommand(), operando);
		}
		return String.valueOf(subtotal);
	}

	private void getResult(String operador, Double operando) {
		switch (operador) {
		case "+":
			subtotal += operando;
			break;
		case "-":
			if (subtotal == 0) {
				subtotal = operando;
			} else {
				subtotal -= operando;
			}
			break;
		case "/":
			if (subtotal == 0)
				subtotal = 1;
			subtotal /= operando;
			break;
		case "X":
			if (subtotal == 0)
				subtotal = 1;
			subtotal *= operando;
			break;
		}
		nuevoDigito = true;
	}

	protected void resultado() {
		vista.getTextoPantalla().setText(String.valueOf(total));

	}

	protected void gestionarMemorias(ActionEvent e) {
		String memo = e.getActionCommand();
		switch (memo) {
		case "M+":
			memoria += Double.parseDouble(vista.getTextoPantalla().getText());
			break;
		case "M-":
			memoria -= Double.parseDouble(vista.getTextoPantalla().getText());
			break;
		case "MC":
			memoria = 0;
			break;
		case "MR":
			vista.getTextoPantalla().setText(String.valueOf(memoria));
			break;

		}
	}

	private void manejarbotonera() {
		aux = new JButton[vista.getBotoneraCompleta().getVistaNumeros().getBotonesNumericos().length];
		aux = vista.getBotoneraCompleta().getVistaNumeros().getBotonesNumericos();
		vista.getTextoPantalla().requestFocus();
		for (int i = 0; i < aux.length; i++) {
			aux[i].addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					modificarPantalla(e);
					nuevoDigito = false;

				}

			});
		}
	}

	protected void controlarComa() {
		if (!vista.getTextoPantalla().getText().contains(".")) {
			vista.getTextoPantalla().setText(vista.getTextoPantalla().getText() + ".");
		}

	}

	protected void modificarPantalla(ActionEvent e) {
		if (e.getActionCommand().equals(".")) {
			controlarComa();
		} else if (e.getActionCommand().equals("=")) {
			vista.getTextoPantalla().setText(realizarOperacion(e, Double.valueOf(vista.getTextoPantalla().getText())));
			subtotal = 0;
		} else if (!vista.getTextoPantalla().getText().equals("0") && !nuevoDigito) {
			vista.getTextoPantalla().setText(vista.getTextoPantalla().getText() + e.getActionCommand());
		} else {
			vista.getTextoPantalla().setText(e.getActionCommand());
		}

	}
}
