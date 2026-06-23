/*  Rafael Magno Campos da Silva - 202435012
    Gabriel Haddad Cyrino Gadioli - 202435026
    Thomas Adam Chapman - 202476030 */

package br.ufjf.dcc.soundcheck.view.TelasCliente;

import br.ufjf.dcc.soundcheck.controller.ReservaController;
import br.ufjf.dcc.soundcheck.model.*;
import br.ufjf.dcc.soundcheck.model.enums.ReservaStatus;
import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

public class TelaReserva {
    private JFrame frame;
    private JPanel painelPrincipal;
    private JPanel painelReservas;
    private JPanel painelAgendar;
    private JPanel painelLabel;
    private JPanel painelCampos;
    private JPanel painelBotoes;
    private JButton botaoConfirmar;
    private JButton botaoCancelar;
    private JButton botaoNovaReserva;
    private JButton botaoSair;
    private JComboBox<Sala> comboBoxSalas;
    private JDateChooser campoData;
    private JSpinner spinnerHora;
    private JSpinner spinnerDuracao;
    private JList<EquipamentoExtra> listaEquipamentos;
    private JList<Reserva> listaReservas;
    private JLabel labelSala;
    private JLabel labelData;
    private JLabel labelHora;
    private JLabel labelDuracao;
    private JLabel labelStatus;
    private JLabel respostaStatus;
    private Cliente clienteAtual;
    private ArrayList<Reserva> reservas;

    public TelaReserva() {
        frame            = new JFrame("Reservas - SoundCheck");
        painelPrincipal   = new JPanel();
        painelReservas    = new JPanel();
        painelAgendar     = new JPanel();
        painelLabel       = new JPanel();
        painelCampos      = new JPanel();
        painelBotoes      = new JPanel();
        botaoConfirmar    = new JButton("Confirmar Reserva");
        botaoCancelar     = new JButton("Cancelar Reserva");
        botaoNovaReserva  = new JButton("Nova Reserva");
        botaoSair         = new JButton("Sair");
        comboBoxSalas     = new JComboBox<>();
        campoData         = new JDateChooser();
        campoData.setDateFormatString("dd/MM/yyyy");
        spinnerHora       = new JSpinner(new SpinnerNumberModel(9, 0, 23, 1));
        spinnerDuracao    = new JSpinner(new SpinnerNumberModel(1.0, 0.5, 12.0, 0.5));
        listaEquipamentos = new JList<>();
        listaReservas     = new JList<>();
        labelSala         = new JLabel("Sala:");
        labelData         = new JLabel("Data:");
        labelHora         = new JLabel("Hora:");
        labelDuracao      = new JLabel("Duração (h):");
        labelStatus       = new JLabel("Status:");
        respostaStatus    = new JLabel();
        respostaStatus.setForeground(new Color(0, 155, 0));
    }

    public void abrirTelaReserva(Cliente cliente) {
        this.clienteAtual = cliente;

        frame.setSize(850, 520);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout(5, 5));

        carregarSalasDisponiveis();
        carregarEquipamentosDisponiveis();

        reservas = ReservaController.carregarReservasCliente(cliente);
        listaReservas.setListData(reservas.toArray(new Reserva[0]));

        listaReservas.addListSelectionListener(e -> {
            if (listaReservas.getSelectedValue() != null) {
                Reserva s = listaReservas.getSelectedValue();
                respostaStatus.setText(s.getStatus().toString());
                comboBoxSalas.removeAllItems();
                comboBoxSalas.addItem(s.getSala());
                comboBoxSalas.setEnabled(false);
                campoData.setEnabled(false);
                spinnerHora.setEnabled(false);
                spinnerDuracao.setEnabled(false);
                listaEquipamentos.setEnabled(false);
            }
        });

        campoData.addPropertyChangeListener("date", e -> {
            if (listaReservas.getSelectedValue() == null) carregarSalasDisponiveis();
        });

        painelReservas.setBorder(BorderFactory.createTitledBorder("Minhas Reservas"));
        painelReservas.setLayout(new BorderLayout(10, 10));
        painelReservas.add(new JScrollPane(listaReservas), BorderLayout.CENTER);

