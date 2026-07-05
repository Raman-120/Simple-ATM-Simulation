import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Atm {

    private Scanner scanner;
    private File file = new File("Users.ser");
    private ArrayList<Account> accounts;
    private Account loggedInAccount;

    Atm(Scanner scanner, Account loggedInAccount){
        this.scanner = scanner;
        this.loggedInAccount = loggedInAccount;
    }


    @SuppressWarnings("unchecked")
    private ArrayList<Account> loadAccounts(){
        if(!file.exists()){
            return new ArrayList<>();
        }
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))){
            return (ArrayList<Account>) ois.readObject();
        }
        catch (Exception e){
            System.out.println("Unable to read the file.");
            System.out.println("Error: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    private void saveAccounts(ArrayList<Account> accounts){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))){
            oos.writeObject(accounts);
        }catch (IOException e){
            System.out.println("Unable to write.");
        }
    }
}
