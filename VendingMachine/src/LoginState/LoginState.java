package LoginState;

import LoadingMessage.LoadingBuffer;
import VendingMachine.VendingMachine;

public class LoginState extends VendingMachine implements LoginStateAPI {

    // Login workflow
    public String login(String userInput, String adminPasswordInput) {

        boolean validInput = false;

        while (!validInput) {

            if (userInput.equalsIgnoreCase("customer")) {

                LoadingBuffer.loading();

                validInput = true;

                return "customer";
            } else if (userInput.equalsIgnoreCase("administrator")) {

                LoadingBuffer.loading();

                if (isLockedAdminAccount()) {

                    System.out.println("Admin account is locked. Please contact support.");

                    validInput = true;

                    // if authentication successful then return true
                } else {

                    validInput = administratorAuthentication(adminPasswordInput);
                }

                if (validInput) {
                    return "administrator";
                } else {
                    return "Null";
                }

            } else if (userInput.equalsIgnoreCase("exit")) {

                validInput = true;
                return "exit";

            } else {

                LoadingBuffer.loading();

                System.out.println("Oh no! Incorrect input... please try again");

                validInput = true;

            }


        }
        return "Program terminated.";
    }

    // Login workflow
    public String login(String userInput) {

        boolean validInput = false;

        while (!validInput) {

            if (userInput.equalsIgnoreCase("customer")) {

                LoadingBuffer.loading();

                validInput = true;

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
                return "exit";

            } else {

                LoadingBuffer.loading();

                System.out.println("Oh no! Incorrect input... please try again");

                validInput = true;
            }


        }
        return "Program terminated.";
    }


    // Method used in login() to authenticate admin
    public boolean administratorAuthentication(String adminPasswordInput) {

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