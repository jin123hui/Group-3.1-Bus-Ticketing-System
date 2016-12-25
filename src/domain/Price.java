package domain;

public class Price {

    private String priceID;
    private double price;

    public Price() {
    }

    public Price(String priceID) {
        this.priceID = priceID;
    }

    public Price(String priceID, double price) {
        this.priceID = priceID;
        this.price = price;
    }

    public String getPriceID() {
        return priceID;
    }

    public double getPrice() {
        return price;
    }
}
