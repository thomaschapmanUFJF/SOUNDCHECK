/*  Rafael Magno Campos da Silva - 202435012
    Gabriel Haddad Cyrino Gadioli - 202435026
    Thomas Adam Chapman - 202476030 */

package br.ufjf.dcc.soundcheck.model;

import br.ufjf.dcc.soundcheck.model.enums.ReservaStatus;
import java.io.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Persistencia {

    private static final String ARQUIVO_USUARIOS    = "data/usuarios.txt";
    private static final String ARQUIVO_SALAS       = "data/salas.txt";
    private static final String ARQUIVO_EQUIPAMENTOS = "data/equipamentos.txt";
    private static final String ARQUIVO_RESERVAS    = "data/reservas.txt";
    private static final DateTimeFormatter FMT      = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    // ─── utilidade interna ─────────────────────────────────────────────────
    private static String esc(String s)  { return s == null ? "" : s.replaceAll(";", "΋"); }
    private static String unesc(String s){ return s.replaceAll("΋", ";"); }

    private static void reescreverSemLinha(String arquivo, String linhaAlvo) throws IOException {
        File tmp = new File(arquivo + ".tmp");
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo));
             BufferedWriter bw = new BufferedWriter(new FileWriter(tmp))) {
            String linha;
            while ((linha = br.readLine()) != null)
                if (!linha.equals(linhaAlvo)) { bw.write(linha); bw.newLine(); }
        }
        Files.delete(Paths.get(arquivo));
        Files.move(tmp.toPath(), Paths.get(arquivo));
    }

    // ─── serialização de linha por tipo ───────────────────────────────────
    private static String linhaUsuario(Usuario u) {
        if (u instanceof Cliente c) {
            Endereco e = c.getEndereco();
            return "CLIENTE;" + esc(c.getNome()) + ";" + esc(c.getCpf()) + ";" +
                esc(c.getTelefone()) + ";" + esc(c.getEmail()) + ";" + esc(c.getSenha()) + ";" +
                esc(e.getRua()) + ";" + esc(e.getBairro()) + ";" + esc(e.getCep()) + ";" +
                esc(e.getCidade()) + ";" + esc(e.getEstado()) + ";" + esc(e.getNumero()) + ";" +
                esc(e.getComplemento()) + ";" + esc(c.getBanda()) + ";" + esc(c.getEstiloMusical());
        } else if (u instanceof Funcionario f) {
            return "FUNCIONARIO;" + esc(f.getNome()) + ";" + esc(f.getCpf()) + ";" +
                esc(f.getTelefone()) + ";" + esc(f.getEmail()) + ";" + esc(f.getSenha()) + ";" +
                esc(f.getMatricula()) + ";" + esc(f.getArea());
        } else {
            Gerente g = (Gerente) u;
            return "GERENTE;" + esc(g.getNome()) + ";" + esc(g.getCpf()) + ";" +
                esc(g.getTelefone()) + ";" + esc(g.getEmail()) + ";" + esc(g.getSenha()) + ";" +
                esc(g.getNivel());
        }
    }

    // ─── USUÁRIOS ──────────────────────────────────────────────────────────
    public static void salvarUsuario(Usuario u) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO_USUARIOS, true))) {
            bw.write(linhaUsuario(u)); bw.newLine();
        }
    }

    public static void deletarUsuario(Usuario u) throws IOException {
        reescreverSemLinha(ARQUIVO_USUARIOS, linhaUsuario(u));
    }

    public static ArrayList<Usuario> carregarUsuarios() throws IOException {
        ArrayList<Usuario> lista = new ArrayList<>();
        File f = new File(ARQUIVO_USUARIOS);
        if (!f.exists()) return lista;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] d = linha.split(";");
                switch (d[0]) {
                    case "CLIENTE" -> lista.add(new Cliente(
                        unesc(d[1]), unesc(d[2]), unesc(d[3]), unesc(d[4]), unesc(d[5]),
                        new Endereco(unesc(d[6]), unesc(d[7]), unesc(d[8]), unesc(d[9]),
                            unesc(d[10]), unesc(d[11]), unesc(d[12])),
                        unesc(d[13]), unesc(d[14])));
                    case "FUNCIONARIO" -> lista.add(new Funcionario(
                        unesc(d[1]), unesc(d[2]), unesc(d[3]), unesc(d[4]), unesc(d[5]),
                        unesc(d[6]), unesc(d[7])));
                    case "GERENTE" -> lista.add(new Gerente(
                        unesc(d[1]), unesc(d[2]), unesc(d[3]), unesc(d[4]), unesc(d[5]),
                        unesc(d[6])));
                }
            }
        }
        return lista;
    }

    public static ArrayList<Cliente> carregarClientes() throws IOException {
        ArrayList<Cliente> lista = new ArrayList<>();
        for (Usuario u : carregarUsuarios()) if (u instanceof Cliente c) lista.add(c);
        return lista;
    }

    public static ArrayList<Funcionario> carregarFuncionarios() throws IOException {
        ArrayList<Funcionario> lista = new ArrayList<>();
        for (Usuario u : carregarUsuarios()) if (u instanceof Funcionario f) lista.add(f);
        return lista;
    }

    public static ArrayList<Gerente> carregarGerentes() throws IOException {
        ArrayList<Gerente> lista = new ArrayList<>();
        for (Usuario u : carregarUsuarios()) if (u instanceof Gerente g) lista.add(g);
        return lista;
    }

    // ─── SALAS ─────────────────────────────────────────────────────────────
    private static String linhaSala(Sala s) {
        return esc(s.getNumero()) + ";" + s.getCapacidade() + ";" + s.getValor() + ";" + s.isDisponivel();
    }

    public static void salvarSala(Sala s) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO_SALAS, true))) {
            bw.write(linhaSala(s)); bw.newLine();
        }
    }

    public static void deletarSala(Sala s) throws IOException {
        reescreverSemLinha(ARQUIVO_SALAS, linhaSala(s));
    }

    public static ArrayList<Sala> carregarSalas() throws IOException {
        ArrayList<Sala> lista = new ArrayList<>();
        File f = new File(ARQUIVO_SALAS);
        if (!f.exists()) return lista;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] d = linha.split(";");
                Sala s = new Sala(unesc(d[0]), Integer.parseInt(d[1]), Float.parseFloat(d[2]));
                s.setDisponivel(Boolean.parseBoolean(d[3]));
                lista.add(s);
            }
        }
        return lista;
    }

    // ─── EQUIPAMENTOS ──────────────────────────────────────────────────────
    private static String linhaEquipamento(EquipamentoExtra eq) {
        return esc(eq.getNome()) + ";" + eq.getPrecoUnidade() + ";" + eq.getQuantidade() + ";" + eq.isDisponivel();
    }

    public static void salvarEquipamento(EquipamentoExtra eq) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO_EQUIPAMENTOS, true))) {
            bw.write(linhaEquipamento(eq)); bw.newLine();
        }
    }

    public static void deletarEquipamento(EquipamentoExtra eq) throws IOException {
        reescreverSemLinha(ARQUIVO_EQUIPAMENTOS, linhaEquipamento(eq));
    }

    public static ArrayList<EquipamentoExtra> carregarEquipamentos() throws IOException {
        ArrayList<EquipamentoExtra> lista = new ArrayList<>();
        File f = new File(ARQUIVO_EQUIPAMENTOS);
        if (!f.exists()) return lista;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] d = linha.split(";");
                EquipamentoExtra eq = new EquipamentoExtra(unesc(d[0]), Float.parseFloat(d[1]), Integer.parseInt(d[2]));
                eq.setDisponivel(Boolean.parseBoolean(d[3]));
                lista.add(eq);
            }
        }
        return lista;
    }

    // ─── RESERVAS ──────────────────────────────────────────────────────────
    private static String linhaReserva(Reserva r) {
        StringBuilder equips = new StringBuilder();
        for (EquipamentoExtra eq : r.getEquipExtras()) equips.append(esc(eq.getNome())).append("΋");
        return esc(r.getCliente().getCpf()) + ";" + esc(r.getSala().getNumero()) + ";" +
            r.getData().format(FMT) + ";" + r.getDuracao() + ";" +
            r.isPagamentoConfirmado() + ";" + r.getStatus() + ";" +
            r.getValorTotal() + ";" + equips.toString();
    }

    public static void salvarReserva(Reserva r) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO_RESERVAS, true))) {
            bw.write(linhaReserva(r)); bw.newLine();
        }
    }

    public static void deletarReserva(Reserva r) throws IOException {
        reescreverSemLinha(ARQUIVO_RESERVAS, linhaReserva(r));
    }

    public static ArrayList<Reserva> carregarReservas() throws IOException {
        ArrayList<Reserva> lista = new ArrayList<>();
        File f = new File(ARQUIVO_RESERVAS);
        if (!f.exists()) return lista;

        ArrayList<Cliente>         clientes     = carregarClientes();
        ArrayList<Sala>            salas        = carregarSalas();
        ArrayList<EquipamentoExtra> equipamentos = carregarEquipamentos();

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] d = linha.split(";");
                Cliente cliente = null;
                for (Cliente c : clientes) if (c.getCpf().equals(unesc(d[0]))) { cliente = c; break; }
                Sala sala = null;
                for (Sala s : salas) if (s.getNumero().equals(unesc(d[1]))) { sala = s; break; }
                if (cliente == null || sala == null) continue;

                Reserva r = new Reserva(cliente, sala,
                    LocalDateTime.parse(d[2], FMT), Float.parseFloat(d[3]));
                r.setPagamentoConfirmado(Boolean.parseBoolean(d[4]));
                r.setStatus(ReservaStatus.valueOf(d[5]));
                r.setValorTotal(Float.parseFloat(d[6]));

                if (d.length > 7 && !d[7].isEmpty()) {
                    for (String nomeEq : d[7].split("΋"))
                        for (EquipamentoExtra eq : equipamentos)
                            if (eq.getNome().equals(unesc(nomeEq))) { r.adicionarEquipamento(eq); break; }
                }
                lista.add(r);
            }
        }
        return lista;
    }

    public static ArrayList<Reserva> carregarReservasCliente(Cliente cliente) throws IOException {
        ArrayList<Reserva> lista = new ArrayList<>();
        for (Reserva r : carregarReservas())
            if (r.getCliente().getCpf().equals(cliente.getCpf())) lista.add(r);
        return lista;
    }
}
