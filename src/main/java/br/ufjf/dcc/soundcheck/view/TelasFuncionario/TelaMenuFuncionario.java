/*  Rafael Magno Campos da Silva - 202435012
    Gabriel Haddad Cyrino Gadioli - 202435026
    Thomas Adam Chapman - 202476030 */

package br.ufjf.dcc.soundcheck.view.TelasFuncionario;

import br.ufjf.dcc.soundcheck.model.Funcionario;
import javax.swing.*;
import java.awt.*;

public class TelaMenuFuncionario {
    private JFrame frame;
    private JPanel painelPrincipal;
    private JPanel painelEsq;
    private JPanel painelDir;
    private JButton botaoGerenciarClientes;
    private JButton botaoGerenciarReservas;
    private JButton botaoRegistrarSalas;
    private JButton botaoRegistrarEquipamentos;
    private JButton botaoSair;

    public TelaMenuFuncionario() {
        frame                    = new JFrame("Menu - Funcionário");
        painelPrincipal           = new JPanel();
        painelEsq                 = new JPanel();
        painelDir                 = new JPanel();
        botaoGerenciarClientes    = new JButton("Gerenciar Clientes");
        botaoGerenciarReservas    = new JButton("Gerenciar Reservas");
        botaoRegistrarSalas       = new JButton("Registrar Salas");
        botaoRegistrarEquipamentos = new JButton("Registrar Equipamentos");
        botaoSair                 = new JButton("Sair");
    }

    public void abrirTelaMenuFuncionario(Funcionario funcionario) {
        frame.setSize(800, 450);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);

        botaoGerenciarClientes.addActionListener(e -> {
            new TelaCadastroCliente().abrirTelaCadastroCliente();
        });
        botaoGerenciarReservas.addActionListener(e -> {
            new TelaGerenciarReservas().abrirTelaGerenciarReservas();
        });
        botaoRegistrarSalas.addActionListener(e -> {
            new TelaRegistrarSalas().abrirTelaRegistrarSalas();
        });
        botaoRegistrarEquipamentos.addActionListener(e -> {
            new TelaRegistrarEquipamentos().abrirTelaRegistrarEquipamentos();
        });
        botaoSair.addActionListener(e -> frame.dispose());

        painelPrincipal.setLayout(new GridLayout(0, 2, 25, 0));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(45, 35, 45, 35));

        painelEsq.setLayout(new GridLayout(0, 1, 0, 20));
        painelEsq.add(botaoGerenciarClientes);
        painelEsq.add(botaoGerenciarReservas);

        painelDir.setLayout(new GridLayout(0, 1, 0, 20));
        painelDir.add(botaoRegistrarSalas);
        painelDir.add(botaoRegistrarEquipamentos);
        painelDir.add(botaoSair);

        painelPrincipal.add(painelEsq);
        painelPrincipal.add(painelDir);

        frame.add(painelPrincipal);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
