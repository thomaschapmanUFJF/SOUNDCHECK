/*  Rafael Magno Campos da Silva - 202435012
    Gabriel Haddad Cyrino Gadioli - 202435026
    Thomas Adam Chapman - 202476030 */

package br.ufjf.dcc.soundcheck.view;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.metal.MetalSliderUI;
import java.awt.*;

public class Tema {

    public static final Color FUNDO          = new Color(18, 18, 18);
    public static final Color FUNDO_PAINEL   = new Color(28, 28, 28);
    public static final Color FUNDO_CAMPO    = new Color(40, 40, 40);
    public static final Color ACENTO         = new Color(168, 85, 247);
    public static final Color ACENTO_HOVER   = new Color(139, 60, 210);
    public static final Color TEXTO          = new Color(240, 240, 240);
    public static final Color TEXTO_SECUNDARIO = new Color(160, 160, 160);
    public static final Color BORDA          = new Color(60, 60, 60);
    public static final Color SUCESSO        = new Color(74, 222, 128);
    public static final Color ERRO           = new Color(248, 113, 113);

    public static final Font FONTE_TITULO    = new Font("Segoe UI", Font.BOLD, 28);
    public static final Font FONTE_SUBTITULO = new Font("Segoe UI", Font.BOLD, 13);
    public static final Font FONTE_LABEL     = new Font("Segoe UI", Font.PLAIN, 13);
    public static final Font FONTE_CAMPO     = new Font("Segoe UI", Font.PLAIN, 13);
    public static final Font FONTE_BOTAO     = new Font("Segoe UI", Font.BOLD, 13);
    public static void aplicarTema() {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        UIManager.put("Panel.background", FUNDO_PAINEL);
        UIManager.put("OptionPane.background", FUNDO_PAINEL);
        UIManager.put("OptionPane.messageForeground", TEXTO);
        
        UIManager.put("Label.foreground", TEXTO);
        UIManager.put("Label.font", FONTE_LABEL);
        
        UIManager.put("TextField.background", FUNDO_CAMPO);
        UIManager.put("TextField.foreground", TEXTO);
        UIManager.put("TextField.caretForeground", TEXTO);
        UIManager.put("TextField.font", FONTE_CAMPO);
        UIManager.put("TextField.border", BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDA, 1),
            BorderFactory.createEmptyBorder(4, 8, 4, 8)));
        
        UIManager.put("PasswordField.background", FUNDO_CAMPO);
        UIManager.put("PasswordField.foreground", TEXTO);
        UIManager.put("PasswordField.caretForeground", TEXTO);
        UIManager.put("PasswordField.border", BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDA, 1),
            BorderFactory.createEmptyBorder(4, 8, 4, 8)));
        
        UIManager.put("ComboBox.background", FUNDO_CAMPO);
        UIManager.put("ComboBox.foreground", TEXTO);
        UIManager.put("ComboBox.font", FONTE_CAMPO);
        UIManager.put("ComboBox.selectionBackground", ACENTO);
        UIManager.put("ComboBox.selectionForeground", Color.WHITE);
        
        UIManager.put("List.background", FUNDO_CAMPO);
        UIManager.put("List.foreground", TEXTO);
        UIManager.put("List.selectionBackground", ACENTO);
        UIManager.put("List.selectionForeground", Color.WHITE);
        UIManager.put("List.font", FONTE_CAMPO);
        
        UIManager.put("ScrollPane.background", FUNDO_CAMPO);
        UIManager.put("ScrollPane.viewportBackground", FUNDO_CAMPO);
        UIManager.put("ScrollBar.background", FUNDO_PAINEL);
        UIManager.put("ScrollBar.thumb", BORDA);
        UIManager.put("ScrollBar.thumbShadow", BORDA);
        UIManager.put("ScrollBar.thumbDarkShadow", BORDA);
        
        UIManager.put("CheckBox.background", FUNDO_PAINEL);
        UIManager.put("CheckBox.foreground", TEXTO);
        UIManager.put("CheckBox.font", FONTE_LABEL);
        
        UIManager.put("RadioButton.background", FUNDO_PAINEL);
        UIManager.put("RadioButton.foreground", TEXTO);
        UIManager.put("RadioButton.font", FONTE_LABEL);
        
        UIManager.put("Spinner.background", FUNDO_CAMPO);
        UIManager.put("Spinner.foreground", TEXTO);
        UIManager.put("Spinner.font", FONTE_CAMPO);
        
        UIManager.put("Button.background", ACENTO);
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("Button.font", FONTE_BOTAO);
        UIManager.put("Button.focus", ACENTO);
        UIManager.put("Button.select", ACENTO_HOVER);
        
        UIManager.put("TitledBorder.titleColor", TEXTO);
        UIManager.put("TitledBorder.font", FONTE_SUBTITULO);
        
        UIManager.put("Slider.background", FUNDO_PAINEL);
        UIManager.put("Slider.foreground", ACENTO);
        UIManager.put("Slider.tickColor", TEXTO_SECUNDARIO);
        UIManager.put("Slider.trackColor", FUNDO_CAMPO);
        UIManager.put("Slider.thumbColor", ACENTO);
        UIManager.put("Slider.thumbDarkShadowColor", ACENTO_HOVER);
        UIManager.put("Slider.thumbShadowColor", ACENTO_HOVER);
        UIManager.put("Slider.focusColor", ACENTO);
        UIManager.put("Slider.highlight", FUNDO_CAMPO);
        UIManager.put("Slider.shadow", BORDA);
        UIManager.put("Slider.focusInsets", new Insets(0, 0, 0, 0));

        UIManager.put("Slider.focus", new Color(0, 0, 0, 0));
        UIManager.put("ProgressBar.background", FUNDO_CAMPO);
        UIManager.put("ProgressBar.foreground", ACENTO);
        
        UIManager.put("TabbedPane.background", FUNDO_PAINEL);
        UIManager.put("TabbedPane.foreground", TEXTO);
        UIManager.put("TabbedPane.selected", ACENTO);
        
        UIManager.put("Table.background", FUNDO_CAMPO);
        UIManager.put("Table.foreground", TEXTO);
        UIManager.put("Table.selectionBackground", ACENTO);
        UIManager.put("Table.selectionForeground", Color.WHITE);
        UIManager.put("Table.gridColor", BORDA);
        
        UIManager.put("ToolTip.background", FUNDO_PAINEL);
        UIManager.put("ToolTip.foreground", TEXTO);
        UIManager.put("ToolTip.border", BorderFactory.createLineBorder(BORDA, 1));
        
        UIManager.put("Menu.background", FUNDO_PAINEL);
        UIManager.put("Menu.foreground", TEXTO);
        UIManager.put("MenuItem.background", FUNDO_PAINEL);
        UIManager.put("MenuItem.foreground", TEXTO);
        UIManager.put("MenuItem.selectionBackground", ACENTO);
        UIManager.put("MenuItem.selectionForeground", Color.WHITE);
        
        UIManager.put("Button.focus", new ColorUIResource(ACENTO));
        UIManager.put("ToggleButton.focus", new ColorUIResource(ACENTO));
        UIManager.put("CheckBox.focus", new ColorUIResource(ACENTO));
        UIManager.put("RadioButton.focus", new ColorUIResource(ACENTO));
        UIManager.put("Slider.focus", new ColorUIResource(ACENTO));
        UIManager.put("ComboBox.focus", new ColorUIResource(ACENTO));

        UIManager.put("Spinner.focus", new Color(0, 0, 0, 0));

        UIManager.put("ComboBox.focus", new Color(0, 0, 0, 0));

        UIManager.put("List.focusCellHighlightBorder", BorderFactory.createEmptyBorder());
        UIManager.put("List.focusSelectedCellHighlightBorder", BorderFactory.createEmptyBorder());

        UIManager.put("Slider.focus", new Color(0, 0, 0, 0));
        UIManager.put("Slider.focusInsets", new Insets(0, 0, 0, 0));

        UIManager.put("List.focusCellHighlightBorder", BorderFactory.createEmptyBorder());
        UIManager.put("List.focusCellHighlightColor", new ColorUIResource(ACENTO));
        UIManager.put("Table.focusCellHighlightBorder", BorderFactory.createEmptyBorder());
        UIManager.put("Table.focusCellHighlightColor", new ColorUIResource(ACENTO));
        UIManager.put("Tree.focusCellHighlightBorder", BorderFactory.createEmptyBorder());
        UIManager.put("Tree.focusCellHighlightColor", new ColorUIResource(ACENTO));
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
    public static JSlider criarSlider(int min, int max, int value) {
        JSlider slider = new JSlider(min, max, value) {
            @Override
            public void updateUI() {
                setUI(new DarkSliderUI(this));
            }
        };
        
        slider.setBackground(FUNDO_PAINEL);
        slider.setForeground(ACENTO);
        slider.setFont(FONTE_LABEL);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setMajorTickSpacing((max - min) / 5);
        slider.setMinorTickSpacing((max - min) / 10);
        slider.setOpaque(true);
        
        slider.setFocusable(false);
        
        return slider;
    }

    private static class DarkSliderUI extends MetalSliderUI {
        public DarkSliderUI(JSlider slider) {
            super();
        }

        @Override
        public void paintTrack(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                                RenderingHints.VALUE_ANTIALIAS_ON);
            
            Rectangle trackBounds = trackRect;
            int trackHeight = 6;
            int trackY = trackBounds.y + (trackBounds.height - trackHeight) / 2;
            
            g2d.setColor(FUNDO_CAMPO);
            g2d.fillRoundRect(trackBounds.x, trackY, trackBounds.width, trackHeight, 3, 3);
            
            JSlider slider = (JSlider) this.slider;
            if (slider.getValue() > slider.getMinimum()) {
                int fillWidth = (int) ((double) (slider.getValue() - slider.getMinimum()) / 
                                       (slider.getMaximum() - slider.getMinimum()) * trackBounds.width);
                g2d.setColor(ACENTO);
                g2d.fillRoundRect(trackBounds.x, trackY, fillWidth, trackHeight, 3, 3);
            }
        }

        @Override
        public void paintThumb(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                                RenderingHints.VALUE_ANTIALIAS_ON);
            
            Rectangle thumbBounds = thumbRect;
            int size = 16;
            int x = thumbBounds.x + (thumbBounds.width - size) / 2;
            int y = thumbBounds.y + (thumbBounds.height - size) / 2;
            
            g2d.setColor(new Color(0, 0, 0, 50));
            g2d.fillOval(x + 2, y + 2, size, size);
            
            g2d.setColor(ACENTO);
            g2d.fillOval(x, y, size, size);
            
            g2d.setColor(new Color(255, 255, 255, 80));
            g2d.fillOval(x + 3, y + 3, size / 3, size / 3);
            
            g2d.setColor(ACENTO_HOVER);
            g2d.drawOval(x, y, size, size);
        }
    }

    public static void removerBordaFoco(JComponent comp) {
        comp.setFocusable(false);
        if (comp instanceof JButton) {
            ((JButton) comp).setFocusPainted(false);
        } else if (comp instanceof JList) {
            ((JList<?>) comp).setFocusable(false);
        } else if (comp instanceof JTable) {
            ((JTable) comp).setFocusable(false);
        } else if (comp instanceof JComboBox) {
            ((JComboBox<?>) comp).setFocusable(false);
        }
    }

    public static void aplicarTemaRecursivamente(Container container) {
        for (Component comp : container.getComponents()) {
            if (comp instanceof JComponent) {
                JComponent jcomp = (JComponent) comp;
                
                jcomp.setFocusable(false);
                
                if (comp instanceof JPanel) {
                    jcomp.setBackground(FUNDO_PAINEL);
                } else if (comp instanceof JLabel) {
                    jcomp.setForeground(TEXTO);
                } else if (comp instanceof JList) {
                    JList<?> list = (JList<?>) comp;
                    list.setBackground(FUNDO_CAMPO);
                    list.setForeground(TEXTO);
                    list.setSelectionBackground(ACENTO);
                    list.setSelectionForeground(Color.WHITE);
                    list.setFocusable(false);
                } else if (comp instanceof JTable) {
                    JTable table = (JTable) comp;
                    table.setBackground(FUNDO_CAMPO);
                    table.setForeground(TEXTO);
                    table.setSelectionBackground(ACENTO);
                    table.setSelectionForeground(Color.WHITE);
                    table.setFocusable(false);
                } else if (comp instanceof JComboBox) {
                    JComboBox<?> combo = (JComboBox<?>) comp;
                    combo.setBackground(FUNDO_CAMPO);
                    combo.setForeground(TEXTO);
                    combo.setFocusable(false);
                } else if (comp instanceof JTextField) {
                    JTextField field = (JTextField) comp;
                    field.setBackground(FUNDO_CAMPO);
                    field.setForeground(TEXTO);
                    field.setCaretColor(TEXTO);
                } else if (comp instanceof JPasswordField) {
                    JPasswordField field = (JPasswordField) comp;
                    field.setBackground(FUNDO_CAMPO);
                    field.setForeground(TEXTO);
                    field.setCaretColor(TEXTO);
                } else if (comp instanceof JSpinner) {
                    JSpinner spinner = (JSpinner) comp;
                    spinner.setBackground(FUNDO_CAMPO);
                    spinner.setForeground(TEXTO);
                } else if (comp instanceof JCheckBox) {
                    JCheckBox check = (JCheckBox) comp;
                    check.setBackground(FUNDO_PAINEL);
                    check.setForeground(TEXTO);
                } else if (comp instanceof JRadioButton) {
                    JRadioButton radio = (JRadioButton) comp;
                    radio.setBackground(FUNDO_PAINEL);
                    radio.setForeground(TEXTO);
                } else if (comp instanceof JScrollPane) {
                    JScrollPane scroll = (JScrollPane) comp;
                    scroll.setBackground(FUNDO_CAMPO);
                    scroll.getViewport().setBackground(FUNDO_CAMPO);
                }
            }
            
            if (comp instanceof Container) {
                aplicarTemaRecursivamente((Container) comp);
            }
        }
    }

    public static void estilizarFrame(JFrame frame) {
        frame.getContentPane().setBackground(FUNDO);
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