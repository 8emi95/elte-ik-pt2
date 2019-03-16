package film;
import java.util.ArrayList;
public class FilmCollection {
    private ArrayList<Film> films;

    public FilmCollection(ArrayList<Film> films) {
        this.films = new ArrayList<Film>(films);
    }

    public FilmCollection() {
        this.films = new ArrayList<Film>();
    }

    public ArrayList<Film> getFilms() {
        return new ArrayList<>(films);
    }

    public boolean addFilm(Film film){
      
        boolean is=false;
        for(Film f: films){
            if(f.equals(film)){
                is=true;
                break;
            }
        }
        if(!is){
            this.films.add(film);
        }
        return !is;
        
    };

    public boolean containsPerson(ArrayList<Person> container, ArrayList<Person> people) {
        for(Person p : container) {
            if(people.contains(p)) {
                return true;
            }
        }
        
        return false;
    }
    
    //Ha több főszereplő is van, elég csak az egyiket megadni
    //Vagyis csak az egyiket kell megadni!
     public ArrayList<Film> findFilms(
            String title,Person director, ArrayList<Person> mainCharacter,
            Integer year, Integer length, DataType type, Boolean isOriginal,
            Boolean isLent){
        
        ArrayList<Film> cont=new ArrayList<>();
        for(Film f:films){
            
            if(title!=null && !title.equals(f.getTitle())) 
                continue;

            if(director!=null && !director.equals(f.getDirector())) 
                continue;
            
            if(mainCharacter!=null && !containsPerson(f.getMainCharacter(), mainCharacter)) 
                continue;
            
            if(year!=null && year!=f.getYear())
                continue;
            
            if(length!=null && length!=f.getLength())
                continue;
            
            if(type!=null && type!=f.getDataType())
                continue;
            
            if(isOriginal!=null && isOriginal!=f.isOriginal())
                continue;
            
            if(isLent!=null && isLent!=f.isLent())
                continue;
            
            cont.add(f);
            
        }
        return cont;
        
    };
     
       /* public ArrayList<Film> findFilms(
            String title,Person director, Person mainCharacter,
            Integer year, Integer length, DataType type, Boolean isOriginal,
            Boolean isLent){
        
        ArrayList<Film> cont=new ArrayList<>();
        for(Film f:films){
            
            if(title!=null && !title.equals(f.getTitle())) 
                continue;

            if(director!=null && !director.equals(f.getDirector())) 
                continue;
            
            if(mainCharacter!=null && !f.getMainCharacter().contains(mainCharacter)) 
                continue;
            
            if(year!=null && year!=f.getYear())
                continue;
            
            if(length!=null && length!=f.getLength())
                continue;
            
            if(type!=null && type!=f.getDataType())
                continue;
            
            if(isOriginal!=null && isOriginal!=f.isOriginal())
                continue;
            
            if(isLent!=null && isLent!=f.isLent())
                continue;
            
            cont.add(f);
            
        }
        return cont;
        
    };*/
    
    
    public boolean deleteFilm(Film film){
        int index=films.indexOf(film);
        if(index!=-1 && !film.isLent()){
            return films.remove(film);
        }
        return false;
    };
    //MEG EZT KELL MÉG ÁTNÉZNI ??????????????????????????????????????????????????????????????????
    public boolean lend(Film film, String lendFrom, String lendTo, Person lendBy){
        if(films.indexOf(film)>-1 && film.lend(lendFrom, lendTo, lendBy)){
            lendBy.borrow(film);
            return true;
        }
        return false;
    }
    
    public boolean getBack(Film film, Person lentBy){
        
        if(film.getLentBy()!=null && film.isLent() && film.getLentBy().equals(lentBy) && film.getLentBy().giveBack(film)){
            film.putBack();
            return true;
        }
        return false;
    }
    
    public ArrayList<Film> getBorrowedFilms(boolean onlyCurrentlyBorrowed) {
        //true -> most van 
        //false -> már volt 
        if(onlyCurrentlyBorrowed) {
            return this.findFilms(null, null, null, null, null, null, null, true);
        }
        ArrayList<Film> bFilms=new ArrayList<>();
        for(Film f:films){
            if(f.getLendNum()>0){
                bFilms.add(f);
            }
        }
        return bFilms;
    }
    
    public boolean remove(Film film){
        return films.remove(film);
    }
     //Ha épp kölcsön van adva a film akkor is törli, de akkor a kölcsönző vessen magára. 
    //Aki kikölcsönözte azé is marad a film ha nem hozza vissza 
    public void panic(){
      ArrayList<Film> notOriginalFilms=
              findFilms(null,null,null,null,null,null,false,null);
        for(Film notOF:notOriginalFilms){
            this.remove(notOF);
        }
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for(Film film : this.films) {
                sb.append(film.toString()+"\n");
        }

        return sb.toString();
    }
}
