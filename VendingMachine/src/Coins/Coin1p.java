package Coins;

public class Coin1p extends Coin implements CoinAPI{

    final private double value = 0.01; // Each coin value is final

    public double getValue() {
        return value;
    };


}
