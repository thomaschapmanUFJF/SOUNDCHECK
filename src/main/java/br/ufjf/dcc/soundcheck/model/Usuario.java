/*  Rafael Magno Campos da Silva - 202435012
    Gabriel Haddad Cyrino Gadioli - 202435026
    Thomas Adam Chapman - 202476030 */

package br.ufjf.dcc.soundcheck.model;

import br.ufjf.dcc.soundcheck.model.enums.TipoUsuario;
import java.io.Serializable;

public abstract class Usuario implements Serializable, Savable {
    private static final long serialVersionUID = 1L;
    private String nome;
    private String cpf;
    private String email;
    private String senha;
    private String telefone;

    private TipoUsuario tipo;

    public Usuario(String nome, String cpf, String telefone, String email, String senha, TipoUsuario tipo) {
        setInfo(nome,cpf,telefone,email,senha);
        this.tipo = tipo;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) {
        if (nome == null || nome.trim().length() <= 1)
            throw new IllegalArgumentException("O nome deve ter mais de um caractere");
        this.nome = nome;
    }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) {
        if (cpf == null || cpf.trim().isEmpty())
            throw new IllegalArgumentException("CPF não pode ser vazio ou nulo.");
        cpf = cpf.replaceAll("[^0-9]", "");
        if (cpf.length() != 11 || !cpf.matches("\\d{11}"))
            throw new IllegalArgumentException("CPF deve conter 11 dígitos numéricos.");
        if (cpf.matches("(\\d)\\1{10}"))
            throw new IllegalArgumentException("CPF inválido.");
        this.cpf = String.format("%s.%s.%s-%s",
            cpf.substring(0,3), cpf.substring(3,6), cpf.substring(6,9), cpf.substring(9));
    }

    public String getSenha() { return senha; }
    public void setSenha(String senha) {
        if (senha == null || senha.trim().isEmpty())
            throw new IllegalArgumentException("A senha é obrigatória.");
        this.senha = senha;
    }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) {
        if (telefone == null || telefone.trim().isEmpty())
            throw new IllegalArgumentException("Telefone não pode ser vazio ou nulo.");
        telefone = telefone.replaceAll("[^0-9]", "");
        if (telefone.length() != 11)
            throw new IllegalArgumentException("Telefone deve conter 11 dígitos numéricos.");
        this.telefone = String.format("(%s)%s-%s",
            telefone.substring(0,2), telefone.substring(2,7), telefone.substring(7));
    }

    public String getEmail() { return email; }
    public void setEmail(String email) {
        if (email == null || email.trim().isEmpty())
            throw new IllegalArgumentException("Email não pode ser vazio ou nulo.");
        if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"))
            throw new IllegalArgumentException("Email inválido.");
        this.email = email;
    }
    
    public void setInfo(String nome, String cpf, String telefone, String email, String senha){
        setNome(nome);
        setCpf(cpf);
        setSenha(senha);
        setTelefone(telefone);
        setEmail(email);
    }

    public TipoUsuario getTipo() { return this.tipo; }
    @Override

    public String getArquivo(){
        return "data/usuarios.txt";
    }

    @Override
    public String toLinha(){
        return String.join(";",
        this.tipo.toString(), 
        this.nome, 
        this.cpf, 
        this.senha, 
        this.telefone,
        this.email);
    }
}
