/**
 * Created by Csaba_Hete on 2017.04.13..
 */
package hu.elte.progtech.icafe.view.listener;

import hu.elte.progtech.icafe.api.entity.Computer;
import hu.elte.progtech.icafe.api.service.ComputerService;
import hu.elte.progtech.icafe.view.dialog.EditComputerDialog;
import hu.elte.progtech.icafe.view.table.ComputerTable;
import hu.elte.progtech.icafe.view.table.model.ComputersTableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditComputerListener implements ActionListener {

    private ComputerTable table;

    public EditComputerListener(ComputerTable table) {
        this.table = table;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ComputersTableModel tableModel = (ComputersTableModel) table.getModel();
        Computer computer = tableModel.getEntityAtRow(table.convertRowIndexToModel(table.getSelectedRow()));
        if (null != computer) {
            JDialog inputDialog = new EditComputerDialog(null, "Számítógép szerkesztése", true, computer) {

                @Override
                public void onConfirm() {
                    try {
                        new ComputerService().updateEntity(getEditable(), getEditable().getId());
                        setVisible(false);
                        tableModel.reloadEntities();
                    } catch (Exception e) {
                        displayErrorDialog("HIBA", "A számítógép adatainak mentése során hiba történt! \n" + e.getMessage());
                    }
                }
            };
            inputDialog.setVisible(true);
        }
    }
}
