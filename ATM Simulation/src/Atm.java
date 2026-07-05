import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Atm {

    private Scanner scanner;
    private File file = new File("Users.ser");
    private ArrayList<Account> accounts;
    private Account loggedInAccount;
    private double amount;

    Atm(Scanner scanner, Account loggedInAccount){
        this.scanner = scanner;
        this.loggedInAccount = loggedInAccount;
    }

    public void showMenu(){
        System.out.println("1. Balance Inquiry");
        System.out.println("2. Cash Withdrawal");
        System.out.println("3. Deposit");
        System.out.println("4. Money Transfer");
        System.out.println("5. Change Pin");
        System.out.println("6. Transaction History");
    }

    public void atmSimulation(){
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice){
            case 1 -> System.out.println(loggedInAccount.getBalance());

            case 2 -> withdraw();

            case 3 -> deposit();

            case 4 -> MoneyTransfer();

            case 5 -> pin();

        }
    }


    public void withdraw(){
        System.out.print("Enter the amount you wanna withdraw: ");
        amount = scanner.nextDouble();
        scanner.nextLine();

        if(amount <= 0){
            System.out.println("Amount can't be in negative and zero.");
        }
        else if(amount > loggedInAccount.getBalance()){
            System.out.println("Insufficient Balance.");
        }
        else{
            loggedInAccount.setBalance(loggedInAccount.getBalance() - amount);
            System.out.println("You have successfully withdrawn " +  amount);
            System.out.println("Your new balance is " + loggedInAccount.getBalance());
        }

        saveUpdatedAccount();

    }

    public void deposit(){
        System.out.print("Enter the amount you wanna deposit: ");
        amount = scanner.nextDouble();
        scanner.nextLine();

        if(amount <= 0 ){
            System.out.println("Amount can't be in negative and zero.");
        }
        else{
            loggedInAccount.setBalance(loggedInAccount.getBalance() + amount);
            System.out.println("You have successfully deposit " + amount);
            System.out.println("Your new balance is " + loggedInAccount.getBalance());
        }

        saveUpdatedAccount();
    }

    public void MoneyTransfer(){

        System.out.print("Enter the receiver's account or card number: ");
        String cardNumber = scanner.nextLine().trim();

        accounts = loadAccounts();

        System.out.print("Enter the amount you wanna transfer: ");
        amount = scanner.nextDouble();
        scanner.nextLine();

        Account receiver = null;
        for(Account account: accounts){
            if(account.getCardNumber().equals(cardNumber)){
                receiver = account;
                break;
            }
        }

        if(receiver == null){
            System.out.println("Receiver not found.");
            return;
        }

        loggedInAccount.setBalance(loggedInAccount.getBalance() - amount);

        receiver.setBalance(receiver.getBalance() + amount);

        for(int i = 0; i < accounts.size(); i++){
            if(accounts.get(i).getCardNumber().equals(loggedInAccount.getCardNumber())){
                accounts.set(i, loggedInAccount);
                break;
            }
        }

        saveAccounts(accounts);


    }

    public String pin(){
        System.out.print("Enter your new pin: ");
        return scanner.nextLine();
    }

    @SuppressWarnings("unchecked")
    private void saveUpdatedAccount(){
        accounts = loadAccounts();
        boolean found = false;

        for(int i = 0; i < accounts.size(); i++){
            if(accounts.get(i).getCardNumber().equals(loggedInAccount.getCardNumber())){
                accounts.set(i,loggedInAccount);
                found = true;
                break;
            }

        }
        if(!found){
            accounts.add(loggedInAccount);
        }

        saveAccounts(accounts);

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
