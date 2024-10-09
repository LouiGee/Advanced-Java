package Tests;

import CustomerState.CustomerState;
import Exceptions.InsufficientChangeException;
import Exceptions.InsufficientFundsException;
import Exceptions.NotInStockException;
import VendingMachine.VendingMachine;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;

import static org.junit.Assert.assertThrows;

public class CustomerTests {

   CustomerState vmCustomer = new CustomerState("5");

   //Test1
   @Test
   public void testPrintMenu() {
      System.out.println("\n\nTest1 (Customer) Print main menu.");
      vmCustomer.printMenu();
      System.out.println("Test passed.");
   }

   //Test2
   @Test
   public void testCoinDeposit() {
      System.out.println("\n\nTest2 (Customer) Deposit valid coin '2.00'.");
      System.out.println("Current balance £: " + vmCustomer.getCurrentBalanceNumber() + ".");
      vmCustomer.depositCoin("2.00");
      vmCustomer.depositCoin("done");
      System.out.println("Test passed.");
   }

   //Test3
   @Test
   public void testViewItems() {
      System.out.println("\n\nTest3 (Customer) View items available to purchase.");
      //vmCustomer.initialiseItems();
      vmCustomer.viewInventory();
      System.out.println("Test passed.");
   }

   //Test4
   @Test
   public void testViewCurrentBalance() {
      System.out.println("\n\nTest4 (Customer) View current balance at start of transaction.");
      System.out.println("Balance: £" + vmCustomer.getCurrentBalanceNumber() + ".");
      System.out.println("Test passed.");
   }

   //Test5
   @Test
   public void testPurchaseItem() throws NotInStockException, InsufficientFundsException, InsufficientChangeException {
      System.out.println("\n\nTest5 (Customer) Customer purchases item code '03', change is then refunded.");
      vmCustomer.initialiseCoins();
      vmCustomer.setTotalStoredMoney();
      vmCustomer.purchaseItem("03");
      System.out.println("Test passed.");
   }

   //Test6
   @Test
   public void testCollectChange() {
      System.out.println("\n\nTest6 (Customer) Customer collects change.");
      System.out.println("Amount in refund tray : £" + vmCustomer.getReturnBucket() + ".");
      vmCustomer.collectRefund();
      System.out.println("Amount in refund tray : £" + vmCustomer.getReturnBucket() + ".");
      System.out.println("Test passed.");
   }

   //Test7
   @Test
   public void testCollectItem() {
      System.out.println("\n\nTest7 (Customer) Customer collects item.");
      System.out.println("Is there an item in the tray :" + vmCustomer.isItemInTray() + ".");
      vmCustomer.collectItem();
      System.out.println("Is there an item in the tray :" + vmCustomer.isItemInTray() + ".");
      System.out.println("Test passed.");
   }

   //Test8
   @Test
   public void testPurchaseItemNotInStock() throws NotInStockException, InsufficientFundsException, InsufficientChangeException {
      System.out.println("\n\nTest8 (Customer) Customer deposits '1.00' and purchases item code '02', however item is out of stock.");
      //Deposit £1
      vmCustomer.depositCoin("1.00");
      //Refund bucket state before
      Exception exception = assertThrows(
              NotInStockException.class,
              () -> vmCustomer.purchaseItem("02"));

      Assertions.assertEquals("Coke 330ml is out of stock, please choose another item.", exception.getMessage());
      System.out.println("NotInStockException exception thrown.");
      vmCustomer.refundBalance();
      System.out.println("Test passed.");

   }

   //Test9
   @Test
   public void testPurchaseItemInsufficientChange() throws NotInStockException, InsufficientFundsException, InsufficientChangeException {
      System.out.println("\n\nTest9 (Customer) Customer deposits '2.00' and purchases item code '02' however there is not enough change.");
      //Empty change from machine and recalculate amount in machine
      vmCustomer.setStored1pCoins(new ArrayList<>());
      vmCustomer.setStored2pCoins(new ArrayList<>());
      vmCustomer.setStored5pCoins(new ArrayList<>());
      vmCustomer.setStored10pCoins(new ArrayList<>());
      vmCustomer.setStored20pCoins(new ArrayList<>());
      vmCustomer.setStored50pCoins(new ArrayList<>());
      vmCustomer.setStored100pCoins(new ArrayList<>());
      vmCustomer.setStored200pCoins(new ArrayList<>());
      vmCustomer.setTotalStoredMoney();
      System.out.println("Balance: £" + vmCustomer.getCurrentBalanceNumber() + ".");

      //Deposit £2
      vmCustomer.depositCoin("2.00");

      //Refund bucket state before
      Exception exception = assertThrows(
              InsufficientChangeException.class,
              () -> vmCustomer.purchaseItem("03"));

      Assertions.assertEquals("Insufficient change. Current balance refunded. Contact admin.", exception.getMessage());
      System.out.println("InsufficientChangeException thrown.");
      //Refund bucket state after
      System.out.println("Amount in refund bucket: £" + vmCustomer.getReturnBucket());
      vmCustomer.collectRefund();
      System.out.println("Amount in refund bucket: £" + vmCustomer.getReturnBucket());

      System.out.println("Test passed.");
   }

   @Test
   public void testAnyTimeRefund() {
      System.out.println("\n\nTest10 (Customer) Customer request refund midway through transaction.");
      System.out.println("Amount in refund bucket: £" + vmCustomer.getReturnBucket());
      vmCustomer.depositCoin("2.00");
      vmCustomer.refundBalance();
      System.out.println("Amount in refund bucket: £" + vmCustomer.getReturnBucket());
      vmCustomer.collectRefund();
      System.out.println("Amount in refund bucket: £" + vmCustomer.getReturnBucket());
      System.out.println("Test passed.");
   }










}
