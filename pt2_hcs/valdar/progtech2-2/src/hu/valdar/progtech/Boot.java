package hu.valdar.progtech;

import hu.valdar.progtech.frontend.MainFrame;

public class Boot {

    public static void main(String[] args){
        java.awt.EventQueue.invokeLater(
                () -> new MainFrame().setVisible(true)
        );
    }
}

