/*  Rafael Magno Campos da Silva - 202435012
    Gabriel Haddad Cyrino Gadioli - 202435026
    Thomas Adam Chapman - 202476030 */

package br.ufjf.dcc.soundcheck.model;

import br.ufjf.dcc.soundcheck.model.enums.ReservaStatus;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Reserva {
    private Cliente cliente;
    private Sala sala;
    private LocalDateTime data;
    private float duracao;
    private boolean pagamentoConfirmado;
    private ReservaStatus status;
    private List<EquipamentoExtra> equipExtras;
    private float valorTotal;

    public Reserva(Cliente cliente, Sala sala, LocalDateTime data, float duracao) {
        setCliente(cliente); setSala(sala); setData(data); setDuracao(duracao);
        this.pagamentoConfirmado = false;
        this.status = ReservaStatus.PENDENTE;
        this.equipExtras = new ArrayList<>();
        this.valorTotal = 0;
    }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) {
        if (cliente == null) throw new IllegalArgumentException("A reserva deve ter um cliente.");
        this.cliente = cliente;
    }

    public Sala getSala() { return sala; }
    public void setSala(Sala sala) {
        if (sala == null) throw new IllegalArgumentException("A reserva deve ter uma sala.");
        this.sala = sala;
    }

    public LocalDateTime getData() { return data; }
    public void setData(LocalDateTime data) {
        if (data == null) throw new IllegalArgumentException("A data é obrigatória.");
        this.data = data;
    }

    public float getDuracao() { return duracao; }
    public void setDuracao(float duracao) {
        if (duracao <= 0) throw new IllegalArgumentException("Duração deve ser maior que zero.");
        this.duracao = duracao;
    }

    public boolean isPagamentoConfirmado() { return pagamentoConfirmado; }
    public void setPagamentoConfirmado(boolean pagamentoConfirmado) { this.pagamentoConfirmado = pagamentoConfirmado; }

    public ReservaStatus getStatus() { return status; }
    public void setStatus(ReservaStatus status) { this.status = status; }

    public List<EquipamentoExtra> getEquipExtras() { return equipExtras; }

    public void adicionarEquipamento(EquipamentoExtra equipamento) {
        this.equipExtras.add(equipamento);
        atualizaValorTotal();
    }

    public float getValorTotal() { return valorTotal; }
    public void setValorTotal(float valorTotal) { this.valorTotal = valorTotal; }

    public void atualizaValorTotal() {
        // TODO: valorTotal = sala.getValor() * duracao + soma de cada equipamento.getPrecoUnidade()
    }

    @Override
    public String toString() {
        return "Sala " + sala.getNumero() + " | " + data.toLocalDate() + " " + data.toLocalTime()
            + " | " + duracao + "h | " + status + " | R$" + String.format("%.2f", valorTotal);
    }
}
