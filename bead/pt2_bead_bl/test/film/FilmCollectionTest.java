package film;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Lúcia
 */
public class FilmCollectionTest {
    
    Person Zsolt= new Person(null,"Nagy", "Zsolt", PersonType.artist);
    Person Lúcia= new Person(null,"Bajári", "Lúcia", PersonType.artist);
    Person John=new Person(null,"John","Higgins",PersonType.artist);
    Person Ákos= new Person(null,"Hf", "Ákos", PersonType.director);
    Person Márió= new Person(null,"Szentesi", "Márió", PersonType.director);
    Person Bia= new Person(null,"Kegyes", "Bianka", PersonType.friend);
    Person Kata= new Person(null,"Kállai", "Katalin", PersonType.friend);
    
    ArrayList<Person> mainCharacters=new ArrayList<>();
    ArrayList<Person> mainCharacters2=new ArrayList<>();
    /*
    Film(String title,Person director, ArrayList<Person> mainCharacter, int year, int length, DataType dataType, String image, boolean isOriginal
        ,int lendNun,boolean isLent,String lendFrom, String lendTo, Person lentBy)
    
    */
    Film film1=new Film(null,"It Follows", Márió, mainCharacters,1980, 160, DataType.DVD, "itFollows.jpg", true, 0, true, "2015-03-01", "2015-04-01", Bia);
    Film film2=new Film(null,"The Godfather",Ákos,mainCharacters,1974,190,DataType.DVD,"goldfather2.jpg",true,5,false,"","",null);
    Film film3=new Film(null,"Film_3", Márió, mainCharacters, 1944, 200, DataType.DVD, "3.jpg", true, 0, false, "", "", null);
    Film film4=new Film(null,"Film_4",Márió,mainCharacters,1955,220,DataType.VHS,"4.jpg",false,1,true,"2016-03-01", "2016-04-05",Kata);


     /**
      * Test of getFilms method, of class FilmCollection.
      */
    @Test
    public void testGetFilms_WhenSizeIsTwo() {
        mainCharacters.add(Zsolt);
        mainCharacters.add(Lúcia);

        FilmCollection filmCollection = new FilmCollection();
        
        filmCollection.addFilm(film1);
        filmCollection.addFilm(film2);

        assertEquals(filmCollection.getFilms().size(),2);

    }

    @Test
    public void testGetFilms_WhenNoContainsFilm() {
        FilmCollection filmCollection = new FilmCollection();
        assertEquals(filmCollection.getFilms().size(),0);
    }

    /**
     * Test of addFilm method, of class FilmCollection.
     */
    @Test
    public void testAddFilm() {
        FilmCollection filmCollection = new FilmCollection();
        assertTrue(filmCollection.addFilm(film1));
        
    }
     @Test
    public void testAddFilm_WhenTheFilmAlreadyAdded() {
        FilmCollection filmCollection = new FilmCollection();
        filmCollection.addFilm(film1);//Itt egyszer már hozzá lesz adva
        assertFalse(filmCollection.addFilm(film1));
        
    }
    /**
     * Test of findFilms method, of class FilmCollection.
     */
    
    
     @Test
    public void testFindFilms_ByTitle_Director_MainChars() {
        FilmCollection filmCollection = new FilmCollection();
        
        //Üres FilmCollection-ban keresés
        ArrayList<Film> result1 = filmCollection.findFilms("It Follows", null, null, null, null, null, null, null);
        assertEquals(filmCollection.getFilms().size(), result1.size());
        
        filmCollection.addFilm(film1);

        //Cím alapján keresés
        ArrayList<Film> result2 = filmCollection.findFilms("It Follows", null, null, null, null, null, null, null);        
        assertEquals(filmCollection.getFilms().size(), result2.size());
        
        //Rendező alapján keresés
        ArrayList<Film> result3 = filmCollection.findFilms(null, Márió, null, null, null, null, null, null);        
        assertEquals(filmCollection.getFilms().size(), result3.size());
        
        //Főszereplő(k) alapján keresés
        mainCharacters.add(Zsolt);
        mainCharacters.add(Lúcia);

        ArrayList<Film> result4 = filmCollection.findFilms(null, null, mainCharacters, null, null, null, null, null);  
        assertEquals(filmCollection.getFilms().size(), result4.size());

    }
    
    
     @Test
    public void testContainsPerson() {
        FilmCollection filmCollection = new FilmCollection();
        
        mainCharacters.add(Zsolt);
        mainCharacters.add(Lúcia);
        mainCharacters2.add(Zsolt);
        mainCharacters2.add(Lúcia);
        
        assertTrue(filmCollection.containsPerson(mainCharacters,mainCharacters2));
    
    }
    
    
    
    
    
