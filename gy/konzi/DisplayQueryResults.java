// Fig. 28.28: DisplayQueryResults.java
// Display the contents of the Authors table in the books database.

// Átírta: Keszthelyi Zsolt 2017. április 17.
// (9. kiadás)
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.sql.SQLException;
import java.util.regex.PatternSyntaxException;

public class DisplayQueryResults extends JFrame {
    private static final String DATABASE_URL = "jdbc:derby://localhost:1527/books";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    // default query retrieves all data from authors table
    private static final String DEFAULT_QUERY = "SELECT * FROM authors";

    private ResultSetTableModel resultSetTableModel;
    // A contentPane-ben megjelenő gyerekkomponensek:
    private JTextArea taQuery = new JTextArea(DEFAULT_QUERY, 3, 100);
    private JScrollPane spQuery = new JScrollPane(taQuery,
                    ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                    ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    private JButton btSubmit = new JButton("Submit Query");        
    private JTable tbResult;
    private JScrollPane spResult;
    private JLabel lbFilter = new JLabel("Filter:");    
    private JTextField tfFilter = new JTextField();    
    private JButton btFilter = new JButton("Apply Filter");    

    private TableRowSorter<TableModel> sorter;
    
    public DisplayQueryResults() {
        setTitle("Displaying Query Results");
        setBounds(100, 100, 500, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);        
        try {
            // create TableModel for results of query SELECT * FROM authors
            resultSetTableModel = new ResultSetTableModel(DATABASE_URL,
                    USERNAME, PASSWORD, DEFAULT_QUERY);
        }
        catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, sqlException.getMessage(),
                    "Database error", JOptionPane.ERROR_MESSAGE);
            System.exit(1); // terminate application
        }
        
        // A contentPane középső részének kialakítása
        // create JTable based on the resultSetTableModel
        tbResult = new JTable(resultSetTableModel);
        spResult = new JScrollPane(tbResult);
        add(spResult, BorderLayout.CENTER); // contentPane-hez adjuk            

        // A contentPane északi részének kialakítása
        // set up JTextArea in which user types queries
        taQuery.setWrapStyleWord(true);
        taQuery.setLineWrap(true);
        Box boxNorth = Box.createHorizontalBox();
        boxNorth.add(spQuery);
        boxNorth.add(btSubmit);
        add(boxNorth, BorderLayout.NORTH); // contentPane-hez adjuk

        // A contentPane déli részének kialakítása        
        Box boxSouth = Box.createHorizontalBox();
        boxSouth.add(lbFilter);
        boxSouth.add(tfFilter);
        boxSouth.add(btFilter);
        add(boxSouth, BorderLayout.SOUTH); // contentPane-hez adjuk

        sorter = new TableRowSorter<TableModel>(resultSetTableModel);
        tbResult.setRowSorter(sorter);        
        
        // create event listener for btSubmit
        btSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btSubmit_actionPerformed(e);
            }
        });

        // create event listener for btFilter
        btFilter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btFilter_actionPerformed(e);
            }
        });

        // A tfFilter szövegmezőben ENTERt nyomtak
        tfFilter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btFilter_actionPerformed(e);
            }
        });
            
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                frame_windowClosing(e);                
            }
        });
        
        setVisible(true);                  
    } // end DisplayQueryResults constructor

    void btSubmit_actionPerformed(ActionEvent e) {
        try { // pass query to table model
            resultSetTableModel.setQuery(taQuery.getText());
        } catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null,
                    sqlException.getMessage(),
                    "Database error",
                    JOptionPane.ERROR_MESSAGE);
            // try to recover from invalid user query by executing default query
            try {
                resultSetTableModel.setQuery(DEFAULT_QUERY);
                taQuery.setText(DEFAULT_QUERY);
            } catch (SQLException sqlException2) {
                JOptionPane.showMessageDialog(null,
                        sqlException2.getMessage(),
                        "Database error",
                        JOptionPane.ERROR_MESSAGE);
                // ensure database connection is closed
                resultSetTableModel.disconnectFromDatabase();
                System.exit(1); // terminate application
            } // end inner catch
        } // end outer catch
    } // end btSubmit_actionPerformed
   
    void btFilter_actionPerformed(ActionEvent e) {
        String text = tfFilter.getText();
        if (text.length() == 0) {
            sorter.setRowFilter(null); // válogató
        }
        else {
            try {                             // regex: regular expression
                // az összes oszlopban keres:
                sorter.setRowFilter(RowFilter.regexFilter(text));
                // csak a 2. oszlopban keres: (0-tól kezdődik a sorszámozás)
                //sorter.setRowFilter(RowFilter.regexFilter(text, 2));                
            }
            catch (PatternSyntaxException pse) { // { vagy [] karakterek dobják
                JOptionPane.showMessageDialog(null,
                        "Bad regex pattern", "Bad regex pattern",
                        JOptionPane.ERROR_MESSAGE);
            }
        } // end else
    } // end method btFilter_actionPerfomed
    
    // disconnect from database and exit
    void frame_windowClosing(WindowEvent e) {
        resultSetTableModel.disconnectFromDatabase();
        System.exit(0);        
    }
    
    // execute application
    public static void main(String args[]) {
        new DisplayQueryResults();
    } // end main
} // end class DisplayQueryResults

/**
 * ************************************************************************
 * (C) Copyright 1992-2012 by Deitel & Associates, Inc. and * Pearson Education,
 * Inc. All Rights Reserved. * * DISCLAIMER: The authors and publisher of this
 * book have used their * best efforts in preparing the book. These efforts
 * include the * development, research, and testing of the theories and programs
 * * to determine their effectiveness. The authors and publisher make * no
 * warranty of any kind, expressed or implied, with regard to these * programs
 * or to the documentation contained in these books. The authors * and publisher
 * shall not be liable in any event for incidental or * consequential damages in
 * connection with, or arising out of, the * furnishing, performance, or use of
 * these programs. *
 ************************************************************************
 */
