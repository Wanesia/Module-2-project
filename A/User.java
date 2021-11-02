import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.IOException;
import java.util.Scanner;

public class User {
    private String username;
    private String mail;
    private String password;
    Scanner scan= new Scanner(System.in);

    //clears screen using ANSI C
    public void cls(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    public void register(User user){
        user.cls();
        System.out.println("    REGISTER");
        System.out.print("Username:");
        user.setUsername(scan.next());
        System.out.print("Mail:");
        user.setMail(scan.next());
        System.out.print("Password:");
        user.setPassword(scan.next());
        System.out.println("Account created successfully");
    }
    public void login(User user,String userInput){
        user.cls();
        System.out.println("    LOGIN");

        System.out.print("Username:");
        userInput=scan.next();
        try{
            File f= new File("users/"+userInput+".txt");
            Scanner scanFile = new Scanner(f);
            String line=scanFile.nextLine();

            if (userInput.equals(line)){
                System.out.print("Password:");
                scanFile.nextLine();
                line=scanFile.nextLine();
                userInput=scan.next();
                    if (userInput.equals(line))
                    {
                        System.out.println("You have successfully logged in!");
                    }
                    else{
                        System.out.println("Login failed");
                    }
            }
            scanFile.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Username not found");
        }
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
