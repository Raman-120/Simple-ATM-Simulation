import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        AccountAuthentication accountAuthentication = new AccountAuthentication(scanner);


        int choice = 0;
        System.out.print("Do you have an card in our bank?: ");
        String response = scanner.nextLine();

        if(response.equals("no")){
            accountAuthentication.createAccount();
        }

        accountAuthentication.Login();
        Atm atm = accountAuthentication.getAtm();

        if(atm == null){
            System.out.println("Atm is null");
            return;
        }

        do{
            atm.showMenu();
            atm.atmSimulation();
        }while (choice != 7);






    }



}
