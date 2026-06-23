/*  Rafael Magno Campos da Silva - 202435012
    Gabriel Haddad Cyrino Gadioli - 202435026
    Thomas Adam Chapman - 202476030 */

package br.ufjf.dcc.soundcheck.model;

import br.ufjf.dcc.soundcheck.model.enums.ReservaStatus;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Reserva implements Savable {
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
    public void setPagamentoConfirmado(boolean pagamentoConfirmado) { this.pagamentoConfirmado = pagamentoConfirmado; this.status = ReservaStatus.CONFIRMADA;}

    public ReservaStatus getStatus() { return status; }
    public void setStatus(ReservaStatus status) { this.status = status; }

    public List<EquipamentoExtra> getEquipExtras() { return equipExtras; }

    public void adicionarEquipamento(List<EquipamentoExtra> equipamentos){
        for (EquipamentoExtra equipamento: equipamentos){
            adicionarEquipamento(equipamento);
        }
    }
    public void adicionarEquipamento(EquipamentoExtra equipamento) {
        this.equipExtras.add(equipamento);
        atualizaValorTotal();
    }

    public float getValorTotal() { return valorTotal; }
    public void setValorTotal(float valorTotal) { this.valorTotal = valorTotal; }

    public void atualizaValorTotal() {
        float novoValor = this.sala.getValor() * this.duracao;
        for (EquipamentoExtra eq : this.equipExtras){
            novoValor += eq.getPrecoUnidade();
        }
        this.valorTotal = novoValor;
    }

    private static final java.time.format.DateTimeFormatter FMT =
        java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    @Override
    public String getArquivo() { return "data/reservas.txt"; }

    @Override
    public String toLinha() {
        StringBuilder equips = new StringBuilder();
        for (EquipamentoExtra eq : equipExtras)
            equips.append(eq.getNome().replace(";", "΋")).append("΋");
        return String.join(";",
            cliente.getCpf(),
            sala.getNumero(),
            data.format(FMT),
            String.valueOf(duracao),
            String.valueOf(pagamentoConfirmado),
            status.toString(),
            String.valueOf(valorTotal),
            equips.toString());
    }

    @Override
    public String toString() {
        return "Sala " + sala.getNumero() + " | " + data.toLocalDate() + " " + data.toLocalTime()
            + " | " + duracao + "h | " + status + " | R$" + String.format("%.2f", valorTotal);
    }
}