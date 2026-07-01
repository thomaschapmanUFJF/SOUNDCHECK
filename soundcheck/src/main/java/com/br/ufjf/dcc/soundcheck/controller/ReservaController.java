/*  Rafael Magno Campos da Silva - 202435012
    Gabriel Haddad Cyrino Gadioli - 202435026
    Thomas Adam Chapman - 202476030 */

package br.ufjf.dcc.soundcheck.controller;

import br.ufjf.dcc.soundcheck.model.*;
import br.ufjf.dcc.soundcheck.model.enums.ReservaStatus;
import javax.swing.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReservaController {

    public static ArrayList<Sala> carregarSalasDisponiveis(LocalDateTime dataHora, float duracao) {
        // TODO: carregar todas as salas, filtrar as que têm disponivel=true
        //       e que não possuem reserva com sobreposição de horário com dataHora + duracao
        return new ArrayList<>();
    }

    public static void fazerReserva(Cliente cliente, Sala sala, LocalDateTime dataHora,
            float duracao, List<EquipamentoExtra> equipamentos) {
        // TODO: criar Reserva, adicionar equipamentos, chamar atualizaValorTotal(),
        //       salvar via Persistencia.salvarReserva(), exibir JOptionPane de confirmação
    }

    public static void cancelarReserva(Reserva reserva) {
        // TODO: verificar se status != CONFIRMADA, alterar para CANCELADA,
        //       deletar e regravar via Persistencia
    }

    public static ArrayList<Reserva> carregarReservasCliente(Cliente cliente) {
        try { return Persistencia.carregarReservasCliente(cliente); }
        catch (IOException ex) { ex.printStackTrace(); return new ArrayList<>(); }
    }

    public static ArrayList<Reserva> carregarReservasPendentes() {
        // TODO: carregar todas as reservas e filtrar as com status == PENDENTE
        return new ArrayList<>();
    }

    public static void confirmarPagamento(Reserva reserva) {
        // TODO: setPagamentoConfirmado(true), setStatus(CONFIRMADA),
        //       deletar e regravar via Persistencia, exibir JOptionPane de sucesso
    }
}
