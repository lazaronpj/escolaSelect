package escolaSelect.br.com.escolaSelect.chatBot.view.telaPincipal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class TelaLogin {

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
		norte.add(labelTitulo("<html><div style='text-align:center;'>" + "Bem-vindo(a) ao sistema de login da <b>Escola Select</b>" + "</div></html>"));
		norte.add(Box.createVerticalStrut(10));
		norte.add(labelTitulo("Faça seu login abaixo com suas credenciais abaixo!"));
		norte.add(Box.createRigidArea(new Dimension(0, 20)));
		frame.add(norte, BorderLayout.NORTH);

		// Centro
		JPanel centro = new JPanel();
		centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));
		centro.add(Box.createVerticalStrut(20));

		final Color corDesfoco = new Color(255, 255, 255);
		final Color corFoco = new Color(220, 236, 241);

		centro.add(labelNormal("Digite seu E-mail: "));

		JTextField textoEmail = campoTexto("Digite seu e-mail corretamente");
		textoEmail.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				textoEmail.setBackground(corDesfoco);
			}

			@Override
			public void focusGained(FocusEvent e) {
				textoEmail.setBackground(corFoco);
			}
		});

		textoEmail.addKeyListener(new KeyAdapter() {

			public void keyReleased(KeyEvent e) {
				String txt = textoEmail.getText().trim();
				if (txt.length() >= 38) {
					alerta("O campo de e-mail deve possuir 38 ou menos caracteres!");
					textoEmail.setText("");
				} else {
					textoEmail.setText(txt);
				}

			}
		});

		centro.add(textoEmail);
		centro.add(Box.createVerticalStrut(5));
		centro.add(labelNormal("Digite sua senha: "));
		JPasswordField textoSenha = campoSenha("Digite sua senha cadastrada corretamente");
		textoSenha.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				textoSenha.setBackground(corDesfoco);
			}

			@Override
			public void focusGained(FocusEvent e) {
				textoSenha.setBackground(corFoco);
			}
		});

		textoSenha.addKeyListener(new KeyAdapter() {

			public void keyReleased(KeyEvent e) {

				char[] txt = textoSenha.getPassword();
				if (txt.length >= 38) {
					alerta("O campo senha deve possuir 38 ou menos caracteres!");
					textoSenha.setText("");
					java.util.Arrays.fill(txt, '0');
				}

			}
		});

		centro.add(textoSenha);
		centro.add(Box.createVerticalStrut(20));
		frame.add(centro, BorderLayout.CENTER);

		// Sul
		JPanel sul = new JPanel();
		sul.setLayout(new BoxLayout(sul, BoxLayout.Y_AXIS));

		JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

		JButton entrar = botao("Entrar", "Clique aqui para fazer login");
		entrar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		JButton recuperarSenha = botao("Recuperar senha", "Clique aqui para recuperar a senha");
		recuperarSenha.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				TelaRecuperarConta.recuperarConta();

			}

		});

		JButton criarConta = botao("Criar conta", "Clique aqui para criar sua conta");
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
		sul.add(criarCopyright());
		sul.add(Box.createVerticalStrut(10));

		frame.add(sul, BorderLayout.SOUTH);

		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public static JLabel labelTitulo(String htmlTexto) {
		JLabel label = new JLabel(htmlTexto);
		label.setFont(new Font("Arial", Font.PLAIN, 16));
		label.setForeground(new Color(30, 30, 30));
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		label.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
		label.setPreferredSize(new Dimension(600, 30));
		return label;
	}

	public static JLabel labelNormal(String texto) {
		JLabel label = new JLabel(texto);
		label.setFont(new Font("Arial", Font.PLAIN, 16));
		label.setForeground(new Color(40, 40, 40));
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		label.setMaximumSize(new Dimension(340, 30));
		return label;
	}

	public static JLabel labelBold(String texto) {
		JLabel label = new JLabel(texto);
		label.setFont(new Font("Arial", Font.BOLD, 16));
		label.setForeground(new Color(30, 30, 30));
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		label.setMaximumSize(new Dimension(340, 30));
		return label;
	}

	public static JTextField campoTexto(String toolTip) {
		JTextField campo = new JTextField();
		campo.setPreferredSize(new Dimension(340, 35));
		campo.setMaximumSize(new Dimension(340, 35));
		campo.setFont(new Font("Arial", Font.PLAIN, 18));
		campo.setToolTipText(toolTip);
		campo.setMargin(new Insets(5, 8, 5, 8));
		campo.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180)));
		return campo;
	}

	public static JPasswordField campoSenha(String toolTip) {
		JPasswordField campo = new JPasswordField();
		campo.setPreferredSize(new Dimension(340, 35));
		campo.setMaximumSize(new Dimension(340, 35));
		campo.setFont(new Font("Arial", Font.PLAIN, 18));
		campo.setToolTipText(toolTip);
		campo.setMargin(new Insets(5, 8, 5, 8));
		campo.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180)));
		return campo;
	}

	public static JButton botao(String texto, String toolTip) {
		JButton btn = new JButton(texto);
		btn.setFont(new Font("Arial", Font.BOLD, 16));
		btn.setPreferredSize(new Dimension(200, 40));
		btn.setToolTipText(toolTip);
		btn.setFocusPainted(false);
		btn.setBackground(new Color(25, 118, 210));
		btn.setForeground(Color.WHITE);
		return btn;
	}

	public static JPanel criarCopyright() {
		JLabel copy = new JLabel("<html><div style='text-align:center; color:#808080;'>" + "© 2025 Lazaro Coder <span style='color:#C0C0C0;'>v1.0</span>" + "</div></html>");
		copy.setFont(new Font("Arial", Font.PLAIN, 16));

		JPanel painelCopyright = new JPanel(new FlowLayout(FlowLayout.CENTER));
		painelCopyright.add(copy);
		painelCopyright.setAlignmentX(Component.CENTER_ALIGNMENT);
		painelCopyright.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
		return painelCopyright;
	}

	public static void alerta(String msg) {
		JOptionPane.showMessageDialog(null, "<html>⚠️ <b><font color='orange'>Aviso:</font></b> " + msg + "</html>", "Aviso", JOptionPane.WARNING_MESSAGE);
	}

	public static void alertaSucesso(String msg) {
		JOptionPane.showMessageDialog(null, "<html>✅ <b><font color='green'>Sucesso:</font></b> " + msg + "</html>", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
	}

	public static void alertaErro(String msg) {
		JOptionPane.showMessageDialog(null, "<html>❌ <b><font color='red'>Erro:</font></b> " + msg + "</html>", "Erro", JOptionPane.ERROR_MESSAGE);
	}

	public static void main(String[] args) {
		abrirTela();
	}

}
