import AdminState.AdminState;
import CustomerState.CustomerState;
import Exceptions.*;
import LoadingMessage.LoadingBuffer;
import LoginState.LoginState;
import VendingMachine.VendingMachine;

import java.util.Scanner;


public class Interaction {

    private String adminPasswordInput;
    private String user;


    public void start() {

        //Maximum capacity of vending machine
        System.out.println("What is the capacity of the vending machine?");
        Scanner capacityReader = new Scanner(System.in);
        String vmCapacity = capacityReader.nextLine();

        //Create an instance of the vending machine
        VendingMachine vm = new VendingMachine(vmCapacity);

        //Load preloaded items and coins into vending machine
        vm.initialiseItems();
        vm.initialiseCoins();

        //Create instance of login state
        LoginState loginState = new LoginState(vmCapacity);

        //Welcome message
        System.out.println("Hello! I am " + vm.getVendingMachineName() + " the vending machine.");

        boolean exit = false;
        while (!exit) {

            // Trigger login and determine whether user is a 'customer' or an 'administrator'
            System.out.println("Are you a 'customer' or an 'administrator'? Type 'exit' at anytime to stop interaction.");
            //User input stored as a variable
            Scanner reader = new Scanner(System.in);
            String userInput = reader.nextLine();

            //Gather password for authentication
            if (userInput.equals("administrator")) {
                System.out.println("Please enter your administrator password:");
                Scanner readerAuth = new Scanner(System.in);
                adminPasswordInput = readerAuth.nextLine();
                user = loginState.login(userInput, adminPasswordInput);
            } else if (userInput.equals("customer")) {
                user = loginState.login(userInput);
            }

            // Control statements to dictate flow based on whether customer or administrator
            if (user.equalsIgnoreCase("customer")) {

                // Initialise customer interface class with bespoke methods for a customer
                CustomerState CustomerState = new CustomerState(vmCapacity);

                boolean returnToLogin = false;
                while (!returnToLogin) {

                    //Print menu
                    CustomerState.printMenu();

                    //Retrieve user input between 1-8
                    Scanner readerMainMenu = new Scanner(System.in);
                    String menuInput = readerMainMenu.nextLine();

                    //Input control flow
                    switch (menuInput) {

                        case "1":

                            //Loading
                            LoadingBuffer.loading();

                            //Print out list of accepted coins
                            CustomerState.printAcceptedCoins();

                            //Receive input
                            Scanner readerInsertCoin = new Scanner(System.in);
                            String coinInput = readerInsertCoin.nextLine();

                            CustomerState.depositCoin(coinInput);

                            break;

                        case "2":

                            CustomerState.viewInventory();
                            break;

                        case "3":

                            LoadingBuffer.loading();
                            System.out.println("Balance: £" + CustomerState.getCurrentBalance());
                            break;

                        case "4":

                            //Loading
                            LoadingBuffer.loading();

                            //Ask for item code
                            System.out.println("Please select an item code e.g. '03'");
                            Scanner readerItemInput = new Scanner(System.in);
                            String itemPurchaseCodeInput = readerItemInput.nextLine();

                            try {
                                CustomerState.purchaseItem(itemPurchaseCodeInput);
                            } catch (InsufficientChangeException | NotInStockException | InsufficientFundsException e) {System.err.println(e.getMessage());}
                            break;

                        case "5":

                            CustomerState.refundBalance();
                            break;

                        case "6":

                            CustomerState.collectRefund();
                            break;

                        case "7":

                            CustomerState.collectItem();
                            break;

                        case "8":

                            LoadingBuffer.loading();
                            returnToLogin = true;
                            break;

                        case "9":

                            returnToLogin = true;
                            exit = true;

                            //Goodbye message
                            LoadingBuffer.loading();
                            System.out.println("See you soon!");
                            break;

                    }

                }

            } else if (user.equalsIgnoreCase("administrator")) {

                // Initialise administrator interface class with bespoke methods for a administrator
                AdminState AdminState = new AdminState(vmCapacity);

                boolean returnToLogin = false;
                while (!returnToLogin) {

                    //Print menu
                    AdminState.printMenu();

                    //Retrieve user input
                    Scanner readerMainMenu = new Scanner(System.in);
                    String menuInput = readerMainMenu.nextLine();

                    switch (menuInput) {

                        case "1":

                            AdminState.withdrawMoney();
                            break;

                        case "2":

                            LoadingBuffer.loading();
                            System.out.println("Please choose a coin to deposit e.g. '2.00'.");

                            Scanner readerDepositMoney = new Scanner(System.in);
                            String depositInput = readerMainMenu.nextLine();

                            try {
                            AdminState.depositMoney(depositInput); }
                            catch (InvalidInputException e) {System.err.println(e.getMessage());}
                            break;

                        case "3":

                            AdminState.viewInventory();
                            break;

                        case "4":

                            // Gather user input
                            Scanner readerAddItem;
                            readerAddItem = new Scanner(System.in);

                            System.out.println("Enter code: ");
                            String codeInput = readerAddItem.nextLine();
                            LoadingBuffer.loading();
                            System.out.println("Enter name: ");
                            String nameInput = readerAddItem.nextLine();
                            LoadingBuffer.loading();
                            System.out.println("Enter price: ");
                            String priceInput = readerAddItem.nextLine();
                            LoadingBuffer.loading();
                            System.out.println("Enter quantity: ");
                            String quantityInput = readerAddItem.nextLine();


                            try {
                                AdminState.addItem(codeInput, nameInput, priceInput, quantityInput);
                            } catch (CapacityFullException | DuplicateEntryException c) {System.err.println(c.getMessage());}
                            break;


                        case "5":

                            //User input
                            Scanner readerRemoveItem = new Scanner(System.in);
                            System.out.println("Enter item code: ");
                            String removeItemCodeInput = readerRemoveItem.nextLine();

                            AdminState.removeItem(removeItemCodeInput);
                            break;

                        case "6":

                            LoadingBuffer.loading();
                            returnToLogin = true;
                            break;

                        case "7":
                            returnToLogin = true;
                            exit = true;

                            //Goodbye message
                            LoadingBuffer.loading();
                            System.out.println("See you soon!");
                            break;

                    }


                }


            }
            else if (user.equalsIgnoreCase("exit")) {
                //Exit lopp
                exit = true;
                //Goodbye message
                LoadingBuffer.loading();
                System.out.println("Goodbye, see you soon!");

            }

        }




    }
}






