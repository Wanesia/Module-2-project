import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
public class Main2 {
    public static void main(String[] args) throws Exception {
        ArrayList<Actor> namesList = new ArrayList<Actor>();
        Actor a1=new Actor("Wanesia","aaaa");
        Actor a2 = new Actor("asd","123");
        namesList.add(a1);
        namesList.add(a2);
        try{
            FileOutputStream fos = new FileOutputStream("Movies/listOfMovies.bin");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(namesList);
            oos.close();
            fos.close();
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }

        //aaaaaaaaaaaaaaaaaaaaa

        try
        {
            FileInputStream fis = new FileInputStream("Movies/listOfMovies.bin");
            ObjectInputStream ois = new ObjectInputStream(fis);
 
            namesList = (ArrayList) ois.readObject();
 
            ois.close();
            fis.close();
        } 
        catch (IOException ioe) 
        {
            ioe.printStackTrace();
            return;
        } 
        catch (ClassNotFoundException c) 
        {
            System.out.println("Class not found");
            c.printStackTrace();
            return;
        }
        System.out.print(namesList);

    }
}