/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movies.gui.tables;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;
import movies.database.MovieService;
import movies.database.RentalService;
import movies.entities.Rental;
import movies.gui.utils.GlobalMenuBar;
import movies.gui.utils.JTableButtonMouseListener;
import movies.gui.utils.JTableButtonRenderer;

/**
 *
 * @author Emese
 */
public class RentalTableFrame extends JFrame {
    private final RentalService rentalService = new RentalService();
    private final MovieService movieService = new MovieService();

    public List<Rental> rentals = new ArrayList<>();
    private JScrollPane jsp;
    private JTable table;
    private TableRowSorter sorter;
    private JTextField filt = new JTextField(30);

    private final SwingWorker<Void, Void> PAGE_REFRESH_WORKER = new SwingWorker<Void, Void>() {
        @Override
        protected Void doInBackground() throws Exception {
            while (true) {
                reload();
                Thread.sleep(1000);
            }
        }
    };

    public RentalTableFrame() {
        super("Rental");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.table = rentalTable();
        setJMenuBar(GlobalMenuBar.createMenu(this));
        setLayout(new BorderLayout());
        table = rentalTable();
        jsp = new JScrollPane(table);
        add(jsp, BorderLayout.EAST);
        JPanel filter = new JPanel();
        //setPreferredSize(new Dimension(800, 700));
        //table.setPreferredScrollableViewportSize(table.getPreferredSize());

        filt.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {

            }

            @Override
            public void removeUpdate(DocumentEvent e) {

            }

            @Override
            public void insertUpdate(DocumentEvent e) {

            }
        });
        //filter.add(new JLabel("Filter: "));
        filter.add(filt);
        // add(filter, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        PAGE_REFRESH_WORKER.execute();
    }

    private JTable rentalTable() {
        AbstractTableModel model = new AbstractTableModel() {
            private final String[] tabs = { "ID", "Movie ID", "Name", "Rent Date", "Return Date", "Return" };

            @Override
            public String getColumnName(int column) {
                return tabs[column];
            }

            @Override
            public int getRowCount() {
                return rentals.size();
            }

            @Override
            public int getColumnCount() {
                return tabs.length;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                switch (columnIndex) {
                case 0:
                    return rentals.get(rowIndex).getID();
                case 1:
                    // return rentals.get(rowIndex).getMovieID();
                    return movieService.findMovieByID(rentals.get(rowIndex).getMovieID());
                case 2:
                    return rentals.get(rowIndex).getName();
                case 3:
                    return rentals.get(rowIndex).getRentDate();
                case 4:
                    if (rentals.get(rowIndex).getReturnDate() == null) {
                        return "";
                    } else {
                        return rentals.get(rowIndex).getReturnDate();
                    }
                    //return rentals.get(rowIndex).getReturnDate();
                case 5:
                    final JButton buttonReturn = new JButton("Return");
                    buttonReturn.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            rentalService.deleteRentalByID(rentals.get(rowIndex)); // return date?
                            reload();
                        }
                    });
                    return buttonReturn;
                }
                return null;
            }

            @Override
            public Class getColumnClass(int columnIndex) {
                if (getRowCount() == 0)
                    return Object.class;
                return getValueAt(0, columnIndex).getClass();
            }
        };

        sorter = new TableRowSorter<>(model);
        JTable table = new JTable(model);
        TableCellRenderer buttonRenderer = new JTableButtonRenderer();
        table.getColumn("Return").setCellRenderer(buttonRenderer);
        table.addMouseListener(new JTableButtonMouseListener(table));

        table.setAutoCreateRowSorter(true);
        table.setRowSorter(sorter);
        return table;
    }

    private void reload() {
        rentals = rentalService.listRentals();
        setJMenuBar(GlobalMenuBar.createMenu(this));

        getContentPane().removeAll();

        setLayout(new BorderLayout());
        jsp = new JScrollPane(table);
        add(jsp, BorderLayout.EAST);
        JPanel filter = new JPanel();

        filt.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
            }
        });
        //filter.add(new JLabel("Filter: "));
        filter.add(filt);
        // add(filter, BorderLayout.SOUTH);

        pack();
        revalidate();
        repaint();

        pack();
        revalidate();
        repaint();
    }
}
