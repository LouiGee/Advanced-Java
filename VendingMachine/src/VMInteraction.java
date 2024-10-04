import Customer.VMCustomer;
import LoadingMessage.LoadingBuffer;
import VMParent.VM;

import java.util.Scanner;


public class VMInteraction {


    public void start() {

        // Trigger login and determine whether user is a 'customer' or an 'administrator'
        String user;
        user = login();

        if (user.equalsIgnoreCase("customer")) {

            VMCustomer VMCustomer = new VMCustomer();

            boolean exit = false;
            while (!exit) {

                //Print menu
                LoadingBuffer.loading();
                VMCustomer.printMenu();

                //Retrieve user input
                Scanner readerMainMenu;
                String menuInput;
                readerMainMenu = new Scanner(System.in);
                menuInput = readerMainMenu.nextLine();

                switch (menuInput) {

                    case "1":

                        boolean coinInputComplete = false;
                        while (!coinInputComplete) {

                            //Print out list of accepted coins
                            LoadingBuffer.loading();
                            VMCustomer.printAcceptedCoins();

                            //Receive input
                            String coinInput;
                            Scanner readerInsertCoin;
                            readerInsertCoin = new Scanner(System.in);
                            coinInput = readerInsertCoin.nextLine();

                            //Validate input and show balance
                            if (VMCustomer.isValidCoin(coinInput)) {
                                LoadingBuffer.loading();
                                System.out.println("Coin accepted.");

                                //Add coin to current balance
                                VMCustomer.setCurrentBalance(VMCustomer.getCurrentBalance() + Double.parseDouble(coinInput));

                                //Print current balance
                                double currentBalance;
                                currentBalance = VMCustomer.getCurrentBalance();
                                System.out.println("Current balance: " + currentBalance);

                            } else if (coinInput.equalsIgnoreCase("done")) {
                                coinInputComplete = true;
                            } else {
                                LoadingBuffer.loading();
                                System.out.println("Unrecognised coin. Coin returned.");
                            }
                            break;
                        }
                    case "3":

                        System.out.println("Balance: £" + VMCustomer.getCurrentBalance());
                        break;

                    case "6":

                        System.out.println("A balance of £" + VMCustomer.getCurrentBalance() + " has been refunded.");
                        // Reset balance
                        VMCustomer.setCurrentBalance(0);
                        break;

                    }

                }

            }

        else if (user.equalsIgnoreCase("administrator")) {


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

                validInput = true;

                return "customer";
            }

            else if (userInput.equalsIgnoreCase("administrator")) {

                LoadingBuffer.loading();

                if (VM.isLockedAdminAccount()) {

                    System.out.println("Admin account is locked. Please contact support.");

                    LoadingBuffer.loading();

                    login();
                }

                administratorAuthentication();

                validInput = true;
                return "administrator";

            }

            else if (userInput.equalsIgnoreCase("exit")) {

                LoadingBuffer.loading();

                System.out.println("Goodbye!");

                validInput = true;

            }

            else {

                 LoadingBuffer.loading();

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

        while (!validPassword && !(VM.isLockedAdminAccount())) {

            System.out.println("Please enter your administrator password:");

            readerAuth = new Scanner(System.in);
            adminPasswordInput = readerAuth.nextLine();


            if (adminPassword.equals(adminPasswordInput)) {
                LoadingBuffer.loading();

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
                    VM.setLockedAdminAccount(true);

                    start();
                }

            }




        }
    }
}




            //System.out.println("User input: " + userInput);

