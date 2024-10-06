package Administrator;
import LoadingMessage.LoadingBuffer;
import VendingMachine.VM;
import VendingMachine.VMItem;

import java.util.Scanner;

public class VMAdmin extends VM implements VMAdminAPI {

    @Override
    public void addItem() {

        //Loading
        LoadingBuffer.loading();

        // Validate there is enough space
        if(items.size() < VM.MAX_ITEMS) {

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
            System.out.println("Enter price: ");
            String quantityInput = readerAddItem.nextLine();

            items.add(new VMItem(Integer.parseInt(codeInput), nameInput, Double.parseDouble(priceInput), Integer.parseInt(quantityInput)));
            System.out.println("Item added.");
        }
            else{ System.out.println("The vending machine is full. You must remove an item before you can add another.");
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
            items.removeIf(item -> Integer.parseInt(codeInput) == item.getCode());

            //Print validation
            System.out.println("Item removed.");
        }
    }

    @Override
    public void collectMoney() {
        LoadingBuffer.loading();
        System.out.println("Safe open, please collect £" + super.getStoredMoney());
        LoadingBuffer.loading();
        System.out.println("Operation complete.");
        super.setStoredMoney(0);
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
