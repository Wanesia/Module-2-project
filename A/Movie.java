import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;


public class Movie implements Serializable {
    private String title;
    private ArrayList<Actor> actorsInMovie;
    private String whenSeen;


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
    public String toString() {
        return (title+" "+actorsInMovie);
    }
    Movie(){}
    Movie(String title){
        this.title = title;
    }
    Movie(String title,ArrayList<Actor> actorsInMovie) {
        this.title=title;
        this.actorsInMovie=actorsInMovie;
    }


    /**
     * @return String return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }


    /**
     * @return ArrayList<Actor> return the actorsInMovie
     */
    public ArrayList<Actor> getActorsInMovie() {
        return actorsInMovie;
    }

    /**
     * @param actorsInMovie the actorsInMovie to set
     */
    public void setActorsInMovie(ArrayList<Actor> actorsInMovie) {
        this.actorsInMovie = actorsInMovie;
    }


    /**
     * @return String return the whenSeen
     */
    public String getWhenSeen() {
        return whenSeen;
    }

    /**
     * @param whenSeen the whenSeen to set
     */
    public void setWhenSeen(String whenSeen) {
        this.whenSeen = whenSeen;
    }

}
