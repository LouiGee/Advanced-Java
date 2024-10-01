/* import Customer.VMCustomer; */
import Customer.VMCustomer;
import java.util.Scanner;

public class VendingMachine {

    private int currentBalance = 0; //Total amount deposited in current interaction
    private int storedMoney = 100; //Total money in the vending machine
    private boolean lockedAdminAccount = false; //Orignally starts as false and then true if password attempts reached
    private String user;

    public void interactionStart() {

        Scanner readerMainMenu;
        Scanner readerInsertCoin;
        String menuInput;
        String coinInput;
        boolean coinInputComplete = false;
        boolean exit = false;

        user = login();

        if (user.equalsIgnoreCase("customer")) {

            VMCustomer VMCustomer = new VMCustomer();

            while (!exit) {

                //Print menu
                VMCustomer.printMenu();

                //Retrieve user input
                readerMainMenu = new Scanner(System.in);
                menuInput = readerMainMenu.nextLine();

                switch (menuInput) {
                    case "1":

                        readerInsertCoin = new Scanner(System.in);

                        while (!coinInputComplete) {

                            //Print out list of accepted coins
                            VMCustomer.printAcceptedCoins();

                            //Receive input
                            coinInput = readerInsertCoin.nextLine();

                            //Validate input
                            VMCustomer.validateCoinInput(coinInput);

                            //Show balance



                        }



                        }


                }


            }
        }

    private String login() {

        String userInput;
        boolean validInput = false;
        Scanner reader;

        System.out.println("Hello! I am a vending machine");

        while (!validInput) {

            System.out.println("Are you a 'customer' or an 'administrator'? Type 'exit' at anytime to stop interaction.");

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
                System.out.println("Welcome, please choose an option(number) from the following:");

                validInput = true;

                return "customer";
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

                if (lockedAdminAccount) {
                    System.out.println("Admin account is locked. Please contact support.");
                    login();
                }

                administratorAuthentication();

                validInput = true;
                return "administrator";

            }

            else if (userInput.equalsIgnoreCase("exit")) {
                System.out.println("Goodbye!");

                validInput = true;

            }

            else {
                 System.out.println("Oh no! Incorrect input... please try again");
                 //throw new invalidInputException("Oh no! Incorrect input... please try again");
            }


        }
        return "Program terminated.";
    }




    private void administratorAuthentication() {

        Scanner readerAuth;
        String adminPasswordInput;
        String adminPassword = "password";
        boolean validPassword = false;
        Integer passwordAttempts = 0;
        Integer maxLoginAttempts = 3;

        while (!validPassword && !lockedAdminAccount) {

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

            else if (adminPasswordInput.equalsIgnoreCase("exit")) {
                System.out.println("Goodbye!");

                validPassword = true;

            }

            else if (adminPassword != adminPasswordInput ) {

                passwordAttempts++;

                if (passwordAttempts < maxLoginAttempts) {
                    System.out.println("Oops, wrong password! You have " + (maxLoginAttempts - passwordAttempts) + " attempts remaining.");
                }

                if (passwordAttempts >= maxLoginAttempts) {
                    System.out.println("Oops, you have entered the wrong password and you have reached the password attempt limit! Please contact the admin team to reset your password.");
                    lockedAdminAccount = true;

                    interactionStart();
                }

            }




        }
    }
}




            //System.out.println("User input: " + userInput);

