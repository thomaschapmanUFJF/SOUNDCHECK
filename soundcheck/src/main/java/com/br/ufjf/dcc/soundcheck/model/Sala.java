/*  Rafael Magno Campos da Silva - 202435012
    Gabriel Haddad Cyrino Gadioli - 202435026
    Thomas Adam Chapman - 202476030 */

package br.ufjf.dcc.soundcheck.model;

public class Sala {
    private String numero;
    private int capacidade;
    private float valor;
    private boolean disponivel;

    public Sala(String numero, int capacidade, float valor) {
        setNumero(numero); setCapacidade(capacidade); setValor(valor);
        this.disponivel = true;
    }

    public String getNumero() { return numero; }
    public void setNumero(String numero) {
        if (numero == null || numero.trim().isEmpty()) throw new IllegalArgumentException("Número da sala não pode ser vazio.");
        this.numero = numero;
    }

    public int getCapacidade() { return capacidade; }
    public void setCapacidade(int capacidade) {
        if (capacidade <= 0) throw new IllegalArgumentException("Capacidade deve ser maior que zero.");
        this.capacidade = capacidade;
    }

    public float getValor() { return valor; }
    public void setValor(float valor) {
        if (valor < 0) throw new IllegalArgumentException("Valor não pode ser negativo.");
        this.valor = valor;
    }

    public boolean isDisponivel() { return disponivel; }
    public void setDisponivel(boolean disponivel) { this.disponivel = disponivel; }

    @Override
    public String toString() {
        return "Sala " + numero + " - " + capacidade + " pessoas - R$" + String.format("%.2f", valor) + "/h";
    }
}
