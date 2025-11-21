package escolaSelect.br.com.escolaSelect.chatBot.view.telaPincipal;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TelaRecuperarConta {

	private static JTextField campoEmail;

	public static void recuperarConta() {

		JFrame frame = new JFrame("Recuperar Conta");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(480, 350);
		frame.setLayout(new BorderLayout());
		frame.setLocationRelativeTo(null);

		JPanel norte = new JPanel();
		norte.setLayout(new BoxLayout(norte, BoxLayout.Y_AXIS));
		norte.add(Box.createRigidArea(new Dimension(0, 20)));
		norte.add(TelaLogin.labelTitulo("<html><div style='text-align:center;'>Recuperação de Conta</div></html>"));
		norte.add(Box.createRigidArea(new Dimension(0, 10)));
		norte.add(TelaLogin.labelNormal("Informe seu e-mail para recuperar o acesso:"));
		norte.add(Box.createRigidArea(new Dimension(0, 20)));

		frame.add(norte, BorderLayout.NORTH);

		JPanel centro = new JPanel();
		centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));

		campoEmail = TelaLogin.campoTexto("Digite seu e-mail cadastrado");

		centro.add(TelaLogin.labelNormal("E-mail:"));
		centro.add(campoEmail);
		centro.add(Box.createVerticalStrut(20));

		frame.add(centro, BorderLayout.CENTER);

		JPanel sul = new JPanel();
		sul.setLayout(new BoxLayout(sul, BoxLayout.Y_AXIS));

		JButton btnEnviar = TelaLogin.botao("Enviar", "Enviar e-mail de recuperação");

		btnEnviar.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Se o e-mail existir no sistema, você receberá instruções.", "Recuperação enviada", JOptionPane.INFORMATION_MESSAGE));

		sul.add(btnEnviar);
		sul.add(Box.createVerticalStrut(15));
		sul.add(TelaLogin.criarCopyright());
		sul.add(Box.createVerticalStrut(10));

		frame.add(sul, BorderLayout.SOUTH);

		frame.setVisible(true);
	}
}
