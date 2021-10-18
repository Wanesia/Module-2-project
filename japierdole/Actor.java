import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class Actor {
    private String name;
    private String role;
    public void addActor(String name, String role)throws IOException {
        File file = new File("ActorsAndRoles.txt");
        FileOutputStream os = new FileOutputStream(file,true);
        PrintStream out = new PrintStream(os);
        out.println(name);
        out.println(role);
        out.close();

    }
    Actor(){

    }
    Actor(String name, String role) {
        this.name = name;
        this.role = role;
    }

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
