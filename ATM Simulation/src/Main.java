import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        Atm atm = new Atm(scanner);
        AccountAuthentication accountAuthentication = new AccountAuthentication(scanner);

    }



}
