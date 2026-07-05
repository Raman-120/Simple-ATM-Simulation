import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class AccountAuthentication implements Serializable {

    ArrayList<Account> accounts;
    private final Scanner scanner;
    private String cardNumber;
    private String pin;
    private double balance;
    Account currentAccount;
    private File file = new File("Account.ser");
    private boolean found = false;

    AccountAuthentication(Scanner scanner){
        this.scanner = scanner;
    }

    public void Login(){

        accounts = loadAccounts();

        System.out.print("Enter your card number: ");
        cardNumber = scanner.nextLine();

        System.out.print("Enter your pin: ");
        pin = scanner.nextLine();

        for(Account account: accounts){
            if(account.getCardNumber().equals(cardNumber) && account.getPin().equals(pin)){
                System.out.println("Login successfully.");
                found = true;
                currentAccount = account;
                return;
            }
        }
        if(!found){
            System.out.println("Invalid credentials.");
        }
    }

    public Atm getAtm(){
        if(currentAccount == null){
            System.out.println("No user is logged in.");
        }
        return new Atm(scanner, currentAccount);
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
