package Administrator;

import Customer.VMCustomerAPI;
import LoadingMessage.LoadingBuffer;
import VMParent.VM;

import java.util.ArrayList;
import java.util.List;

public class VMAdmin extends VM implements VMAdminAPI {

    @Override
    public void addItem(int code, String name, double price) {
        LoadingBuffer.loading();
        if(super.items.size() <= VM.MAX_ITEMS){
        super.items.add(new VMItem(code, name, price));}
        else{ System.out.println("Vending machine full. You must remove an item before you can add another.");
    }}

    @Override
    public void collectMoney() {
        LoadingBuffer.loading();
        System.out.println("Safe open, please collect £" + super.getStoredMoney());
        super.setStoredMoney(0);


    }


}
