package LoginState;

import LoadingMessage.LoadingBuffer;
import VendingMachine.VendingMachine;

public class LoginState extends VendingMachine implements LoginStateAPI {

    public LoginState(String maxItems) {
        super(maxItems);
    }

    // OVERLOADED METHOD
    public String login(String userInput, String adminPasswordInput) {

        boolean validInput = false;

        // if input is valid control flow will either be 'customer' or 'administrator'
        while (!validInput) {

            if (userInput.equalsIgnoreCase("customer")) {

                LoadingBuffer.loading();

                validInput = true;

                System.out.println("Login successful.");

                return "customer";
            } else if (userInput.equalsIgnoreCase("administrator")) {

                //Loading
                LoadingBuffer.loading();

                // Item locked after 3 password attempts
                if (isLockedAdminAccount()) {

                    System.out.println("Admin account is locked. Please contact support.");

                    validInput = true;

                    // if authentication successful then return true
                } else {

                    validInput = administratorAuthentication(adminPasswordInput);
                }

                //if authentication successful print message otherwise repeat authentication loop
                if (validInput) {

                    System.out.println("Login successful.");
                    return "administrator";

                } else {
                    return "Null";
                }

                // input is exit then exit program
            } else if (userInput.equalsIgnoreCase("exit")) {

                validInput = true;
                return "exit";

            } else {

                //Loading
                LoadingBuffer.loading();

                System.out.println("Oh no! Incorrect input... please try again.");

                validInput = true;

            }


        }
        return "Program terminated.";
    }

    // OVERLOADED METHOD
    public String login(String userInput) {

        boolean validInput = false;


        while (!validInput) {

            // if input is valid control flow will either be 'customer' or 'administrator'
            if (userInput.equalsIgnoreCase("customer")) {

                //Loading
                LoadingBuffer.loading();

                validInput = true;

                //Confirmation message
                System.out.println("Login successful.");

                return "customer";
            } else if (userInput.equalsIgnoreCase("administrator")) {

                LoadingBuffer.loading();

                if (isLockedAdminAccount()) {

                    System.out.println("Admin account is locked. Please contact support.");

                    LoadingBuffer.loading();

                    // if authentication successful then return true
                }

            } else if (userInput.equalsIgnoreCase("exit")) {

                validInput = true;

                System.out.println("Goodbye, see you soon!");
                return "exit";

            } else {

                //Loading
                LoadingBuffer.loading();

                //Could be an exception
                System.out.println("Oh no! Incorrect input... please try again.");

                validInput = true;
            }


        }
        return "Program terminated.";
    }


    // Method used in login() to authenticate admin
    public boolean administratorAuthentication(String adminPasswordInput) {

        //Initialise variables
        String adminPassword = "password";
        boolean validPassword = false;
        boolean return_value = true;
        Integer passwordAttempts = 0;
        Integer maxLoginAttempts = 3;

        // Loop breaks if one of these true
        while (!validPassword && !(isLockedAdminAccount())) {

            if (adminPassword.equals(adminPasswordInput)) {
                validPassword = true;

            } else if (adminPasswordInput.equalsIgnoreCase("exit")) {
                System.out.println("Goodbye!");

                validPassword = true;

            } else if (adminPassword != adminPasswordInput) {

                passwordAttempts++;

                if (passwordAttempts < maxLoginAttempts) {
                    System.out.println("Oops, wrong password! You have " + (maxLoginAttempts - passwordAttempts) + " attempts remaining.");
                }

                if (passwordAttempts >= maxLoginAttempts) {
                    System.out.println("Oops, you have entered the wrong password and you have reached the password attempt limit! Please contact the admin team to reset your password.");
                    setLockedAdminAccount(true);
                    return_value = false;


                }

            }

        }

        return return_value;
    }

}