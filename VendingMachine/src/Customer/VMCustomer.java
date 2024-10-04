package Customer;


import VMParent.VM;

public class VMCustomer extends VM implements VMCustomerAPI {

    private double currentBalance = 0; //Total amount deposited in current interaction

    @Override
    public void printMenu() {
        System.out.println("Welcome, please choose one of the following (type the number):");
        for (var instruction : CustomerMainMenu.values()) {
            System.out.println(instruction.getInstruction());
        }
    }

    @Override
    public void printAcceptedCoins() {
        System.out.println("Please insert one of the following coins:");
        for (var coin : AcceptedCoins.values()) {
            System.out.println(coin.getCoin());
        }
    }

    @Override
    public boolean isValidCoin (String coinInput) {

        for (AcceptedCoins coin : AcceptedCoins.values()) {
            if (coin.getCoin().equals(coinInput)){
                return true;

            }

        }

        return false;
    }

    @Override
    public void insertCoin(String coin) {
        this.currentBalance = this.currentBalance + Long.parseLong(coin);
        System.out.println("Current balance: " + getCurrentBalance());

    }

    @Override
    public double getCurrentBalance() {
        return currentBalance;
    }

    @Override
    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }
}

     /*

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
*/


