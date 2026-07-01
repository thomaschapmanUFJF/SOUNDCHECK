/*  Rafael Magno Campos da Silva - 202435012
    Gabriel Haddad Cyrino Gadioli - 202435026
    Thomas Adam Chapman - 202476030 */

package br.ufjf.dcc.soundcheck.view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Tema {

    public static final Color FUNDO          = new Color(18, 18, 18);
    public static final Color FUNDO_PAINEL   = new Color(28, 28, 28);
    public static final Color FUNDO_CAMPO    = new Color(40, 40, 40);
    public static final Color ACENTO         = new Color(168, 85, 247);   // roxo
    public static final Color ACENTO_HOVER   = new Color(139, 60, 210);
    public static final Color TEXTO          = new Color(240, 240, 240);
    public static final Color TEXTO_SECUNDARIO = new Color(160, 160, 160);
    public static final Color BORDA          = new Color(60, 60, 60);
    public static final Color SUCESSO        = new Color(74, 222, 128);
    public static final Color ERRO          = new Color(248, 113, 113);

    public static final Font FONTE_TITULO    = new Font("Segoe UI", Font.BOLD, 28);
    public static final Font FONTE_SUBTITULO = new Font("Segoe UI", Font.BOLD, 13);
    public static final Font FONTE_LABEL     = new Font("Segoe UI", Font.PLAIN, 13);
    public static final Font FONTE_CAMPO     = new Font("Segoe UI", Font.PLAIN, 13);
    public static final Font FONTE_BOTAO     = new Font("Segoe UI", Font.BOLD, 13);

    public static void aplicarTema() {
        UIManager.put("Panel.background",          FUNDO_PAINEL);
        UIManager.put("Label.foreground",          TEXTO);
        UIManager.put("Label.font",                FONTE_LABEL);
        UIManager.put("TextField.background",      FUNDO_CAMPO);
        UIManager.put("TextField.foreground",      TEXTO);
        UIManager.put("TextField.caretForeground", TEXTO);
        UIManager.put("TextField.border",          BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDA, 1),
            BorderFactory.createEmptyBorder(4, 8, 4, 8)));
        UIManager.put("TextField.font",            FONTE_CAMPO);
        UIManager.put("PasswordField.background",  FUNDO_CAMPO);
        UIManager.put("PasswordField.foreground",  TEXTO);
        UIManager.put("PasswordField.caretForeground", TEXTO);
        UIManager.put("PasswordField.border",      BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDA, 1),
            BorderFactory.createEmptyBorder(4, 8, 4, 8)));
        UIManager.put("ComboBox.background",       FUNDO_CAMPO);
        UIManager.put("ComboBox.foreground",       TEXTO);
        UIManager.put("ComboBox.font",             FONTE_CAMPO);
        UIManager.put("List.background",           FUNDO_CAMPO);
        UIManager.put("List.foreground",           TEXTO);
        UIManager.put("List.selectionBackground",  ACENTO);
        UIManager.put("List.selectionForeground",  Color.WHITE);
        UIManager.put("List.font",                 FONTE_CAMPO);
        UIManager.put("ScrollPane.background",     FUNDO_CAMPO);
        UIManager.put("ScrollBar.background",      FUNDO_PAINEL);
        UIManager.put("ScrollBar.thumb",           BORDA);
        UIManager.put("CheckBox.background",       FUNDO_PAINEL);
        UIManager.put("CheckBox.foreground",       TEXTO);
        UIManager.put("Spinner.background",        FUNDO_CAMPO);
        UIManager.put("Spinner.foreground",        TEXTO);
        UIManager.put("OptionPane.background",     FUNDO_PAINEL);
        UIManager.put("OptionPane.messageForeground", TEXTO);
    }

    public static JButton botaoPrimario(String texto) {
        JButton b = new JButton(texto);
        b.setFont(FONTE_BOTAO);
        b.setBackground(ACENTO);
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setBorderPainted(false);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) { b.setBackground(ACENTO_HOVER); }
            public void mouseExited(java.awt.event.MouseEvent e)  { b.setBackground(ACENTO); }
        });
        return b;
    }

    public static JButton botaoSecundario(String texto) {
        JButton b = new JButton(texto);
        b.setFont(FONTE_BOTAO);
        b.setBackground(FUNDO_CAMPO);
        b.setForeground(TEXTO_SECUNDARIO);
        b.setFocusPainted(false);
        b.setBorderPainted(false);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) { b.setBackground(BORDA); }
            public void mouseExited(java.awt.event.MouseEvent e)  { b.setBackground(FUNDO_CAMPO); }
        });
        return b;
    }

    public static void estilizarFrame(JFrame frame) {
        frame.getContentPane().setBackground(FUNDO);
    }

    public static Border bordaPainel() {
        return BorderFactory.createLineBorder(BORDA, 1);
    }

    public static JLabel labelTitulo(String texto) {
        JLabel l = new JLabel(texto, SwingConstants.CENTER);
        l.setFont(FONTE_TITULO);
        l.setForeground(ACENTO);
        return l;
    }

    public static JLabel labelSubtitulo(String texto) {
        JLabel l = new JLabel(texto, SwingConstants.CENTER);
        l.setFont(FONTE_SUBTITULO);
        l.setForeground(TEXTO_SECUNDARIO);
        return l;
    }
}
