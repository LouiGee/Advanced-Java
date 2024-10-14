

package Tests;

import AdminState.AdminState;
import Exceptions.CapacityFullException;
import Exceptions.DuplicateEntryException;
import Exceptions.InvalidInputException;
import Exceptions.ItemNotFoundException;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;

import static org.junit.Assert.assertThrows;

public class AdminTests {


    private static final int MAX_ITEMS = 5;
    AdminState vmAdmin = new AdminState("5");

    //Test 1
    @Test
    public void testPrintMenu() {
        System.out.println("\n\nTest1 (Admin). Print admin main menu.");
        vmAdmin.printMenu();
        System.out.println("Test complete.");
    }

    //Test 2
    @Test
    public void testWithdrawMoney() {
        System.out.println("\n\nTest2 (Admin). Withdraw stored money.");
        vmAdmin.withdrawMoney();
        System.out.println("Test complete.");
    }

    //Test 3
    @Test
    public void testDepositMoney() throws InvalidInputException {
        System.out.println("\n\nTest3 (Admin). Deposit  money.");
        vmAdmin.depositMoney("0.50");
        System.out.println("Test passed.");
    }

    //Test 4
    @Test
    public void testDepositMoneyIncorrect() {
        System.out.println("\n\nTest4 (Admin). Deposit unsupported money input with exception thrown.");
        Exception exception = assertThrows(
                InvalidInputException.class,
                () -> vmAdmin.depositMoney("0.30")
        );
        Assertions.assertEquals("Invalid input.", exception.getMessage());
        System.out.println("Invalid input exception thrown.");
        System.out.println("Test passed.");

    }

    //Test 5
    @Test
    public void testViewItems() {
        System.out.println("\n\nTest5 (Admin). View items.");
        vmAdmin.initialiseItems();
        vmAdmin.viewInventory();
        System.out.println("Test complete.");
    }

    //Test 6
    @Test
    public void testAddItem() throws CapacityFullException, DuplicateEntryException {
        System.out.println("\n\nTest6 (Admin). Add item.");
        vmAdmin.setItems( new ArrayList<>(MAX_ITEMS - 1 ));
        vmAdmin.initialiseItems();
        vmAdmin.addItem("05", "Sprite 500ml", "1.40", "10");
        vmAdmin.viewInventory();
        System.out.println("Test complete.");
        vmAdmin.setItems( new ArrayList<>(MAX_ITEMS - 1 )); //reset items array
    }

    //Test 7
    @Test
    public void testAddItemDuplicateEntry() throws CapacityFullException, DuplicateEntryException {
        System.out.println("\n\nTest7 (Admin). Add item with duplicate code '03' .");
        vmAdmin.initialiseItems();
        Exception exception = assertThrows(
                DuplicateEntryException.class,
                () -> vmAdmin.addItem("03", "Sprite 500ml", "1.40", "10")
        );
        Assertions.assertEquals("Either code or name is already taken.", exception.getMessage());
        System.out.println("Duplicate entry exception thrown.");
        System.out.println("Test passed.");
        vmAdmin.setItems( new ArrayList<>(MAX_ITEMS - 1 )); //reset items array

    }

    //Test 8
    @Test
    public void testAddItemCapacityFull() throws CapacityFullException, DuplicateEntryException {
        System.out.println("\n\nTest8 (Admin). Initialise four items and then add two items to cause a CapacityFullException.");
        vmAdmin.initialiseItems(); //Creates 4 items and Limit is 5
        vmAdmin.viewInventory();
        vmAdmin.addItem("05", "Sprite 330ml", "1.0", "10");
        vmAdmin.viewInventory();
        Exception exception = assertThrows(
                CapacityFullException.class,
                () -> vmAdmin.addItem("06", "Sprite 500ml", "1.40", "6")
        );
        Assertions.assertEquals("The vending machine is full. You must remove an item before you can add another.", exception.getMessage());
        System.out.println("CapacityFullException exception thrown.");
        System.out.println("Test passed.");

    }

    //Test 9
    @Test
    public void testRemoveItem() throws ItemNotFoundException {
        System.out.println("\n\nTest 9 (Admin). Remove a item with code 01.");
        vmAdmin.viewInventory();
        vmAdmin.removeItem("01");
        vmAdmin.viewInventory();
        System.out.println("Test complete.");

    }

    //Test 10
    @Test
    public void testRemoveItemNoItemsToRemove() throws ItemNotFoundException {
        System.out.println("\n\nTest 10 (Admin). Attempt to remove a item with code '01' when no items are stocked.");
        vmAdmin.viewInventory();
        Exception exception = assertThrows(
                ItemNotFoundException.class,
                () -> vmAdmin.removeItem("01")
        );
        Assertions.assertEquals("The item code you have entered does not exist in the machine.", exception.getMessage());
        System.out.println("NotInStockException thrown.");
        System.out.println("Test passed.");

    }

}
