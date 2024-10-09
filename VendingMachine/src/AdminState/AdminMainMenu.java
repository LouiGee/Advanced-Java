package AdminState;

// Admin menu options
public enum AdminMainMenu {
    WITHDRAW_MONEY("1.Withdraw money"),
    DEPOSIT_MONEY("2.Deposit money"),
    VIEW_ITEMS("3.View items"),
    ADD_ITEM("4.Add item"),
    REMOVE_ITEM("5.Remove item"),
    BACK_TO_LOGIN("6.Back to login"),
    EXIT("7.Exit");


    private final String instruction;

    AdminMainMenu(String instruction) {
        this.instruction = instruction;
    }
    public String getInstruction() {
        return instruction;
    }

}
