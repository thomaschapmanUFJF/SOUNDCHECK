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
    public static ArrayList<Sala> carregarSalasDisponiveis(LocalDateTime dataHoraDesejada, float duracao) {
        try{
        LocalDateTime horaFim = dataHoraDesejada.plusMinutes((long)(duracao * 60));

        ArrayList<Reserva> reservas = Persistencia.carregarReservas();
        ArrayList<Sala> indisponiveis = new ArrayList<>();

        for (Reserva r: reservas){

            if (r.getStatus() == ReservaStatus.CANCELADA) continue;
            if (indisponiveis.contains(r.getSala())) continue;

            LocalDateTime rInicio = r.getData();
            LocalDateTime rFim = rInicio.plusMinutes((long) (r.getDuracao() * 60));
            boolean temOverlap = rInicio.isBefore(horaFim) && rFim.isAfter(dataHoraDesejada);
            if (temOverlap){
                indisponiveis.add(r.getSala());
                continue;
                }
            }

            ArrayList<Sala> disponiveis = new ArrayList<>();
            for (Sala s : Persistencia.carregarSalas()) {
                if (s.isDisponivel() && !indisponiveis.contains(s))
                    disponiveis.add(s);
            }
            return disponiveis;   
        }
        catch (IOException e){
            e.printStackTrace();
            return new ArrayList<>();
        }     
    }
         

    public static void fazerReserva(Cliente cliente, Sala sala, LocalDateTime dataHora,
        float duracao, List<EquipamentoExtra> equipamentos) {
            Reserva nova = new Reserva(cliente, sala, dataHora, duracao);
            nova.adicionarEquipamento(equipamentos);
            Persistencia.salvar(nova);
            JOptionPane.showMessageDialog(null, "Reserva cadastrada!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void cancelarReserva(Reserva reserva) {
        Persistencia.deletar(reserva);
        JOptionPane.showMessageDialog(null, "Reserva cancelada!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    public static ArrayList<Reserva> carregarReservasCliente(Cliente cliente) {
        try { return Persistencia.carregarReservasCliente(cliente); }
        catch (IOException ex) { ex.printStackTrace(); return new ArrayList<>(); }
    }

    public static ArrayList<Reserva> carregarReservasPendentes() {
        try{
            ArrayList<Reserva> reservas = Persistencia.carregarReservas();
            ArrayList<Reserva> reservasPendentes = new ArrayList<>();
            for (Reserva r : reservas){
                if(r.getStatus() == ReservaStatus.PENDENTE){
                    reservasPendentes.add(r);
                }
            }
            return reservasPendentes;
        }
        catch (IOException ex) { ex.printStackTrace(); return new ArrayList<>();}
    }

    public static void confirmarPagamento(Reserva reserva) {
            Persistencia.deletar(reserva);
            reserva.setPagamentoConfirmado(true);
            Persistencia.salvar(reserva);
            JOptionPane.showMessageDialog(null, "Pagamento confirmado!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }
}