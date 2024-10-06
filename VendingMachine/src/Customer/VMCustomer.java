package Customer;
import VendingMachine.VMItem;
import LoadingMessage.LoadingBuffer;
import VendingMachine.VM;

import java.util.ArrayList;
import java.util.Scanner;

public class VMCustomer extends VM implements VMCustomerAPI {

    private double currentBalance = 0; //Total amount deposited in current interaction

    @Override
    public void purchaseItem() {

        //Loading
        LoadingBuffer.loading();

        //Ask for item code
        System.out.println("Please select an item code e.g. '3'");
        Scanner readerItemInput = new Scanner(System.in);
        String codeInput = readerItemInput.nextLine();

        //Loading
        LoadingBuffer.loading();

        //Control statement to handle purchase
        boolean itemFound = false;
        String itemName = "";
        double itemPrice = 0.0;
        int itemQuantity = 0;

        for (VMItem item : items) {
            if (Integer.parseInt(codeInput) == item.getCode()) {
                itemName = item.getName();
                itemPrice = item.getPrice();
                itemQuantity = item.getQuantity();
                itemFound = true;
            }
        }

        if (itemFound) {
            if (itemQuantity > 0) {
                if (getCurrentBalance() >= itemPrice) {
                    System.out.println("Processing order...");
                    LoadingBuffer.loading();
                    System.out.println(itemName + "has been deposited in collection tray.");
                    //Deposit change in return bucket
                    setReturnBucket((getCurrentBalance() - itemPrice));
                    //Print refund  and update current balance
                    System.out.println("£" + (getCurrentBalance() - itemPrice) + " change deposited in refundtray.");
                    setCurrentBalance(0);
                    //Update stored amount
                    setStoredMoney(getStoredMoney() + itemPrice);
                    //Update change in quantity
                    updateItemQuantity(items, codeInput, itemQuantity - 1);
                    //Item in tray
                    setItemInTray(true);

                } else {
                    System.out.println("Please insert an additional £ " + (itemPrice - getCurrentBalance()) + " to buy a " + itemName + ".");

                }
            } else {
                System.out.println(itemName + "is out of stock, please choose another item.");
            }
        }
        else {
            // itemFound remains false
            System.out.println("Item code not found.");
        }

    }

    @Override
    public void updateItemQuantity(ArrayList<VMItem> items, String itemCode, int newQuantity) {
        for (VMItem item : items) {
            if (item.getCode() == Integer.parseInt(itemCode)) {
                item.setQuantity(newQuantity);

            }
        }
    }

    @Override
    public void refundBalance() {
        //Loading
        LoadingBuffer.loading();
        //Print refund
        System.out.println("A balance of £" + getCurrentBalance() + " has been refunded.");
        //Deposit balance in refund bucket
        setReturnBucket(getCurrentBalance());
        //Reset balance
        setCurrentBalance(0);


    }

    @Override
    public void coinInput() {

        boolean coinInputComplete = false;
        while (!coinInputComplete) {

            //Loading
            LoadingBuffer.loading();

            //Print out list of accepted coins
            printAcceptedCoins();

            //Receive input
            Scanner readerInsertCoin = new Scanner(System.in);
            String coinInput = readerInsertCoin.nextLine();

            //Validate input and show balance
            if (isValidCoin(coinInput)) {

                //Loading and print validation message
                LoadingBuffer.loading();
                System.out.println("Coin accepted.");

                //Add coin to current balance
                setCurrentBalance(getCurrentBalance() + Double.parseDouble(coinInput));

                //Print current balance
                System.out.println("Current balance: " + getCurrentBalance());

            } else if (coinInput.equalsIgnoreCase("done")) {
                coinInputComplete = true;

            } else {
                LoadingBuffer.loading();
                System.out.println("Unrecognised coin. Coin returned.");
            }

        }
    }

    @Override
    public void printMenu() {
        System.out.println("Welcome, please choose one of the following (type the number):");
        for (var instruction : CustomerMainMenu.values()) {
            System.out.println(instruction.getInstruction());
        }
    }

    @Override
    public void printAcceptedCoins() {
        System.out.println("Please insert one of the following coins e.g. '2.00' (type 'done' to return to the main menu) :");
        for (var coin : AcceptedCoins.values()) {
            System.out.println(coin.getCoin());
        }
    }

    @Override
    public boolean isValidCoin(String coinInput) {
        for (AcceptedCoins coin : AcceptedCoins.values()) {
            if (coin.getCoin().equals(coinInput)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void insertCoin(String coin) {
        this.currentBalance = this.currentBalance + Long.parseLong(coin);
        System.out.println("Current balance: " + getCurrentBalance());

    }

    @Override
    public void collectRefund() {
        // Loading
        LoadingBuffer.loading();
        // Validate action
        if (getReturnBucket() > 0) {
            System.out.println("£" + getReturnBucket() + " collected.");
            setReturnBucket(0);
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
            System.out.println("Please collect item from tray.");
            setItemInTray(false);
        } else {
            System.out.println("There is currently no item in the tray.");
        }
    }


    @Override
    public double getCurrentBalance() {
        return currentBalance;
    }

    @Override
    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }

}



