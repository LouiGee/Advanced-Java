package CustomerState;
//List of accepted coins
public enum AcceptedCoins {

    ONE_PENNY("0.01"),
    FIVE_PENCE("0.05"),
    TEN_PENCE("0.10"),
    TWENTY_PENCE("0.20"),
    FIFTY_PENCE("0.50"),
    ONE_POUND("1.00"),
    TWO_POUNDS("2.00");

    private final String coin;

    AcceptedCoins(String coin) {
        this.coin = coin;
    }
    public String getCoin() {
        return coin;
    }
}
