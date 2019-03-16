/**
 * Created by Csaba_Hete on 2017.03.21..
 */
package hu.elte.progtech.icafe.view.menu;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Absztrakt osztály, melynek segítségével bármilyen {@link java.awt.Component} objektumra kattintva
 * helyi menüt jeleníthetünk meg.
 */
abstract class AbstractContextMenu extends JPopupMenu {
    private static Font FONT = new Font("Garamond", Font.PLAIN, 16);
    @Getter
    private Component parentElement;

    AbstractContextMenu(Component parentElement) {
        this.parentElement = parentElement;
    }

    /**
     * A metódus segíségével új menüpontot hozhatunk létre a helyi menühöz.
     * @param label a menüpont felirata
     * @param actionListener a menüpont kattintási eseményét feldolgozó {@link java.awt.event.ActionListener}
     * @return {@link javax.swing.JMenuItem}
     */
    JMenuItem createMenuItem(String label, ActionListener actionListener) {
        JMenuItem jMenuItem = new JMenuItem(label);
        jMenuItem.setFont(FONT);
        jMenuItem.addActionListener(actionListener);
        return jMenuItem;
    }
}
