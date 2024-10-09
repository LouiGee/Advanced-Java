package Tests;
import LoadingMessage.LoadingBuffer;
import LoginState.LoginState;
import org.junit.Assert;
import org.junit.Test;

public class LoginTests {

    LoginState vmLogin = new LoginState("5");

    //Test 1
    @Test
    public void testLoginAdministrator() {
        System.out.println("\n\nTest1 (Login). User has typed 'administrator' at login and entered the correct password 'password'.");
        System.out.println("Are you a 'customer' or an 'administrator'? Type 'exit' at anytime to stop interaction.");
        System.out.println("Please enter your administrator password:");
        String result = vmLogin.login("administrator","password");
        Assert.assertEquals("administrator", result);
        System.out.println("Test Passed.");

    }

    //Test 2
    @Test
    public void testLoginAdministratorIncorrect() {
        System.out.println("\n\nTest2a (Login). User has typed 'administrator' and entered the incorrect password 'incorrectpassword' 3 times.");
        System.out.println("Are you a 'customer' or an 'administrator'? Type 'exit' at anytime to stop interaction.");
        LoadingBuffer.loading();
        System.out.println("Please enter your administrator password:");
        vmLogin.login("administrator","incorrectpassword");
        System.out.println(" \n Test2b (Login).The Admin Account is now locked to prevent bruteforce attempts. Attempting again provides the follwing message: ");
        vmLogin.login("administrator","incorrectpassword");
        System.out.println("Test Passed.");
    }

    //Test 3
    @Test
    public void testLoginUserIncorrect() {
        System.out.println("\n\nTest3 (Login). User has typed 'notauser' at the login page");
        System.out.println("Are you a 'customer' or an 'administrator'? Type 'exit' at anytime to stop interaction.");
        vmLogin.login("notauser");
        System.out.println("Test Passed.");
    }

    //Test 4
    @Test
    public void testLoginExit() {
        System.out.println("\n\nTest4 (Login). User has typed 'exit' at the login page");
        System.out.println("Are you a 'customer' or an 'administrator'? Type 'exit' at anytime to stop interaction.");
        String result = vmLogin.login("exit");
        Assert.assertEquals("exit", result);
        System.out.println("Test Passed.");
    }


    //Test 5
    @Test
    public void testLoginCustomer() {
        System.out.println("\n\nTest5 (Login). User has typed 'customer' at the login page");
        String result = vmLogin.login("customer");
        Assert.assertEquals("customer", result);
        System.out.println("Test Passed");

    }




}
