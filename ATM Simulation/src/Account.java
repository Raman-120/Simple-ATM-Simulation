import java.io.Serial;
import java.io.Serializable;
import java.util.Scanner;

public class Account implements Serializable{

    @Serial
    private static final long serialVersionUID = 1L;
    private Scanner scanner;


    private String cardNumber;
    private String pin;
    private double balance;

    Account(String cardNumber, String pin, double balance){
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.balance = balance;
    }

    public String getCardNumber(){
        return this.cardNumber;
    }

    public String getPin(){
        return this.pin;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance){
        this.balance = balance;
    }
}
