package Customer;

public enum CustomerMainMenu {
    INSERT_COIN("1.Insert coins"),
    VIEW_ITEMS("2.View items"),
    CURRENT_BALANCE("3.View current balance"),
    PURCHASE_ITEM("4.Purchase item"),
    REQUEST_REFUND("5.Request refund"),
    COLLECT_REFUND("6.Collect refund"),
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
