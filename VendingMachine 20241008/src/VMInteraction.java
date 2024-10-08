import AdministratorInterface.VMAdminInterface;
import Coins.Coin100p;
import CustomerInterface.VMCustomerInterface;
import LoadingMessage.LoadingBuffer;
import VendingMachine.VM;

import java.util.Scanner;


public class VMInteraction {

    private String adminPasswordInput;
    private String user;


    public void start() {

        //Create an instance of the vending machine
        VM vm = new VM();

        //Load preloaded items and coins into vending machine
        vm.initialiseItems();
        vm.initialiseCoins();

        //Welcome message
        System.out.println("Hello! I am a vending machine.");



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
                user = vm.login(userInput, adminPasswordInput);
            } else if (userInput.equals("customer")) {
                user = vm.login(userInput);
            }

            // Control statements to dictate flow based on whether customer or administrator
            if (user.equalsIgnoreCase("customer")) {

                // Initialise customer interface class with bespoke methods for a customer
                VMCustomerInterface VMCustomerInterface = new VMCustomerInterface();

                boolean returnToLogin = false;
                while (!returnToLogin) {

                    //Print menu
                    VMCustomerInterface.printMenu();

                    //Retrieve user input between 1-8
                    Scanner readerMainMenu = new Scanner(System.in);
                    String menuInput = readerMainMenu.nextLine();

                    //Input control flow
                    switch (menuInput) {

                        case "1":

                            VMCustomerInterface.coinInput();

                            break;

                        case "2":

                            VMCustomerInterface.viewInventory();
                            break;

                        case "3":

                            LoadingBuffer.loading();
                            System.out.println("Balance: £" + VMCustomerInterface.getCurrentBalance());
                            break;

                        case "4":

                            VMCustomerInterface.purchaseItem();
                            break;

                        case "5":

                            VMCustomerInterface.refundBalance();
                            break;

                        case "6":

                            VMCustomerInterface.collectRefund();
                            break;

                        case "7":

                            VMCustomerInterface.collectItem();
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
                VMAdminInterface VMAdminInterface = new VMAdminInterface();

                boolean returnToLogin = false;
                while (!returnToLogin) {

                    //Print menu
                    VMAdminInterface.printMenu();

                    //Retrieve user input
                    Scanner readerMainMenu = new Scanner(System.in);
                    String menuInput = readerMainMenu.nextLine();

                    switch (menuInput) {

                        case "1":

                            VMAdminInterface.collectMoney();
                            break;

                        case "2":

                            VMAdminInterface.viewInventory();
                            break;

                        case "3":

                            VMAdminInterface.addItem();
                            break;

                        case "4":

                            VMAdminInterface.removeItem();
                            break;

                        case "5":

                            LoadingBuffer.loading();
                            returnToLogin = true;
                            break;

                        case "6":

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
                System.out.println("See you soon!");

            }

        }




    }
}






