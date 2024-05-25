/* "Your name"
 * "Class and period"
 * "Date"
 */

package main.java;
import java.util.ArrayList;
import java.util.Scanner;

class Main {
    private static final Scanner scanner = new Scanner(System.in);   // Creates a scanner to read inputs
    private static ArrayList<Person> people = new ArrayList<>();
    private static Person currentPerson;
    private static double balance;
    
    public static void main(String[] args) {
        boolean run = true;
        while (run) {
            System.out.println("\nWelcome to my Application!");
            System.out.println("Please select an option:");
            System.out.println("1. login");
            System.out.println("2. create an account");
            System.out.println("3. Quit\n");

            String option = scanner.nextLine();     // Takes input from user
            switch(option) {        // Switch statement for options
                case "1":
                    login();
                    break;
                case "2":
                   createAccount();
                break;
                case "3":
                    System.out.println("Application is closing now. Bye!");
                    run = false;
                    break;
                default:
                    System.out.println("Option is invalid. Try again");
            }
        }
    }

    

        public static boolean login(){
        System.out.println("Enter your name: ");
        String name =scanner.nextLine();
        System.out.println("Enter your pin: ");
        int pin = scanner.nextInt();
        scanner.nextLine();


        for (Person person:people){
            if (person.getName().equals(name)){
                currentPerson=person;
                break;
            }
        }
        if (currentPerson==null){
            System.out.println("Invalid name");
            return false;
        }

        if (currentPerson.getPin()!=pin){
            System.out.println("Invalid pin");
            currentPerson=null;
            return false;
        }
        else{
            accountMainMenu();
            return true;
        }
    }

    public static void createAccount(){
        System.out.println("Enter your name;");
        String name = scanner.nextLine();
        System.out.println("Enter your pin;");
        int pin = scanner.nextInt();
        scanner.nextLine();

       
    // Person newPerson = new Person(name, pin);
       people.add(new Person(name, pin, balance));
       System.out.println("Created your account successfully!");

    }
    public static void accountMainMenu(){
        boolean run1 = true;
        while (run1) {

      System.out.println("welcome to your account");
      System.out.println("1. withdraw");
      System.out.println("2. deposit");
      System.out.println("3. check balance");
      System.out.println("4. change pin");
      System.out.println("5. logout");
      System.out.println("6. switch the account");
      System.out.println("select an option: ");

      String option = scanner.nextLine();
      switch(option) {        // Switch statement for options
                case "1":
                System.out.println("Enter the amount to withdraw");
                    double amount= scanner.nextDouble();
                    scanner.nextLine();
                    currentPerson.withdraw(amount);
                    accountMainMenu();
                    break;
                case "2":
                System.out.println("Enter the amount to deposit");
                    double depositAmount = scanner.nextDouble();
                    scanner.nextLine();
                    currentPerson.deposit(depositAmount);
                    accountMainMenu();
                    break;
                case "3":
                currentPerson.checkBalance();
                System.out.println("Your balane is"+currentPerson.checkBalance());
                accountMainMenu();

                break;
                 case "4":
                 System.out.println("Enter your new pin");
                 int newPin = scanner.nextInt();
                 scanner.nextLine();
                 currentPerson.changePin(newPin);
                 accountMainMenu();
                 break;
                 case "5":
                 System.out.println("You successfully logged out!");
                 main(null);
                 break;
                 case "6":
                    return; 

                    default:
                    System.out.println("Invalid choice. Please try again.");
            }
    }
    
}
}