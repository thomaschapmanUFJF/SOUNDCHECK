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
    private final JPanel painelPrincipal;
    private final JPanel painelEsq;
    private final JPanel painelDir;
    private final JPanel painelBotoes;
    private final JTextField campoEmail;
    private final JTextField campoSenha;
    private final JLabel labelEmail;
    private final JLabel labelSenha;
    private final JButton botaoEntrar;

    public TelaLogin() {
        frame          = new JFrame("SoundCheck - Login");
        painelPrincipal = new JPanel();
        painelEsq       = new JPanel();
        painelDir       = new JPanel();
        painelBotoes    = new JPanel();
        campoEmail      = new JTextField(26);
        campoSenha      = new JPasswordField(26);
        labelEmail      = new JLabel("E-mail:");
        labelSenha      = new JLabel("Senha:");
        botaoEntrar     = new JButton("Entrar");

        botaoEntrar.addActionListener(e -> {
            try {
                Usuario usuario = LoginController.autenticar(campoEmail.getText(), campoSenha.getText());
                abrirTelaPorPerfil(usuario);
                frame.dispose();
            } catch (LoginException ex) {
                JOptionPane.showMessageDialog(frame, "Email ou senha inválidos", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void abrirTelaPorPerfil(Usuario usuario) {
        switch (usuario.getTipo()) {
            case CLIENTE -> { new TelaMenuCliente().abrirTelaMenuCliente((Cliente) usuario); }
            case FUNCIONARIO -> { new TelaMenuFuncionario().abrirTelaMenuFuncionario((Funcionario) usuario); }
            case GERENTE -> { new TelaMenuGerente().abrirTelaMenuGerente((Gerente) usuario); }
        }
    }

    public void abrirLogin() {
        frame.setSize(400, 200);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        painelPrincipal.setLayout(new BorderLayout(0, 5));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        painelEsq.setLayout(new GridLayout(0, 1, 0, 20));
        painelEsq.setBorder(BorderFactory.createEmptyBorder(15, 0, 21, 0));
        painelDir.setLayout(new GridLayout(0, 1, 0, 20));
        painelDir.setBorder(BorderFactory.createEmptyBorder(15, 0, 21, 0));
        painelBotoes.setLayout(new GridLayout(1, 1, 10, 0));
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 100));

        painelEsq.add(labelEmail);
        painelEsq.add(labelSenha);
        painelDir.add(campoEmail);
        painelDir.add(campoSenha);
        painelBotoes.add(botaoEntrar);

        painelPrincipal.add(painelEsq, BorderLayout.WEST);
        painelPrincipal.add(painelDir, BorderLayout.EAST);
        painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);

        frame.add(painelPrincipal);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
