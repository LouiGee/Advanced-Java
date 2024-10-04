package Administrator;

public record VMItem(int code, String name, double price) {

    public VMItem(int code, String name, double price) {
        this.code = code;
        this.name = name;
        this.price = price;
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public double price() {
        return price;
    }
}

