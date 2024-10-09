package VendingMachine;

import Coins.Coin;
import Item.Item;

import java.util.ArrayList;

public interface VendingMachineAPI {

    // Intitalise items in vending machine
    void initialiseItems();
    // Login workflow, uses administratorAuthentication

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

    ArrayList<Item> getItems();

    void setItems(ArrayList<Item> items);

    String getItemInTrayName();

    void setItemInTrayName(String itemInTrayName);


}
