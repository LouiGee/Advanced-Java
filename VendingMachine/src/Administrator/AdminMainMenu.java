package Administrator;

public enum AdminMainMenu {
    COLLECT_MONEY("1.Collect money"),
    VIEW_ITEMS("2.View items"),
    ADD_ITEM("3.Add item"),
    REMOVE_ITEM("4.Remove item");

    private final String instruction;

    AdminMainMenu(String instruction) {
        this.instruction = instruction;
    }
    public String getInstruction() {
        return instruction;
    }

}