    @Test
    public void testFindFilms_ByYear_Length_Datatype() {
        FilmCollection filmCollection = new FilmCollection();

        filmCollection.addFilm(film1);

        //Év alapján keresés
        //Rossz év, nem találja
        ArrayList<Film> result1 = filmCollection.findFilms(null, null, null, 1960, null, null, null, null); 
        assertTrue(result1.size()==0);
        //Jó év
        ArrayList<Film> result2 = filmCollection.findFilms(null, null, null, film1.getYear(), null, null, null, null); 
        assertTrue(result2.size()==1);
        
        //Rendező alapján keresés
        ArrayList<Film> result3 = filmCollection.findFilms(null, null, null, null, film1.getLength(), null, null, null);        
        assertTrue(result3.size()==1);
        
        //Eredetiség alapján keresés
        ArrayList<Film> result4 = filmCollection.findFilms(null, null, null, null, null, null, true, null);        
        assertTrue(result4.size()==1);
        
        //...
 
    }
    
    /**
     * Test of deleteFilm method, of class FilmCollection.
     */
    @Test
    public void testDeleteFilm_WhenFilmIsLent() {
        
        FilmCollection filmCollection = new FilmCollection();
        
        filmCollection.addFilm(film1); //film1.isLent()==true
        filmCollection.addFilm(film2);
        //Nem törölheti, mert kölcsön van adva éppen
        assertTrue(filmCollection.deleteFilm(film1)==false);
       
    }
    
    @Test
    public void testDeleteFilm_WhenFilmNotIsLent() {
        
        FilmCollection filmCollection = new FilmCollection();
        filmCollection.addFilm(film1);
        filmCollection.addFilm(film2); //film2.isLent()==false
        //Adatbázisban szereplő film (nincs kölcsönadva), tudni kell törölni
        assertTrue(filmCollection.deleteFilm(film2)==true);
    }

    /**
     * Test of getBack method, of class FilmCollection.
     */

    @Test
    public void testLend() {
        FilmCollection filmCollection = new FilmCollection();
                                       //isLent()
        filmCollection.addFilm(film1); //true   
        filmCollection.addFilm(film2); //false
        filmCollection.addFilm(film3); //false
        filmCollection.addFilm(film4); //true
        //Még nincs kikölcsönözve a film
        assertTrue(filmCollection.lend(film2, "2013-02-21", "", Bia));
        //Már ki van kölcsönözve a film
        assertFalse(filmCollection.lend(film1, "2013-02-21", "", Bia));
        
    }
     @Test
    public void testgetBack() {
        FilmCollection filmCollection = new FilmCollection();
                                       //isLent()
        filmCollection.addFilm(film1); //true   
        filmCollection.addFilm(film2); //false
        filmCollection.addFilm(film3); //false
        filmCollection.addFilm(film4); //true
        
        //Még nincs kikölcsönözve a film
        filmCollection.lend(film2, "2013-02-21", "", Bia);
        filmCollection.lend(film3, "2014-03-02", "", Bia);
        
        //System.out.println("By: "+film2.getLentBy());
        //System.out.println("IsLent: "+film2.isLent());
        //System.out.println(Bia.getFilms());
        
        assertTrue(filmCollection.getBack(film2,Bia));
        //Film 4 nincs kölcsönadva, nem lehet visszavesszi
        assertFalse(filmCollection.getBack(film4,Bia));
        //film3-at NEM Kata kölcsönözte ki, így nem is adhatja vissza
        assertFalse(filmCollection.getBack(film3,Kata));
        
    }
    
    /**
     * Test of getBorrowedFilms method, of class FilmCollection.
     */
    @Test
    public void testGetBorrowedFilms() {
       FilmCollection filmCollection = new FilmCollection();
                                       //isLent()
        filmCollection.addFilm(film1); //true   
        filmCollection.addFilm(film2); //false
        filmCollection.addFilm(film3); //false
        filmCollection.addFilm(film4); //true
        
        //Film_3 eddig 0-szor volt kölcsönözve
                //System.out.println(filmCollection.getBorrowedFilms(false)); //==2
        assertEquals(filmCollection.getBorrowedFilms(false).size(),2);
        //Film_3-at kikölcsönzi Bia, így ő is volt kölcsönözve, ezért a getBorrowedFilms.size()-nak növelődnie kell 1-el
        filmCollection.lend(film3, "2014-03-02", "", Bia);
        filmCollection.getBack(film3,Bia);
                //System.out.println(filmCollection.getBorrowedFilms(false)); 
        assertEquals(filmCollection.getBorrowedFilms(false).size(),3);
        
        //Ez a grafikus megjelenítésnél fontos: 
        //true -> most van (azokat listázza amik jelenleg kölcsönözve vannak)
        //false -> már volt vagy ki van kölcsönözve
    }

    /**
     * Test of panic method, of class FilmCollection.
     */
    @Test
    public void testPanic() {
        FilmCollection filmCollection = new FilmCollection();
                                       //isOriginal()
        filmCollection.addFilm(film1); //true
        filmCollection.addFilm(film2); //true
        filmCollection.addFilm(film3); //true
        filmCollection.addFilm(film4); //false
        
        //System.out.println(film1.isOriginal()+" "+film2.isOriginal()+" "+film3.isOriginal()+" "+film4.isOriginal());
        
        assertTrue(filmCollection.getFilms().size()==4);
        filmCollection.panic();
        //Pánik gomb után törölve lett az egyetlen másolt példány
        assertTrue(filmCollection.getFilms().size()==3);
 
    }  
}
