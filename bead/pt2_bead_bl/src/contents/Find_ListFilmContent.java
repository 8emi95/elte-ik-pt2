
package contents;

import contents.entering.DataEntering;
import contents.entering.DataEnteringForSearching;
import film.*;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.*;

public class Find_ListFilmContent extends JButton{
    
    
    private void openTable(ArrayList<Film> wantOpenFilms){
    
        FilmsMain.tableFrame = new JFrame("Keresett filmek");
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
 
        ArrayList<Film> films = wantOpenFilms;
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
                FilmsMain.tableFrame=new JFrame("Keresett filmek");
            }
        
        if(FilmsMain.tableFrame.isShowing()){
               FilmsMain.tableFrame.setVisible(false);
            }else{
               FilmsMain.tableFrame.setVisible(true);
            }
    
    
    }
    

    private int currentYear = Calendar.getInstance().get(Calendar.YEAR);

    public Find_ListFilmContent(JFrame frame){
 
            String information="Írja be a film paramétereit "
            + "és listázza ki a keresett filmet. "
                    + "Amennyiben nem ad meg feltételt és nem választ opciót, akkor az "
                    + "alapértelmezetten beállított értékek alapján "
                    + "kerülnek listázásra a filmek. ";
            DataEnteringForSearching find_listFilmDataEntering=new DataEnteringForSearching(frame,information);
            this.setText("Film(ek) keresése");

          JLabel preError=new JLabel("Hibás: ");
          JLabel error = new JLabel("");
          //________________________________________________________________________
           this.addActionListener(e->{
                    error.setText("");
                    String title=find_listFilmDataEntering.getTitleField().getText(); 
                    
                    if(title.equals("")) {
                        title = null;
                    }

               //________________________________________________________________________
                Person director=null;
                boolean directorBool=false;
                boolean directorEmptyBool=false;
                try{
                 String[] directorFieldContent = find_listFilmDataEntering.getDirectorField().getText().split(" ");
                 if(directorFieldContent.length==2){
                     if(!directorFieldContent[0].equals("") && !directorFieldContent[1].equals("")){
                         director = new Person(null, directorFieldContent[0], directorFieldContent[1], PersonType.director);
                         directorBool=true;
                     }
                 }
                 //A split miatt nem nulla a hossz
                 if(directorFieldContent.length==1 && directorFieldContent[0].equals("")){
                     directorEmptyBool=true;
                 } 
                }catch(NumberFormatException ex){ }

                if(!directorBool && !directorEmptyBool){
                     error.setText(error.getText() + " rendező.. ");
                }

               //________________________________________________________________________

                String[] mainCharFieldContent=find_listFilmDataEntering.getMainCharField().getText().split(",");
                ArrayList<Person> mainCharacters=new ArrayList<>();
                boolean mainCharactersBool=false;
                boolean mainCharactersEmptyBool=false;
                if(mainCharFieldContent.length>=1){

                 for(int i=0;i<mainCharFieldContent.length;++i){
                     String[] name=mainCharFieldContent[i].split(" ");
                    Person p;
                     if(name.length==2 && !name[0].equals("") && !name[1].equals("")/*&& name[0].matches("[A-Z][a-z]+") && name[1].matches("[A-Z][a-z]+")*/){
                         p=new Person(null, name[0],name[1],PersonType.artist);
                         mainCharacters.add(p);
                         mainCharactersBool=true;
                     }
                 }
                }
                if(mainCharFieldContent.length==1 && mainCharFieldContent[0].equals("")){
                    mainCharactersEmptyBool=true;
                    mainCharacters = null;
                } else if(!mainCharactersBool && !mainCharactersEmptyBool){
                   error.setText(error.getText() + " főszereplő(k).. "); 
                }

               //________________________________________________________________________
                Integer year=-1;
                boolean yearBool=false;
                boolean yearEmptyBool=false;
                String yearText=find_listFilmDataEntering.getYearField().getText();
                if(yearText.equals("")){
                    yearEmptyBool=true;
                    year=null;
                }else{
                    
                    try{
                     year=Integer.parseInt(find_listFilmDataEntering.getYearField().getText());

                    }catch(Exception nfe){
                          // yearBool=false;
                    }
                    if(year>=0 && year<=currentYear) yearBool=true;


                    if(!yearBool && !yearEmptyBool) error.setText(error.getText() + " év.. ");
                
                }

                 //________________________________________________________________________
                 Integer length=-1;
                 boolean lengthBool=false;
                 boolean lengthEmptyBool=false;
                 String lengthText=find_listFilmDataEntering.getLengthField().getText();
                 if(lengthText.equals("")){
                     lengthEmptyBool=true;
                     length=null;
                 }else{
                    try{
                    length=Integer.parseInt(find_listFilmDataEntering.getLengthField().getText());

                    }catch(Exception nfe){
                       // lengthBool=false;
                    }
                    if(length>=0 && length<=500) lengthBool=true;
                    if(!lengthBool && !lengthEmptyBool) error.setText(error.getText() + " hossz.. ");
                 
                 }

               //________________________________________________________________________

                DataType type=DataType.DVD;
                if(find_listFilmDataEntering.getDVD_RadioButton().isSelected()){
                    type=DataType.DVD;
                }else if(find_listFilmDataEntering.getVHS_RadioButton().isSelected()){
                    type=DataType.VHS;
                }
               //________________________________________________________________________

                boolean filmIsOriginal=true;
                if(find_listFilmDataEntering.getOriginalRadioButton().isSelected()){
                    filmIsOriginal=true;
                }else if(find_listFilmDataEntering.getNotOriginalRadioButton().isSelected()){
                    filmIsOriginal=false;
                }
               //________________________________________________________________________
              
                   ArrayList<Film> wantListFilms=new ArrayList<>();

                   
                  wantListFilms=FilmsMain.filmCollection.findFilms(title, director, mainCharacters, year, length, type, filmIsOriginal, null);

               if(error.getText()==""){
                 if(wantListFilms!=null && wantListFilms.size()!=0){
                    
                    openTable(wantListFilms);
                 }else{
                     JOptionPane.showMessageDialog(frame,"Ez(ek) a film(ek) nem található(ak) az adatbázisban","Keresési hiba",JOptionPane.ERROR_MESSAGE);

                 }
               }
               else{
                   JOptionPane.showMessageDialog(frame,preError.getText()+error.getText(),"Input hiba",JOptionPane.ERROR_MESSAGE);
                    error.setVisible(true);
               }
             
       });//BUTTON VÉGE

       frame.getContentPane().add(this);
       frame.getContentPane().revalidate();
       frame.getContentPane().repaint();
    }   
}//endOfClass
