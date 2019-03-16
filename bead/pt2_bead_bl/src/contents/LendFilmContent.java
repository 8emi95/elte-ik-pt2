
package contents;
import contents.entering.DataEnteringForLending;
import film.*;
import java.awt.Choice;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.*;



public class LendFilmContent extends JButton{
    
    
    
    private void openTable(ArrayList<Film> wantOpenFilms, String text){
    
        FilmsMain.tableFrame = new JFrame(text);
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
                FilmsMain.tableFrame=new JFrame("Adatbázisban szereplő filmek");
            }
        
        if(FilmsMain.tableFrame.isShowing()){
               FilmsMain.tableFrame.setVisible(false);
            }else{
               FilmsMain.tableFrame.setVisible(true);
            }
    
    
    }
    
    
    private int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    
    /*cím, rendező, ki, dátum*/

    public LendFilmContent(JFrame frame){
        
        
 
       String information="Írja be a film paramétereit "
        + "és a nevét a kölcsönzéshez  ";
        DataEnteringForLending lendFilmDataEntering=new DataEnteringForLending(frame,information);
        this.setText("Kölcsönvétel");
        
        JLabel preError=new JLabel("Hibás: ");
        JLabel error = new JLabel("");
        
        this.addActionListener(e->{
        
            error.setText("");
                
            String title=lendFilmDataEntering.getTitleField().getText();
            if(title.equals("")){
                error.setText(error.getText() + "cím.. ");
            }
            
            
            //________________________________________________________________________
            Person director=null;
            try{
                String[] directorFieldContent = lendFilmDataEntering.getDirectorField().getText().split(" ");
                if(directorFieldContent.length==2){
                    if(!directorFieldContent[0].equals("") && !directorFieldContent[1].equals("")){
                        director = new Person(null, directorFieldContent[0], directorFieldContent[1], PersonType.director);  
                    }
                }
            }catch(NumberFormatException ex){
                System.out.println("HIBA: "+ ex.getMessage());
            }

            if(director == null){
                error.setText(error.getText() + " rendező.. ");
            }

            //________________________________________________________________________
            
            Person lendBy=null;
            try{
                String[] lendByContent = lendFilmDataEntering.getLendByField().getText().split(" ");
                if(lendByContent.length==2){
                    if(!lendByContent[0].equals("") && !lendByContent[1].equals("")){
                        lendBy = new Person(null, lendByContent[0], lendByContent[1], PersonType.friend);  
                    }
                }
            }catch(NumberFormatException ex){
                System.out.println("HIBA: "+ ex.getMessage());
            }

            if(lendBy == null){
                error.setText(error.getText() + " neve.. ");
            }
            
            
            
            if(error.getText().equals("")){
                
                ArrayList<Film> lendFilms=FilmsMain.filmCollection.findFilms(title, director, null, null, null, null, null, null);
                if(lendFilms.size()!=0){
                    
                    String y=Integer.toString(Calendar.getInstance().get(Calendar.YEAR));
                    String m=Integer.toString(Calendar.getInstance().get(Calendar.MONTH));
                    String d=Integer.toString(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
                    
                    String m2=Integer.toString(Calendar.getInstance().get(Calendar.MONTH)+1);
                    
                    if(FilmsMain.filmCollection.lend(lendFilms.get(0), y+"-"+m+"-"+d, y+"-"+m2+"-"+d, lendBy)){
                        JOptionPane.showMessageDialog(frame,"Sikeres kölcsönzés");
                        lendFilmDataEntering.setText("");
                        
                        
                    }else{
                        JOptionPane.showMessageDialog(frame,"Sikertelen kölcsönzés","",JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(frame,"A film nem található (lehet épp ki van kölcsönözve)","Kölcsönzési hiba",JOptionPane.ERROR_MESSAGE);
                }
                
                
            
            }else{
                JOptionPane.showMessageDialog(frame,preError.getText()+error.getText(),"Beszúrási hiba",JOptionPane.ERROR_MESSAGE);
                error.setVisible(true);
            }
  
        });
        
        
        
        
        
        JButton checkLendingButton=new JButton("Keresés");
        checkLendingButton.addActionListener(e->{
            
            JDialog.setDefaultLookAndFeelDecorated(true);
            Object[] selectionValues = { "Már voltak kölcsönözve", "Jelenleg ki vannak kölcsönözve"};
            
            Object selection = JOptionPane.showInputDialog(null, "Válassz:",
            "", JOptionPane.QUESTION_MESSAGE, null, selectionValues, "Már voltak kölcsönözve");
            
           String answer=(String)selection;
           try{
                if(answer.equals("Már voltak kölcsönözve")){
                    openTable(FilmsMain.filmCollection.getBorrowedFilms(false),"Filmek amik már voltak kölcsönözve");

                }else{
                    openTable(FilmsMain.filmCollection.getBorrowedFilms(true),"Filmek amelyek jelenleg ki vannak kölcsönözve");
                }

           }catch(Exception ex){}
           

       });

       
       frame.getContentPane().add(this);
       frame.getContentPane().add(checkLendingButton);
       frame.getContentPane().revalidate();
       frame.getContentPane().repaint();

    }
    

    
    
    
}//endOfClass
