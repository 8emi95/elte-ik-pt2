package hu.valdar.progtech.frontend;

import hu.elte.DateCellEditor;
import hu.valdar.progtech.backend.db.DataSource;
import hu.valdar.progtech.backend.db.connection.DBConnectionSource;
import hu.valdar.progtech.frontend.model.combobox.EntityComboBoxModel;
import hu.valdar.progtech.frontend.model.table.AbstractEntityTableModel;
import hu.valdar.progtech.frontend.model.table.ArtistTableModel;
import hu.valdar.progtech.frontend.model.table.BandArtistTableModel;
import hu.valdar.progtech.frontend.model.table.BandTableModel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

public class MainFrame extends JFrame{

    private final JTable artistTable;
    private final JTable bandTable;
    private final JTable bandArtistTable;

    private final ArtistTableModel artistTableModel;
    private final BandTableModel bandTableModel;
    private final BandArtistTableModel bandArtistTableModel;

    private final JTabbedPane tabbedPane;
    private final BandsMenuBar bandsMenuBar;

    public MainFrame(){
        try {
            DBConnectionSource.getInstance().getConnection().close();
        } catch (SQLException ex) {
            showError(ex.getMessage());
            System.exit(1);
        }
        applyNimbusLookAndFeelTheme();
        setFrameProperties();

        this.bandsMenuBar = new BandsMenuBar();
        tabbedPane = new JTabbedPane();

        artistTableModel = new ArtistTableModel();
        artistTable = new JTable(artistTableModel);
        artistTable.setAutoCreateRowSorter(true);
        artistTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabbedPane.addTab("Artists", new JScrollPane(artistTable));

        bandTableModel = new BandTableModel();
        bandTable = new JTable(bandTableModel);
        bandTable.setAutoCreateRowSorter(true);
        bandTable.getColumnModel().getColumn(1).setCellEditor(new DateCellEditor());
        bandTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabbedPane.addTab("Bands", new JScrollPane(bandTable));

        bandArtistTableModel = new BandArtistTableModel();
        bandArtistTable = new JTable(bandArtistTableModel);
        bandArtistTable.setAutoCreateRowSorter(true);
        bandArtistTable.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(new JComboBox<>(new EntityComboBoxModel<>(DataSource.getInstance().getArtistDao()))));
        bandArtistTable.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(new JComboBox<>(new EntityComboBoxModel<>(DataSource.getInstance().getBandDao()))));
        bandArtistTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabbedPane.addTab("Band-Artist connection", new JScrollPane(bandArtistTable));

        getContentPane().add(tabbedPane, BorderLayout.CENTER);
        tabbedPane.addChangeListener(bandsMenuBar);

        setJMenuBar(bandsMenuBar);

        pack();
    }

    private void deleteRowsFromTable(JTable table, AbstractEntityTableModel tableModel) {
        int[] selectedRows = table.getSelectedRows();
        ArrayList<Integer> rowIndicesList = new ArrayList<>(selectedRows.length);
        for (int selectedRowIdx : selectedRows) {
            rowIndicesList.add(table.convertRowIndexToModel(selectedRowIdx));
        }
        Collections.sort(rowIndicesList);
        Collections.reverse(rowIndicesList);
        for (Integer rowIndex : rowIndicesList) {
            tableModel.deleteEntity(rowIndex);
        }
    }

    /**
     * Nimbus téma beállítása.
     */
    private void applyNimbusLookAndFeelTheme(){
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ignored) {
        }
    }

    private void setFrameProperties() {
        setTitle("Bands Swing Demo");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
    }

    public static void showError(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private final Action addArtistAction = new AbstractAction("Add new artist") {

        @Override
        public void actionPerformed(ActionEvent e) {
            artistTableModel.addNewEntity();
        }
    };

    private final Action addBandAction = new AbstractAction("Add new band") {

        @Override
        public void actionPerformed(ActionEvent e) {
            bandTableModel.addNewEntity();
        }
    };

    private final Action addBandArtistAction = new AbstractAction("Add new band-artist connection") {

        @Override
        public void actionPerformed(ActionEvent e) {
            bandArtistTableModel.addNewEntity();
        }
    };

    private final Action deleteArtistAction = new AbstractAction("Delete selected artist") {

        @Override
        public void actionPerformed(ActionEvent e) {
            deleteRowsFromTable(artistTable, artistTableModel);
        }
    };

    private final Action deleteBandAction = new AbstractAction("Delete selected band") {

        @Override
        public void actionPerformed(ActionEvent e) {
            deleteRowsFromTable(bandTable, bandTableModel);
        }
    };

    private final Action deleteBandArtistAction = new AbstractAction("Delete selected band-artist connection") {

        @Override
        public void actionPerformed(ActionEvent e) {
            deleteRowsFromTable(bandArtistTable, bandArtistTableModel);
        }
    };

    private class BandsMenuBar extends JMenuBar implements ChangeListener {

        private final JMenu artistMenu, bandMenu, bandArtistMenu;

        public BandsMenuBar() {
            artistMenu = new JMenu("Artists");
            bandMenu = new JMenu("Bands");
            bandArtistMenu = new JMenu("Band-Artist connection");

            add(artistMenu);

            artistMenu.add(addArtistAction);
            artistMenu.add(deleteArtistAction);
            bandMenu.add(addBandAction);
            bandMenu.add(deleteBandAction);
            bandArtistMenu.add(addBandArtistAction);
            bandArtistMenu.add(deleteBandArtistAction);
        }

        @Override
        public void stateChanged(ChangeEvent e) {
            removeAll();
            repaint();
            switch (tabbedPane.getTitleAt(tabbedPane.getSelectedIndex())) {
                case "Artists":
                    add(artistMenu);
                    break;
                case "Bands":
                    add(bandMenu);
                    break;
                case "Band-Artist connection":
                    add(bandArtistMenu);
                    break;
            }
        }
    }
}
