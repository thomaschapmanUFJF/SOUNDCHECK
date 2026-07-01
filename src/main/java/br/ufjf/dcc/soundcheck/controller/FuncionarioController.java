/*  Rafael Magno Campos da Silva - 202435012
    Gabriel Haddad Cyrino Gadioli - 202435026
    Thomas Adam Chapman - 202476030 */

package br.ufjf.dcc.soundcheck.controller;

import br.ufjf.dcc.soundcheck.model.*;
import java.io.IOException;
import java.util.ArrayList;

public class FuncionarioController {

    public static void cadastrarFuncionario(String nome, String cpf, String telefone,
            String email, String senha, String matricula, String area) {
        try {
            if (!isCpfDisponivel(cpf)) {
                Metodos.exibeErro(new IllegalArgumentException("Já existe um funcionário cadastrado com este CPF!"));
                return;
            }
            if (!isMatriculaDisponivel(matricula)) {
                Metodos.exibeErro(new IllegalArgumentException("Já existe um funcionário cadastrado com esta matrícula!"));
                return;
            }
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
            if (!f.getCpf().equals(cpf) && !isCpfDisponivel(cpf)) {
                Metodos.exibeErro(new IllegalArgumentException("Já existe um funcionário cadastrado com este CPF!"));
                return;
            }
            if (!f.getMatricula().equals(matricula) && !isMatriculaDisponivel(matricula)) {
                Metodos.exibeErro(new IllegalArgumentException("Já existe um funcionário cadastrado com esta matrícula!"));
                return;
            }
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

    private static boolean isCpfDisponivel(String cpf) {
        try {
            ArrayList<Funcionario> funcionarios = Persistencia.carregarFuncionarios();
            for (Funcionario f : funcionarios) {
                if (f.getCpf().equals(cpf)) {
                    return false;
                }
            }
            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
            return true;
        }
    }

    private static boolean isMatriculaDisponivel(String matricula) {
        try {
            ArrayList<Funcionario> funcionarios = Persistencia.carregarFuncionarios();
            for (Funcionario f : funcionarios) {
                if (f.getMatricula().equals(matricula)) {
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