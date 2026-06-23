/*  Rafael Magno Campos da Silva - 202435012
    Gabriel Haddad Cyrino Gadioli - 202435026
    Thomas Adam Chapman - 202476030 */

package br.ufjf.dcc.soundcheck.controller;

import br.ufjf.dcc.soundcheck.model.*;
import javax.swing.*;
import java.io.IOException;

public class EquipamentoController {

    public static void cadastrarEquipamento(String nome, float preco, int quantidade) {
        try {
            EquipamentoExtra eq = new EquipamentoExtra(nome, preco, quantidade);
            Persistencia.salvarEquipamento(eq);
            JOptionPane.showMessageDialog(null, "Equipamento cadastrado!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) { ex.printStackTrace(); }
    }

    public static void alterarEquipamento(EquipamentoExtra eq, String nome, float preco, int quantidade, boolean disponivel) {
        try { Persistencia.deletarEquipamento(eq); } catch (IOException ex) { ex.printStackTrace(); }
        try {
            eq.setNome(nome); eq.setPrecoUnidade(preco);
            eq.setQuantidade(quantidade); eq.setDisponivel(disponivel);
            JOptionPane.showMessageDialog(null, "Equipamento atualizado!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        try { Persistencia.salvarEquipamento(eq); } catch (IOException ex) { ex.printStackTrace(); }
    }

    public static void deletarEquipamento(EquipamentoExtra eq) {
        try {
            Persistencia.deletarEquipamento(eq);
            JOptionPane.showMessageDialog(null, "Equipamento removido!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) { ex.printStackTrace(); }
    }
}
