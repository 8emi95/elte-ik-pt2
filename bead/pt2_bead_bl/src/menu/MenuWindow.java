package menu;

import java.awt.*;
import contents.*;
import film.*;
import static film.FilmsMain.conn;
import film.table.FilmDatasTable;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.*;

public class MenuWindow extends JFrame {  
  private final Dimension dimension=new Dimension(300,600);
  private void manageFrame(JFrame frame){
        
            if(frame.isShowing()){
               frame.setVisible(false);
            }else{
               frame.setVisible(true);
            }
  }
  
    public MenuWindow() {
      //ADD FILMS
      //________________________________________________________________________
        JButton addB=FilmsMain.addFilmB = new JButton();
        addB.setText("Film hozzáadása");
        addB.addActionListener(e ->{ //Gombra rányomás
           if(FilmsMain.addFilmFrame==null){
               FilmsMain.addFilmFrame=new JFrame("Filmek hozzáadás");
               FilmsMain.addFilmFrame.setSize(dimension);
            }
            manageFrame(FilmsMain.addFilmFrame);

       AddFilmContent addfilmC=new AddFilmContent(FilmsMain.addFilmFrame);
       
        });
      //________________________________________________________________________
        //LIST FILMS
        FilmsMain.find_listFilmB=new JButton("Keresés és listázás");
        FilmsMain.find_listFilmB.addActionListener(e ->{
            if(FilmsMain.find_listFilmFrame==null){
                FilmsMain.find_listFilmFrame=new JFrame("List films window");
                FilmsMain.find_listFilmFrame.setSize(dimension);
            }
        manageFrame(FilmsMain.find_listFilmFrame);
        
        
       Find_ListFilmContent listfilmC=new Find_ListFilmContent(FilmsMain.find_listFilmFrame);
        
        });
      
        //DELETE FILMS
        //______________________________________________________________________
        FilmsMain.deleteFilmB=new JButton("Film törlése");
        FilmsMain.deleteFilmB.addActionListener(e ->{
   
        if(FilmsMain.deleteFilmFrame==null){
            FilmsMain.deleteFilmFrame=new JFrame("Filmek törlése");
            FilmsMain.deleteFilmFrame.setSize(dimension);
        }
        manageFrame(FilmsMain.deleteFilmFrame);
        DeleteFilmContent deletefilmC=new DeleteFilmContent(FilmsMain.deleteFilmFrame);
        
        
        });
         //LEND FILMS
        //______________________________________________________________________
        FilmsMain.lendFilmB=new JButton("Film kölcsönadása");

        FilmsMain.lendFilmB.addActionListener(e ->{
            
            if(FilmsMain.lendFilmFrame==null){
                FilmsMain.lendFilmFrame=new JFrame("Filmek kölcsönadása");
                FilmsMain.lendFilmFrame.setSize(dimension);
            }
            manageFrame(FilmsMain.lendFilmFrame);
            LendFilmContent lenffilmC=new LendFilmContent(FilmsMain.lendFilmFrame);
        });
        
        //LEND-LIST FILMS
        //______________________________________________________________________
        FilmsMain.lend_listFilmB=new JButton("Kölcsönzött film visszadása");
        
        FilmsMain.lend_listFilmB.addActionListener(e ->{
            
        if(FilmsMain.lend_listFilmFrame==null){
                FilmsMain.lend_listFilmFrame=new JFrame("Film visszaadása");
                FilmsMain.lend_listFilmFrame.setSize(dimension);
            }
            manageFrame(FilmsMain.lend_listFilmFrame);
        
        
        LentFilmsBackContent lentFilmsBackC= new LentFilmsBackContent(FilmsMain.lend_listFilmFrame);
        
        });
        
        FilmsMain.saveButton=new JButton("Mentés");
        
        FilmsMain.saveButton.addActionListener(e ->{
            
            try (Statement stmt = conn.createStatement()) {
            
              stmt.executeUpdate("TRUNCATE person");
               stmt.executeUpdate("TRUNCATE film");
               
                //String idézőjelbe
                
                for ( Film f : FilmsMain.filmCollection.getFilms()) {
                    
                    ArrayList<String> guys=new ArrayList<>();
                    for(Person p: f.getMainCharacter()){
                        guys.add(Integer.toString(p.getID()));
                    }
                    
                   
                    int by=0;
                    if(f.getLentBy()!=null){
                        by=f.getLentBy().getID();
                    }
                    
                       String sql = "INSERT INTO film VALUES ("
                        +f.getID()+","
                        +"'"+f.getTitle().trim()+"'," 
                        +"'"+f.getDirector().getID()+"',"
                        +"'"+ String.join(",", guys.toArray(new String[0])) +"',"
                        +f.getYear()+","
                        +f.getLength()+","
                        +"'"+f.getDataType().toString().trim()+"',"
                        +"'"+f.getImage().trim()+"',"
                        +f.isOriginal()+","
                        +f.getLendNum()+","
                        +f.isLent()+","
                        +"'"+f.getLendFrom().trim()+"',"
                        +"'"+f.getLendTo().trim()+"',"
                        +by+")";
                       
                    
                    stmt.executeUpdate(sql);
                    
                }
                System.out.println("Filmek mentve");
                
                for ( Person friend : FilmsMain.friends) {
                    
                    int type=0;
                    if(friend.getType()==PersonType.director){
                        type=1;
                    }else if(friend.getType()==PersonType.friend){
                        type=2;
                    }
                    String sql="INSERT INTO person VALUES ("+
                        friend.getID()+","
                        +"'"+friend.getName().trim()+"'," 
                        +type+")";

                    stmt.executeUpdate(sql);
                }
                System.out.println("Emberek mentve");
                JOptionPane.showMessageDialog(new JFrame(),"Sikeres mentés");
            } catch (Exception ex){
                System.out.println(ex.toString());
            };
  
        });
        

        //______________________________________________________________________
          //PANIC
        FilmsMain.panicFilmB=new JButton("Pánik");
        FilmsMain.panicFilmB.addActionListener(e ->{
            FilmsMain.filmCollection.panic();
            JOptionPane.showMessageDialog(new JFrame(),"Ha voltak kalózmásolatok, akkor"
                    + " törlésre kerültek");
            
        });

      
        BoxLayout boxLayout =new BoxLayout(getContentPane(), BoxLayout.X_AXIS);    
        getContentPane().setLayout(boxLayout);
        getContentPane().add(FilmsMain.addFilmB);
        getContentPane().add(FilmsMain.find_listFilmB);
        getContentPane().add(FilmsMain.deleteFilmB);
        getContentPane().add(FilmsMain.lendFilmB);
        getContentPane().add(FilmsMain.lend_listFilmB);
        getContentPane().add(FilmsMain.panicFilmB);
        FilmDatasTable datas=new FilmDatasTable();
        getContentPane().add(FilmsMain.tableButton);
        getContentPane().add(FilmsMain.saveButton);

        pack();
        this.setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

}//endOfClass

