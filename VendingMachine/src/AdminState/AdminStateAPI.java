package AdminState;

import Exceptions.*;

public interface AdminStateAPI {

    public void addItem(String codeInput, String nameInput, String priceInput, String quantityInput) throws CapacityFullException, DuplicateEntryException;

    public void withdrawMoney();

    public void depositMoney(String inputMoney) throws InvalidInputException;

    public void printMenu();

    public void removeItem(String codeInput) throws ItemNotFoundException;




}
