package br.ufjf.dcc.soundcheck.controller;

import javax.swing.JOptionPane;

public class Metodos {
    public static void exibeSucesso(String mensagem){
        JOptionPane.showMessageDialog(null, mensagem, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }
    public static void exibeErro(Exception e){
        JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);    
    }    
    public static void exibeErro(String mensagem, Exception e){
        JOptionPane.showMessageDialog(null, e.getMessage(), mensagem, JOptionPane.ERROR_MESSAGE);    
    }
}
