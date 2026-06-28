/*  Rafael Magno Campos da Silva - 202435012
    Gabriel Haddad Cyrino Gadioli - 202435026
    Thomas Adam Chapman - 202476030 */

package br.ufjf.dcc.soundcheck.controller;

import br.ufjf.dcc.soundcheck.model.*;

public class EquipamentoController {

    public static void cadastrarEquipamento(String nome, float preco, int quantidade) {
        try {
            EquipamentoExtra eq = new EquipamentoExtra(nome, preco, quantidade);
            Persistencia.salvar(eq);
            Metodos.exibeSucesso("Equipamento cadastrado!");
        } catch (IllegalArgumentException ex) {
            Metodos.exibeErro(ex);
        }
    }

    public static void alterarEquipamento(EquipamentoExtra eq, String nome, float preco, int quantidade, boolean disponivel) {
        try {
            Persistencia.deletar(eq);
            eq.setInfo(nome, preco, quantidade, disponivel);
            Persistencia.salvar(eq);
            Metodos.exibeSucesso("Equipamento atualizado!");
        } catch (IllegalArgumentException ex) {
            Metodos.exibeErro(ex);
        }
    }

    public static void deletarEquipamento(EquipamentoExtra eq) {
        Persistencia.deletar(eq);
        Metodos.exibeSucesso("Equipamento removido!");
    }
}