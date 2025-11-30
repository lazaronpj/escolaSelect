package escolaSelect.br.com.escolaSelect.chatBot.view.telaPincipal;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import escolaSelect.br.com.escolaSelect.chatBot.dao.PessoaDAO;
import escolaSelect.br.com.escolaSelect.chatBot.util.CodigosReutilizaveisUI;
import escolaSelect.br.com.escolaSelect.chatBot.view.chat.TelaChat;

public class TelaLogin {

	private static JTextField campoEmail;
	private static JPasswordField campoSenha;

	public static void abrirTela() {
		JFrame frame = new JFrame("Escola Select - Login");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(700, 450);
		frame.setResizable(false);
		frame.setLayout(new BorderLayout());

		// Norte
		JPanel norte = new JPanel();
		norte.setLayout(new BoxLayout(norte, BoxLayout.Y_AXIS));
		norte.setBorder(BorderFactory.createEmptyBorder(5, 15, 0, 0));
		norte.add(Box.createRigidArea(new Dimension(0, 30)));
		norte.add(CodigosReutilizaveisUI.labelTitulo("<html><div style='text-align:center;'>" + "Bem-vindo(a) ao sistema de login da <b>Escola Select</b>" + "</div></html>"));
		norte.add(Box.createVerticalStrut(10));
		norte.add(CodigosReutilizaveisUI.labelTitulo("Faça seu login abaixo com suas credenciais abaixo!"));
		norte.add(Box.createRigidArea(new Dimension(0, 20)));
		frame.add(norte, BorderLayout.NORTH);

		// Centro
		JPanel centro = new JPanel();
		centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));
		centro.add(Box.createVerticalStrut(20));
		centro.add(CodigosReutilizaveisUI.labelNormal("Digite seu E-mail: "));

		campoEmail = CodigosReutilizaveisUI.campoTexto("Digite seu e-mail corretamente");

		campoEmail.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				campoEmail.setBackground(CodigosReutilizaveisUI.corDesfoco);
				campoEmail.setBorder(BorderFactory.createLineBorder(CodigosReutilizaveisUI.corBordaCampoDesfoco, 2));
			}

			@Override
			public void focusGained(FocusEvent e) {
				campoEmail.setBackground(CodigosReutilizaveisUI.corFoco);
				campoEmail.setBorder(BorderFactory.createLineBorder(CodigosReutilizaveisUI.corBordaCampoFoco, 2));

			}
		});

		campoEmail.addKeyListener(new KeyAdapter() {

			public void keyReleased(KeyEvent e) {
				String txt = campoEmail.getText().trim();
				if (txt.length() >= 38) {
					CodigosReutilizaveisUI.alerta("O campo de e-mail deve possuir 38 ou menos caracteres!");
					campoEmail.setText("");
				} else {
					campoEmail.setText(txt);
				}

			}
		});

		centro.add(campoEmail);
		centro.add(Box.createVerticalStrut(5));
		centro.add(CodigosReutilizaveisUI.labelNormal("Digite sua senha: "));
		campoSenha = CodigosReutilizaveisUI.campoSenha("Digite sua senha cadastrada corretamente");
		campoSenha.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				campoSenha.setBackground(CodigosReutilizaveisUI.corDesfoco);
				campoSenha.setBorder(BorderFactory.createLineBorder(CodigosReutilizaveisUI.corBordaCampoDesfoco, 2));
			}

			@Override
			public void focusGained(FocusEvent e) {
				campoSenha.setBackground(CodigosReutilizaveisUI.corFoco);
				campoSenha.setBorder(BorderFactory.createLineBorder(CodigosReutilizaveisUI.corBordaCampoFoco, 2));
			}
		});

		campoSenha.addKeyListener(new KeyAdapter() {

			public void keyReleased(KeyEvent e) {

				char[] txt = campoSenha.getPassword();
				if (txt.length >= 38) {
					CodigosReutilizaveisUI.alerta("O campo senha deve possuir 38 ou menos caracteres!");
					campoSenha.setText("");
					java.util.Arrays.fill(txt, '0');
				}

			}
		});

		centro.add(campoSenha);
		centro.add(Box.createVerticalStrut(20));
		frame.add(centro, BorderLayout.CENTER);

		// Sul
		JPanel sul = new JPanel();
		sul.setLayout(new BoxLayout(sul, BoxLayout.Y_AXIS));

		JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

		JButton entrar = CodigosReutilizaveisUI.botao("Entrar", "Clique aqui para fazer login");
		entrar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				PessoaDAO psd = new PessoaDAO();

				String txtEmail = campoEmail.getText().trim();
				char[] txtSenha = campoSenha.getPassword();
				String txtSenhaString = new String(txtSenha);

				if (txtEmail.isBlank() && txtSenha.length == 0) {
					CodigosReutilizaveisUI.alerta("Os campos 'e-mail' e 'senha' não podem ficar vazios!");
					return;
				} else {
					boolean entrou = psd.fazerLogin(txtEmail, txtSenhaString);

					if (entrou) {

						TelaChat.abrirTelaChat();
						campoEmail.setText("");
						campoSenha.setText("");

					} else {
						CodigosReutilizaveisUI.alerta("E-mail ou senha incorretos!");
					}
				}
			}
		});

		JButton recuperarSenha = CodigosReutilizaveisUI.botao("Recuperar senha", "Clique aqui para recuperar a senha");
		recuperarSenha.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				TelaRecuperarConta.recuperarConta();
			}
		});

		JButton criarConta = CodigosReutilizaveisUI.botao("Criar conta", "Clique aqui para criar sua conta");
		criarConta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				TelaCriarConta.criarConta();

			}
		});

		painelBotoes.setAlignmentX(Component.CENTER_ALIGNMENT);
		painelBotoes.add(entrar);
		painelBotoes.add(recuperarSenha);
		painelBotoes.add(criarConta);

		sul.add(painelBotoes);
		sul.add(Box.createVerticalStrut(15));
		sul.add(CodigosReutilizaveisUI.criarCopyright());
		sul.add(Box.createVerticalStrut(10));

		frame.add(sul, BorderLayout.SOUTH);

		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		abrirTela();
	}

}
