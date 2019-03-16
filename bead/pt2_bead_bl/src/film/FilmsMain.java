package film;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import com.sun.tracing.dtrace.NameAttributes;
import java.sql.*;
import java.util.ArrayList;
import menu.MenuWindow;
import javax.swing.*;

public class FilmsMain {
    public static ArrayList<Person> friends=new ArrayList<>();
    //addFilms
    public static JButton addFilmB; 
    public static JFrame addFilmFrame;
     //listFilms
    public static JButton find_listFilmB; 
    public static JFrame find_listFilmFrame; 
     //deleteFilms
    public static JButton deleteFilmB; 
    public static JFrame deleteFilmFrame; 
    //lendFilms
    public static JButton lendFilmB; 
    public static JFrame lendFilmFrame; 
    //lendFilmslist
    public static JButton lend_listFilmB; 
    public static JFrame lend_listFilmFrame; 
     //panic
    public static JButton panicFilmB; 
    public static JFrame panicFilmFrame; 
    
    public static JFrame tableFrame;
    public static JButton tableButton;
    
    //save
    public static JFrame saveFrame;
    public static JButton saveButton;
   
    public static FilmCollection filmCollection;
    
    private static int IDCounterForFilms=0;
    private static int IDCounterForPerson=0;
    public static int getNextIdForFilms(){
       return ++IDCounterForFilms;
    }
    public static int getNextIdForPerson(){
       return ++IDCounterForPerson;
    }
    
    private static MysqlConnectionPoolDataSource mysql;
    public static Connection conn;
    private static String DBNAME = "filmek";
    
    public static Connection getConnection() {
        try {
            if (mysql == null){
                Class.forName("com.mysql.jdbc.Driver"); // Driver betöltése
                mysql = new MysqlConnectionPoolDataSource();
                mysql.setServerName("localhost");
                mysql.setPort(3306);
                mysql.setDatabaseName(DBNAME);
                mysql.setUser("tanulo");
                mysql.setPassword("asd123");
                System.out.println("Szuper");
                return mysql.getPooledConnection().getConnection();
            }
        }catch (Exception e) {
            System.out.println("Rossz adatbázis");
        }
        
        return null;
    }
    
    public static void main(String[] args) {
        conn = getConnection();
        
        if(conn == null) {
            return;
        }
        
        filmCollection=new FilmCollection();
        loadData();
        
        MenuWindow menu=new MenuWindow();
        menu.setVisible(true); 

    }

    private static void loadData() {
        try (Statement stmt = conn.createStatement()) {
            Person friend=null;
            ResultSet rsb = stmt.executeQuery("SELECT * FROM person;");
            while (rsb.next()){
                int id = rsb.getInt("id");
                String[] name = rsb.getString("nev").split(" ");
                
                int type=rsb.getInt("típus");
                PersonType personType=null;
                switch(type){
                    case 0: personType=PersonType.artist;
                        break;
                    case 1: personType=PersonType.director;
                        break;
                    case 2: personType=PersonType.friend;
                        break;
                }
                if(name.length!=2){
                    System.out.println("Rossz név");
                    continue;
                }
                friend=new Person(id, name[0],name[1],personType);
                friends.add(friend); 
                IDCounterForPerson = Math.max(IDCounterForPerson, id);
                
            }
            ResultSet rs = stmt.executeQuery("SELECT * FROM film;");
            
            while (rs.next()){
                
                int id = rs.getInt("id");
                String title = rs.getString("cim");
                int dirId = rs.getInt("rendezok");
                Person director= getFriendById(dirId);
                
                if(director == null) {
                    System.out.println("(Film: "+title+" "+id+") Rossz rendezo id: "+dirId);
                    continue;
                }
                
                ArrayList<Person> mainCharacters=new ArrayList<>();
                String[] mainC=rs.getString("foszereplok").split(",");
                for(String strId : mainC){
                    int personId = Integer.parseInt(strId);
                    
                    Person tmp = getFriendById(personId);
                    
                    if(tmp != null) {
                        mainCharacters.add(tmp);  
                    } else {
                        System.out.println("Hibas foszereplo id: "+personId);
                    }     
                }
                int year=rs.getInt("év");
                int length=rs.getInt("hossz");
                String typeString=rs.getString("típus");
                DataType type = DataType.valueOf(typeString);
                
                String image=rs.getString("kép");
                boolean isOriginal=rs.getBoolean("eredeti");
                int numOfLend=rs.getInt("hanyszor");
                boolean isGetBack=rs.getBoolean("visszahozva");
                String lendFrom=rs.getString("kolcson_tol");
                String lendTo=rs.getString("kolcson_ig");
                int lendByint=rs.getInt("kolcson_ki");
                
                friend = getFriendById(lendByint);
                
                if(friend != null || lendByint==0) {
                    Film film=new Film(id, title, director, mainCharacters, year, length, type, image, isOriginal,numOfLend,isGetBack,lendFrom, lendTo, friend);
                    FilmsMain.filmCollection.addFilm(film);
                    
                    IDCounterForFilms = Math.max(IDCounterForFilms, id);
                } else {
                    System.out.println("Rossz barat id");
                } 
            }
           
        } catch (Exception e){
        System.out.println(e.toString());
        };
    }
    
    private static Person getFriendById(int id) {
        Person friend = null;
        
        for(Person frnd : friends) {
            if(frnd.getID() == id) {
                friend = frnd;
                break;
            }
        } 
        return friend;
    }
    
     public static void addMaincharacters(ArrayList<Person> mainc){
         for(Person p: mainc){
            if(p!=null && !friends.contains(p))
            friends.add(p);
         } 
    }
    
    public static void addDirector(Person dir){
        if(dir!=null && !friends.contains(dir))
        friends.add(dir);
    }   
}
