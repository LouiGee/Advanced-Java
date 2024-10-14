package CustomerState;
import Coins.*;
import Exceptions.InsufficientChangeException;
import Exceptions.InsufficientFundsException;
import Exceptions.ItemNotFoundException;
import Exceptions.ItemOutOfStockException;
import Item.Item;
import LoadingMessage.LoadingBuffer;
import VendingMachine.VendingMachine;

import java.util.ArrayList;
import java.util.Objects;

public class CustomerState extends VendingMachine implements CustomerStateAPI {


    public CustomerState(String maxItems) {
        super(maxItems);
    }

    @Override
    public void purchaseItem( String codeInput) throws ItemNotFoundException, InsufficientFundsException, InsufficientChangeException, ItemOutOfStockException {

        //Loading
        LoadingBuffer.loading();

        //Initialise variables
        boolean itemFound = false;
        String itemName = "";
        double itemPrice = 0.0;
        int itemQuantity = 0;


        // Validate input and that item is stocked in vending machine
        for (Item item : items) {
            if (Objects.equals(codeInput, item.getCode())) {
                itemName = item.getName();
                itemPrice = item.getPrice();
                itemQuantity = item.getQuantity();
                itemFound = true;
            }
        }

        //Calculate change due
        double changeDue = getCurrentBalanceNumber() - itemPrice;

        //If not enough change coins in machine throw InsufficientChangeException
        if (getTotalStoredMoney() < changeDue) {

            //Refund deposited
            setReturnBucket(getCurrentBalance());

            //Refresh current balance
            setCurrentBalance(new ArrayList<>());

            throw new InsufficientChangeException("Insufficient change. Current balance refunded. Contact admin.");

        //Else allocate change (Change allocation algorithm)
        } else {
            // While change is outstanding
            while (changeDue > 0) {

                if (changeDue >= 2.0 && !stored200pCoins.isEmpty()) {
                    returnBucket.add(stored200pCoins.getFirst());
                    changeDue = changeDue - 2.0;
                    System.out.println("£2.00 coin deposited in refund tray.");
                } else if (changeDue >= 1.0 && !stored100pCoins.isEmpty()) {
                    returnBucket.add(stored100pCoins.getFirst());
                    changeDue = changeDue - 1.0;
                    System.out.println("£1.00 coin deposited in refund tray.");
                } else if (changeDue >= 0.5 && !stored50pCoins.isEmpty()) {
                    returnBucket.add(stored50pCoins.getFirst());
                    changeDue = changeDue - 0.5;
                    System.out.println("£0.50 coin deposited in refund tray.");
                } else if (changeDue >= 0.2 && !stored100pCoins.isEmpty()) {
                    returnBucket.add(stored100pCoins.getFirst());
                    changeDue = changeDue - 0.2;
                    System.out.println("£0.20 coin deposited in refund tray.");
                } else if (changeDue >= 0.1 && !stored100pCoins.isEmpty()) {
                    returnBucket.add(stored100pCoins.getFirst());
                    changeDue = changeDue - 0.1;
                    System.out.println("£0.10 coin deposited in refund tray.");
                } else if (changeDue >= 0.05 && !stored100pCoins.isEmpty()) {
                    returnBucket.add(stored100pCoins.getFirst());
                    changeDue = changeDue - 0.05;
                    System.out.println("£0.05 coin deposited in refund tray.");
                } else if (changeDue >= 0.02 && !stored100pCoins.isEmpty()) {
                    returnBucket.add(stored100pCoins.getFirst());
                    changeDue = changeDue - 0.02;
                    System.out.println("£0.02 coin deposited in refund tray.");
                } else if (changeDue >= 0.01 && !stored100pCoins.isEmpty()) {
                    returnBucket.add(stored100pCoins.getFirst());
                    changeDue = changeDue - 0.01;
                    System.out.println("£0.01 coin deposited in refund tray.");
                }
            }
        }

        // if item found, there is enough quantity and the user has enough money
        if (itemFound) {
            if (itemQuantity > 0) {
                if (getCurrentBalanceNumber() >= itemPrice) {

                    //Validation message
                    System.out.println("Processing order...");

                    //Loading
                    LoadingBuffer.loading();

                    //Deposit coins in current balance to  coin storage
                    for (Coin coin: currentBalance) {
                        if(coin.getValue() == 0.01){
                            stored1pCoins.add(new Coin1p());
                        }
                        else if (coin.getValue() == 0.02){
                            stored2pCoins.add(new Coin2p());
                        }
                        else if (coin.getValue() == 0.05){
                            stored5pCoins.add(new Coin5p());
                        }
                        else if (coin.getValue() == 0.10){
                            stored10pCoins.add(new Coin10p());
                        }
                        else if (coin.getValue() == 0.20){
                            stored20pCoins.add(new Coin20p());
                        }
                        else if (coin.getValue() == 0.50){
                            stored50pCoins.add(new Coin50p());
                        }
                        else if (coin.getValue() == 1.00){
                            stored100pCoins.add(new Coin100p());
                        }
                        else if (coin.getValue() == 2.00){
                            stored200pCoins.add(new Coin200p());
                        }
                    }

                    // Update current balance array
                    currentBalance.clear();

                    // Item deposited message
                    System.out.println(itemName + " has been deposited in the collection tray.");
                    setItemInTray(true);
                    setItemInTrayName(itemName);

                    //Update stored amount after current balance intake and refund considerations
                    setTotalStoredMoney();
                    //Update change in quantity of item
                    updateItemQuantity(items, codeInput, itemQuantity - 1);

                } else {
                    throw new InsufficientFundsException("Please insert an additional £ " + (itemPrice - getCurrentBalanceNumber()) + " to buy a " + itemName + ".");
                }
            } else {
                throw new ItemOutOfStockException(itemName + " is out of stock, please choose another item.");
            }
        }
        else {
            // Occurs if itemFound remains false
            throw new ItemNotFoundException("The item code you have entered does not exist in the machine.");
        }
    }

