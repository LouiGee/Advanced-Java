package Tests;

import AdministratorInterface.VMAdminInterface;
import LoadingMessage.LoadingBuffer;
import VendingMachine.VM;
import org.junit.Assert;
import org.junit.Test;

public class AdminTests {

    VMAdminInterface vmAdmin = new VMAdminInterface();

    public void state() {
        vmAdmin.initialiseItems();
        System.out.println("Vending machine starting state: \n");
        vmAdmin.viewInventory();
        System.out.println("\nStored money: £" + vmAdmin.getTotalStoredMoney());
        System.out.println("Current balance: £" + vmAdmin.getCurrentBalance());
        System.out.println("Money in refund tray: £" + vmAdmin.getReturnBucket());
        System.out.println("Item in tray: " + vmAdmin.isItemInTray());

    }

    @Test
    public void testLoginAdministrator() {
        System.out.println("\n\nTest1 (Admin). User has typed 'administrator' at login and entered the correct password 'password'.");
        System.out.println("Are you a 'customer' or an 'administrator'? Type 'exit' at anytime to stop interaction.");
        System.out.println("Please enter your administrator password:");
        String result = vmAdmin.login("administrator","password");
        Assert.assertEquals("administrator", result);
        System.out.println("Test Complete.");

    }

    @Test
    public void testLoginAdministratorIncorrect() {
        System.out.println("\n\nTest2a (Admin). User has typed 'administrator' and entered the incorrect password 'incorrectpassword' 3 times.");
        System.out.println("Are you a 'customer' or an 'administrator'? Type 'exit' at anytime to stop interaction.");
        LoadingBuffer.loading();
        System.out.println("Please enter your administrator password:");
        vmAdmin.login("administrator","incorrectpassword");
        System.out.println(" \n Test2b (Admin).The Admin Account is now locked to prevent bruteforce attempts. Attempting again provides the follwing message: ");
        vmAdmin.login("administrator","incorrectpassword");
        System.out.println("Test Complete.");
    }

    @Test
    public void testLoginUserIncorrect() {
        System.out.println("\n\nTest3 (Admin). User has typed 'notauser' at the login page");
        System.out.println("Are you a 'customer' or an 'administrator'? Type 'exit' at anytime to stop interaction.");
        vmAdmin.login("notauser");
        System.out.println("Test Complete.");
    }

    @Test
    public void testLoginExit() {
        System.out.println("\n\nTest4 (Admin). User has typed 'exit' at the login page");
        System.out.println("Are you a 'customer' or an 'administrator'? Type 'exit' at anytime to stop interaction.");
        String result = vmAdmin.login("exit");
        Assert.assertEquals("exit", result);
        System.out.println("Test Complete.");
    }

    @Test
    public void testPrintMenu() {
        System.out.println("\n\nTest5 (Admin). Print admin main menu.");
        vmAdmin.printMenu();
        System.out.println("Test Complete.");
    }

    @Test
    public void testCollectMoney() {
        System.out.println("\n\nTest6 (Admin). Collect Stored Money.");
        vmAdmin.collectMoney();
        System.out.println("Test Complete.");

    }




    @Test
    public void testStockItem() {
        vmAdmin.addItem();
    }

}
