import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.IOException;

public class User {
    private String username;
    private String mail;
    private String password;

    //clears screen using ANSI C
    public void cls(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void userCreateFile()throws IOException {
        File file = new File("users/"+this.username+".txt");
        FileOutputStream os = new FileOutputStream(file,true);
        PrintStream out = new PrintStream(os);
        out.println(this.username);
        out.println(this.mail);
        out.println(this.password);
        out.close();
    }


    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public String getMail() {
        return mail;
    }


    public void setMail(String mail) {
        this.mail = mail;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }

}
