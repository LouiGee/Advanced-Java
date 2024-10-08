package CustomerState;
import Coins.*;
import Exceptions.InsufficentChangeException;
import Exceptions.InsufficientFundsException;
import Exceptions.NotInStockException;
import Item.VendingMachineItem;
import LoadingMessage.LoadingBuffer;
import VendingMachine.VendingMachine;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class CustomerState extends VendingMachine implements CustomerStateAPI {

    @Override
    public void purchaseItem() throws NotInStockException, InsufficientFundsException, InsufficentChangeException {

        //Loading
        LoadingBuffer.loading();

        //Ask for item code
        System.out.println("Please select an item code e.g. '03'");
        Scanner readerItemInput = new Scanner(System.in);
        String codeInput = readerItemInput.nextLine();

        //Loading
        LoadingBuffer.loading();

        //Control statement to handle purchase
        boolean itemFound = false;
        String itemName = "";
        double itemPrice = 0.0;
        int itemQuantity = 0;

        for (VendingMachineItem item : items) {
            if (Objects.equals(codeInput, item.getCode())) {
                itemName = item.getName();
                itemPrice = item.getPrice();
                itemQuantity = item.getQuantity();
                itemFound = true;
            }
        }

        System.out.println(itemName + " has been deposited in collection tray.");
        //Deposit change in return bucket (change allocation algorithm)

        double changeDue = getCurrentBalance() - itemPrice;

        while (changeDue > 0) {

            if (changeDue >= 2.0 && !stored200pCoins.isEmpty()) {
                returnBucket.add(stored200pCoins.getFirst());
                changeDue = changeDue - 2.0;
                System.out.println("£2.00 coin deposited in refund tray.");
            }
            else if (changeDue >= 1.0 && !stored100pCoins.isEmpty()) {
                returnBucket.add(stored100pCoins.getFirst());
                changeDue = changeDue - 1.0;
                System.out.println("£1.00 coin deposited in refund tray.");
            }
            else if (changeDue >= 0.5 && !stored50pCoins.isEmpty()) {
                returnBucket.add(stored50pCoins.getFirst());
                changeDue = changeDue - 0.5;
                System.out.println("£0.50 coin deposited in refund tray.");
            }
            else if (changeDue >= 0.2 && !stored100pCoins.isEmpty()) {
                returnBucket.add(stored100pCoins.getFirst());
                changeDue = changeDue - 0.2;
                System.out.println("£0.20 coin deposited in refund tray.");
            }
            else if (changeDue >= 0.1 && !stored100pCoins.isEmpty()) {
                returnBucket.add(stored100pCoins.getFirst());
                changeDue = changeDue - 0.1;
                System.out.println("£0.10 coin deposited in refund tray.");
            }
            else if (changeDue >= 0.05 && !stored100pCoins.isEmpty()) {
                returnBucket.add(stored100pCoins.getFirst());
                changeDue = changeDue - 0.05;
                System.out.println("£0.05 coin deposited in refund tray.");
            }
            else if (changeDue >= 0.02 && !stored100pCoins.isEmpty()) {
                returnBucket.add(stored100pCoins.getFirst());
                changeDue = changeDue - 0.02;
                System.out.println("£0.02 coin deposited in refund tray.");
            }
            else if (changeDue >= 0.01 && !stored100pCoins.isEmpty()) {
                returnBucket.add(stored100pCoins.getFirst());
                changeDue = changeDue - 0.01;
                System.out.println("£0.01 coin deposited in refund tray.");
            }

            else if (getTotalStoredMoney() < changeDue) {
                throw new InsufficentChangeException("Insufficient change. Contact admin.");
            }
        }



        if (itemFound) {
            if (itemQuantity > 0) {
                if (getCurrentBalance() >= itemPrice) {
                    System.out.println("Processing order...");
                    LoadingBuffer.loading();
                    //Deposit coins in current balance in coin storage
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


                    //Print refund  and update current balance array
                    System.out.println("£" + (getCurrentBalance() - itemPrice) + " deposited in total in refund tray.");
                    currentBalance.clear();
                    //Update stored amount
                    //setTotalStoredMoney(getTotalStoredMoney() + itemPrice);
                    //Update change in quantity
                    updateItemQuantity(items, codeInput, itemQuantity - 1);
                    //Item in tray
                    setItemInTray(true);
                    setItemInTrayName(itemName);

                } else {
                    throw new InsufficientFundsException("Please insert an additional £ " + (itemPrice - getCurrentBalance()) + " to buy a " + itemName + ".");


                }
            } else {
                throw new NotInStockException(itemName + " is out of stock, please choose another item.");

            }
        }
        else {
            // itemFound remains false
            System.out.println("Item code not found.");
        }

    }

    @Override
    public void updateItemQuantity(ArrayList<VendingMachineItem> items, String itemCode, int newQuantity) {
        for (VendingMachineItem item : items) {
            if (Objects.equals(item.getCode(), itemCode)) {
                item.setQuantity(newQuantity);

            }
        }
    }

    @Override
    public void refundBalance() {
        //Loading
        LoadingBuffer.loading();

        double changeDue = getCurrentBalance();

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
            //Print refund total
            System.out.println("A total balance of £" + getCurrentBalance() + " has been refunded.");
            //Clear current balance
            currentBalance.clear();


        }
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
                System.out.println("Current balance: £" + getCurrentBalance());



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
        System.out.println("Welcome, please choose one of the following options (type number):");
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
        //this.currentBalance = this.currentBalance + Long.parseLong(coin);
        System.out.println("Current balance: " + getCurrentBalance());

    }

    @Override
    public void collectRefund() {
        // Loading
        LoadingBuffer.loading();
        // Validate action
        if (getReturnBucket() > 0) {
            System.out.println("£" + getReturnBucket() + " collected.");
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

    @Override
    public void setTotalStoredMoney() {

    }
}



