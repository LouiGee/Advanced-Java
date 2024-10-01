package Customer;

public class VMCustomer extends VendingMachine implements VMCustomerAPI {

    @Override
    public void printMenu() {
         for (var instruction : mainMenuOptions.values()) {
            System.out.println(instruction.getInstruction());
        }
    }

    @Override
    public void printAcceptedCoins() {
        System.out.println("Please insert one of the following coins:");
        for (var coin : acceptedCoins.values()) {
            System.out.println(coin.getCoin());
        }
    }

    @Override
    public String validateCoinInput() {
        return "";
    }

    @Override
    public String validateCoinInput(String coinInput) {

        if(acceptedCoins.values().equals(coinInput)) {
            return "Deposit successful.";
        }
        else {
            return "Invalid coin. Find coin in collection tray.";
        }
    }

    @Override



    @Override
    public void insertCoin(Coin coin) {


    }

    @Override
    public int getCurrentBalance() {
        return 0;
    }

    @Override
    public void selectItem(String code) throws PurchaseException {

    }

    @Override
    public String getItemCode() {
        return "";
    }

    @Override
    public void requestRefund() {

    }

    @Override
    public void requestPurchaseItem() throws PurchaseException {

    }

    @Override
    public void requestChange() {

    }

    @Override
    public void collect() {

    }
}

