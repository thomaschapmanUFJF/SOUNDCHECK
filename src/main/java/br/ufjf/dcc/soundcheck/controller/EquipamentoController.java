/*  Rafael Magno Campos da Silva - 202435012
    Gabriel Haddad Cyrino Gadioli - 202435026
    Thomas Adam Chapman - 202476030 */

package br.ufjf.dcc.soundcheck.controller;

import br.ufjf.dcc.soundcheck.model.*;
import java.io.IOException;
import java.util.ArrayList;

public class EquipamentoController {

    public static void cadastrarEquipamento(String nome, float preco, int quantidade) {
        try {
            if (!isNomeEquipamentoDisponivel(nome)) {
                Metodos.exibeErro(new IllegalArgumentException("Já existe um equipamento cadastrado com este nome!"));
                return;
            }
            EquipamentoExtra eq = new EquipamentoExtra(nome, preco, quantidade);
            Persistencia.salvar(eq);
            Metodos.exibeSucesso("Equipamento cadastrado!");
        } catch (IllegalArgumentException ex) {
            Metodos.exibeErro(ex);
        }
    }

    public static void alterarEquipamento(EquipamentoExtra eq, String nome, float preco, int quantidade, boolean disponivel) {
        try {
            if (!eq.getNome().equals(nome) && !isNomeEquipamentoDisponivel(nome)) {
                Metodos.exibeErro(new IllegalArgumentException("Já existe um equipamento cadastrado com este nome!"));
                return;
            }
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

    private static boolean isNomeEquipamentoDisponivel(String nome) {
        try {
            ArrayList<EquipamentoExtra> equipamentos = Persistencia.carregarEquipamentos();
            for (EquipamentoExtra e : equipamentos) {
                if (e.getNome().equalsIgnoreCase(nome)) {
                    return false;
                }
            }
            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
            return true;
        }
    }
}