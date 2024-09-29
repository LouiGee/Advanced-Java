import java.util.Scanner;

public class vendingMachine {

    private int currentBalance; //Total amount deposited in current interaction
    private int storedBalance; //Total money in the vending machine
    private String customerOrAdmin;


    public void interactionStart() {

        printWelcome(); //obtains whether user is custom or admin
    }


    private void printWelcome() {

        String userInput;
        boolean validInput = false;
        Scanner reader;

        System.out.println("Hello! I am a vending machine");

        while (!validInput) {

            System.out.println("Are you a 'customer' or an 'administrator'?");

            reader = new Scanner(System.in);
            userInput = reader.nextLine(); //User input stored as a variable


            if (userInput.equalsIgnoreCase("customer")) {
                System.out.println("Loading...");
                try {
                    // Pause the program for 3 seconds (3000 milliseconds)
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    // Handle the exception if the thread is interrupted
                    e.printStackTrace();
                }
                System.out.println("Welcome, please choose an option from the following:");

                validInput = true;
            }


            else if (userInput.equalsIgnoreCase("administrator")) {

                System.out.println("Loading...");

                try {
                    // Pause the program for 3 seconds (3000 milliseconds)
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    // Handle the exception if the thread is interrupted
                    e.printStackTrace();
                }

                adminAuthentication();

                validInput = true;

            } else {
                System.out.println("Oh no! Incorrect input... please try again");
            }


        }
    }


    private void adminAuthentication() {

        Scanner readerAuth;
        String adminPasswordInput;
        String adminPassword = "password";
        boolean validPassword = false;
        Integer passwordAttempts = 0;
        Integer maxAttempts = 3;
        boolean Locked = false;


        while (!validPassword && !Locked) {

            System.out.println("Please enter your administrator password:");

            readerAuth = new Scanner(System.in);
            adminPasswordInput = readerAuth.nextLine();


            if (adminPassword.equals(adminPasswordInput)) {
                System.out.println("Loading...");

                try {
                    // Pause the program for 3 seconds (3000 milliseconds)
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    // Handle the exception if the thread is interrupted
                    e.printStackTrace();
                }

                System.out.println("Welcome, please choose an option from the following:");
                validPassword = true;

            }
            else if ((adminPassword != adminPasswordInput) ) {

                passwordAttempts++;

                if (passwordAttempts < maxAttempts) {
                    System.out.println("Oops, wrong password! You have " + (maxAttempts - passwordAttempts) + " attempts remaining.");
                }

                if (passwordAttempts >= maxAttempts) {
                    System.out.println("Oops, you have entered the wrong password and you have reached the password attempt limit! Please contact admin team to reset your password.");
                    Locked = true;
                }

            }

        }
    }
}




            //System.out.println("User input: " + userInput);

