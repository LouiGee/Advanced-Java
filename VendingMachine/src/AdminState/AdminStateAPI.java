package AdminState;

import Exceptions.CapacityFullException;
import Exceptions.DuplicateEntryException;

import java.util.InputMismatchException;

public interface AdminStateAPI {

    public void viewInventory();

    public void addItem() throws CapacityFullException, DuplicateEntryException;

    public void withdrawMoney();

    public void depositMoney(String inputMoney) throws InputMismatchException;

    public void printMenu();

    public void removeItem();




}
