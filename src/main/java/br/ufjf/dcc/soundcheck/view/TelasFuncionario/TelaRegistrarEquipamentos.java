/*  Rafael Magno Campos da Silva - 202435012
    Gabriel Haddad Cyrino Gadioli - 202435026
    Thomas Adam Chapman - 202476030 */

package br.ufjf.dcc.soundcheck.view.TelasFuncionario;

import br.ufjf.dcc.soundcheck.controller.EquipamentoController;
import br.ufjf.dcc.soundcheck.model.*;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class TelaRegistrarEquipamentos {
    private JFrame frame;
    private JPanel painelPrincipal;
    private JPanel painelCadastrar;
    private JPanel painelEsq;
    private JPanel painelDir;
    private JPanel painelList;
    private JPanel painelBotoes;
    private JTextField campoNome;
    private JTextField campoPreco;
    private JTextField campoQuantidade;
    private JCheckBox checkDisponivel;
    private JButton botaoCadastrar;
    private JButton botaoSair;
    private JButton botaoRemover;
    private JButton botaoNovo;
    private JLabel labelNome;
    private JLabel labelPreco;
    private JLabel labelQuantidade;
    private JLabel labelDisponivel;
    private JList<EquipamentoExtra> listEquipamentos;
    private ArrayList<EquipamentoExtra> equipamentos;

    public TelaRegistrarEquipamentos() {
        frame            = new JFrame("Registrar Equipamentos");
        painelPrincipal   = new JPanel();
        painelCadastrar   = new JPanel();
        painelEsq         = new JPanel();
        painelDir         = new JPanel();
        painelList        = new JPanel();
        painelBotoes      = new JPanel();
        campoNome         = new JTextField(36);
        campoPreco        = new JTextField(36);
        campoQuantidade   = new JTextField(36);
        checkDisponivel   = new JCheckBox();
        checkDisponivel.setSelected(true);
        botaoCadastrar    = new JButton("Salvar Equipamento");
        botaoSair         = new JButton("Sair");
        botaoRemover      = new JButton("Remover Equipamento");
        botaoNovo         = new JButton("Novo Equipamento");
        labelNome         = new JLabel("Nome:");
        labelPreco        = new JLabel("Preço (R$/un):");
        labelQuantidade   = new JLabel("Quantidade:");
        labelDisponivel   = new JLabel("Disponível:");
        listEquipamentos  = new JList<>();
        try { equipamentos = Persistencia.carregarEquipamentos(); }
        catch (IOException ex) { equipamentos = new ArrayList<>(); ex.printStackTrace(); }
        listEquipamentos.setListData(equipamentos.toArray(new EquipamentoExtra[0]));
    }

    public void abrirTelaRegistrarEquipamentos() {
        frame.setSize(800, 400);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        painelEsq.setLayout(new GridLayout(0, 1, 0, 15));
        painelEsq.setBorder(BorderFactory.createEmptyBorder(15, 0, 23, 0));
        painelDir.setLayout(new GridLayout(0, 1, 0, 15));
        painelDir.setBorder(BorderFactory.createEmptyBorder(15, 0, 23, 0));
        painelBotoes.setLayout(new GridLayout(1, 3, 10, 0));

        listEquipamentos.addListSelectionListener(e -> {
            if (listEquipamentos.getSelectedValue() != null) {
                EquipamentoExtra eq = listEquipamentos.getSelectedValue();
                campoNome.setEditable(false);
                campoNome.setText(eq.getNome());
                campoPreco.setText(String.valueOf(eq.getPrecoUnidade()));
                campoQuantidade.setText(String.valueOf(eq.getQuantidade()));
                checkDisponivel.setSelected(eq.isDisponivel());
            }
        });

        botaoNovo.addActionListener(e -> {
            campoNome.setEditable(true); listEquipamentos.clearSelection();
            campoNome.setText(null); campoPreco.setText(null);
            campoQuantidade.setText(null); checkDisponivel.setSelected(true);
        });

        botaoCadastrar.addActionListener(e -> {
            if (!campoNome.getText().isEmpty() && !campoPreco.getText().isEmpty() && !campoQuantidade.getText().isEmpty()) {
                try {
                    float preco = Float.parseFloat(campoPreco.getText());
                    int qtd = Integer.parseInt(campoQuantidade.getText());
                    if (listEquipamentos.getSelectedValue() == null)
                        EquipamentoController.cadastrarEquipamento(campoNome.getText(), preco, qtd);
                    else
                        EquipamentoController.alterarEquipamento(listEquipamentos.getSelectedValue(),
                            campoNome.getText(), preco, qtd, checkDisponivel.isSelected());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Preço e quantidade devem ser números!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Preencha todos os campos!", "Erro!", JOptionPane.ERROR_MESSAGE);
            }
            try { equipamentos = Persistencia.carregarEquipamentos(); } catch (IOException ex) { ex.printStackTrace(); }
            listEquipamentos.setListData(equipamentos.toArray(new EquipamentoExtra[0]));
        });

        botaoRemover.addActionListener(e -> {
            if (listEquipamentos.getSelectedValue() != null) {
                EquipamentoController.deletarEquipamento(listEquipamentos.getSelectedValue());
                try { equipamentos = Persistencia.carregarEquipamentos(); } catch (IOException ex) { ex.printStackTrace(); }
                listEquipamentos.setListData(equipamentos.toArray(new EquipamentoExtra[0]));
                listEquipamentos.clearSelection();
                campoNome.setEditable(true);
                campoNome.setText(null); campoPreco.setText(null); campoQuantidade.setText(null);
                checkDisponivel.setSelected(true);
            } else {
                JOptionPane.showMessageDialog(frame, "Nenhum equipamento selecionado!", "Erro!", JOptionPane.ERROR_MESSAGE);
            }
        });

        botaoSair.addActionListener(e -> frame.dispose());

        painelList.setLayout(new BorderLayout(10, 10));
        painelList.setBorder(BorderFactory.createTitledBorder("Equipamentos Cadastrados"));
        painelList.add(new JScrollPane(listEquipamentos));

        painelEsq.add(labelNome); painelEsq.add(labelPreco);
        painelEsq.add(labelQuantidade); painelEsq.add(labelDisponivel);

        painelDir.add(campoNome); painelDir.add(campoPreco);
        painelDir.add(campoQuantidade); painelDir.add(checkDisponivel);

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
