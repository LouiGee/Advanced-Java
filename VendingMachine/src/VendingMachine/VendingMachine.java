package VendingMachine;

import Item.VendingMachineItem;
import LoadingMessage.LoadingBuffer;
import Coins.*;

import java.util.ArrayList;

public class VendingMachine implements VendingMachineAPI {

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
    protected static double totalStoredMoney = 15.70; //Total money in the vending machine to start
    protected boolean itemInTray = false; //Turns true when item is purchased
    protected String itemInTrayName = "";
    protected static final int MAX_ITEMS = 5;
    protected static ArrayList<VendingMachineItem> items = new ArrayList<>(MAX_ITEMS - 1 ); //-1 as array indexed at 0
    protected static boolean lockedAdminAccount = false; //Turns true when max login attempts reached
    protected String vendingMachineName = "Victor";


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
        items.add(new VendingMachineItem("01", "Coke 500ml", 1.40, 10));
        items.add(new VendingMachineItem("02", "Coke 330ml", 1, 0));
        items.add(new VendingMachineItem("03", "Fanta 330ml", 1, 5));
        items.add(new VendingMachineItem("04", "Fanta 500ml", 1.40, 4));
        //items.add(new VMItem(5, "Sprite 500ml", 1.40, 10));
    }

    // Used to view inventory
    public void viewInventory() {
        for (VendingMachineItem item : items) {
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
        VendingMachine.stored1pCoins = stored1pCoins;
    }

    public void setStored2pCoins(ArrayList<Coin2p> stored2pCoins) {
        VendingMachine.stored2pCoins = stored2pCoins;
    }

    public void setStored5pCoins(ArrayList<Coin5p> stored5pCoins) {
        VendingMachine.stored5pCoins = stored5pCoins;
    }

    public void setStored10pCoins(ArrayList<Coin10p> stored10pCoins) {
        VendingMachine.stored10pCoins = stored10pCoins;
    }

    public void setStored20pCoins(ArrayList<Coin20p> stored20pCoins) {
        VendingMachine.stored20pCoins = stored20pCoins;
    }

    public void setStored50pCoins(ArrayList<Coin50p> stored50pCoins) {
        VendingMachine.stored50pCoins = stored50pCoins;
    }

    public void setStored100pCoins(ArrayList<Coin100p> stored100pCoins) {
        VendingMachine.stored100pCoins = stored100pCoins;
    }

    public void setStored200pCoins(ArrayList<Coin200p> stored200pCoins) {
        VendingMachine.stored200pCoins = stored200pCoins;
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
        VendingMachine.lockedAdminAccount = lockedAdminAccount;
    }

    public double getReturnBucket() {
        double returnBucketNumber = 0.0;
        for (Coin coin : returnBucket) {
            returnBucketNumber = returnBucketNumber + coin.getValue();
        }
        return returnBucketNumber;
    }

    public void setReturnBucket(ArrayList<Coin> returnBucket) {
        VendingMachine.returnBucket = returnBucket;
    }

    public boolean isItemInTray() {
        return itemInTray;
    }

    public void setItemInTray(boolean itemInTray) {
        this.itemInTray = itemInTray;
    }



    public ArrayList<VendingMachineItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<VendingMachineItem> items) {
        VendingMachine.items = items;
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
        VendingMachine.currentBalance = currentBalance;
    }

    public String getVendingMachineName() {
        return vendingMachineName;
    }

    public void setVendingMachineName(String vendingMachineName) {
        this.vendingMachineName = vendingMachineName;
    }
}
