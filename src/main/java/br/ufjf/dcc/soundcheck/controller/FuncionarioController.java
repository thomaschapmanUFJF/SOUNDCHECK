/*  Rafael Magno Campos da Silva - 202435012
    Gabriel Haddad Cyrino Gadioli - 202435026
    Thomas Adam Chapman - 202476030 */

package br.ufjf.dcc.soundcheck.controller;

import br.ufjf.dcc.soundcheck.model.*;

public class FuncionarioController {

    public static void cadastrarFuncionario(String nome, String cpf, String telefone,
            String email, String senha, String matricula, String area) {
        try {
            Funcionario f = new Funcionario(nome, cpf, telefone, email, senha, matricula, area);
            Persistencia.salvar(f);
            Metodos.exibeSucesso("Funcionário cadastrado!");
        } catch (IllegalArgumentException ex) {
            Metodos.exibeErro(ex);
        }
    }

    public static void alterarDadosFuncionario(Funcionario f, String nome, String cpf,
            String telefone, String email, String senha, String matricula, String area) {
        try {
            Persistencia.deletar(f);
            f.setInfo(nome, cpf, telefone, email, senha, matricula, area);
            Persistencia.salvar(f);
            Metodos.exibeSucesso("Dados atualizados!");
        } catch (IllegalArgumentException ex) {
            Metodos.exibeErro(ex);
        }
    }

    public static void deletarFuncionario(Funcionario f) {
        Persistencia.deletar(f);
        Metodos.exibeSucesso("Funcionário removido!");
    }
}