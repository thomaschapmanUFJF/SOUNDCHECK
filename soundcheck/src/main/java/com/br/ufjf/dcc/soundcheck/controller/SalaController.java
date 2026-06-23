/*  Rafael Magno Campos da Silva - 202435012
    Gabriel Haddad Cyrino Gadioli - 202435026
    Thomas Adam Chapman - 202476030 */

package br.ufjf.dcc.soundcheck.controller;

import br.ufjf.dcc.soundcheck.model.*;
import javax.swing.*;
import java.io.IOException;

public class SalaController {

    public static void cadastrarSala(String numero, int capacidade, float valor) {
        try {
            Sala s = new Sala(numero, capacidade, valor);
            Persistencia.salvarSala(s);
            JOptionPane.showMessageDialog(null, "Sala cadastrada!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) { ex.printStackTrace(); }
    }

    public static void alterarSala(Sala s, String numero, int capacidade, float valor, boolean disponivel) {
        try { Persistencia.deletarSala(s); } catch (IOException ex) { ex.printStackTrace(); }
        try {
            s.setNumero(numero); s.setCapacidade(capacidade);
            s.setValor(valor); s.setDisponivel(disponivel);
            JOptionPane.showMessageDialog(null, "Sala atualizada!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        try { Persistencia.salvarSala(s); } catch (IOException ex) { ex.printStackTrace(); }
    }

    public static void deletarSala(Sala s) {
        try {
            Persistencia.deletarSala(s);
            JOptionPane.showMessageDialog(null, "Sala removida!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) { ex.printStackTrace(); }
    }
}
