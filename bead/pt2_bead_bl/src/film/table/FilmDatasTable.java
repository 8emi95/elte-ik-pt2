package film.table;

import film.Film;
import film.FilmsMain;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author Lúcia
 * 
 */

import javax.swing.table.AbstractTableModel;

public class FilmDatasTable {

    public FilmDatasTable(){
       
        
        FilmsMain.tableButton=new JButton("Táblázat");
        FilmsMain.tableButton.addActionListener(e->{
        FilmsMain.tableFrame = new JFrame("Adatbázisban szereplő filmek");
        FilmsMain.tableFrame.setSize(1300,500);
        String[] columnNames = {"ID",
                                "Cím",
                                "Rendező",
                                "Főszereplő(k)",
                                "Gyártási év",
                                "Hossz",
                                "Adathordozó",
                                "Borítókép",
                                "Eredeti-e",
                                "Kölcsönadva",
                                "Vissza van hozva?",
                                "Mettől",
                                "Meddig",
                                "Által"
                                 };
 
        ArrayList<Film> films = FilmsMain.filmCollection.getFilms();
        String[][] data = new String[films.size()][];
        int i = 0;
        for (Film f : films) {
            String[] row = new String[14];
            
            row[0] = Integer.toString(f.getID());
            
            row[1] = f.getTitle();
            row[2] = f.getDirector().toString();
           // row[3] = String.join(",",f.getMainCharacter().toArray(new String[0]));
            row[3] = f.getMainCharacter().toString();
            row[4] = Integer.toString(f.getYear());
            row[5] = Integer.toString(f.getLength());
            row[6] = f.getDataType().toString();
            row[7] = f.getImage();
            
            
            row[8] = f.isOriginal() ? "Igen" : "Nem";
            row[9] = Integer.toString(f.getLendNum());
            row[10] = f.isLent()== true ? "Nincs" : "";
            row[11] = f.getLendFrom();
            row[12] = f.getLendTo();
            row[13] = f.getLentBy() == null ? "" : f.getLentBy().toString();
            
            data[i] = row;
            ++i;
        }
 
        final JTable table = new JTable(data, columnNames);
        
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
        
        
        JScrollPane scrollPane = new JScrollPane(table);
 
        FilmsMain.tableFrame.getContentPane().add(scrollPane);
        
        if(FilmsMain.tableFrame==null){
                FilmsMain.tableFrame=new JFrame("Adatbázisban szereplő filmek");
            }
        
        if(FilmsMain.tableFrame.isShowing()){
               FilmsMain.tableFrame.setVisible(false);
            }else{
               FilmsMain.tableFrame.setVisible(true);
            }

        });
    }
    
}
