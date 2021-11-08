import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;


public class Movie implements Serializable {

////////    ATTRIBUTES
    private String title;
    private ArrayList<Actor> actorsInMovie;
    private String whenSeen;

////////     METHODS

    public Movie createMovie()throws IOException {
        Scanner scan=new Scanner(System.in);
        Movie movie;
        String title;
        System.out.println("Title?");
        title=scan.nextLine();
        movie=new Movie (title,new ArrayList<Actor>());
        return movie;
    }
    public static void addActorToMovie(Actor actor){

    }
////////    toSTRING

    public String toString() {
        return (title+" "+actorsInMovie);
    }

////////    CONSTRUCTOR

    Movie(){}
    Movie(String title){
        this.title = title;
    }
    Movie(String title,ArrayList<Actor> actorsInMovie) {
        this.title=title;
        this.actorsInMovie=actorsInMovie;
    }

////////    SETTERS AND GETTERS

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<Actor> getActorsInMovie() {
        return actorsInMovie;
    }

    public void setActorsInMovie(ArrayList<Actor> actorsInMovie) {
        this.actorsInMovie = actorsInMovie;
    }

    public String getWhenSeen() {
        return whenSeen;
    }

    public void setWhenSeen(String whenSeen) {
        this.whenSeen = whenSeen;
    }

}