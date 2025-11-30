package escolaSelect.br.com.escolaSelect.chatBot.view.chat;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import escolaSelect.br.com.escolaSelect.chatBot.util.CodigosReutilizaveisUI;

public class TelaChat {

	public static void abrirTelaChat() {
		JFrame frame = new JFrame("Escola Select - Chat de suporte: ");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(480, 450);
		frame.setLayout(new BorderLayout());
		frame.setLocationRelativeTo(null);

		JPanel norte = new JPanel();
		norte.setLayout(new BoxLayout(norte, BoxLayout.Y_AXIS));

		norte.add(CodigosReutilizaveisUI.labelNormal("Seja bem-vindo(a) ao Chat!"));

		frame.add(norte, BorderLayout.NORTH);

		JPanel centro = new JPanel();

		frame.setVisible(true);
	}

}
