package AdminState;
import Coins.*;
import Exceptions.CapacityFullException;
import Exceptions.DuplicateEntryException;
import Exceptions.InvalidInputException;
import LoadingMessage.LoadingBuffer;
import VendingMachine.VendingMachine;
import Item.Item;

import java.util.ArrayList;
import java.util.Objects;

public class AdminState extends VendingMachine implements AdminStateAPI {

    private boolean duplicate = false; // Used in addItem() to determine whether item already exists in vending machine

    public AdminState(String maxItems) {
        super(maxItems);
    }

    @Override
    public void addItem(String codeInput, String nameInput, String priceInput, String quantityInput) throws CapacityFullException, DuplicateEntryException {

        //Loading
        LoadingBuffer.loading();

        // Validate there is enough space in vending machine
        if(items.size() < getMAX_ITEMS()) {
            // Determine if inputs are already in the vending machine
            for (Item item : items) {
                if (Objects.equals(codeInput, item.getCode()) || Objects.equals(nameInput, item.getName())) {
                    duplicate = true;
                    break;
                }
            }
            // Throw error if code or name already takern
            if(duplicate) {
                setDuplicate(false);
                throw new DuplicateEntryException("Either code or name is already taken.");
            // add item
            } else {
                items.add(new Item(codeInput, nameInput, Double.parseDouble(priceInput), Integer.parseInt(quantityInput)));
            System.out.println("Item added.");}
        }   // Capacity exception
            else{ throw new CapacityFullException("The vending machine is full. You must remove an item before you can add another.");
    }}

    @Override
    public void removeItem(String codeInput) {

        //Loading
        LoadingBuffer.loading();

        // Validate array is not empty already
        if (!items.isEmpty()) {

            //Remove item
            items.removeIf(item -> Objects.equals(codeInput, item.getCode()));

            //Print validation
            System.out.println("Item removed.");
        } else {System.out.println("There are no items in the vending machine to remove.");}
    }

    @Override
    public void withdrawMoney() {

        //Loading
        LoadingBuffer.loading();

        // Initial state before withdrawal
        System.out.println("Safe open, please collect £" + getTotalStoredMoney() + ".");

        //Loading
        LoadingBuffer.loading();

        //Empty money holder arrays
        System.out.println("Operation complete.");
        setStored1pCoins(new ArrayList<>());
        setStored2pCoins(new ArrayList<>());
        setStored5pCoins(new ArrayList<>());
        setStored10pCoins(new ArrayList<>());
        setStored20pCoins(new ArrayList<>());
        setStored50pCoins(new ArrayList<>());
        setStored100pCoins(new ArrayList<>());
        setStored200pCoins(new ArrayList<>());

        //Recalculate stored money now that money holder arrays are closed
        setTotalStoredMoney();

        //Print change in state
        System.out.println("Total stored money now equals £" + getTotalStoredMoney() + ".");
    }

    @Override
    public void depositMoney(String inputCoin) throws InvalidInputException {

        //Loading
        LoadingBuffer.loading();

        //Deposit money in correct money array (switch could have been used)
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
        else{ throw new InvalidInputException("Invalid input.");}

        //Update total stored money
        setTotalStoredMoney();

        //End state
        System.out.printf("Total money in machine: £%.2f.%n", getTotalStoredMoney());

    }

    @Override
    public void printMenu() {
        //Loading
        LoadingBuffer.loading();

        //Welcome Message
        System.out.println("Welcome, please choose one of the following (type the number):");

        //Loop through AdminMainMenu Enum
        for (var instruction : AdminMainMenu.values()) {
            System.out.println(instruction.getInstruction());
        }
    }


    //Getters and setters for completeness

    public boolean isDuplicate() {
        return duplicate;
    }

    public void setDuplicate(boolean duplicate) {
        this.duplicate = duplicate;
    }
}
