/*  Rafael Magno Campos da Silva - 202435012
    Gabriel Haddad Cyrino Gadioli - 202435026
    Thomas Adam Chapman - 202476030 */

package br.ufjf.dcc.soundcheck.view.TelasCliente;

import br.ufjf.dcc.soundcheck.controller.ClienteController;
import javax.swing.*;
import java.awt.*;

public class TelaAutoRegistro {
    private JFrame frame;
    private JPanel painelPrincipal;
    private JPanel painelEsq;
    private JPanel painelDir;
    private JPanel painelBotoes;
    private JTextField campoNome;
    private JTextField campoCPF;
    private JTextField campoTelefone;
    private JTextField campoEmail;
    private JTextField campoSenha;
    private JTextField campoBanda;
    private JTextField campoEstilo;
    private JTextField campoRua;
    private JTextField campoBairro;
    private JTextField campoCEP;
    private JTextField campoCidade;
    private JTextField campoEstado;
    private JTextField campoNumero;
    private JTextField campoComplemento;
    private JButton botaoCadastrar;
    private JButton botaoSair;
    private JLabel labelNome;
    private JLabel labelCPF;
    private JLabel labelTelefone;
    private JLabel labelEmail;
    private JLabel labelSenha;
    private JLabel labelBanda;
    private JLabel labelEstilo;
    private JLabel labelRua;
    private JLabel labelBairro;
    private JLabel labelCEP;
    private JLabel labelCidade;
    private JLabel labelEstado;
    private JLabel labelNumero;
    private JLabel labelComplemento;

    public TelaAutoRegistro() {
        frame            = new JFrame("SoundCheck - Cadastro");
        painelPrincipal   = new JPanel();
        painelEsq         = new JPanel();
        painelDir         = new JPanel();
        painelBotoes      = new JPanel();
        campoNome         = new JTextField(23);
        campoCPF          = new JTextField(23);
        campoTelefone     = new JTextField(23);
        campoEmail        = new JTextField(23);
        campoSenha        = new JTextField(23);
        campoBanda        = new JTextField(23);
        campoEstilo       = new JTextField(23);
        campoRua          = new JTextField(23);
        campoBairro       = new JTextField(23);
        campoCEP          = new JTextField(23);
        campoCidade       = new JTextField(23);
        campoEstado       = new JTextField(23);
        campoNumero       = new JTextField(23);
        campoComplemento  = new JTextField(23);
        botaoCadastrar    = new JButton("Cadastrar");
        botaoSair         = new JButton("Voltar");
        labelNome         = new JLabel("Nome:");
        labelCPF          = new JLabel("CPF:");
        labelTelefone     = new JLabel("Telefone:");
        labelEmail        = new JLabel("E-mail:");
        labelSenha        = new JLabel("Senha:");
        labelBanda        = new JLabel("Banda:");
        labelEstilo       = new JLabel("Estilo Musical:");
        labelRua          = new JLabel("Rua:");
        labelBairro       = new JLabel("Bairro:");
        labelCEP          = new JLabel("CEP:");
        labelCidade       = new JLabel("Cidade:");
        labelEstado       = new JLabel("Estado:");
        labelNumero       = new JLabel("Número:");
        labelComplemento  = new JLabel("Complemento:");
    }

    public void abrirTelaAutoRegistro() {
        frame.setSize(470, 750);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        botaoCadastrar.addActionListener(e -> {
            if (!campoNome.getText().isEmpty() && !campoCPF.getText().isEmpty()
                    && !campoTelefone.getText().isEmpty() && !campoEmail.getText().isEmpty()
                    && !campoSenha.getText().isEmpty() && !campoBanda.getText().isEmpty()
                    && !campoEstilo.getText().isEmpty() && !campoRua.getText().isEmpty()
                    && !campoBairro.getText().isEmpty() && !campoCEP.getText().isEmpty()
                    && !campoCidade.getText().isEmpty() && !campoEstado.getText().isEmpty()
                    && !campoNumero.getText().isEmpty()) {
                ClienteController.cadastrarCliente(
                    campoNome.getText(), campoCPF.getText(), campoTelefone.getText(),
                    campoEmail.getText(), campoSenha.getText(),
                    campoRua.getText(), campoBairro.getText(), campoCEP.getText(),
                    campoCidade.getText(), campoEstado.getText(), campoNumero.getText(),
                    campoComplemento.getText(), campoBanda.getText(), campoEstilo.getText());
                frame.dispose();
            } else {
                JOptionPane.showMessageDialog(frame, "Preencha todos os campos obrigatórios!", "Erro!", JOptionPane.ERROR_MESSAGE);
            }
        });

        botaoSair.addActionListener(e -> frame.dispose());

        painelEsq.setLayout(new GridLayout(0, 1, 0, 15));
        painelEsq.setBorder(BorderFactory.createEmptyBorder(15, 0, 23, 0));
        painelEsq.add(labelNome); painelEsq.add(labelCPF); painelEsq.add(labelTelefone);
        painelEsq.add(labelEmail); painelEsq.add(labelSenha); painelEsq.add(labelBanda);
        painelEsq.add(labelEstilo); painelEsq.add(labelRua); painelEsq.add(labelBairro);
        painelEsq.add(labelCEP); painelEsq.add(labelCidade); painelEsq.add(labelEstado);
        painelEsq.add(labelNumero); painelEsq.add(labelComplemento);

        painelDir.setLayout(new GridLayout(0, 1, 0, 15));
        painelDir.setBorder(BorderFactory.createEmptyBorder(15, 0, 23, 0));
        painelDir.add(campoNome); painelDir.add(campoCPF); painelDir.add(campoTelefone);
        painelDir.add(campoEmail); painelDir.add(campoSenha); painelDir.add(campoBanda);
        painelDir.add(campoEstilo); painelDir.add(campoRua); painelDir.add(campoBairro);
        painelDir.add(campoCEP); painelDir.add(campoCidade); painelDir.add(campoEstado);
        painelDir.add(campoNumero); painelDir.add(campoComplemento);

        painelBotoes.setLayout(new GridLayout(1, 2, 10, 0));
        painelBotoes.add(botaoSair);
        painelBotoes.add(botaoCadastrar);

        painelPrincipal.setLayout(new BorderLayout(10, 10));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(15, 23, 15, 23));
        painelPrincipal.add(painelEsq, BorderLayout.WEST);
        painelPrincipal.add(painelDir, BorderLayout.EAST);
        painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);

        frame.add(painelPrincipal);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}