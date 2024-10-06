package VendingMachine;

import LoadingMessage.LoadingBuffer;

import java.util.ArrayList;
import java.util.Scanner;

public class VM {

    protected double storedMoney = 100.0; //Total money in the vending machine to start
    protected double returnBucket = 0; //Useful to know how much money is refunded
    protected boolean itemInTray = false; //Turns true when item is purchased
    protected static final int MAX_ITEMS = 5;
    protected static ArrayList<VMItem> items = new ArrayList<>(MAX_ITEMS - 1 ); //-1 as array indexed at 0
    protected static boolean lockedAdminAccount = false; //Turns true when max login attempts reached

    // Intitalise items in vending machine
    public void initialiseItems()
    {
        items.add(new VMItem(1, "Coke 500ml", 1.40, 10));
        items.add(new VMItem(2, "Coke 330ml", 1, 0));
        items.add(new VMItem(3, "Fanta 330ml", 1, 5));
        items.add(new VMItem(4, "Fanta 500ml", 1.40, 4));
        items.add(new VMItem(5, "Sprite 500ml", 1.40, 10));
    }

    // Login workflow
    public String login() {

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


                } else {validInput = administratorAuthentication();}

                if (validInput) {return "administrator";};

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

    // Method used in login() to authenticate admin
    private boolean administratorAuthentication() {

        Scanner readerAuth;
        String adminPasswordInput;
        String adminPassword = "password";
        boolean validPassword = false;
        boolean return_value = true;
        Integer passwordAttempts = 0;
        Integer maxLoginAttempts = 3;

        while (!validPassword && !(isLockedAdminAccount())) {

            System.out.println("Please enter your administrator password:");

            readerAuth = new Scanner(System.in);
            adminPasswordInput = readerAuth.nextLine();


            if (adminPassword.equals(adminPasswordInput)) {
                validPassword = true;

            }

            else if (adminPasswordInput.equalsIgnoreCase("exit")) {
                System.out.println("Goodbye!");

                validPassword = true;

            }

            else if (adminPassword != adminPasswordInput) {

                passwordAttempts++;

                if (passwordAttempts < maxLoginAttempts) {
                    System.out.println("Oops, wrong password! You have " + (maxLoginAttempts - passwordAttempts) + " attempts remaining.");
                }

                if (passwordAttempts >= maxLoginAttempts) {
                    System.out.println("Oops, you have entered the wrong password and you have reached the password attempt limit! Please contact the admin team to reset your password.");
                    setLockedAdminAccount(true);
                    return_value = false;


                }

            }




        }

        return return_value;
    }

    // Used to view inventory
    public void viewInventory() {
        for (VMItem item : items) {
            System.out.println( "Code: " + item.getCode() + " Item: " + item.getName() + " Price: " + item.getPrice() + " Quantity: " + item.getQuantity());
        }

    }

    //Getters and setters

    public double getStoredMoney() {
        return storedMoney;
    }

    public void setStoredMoney(double storedMoney) {
        this.storedMoney = storedMoney;
    }

    public static boolean isLockedAdminAccount() {
        return lockedAdminAccount;
    }

    public static void setLockedAdminAccount(boolean lockedAdminAccount) {
        VM.lockedAdminAccount = lockedAdminAccount;
    }

    public double getReturnBucket() {
        return returnBucket;
    }

    public void setReturnBucket(double returnBucket) {
        this.returnBucket = returnBucket;
    }

    public boolean isItemInTray() {
        return itemInTray;
    }

    public void setItemInTray(boolean itemInTray) {
        this.itemInTray = itemInTray;
    }

    public static ArrayList<VMItem> getItems() {
        return items;
    }

    public static void setItems(ArrayList<VMItem> items) {
        VM.items = items;
    }
}
