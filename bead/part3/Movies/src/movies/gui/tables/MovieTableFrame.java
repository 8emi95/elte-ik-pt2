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
import movies.entities.Movie;
import movies.gui.RentMovie;
import movies.gui.utils.GlobalMenuBar;
import movies.gui.utils.JTableButtonMouseListener;
import movies.gui.utils.JTableButtonRenderer;

/**
 *
 * @author Emese
 */
public class MovieTableFrame extends JFrame {
    private final MovieService movieService = new MovieService();

    public List<Movie> movies = new ArrayList<>();
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

    public MovieTableFrame() {
        super("Movie");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.table = movieTable();
        setJMenuBar(GlobalMenuBar.createMenu(this));
        setLayout(new BorderLayout());
        table = movieTable();
        jsp = new JScrollPane(table);
        add(jsp, BorderLayout.EAST);
        JPanel filter = new JPanel();
        setPreferredSize(new Dimension(1000, 700));
        table.setPreferredScrollableViewportSize(table.getPreferredSize());

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

    private JTable movieTable() {
        AbstractTableModel model = new AbstractTableModel() {

            private final String[] tabs = { "ID", "Title", "Directors", "Actors", "Release Year", "Length in Minutes", "Cover URL", "Original", "Number of Rentals", "Available", "Rent", "Modify", "Delete" };

            @Override
            public String getColumnName(int column) {
                return tabs[column];
            }

            @Override
            public int getRowCount() {
                return movies.size();
            }

            @Override
            public int getColumnCount() {
                return tabs.length;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                switch (columnIndex) {
                case 0:
                    return movies.get(rowIndex).getID();
                case 1:
                    return movies.get(rowIndex).getTitle();
                case 2:
                    return movies.get(rowIndex).getDirectors();
                case 3:
                    return movies.get(rowIndex).getActors();
                case 4:
                    return movies.get(rowIndex).getReleaseYear();
                case 5:
                    return movies.get(rowIndex).getLengthInMins();
                case 6:
                    return movies.get(rowIndex).getCover();
                case 7:
                    return movies.get(rowIndex).isOriginal();
                case 8:
                    return movies.get(rowIndex).getRentalNumber();
                case 9:
                    return movies.get(rowIndex).isAvailable();
                case 10:
                    final JButton buttonRent = new JButton(movies.get(rowIndex).isAvailable() ? "Rent" : "");
                    if (movies.get(rowIndex).isAvailable()) {
                        buttonRent.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                new RentMovie(movies.get(rowIndex).getID());
                            }
                        });
                    }
                    return buttonRent;
                case 11:
                    final JButton buttonModify = new JButton("Modify");
                    buttonModify.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            // modify
                        }
                    });
                    return buttonModify;
                case 12:
                    final JButton buttonDelete = new JButton("Delete");
                    if (movies.get(rowIndex).isAvailable()) {
                        buttonDelete.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                movieService.deleteMovieByID(movies.get(rowIndex).getID());
                            }
                        });
                    }
                    return buttonDelete;
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
        table.getColumn("Rent").setCellRenderer(buttonRenderer);
        table.getColumn("Modify").setCellRenderer(buttonRenderer);
        table.getColumn("Delete").setCellRenderer(buttonRenderer);
        table.addMouseListener(new JTableButtonMouseListener(table));

        table.setAutoCreateRowSorter(true);
        table.setRowSorter(sorter);
        return table;
    }

    private void reload() {
        movies = movieService.listMovies();
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
