/*  Rafael Magno Campos da Silva - 202435012
    Gabriel Haddad Cyrino Gadioli - 202435026
    Thomas Adam Chapman - 202476030 */

package br.ufjf.dcc.soundcheck.view.TelasFuncionario;

import br.ufjf.dcc.soundcheck.controller.ReservaController;
import br.ufjf.dcc.soundcheck.model.*;
import br.ufjf.dcc.soundcheck.model.enums.ReservaStatus;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class TelaGerenciarReservas {
    private JFrame frame;
    private JPanel painelPrincipal;
    private JPanel painelInfo;
    private JPanel painelList;
    private JPanel painelBotoes;
    private JPanel painelInfoEsq;
    private JPanel painelInfoDir;
    private JList<Reserva> listReservas;
    private JLabel labelCliente;
    private JLabel labelSala;
    private JLabel labelData;
    private JLabel labelDuracao;
    private JLabel labelStatus;
    private JLabel labelValor;
    private JLabel campoCliente;
    private JLabel campoSala;
    private JLabel campoData;
    private JLabel campoDuracao;
    private JLabel campoStatus;
    private JLabel campoValor;
    private JButton botaoConfirmarPagamento;
    private JButton botaoCancelarReserva;
    private JButton botaoSair;
    private ArrayList<Reserva> reservas;

    public TelaGerenciarReservas() {
        frame                  = new JFrame("Gerenciar Reservas");
        painelPrincipal         = new JPanel();
        painelInfo              = new JPanel();
        painelList              = new JPanel();
        painelBotoes            = new JPanel();
        painelInfoEsq           = new JPanel();
        painelInfoDir           = new JPanel();
        listReservas            = new JList<>();
        labelCliente            = new JLabel("Cliente:");
        labelSala               = new JLabel("Sala:");
        labelData               = new JLabel("Data/Hora:");
        labelDuracao            = new JLabel("Duração:");
        labelStatus             = new JLabel("Status:");
        labelValor              = new JLabel("Valor Total:");
        campoCliente            = new JLabel();
        campoSala               = new JLabel();
        campoData               = new JLabel();
        campoDuracao            = new JLabel();
        campoStatus             = new JLabel();
        campoValor              = new JLabel();
        botaoConfirmarPagamento = new JButton("Confirmar Pagamento");
        botaoCancelarReserva    = new JButton("Cancelar Reserva");
        botaoSair               = new JButton("Sair");
        try { reservas = br.ufjf.dcc.soundcheck.model.Persistencia.carregarReservas(); }
        catch (IOException ex) { reservas = new ArrayList<>(); ex.printStackTrace(); }
        listReservas.setListData(reservas.toArray(new Reserva[0]));
    }

    public void abrirTelaGerenciarReservas() {
        frame.setSize(800, 430);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout(5, 5));

        listReservas.addListSelectionListener(e -> {
            if (listReservas.getSelectedValue() != null) {
                Reserva r = listReservas.getSelectedValue();
                campoCliente.setText(r.getCliente().getNome() + " - " + r.getCliente().getBanda());
                campoSala.setText(r.getSala().toString());
                campoData.setText(r.getData().toLocalDate() + " " + r.getData().toLocalTime());
                campoDuracao.setText(r.getDuracao() + "h");
                campoStatus.setText(r.getStatus().toString());
                campoStatus.setForeground(r.getStatus() == ReservaStatus.CONFIRMADA
                    ? new Color(0, 155, 0) : new Color(155, 35, 35));
                campoValor.setText("R$" + String.format("%.2f", r.getValorTotal()));
            }
        });

        botaoConfirmarPagamento.addActionListener(e -> {
            if (listReservas.getSelectedValue() != null) {
                ReservaController.confirmarPagamento(listReservas.getSelectedValue());
                atualizarLista();
            } else {
                JOptionPane.showMessageDialog(frame, "Nenhuma reserva selecionada!", "Erro!", JOptionPane.ERROR_MESSAGE);
            }
        });

        botaoCancelarReserva.addActionListener(e -> {
            if (listReservas.getSelectedValue() != null) {
                ReservaController.cancelarReserva(listReservas.getSelectedValue());
                atualizarLista();
                limparCampos();
            } else {
                JOptionPane.showMessageDialog(frame, "Nenhuma reserva selecionada!", "Erro!", JOptionPane.ERROR_MESSAGE);
            }
        });

        botaoSair.addActionListener(e -> frame.dispose());

        painelList.setLayout(new BorderLayout(10, 10));
        painelList.setBorder(BorderFactory.createTitledBorder("Reservas"));
        painelList.add(new JScrollPane(listReservas));

        painelInfoEsq.setLayout(new GridLayout(0, 1, 0, 15));
        painelInfoEsq.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        painelInfoEsq.add(labelCliente); painelInfoEsq.add(labelSala);
        painelInfoEsq.add(labelData); painelInfoEsq.add(labelDuracao);
        painelInfoEsq.add(labelStatus); painelInfoEsq.add(labelValor);

        painelInfoDir.setLayout(new GridLayout(0, 1, 0, 15));
        painelInfoDir.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        painelInfoDir.add(campoCliente); painelInfoDir.add(campoSala);
        painelInfoDir.add(campoData); painelInfoDir.add(campoDuracao);
        painelInfoDir.add(campoStatus); painelInfoDir.add(campoValor);

        painelInfo.setLayout(new BorderLayout(5, 5));
        painelInfo.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        painelInfo.add(painelInfoEsq, BorderLayout.WEST);
        painelInfo.add(painelInfoDir, BorderLayout.CENTER);

        painelBotoes.setLayout(new GridLayout(0, 3, 10, 0));
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(10, 20, 15, 20));
        painelBotoes.add(botaoSair);
        painelBotoes.add(botaoCancelarReserva);
        painelBotoes.add(botaoConfirmarPagamento);

        painelPrincipal.setLayout(new BorderLayout(10, 10));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        painelPrincipal.add(painelList, BorderLayout.CENTER);
        painelPrincipal.add(painelInfo, BorderLayout.EAST);

        frame.add(painelPrincipal, BorderLayout.CENTER);
        frame.add(painelBotoes, BorderLayout.SOUTH);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    private void atualizarLista() {
        try { reservas = br.ufjf.dcc.soundcheck.model.Persistencia.carregarReservas(); }
        catch (IOException ex) { ex.printStackTrace(); }
        listReservas.setListData(reservas.toArray(new Reserva[0]));
    }

    private void limparCampos() {
        campoCliente.setText(null); campoSala.setText(null);
        campoData.setText(null); campoDuracao.setText(null);
        campoStatus.setText(null); campoValor.setText(null);
        listReservas.clearSelection();
    }
}
