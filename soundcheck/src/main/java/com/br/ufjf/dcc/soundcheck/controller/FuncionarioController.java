/*  Rafael Magno Campos da Silva - 202435012
    Gabriel Haddad Cyrino Gadioli - 202435026
    Thomas Adam Chapman - 202476030 */

package br.ufjf.dcc.soundcheck.controller;

import br.ufjf.dcc.soundcheck.model.*;
import javax.swing.*;
import java.io.IOException;

public class FuncionarioController {

    public static void cadastrarFuncionario(String nome, String cpf, String telefone,
            String email, String senha, String matricula, String area) {
        try {
            Funcionario f = new Funcionario(nome, cpf, telefone, email, senha, matricula, area);
            Persistencia.salvarUsuario(f);
            JOptionPane.showMessageDialog(null, "Funcionário cadastrado!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) { ex.printStackTrace(); }
    }

    public static void alterarDadosFuncionario(Funcionario f, String nome, String cpf,
            String telefone, String email, String senha, String matricula, String area) {
        try { Persistencia.deletarUsuario(f); } catch (IOException ex) { ex.printStackTrace(); }
        try {
            f.setNome(nome); f.setCpf(cpf); f.setTelefone(telefone);
            f.setEmail(email); f.setSenha(senha);
            f.setMatricula(matricula); f.setArea(area);
            JOptionPane.showMessageDialog(null, "Dados atualizados!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        try { Persistencia.salvarUsuario(f); } catch (IOException ex) { ex.printStackTrace(); }
    }

    public static void deletarFuncionario(Funcionario f) {
        try {
            Persistencia.deletarUsuario(f);
            JOptionPane.showMessageDialog(null, "Funcionário removido!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) { ex.printStackTrace(); }
    }
}
