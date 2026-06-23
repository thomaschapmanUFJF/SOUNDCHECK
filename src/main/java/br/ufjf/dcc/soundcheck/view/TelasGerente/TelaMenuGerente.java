/*  Rafael Magno Campos da Silva - 202435012
    Gabriel Haddad Cyrino Gadioli - 202435026
    Thomas Adam Chapman - 202476030 */

package br.ufjf.dcc.soundcheck.view.TelasGerente;

import br.ufjf.dcc.soundcheck.model.Gerente;
import javax.swing.*;
import java.awt.*;

public class TelaMenuGerente {
    private JFrame frame;
    private JPanel painelPrincipal;
    private JPanel painelEsq;
    private JPanel painelDir;
    private JButton botaoGerenciarFuncionarios;
    private JButton botaoSair;

    public TelaMenuGerente() {
        frame                      = new JFrame("Menu - Gerente");
        painelPrincipal             = new JPanel();
        painelEsq                   = new JPanel();
        painelDir                   = new JPanel();
        botaoGerenciarFuncionarios  = new JButton("Gerenciar Funcionários");
        botaoSair                   = new JButton("Sair");
    }

    public void abrirTelaMenuGerente(Gerente gerente) {
        frame.setSize(800, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);

        botaoGerenciarFuncionarios.addActionListener(e -> {
            new TelaCadastroFuncionario().abrirTelaCadastroFuncionario();
        });
        botaoSair.addActionListener(e -> frame.dispose());

        painelPrincipal.setLayout(new GridLayout(0, 2, 25, 0));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(45, 35, 45, 35));

        painelEsq.setLayout(new GridLayout(0, 1, 0, 25));
        painelEsq.add(botaoGerenciarFuncionarios);

        painelDir.setLayout(new GridLayout(0, 1, 0, 25));
        painelDir.add(botaoSair);

        painelPrincipal.add(painelEsq);
        painelPrincipal.add(painelDir);

        frame.add(painelPrincipal);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
