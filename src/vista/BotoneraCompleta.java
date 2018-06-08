package vista;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class BotoneraCompleta extends JPanel {
	private VistaNumeros vistaNumeros;
	private VistaOperadores vistaOperadores;
	
	public BotoneraCompleta() {
		vistaNumeros=new VistaNumeros();
		vistaOperadores=new VistaOperadores();
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.add(vistaNumeros);
		this.add(vistaOperadores);
	}

	public VistaNumeros getVistaNumeros() {
		return vistaNumeros;
	}

	public VistaOperadores getVistaOperadores() {
		return vistaOperadores;
	}
	
}
