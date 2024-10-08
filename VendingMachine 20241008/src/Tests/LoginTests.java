package Tests;
import VendingMachine.VM;
import org.junit.Assert;
import org.junit.Test;

public class LoginTests {



    //
    @Test
    public void testLoginCustomer() {
        VM vm = new VM();
        String result = vm.login("customer");
        Assert.assertEquals("customer", result);
        System.out.println("'customer' input provided correct output.");

    }

    @Test
    public void correctPasswordTestAuthentication() {
        VM vm = new VM();
        boolean result = vm.administratorAuthentication("password");
        Assert.assertTrue(result);

    }

    @Test
    public void incorrectPasswordTestAuthentication() {
        VM vm = new VM();
        boolean result = vm.administratorAuthentication("wrongpassword");
        Assert.assertFalse(result);

    }



}
