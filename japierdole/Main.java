import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main{

    public static void main(String[] args)throws IOException{
        Actor actor = new Actor();
        actor.addActor("AAA","BBB");
        User user = new User();
        Scanner scan = new Scanner(System.in);
        while(true){
            System.out.println("Type register or login");
            String userInput=scan.next();
            if (userInput.equals("register")){
                user.cls();
                System.out.println("    REGISTER");
                System.out.print("Username:");
                user.setUsername(scan.next());
                System.out.print("Mail:");
                user.setMail(scan.next());
                System.out.print("Password:");
                user.setPassword(scan.next());
                user.userCreateFile();

            }
            else if (userInput.equals("login")){
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
                                System.out.println("You have successfully logged in");
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
            else{
                System.out.println("Something went wrong. Please try again");
            }

        }
    }
}