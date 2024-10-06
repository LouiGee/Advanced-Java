import Administrator.VMAdmin;
import Customer.VMCustomer;
import LoadingMessage.LoadingBuffer;
import VendingMachine.VM;

import java.util.Scanner;


public class VMInteraction {


    public void start() {

        //Create an instance of the vending machine
        VM vm = new VM();

        //Load preloaded items into vending machine
        vm.initialiseItems();

        // Trigger login and determine whether user is a 'customer' or an 'administrator'
        String user = vm.login();

        // Control statements to dictate flow based on whether customer or administrator
        if (user.equalsIgnoreCase("customer")) {

            // Initialise customer interface class with bespoke methods for a customer
            VMCustomer VMCustomerInterface = new VMCustomer();

            boolean exit = false;
            while (!exit) {

                //Loading
                LoadingBuffer.loading();

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

                        exit = true;
                        break;

                }

            }

        } else if (user.equalsIgnoreCase("administrator")) {

            // Initialise administrator interface class with bespoke methods for a administrator
            VMAdmin VMAdminInterface = new VMAdmin();

            boolean exit = false;
            while (!exit) {

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

                        exit = true;

                }


            }


        }

        //Goodbye message
        LoadingBuffer.loading();
        System.out.println("See you soon!");

    }


}