        painelLabel.setLayout(new GridLayout(5, 0, 0, 20));
        painelLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 5, 0));
        painelLabel.add(labelSala);
        painelLabel.add(labelData);
        painelLabel.add(labelHora);
        painelLabel.add(labelDuracao);
        painelLabel.add(labelStatus);

        painelCampos.setLayout(new GridLayout(5, 0, 0, 20));
        painelCampos.setBorder(BorderFactory.createEmptyBorder(20, 0, 5, 20));
        painelCampos.add(comboBoxSalas);
        painelCampos.add(campoData);
        painelCampos.add(spinnerHora);
        painelCampos.add(spinnerDuracao);
        painelCampos.add(respostaStatus);

        painelBotoes.setLayout(new GridLayout(0, 3, 10, 0));
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(10, 20, 15, 20));
        painelBotoes.add(botaoSair);
        painelBotoes.add(botaoCancelar);
        painelBotoes.add(botaoConfirmar);

        botaoConfirmar.addActionListener(e -> fazerNovaReserva());

        botaoCancelar.addActionListener(e -> {
            if (listaReservas.getSelectedValue() != null) {
                Reserva s = listaReservas.getSelectedValue();
                if (s.getStatus() == ReservaStatus.CONFIRMADA) {
                    JOptionPane.showMessageDialog(frame,
                        "Reservas confirmadas só podem ser canceladas por um funcionário.",
                        "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                ReservaController.cancelarReserva(s);
                atualizarListaReservas();
                limparCampos();
                carregarSalasDisponiveis();
                habilitarCampos();
            } else {
                JOptionPane.showMessageDialog(frame, "Nenhuma reserva selecionada!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        botaoNovaReserva.addActionListener(e -> {
            limparCampos();
            habilitarCampos();
            carregarSalasDisponiveis();
            listaReservas.clearSelection();
        });

        botaoSair.addActionListener(e -> frame.dispose());

        listaEquipamentos.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        listaEquipamentos.setBorder(BorderFactory.createTitledBorder("Equipamentos extras"));
        listaEquipamentos.setPreferredSize(new Dimension(200, 120));

        painelAgendar.setLayout(new BorderLayout(10, 10));
        painelAgendar.add(botaoNovaReserva, BorderLayout.NORTH);
        painelAgendar.add(painelLabel, BorderLayout.WEST);
        painelAgendar.add(painelCampos, BorderLayout.CENTER);
        painelAgendar.add(new JScrollPane(listaEquipamentos), BorderLayout.SOUTH);
        painelAgendar.add(painelBotoes, BorderLayout.AFTER_LAST_LINE);

        painelPrincipal.setLayout(new BorderLayout(10, 10));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        painelPrincipal.add(painelReservas, BorderLayout.CENTER);
        painelPrincipal.add(painelAgendar, BorderLayout.EAST);

        frame.add(painelPrincipal);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    private void fazerNovaReserva() {
        Sala sala = (Sala) comboBoxSalas.getSelectedItem();
        java.util.Date date = campoData.getDate();
        int hora = (int) spinnerHora.getValue();
        double duracao = (double) spinnerDuracao.getValue();

        if (sala == null || date == null) {
            JOptionPane.showMessageDialog(frame, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        LocalDateTime dataHora = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().atTime(hora, 0);
        ArrayList<EquipamentoExtra> equips = new ArrayList<>(listaEquipamentos.getSelectedValuesList());
        ReservaController.fazerReserva(clienteAtual, sala, dataHora, (float) duracao, equips);
        atualizarListaReservas();
    }

    private void carregarSalasDisponiveis() {
        java.util.Date date = campoData.getDate();
        int hora = (int) spinnerHora.getValue();
        double duracao = (double) spinnerDuracao.getValue();
        LocalDateTime dataHora = date == null ? LocalDateTime.now() :
            date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().atTime(hora, 0);
        ArrayList<Sala> salas = ReservaController.carregarSalasDisponiveis(dataHora, (float) duracao);
        comboBoxSalas.removeAllItems();
        for (Sala s : salas) comboBoxSalas.addItem(s);
    }

    private void carregarEquipamentosDisponiveis() {
        try {
            ArrayList<EquipamentoExtra> equips = br.ufjf.dcc.soundcheck.model.Persistencia.carregarEquipamentos();
            listaEquipamentos.setListData(equips.toArray(new EquipamentoExtra[0]));
        } catch (Exception ex) { ex.printStackTrace(); }
    }

    private void atualizarListaReservas() {
        reservas = ReservaController.carregarReservasCliente(clienteAtual);
        listaReservas.setListData(reservas.toArray(new Reserva[0]));
    }

    private void limparCampos() {
        if (comboBoxSalas.getItemCount() > 0) {
            comboBoxSalas.setSelectedIndex(0);
        }
        campoData.setDate(null);
        spinnerHora.setValue(9);
        spinnerDuracao.setValue(1.0);
        listaEquipamentos.clearSelection();
        respostaStatus.setText(null);
        listaReservas.clearSelection();
    }

    private void habilitarCampos() {
        comboBoxSalas.setEnabled(true);
        campoData.setEnabled(true);
        spinnerHora.setEnabled(true);
        spinnerDuracao.setEnabled(true);
        listaEquipamentos.setEnabled(true);
    }
}
