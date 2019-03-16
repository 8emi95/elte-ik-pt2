/**
 * Created by Csaba_Hete on 2017.04.12..
 */
package hu.elte.progtech.icafe.view.dialog;

import hu.elte.progtech.icafe.view.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public abstract class ICafeEntityEditDialog<T> extends JDialog {

    private final JButton okBtn = new JButton("Mentés");
    private final JButton cancelBtn = new JButton("Mégse");
    T editable;

    ICafeEntityEditDialog(JFrame frame, String title, boolean isModal, final T editable) {
        super(frame, title, isModal);
        if (null == editable) {
            throw new IllegalArgumentException("Editable entity must not be null!");
        }
        this.editable = editable;
        setResizable(false);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                setVisible(false);
            }
        });
        cancelBtn.addActionListener(e -> setVisible(false));
        okBtn.addActionListener(e -> {
            if (inputsAreValid()) {
                setEditableValues();
                onConfirm();
            }
        });

        prepareFields();
        initializeFields();
        constructSelf();
        setLocationRelativeTo(frame);
    }

    /**
     * Metódus az input mezők létrehozásához
     */
    abstract void prepareFields();

    /**
     * Metódus az input mezők esetleges alapértelmezett értékeinek beállításához
     */
    abstract void initializeFields();

    private void constructSelf() {
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(createInputsPanel(), BorderLayout.NORTH);
        cp.add(createControlButtonsPanel(), BorderLayout.SOUTH);
        pack();
    }

    /**
     * Metódus, amely elhelyezi a beiteli mezőket és a címkéiket a felületen
     */
    abstract JPanel createInputsPanel();

    /**
     * Metódus, amely elhelyezi a fő vezérlőgombokat a felületen
     */
    private JPanel createControlButtonsPanel() {
        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new FlowLayout());
        btnPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
        btnPanel.add(cancelBtn);
        btnPanel.add(okBtn);
        return btnPanel;
    }

    protected void displayErrorDialog(String title, String message) {
        MainFrame.showError(title, message);
    }

    boolean inputsAreValid() {
        if (validateInputs()) {
            return true;
        }
        displayErrorDialog("Hiba", "Hiányzó adat, vagy hibás érték került megadásra!\nJavítsa az adatokat, majd próbálja újra.");
        return false;
    }

    protected T getEditable() {
        return editable;
    }

    abstract boolean validateInputs();

    abstract void setEditableValues();

    public abstract void onConfirm();
}
