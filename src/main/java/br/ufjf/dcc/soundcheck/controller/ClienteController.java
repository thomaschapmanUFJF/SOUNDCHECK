/*  Rafael Magno Campos da Silva - 202435012
    Gabriel Haddad Cyrino Gadioli - 202435026
    Thomas Adam Chapman - 202476030 */

package br.ufjf.dcc.soundcheck.controller;

import br.ufjf.dcc.soundcheck.model.*;

public class ClienteController {

    public static void cadastrarCliente(String nome, String cpf, String telefone, String email,
            String senha, String rua, String bairro, String cep, String cidade, String estado,
            String numero, String complemento, String banda, String estiloMusical) {
        try {
            for (Cliente existente : Persistencia.carregarClientes()) {
                if (existente.getCpf().equals(cpf)) {
                    throw new IllegalArgumentException("Já existe um cliente cadastrado com esse CPF.");
                }
            }
            Endereco e = new Endereco(rua, bairro, cep, cidade, estado, numero, complemento);
            Cliente c = new Cliente(nome, cpf, telefone, email, senha, e, banda, estiloMusical);
            Persistencia.salvar(c);
            Metodos.exibeSucesso("Cliente cadastrado!");
        } catch (IllegalArgumentException | java.io.IOException ex) {
            Metodos.exibeErro(ex);
        }
    }

    public static void alterarDadosCliente(Cliente c, String nome, String cpf, String telefone,
            String email, String senha, String rua, String bairro, String cep, String cidade,
            String estado, String numero, String complemento, String banda, String estiloMusical) {
        try {
            Persistencia.deletar(c);
            c.setInfo(nome, cpf, telefone, email, senha, rua, bairro, cep, cidade, estado, numero, complemento, banda, estiloMusical);
            Persistencia.salvar(c);
            Metodos.exibeSucesso("Dados atualizados!");
        } catch (IllegalArgumentException ex) {
            Metodos.exibeErro(ex);
        }
    }

    public static void deletarCliente(Cliente c) {
        Persistencia.deletar(c);
        Metodos.exibeSucesso("Cliente removido!");
    }
}