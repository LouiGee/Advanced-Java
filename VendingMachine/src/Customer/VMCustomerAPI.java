
package Customer;


public interface VMCustomerAPI {

    // Print menu to customer
    void printMenu();

    // Print out list of accepted coins
    void printAcceptedCoins();

    //Validate coin input
    boolean isValidCoin (String coinInput);

    // Customer inserts a coin
    void insertCoin(String coin);
    // Returns current balance of inserted coins



    double getCurrentBalance();

    void setCurrentBalance(double balance);

    /*

    // Customer selects item
    // throws exception if not found
    void selectItem(String code) throws PurchaseException;
    // Returns currently selected item’s code
    String getItemCode();
    // Customer requests a refund, coins to be placed in return bucket
    void requestRefund();
    // Customer requests purchasing selected item
    // throws exception on error, puts item in return bucket
    public void requestPurchaseItem() throws PurchaseException;
    // Customer requests change, coins to be placed in return bucket
    void requestChange();
    // Customer collects refund, or item and change, from return bucket
    // Update states of the vending machine and return bucket
    void collect();

     */

}