    //Used in purchaseItem() to updateItemQuantity after purchase
    @Override
    public void updateItemQuantity(ArrayList<Item> items, String itemCode, int newQuantity) {
        for (Item item : items) {
            if (Objects.equals(item.getCode(), itemCode)) {
                item.setQuantity(newQuantity);
            }
        }
    }

    @Override
    public void refundBalance() {

        //Loading
        LoadingBuffer.loading();

        //Set refund bucket state
        setReturnBucket(getCurrentBalance());

        //Confirmation message
        System.out.println(getCurrentBalanceNumber() + " has been deposited in refund tray.");

        //Clear current balance array
        setCurrentBalance(new ArrayList<>());

        }

    @Override
    public void depositCoin(String coinInput) {

        boolean coinInputComplete = false;

        while (!coinInputComplete) {

            // isValidCoin() method returns boolean
            if (isValidCoin(coinInput)) {

                //Loading and print validation message
                LoadingBuffer.loading();
                System.out.println("Coin accepted.");

                //Initialise coin and add to current balance
                switch (coinInput) {

                    case "0.01":
                        Coin1p coin1p = new Coin1p();
                        currentBalance.add(coin1p);
                        break;
                    case "0.02":
                        Coin2p coin2p = new Coin2p();
                        currentBalance.add(coin2p);
                        break;
                    case "0.05":
                        Coin5p coin5p = new Coin5p();
                        currentBalance.add(coin5p);
                        break;
                    case "0.10":
                        Coin10p coin10p = new Coin10p();
                        currentBalance.add(coin10p);
                        break;
                    case "0.20":
                        Coin20p coin20p = new Coin20p();
                        currentBalance.add(coin20p);
                        break;
                    case "0.50":
                        Coin50p coin50p = new Coin50p();
                        currentBalance.add(coin50p);
                        break;
                    case "1.00":
                        Coin100p coin100p = new Coin100p();
                        currentBalance.add(coin100p);
                        break;
                    case "2.00":
                        Coin200p coin200p = new Coin200p();
                        currentBalance.add(coin200p);
                        break;

                }

                //Print current balance
                System.out.println("Current balance: £" + getCurrentBalanceNumber() + ".");
                coinInputComplete = true;


              // when input is 'done' return to main menu
            } else if (coinInput.equalsIgnoreCase("done")) {
                coinInputComplete = true;

                // could be an exception here
            } else {
                LoadingBuffer.loading();
                System.out.println("Unrecognised coin. Coin returned.");
            }

        }
    }

    @Override
    public void printMenu() {
        System.out.println("Welcome, please choose one of the following options (type number):");
        //Iterate through enum to print menu
        for (var instruction : CustomerMainMenu.values()) {
            System.out.println(instruction.getInstruction());
        }
    }

    @Override
    public void printAcceptedCoins() {
        //Iterate through enum to print accepted coins
        System.out.println("Please insert one of the following coins e.g. '2.00' (type 'done' to return to the main menu) :");
        for (var coin : AcceptedCoins.values()) {
            System.out.println(coin.getCoin());
        }
    }

    @Override
    public boolean isValidCoin(String coinInput) {
        //Iterate through enum and return true if input coin in list
        for (AcceptedCoins coin : AcceptedCoins.values()) {
            if (coin.getCoin().equals(coinInput)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void collectRefund() {
        // Loading
        LoadingBuffer.loading();
        // Validate action
        if (getReturnBucket() > 0) {
            System.out.println("£" + getReturnBucket() + " collected from the refund tray.");
            returnBucket.clear();
        } else {
            System.out.println("The refund bucket is empty.");
        }
    }

    @Override
    public void collectItem() {

        //Loading
        LoadingBuffer.loading();
        // Tray interaction
        if(isItemInTray()){
            System.out.println("Please collect " + itemInTrayName + " from the tray.");
            setItemInTray(false);
        } else {
            System.out.println("There is currently no item in the tray.");
        }
    }

}



