/*  Rafael Magno Campos da Silva - 202435012
    Gabriel Haddad Cyrino Gadioli - 202435026
    Thomas Adam Chapman - 202476030 */

package br.ufjf.dcc.soundcheck.controller;

import br.ufjf.dcc.soundcheck.model.*;
import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class SalaController {

    public static void cadastrarSala(String numero, int capacidade, float valor) {
        try {
            if (!isNumeroSalaDisponivel(numero)) {
                JOptionPane.showMessageDialog(null,
                    "Já existe uma sala cadastrada com este número!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Sala s = new Sala(numero, capacidade, valor);
            Persistencia.salvar(s);
            Metodos.exibeSucesso("Sala cadastrada!");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void alterarSala(Sala s, String numero, int capacidade, float valor, boolean disponivel) {
        try {
            if (!s.getNumero().equals(numero) && !isNumeroSalaDisponivel(numero)) {
                JOptionPane.showMessageDialog(null,
                    "Já existe uma sala cadastrada com este número!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Persistencia.deletar(s);
            s.setInfo(numero, capacidade, valor, disponivel);
            Persistencia.salvar(s);
            Metodos.exibeSucesso("Sala atualizada!");
        } catch (IllegalArgumentException ex) {
            Metodos.exibeErro(ex);
        }
    }

    public static void deletarSala(Sala s) {
        Persistencia.deletar(s);
        Metodos.exibeSucesso("Sala removida!");
    }

    private static boolean isNumeroSalaDisponivel(String numero) {
        try {
            ArrayList<Sala> salas = Persistencia.carregarSalas();
            for (Sala s : salas) {
                if (s.getNumero().equals(numero)) {
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