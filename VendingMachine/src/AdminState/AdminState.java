package AdminState;
import Coins.*;
import Exceptions.CapacityFullException;
import Exceptions.DuplicateEntryException;
import Exceptions.NotInStockException;
import LoadingMessage.LoadingBuffer;
import VendingMachine.VendingMachine;
import Item.VendingMachineItem;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class AdminState extends VendingMachine implements AdminStateAPI {

    private boolean duplicate = false;

    @Override
    public void addItem() throws CapacityFullException, DuplicateEntryException {

        //Loading
        LoadingBuffer.loading();

        // Validate there is enough space
        if(items.size() < VendingMachine.MAX_ITEMS) {

            Scanner readerAddItem;
            readerAddItem = new Scanner(System.in);

            System.out.println("Enter code: ");
            String codeInput = readerAddItem.nextLine();
            LoadingBuffer.loading();
            System.out.println("Enter name: ");
            String nameInput = readerAddItem.nextLine();
            LoadingBuffer.loading();
            System.out.println("Enter price: ");
            String priceInput = readerAddItem.nextLine();
            LoadingBuffer.loading();
            System.out.println("Enter quantity: ");
            String quantityInput = readerAddItem.nextLine();

            for (VendingMachineItem item : items) {
                if (Objects.equals(codeInput, item.getCode()) || Objects.equals(nameInput, item.getName())) {
                    duplicate = true;
                    break;
                }
            }

            if(duplicate) {
                throw new DuplicateEntryException("Either code or name is already taken.");
            } else {
                items.add(new VendingMachineItem(codeInput, nameInput, Double.parseDouble(priceInput), Integer.parseInt(quantityInput)));
            System.out.println("Item added.");}
        }
            else{ throw new CapacityFullException("The vending machine is full. You must remove an item before you can add another.");
    }}

    @Override
    public void removeItem() {
        //Loading
        LoadingBuffer.loading();

        // Validate array is not empty already
        if (!items.isEmpty()) {

            Scanner readerRemoveItem;
            String codeInput;
            readerRemoveItem = new Scanner(System.in);
            System.out.println("Enter code: ");
            codeInput = readerRemoveItem.nextLine();

            //Loading
            LoadingBuffer.loading();

            //Remove item
            items.removeIf(item -> Objects.equals(codeInput, item.getCode()));

            //Print validation
            System.out.println("Item removed.");
        }
    }

    @Override
    public void withdrawMoney() {
        LoadingBuffer.loading();
        System.out.println("Safe open, please collect £" + getTotalStoredMoney());
        LoadingBuffer.loading();
        System.out.println("Operation complete.");
        stored1pCoins.clear();
        stored2pCoins.clear();
        stored5pCoins.clear();
        stored10pCoins.clear();
        stored20pCoins.clear();
        stored50pCoins.clear();
        stored100pCoins.clear();
        stored200pCoins.clear();
        setTotalStoredMoney();
        System.out.println("Total stored money now equals £" + getTotalStoredMoney());
    }

    @Override
    public void depositMoney(String inputCoin) throws InputMismatchException {
        LoadingBuffer.loading();

        if(Objects.equals(inputCoin, "0.01")){
            stored1pCoins.add(new Coin1p());
            System.out.println("£0.01 Deposited.");
        }
        else if (Objects.equals(inputCoin, "0.02")){
            stored2pCoins.add(new Coin2p());
            System.out.println("£0.02 Deposited.");
        }
        else if (Objects.equals(inputCoin, "0.05")){
            stored5pCoins.add(new Coin5p());
            System.out.println("£0.05 Deposited.");
        }
        else if (Objects.equals(inputCoin, "0.10")){
            stored10pCoins.add(new Coin10p());
            System.out.println("£0.10 Deposited.");
        }
        else if (Objects.equals(inputCoin, "0.20")){
            stored20pCoins.add(new Coin20p());
            System.out.println("£0.20 Deposited.");
        }
        else if (Objects.equals(inputCoin, "0.50")){
            stored50pCoins.add(new Coin50p());
            System.out.println("£0.50 Deposited.");
        }
        else if (Objects.equals(inputCoin, "1.00")){
            stored100pCoins.add(new Coin100p());
            System.out.println("£1.00 Deposited.");
        }
        else if (Objects.equals(inputCoin, "2.00")){
            stored200pCoins.add(new Coin200p());
            System.out.println("£2.00 Deposited.");
        }
        else{ throw new InputMismatchException("Invalid input.");}

        //Update total stored money
        setTotalStoredMoney();

        System.out.printf("Total money in machine: £%.2f%n", getTotalStoredMoney());

    }

    @Override
    public void printMenu() {
        LoadingBuffer.loading();
        System.out.println("Welcome, please choose one of the following (type the number):");
        for (var instruction : AdminMainMenu.values()) {
            System.out.println(instruction.getInstruction());
        }
    }
}
