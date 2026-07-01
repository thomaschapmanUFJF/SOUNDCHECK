/*  Rafael Magno Campos da Silva - 202435012
    Gabriel Haddad Cyrino Gadioli - 202435026
    Thomas Adam Chapman - 202476030 */

package br.ufjf.dcc.soundcheck.view;

import br.ufjf.dcc.soundcheck.controller.LoginController;
import br.ufjf.dcc.soundcheck.model.*;
import br.ufjf.dcc.soundcheck.model.exceptions.LoginException;
import br.ufjf.dcc.soundcheck.view.TelasCliente.TelaMenuCliente;
import br.ufjf.dcc.soundcheck.view.TelasFuncionario.TelaMenuFuncionario;
import br.ufjf.dcc.soundcheck.view.TelasGerente.TelaMenuGerente;
import javax.swing.*;
import java.awt.*;

public class TelaLogin {
    private final JFrame frame;
    private final JTextField campoEmail;
    private final JPasswordField campoSenha;
    private final JButton botaoEntrar;
    private final JButton botaoCadastrar;

    public TelaLogin() {
        frame          = new JFrame("SoundCheck");
        campoEmail     = new JTextField(22);
        campoSenha     = new JPasswordField(22);
        botaoEntrar    = Tema.botaoPrimario("Entrar");
        botaoCadastrar = Tema.botaoSecundario("Criar conta");

        botaoEntrar.addActionListener(e -> {
            try {
                Usuario u = LoginController.autenticar(campoEmail.getText(), new String(campoSenha.getPassword()));
                abrirTelaPorPerfil(u);
                frame.dispose();
            } catch (LoginException ex) {
                JOptionPane.showMessageDialog(frame, "E-mail ou senha inválidos.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        botaoCadastrar.addActionListener(e -> new TelaAutoRegistro().abrirTelaAutoRegistro());
    }

    private void abrirTelaPorPerfil(Usuario u) {
        switch (u.getTipo()) {
            case CLIENTE:
                new TelaMenuCliente().abrirTelaMenuCliente((Cliente) u);
                break;
            case FUNCIONARIO:
                new TelaMenuFuncionario().abrirTelaMenuFuncionario((Funcionario) u);
                break;
            case GERENTE:
                new TelaMenuGerente().abrirTelaMenuGerente((Gerente) u);
                break;
            default:
                break;
        }
    }

    public void abrirLogin() {
        Tema.aplicarTema();
        Tema.estilizarFrame(frame);

        frame.setSize(400, 420);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // ── painel central ─────────────────────────────────────────────
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBackground(Tema.FUNDO);
        painel.setBorder(BorderFactory.createEmptyBorder(40, 50, 40, 50));

        JLabel titulo = Tema.labelTitulo("SoundCheck");
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitulo = Tema.labelSubtitulo("Gestão de estúdio musical");
        subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblEmail = new JLabel("E-mail");
        lblEmail.setFont(Tema.FONTE_SUBTITULO);
        lblEmail.setForeground(Tema.TEXTO_SECUNDARIO);
        lblEmail.setAlignmentX(Component.CENTER_ALIGNMENT);

        campoEmail.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
        campoEmail.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblSenha = new JLabel("Senha");
        lblSenha.setFont(Tema.FONTE_SUBTITULO);
        lblSenha.setForeground(Tema.TEXTO_SECUNDARIO);
        lblSenha.setAlignmentX(Component.CENTER_ALIGNMENT);

        campoSenha.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
        campoSenha.setAlignmentX(Component.CENTER_ALIGNMENT);

        botaoEntrar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        botaoEntrar.setAlignmentX(Component.CENTER_ALIGNMENT);

        botaoCadastrar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
        botaoCadastrar.setAlignmentX(Component.CENTER_ALIGNMENT);

        painel.add(titulo);
        painel.add(Box.createVerticalStrut(4));
        painel.add(subtitulo);
        painel.add(Box.createVerticalStrut(32));
        painel.add(lblEmail);
        painel.add(Box.createVerticalStrut(6));
        painel.add(campoEmail);
        painel.add(Box.createVerticalStrut(16));
        painel.add(lblSenha);
        painel.add(Box.createVerticalStrut(6));
        painel.add(campoSenha);
        painel.add(Box.createVerticalStrut(24));
        painel.add(botaoEntrar);
        painel.add(Box.createVerticalStrut(10));
        painel.add(botaoCadastrar);

        frame.add(painel, BorderLayout.CENTER);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
