//ELVILEG MŰKÖDIK
package contents;

import contents.entering.DataEntering;
import film.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.*;


public class AddFilmContent extends JButton{
    private int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    
    

    public AddFilmContent(JFrame frame){
 
        String information="Írja be a film paramétereit "
        + "és adja hozzá a filmet az adatbázishoz  ";
        DataEntering addFilmDataEntering=new DataEntering(frame,information);
        this.setText("Hozzáadás");

      JLabel preError=new JLabel("Hibás: ");
      JLabel error = new JLabel("");

      //________________________________________________________________________
       this.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                error.setText("");
                
                String title=addFilmDataEntering.getTitleField().getText();
                if(title.equals("")){
                    error.setText(error.getText() + "cím.. ");
                }
                
                
                //________________________________________________________________________
                Person director=null;
                try{
                    String[] directorFieldContent = addFilmDataEntering.getDirectorField().getText().split(" ");
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
                
                String[] mainCharFieldContent=addFilmDataEntering.getMainCharField().getText().split(",");
                ArrayList<Person> mainCharacters=new ArrayList<>();
                boolean mainCharactersBool=false;
                if(mainCharFieldContent.length>=1){
                    
                    for(int i=0;i<mainCharFieldContent.length;++i){
                        String[] name=mainCharFieldContent[i].split(" ");
                        Person p;
                        if(name.length==2 && name[0]!=null && name[0]!="" && name[1]!=null && name[1]!=""/*&& name[0].matches("[A-Z][a-z]+") && name[1].matches("[A-Z][a-z]+")*/){
                            p=new Person(null, name[0],name[1],PersonType.artist);
                            mainCharacters.add(p);
                            mainCharactersBool=true;
                        }
                    }
                }
                
                if(!mainCharactersBool){
                    error.setText(error.getText() + " főszereplő(k).. ");
                }
                
                //________________________________________________________________________
                boolean yearBool=false;
                int year=-1;
                try{
                    year=Integer.parseInt(addFilmDataEntering.getYearField().getText());
                    
                }catch(Exception nfe){
                    yearBool=false;
                    //error.setText(error.getText() + " év.. ");
                }
                if(year>=0 && year<=currentYear) yearBool=true;
                if(!yearBool) error.setText(error.getText() + " év.. ");
                
                //________________________________________________________________________
                boolean lengthBool=false;
                int length=-1;
                try{
                    length=Integer.parseInt(addFilmDataEntering.getLengthField().getText());
                    
                }catch(Exception nfe){
                    lengthBool=false;
                    //error.setText(error.getText() + " hossz.. ");
                }
                if(length>=0 && length<=500) lengthBool=true;
                if(!lengthBool) error.setText(error.getText() + " hossz.. ");
                
                //________________________________________________________________________
                
                DataType type=DataType.DVD;
                if(addFilmDataEntering.getDVD_RadioButton().isSelected()){
                    type=DataType.DVD;
                }else if(addFilmDataEntering.getVHS_RadioButton().isSelected()){
                    type=DataType.VHS;
                }
                //________________________________________________________________________
                
                String image=addFilmDataEntering.getImageField().getText();
                //_______________________________________________________________________
                
                boolean filmIsOriginal=true;
                if(addFilmDataEntering.getOriginalRadioButton().isSelected()){
                    filmIsOriginal=true;
                }else if(addFilmDataEntering.getNotOriginalRadioButton().isSelected()){
                    filmIsOriginal=false;
                }
                //________________________________________________________________________
                
                
                if(title!=null && title!=""  && mainCharactersBool && error.getText() == "") {
                  
                   
                    Film film=new Film(null, title, director, mainCharacters,year,length,type,image,filmIsOriginal,0,false,"","",null);
                    FilmsMain.addDirector(director);
                    FilmsMain.addMaincharacters(mainCharacters);
                    
                    if(!FilmsMain.filmCollection.addFilm(film)){
                        
                        JOptionPane.showMessageDialog(frame,"Ezt a filmet már hozzáadtad","Beszúrási hiba",JOptionPane.ERROR_MESSAGE);
                        error.setVisible(true);
                    }else{
                        JOptionPane.showMessageDialog(frame,"Sikeresen hozzáadtad a filmet");
                        addFilmDataEntering.getTitleField().setText("");
                        addFilmDataEntering.getDirectorField().setText("");
                        addFilmDataEntering.getMainCharField().setText("");
                        addFilmDataEntering.getYearField().setText("");
                        addFilmDataEntering.getLengthField().setText("");
                        addFilmDataEntering.getImageField().setText("");
                        
                    }
                } else {
                    
                    JOptionPane.showMessageDialog(frame,preError.getText()+error.getText(),"Beszúrási hiba",JOptionPane.ERROR_MESSAGE);
                    error.setVisible(true);
                    error.removeAll();
                }    }
        });//BUTTON VÉGE
       
       frame.getContentPane().add(this);
       frame.getContentPane().revalidate();
       frame.getContentPane().repaint();
 
       
    }
    
}//endOfClass
