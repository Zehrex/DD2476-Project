38
https://raw.githubusercontent.com/piyush6348/Design-Patterns/master/Visitor%20Pattern/src/CricketBat.java
public class CricketBat implements IShoppingElement {
    private String brandName;
    private int price;

    public CricketBat(String brandName, int price) {
        this.brandName = brandName;
        this.price = price;
    }

    public String getBrandName() {
        return brandName;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public Double visit(IVisitor visitor) {
        return visitor.accept(this);
    }
}
