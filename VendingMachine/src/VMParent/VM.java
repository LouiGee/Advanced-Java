package VMParent;

import Administrator.VMItem;

import java.util.ArrayList;
import java.util.List;

public class VM {

    protected int storedMoney = 100; //Total money in the vending machine
    protected List<VMItem> items = new ArrayList<>();
    protected static final int MAX_ITEMS = 5;
    protected static boolean lockedAdminAccount = false;



    public void viewInventory() {
        for (VMItem item : items) {
            System.out.println(item);
        }

    }

    public int getStoredMoney() {
        return storedMoney;
    }

    public void setStoredMoney(int storedMoney) {
        this.storedMoney = storedMoney;
    }

    public static boolean isLockedAdminAccount() {
        return lockedAdminAccount;
    }
    public static void setLockedAdminAccount(boolean lockedAdminAccount) {}
}
