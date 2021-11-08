import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Actor implements Serializable {

////////    ATTRIBUTES
    private String name;
    private String role;

////////    METHODS
    public void movieList(){
        Scanner scan = new Scanner(System.in);
        String decision=scan.nextLine();
        if (decision.equals("1")){
            System.out.println("List of your favorite movies:");

        }
    }

    public Actor createActor()throws IOException {
        Scanner scan=new Scanner(System.in);
        Actor a;
        String name,role;
        System.out.println("Name?");
        name=scan.nextLine();
        System.out.println("Role?");
        role=scan.nextLine();
        a=new Actor (name,role);
        return a;
    }

////////    CONSTRUCTOR
    Actor(){}
    Actor(String name, String role) {
        this.name = name;
        this.role = role;
    }
    public String toString(){
        return name + " - "+role;
    }

////////    SETTERS AND GETTERS

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
