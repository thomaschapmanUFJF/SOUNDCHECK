/*  Rafael Magno Campos da Silva - 202435012
    Gabriel Haddad Cyrino Gadioli - 202435026
    Thomas Adam Chapman - 202476030 */

package br.ufjf.dcc.soundcheck.model;

import br.ufjf.dcc.soundcheck.model.enums.TipoUsuario;
import java.util.ArrayList;
import java.util.List;

public class Cliente extends Usuario {
    private static final long serialVersionUID = 1L;
    private String banda;
    private String estiloMusical;
    private Endereco endereco;
    private List<Reserva> reservas;

    public Cliente(String nome, String cpf, String telefone, String email, String senha,
                   Endereco endereco, String banda, String estiloMusical) {
        super(nome, cpf, telefone, email, senha, TipoUsuario.CLIENTE);
        this.endereco = endereco;
        setBanda(banda);
        setEstiloMusical(estiloMusical);
        this.reservas = new ArrayList<>();
    }

    public String getBanda() { return banda; }
    public void setBanda(String banda) {
        if (banda == null || banda.trim().isEmpty()) throw new IllegalArgumentException("O nome da banda não pode ser vazio.");
        this.banda = banda;
    }

    public String getEstiloMusical() { return estiloMusical; }
    public void setEstiloMusical(String estiloMusical) {
        if (estiloMusical == null || estiloMusical.trim().isEmpty()) throw new IllegalArgumentException("O estilo musical não pode ser vazio.");
        this.estiloMusical = estiloMusical;
    }

    public Endereco getEndereco() { return endereco; }
    public void setEndereco(Endereco endereco) { this.endereco = endereco; }

    public List<Reserva> getReservas() { return reservas; }
    public void adicionarReserva(Reserva reserva) { this.reservas.add(reserva); }

    public void setInfo(String nome, String cpf, String telefone, String email, String senha,
                        String rua, String bairro, String cep, String cidade, String estado,
                        String numero, String complemento, String banda, String estiloMusical) {
        super.setInfo(nome, cpf, telefone, email, senha);
        this.endereco.setInfo(rua, bairro, cep, cidade, estado, numero, complemento);
        setBanda(banda);
        setEstiloMusical(estiloMusical);
    }

    @Override
    public String toLinha() {
        return String.join(";",
            "CLIENTE",
            getNome(),
            getCpf(),
            getTelefone(),
            getEmail(),
            getSenha(),
            endereco.toLinha(),
            banda,
            estiloMusical);
    }

    @Override
    public String toString() { return getNome() + " - " + banda; }
}