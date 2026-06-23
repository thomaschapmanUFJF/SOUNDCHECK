/*  Rafael Magno Campos da Silva - 202435012
    Gabriel Haddad Cyrino Gadioli - 202435026
    Thomas Adam Chapman - 202476030 */

package br.ufjf.dcc.soundcheck.view.TelasGerente;

import br.ufjf.dcc.soundcheck.controller.FuncionarioController;
import br.ufjf.dcc.soundcheck.model.*;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class TelaCadastroFuncionario {
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
    private JTextField campoMatricula;
    private JTextField campoArea;
    private JButton botaoCadastrar;
    private JButton botaoSair;
    private JButton botaoRemover;
    private JButton botaoNovo;
    private JLabel labelNome;
    private JLabel labelCPF;
    private JLabel labelTelefone;
    private JLabel labelEmail;
    private JLabel labelSenha;
    private JLabel labelMatricula;
    private JLabel labelArea;
    private JList<Funcionario> listFuncionarios;
    private ArrayList<Funcionario> funcionarios;

    public TelaCadastroFuncionario() {
        frame            = new JFrame("Cadastro de Funcionários");
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
        campoMatricula    = new JTextField(36);
        campoArea         = new JTextField(36);
        botaoCadastrar    = new JButton("Salvar Funcionário");
        botaoSair         = new JButton("Sair");
        botaoRemover      = new JButton("Remover Funcionário");
        botaoNovo         = new JButton("Novo Funcionário");
        labelNome         = new JLabel("Nome:");
        labelCPF          = new JLabel("CPF:");
        labelTelefone     = new JLabel("Telefone:");
        labelEmail        = new JLabel("E-mail:");
        labelSenha        = new JLabel("Senha:");
        labelMatricula    = new JLabel("Matrícula:");
        labelArea         = new JLabel("Área:");
        listFuncionarios  = new JList<>();
        try { funcionarios = Persistencia.carregarFuncionarios(); }
        catch (IOException ex) { funcionarios = new ArrayList<>(); ex.printStackTrace(); }
        listFuncionarios.setListData(funcionarios.toArray(new Funcionario[0]));
    }

    public void abrirTelaCadastroFuncionario() {
        frame.setSize(850, 550);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        painelEsq.setLayout(new GridLayout(0, 1, 0, 15));
        painelEsq.setBorder(BorderFactory.createEmptyBorder(15, 0, 23, 0));
        painelDir.setLayout(new GridLayout(0, 1, 0, 15));
        painelDir.setBorder(BorderFactory.createEmptyBorder(15, 0, 23, 0));
        painelBotoes.setLayout(new GridLayout(1, 3, 10, 0));

        listFuncionarios.addListSelectionListener(e -> {
            if (listFuncionarios.getSelectedValue() != null) {
                Funcionario f = listFuncionarios.getSelectedValue();
                campoNome.setEditable(false); campoCPF.setEditable(false);
                campoNome.setText(f.getNome()); campoCPF.setText(f.getCpf());
                campoTelefone.setText(f.getTelefone()); campoEmail.setText(f.getEmail());
                campoSenha.setText(f.getSenha()); campoMatricula.setText(f.getMatricula());
                campoArea.setText(f.getArea());
            }
        });

        botaoNovo.addActionListener(e -> {
            campoNome.setEditable(true); campoCPF.setEditable(true);
            listFuncionarios.clearSelection();
            campoNome.setText(null); campoCPF.setText(null); campoTelefone.setText(null);
            campoEmail.setText(null); campoSenha.setText(null);
            campoMatricula.setText(null); campoArea.setText(null);
        });

        botaoCadastrar.addActionListener(e -> {
            if (!campoNome.getText().isEmpty() && !campoCPF.getText().isEmpty()
                    && !campoTelefone.getText().isEmpty() && !campoEmail.getText().isEmpty()
                    && !campoSenha.getText().isEmpty() && !campoMatricula.getText().isEmpty()
                    && !campoArea.getText().isEmpty()) {
                if (listFuncionarios.getSelectedValue() == null)
                    FuncionarioController.cadastrarFuncionario(campoNome.getText(), campoCPF.getText(),
                        campoTelefone.getText(), campoEmail.getText(), campoSenha.getText(),
                        campoMatricula.getText(), campoArea.getText());
                else
                    FuncionarioController.alterarDadosFuncionario(listFuncionarios.getSelectedValue(),
                        campoNome.getText(), campoCPF.getText(), campoTelefone.getText(),
                        campoEmail.getText(), campoSenha.getText(),
                        campoMatricula.getText(), campoArea.getText());
            } else {
                JOptionPane.showMessageDialog(frame, "Preencha todos os campos!", "Erro!", JOptionPane.ERROR_MESSAGE);
            }
            try { funcionarios = Persistencia.carregarFuncionarios(); } catch (IOException ex) { ex.printStackTrace(); }
            listFuncionarios.setListData(funcionarios.toArray(new Funcionario[0]));
        });

        botaoRemover.addActionListener(e -> {
            if (listFuncionarios.getSelectedValue() != null) {
                FuncionarioController.deletarFuncionario(listFuncionarios.getSelectedValue());
                try { funcionarios = Persistencia.carregarFuncionarios(); } catch (IOException ex) { ex.printStackTrace(); }
                listFuncionarios.setListData(funcionarios.toArray(new Funcionario[0]));
                listFuncionarios.clearSelection();
                campoNome.setEditable(true); campoCPF.setEditable(true);
                campoNome.setText(null); campoCPF.setText(null); campoTelefone.setText(null);
                campoEmail.setText(null); campoSenha.setText(null);
                campoMatricula.setText(null); campoArea.setText(null);
            } else {
                JOptionPane.showMessageDialog(frame, "Nenhum funcionário selecionado!", "Erro!", JOptionPane.ERROR_MESSAGE);
            }
        });

        botaoSair.addActionListener(e -> frame.dispose());

        painelList.setLayout(new BorderLayout(10, 10));
        painelList.setBorder(BorderFactory.createTitledBorder("Funcionários Cadastrados"));
        painelList.add(new JScrollPane(listFuncionarios));

        painelEsq.add(labelNome); painelEsq.add(labelCPF); painelEsq.add(labelTelefone);
        painelEsq.add(labelEmail); painelEsq.add(labelSenha);
        painelEsq.add(labelMatricula); painelEsq.add(labelArea);

        painelDir.add(campoNome); painelDir.add(campoCPF); painelDir.add(campoTelefone);
        painelDir.add(campoEmail); painelDir.add(campoSenha);
        painelDir.add(campoMatricula); painelDir.add(campoArea);

        painelBotoes.add(botaoSair); painelBotoes.add(botaoRemover); painelBotoes.add(botaoCadastrar);

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
