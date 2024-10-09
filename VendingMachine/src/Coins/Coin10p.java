package Coins;

public class Coin10p extends Coin implements CoinAPI{

    final private double value = 0.10; // Each coin value is final

    public double getValue() {
        return value;
    };


}
