package escolaSelect.br.com.escolaSelect.chatBot.util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class CodigosReutilizaveisUI {
	final public static Color corDesfoco = new Color(255, 255, 255);
	final public static Color corFoco = new Color(220, 236, 241);
	final public static Color corFocoSenha = new Color(220, 236, 241);
	final public static Color corBordaCampoFoco = new Color(25, 118, 210);
	final public static Color corBordaCampoDesfoco = new Color(180, 180, 180);
	public static final Color corErroSenha = new Color(242, 220, 220);
	public static final Color corErroBordaSenha = new Color(210, 25, 25);

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

	public static JButton botao(String texto, String toolTipTexto) {
		JButton botao = new JButton(texto);
		botao.setFont(new Font("Arial", Font.BOLD, 16));
		botao.setPreferredSize(new Dimension(200, 40));
		botao.setToolTipText(toolTipTexto);
		botao.setFocusPainted(false);
		botao.setBackground(new Color(25, 118, 210));
		botao.setForeground(Color.WHITE);
		return botao;
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

	public static boolean validarSenha(JPasswordField campoSenha, JPasswordField campoConfirmaSenha) {
		char[] txtSenha = campoSenha.getPassword();
		char[] txtConfirmaSenha = campoConfirmaSenha.getPassword();

		boolean iguais = Arrays.equals(txtSenha, txtConfirmaSenha);

		Arrays.fill(txtSenha, ' ');
		Arrays.fill(txtConfirmaSenha, ' ');

		return iguais;
	}

	public static Integer aleatorizarNumero() {
		int numMax = 9999;
		int numMin = 1000;

		int numeroRandom = (int) (Math.random() * (numMax - numMin + 1)) + numMin;

		return numeroRandom;
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
}
