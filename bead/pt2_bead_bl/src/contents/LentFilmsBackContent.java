
package contents;
import contents.entering.DataEnteringForLending;
import film.*;
import java.util.ArrayList;
import javax.swing.*;

public class LentFilmsBackContent extends JButton{


    public LentFilmsBackContent(JFrame frame){
        
       String information="Írja be a film paramétereit "
        + "és a nevét"
               + ", hogy vissza tudja adni a filmet   ";
        DataEnteringForLending lendFilmDataEntering=new DataEnteringForLending(frame,information);
        this.setText("Film visszadása");
        
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
                    if(FilmsMain.filmCollection.getBack(lendFilms.get(0),lendBy)){
                        JOptionPane.showMessageDialog(frame,"Sikeresen visszadta a filmet");
                        lendFilmDataEntering.setText(""); 
                    }else{
                        JOptionPane.showMessageDialog(frame,"Sikertelen visszadás","Rossz adatok",JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(frame,"A film nem található a kikölcsönzött filmek listáján","Visszavételi hiba hiba",JOptionPane.ERROR_MESSAGE);
                }
            }else{
                JOptionPane.showMessageDialog(frame,preError.getText()+error.getText(),"Beszúrási hiba",JOptionPane.ERROR_MESSAGE);
                error.setVisible(true);
            }
 
        });

       frame.getContentPane().add(this);
       frame.getContentPane().revalidate();
       frame.getContentPane().repaint();

    }
    

    
    
    
}//endOfClass
