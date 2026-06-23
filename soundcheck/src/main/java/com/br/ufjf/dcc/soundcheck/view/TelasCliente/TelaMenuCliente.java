/*  Rafael Magno Campos da Silva - 202435012
    Gabriel Haddad Cyrino Gadioli - 202435026
    Thomas Adam Chapman - 202476030 */

package br.ufjf.dcc.soundcheck.view.TelasCliente;

import br.ufjf.dcc.soundcheck.model.Cliente;
import javax.swing.*;
import java.awt.*;

public class TelaMenuCliente {
    private JFrame frame;
    private JPanel painelPrincipal;
    private JPanel painelEsq;
    private JPanel painelDir;
    private JButton botaoReservas;
    private JButton botaoDadosPessoais;
    private JButton botaoSair;

    public TelaMenuCliente() {
        frame           = new JFrame("Menu - Cliente");
        painelPrincipal  = new JPanel();
        painelEsq        = new JPanel();
        painelDir        = new JPanel();
        botaoReservas    = new JButton("Minhas Reservas");
        botaoDadosPessoais = new JButton("Meus Dados");
        botaoSair        = new JButton("Sair");
    }

    public void abrirTelaMenuCliente(Cliente cliente) {
        frame.setSize(800, 350);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);

        botaoReservas.addActionListener(e -> {
            new TelaReserva().abrirTelaReserva(cliente);
        });
        botaoDadosPessoais.addActionListener(e -> {
            new TelaDadosPessoaisCliente().abrirTelaDadosPessoaisCliente(cliente);
        });
        botaoSair.addActionListener(e -> frame.dispose());

        painelPrincipal.setLayout(new GridLayout(0, 2, 25, 0));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(45, 35, 45, 35));

        painelEsq.setLayout(new GridLayout(0, 1, 0, 25));
        painelEsq.add(botaoReservas);

        painelDir.setLayout(new GridLayout(0, 1, 0, 25));
        painelDir.add(botaoDadosPessoais);
        painelDir.add(botaoSair);

        painelPrincipal.add(painelEsq);
        painelPrincipal.add(painelDir);

        frame.add(painelPrincipal);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
