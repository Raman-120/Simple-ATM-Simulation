import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Atm {

    private Scanner scanner;
    private File file = new File("Users.ser");
    ArrayList<Account> accounts;

    Atm(Scanner scanner){
        this.scanner = scanner;
    }

    public void Login(){

        accounts = loadAccounts();
        System.out.print("Enter your card number: ");
        String cardNumber = scanner.nextLine();

        System.out.print("Enter your pin: ");
        String pin = scanner.nextLine();
        boolean found = false;

        for(Account account : accounts){
            if(account.getCardNumber().equals(cardNumber) && account.getPin().equals(pin)){
                System.out.println("You are logged in.");
                found = true;
                break;
            }
        }

        if(!found){
            System.out.println("It looks like you are not registered.");
        }

        else{

        }

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
