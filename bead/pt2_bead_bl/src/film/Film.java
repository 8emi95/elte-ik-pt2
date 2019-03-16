package film;

import java.util.ArrayList;

public class Film {
    private int ID;
    private String title;
    private Person director;
    private ArrayList<Person> mainCharacter;
    private int year;
    private int length;
    private DataType dataType;
    private String image;  //???
    private boolean isOriginal;
    private int lendNum; //kölcsönadás száma
    
    private boolean isLent=false; 
    private String lendFrom;
    private String lendTo;
    private Person lentBy;

    public Film(Integer id, String title,Person director, ArrayList<Person> mainCharacter, int year, int length, DataType dataType, String image, boolean isOriginal
        ,int lendNun,boolean isLent,String lendFrom, String lendTo, Person lentBy) {
        
        this.ID= id == null ? FilmsMain.getNextIdForFilms() : id;
        this.title=title;
        this.director = director;
        this.mainCharacter = mainCharacter;
        this.year = year;
        this.length = length;
        this.dataType = dataType;
        this.image = image;
        this.isOriginal = isOriginal;
        this.lendNum = lendNun;
        this.isLent = isLent;    
        this.lendFrom=lendFrom;
        this.lendTo=lendTo;
        this.lentBy=lentBy;
    }

    public int getID() {
        return ID;
    }

    public String getTitle() {
        return title;
    }
 
    public Person getDirector() {
        return director;
    }

    public ArrayList<Person> getMainCharacter() {
        return mainCharacter;
    }

    public int getYear() {
        return year;
    }

    public int getLength() {
        return length;
    }

    public DataType getDataType() {
        return dataType;
    }

    public String getImage() {
        return image;
    }

    public boolean isOriginal() {
        return isOriginal;
    }

    public int getLendNum() {
        return lendNum;
    }

    public boolean isLent() {
        return isLent;
    }

    public void putBack(){
     isLent=false; 
     lendFrom=null;
     lendTo=null;
     lentBy=null;
    }

    public String getLendFrom() {
        return lendFrom;
    }

    public String getLendTo() {
        return lendTo;
    }

    public Person getLentBy() {
        return lentBy;
    }
    public boolean lend(String lendFrom, String lendTo, Person lentBy){
        if(isLent) return false;
        this.isLent=true;
        this.lendFrom=lendFrom;
        this.lendTo=lendTo;
        this.lentBy=lentBy;
        this.lendNum++;
        return true;
    }
    
    public boolean equals(Object obj){
        if(this==obj) return true;
	if (obj==null) return false;
        if (this.getClass() != obj.getClass()) return false;
        Film f=(Film)obj;
        return f!=null && title.equals(f.title) && director.equals(f.director);
    }
    
    public String toString(){
     return "Cím: "+title+ "..Rendező: "+ director.toString()+" ... ";
    }
}
