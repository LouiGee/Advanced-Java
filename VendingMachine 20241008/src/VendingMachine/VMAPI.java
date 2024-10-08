package VendingMachine;

import Coins.Coin;

import java.util.ArrayList;

public interface VMAPI {

    // Intitalise items in vending machine
    void initialiseItems();
    // Login workflow, uses administratorAuthentication
    String login(String userInput, String adminPasswordInput);
    // Method used in login() to authenticate admin
    boolean administratorAuthentication(String adminPasswordInput);
    // Used in both customer and administrator interface to view inventory
    void viewInventory();

    //Getters and setters
    public double getTotalStoredMoney();

    public void setTotalStoredMoney();

    boolean isLockedAdminAccount();

    void setLockedAdminAccount(boolean lockedAdminAccount);

    double getReturnBucket();

    void setReturnBucket(ArrayList<Coin> returnBucket);

    boolean isItemInTray();

    void setItemInTray(boolean itemInTray);

    ArrayList<VMItem> getItems();

    void setItems(ArrayList<VMItem> items);

    String getItemInTrayName();

    void setItemInTrayName(String itemInTrayName);


}
