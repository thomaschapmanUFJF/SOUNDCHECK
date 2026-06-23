/*  Rafael Magno Campos da Silva - 202435012
    Gabriel Haddad Cyrino Gadioli - 202435026
    Thomas Adam Chapman - 202476030 */

package br.ufjf.dcc.soundcheck.controller;

import br.ufjf.dcc.soundcheck.model.*;
import javax.swing.*;
import java.io.IOException;

public class ClienteController {

    public static void cadastrarCliente(String nome, String cpf, String telefone, String email,
            String senha, String rua, String bairro, String cep, String cidade, String estado,
            String numero, String complemento, String banda, String estiloMusical) {
        try {
            Endereco e = new Endereco(rua, bairro, cep, cidade, estado, numero, complemento);
            Cliente c = new Cliente(nome, cpf, telefone, email, senha, e, banda, estiloMusical);
            Persistencia.salvarUsuario(c);
            JOptionPane.showMessageDialog(null, "Cliente cadastrado!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) { ex.printStackTrace(); }
    }

    public static void alterarDadosCliente(Cliente c, String nome, String cpf, String telefone,
            String email, String senha, String rua, String bairro, String cep, String cidade,
            String estado, String numero, String complemento, String banda, String estiloMusical) {
        try { Persistencia.deletarUsuario(c); } catch (IOException ex) { ex.printStackTrace(); }
        try {
            c.setNome(nome); c.setCpf(cpf); c.setTelefone(telefone);
            c.setEmail(email); c.setSenha(senha);
            c.getEndereco().setRua(rua); c.getEndereco().setBairro(bairro);
            c.getEndereco().setCep(cep); c.getEndereco().setCidade(cidade);
            c.getEndereco().setEstado(estado); c.getEndereco().setNumero(numero);
            c.getEndereco().setComplemento(complemento);
            c.setBanda(banda); c.setEstiloMusical(estiloMusical);
            JOptionPane.showMessageDialog(null, "Dados atualizados!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        try { Persistencia.salvarUsuario(c); } catch (IOException ex) { ex.printStackTrace(); }
    }

    public static void deletarCliente(Cliente c) {
        try {
            Persistencia.deletarUsuario(c);
            JOptionPane.showMessageDialog(null, "Cliente removido!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) { ex.printStackTrace(); }
    }
}
