package escolaSelect.br.com.escolaSelect.chatBot.view.telaPincipal;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class TelaAuxRecuperarConta {

	public static void abrirTelaRec() {

		JFrame frame = new JFrame("Escola Select - Atualizando senha");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(480, 450);
		frame.setLayout(new BorderLayout());
		frame.setLocationRelativeTo(null);

		frame.setVisible(true);
	}

}
