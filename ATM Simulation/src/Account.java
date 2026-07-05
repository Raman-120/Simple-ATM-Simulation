import java.io.File;
import java.io.Serializable;
import java.util.Scanner;

public class Account implements Serializable{

    private Scanner scanner;
    private File file = new File("Users.ser");

    private String cardNumber;
    private String pin;

    private String getCardNumber(){
        return this.cardNumber;
    }

    private String getPin(){
        return this.pin;
    }


}
