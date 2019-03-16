package film;

import java.util.ArrayList;

public class Person {
    private int ID;
    private String firstName;
    private String lastName;
    private PersonType type;
    private ArrayList<Film> films;

    public Person(Integer id, String firstName, String lastName, PersonType type) {
        this.ID= id == null ? FilmsMain.getNextIdForPerson() : id;
        this.firstName = firstName;
        this.lastName = lastName; 
        this.type = type;
        this.films=new ArrayList<>();
    }

    public int getID() {
        return ID;
    }

    public String getFirstName() {
        return firstName;
    }
    
    public String getName(){
    return this.firstName+" "+this.lastName;
    }

    public PersonType getType() {
        return type;
    }
    

    public ArrayList<Film> getFilms() {
        return new ArrayList<Film>(films);
    }
  
    
    public boolean equals(Person p){
        return firstName.equals(p.firstName) && lastName.equals(p.lastName)
                 && type==p.type;
    }
    
    public void borrow(Film film){
        this.films.add(film);
    }
    
    public boolean giveBack(Film film){
        //Akkor tudunk törölni, ha a paraméterben kapott film 
        //szerepel a listában, vagyis ezelőtt az adott illető tényleg 
        //kikölcsönözte. Szerencsére a remove ezt ellenőrzi. 
        return films.remove(film);
    }

    public String toString(){
        return firstName+" "+lastName;
    }
 
}


