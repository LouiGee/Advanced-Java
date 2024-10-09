package AdminState;

import Exceptions.CapacityFullException;
import Exceptions.DuplicateEntryException;
import Exceptions.InvalidInputException;

import java.util.InputMismatchException;

public interface AdminStateAPI {

    public void addItem(String codeInput, String nameInput, String priceInput, String quantityInput) throws CapacityFullException, DuplicateEntryException;

    public void withdrawMoney();

    public void depositMoney(String inputMoney) throws InvalidInputException;

    public void printMenu();

    public void removeItem(String codeInput);




}
