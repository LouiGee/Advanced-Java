
package CustomerState;


import Exceptions.InsufficientChangeException;
import Exceptions.InsufficientFundsException;
import Exceptions.ItemNotFoundException;
import Exceptions.ItemOutOfStockException;
import Item.Item;

import java.util.ArrayList;

public interface CustomerStateAPI {

    // Print menu to customer
    void printMenu();

    // Print out list of accepted coins
    void printAcceptedCoins();

    //Used as a helper method in purchaseItem()
    boolean isValidCoin (String coinInput);

    //Handles purchases
    void purchaseItem(String codeInput) throws ItemNotFoundException, InsufficientFundsException, InsufficientChangeException, ItemOutOfStockException;

    //Will refund balance at any point
    void refundBalance();

    //Handles coin deposits
    void depositCoin(String coinInput);

    //Collect refund if coins in tray
    void collectRefund();

    //Collect deposited item from tray
    void collectItem();

    // helper method used in purchaseItem()
    void updateItemQuantity(ArrayList<Item> items, String itemCode, int newQuantity);

}
