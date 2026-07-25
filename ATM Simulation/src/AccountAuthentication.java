import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class AccountAuthentication implements Serializable {

    public static final String ACCOUNTS_FILE = "Accounts.ser";

    private final Scanner scanner;
    private String cardNumber;
    private String pin;
    private double balance;
    private Account currentAccount;
    public File file = new File(ACCOUNTS_FILE);

    AccountAuthentication(Scanner scanner){
        this.scanner = scanner;
    }

    public void createAccount(){
        Random random = new Random();
        ArrayList<Account> accounts = loadAccounts();
        cardNumber = "";
        int i = 0;
        while(i < 4){
            int intValue = random.nextInt(10);

            cardNumber += String.valueOf(intValue);
            i++;
        }

        System.out.println("Your atm card number is: " + cardNumber);

        System.out.print("Set up your pin: ");
        pin = scanner.nextLine();

        System.out.println("You have successfully created an Atm card.");

        Account newAccount =new Account(cardNumber, pin, balance);
        accounts.add(newAccount);
        saveAccounts(accounts);
        System.out.println("Information saved successfully!");
    }

    public void Login(){

        ArrayList<Account> accounts = loadAccounts();

        System.out.print("Enter your card number: ");
        cardNumber = scanner.nextLine();

        System.out.print("Enter your pin: ");
        pin = scanner.nextLine();

        for(Account account: accounts){
            if(account.getCardNumber().equals(cardNumber) && account.getPin().equals(pin)){
                System.out.println("Login successfully.");
                currentAccount = account;
                return;
            }
        }
        currentAccount = null;
        System.out.println("Invalid credentials.");

    }

    public Atm getAtm(){
        if(currentAccount == null){
            System.out.println("No user is logged in.");
            return null;
        }
        else {
            return new Atm(scanner, currentAccount);
        }

    }


    @SuppressWarnings("unchecked")
    private ArrayList<Account> loadAccounts(){
        if(!file.exists()){
            return new ArrayList<>();
        }
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))){
            Object object = ois.readObject();
            if(object == null){
                return new ArrayList<>();
            }
            return (ArrayList<Account>) object;
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
            System.out.println("Unable to write." +  e.getMessage());
        }
    }
}
