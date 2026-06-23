/*  Rafael Magno Campos da Silva - 202435012
    Gabriel Haddad Cyrino Gadioli - 202435026
    Thomas Adam Chapman - 202476030 */

package br.ufjf.dcc.soundcheck.view.TelasFuncionario;

import br.ufjf.dcc.soundcheck.controller.ClienteController;
import br.ufjf.dcc.soundcheck.model.*;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class TelaCadastroCliente {
    private JFrame frame;
    private JPanel painelPrincipal;
    private JPanel painelCadastrar;
    private JPanel painelEsq;
    private JPanel painelDir;
    private JPanel painelList;
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
    private JButton botaoRemover;
    private JButton botaoNovo;
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
    private JList<Cliente> listClientes;
    private ArrayList<Cliente> clientes;

    public TelaCadastroCliente() {
        frame            = new JFrame("Cadastro de Clientes");
        painelPrincipal   = new JPanel();
        painelCadastrar   = new JPanel();
        painelEsq         = new JPanel();
        painelDir         = new JPanel();
        painelList        = new JPanel();
        painelBotoes      = new JPanel();
        campoNome         = new JTextField(36);
        campoCPF          = new JTextField(36);
        campoTelefone     = new JTextField(36);
        campoEmail        = new JTextField(36);
        campoSenha        = new JTextField(36);
        campoBanda        = new JTextField(36);
        campoEstilo       = new JTextField(36);
        campoRua          = new JTextField(36);
        campoBairro       = new JTextField(36);
        campoCEP          = new JTextField(36);
        campoCidade       = new JTextField(36);
        campoEstado       = new JTextField(36);
        campoNumero       = new JTextField(36);
        campoComplemento  = new JTextField(36);
        botaoCadastrar    = new JButton("Salvar Cliente");
        botaoSair         = new JButton("Sair");
        botaoRemover      = new JButton("Remover Cliente");
        botaoNovo         = new JButton("Novo Cliente");
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
        listClientes      = new JList<>();
        try { clientes = Persistencia.carregarClientes(); }
        catch (IOException ex) { clientes = new ArrayList<>(); ex.printStackTrace(); }
        listClientes.setListData(clientes.toArray(new Cliente[0]));
    }

    public void abrirTelaCadastroCliente() {
        frame.setSize(850, 750);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        painelEsq.setLayout(new GridLayout(0, 1, 0, 15));
        painelEsq.setBorder(BorderFactory.createEmptyBorder(15, 0, 23, 0));
        painelDir.setLayout(new GridLayout(0, 1, 0, 15));
        painelDir.setBorder(BorderFactory.createEmptyBorder(15, 0, 23, 0));
        painelBotoes.setLayout(new GridLayout(1, 3, 10, 0));

        listClientes.addListSelectionListener(e -> {
            if (listClientes.getSelectedValue() != null) {
                Cliente c = listClientes.getSelectedValue();
                campoNome.setEditable(false); campoCPF.setEditable(false);
                campoNome.setText(c.getNome()); campoCPF.setText(c.getCpf());
                campoTelefone.setText(c.getTelefone()); campoEmail.setText(c.getEmail());
                campoSenha.setText(c.getSenha()); campoBanda.setText(c.getBanda());
                campoEstilo.setText(c.getEstiloMusical());
                campoRua.setText(c.getEndereco().getRua()); campoBairro.setText(c.getEndereco().getBairro());
                campoCEP.setText(c.getEndereco().getCep()); campoCidade.setText(c.getEndereco().getCidade());
                campoEstado.setText(c.getEndereco().getEstado()); campoNumero.setText(c.getEndereco().getNumero());
                campoComplemento.setText(c.getEndereco().getComplemento());
            }
        });

        botaoNovo.addActionListener(e -> {
            campoNome.setEditable(true); campoCPF.setEditable(true);
            listClientes.clearSelection();
            campoNome.setText(null); campoCPF.setText(null); campoTelefone.setText(null);
            campoEmail.setText(null); campoSenha.setText(null); campoBanda.setText(null);
            campoEstilo.setText(null); campoRua.setText(null); campoBairro.setText(null);
            campoCEP.setText(null); campoCidade.setText(null); campoEstado.setText(null);
            campoNumero.setText(null); campoComplemento.setText(null);
        });

        botaoCadastrar.addActionListener(e -> {
            if (!campoNome.getText().isEmpty() && !campoCPF.getText().isEmpty()
                    && !campoTelefone.getText().isEmpty() && !campoEmail.getText().isEmpty()
                    && !campoSenha.getText().isEmpty() && !campoBanda.getText().isEmpty()
                    && !campoEstilo.getText().isEmpty() && !campoRua.getText().isEmpty()
                    && !campoBairro.getText().isEmpty() && !campoCEP.getText().isEmpty()
                    && !campoCidade.getText().isEmpty() && !campoEstado.getText().isEmpty()
                    && !campoNumero.getText().isEmpty()) {
                if (listClientes.getSelectedValue() == null)
                    ClienteController.cadastrarCliente(campoNome.getText(), campoCPF.getText(),
                        campoTelefone.getText(), campoEmail.getText(), campoSenha.getText(),
                        campoRua.getText(), campoBairro.getText(), campoCEP.getText(),
                        campoCidade.getText(), campoEstado.getText(), campoNumero.getText(),
                        campoComplemento.getText(), campoBanda.getText(), campoEstilo.getText());
                else
                    ClienteController.alterarDadosCliente(listClientes.getSelectedValue(),
                        campoNome.getText(), campoCPF.getText(), campoTelefone.getText(),
                        campoEmail.getText(), campoSenha.getText(), campoRua.getText(),
                        campoBairro.getText(), campoCEP.getText(), campoCidade.getText(),
                        campoEstado.getText(), campoNumero.getText(), campoComplemento.getText(),
                        campoBanda.getText(), campoEstilo.getText());
            } else {
                JOptionPane.showMessageDialog(frame, "Preencha todos os campos obrigatórios!", "Erro!", JOptionPane.ERROR_MESSAGE);
            }
            try { clientes = Persistencia.carregarClientes(); } catch (IOException ex) { ex.printStackTrace(); }
            listClientes.setListData(clientes.toArray(new Cliente[0]));
        });

        botaoRemover.addActionListener(e -> {
            if (listClientes.getSelectedValue() != null) {
                ClienteController.deletarCliente(listClientes.getSelectedValue());
                try { clientes = Persistencia.carregarClientes(); } catch (IOException ex) { ex.printStackTrace(); }
                listClientes.setListData(clientes.toArray(new Cliente[0]));
                listClientes.clearSelection();
                campoNome.setEditable(true); campoCPF.setEditable(true);
                campoNome.setText(null); campoCPF.setText(null); campoTelefone.setText(null);
                campoEmail.setText(null); campoSenha.setText(null); campoBanda.setText(null);
                campoEstilo.setText(null); campoRua.setText(null); campoBairro.setText(null);
                campoCEP.setText(null); campoCidade.setText(null); campoEstado.setText(null);
                campoNumero.setText(null); campoComplemento.setText(null);
            } else {
                JOptionPane.showMessageDialog(frame, "Nenhum cliente selecionado!", "Erro!", JOptionPane.ERROR_MESSAGE);
            }
        });

        botaoSair.addActionListener(e -> frame.dispose());

        painelList.setLayout(new BorderLayout(10, 10));
        painelList.setBorder(BorderFactory.createTitledBorder("Clientes Cadastrados"));
        painelList.add(new JScrollPane(listClientes));

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
        painelBotoes.add(botaoRemover);
        painelBotoes.add(botaoCadastrar);

        painelCadastrar.setLayout(new BorderLayout(20, 10));
        painelCadastrar.add(botaoNovo, BorderLayout.NORTH);
        painelCadastrar.add(painelEsq, BorderLayout.WEST);
        painelCadastrar.add(painelDir, BorderLayout.CENTER);
        painelCadastrar.add(painelBotoes, BorderLayout.SOUTH);

        painelPrincipal.setLayout(new BorderLayout(10, 10));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(15, 23, 15, 23));
        painelPrincipal.add(painelCadastrar, BorderLayout.EAST);
        painelPrincipal.add(painelList, BorderLayout.CENTER);

        frame.add(painelPrincipal);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
