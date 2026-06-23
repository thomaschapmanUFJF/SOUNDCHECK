/*  Rafael Magno Campos da Silva - 202435012
    Gabriel Haddad Cyrino Gadioli - 202435026
    Thomas Adam Chapman - 202476030 */

package br.ufjf.dcc.soundcheck.model;

public class Endereco {
    private String rua;
    private String bairro;
    private String cep;
    private String cidade;
    private String estado;
    private String numero;
    private String complemento;

    public Endereco(String rua, String bairro, String cep, String cidade, String estado, String numero, String complemento) {
        setRua(rua); setBairro(bairro); setCep(cep);
        setCidade(cidade); setEstado(estado); setNumero(numero);
        this.complemento = complemento;
    }

    public String getRua() { return rua; }
    public void setRua(String rua) {
        if (rua == null || rua.trim().isEmpty()) throw new IllegalArgumentException("A rua não pode ser vazia.");
        this.rua = rua;
    }

    public String getBairro() { return bairro; }
    public void setBairro(String bairro) {
        if (bairro == null || bairro.trim().isEmpty()) throw new IllegalArgumentException("O bairro não pode ser vazio.");
        this.bairro = bairro;
    }

    public String getCep() { return cep; }
    public void setCep(String cep) {
        if (cep == null || cep.trim().isEmpty()) throw new IllegalArgumentException("CEP não pode ser vazio.");
        cep = cep.replaceAll("[^0-9]", "");
        if (cep.length() != 8) throw new IllegalArgumentException("CEP deve conter 8 dígitos.");
        this.cep = String.format("%s-%s", cep.substring(0,5), cep.substring(5));
    }

    public String getCidade() { return cidade; }
    public void setCidade(String cidade) {
        if (cidade == null || cidade.trim().isEmpty()) throw new IllegalArgumentException("A cidade não pode ser vazia.");
        this.cidade = cidade;
    }

    public String getEstado() { return estado; }
    public void setEstado(String estado) {
        if (estado == null || estado.trim().isEmpty()) throw new IllegalArgumentException("O estado não pode ser vazio.");
        this.estado = estado;
    }

    public String getNumero() { return numero; }
    public void setNumero(String numero) {
        if (numero == null || numero.trim().isEmpty()) throw new IllegalArgumentException("O número não pode ser vazio.");
        this.numero = numero;
    }

    public String getComplemento() { return complemento; }
    public void setComplemento(String complemento) { this.complemento = complemento; }

    public void setInfo(String rua, String bairro, String cep, String cidade,
                        String estado, String numero, String complemento) {
        setRua(rua); setBairro(bairro); setCep(cep);
        setCidade(cidade); setEstado(estado); setNumero(numero);
        setComplemento(complemento);
    }

    @Override
    public String toString() {
        return rua + ", " + numero + " - " + bairro + ", " + cidade + "/" + estado + " - CEP: " + cep;
    }

    public String toLinha() {
        return String.join(";",            
            this.getRua(),
            this.getBairro(),
            this.getCep(),
            this.getCidade(),
            this.getEstado(),
            this.getNumero(),
            this.getComplemento() == null ? "" : this.getComplemento());
    }
}