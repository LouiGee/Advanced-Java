package VendingMachine;

import LoadingMessage.LoadingBuffer;
import Coins.*;

import java.util.ArrayList;

public class VM implements VMAPI {

    protected static ArrayList<Coin> returnBucket = new ArrayList<>(50);//Array to hold coins in refund tray
    protected static ArrayList<Coin> currentBalance = new ArrayList<>(50);//Array to hold deposited coins
    protected static ArrayList<Coin1p> stored1pCoins = new ArrayList<>(50);
    protected static ArrayList<Coin2p> stored2pCoins = new ArrayList<>(50);
    protected static ArrayList<Coin5p> stored5pCoins = new ArrayList<>(50);
    protected static ArrayList<Coin10p> stored10pCoins = new ArrayList<>(50);
    protected static ArrayList<Coin20p> stored20pCoins = new ArrayList<>(50);
    protected static ArrayList<Coin50p> stored50pCoins = new ArrayList<>(50);
    protected static ArrayList<Coin100p> stored100pCoins = new ArrayList<>(50);
    protected static ArrayList<Coin200p> stored200pCoins = new ArrayList<>(50);
    protected double totalStoredMoney = 15.70; //Total money in the vending machine to start
    protected boolean itemInTray = false; //Turns true when item is purchased
    protected String itemInTrayName = "";
    protected static final int MAX_ITEMS = 5;
    protected static ArrayList<VMItem> items = new ArrayList<>(MAX_ITEMS - 1 ); //-1 as array indexed at 0
    protected static boolean lockedAdminAccount = false; //Turns true when max login attempts reached



    // Initialise coins stored in vending machine
    public static void initialiseCoins()
    {
        for (int i = 1; i <= 10; i++)
            stored1pCoins.add(new Coin1p());

        for (int i = 1; i <= 5; i++)
            stored2pCoins.add(new Coin2p());

        for (int i = 1; i <= 10; i++)
            stored5pCoins.add(new Coin5p());

        for (int i = 1; i <= 10; i++)
            stored10pCoins.add(new Coin10p());

        for (int i = 1; i <= 10; i++)
            stored20pCoins.add(new Coin20p());

        for (int i = 1; i <= 6; i++)
            stored50pCoins.add(new Coin50p());

        for (int i = 1; i <= 3; i++)
            stored100pCoins.add(new Coin100p());

        for (int i = 1; i <= 3; i++)
            stored200pCoins.add(new Coin200p());

    }


    // Initialise items in vending machine
    public void initialiseItems()
    {
        items.add(new VMItem(1, "Coke 500ml", 1.40, 10));
        items.add(new VMItem(2, "Coke 330ml", 1, 0));
        items.add(new VMItem(3, "Fanta 330ml", 1, 5));
        items.add(new VMItem(4, "Fanta 500ml", 1.40, 4));
        //items.add(new VMItem(5, "Sprite 500ml", 1.40, 10));
    }

    // Login workflow
    public String login(String userInput, String adminPasswordInput) {

        boolean validInput = false;

        while (!validInput) {

            if (userInput.equalsIgnoreCase("customer")) {

                LoadingBuffer.loading();

                validInput = true;

                return "customer";
            }

            else if (userInput.equalsIgnoreCase("administrator")) {

                LoadingBuffer.loading();

                if (isLockedAdminAccount()) {

                    System.out.println("Admin account is locked. Please contact support.");

                    validInput = true;

                // if authentication successful then return true
                } else {

                    validInput = administratorAuthentication(adminPasswordInput);}

                if (validInput) {return "administrator";}
                else {return "Null";}

            }

            else if (userInput.equalsIgnoreCase("exit")) {

                validInput = true;
                return "exit";

            }

            else {

                LoadingBuffer.loading();

                System.out.println("Oh no! Incorrect input... please try again");

                validInput = true;

            }


        }
        return "Program terminated.";
    }

    // Login workflow
    public String login(String userInput) {

        boolean validInput = false;

        while (!validInput) {

            if (userInput.equalsIgnoreCase("customer")) {

                LoadingBuffer.loading();

                validInput = true;

                return "customer";
            }

            else if (userInput.equalsIgnoreCase("administrator")) {

                LoadingBuffer.loading();

                if (isLockedAdminAccount()) {

                    System.out.println("Admin account is locked. Please contact support.");

                    LoadingBuffer.loading();

                    // if authentication successful then return true
                }

            }

            else if (userInput.equalsIgnoreCase("exit")) {

                validInput = true;
                return "exit";

            }

            else {

                LoadingBuffer.loading();

                System.out.println("Oh no! Incorrect input... please try again");

                validInput = true;
            }


        }
        return "Program terminated.";
    }

