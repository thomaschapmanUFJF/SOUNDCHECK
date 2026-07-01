/*  Rafael Magno Campos da Silva - 202435012
    Gabriel Haddad Cyrino Gadioli - 202435026
    Thomas Adam Chapman - 202476030 */

package br.ufjf.dcc.soundcheck.view;

import br.ufjf.dcc.soundcheck.controller.ClienteController;
import javax.swing.*;
import java.awt.*;

public class TelaAutoRegistro {
    private JFrame frame;

    public TelaAutoRegistro() {
        frame = new JFrame("SoundCheck — Cadastro");
    }

    public void abrirTelaAutoRegistro() {
        Tema.estilizarFrame(frame);
        frame.setSize(480, 760);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel topo = new JPanel(new BorderLayout());
        topo.setBackground(Tema.FUNDO);
        topo.setBorder(BorderFactory.createEmptyBorder(24, 40, 12, 40));
        topo.add(Tema.labelTitulo("Criar Conta"), BorderLayout.CENTER);
        topo.add(Tema.labelSubtitulo("Preencha seus dados para se cadastrar"), BorderLayout.SOUTH);

        String[] labels = {
            "Nome", "CPF", "Telefone", "E-mail", "Senha",
            "Banda", "Estilo Musical",
            "Rua", "Bairro", "CEP", "Cidade", "Estado", "Número", "Complemento"
        };
        JTextField[] campos = new JTextField[labels.length];
        for (int i = 0; i < campos.length; i++) campos[i] = new JTextField();

        JPanel form = new JPanel(new GridLayout(labels.length, 2, 12, 10));
        form.setBackground(Tema.FUNDO);
        form.setBorder(BorderFactory.createEmptyBorder(12, 40, 12, 40));
        for (int i = 0; i < labels.length; i++) {
            JLabel lbl = new JLabel(labels[i]);
            lbl.setFont(Tema.FONTE_LABEL);
            lbl.setForeground(Tema.TEXTO_SECUNDARIO);
            form.add(lbl);
            form.add(campos[i]);
        }

        JButton bCadastrar = Tema.botaoPrimario("Cadastrar");
        JButton bVoltar    = Tema.botaoSecundario("Voltar");
        bCadastrar.setPreferredSize(new Dimension(0, 38));
        bVoltar.setPreferredSize(new Dimension(0, 38));

        bCadastrar.addActionListener(e -> {
            for (int i = 0; i < campos.length - 1; i++) {
                if (campos[i].getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Preencha todos os campos obrigatórios!", "Erro!", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            ClienteController.cadastrarCliente(
                campos[0].getText(), campos[1].getText(), campos[2].getText(),
                campos[3].getText(), campos[4].getText(),
                campos[7].getText(), campos[8].getText(), campos[9].getText(),
                campos[10].getText(), campos[11].getText(), campos[12].getText(),
                campos[13].getText(), campos[5].getText(), campos[6].getText());
            frame.dispose();
        });

        bVoltar.addActionListener(e -> frame.dispose());

        JPanel rodape = new JPanel(new GridLayout(1, 2, 12, 0));
        rodape.setBackground(Tema.FUNDO);
        rodape.setBorder(BorderFactory.createEmptyBorder(8, 40, 20, 40));
        rodape.add(bVoltar);
        rodape.add(bCadastrar);

        frame.add(topo,   BorderLayout.NORTH);
        frame.add(form,   BorderLayout.CENTER);
        frame.add(rodape, BorderLayout.SOUTH);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
