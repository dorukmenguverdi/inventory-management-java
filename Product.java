public class Product {

    private String id;
    private String name;
    private int quantity;
    private double price;

    public Product(String id, String name, int quantity, double price){

        if (quantity < 0) throw new IllegalArgumentException("Quantity can't be negative.");
        if (price < 0) throw new IllegalArgumentException("Price can't be negative.");

        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }


    public void setPrice(double price) {
        if (price < 0) throw new IllegalArgumentException("Price can't be negative.");
        this.price = price;
    }

    public void setQuantity(int quantity) {
        if (quantity < 0) throw new IllegalArgumentException("Quantity can't be negative.");
        this.quantity = quantity;
    }

    @Override
    public String toString(){
        return String.format("ID: %s | Name: %s | Price %.2f | Quantity: %d",
                id, name, price, quantity);
    }
}

