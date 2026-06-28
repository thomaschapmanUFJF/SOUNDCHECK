/*  Rafael Magno Campos da Silva - 202435012
    Gabriel Haddad Cyrino Gadioli - 202435026
    Thomas Adam Chapman - 202476030 */

package br.ufjf.dcc.soundcheck.view.TelasFuncionario;

import br.ufjf.dcc.soundcheck.model.Funcionario;
import br.ufjf.dcc.soundcheck.view.Tema;
import javax.swing.*;
import java.awt.*;

public class TelaMenuFuncionario {
    private JFrame frame;

    public TelaMenuFuncionario() {
        frame = new JFrame("SoundCheck — Funcionário");
    }

    public void abrirTelaMenuFuncionario(Funcionario funcionario) {
        Tema.estilizarFrame(frame);
        frame.setSize(500, 480);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());

        JPanel topo = new JPanel(new BorderLayout());
        topo.setBackground(Tema.FUNDO);
        topo.setBorder(BorderFactory.createEmptyBorder(28, 40, 16, 40));
        JLabel titulo = Tema.labelTitulo("SoundCheck");
        JLabel sub    = Tema.labelSubtitulo("Funcionário · " + funcionario.getNome().split(" ")[0]);
        topo.add(titulo, BorderLayout.CENTER);
        topo.add(sub,    BorderLayout.SOUTH);

        JPanel botoes = new JPanel(new GridLayout(4, 1, 0, 12));
        botoes.setBackground(Tema.FUNDO);
        botoes.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));

        JButton bClientes    = Tema.botaoPrimario("👥  Gerenciar Clientes");
        JButton bReservas    = Tema.botaoPrimario("📋  Gerenciar Reservas");
        JButton bSalas       = Tema.botaoSecundario("🚪  Registrar Salas");
        JButton bEquipamentos = Tema.botaoSecundario("🎛  Registrar Equipamentos");

        for (JButton b : new JButton[]{bClientes, bReservas, bSalas, bEquipamentos}) {
            b.setFont(new Font("Segoe UI", Font.BOLD, 14));
            b.setPreferredSize(new Dimension(0, 48));
        }

        bClientes.addActionListener(e     -> new TelaCadastroCliente().abrirTelaCadastroCliente());
        bReservas.addActionListener(e     -> new TelaGerenciarReservas().abrirTelaGerenciarReservas());
        bSalas.addActionListener(e        -> new TelaRegistrarSalas().abrirTelaRegistrarSalas());
        bEquipamentos.addActionListener(e -> new TelaRegistrarEquipamentos().abrirTelaRegistrarEquipamentos());

        botoes.add(bClientes);
        botoes.add(bReservas);
        botoes.add(bSalas);
        botoes.add(bEquipamentos);

        JPanel rodape = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rodape.setBackground(Tema.FUNDO);
        rodape.setBorder(BorderFactory.createEmptyBorder(0, 40, 16, 40));
        JButton bSair = Tema.botaoSecundario("Sair");
        bSair.addActionListener(e -> frame.dispose());
        rodape.add(bSair);

        frame.add(topo,   BorderLayout.NORTH);
        frame.add(botoes, BorderLayout.CENTER);
        frame.add(rodape, BorderLayout.SOUTH);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
