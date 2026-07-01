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

public class Persistencia {

    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    // ─── utilidade interna ─────────────────────────────────────────────────
    private static String unesc(String s){ return s.replaceAll("΋", ";"); }

    private static void reescreverSemLinha(String arquivo, String linhaAlvo) {
        try {
            File tmp = new File(arquivo + ".tmp");
            BufferedReader br = new BufferedReader(new FileReader(arquivo));
            BufferedWriter bw = new BufferedWriter(new FileWriter(tmp));
            String linha;
            while ((linha = br.readLine()) != null) {
                if (!linha.equals(linhaAlvo)) {
                    bw.write(linha);
                    bw.newLine();
                }
            }
            br.close();
            bw.close();
            Files.delete(Paths.get(arquivo));
            Files.move(tmp.toPath(), Paths.get(arquivo));
        } catch (Exception e) {
            System.out.println("Ocorreu um erro: " + e.getMessage());
        }
    }

    // ─── genérico ──────────────────────────────────────────────────────────
    public static void salvar(Savable s) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(s.getArquivo(), true))) {
            bw.write(s.toLinha());
            bw.newLine();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deletar(Savable s) {
        reescreverSemLinha(s.getArquivo(), s.toLinha());
    }

    // ─── USUÁRIOS ──────────────────────────────────────────────────────────
    public static ArrayList<Usuario> carregarUsuarios() throws IOException {
        ArrayList<Usuario> lista = new ArrayList<>();
        File f = new File("data/usuarios.txt");
        if (!f.exists()) return lista;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] d = linha.split(";");
                switch (d[0]) {
                    case "CLIENTE":
                        lista.add(new Cliente(
                            unesc(d[1]), unesc(d[2]), unesc(d[3]), unesc(d[4]), unesc(d[5]),
                            new Endereco(unesc(d[6]), unesc(d[7]), unesc(d[8]), unesc(d[9]),
                                unesc(d[10]), unesc(d[11]), unesc(d[12])),
                            unesc(d[13]), unesc(d[14])));
                        break;
                    case "FUNCIONARIO":
                        lista.add(new Funcionario(
                            unesc(d[1]), unesc(d[2]), unesc(d[3]), unesc(d[4]), unesc(d[5]),
                            unesc(d[6]), unesc(d[7])));
                        break;
                    case "GERENTE":
                        lista.add(new Gerente(
                            unesc(d[1]), unesc(d[2]), unesc(d[3]), unesc(d[4]), unesc(d[5]),
                            unesc(d[6])));
                        break;
                    default:
                        break;
                }
            }
        }
        return lista;
    }

    public static ArrayList<Cliente> carregarClientes() throws IOException {
        ArrayList<Cliente> lista = new ArrayList<>();
        for (Usuario u : carregarUsuarios()) {
            if (u instanceof Cliente) {
                lista.add((Cliente) u);
            }
        }
        return lista;
    }

    public static ArrayList<Funcionario> carregarFuncionarios() throws IOException {
        ArrayList<Funcionario> lista = new ArrayList<>();
        for (Usuario u : carregarUsuarios()) {
            if (u instanceof Funcionario) {
                lista.add((Funcionario) u);
            }
        }
        return lista;
    }

    public static ArrayList<Gerente> carregarGerentes() throws IOException {
        ArrayList<Gerente> lista = new ArrayList<>();
        for (Usuario u : carregarUsuarios()) {
            if (u instanceof Gerente) {
                lista.add((Gerente) u);
            }
        }
        return lista;
    }

    // ─── SALAS ─────────────────────────────────────────────────────────────
    public static ArrayList<Sala> carregarSalas() throws IOException {
        ArrayList<Sala> lista = new ArrayList<>();
        File f = new File("data/salas.txt");
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

    public static ArrayList<Sala> carregarSalasDisponiveis() throws IOException {
        ArrayList<Sala> salasDisponiveis = new ArrayList<>();
        for (Sala sala : carregarSalas()) {
            if (sala.isDisponivel()) salasDisponiveis.add(sala);
        }
        return salasDisponiveis;
    }

    // ─── EQUIPAMENTOS ──────────────────────────────────────────────────────
    public static ArrayList<EquipamentoExtra> carregarEquipamentos() throws IOException {
        ArrayList<EquipamentoExtra> lista = new ArrayList<>();
        File f = new File("data/equipamentos.txt");
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
    public static ArrayList<Reserva> carregarReservas() throws IOException {
        ArrayList<Reserva> lista = new ArrayList<>();
        File f = new File("data/reservas.txt");
        if (!f.exists()) return lista;

        ArrayList<Cliente>          clientes     = carregarClientes();
        ArrayList<Sala>             salas        = carregarSalas();
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