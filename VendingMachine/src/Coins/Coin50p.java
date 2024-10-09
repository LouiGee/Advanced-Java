package Coins;

public class Coin50p extends Coin implements CoinAPI{

    final private double value = 0.50; // Each coin value is final

    public double getValue() {
        return value;
    };

}
