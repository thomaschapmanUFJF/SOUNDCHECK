/*  Rafael Magno Campos da Silva - 202435012
    Gabriel Haddad Cyrino Gadioli - 202435026
    Thomas Adam Chapman - 202476030 */

package br.ufjf.dcc.soundcheck.view.TelasCliente;

import br.ufjf.dcc.soundcheck.controller.ClienteController;
import br.ufjf.dcc.soundcheck.model.Cliente;
import javax.swing.*;
import java.awt.*;

public class TelaDadosPessoaisCliente {
    private JFrame frame;
    private JPanel painelCadastrar;
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
    private JButton botaoConfirmar;
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

    public TelaDadosPessoaisCliente() {
        frame            = new JFrame("Meus Dados");
        painelCadastrar   = new JPanel();
        painelEsq         = new JPanel();
        painelDir         = new JPanel();
        painelBotoes      = new JPanel();
        campoNome         = new JTextField(23); campoNome.setEditable(false);
        campoCPF          = new JTextField(23); campoCPF.setEditable(false);
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
        botaoConfirmar    = new JButton("Confirmar Mudanças");
        botaoSair         = new JButton("Sair");
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

    public void abrirTelaDadosPessoaisCliente(Cliente cliente) {
        campoNome.setText(cliente.getNome());
        campoCPF.setText(cliente.getCpf());
        campoTelefone.setText(cliente.getTelefone());
        campoEmail.setText(cliente.getEmail());
        campoSenha.setText(cliente.getSenha());
        campoBanda.setText(cliente.getBanda());
        campoEstilo.setText(cliente.getEstiloMusical());
        campoRua.setText(cliente.getEndereco().getRua());
        campoBairro.setText(cliente.getEndereco().getBairro());
        campoCEP.setText(cliente.getEndereco().getCep());
        campoCidade.setText(cliente.getEndereco().getCidade());
        campoEstado.setText(cliente.getEndereco().getEstado());
        campoNumero.setText(cliente.getEndereco().getNumero());
        campoComplemento.setText(cliente.getEndereco().getComplemento());

        frame.setSize(470, 750);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        painelEsq.setLayout(new GridLayout(0, 1, 0, 15));
        painelEsq.setBorder(BorderFactory.createEmptyBorder(15, 0, 23, 0));
        painelDir.setLayout(new GridLayout(0, 1, 0, 15));
        painelDir.setBorder(BorderFactory.createEmptyBorder(15, 0, 23, 0));
        painelBotoes.setLayout(new GridLayout(1, 2, 10, 0));

        botaoConfirmar.addActionListener(e -> {
            if (!campoTelefone.getText().isEmpty() && !campoEmail.getText().isEmpty()
                    && !campoSenha.getText().isEmpty() && !campoBanda.getText().isEmpty()
                    && !campoEstilo.getText().isEmpty() && !campoRua.getText().isEmpty()
                    && !campoBairro.getText().isEmpty() && !campoCEP.getText().isEmpty()
                    && !campoCidade.getText().isEmpty() && !campoEstado.getText().isEmpty()
                    && !campoNumero.getText().isEmpty()) {
                ClienteController.alterarDadosCliente(cliente,
                    campoNome.getText(), campoCPF.getText(), campoTelefone.getText(),
                    campoEmail.getText(), campoSenha.getText(),
                    campoRua.getText(), campoBairro.getText(), campoCEP.getText(),
                    campoCidade.getText(), campoEstado.getText(), campoNumero.getText(),
                    campoComplemento.getText(), campoBanda.getText(), campoEstilo.getText());
            } else {
                JOptionPane.showMessageDialog(frame, "Preencha todos os campos obrigatórios!", "Erro!", JOptionPane.ERROR_MESSAGE);
            }
        });

        botaoSair.addActionListener(e -> frame.dispose());

        painelEsq.add(labelNome); painelEsq.add(labelCPF); painelEsq.add(labelTelefone);
        painelEsq.add(labelEmail); painelEsq.add(labelSenha); painelEsq.add(labelBanda);
        painelEsq.add(labelEstilo); painelEsq.add(labelRua); painelEsq.add(labelBairro);
        painelEsq.add(labelCEP); painelEsq.add(labelCidade); painelEsq.add(labelEstado);
        painelEsq.add(labelNumero); painelEsq.add(labelComplemento);

        painelDir.add(campoNome); painelDir.add(campoCPF); painelDir.add(campoTelefone);
        painelDir.add(campoEmail); painelDir.add(campoSenha); painelDir.add(campoBanda);
        painelDir.add(campoEstilo); painelDir.add(campoRua); painelDir.add(campoBairro);
        painelDir.add(campoCEP); painelDir.add(campoCidade); painelDir.add(campoEstado);
        painelDir.add(campoNumero); painelDir.add(campoComplemento);

        painelBotoes.add(botaoSair);
        painelBotoes.add(botaoConfirmar);

        painelCadastrar.setLayout(new BorderLayout(10, 10));
        painelCadastrar.setBorder(BorderFactory.createEmptyBorder(15, 23, 15, 23));
        painelCadastrar.add(painelEsq, BorderLayout.WEST);
        painelCadastrar.add(painelDir, BorderLayout.EAST);
        painelCadastrar.add(painelBotoes, BorderLayout.SOUTH);

        frame.add(painelCadastrar);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
