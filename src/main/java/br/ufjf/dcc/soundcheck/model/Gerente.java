/*  Rafael Magno Campos da Silva - 202435012
    Gabriel Haddad Cyrino Gadioli - 202435026
    Thomas Adam Chapman - 202476030 */

package br.ufjf.dcc.soundcheck.model;

import br.ufjf.dcc.soundcheck.model.enums.TipoUsuario;

public class Gerente extends Usuario {
    private static final long serialVersionUID = 1L;
    private String nivel;

    public Gerente(String nome, String cpf, String telefone, String email, String senha, String nivel) {
        super(nome, cpf, telefone, email, senha, TipoUsuario.GERENTE);
        setNivel(nivel);
    }

    public String getNivel() { return nivel; }
    public void setNivel(String nivel) {
        if (nivel == null || nivel.trim().isEmpty()) throw new IllegalArgumentException("O nível é obrigatório.");
        this.nivel = nivel;
    }

    public void setInfo(String nome, String cpf, String telefone, String email, String senha, String nivel) {
        super.setInfo(nome, cpf, telefone, email, senha);
        setNivel(nivel);
    }

    @Override
    public String toLinha() {
        return String.join(";",
            "GERENTE",
            getNome(),
            getCpf(),
            getTelefone(),
            getEmail(),
            getSenha(),
            nivel);
    }

    @Override
    public String toString() { return getNome() + " - Nível: " + nivel; }
}