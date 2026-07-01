/*  Rafael Magno Campos da Silva - 202435012
    Gabriel Haddad Cyrino Gadioli - 202435026
    Thomas Adam Chapman - 202476030 */

package br.ufjf.dcc.soundcheck.view.TelasGerente;

import br.ufjf.dcc.soundcheck.model.Gerente;
import br.ufjf.dcc.soundcheck.view.Tema;
import javax.swing.*;
import java.awt.*;

public class TelaMenuGerente {
    private JFrame frame;

    public TelaMenuGerente() {
        frame = new JFrame("SoundCheck — Gerente");
    }

    public void abrirTelaMenuGerente(Gerente gerente) {
        Tema.estilizarFrame(frame);
        frame.setSize(500, 340);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());

        JPanel topo = new JPanel(new BorderLayout());
        topo.setBackground(Tema.FUNDO);
        topo.setBorder(BorderFactory.createEmptyBorder(28, 40, 16, 40));
        JLabel titulo = Tema.labelTitulo("SoundCheck");
        JLabel sub    = Tema.labelSubtitulo("Gerente · " + gerente.getNome().split(" ")[0]);
        topo.add(titulo, BorderLayout.CENTER);
        topo.add(sub,    BorderLayout.SOUTH);

        JPanel botoes = new JPanel(new GridLayout(1, 1, 0, 0));
        botoes.setBackground(Tema.FUNDO);
        botoes.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));

        JButton bFuncionarios = Tema.botaoPrimario("👥  Gerenciar Funcionários");
        bFuncionarios.setFont(new Font("Segoe UI", Font.BOLD, 15));
        bFuncionarios.setPreferredSize(new Dimension(0, 52));
        bFuncionarios.addActionListener(e -> new TelaCadastroFuncionario().abrirTelaCadastroFuncionario());

        botoes.add(bFuncionarios);

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
