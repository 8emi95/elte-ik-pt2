/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progtech2bead.gui.tables;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
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
import progtech2bead.controllers.MovieController;
import progtech2bead.controllers.RentalController;
import progtech2bead.entities.Movie;
import progtech2bead.entities.Rental;
import progtech2bead.gui.modals.RentMovie;
import progtech2bead.gui.utils.GlobalMenuBar;
import progtech2bead.gui.utils.JTableButtonMouseListener;
import progtech2bead.gui.utils.JTableButtonRenderer;

/**
 *
 * @author doma
 */
public class RentalTableFrame extends JFrame{
       private final RentalController rentalController = new RentalController();
       private final MovieController movieController = new MovieController();
    
    public List<Rental> rentals = new ArrayList<>();
    private JScrollPane jsp;
    private JTable table;
    private TableRowSorter sorter;
    private JTextField filt = new JTextField(30);
    
    private final SwingWorker<Void, Void> PAGE_REFRESH_WORKER = new SwingWorker<Void, Void>() {
        @Override
        protected Void doInBackground() throws Exception {
            while(true){
                reload();
                Thread.sleep(1000);
            }
        }
    };

    public RentalTableFrame() {
        super("Rentals");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.table = movieTable();
        setJMenuBar(GlobalMenuBar.createmenu(this));
        setLayout(new BorderLayout());
        table = movieTable();
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
        filter.add(new JLabel("Filter: "));
        filter.add(filt);
        //add(filter, BorderLayout.SOUTH);
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        PAGE_REFRESH_WORKER.execute();
    }
    
      private JTable movieTable(){
        AbstractTableModel model = new AbstractTableModel(){
            
            private final String[]  tabs = { "Name", "Expire at", "Movie", "Options" };

            @Override
            public String getColumnName(int column) { return tabs[column]; }
             
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
                switch(columnIndex){
                    case 0: return rentals.get(rowIndex).getForWho();
                    case 1: return rentals.get(rowIndex).getHowLong();
                    case 2: return movieController.getById(rentals.get(rowIndex).getMovideID()).getTitle();
                    case 3: final JButton button = new JButton("Bring back");
                        button.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                rentalController.bringBack(rentals.get(rowIndex));
                                reload();
                            }
                        });
                        return button;
                    }
                return null;
            }
            
            @Override
            public Class getColumnClass(int columnIndex){
                if ( getRowCount() == 0 )   return Object.class;
                return getValueAt(0, columnIndex).getClass();
            }
        };
         
        sorter = new TableRowSorter<>(model);
        JTable table = new JTable(model);
        TableCellRenderer buttonRenderer = new JTableButtonRenderer();
        table.getColumn("Options").setCellRenderer(buttonRenderer);
        table.addMouseListener(new JTableButtonMouseListener(table));
        
        table.setAutoCreateRowSorter(true);
        table.setRowSorter(sorter);
        return table;
    }

    private void reload(){
        rentals = rentalController.getAllRentals();
        setJMenuBar(GlobalMenuBar.createmenu(this));
        
        
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
        filter.add(new JLabel("Filter: "));
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