    // Method used in login() to authenticate admin
    public boolean administratorAuthentication(String adminPasswordInput) {

        String adminPassword = "password";
        boolean validPassword = false;
        boolean return_value = true;
        Integer passwordAttempts = 0;
        Integer maxLoginAttempts = 3;

        // Loop breaks if one of these true
        while (!validPassword && !(isLockedAdminAccount())) {

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


    public ArrayList<Coin1p> getStored1pCoins() {
        return stored1pCoins;
    }

    public ArrayList<Coin2p> getStored2pCoins() {
        return stored2pCoins;
    }

    public ArrayList<Coin5p> getStored5pCoins() {
        return stored5pCoins;
    }

    public ArrayList<Coin10p> getStored10pCoins() {
        return stored10pCoins;
    }

    public ArrayList<Coin20p> getStored20pCoins() {
        return stored20pCoins;
    }

    public ArrayList<Coin50p> getStored50pCoins() {
        return stored50pCoins;
    }

    public ArrayList<Coin100p> getStored100pCoins() {
        return stored100pCoins;
    }

    public ArrayList<Coin200p> getStored200pCoins() {
        return stored200pCoins;
    }

    public void setStored1pCoins(ArrayList<Coin1p> stored1pCoins) {
        this.stored1pCoins = stored1pCoins;
    }

    public void setStored2pCoins(ArrayList<Coin2p> stored2pCoins) {
        this.stored2pCoins = stored2pCoins;
    }

    public void setStored5pCoins(ArrayList<Coin5p> stored5pCoins) {
        this.stored5pCoins = stored5pCoins;
    }

    public void setStored10pCoins(ArrayList<Coin10p> stored10pCoins) {
        this.stored10pCoins = stored10pCoins;
    }

    public void setStored20pCoins(ArrayList<Coin20p> stored20pCoins) {
        this.stored20pCoins = stored20pCoins;
    }

    public void setStored50pCoins(ArrayList<Coin50p> stored50pCoins) {
        this.stored50pCoins = stored50pCoins;
    }

    public void setStored100pCoins(ArrayList<Coin100p> stored100pCoins) {
        this.stored100pCoins = stored100pCoins;
    }

    public void setStored200pCoins(ArrayList<Coin200p> stored200pCoins) {
        this.stored200pCoins = stored200pCoins;
    }

    public void setTotalStoredMoney(double totalStoredMoney) {
        this.totalStoredMoney = totalStoredMoney;
    }

    public double getTotalStoredMoney() {
        return totalStoredMoney;
    }



    public void setTotalStoredMoney() {

        this.totalStoredMoney = 0.0;

        for (Coin1p coin : stored1pCoins) {
            this.totalStoredMoney = getTotalStoredMoney() + coin.getValue();
        }

        for (Coin2p coin : stored2pCoins) {
            this.totalStoredMoney = getTotalStoredMoney() + coin.getValue();
        }

        for (Coin5p coin : stored5pCoins) {
            this.totalStoredMoney = getTotalStoredMoney() + coin.getValue();
        }

        for (Coin10p coin : stored10pCoins) {
            this.totalStoredMoney = getTotalStoredMoney() + coin.getValue();
        }

        for (Coin20p coin : stored20pCoins) {
            this.totalStoredMoney = getTotalStoredMoney() + coin.getValue();
        }

        for (Coin50p coin : stored50pCoins) {
            this.totalStoredMoney = getTotalStoredMoney() + coin.getValue();
        }

        for (Coin100p coin : stored100pCoins) {
            this.totalStoredMoney = getTotalStoredMoney() + coin.getValue();
        }

        for (Coin200p coin : stored200pCoins) {
            this.totalStoredMoney = getTotalStoredMoney() + coin.getValue();
        }

    }

    public boolean isLockedAdminAccount() {
        return lockedAdminAccount;
    }

    public void setLockedAdminAccount(boolean lockedAdminAccount) {
        VM.lockedAdminAccount = lockedAdminAccount;
    }

    public double getReturnBucket() {
        double returnBucketNumber = 0.0;
        for (Coin coin : returnBucket) {
            returnBucketNumber = returnBucketNumber + coin.getValue();
        }
        return returnBucketNumber;
    }

    public void setReturnBucket(ArrayList<Coin> returnBucket) {
        this.returnBucket = returnBucket;
    }

    public boolean isItemInTray() {
        return itemInTray;
    }

    public void setItemInTray(boolean itemInTray) {
        this.itemInTray = itemInTray;
    }



    public ArrayList<VMItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<VMItem> items) {
        VM.items = items;
    }

    public String getItemInTrayName() {
        return itemInTrayName;
    }

    public void setItemInTrayName(String itemInTrayName) {
        this.itemInTrayName = itemInTrayName;
    }

    public double getCurrentBalance() {
        double currentBalanceNumber = 0.0;
        for (Coin coin : currentBalance) {
            currentBalanceNumber = currentBalanceNumber + coin.getValue();
        }
        return currentBalanceNumber;
    }

    public void setCurrentBalance(ArrayList<Coin> currentBalance) {
        this.currentBalance = currentBalance;
    }
}
