package Customer;

public enum CustomerMainMenu {
    INSERT_COIN("1.Insert coins"),
    VIEW_ITEMS("2.View items available for purchase"),
    CURRENT_BALANCE("3.View current balance"),
    SELECT_ITEM("4.Select item"),
    PURCHASE_ITEM("5.Purchase item"),
    REQUEST_REFUND("6.Request refund"),
    COLLECT_ITEM("7.Collect item"),
    EXIT("8.Exit");


    private final String instruction;

    CustomerMainMenu(String instruction) {
        this.instruction = instruction;
    }
    public String getInstruction() {
        return instruction;
    }


}
