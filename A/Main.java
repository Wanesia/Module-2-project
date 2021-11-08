import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main{
    public static void main(String[] args)throws ClassNotFoundException, IOException {
////////    Creating objects
        ArrayList<Actor> actors = new ArrayList<Actor>();

        ArrayList<Movie> movies = new ArrayList<Movie>();
        ArrayList<Movie> favMovies = new ArrayList<Movie>();

        boolean running = true;//condition to keep program running

////////    Registration or login
        User user = new User();
        String userInput;

        Scanner scan = new Scanner(System.in).useDelimiter("\n");
        while(running){
            System.out.println("Type register or login");
            while (true){
                userInput=scan.next();

                if (userInput.equals("register")){
                    user.register(user);
                    user.createRegisterFile();
                    user.createFavListFile();
                    user.createWatchListFile();
                }
                else if (userInput.equals("login")){
                    user.login(user, userInput);
                    while(running){
                        System.out.println("Pick section or type exit to close program");
                        System.out.println("1-Movies");
                        movies=deserializeMovie("Movies/listOfMovies.bin");
                        userInput=scan.next();
                        if (userInput.equals("1")){
                            //Available decisions
                            System.out.println("What would you like to do?");
                            System.out.println("1-Add movie     2-Delete movie      3-Display Movie");
                            System.out.println("4-Favorite movies   5-Watching history");
        
                            userInput=scan.next();
                            if (userInput.equals("1")){
                                String title;
                                System.out.println("Title?");
                                title = scan.next();
                                Movie mtemp= new Movie(title);
                                //After creating movie with title user can chose to add actors
                                System.out.println("Would you like to add actors now? yes/no");
        
                                userInput=scan.next();
                                if (userInput.equals("yes")){
                                    System.out.println("How many?");
                                    int howMany = scan.nextInt();
                                    String name,role;
                                    for(int i=0;i<howMany;i++){//loop to save as many actors as user wanted to
                                        System.out.println("Name?");
                                        name=scan.next();
                                        System.out.println("Role?");
                                        role=scan.next();
                                        Actor atemp=new Actor (name, role);
                                        actors.add(atemp);//adding created actor to array
                                    }
                                }
                                mtemp.setActorsInMovie(actors);//
                                movies.add(mtemp);
                                serializeMovie(movies,"Movies/listOfMovies.bin");
                                
                                //movies=deserializeMovie("Movies/listOfMovies.bin");
                                System.out.println("Done");
                                //Movie serialized with title and(or without)actors
                                
                                //System.out.println(movies);
        
                            }
                            else if(userInput.equals("2")){
                                System.out.println("What movie you would like to delete?");
                                String title=scan.next();
                            
                                for(int i=0; i<movies.size();i++){//looping through array to find match
                                    String deleteTitle=movies.get(i).getTitle();
                                    if(deleteTitle.equals(title)){
                                        movies.remove(i);
                                        serializeMovie(movies,"Movies/listOfMovies.bin");//saving changes
                                        System.out.println("Deleted");
                                        break;
                                    }
                                    else if(deleteTitle.equals(null)){
                                        System.out.println("Error! Movie not found");
                                    }
                                    
                                }
                            }
                            else if (userInput.equals("3")){
                                System.out.println("Movie list");
                                movies=deserializeMovie("Movies/listOfMovies.bin");
                                for(int i=0; i<movies.size();i++){
                                    System.out.println("\n"+movies.get(i).getTitle()+"\nStarring:");
                                    //prints all actors for every movie
                                    for(int j=0; j<movies.get(i).getActorsInMovie().size();j++){
                                        System.out.println(movies.get(i).getActorsInMovie().get(j));
                                    }
                                }
                            }
                            else if (userInput.equals("4")){
                                System.out.println("Favorite movie list");
                                System.out.println("1-Add   2-Delete    3-Display");
                                userInput=scan.next();
                                if (userInput.equals("1")){
                                    System.out.print("Add your favorite movie from movies list");
                                    System.out.println("Movie title? ");
                                    String title=scan.next();
                                    movies=deserializeMovie("Movies/listOfMovies.bin");//gets data from file
                                    for (int i =0; i<movies.size();i++){
                                        if(title.equals(movies.get(i).getTitle())){
                                            //if searched movie is in database then adds it to favorite list
                                            favMovies.add(movies.get(i));
                                        }
                                    }
                                    System.out.println(user.getUsername());
                                    serializeMovie(favMovies,"favList/"+user.getUsername()+".bin");//updates
                                }
                                if(userInput.equals("2")){
                                    System.out.println("Search using title or actor? ");//similar to above but remove instead of add
                                    userInput=scan.next();
                                    if(userInput.equals("title")){//user can search for movie to delete using title or actor
                                        String userSearch=scan.next();
                                        String searchedInput;
                                        for(int i=0; i<favMovies.size();i++){
                                            searchedInput=favMovies.get(i).getTitle();

                                            if(!searchedInput.equals(userSearch)){
                                                System.out.println("cant find");
                                                break;
                                            }
                                            else{
                                                favMovies.remove(i);
                                                System.out.println("Deleted");
                                                serializeMovie(favMovies,"favList/"+ user.getUsername()+".bin");
                                            }
                                        }
                                    }
                                    else if(userInput.equals("actor")){
                                        String userSearch = scan.nextLine();
                                        for(int i=0; i<favMovies.size();i++){
                                            for(int j=0; j<favMovies.get(i).getActorsInMovie().size();i++)
                                                if((favMovies.get(i).getActorsInMovie().get(j).equals(userSearch))){
                                                    favMovies.remove(i);
                                                    System.out.println("Deleted");
                                                    serializeMovie(favMovies, "favList/"+ user.getUsername()+".bin");
                                                }
                                        }
                                    }
                                    else{
                                        System.out.println("Error! Movie not found");
                                    }
                                }
                                else if (userInput.equals("3")){
                                    System.out.println("Your favorite movie list:\n");
                                    favMovies=deserializeMovie("favList/"+ user.getUsername()+".bin");
                                    if(favMovies!=null){
                                        for (int i=0; i<favMovies.size(); i++){
                                            //loops through whole array to print all titles
                                            System.out.println(favMovies.get(i).getTitle());
                                        }
                                    }
                                    System.out.println("\nEnd of list");
                                }
                            }
                            else{
                                System.out.println("Something went wrong. Please try again");
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

////////    User picks what to do

////////    METHODS

    public void erase(){
        try{
        PrintWriter writer = new PrintWriter("a");
        writer.print("");
        writer.close();
        }
        catch(Exception e){
            System.out.println(e);
        }

    }
    public static void serializeMovie(ArrayList<Movie> obj,String path){
        try{
            FileOutputStream fos = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(obj);

            oos.close();
            fos.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    public static ArrayList<Movie> deserializeMovie(String path)throws ClassNotFoundException{
        try{
            ArrayList<Movie> obj;
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fis);
            obj=(ArrayList<Movie>) ois.readObject();
            ois.close();
            fis.close();
            return obj;
        }
        catch(IOException e){
            e.printStackTrace();
            System.out.println("Its empty");
            return null;
        }
        catch(ClassNotFoundException e){
            System.out.println("Class not found: ");
            e.printStackTrace();
            return null;
        }

    }
    public static void fileWriteActor(ArrayList<Actor> obj) {
        try{
            FileOutputStream fos = new FileOutputStream("actorsAndRoles.bin");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(obj);
            System.out.println(obj);
            oos.close();
            fos.close();
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    public static ArrayList<Actor> fileReadActor(){
        try
        {   ArrayList<Actor> obj = new ArrayList<Actor>();
            FileInputStream fis = new FileInputStream("Movies/listOfMovies.bin");
            ObjectInputStream ois = new ObjectInputStream(fis);
 
            obj = (ArrayList) ois.readObject();
 
            ois.close();
            fis.close();
            System.out.println(obj);
            return obj;

        } 
        catch (IOException ioe) 
        {
            ioe.printStackTrace();
            return null;
        } 
        catch (ClassNotFoundException c) 
        {
            System.out.println("Class not found");
            c.printStackTrace();
            return null;
        }
    }
}