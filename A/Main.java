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
        ArrayList<Actor> actors = new ArrayList<Actor>();
        ArrayList<Actor> actorsTemp = new ArrayList<Actor>();
        ArrayList<Actor> actors3 = new ArrayList<Actor>();
        Actor a= new Actor();
        Actor a1 = new Actor("wanesa","win");
        Movie movie = new Movie();
       
        ArrayList<Movie> movies = new ArrayList<Movie>();
        ArrayList<Movie> moviesTemp = new ArrayList<Movie>();
        Actor actor = new Actor();
        boolean running = true;
        
       /* a=Actor.createActor();
        actors.add(a1);
        actors.add(a);
        System.out.println(actors);
        Main.fileWriteActor(actors);
        //asdasdasd
        actorsTemp=Main.fileReadActor();
        a=Actor.createActor();
        actorsTemp.add(a);
        Main.fileWriteActor(actorsTemp);
        //uuuu
        actors3=Main.fileReadActor();
        System.out.println("result: " + actors3);*/
        ///////
       /* actors=Main.fileReadActor();
        System.out.println("result: " + actors);
        System.out.println("oto movie "+movie);
        movies=Main.fileReadMovie();
        //movies.add(movie);
        //Main.fileWriteMovie(movies);
        System.out.println(movies);
        movies.get(1).setActorsInMovie(actors);
        System.out.println(movies.get(1));
        Main.fileWriteMovie(movies);
        System.out.println(actors);*/
        User user = new User();
        Scanner scan = new Scanner(System.in).useDelimiter("\n");
        while(running){
            System.out.println("Type register or login");
            String userInput=scan.next();
            if (userInput.equals("register")){
                user.register(user);
                user.userCreateFile();
            }
            else if (userInput.equals("login")){
                user.login(user, userInput);
            }
            else{
                System.out.println("Something went wrong. Please try again");
            }
            System.out.println("Pick section or type exit to close program");
            System.out.println("1-Movie");
            try{
                FileInputStream fis =new FileInputStream("Movies/listOfMovies.bin");
                ObjectInputStream ois = new ObjectInputStream(fis);
                movies=(ArrayList)ois.readObject();
            }
            catch(IOException e){
                e.printStackTrace();
            }
            System.out.println(movies);

            userInput=scan.next();
            if (userInput.equals("1")){
                System.out.println("What would you like to do?");
                System.out.println("1-Add movie     2-Delete movie      3-Display Movie");
                System.out.println("4-Search for movie     5-Favorite Movies      6-Watching history");

                userInput=scan.next();
                if (userInput.equals("1")){
                    String title;
                    System.out.println("Title?");
                    title = scan.next();
                    Movie mhelp= new Movie(title);
                    System.out.println("Would you like to add actors now? yes/no");

                    userInput=scan.next();
                    if (userInput.equals("yes")){
                        System.out.println("How many?");
                        int howMany = scan.nextInt();
                        String name,role;
                        for(int i=0;i<howMany;i++){
                            System.out.println("Name?");
                            name=scan.next();
                            System.out.println("Role?");
                            role=scan.next();
                            Actor ahelp=new Actor (name, role);
                            actors.add(ahelp);
                        }
                        mhelp.setActorsInMovie(actors);
                        movies.add(mhelp);
                        System.out.println(movies);
                    }
                    System.out.println(movies);
                    try{
                        FileOutputStream fos = new FileOutputStream("Movies/listOfMovies.bin");
                        ObjectOutputStream oos = new ObjectOutputStream(fos);
                        oos.writeObject(movies);
                        
                    }
                    catch(IOException e){
                        e.printStackTrace();
                    }
                    try{
                        FileInputStream fis =new FileInputStream("Movies/listOfMovies.bin");
                        ObjectInputStream ois = new ObjectInputStream(fis);
                        movies=(ArrayList)ois.readObject();
                    }
                    catch(IOException e){
                        e.printStackTrace();
                    }
                    //fileWriteActor(actors);
                    //fileWriteMovie(movies);
                    System.out.println("Done");
                    //System.out.println(movies);

                }
                else if(userInput.equals("2")){
                    System.out.println("What movie you would like to delete?");
                    String title=scan.next();
                    for(int i=0; i<movies.size();i++){
                        
                        if((movies.get(i).getTitle().equals(title))){
   //AAAAAAAAAAAAAAA                         //cos tam delete
                            movies.remove(i);
                            try{
                                FileOutputStream fos = new FileOutputStream("Movies/listOfMovies.bin");
                                ObjectOutputStream oos = new ObjectOutputStream(fos);
                                oos.writeObject(movies);
                                
                            }
                            catch(IOException e){
                                e.printStackTrace();
                            }
                        }
                        else{
                            System.out.println("Error! Movie not found");
                        }
                        
                    }
                }
                else if (userInput.equals("3")){
                    System.out.println("Movie list");
                    movies=fileReadMovie();

                    for(int i=0; i<movies.size();i++){
                        System.out.println("\n"+movies.get(i).getTitle()+"\nStarring:");
                        
                        for(int j=0; j<movies.size();j++){
                            System.out.println(movies.get(i).getActorsInMovie().get(j));
                        }
                    }
                    
                }
            }
            else if (userInput.equals("exit")){
                System.out.println("Thank you for using program! See you soon :)");
                running = false;
                break;
            }
        }



    }
    public void erase(){
        try{
        PrintWriter writer = new PrintWriter("a");
        writer.print("");
        writer.close();
        }
        catch(Exception e){
            System.out.println("asdasdasd"+e);
        }

    }
    public static void fileWriteMovie(ArrayList<Movie> obj){
        try{
            FileOutputStream fos = new FileOutputStream("Movies/listOfMovies.bin");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(obj);

            oos.close();
            fos.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    public static ArrayList<Movie> fileReadMovie()throws ClassNotFoundException{
        try{
            ArrayList<Movie> obj;
            FileInputStream fis = new FileInputStream("Movies/listOfMovies.bin");
            ObjectInputStream ois = new ObjectInputStream(fis);
            obj=(ArrayList<Movie>) ois.readObject();
            ois.close();
            fis.close();
            return obj;
        }
        catch(IOException e){
            e.printStackTrace();
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