/*  Rafael Magno Campos da Silva - 202435012
    Gabriel Haddad Cyrino Gadioli - 202435026
    Thomas Adam Chapman - 202476030 */

package br.ufjf.dcc.soundcheck.view.TelasCliente;

import br.ufjf.dcc.soundcheck.model.Cliente;
import br.ufjf.dcc.soundcheck.view.Tema;
import javax.swing.*;
import java.awt.*;

public class TelaMenuCliente {
    private JFrame frame;

    public TelaMenuCliente() {
        frame = new JFrame("SoundCheck — Cliente");
    }

    public void abrirTelaMenuCliente(Cliente cliente) {
        Tema.estilizarFrame(frame);
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());

        JPanel topo = new JPanel(new BorderLayout());
        topo.setBackground(Tema.FUNDO);
        topo.setBorder(BorderFactory.createEmptyBorder(28, 40, 16, 40));
        JLabel titulo = Tema.labelTitulo("SoundCheck");
        JLabel sub    = Tema.labelSubtitulo("Olá, " + cliente.getNome().split(" ")[0] + " · " + cliente.getBanda());
        topo.add(titulo,    BorderLayout.CENTER);
        topo.add(sub,       BorderLayout.SOUTH);

        JPanel botoes = new JPanel(new GridLayout(2, 1, 0, 14));
        botoes.setBackground(Tema.FUNDO);
        botoes.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));

        JButton bReservas = Tema.botaoPrimario("🎸  Minhas Reservas");
        JButton bDados    = Tema.botaoSecundario("👤  Meus Dados");
        bReservas.setFont(new Font("Segoe UI", Font.BOLD, 15));
        bDados.setFont(new Font("Segoe UI", Font.BOLD, 15));
        bReservas.setPreferredSize(new Dimension(0, 52));
        bDados.setPreferredSize(new Dimension(0, 52));

        bReservas.addActionListener(e -> new TelaReserva().abrirTelaReserva(cliente));
        bDados.addActionListener(e    -> new TelaDadosPessoaisCliente().abrirTelaDadosPessoaisCliente(cliente));

        botoes.add(bReservas);
        botoes.add(bDados);

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
