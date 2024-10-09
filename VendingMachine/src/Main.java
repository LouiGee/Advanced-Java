import Exceptions.*;
import Tests.AdminTests;
import Tests.CustomerTests;
import Tests.LoginTests;

public class Main {
    public static void main(String[] args) throws InvalidInputException, CapacityFullException, DuplicateEntryException, NotInStockException, InsufficientFundsException, InsufficientChangeException {

        /*
        Interaction vminteraction = new Interaction();

        vminteraction.start();

         */


        //Login Tests
        LoginTests loginTests = new LoginTests();

        //Test 1
        loginTests.testLoginAdministrator();
        //Test 2
        loginTests.testLoginAdministratorIncorrect();
        //Test 3
        loginTests.testLoginUserIncorrect();
        //Test 4
        loginTests.testLoginExit();
        //Test 5
        loginTests.testLoginCustomer();

        //Admin Tests

        AdminTests adminTests = new AdminTests();

        //Test 1
        adminTests.testPrintMenu();
        //Test 2
        adminTests.testWithdrawMoney();
        //Test3
        adminTests.testDepositMoney();
        //Test4
        adminTests.testDepositMoneyIncorrect();
        //Test5
        adminTests.testViewItems();
        //Test6
        adminTests.testAddItem();
        //Test7
        adminTests.testAddItemDuplicateEntry();
        //Test8
        adminTests.testAddItemCapacityFull();
        //Test9
        adminTests.testRemoveItem();

        //Customer Tests

        CustomerTests customerTests = new CustomerTests();

        //Test1
        customerTests.testPrintMenu();
        //Test2
        customerTests.testCoinDeposit();
        //Test3
        customerTests.testViewItems();
        //Test4
        customerTests.testViewCurrentBalance();
        //Test5
        customerTests.testPurchaseItem();
        //Test6
        customerTests.testCollectChange();
        //Test7
        customerTests.testCollectItem();
        //Test8
        customerTests.testPurchaseItemNotInStock();
        //Test9
        customerTests.testPurchaseItemInsufficientChange();
        //Test10
        customerTests.testAnyTimeRefund();
        

        }
    }


