/*  Rafael Magno Campos da Silva - 202435012
    Gabriel Haddad Cyrino Gadioli - 202435026
    Thomas Adam Chapman - 202476030 */

package br.ufjf.dcc.soundcheck.model;

import br.ufjf.dcc.soundcheck.model.enums.TipoUsuario;

public class Funcionario extends Usuario {
    private static final long serialVersionUID = 1L;
    private String matricula;
    private String area;

    public Funcionario(String nome, String cpf, String telefone, String email, String senha,
                       String matricula, String area) {
        super(nome, cpf, telefone, email, senha, TipoUsuario.FUNCIONARIO);
        setMatricula(matricula);
        setArea(area);
    }

    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) {
        if (matricula == null || matricula.trim().isEmpty()) throw new IllegalArgumentException("A matrícula é obrigatória.");
        this.matricula = matricula;
    }

    public String getArea() { return area; }
    public void setArea(String area) {
        if (area == null || area.trim().isEmpty()) throw new IllegalArgumentException("A área é obrigatória.");
        this.area = area;
    }

    public void setInfo(String nome, String cpf, String telefone, String email, String senha, String matricula, String area){
        super.setInfo(nome, cpf, telefone, email, senha);
        setMatricula(matricula);
        setArea(area);
    }

    @Override
    public String toLinha() {
        return String.join(";",
            "FUNCIONARIO",
            getNome(),
            getCpf(),
            getTelefone(),
            getEmail(),
            getSenha(),
            matricula,
            area);
    }

    @Override
    public String toString() { return getNome() + " - Matrícula: " + matricula; }
}