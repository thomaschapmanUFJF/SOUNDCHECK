/*  Rafael Magno Campos da Silva - 202435012
    Gabriel Haddad Cyrino Gadioli - 202435026
    Thomas Adam Chapman - 202476030 */

package br.ufjf.dcc.soundcheck.view.TelasFuncionario;

import br.ufjf.dcc.soundcheck.controller.SalaController;
import br.ufjf.dcc.soundcheck.model.*;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class TelaRegistrarSalas {
    private JFrame frame;
    private JPanel painelPrincipal;
    private JPanel painelCadastrar;
    private JPanel painelEsq;
    private JPanel painelDir;
    private JPanel painelList;
    private JPanel painelBotoes;
    private JTextField campoNumero;
    private JTextField campoCapacidade;
    private JTextField campoValor;
    private JCheckBox checkDisponivel;
    private JButton botaoCadastrar;
    private JButton botaoSair;
    private JButton botaoRemover;
    private JButton botaoNovo;
    private JLabel labelNumero;
    private JLabel labelCapacidade;
    private JLabel labelValor;
    private JLabel labelDisponivel;
    private JList<Sala> listSalas;
    private ArrayList<Sala> salas;

    public TelaRegistrarSalas() {
        frame            = new JFrame("Registrar Salas");
        painelPrincipal   = new JPanel();
        painelCadastrar   = new JPanel();
        painelEsq         = new JPanel();
        painelDir         = new JPanel();
        painelList        = new JPanel();
        painelBotoes      = new JPanel();
        campoNumero       = new JTextField(36);
        campoCapacidade   = new JTextField(36);
        campoValor        = new JTextField(36);
        checkDisponivel   = new JCheckBox();
        checkDisponivel.setSelected(true);
        botaoCadastrar    = new JButton("Salvar Sala");
        botaoSair         = new JButton("Sair");
        botaoRemover      = new JButton("Remover Sala");
        botaoNovo         = new JButton("Nova Sala");
        labelNumero       = new JLabel("Número:");
        labelCapacidade   = new JLabel("Capacidade:");
        labelValor        = new JLabel("Valor (R$/h):");
        labelDisponivel   = new JLabel("Disponível:");
        listSalas         = new JList<>();
        try { salas = Persistencia.carregarSalas(); }
        catch (IOException ex) { salas = new ArrayList<>(); ex.printStackTrace(); }
        listSalas.setListData(salas.toArray(new Sala[0]));
    }

    public void abrirTelaRegistrarSalas() {
        frame.setSize(800, 400);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        painelEsq.setLayout(new GridLayout(0, 1, 0, 15));
        painelEsq.setBorder(BorderFactory.createEmptyBorder(15, 0, 23, 0));
        painelDir.setLayout(new GridLayout(0, 1, 0, 15));
        painelDir.setBorder(BorderFactory.createEmptyBorder(15, 0, 23, 0));
        painelBotoes.setLayout(new GridLayout(1, 3, 10, 0));

        listSalas.addListSelectionListener(e -> {
            if (listSalas.getSelectedValue() != null) {
                Sala s = listSalas.getSelectedValue();
                campoNumero.setEditable(false);
                campoNumero.setText(s.getNumero());
                campoCapacidade.setText(String.valueOf(s.getCapacidade()));
                campoValor.setText(String.valueOf(s.getValor()));
                checkDisponivel.setSelected(s.isDisponivel());
            }
        });

        botaoNovo.addActionListener(e -> {
            campoNumero.setEditable(true); listSalas.clearSelection();
            campoNumero.setText(null); campoCapacidade.setText(null);
            campoValor.setText(null); checkDisponivel.setSelected(true);
        });

        botaoCadastrar.addActionListener(e -> {
            if (!campoNumero.getText().isEmpty() && !campoCapacidade.getText().isEmpty() && !campoValor.getText().isEmpty()) {
                try {
                    int cap = Integer.parseInt(campoCapacidade.getText());
                    float val = Float.parseFloat(campoValor.getText());
                    if (listSalas.getSelectedValue() == null)
                        SalaController.cadastrarSala(campoNumero.getText(), cap, val);
                    else
                        SalaController.alterarSala(listSalas.getSelectedValue(),
                            campoNumero.getText(), cap, val, checkDisponivel.isSelected());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Capacidade e valor devem ser números!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Preencha todos os campos!", "Erro!", JOptionPane.ERROR_MESSAGE);
            }
            try { salas = Persistencia.carregarSalas(); } catch (IOException ex) { ex.printStackTrace(); }
            listSalas.setListData(salas.toArray(new Sala[0]));
        });

        botaoRemover.addActionListener(e -> {
            if (listSalas.getSelectedValue() != null) {
                SalaController.deletarSala(listSalas.getSelectedValue());
                try { salas = Persistencia.carregarSalas(); } catch (IOException ex) { ex.printStackTrace(); }
                listSalas.setListData(salas.toArray(new Sala[0]));
                listSalas.clearSelection();
                campoNumero.setEditable(true);
                campoNumero.setText(null); campoCapacidade.setText(null); campoValor.setText(null);
                checkDisponivel.setSelected(true);
            } else {
                JOptionPane.showMessageDialog(frame, "Nenhuma sala selecionada!", "Erro!", JOptionPane.ERROR_MESSAGE);
            }
        });

        botaoSair.addActionListener(e -> frame.dispose());

        painelList.setLayout(new BorderLayout(10, 10));
        painelList.setBorder(BorderFactory.createTitledBorder("Salas Cadastradas"));
        painelList.add(new JScrollPane(listSalas));

        painelEsq.add(labelNumero); painelEsq.add(labelCapacidade);
        painelEsq.add(labelValor); painelEsq.add(labelDisponivel);

        painelDir.add(campoNumero); painelDir.add(campoCapacidade);
        painelDir.add(campoValor); painelDir.add(checkDisponivel);

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
