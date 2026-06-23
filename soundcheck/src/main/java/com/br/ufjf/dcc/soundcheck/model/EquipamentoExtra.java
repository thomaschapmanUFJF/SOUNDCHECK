/*  Rafael Magno Campos da Silva - 202435012
    Gabriel Haddad Cyrino Gadioli - 202435026
    Thomas Adam Chapman - 202476030 */

package br.ufjf.dcc.soundcheck.model;

public class EquipamentoExtra {
    private String nome;
    private float precoUnidade;
    private int quantidade;
    private boolean disponivel;

    public EquipamentoExtra(String nome, float precoUnidade, int quantidade) {
        setNome(nome); setPrecoUnidade(precoUnidade); setQuantidade(quantidade);
        this.disponivel = true;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) throw new IllegalArgumentException("Nome do equipamento não pode ser vazio.");
        this.nome = nome;
    }

    public float getPrecoUnidade() { return precoUnidade; }
    public void setPrecoUnidade(float precoUnidade) {
        if (precoUnidade < 0) throw new IllegalArgumentException("Preço não pode ser negativo.");
        this.precoUnidade = precoUnidade;
    }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) {
        if (quantidade < 0) throw new IllegalArgumentException("Quantidade não pode ser negativa.");
        this.quantidade = quantidade;
    }

    public boolean isDisponivel() { return disponivel; }
    public void setDisponivel(boolean disponivel) { this.disponivel = disponivel; }

    @Override
    public String toString() { return nome + " - R$" + String.format("%.2f", precoUnidade) + "/un - Estoque: " + quantidade; }
}
