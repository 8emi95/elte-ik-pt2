
package contents;

import contents.entering.DataEnteringForDeleteFilms;
import film.*;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.*;


public class DeleteFilmContent extends JButton{
    private int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    

    public DeleteFilmContent(JFrame frame){
      String information="Ahhoz hogy a filmet törölni lehessen, "
             + "meg kell adni a törölni kívánt film címét és a rendezőjét és a film csak akkor kerül törlésre"
             + "ha még nem tötölték az adatbázisból (szerepel benne), illetbe nincs kölcsönadva";
      DataEnteringForDeleteFilms deleteFilmDataEntering=new DataEnteringForDeleteFilms(frame,information);
      this.setText("Film törlése");
      JLabel preError=new JLabel("Hibás: ");
      JLabel error = new JLabel("");
      //________________________________________________________________________
       this.addActionListener(e->{
           error.setText("");
           
       String title=deleteFilmDataEntering.getTitleField().getText(); 
       if(title.equals("")){
           error.setText(error.getText() + "cím.. "); 
       }
      //________________________________________________________________________
       Person director=null;
       try{
            String[] directorFieldContent = deleteFilmDataEntering.getDirectorField().getText().split(" ");
            if(directorFieldContent.length==2){
                 if(!directorFieldContent[0].equals("") && !directorFieldContent[1].equals("")){
                     director = new Person(null,directorFieldContent[0], directorFieldContent[1], PersonType.director);
                 }
            } 
       }catch(NumberFormatException ex){
           System.out.println("HIBA: "+ ex.getMessage());
       }
       if(director == null){
            error.setText(error.getText() + " rendező.. ");
       }
      //________________________________________________________________________

       if(error.getText().equals("")) {
           ArrayList<Film> wantDeleteFilms=FilmsMain.filmCollection.findFilms(title,director,null,null,null,null,null,null);

           if(wantDeleteFilms.isEmpty())
           JOptionPane.showMessageDialog(frame,"A film nem található az adatbázisban");
           
           for(Film f:wantDeleteFilms){   
               for(int i=0;i<wantDeleteFilms.size();++i){
                    if(!wantDeleteFilms.get(i).isLent()){

                        FilmsMain.filmCollection.deleteFilm(f);
                        JOptionPane.showMessageDialog(frame,"Sikeresen kitörölted a filmet");
                        deleteFilmDataEntering.getDirectorField().setText("");
                        deleteFilmDataEntering.getTitleField().setText("");


                    }else{
                        JOptionPane.showMessageDialog(frame,"A film jelenleg ki van kölcsönözve","Törlési hiba",JOptionPane.ERROR_MESSAGE);
                    }
               }
           }
       } else {
           JOptionPane.showMessageDialog(frame,preError.getText()+error.getText(),"Törlési hiba",JOptionPane.ERROR_MESSAGE);
           error.setVisible(true);
           error.removeAll();
       }
       });//BUTTON VÉGE
       frame.getContentPane().add(this);
       frame.getContentPane().revalidate();
       frame.getContentPane().repaint();    
    }
    
}//endOfClass
