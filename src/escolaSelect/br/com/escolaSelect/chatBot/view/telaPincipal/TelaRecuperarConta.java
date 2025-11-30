package escolaSelect.br.com.escolaSelect.chatBot.view.telaPincipal;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import escolaSelect.br.com.escolaSelect.chatBot.dao.PessoaDAO;
import escolaSelect.br.com.escolaSelect.chatBot.util.CodigosReutilizaveisUI;

public class TelaRecuperarConta {

	private static JTextField campoEmail, campoCodigo;
	private static Integer codGerado = null;

	public static void recuperarConta() {

		JFrame frame = new JFrame("Recuperar Conta");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(500, 450);
		frame.setLayout(new BorderLayout());
		frame.setLocationRelativeTo(null);

		JPanel norte = new JPanel();
		norte.setLayout(new BoxLayout(norte, BoxLayout.Y_AXIS));
		norte.setBorder(new EmptyBorder(20, 20, 10, 20));
		norte.add(CodigosReutilizaveisUI.labelTitulo("<html><div style='text-align:center;'>Recuperação de Conta</div></html>"));
		norte.add(Box.createRigidArea(new Dimension(0, 10)));
		norte.add(CodigosReutilizaveisUI.labelNormal("<html>Informe seu <b>e-mail cadastrado</b> no sistema:</html>"));
		norte.add(Box.createRigidArea(new Dimension(0, 20)));

		frame.add(norte, BorderLayout.NORTH);

		JPanel painelCampos = new JPanel();
		painelCampos.setLayout(new BoxLayout(painelCampos, BoxLayout.Y_AXIS));
		painelCampos.setBorder(new EmptyBorder(0, 40, 0, 40));

		campoEmail = CodigosReutilizaveisUI.campoTexto("Digite seu e-mail cadastrado");
		campoCodigo = CodigosReutilizaveisUI.campoTexto("Digite o código de recuperação enviado");
		campoCodigo.setEditable(false);

		painelCampos.add(CodigosReutilizaveisUI.labelNormal("E-mail:"));
		painelCampos.add(Box.createRigidArea(new Dimension(0, 5)));
		painelCampos.add(campoEmail);

		painelCampos.add(Box.createRigidArea(new Dimension(0, 25)));

		painelCampos.add(CodigosReutilizaveisUI.labelNormal("Código de recuperação:"));
		painelCampos.add(Box.createRigidArea(new Dimension(0, 5)));
		painelCampos.add(campoCodigo);

		frame.add(painelCampos, BorderLayout.CENTER);

		campoEmail.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				campoEmail.setBackground(CodigosReutilizaveisUI.corDesfoco);
				campoEmail.setBorder(BorderFactory.createLineBorder(CodigosReutilizaveisUI.corBordaCampoDesfoco, 2));
			}

			@Override
			public void focusGained(FocusEvent e) {
				campoEmail.setBackground(CodigosReutilizaveisUI.corFoco);
				campoEmail.setBorder(BorderFactory.createLineBorder(CodigosReutilizaveisUI.corBordaCampoFoco));
			}
		});

		JPanel sul = new JPanel();
		sul.setLayout(new BoxLayout(sul, BoxLayout.Y_AXIS));
		sul.setBorder(new EmptyBorder(10, 20, 10, 20));

		JButton botaoEnviar = CodigosReutilizaveisUI.botao("Enviar Código", "Enviar e-mail de recuperação");
		JButton botaoRecuperar = CodigosReutilizaveisUI.botao("Recuperar", "Preencha corretamente o código enviado por e-mail");
		botaoEnviar.setEnabled(true);
		botaoEnviar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PessoaDAO psd = new PessoaDAO();
				String txtEmail = campoEmail.getText().trim();

				if (txtEmail.isBlank()) {
					CodigosReutilizaveisUI.alerta("O campo e-mail não pode ficar vazio!");
					return;
				}

				boolean emailExiste = psd.verificarEmail(txtEmail);

				if (emailExiste) {
					CodigosReutilizaveisUI.alerta("Código de recuperação enviado Verifique sua caixa de entrada.");
					codGerado = CodigosReutilizaveisUI.aleatorizarNumero();
					JOptionPane.showMessageDialog(frame, "<html>O sistema gerou o código: <b>" + codGerado + "</b></html>", "Código Gerado (Simulação)", JOptionPane.INFORMATION_MESSAGE);

					campoCodigo.setEditable(true);
					botaoRecuperar.setEnabled(true);
					botaoEnviar.setEnabled(false);

					campoCodigo.addFocusListener(new FocusListener() {

						@Override
						public void focusLost(FocusEvent e) {
							campoCodigo.setBackground(CodigosReutilizaveisUI.corDesfoco);
							campoCodigo.setBorder(BorderFactory.createLineBorder(CodigosReutilizaveisUI.corBordaCampoDesfoco, 2));
						}

						@Override
						public void focusGained(FocusEvent e) {
							campoCodigo.setBackground(CodigosReutilizaveisUI.corFoco);
							campoCodigo.setBorder(BorderFactory.createLineBorder(CodigosReutilizaveisUI.corBordaCampoFoco));
						}
					});

				} else {
					CodigosReutilizaveisUI.alerta("Esse e-mail não existe em nossa base de dados!");
				}
			}
		});

		botaoRecuperar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int codigoGerado = CodigosReutilizaveisUI.aleatorizarNumero();

					String campoCd = campoCodigo.getText().trim();

					codigoGerado = Integer.parseInt(campoCd);

					if (codigoGerado == codGerado) {
						CodigosReutilizaveisUI.alerta("Código confirmado");
						campoEmail.setEditable(false);
						campoCodigo.setEditable(false);
						TelaAuxRecuperarConta.abrirTelaRec();
					} else {
						CodigosReutilizaveisUI.alerta("Códigos não confere");
					}
				} catch (NumberFormatException exx) {
					CodigosReutilizaveisUI.alerta("Formato de código inválido. Digite apenas números.");
				}
			}
		});
		botaoRecuperar.setEnabled(false);
		JPanel painelBotao = new JPanel(new FlowLayout(FlowLayout.CENTER));
		painelBotao.add(botaoEnviar);
		painelBotao.add(botaoRecuperar);

		sul.add(painelBotao);
		sul.add(Box.createVerticalStrut(15));
		sul.add(CodigosReutilizaveisUI.criarCopyright());
		sul.add(Box.createVerticalStrut(10));

		frame.add(sul, BorderLayout.SOUTH);

		frame.setVisible(true);
	}
}
