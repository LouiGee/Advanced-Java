package Customer;

public enum acceptedCoins {
    ONE_PENNY("1p"),
    FIVE_PENCE("5p"),
    TEN_PENCE("10p"),
    TWENTY_PENCE("20p"),
    FIFTY_PENCE("50p"),
    ONE_POUND("£1"),
    TWO_POUNDS("£2");


    private final String coin;

    acceptedCoins(String coin) {
        this.coin = coin;
    }
    public String getCoin() {
        return coin;
    }
}
