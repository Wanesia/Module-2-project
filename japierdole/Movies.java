import java.util.ArrayList;

public class Movies {
   private String title;
   private ArrayList<Actor> actors;
   private int productionYear;
   private String whenSeen;
   Actor actor=new Actor();
   public void playMovie(String title, ArrayList<Actor> actors){
      System.out.println("Now playing " + title);
      System.out.println(actors+"-"+actor.getRole());
   }
   Movies(){}
   Movies(String title, ArrayList<Actor> actors, int productionYear, String whenSeen){
      this.title = title;
      this.actors = new ArrayList<Actor>();
      this.productionYear = productionYear;
      this.whenSeen = whenSeen;
   }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public ArrayList<Actor> getActors() {
        return actors;
    }


    public void setActors(ArrayList<Actor> actors) {
        this.actors = actors;
    }


    public int getProductionYear() {
        return productionYear;
    }


    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }


    public String getWhenSeen() {
        return whenSeen;
    }


    public void setWhenSeen(String whenSeen) {
        this.whenSeen = whenSeen;
    }

}