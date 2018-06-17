package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;

import vista.Vista;

public class ControladorDos {
	String operador;
	private Vista vista;
	private JButton[] aux;
	private boolean operar = false;
	private boolean nuevoDigito = false;
	private String operacionAnterior = "=";
	private double resultado = 0, memoria = 0, total = 0, subtotal = 0, valor = 0;

	public ControladorDos(Vista vista2) {
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
						operador = e.getActionCommand();
						resultado = calculo(Double.valueOf(vista.getTextoPantalla().getText()), operacionAnterior);
						vista.getTextoPantalla().setText(String.valueOf(resultado));// vista.getTextoPantalla().setText(realizarOperacion(e));
					} else if (e.getActionCommand().equals("√")) {
						realizarRaiz();
					} else if (e.getActionCommand().equals("+/-")) {
						cambiarSimbolo();
					} else if (e.getActionCommand().equals("◄")) {
						eliminarUltimo();
					} else if (e.getActionCommand().equals("A/C")) {
						estadoInicial();
					}

				}

			});
		}
	}

	private double calculo(Double valor, String operacionAnterior) {
		double total = 0;
		if (operacionAnterior.equals("+")) {
			total += valor;
		} else if (operacionAnterior.equals("-")) {
			total -= valor;
		} else if (operacionAnterior.equals("X")) {
			total *= valor;
		} else if (operacionAnterior.equals("/")) {
			total /= valor;
		} else if (operacionAnterior.equals("=")) {
			total = valor;

		}
		return total;
	}

	protected void estadoInicial() {
		vista.getTextoPantalla().setText("0");
		operacionAnterior = "=";
		memoria = 0;
		resultado = 0;
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
		resultado = Math.sqrt(Double.parseDouble(vista.getTextoPantalla().getText()));
		operacionAnterior = "";
		vista.getTextoPantalla().setText(String.valueOf(resultado));
		nuevoDigito = true;
	}

	protected String realizarOperacion(ActionEvent e) {
		// String operador=e.getActionCommand();
		// switch (operador) {
		// case "+":
		// resultado+=Double.parseDouble(vista.getTextoPantalla().getText());
		// break;
		// case "-":
		// resultado-=Double.parseDouble(vista.getTextoPantalla().getText());
		// break;
		// case "/":
		// resultado/=Double.parseDouble(vista.getTextoPantalla().getText());
		// break;
		// case "X":
		// resultado*=Double.parseDouble(vista.getTextoPantalla().getText());
		// break;
		// case "=":
		// resultado=Double.parseDouble(vista.getTextoPantalla().getText());
		// break;
		// }
		// nuevoDigito=true;
		// comaActivada=false;
		// operacionAnterior=operador;
		//

		// vista.getTextoPantalla().setText(String.valueOf(resultado));
		String operador = e.getActionCommand();
		getResult(operador);
		if (operador.equals("=")) {
			getResult(operacionAnterior);
			;

		}

		nuevoDigito = true;
		operacionAnterior = operador;
		return String.valueOf(resultado);
	}

	private void getResult(String operador) {
		switch (operador) {
		case "+":
			resultado += Double.parseDouble(vista.getTextoPantalla().getText());
			break;
		case "-":
			if (resultado == 0) {
				resultado = Double.parseDouble(vista.getTextoPantalla().getText());
			} else {
				resultado -= Double.parseDouble(vista.getTextoPantalla().getText());
			}
			break;
		case "/":
			if (resultado == 0)
				resultado = 1;
			resultado /= Double.parseDouble(vista.getTextoPantalla().getText());
			break;
		case "X":
			if (resultado == 0)
				resultado = 1;
			resultado *= Double.parseDouble(vista.getTextoPantalla().getText());
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
			operador = e.getActionCommand();
			resultado = calculo(Double.valueOf(vista.getTextoPantalla().getText()), operacionAnterior);
			vista.getTextoPantalla().setText(String.valueOf(resultado));
		} else if (!vista.getTextoPantalla().getText().equals("0") && !nuevoDigito) {
			vista.getTextoPantalla().setText(vista.getTextoPantalla().getText() + e.getActionCommand());
		} else {
			vista.getTextoPantalla().setText(e.getActionCommand());
		}

	}
}
