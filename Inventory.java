import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Product> products;

    public Inventory(){
        this.products = new ArrayList<>();
    }

    public boolean addProduct(Product p){
        if (findProduct(p.getId()) != null){
            return false;
        }

        products.add(p);
        return true;
    }

    public boolean removeProduct(String id) {
        for (Product product : products) {
            if (product.getId().equals(id)) {
                products.remove(product);
                return true;
            }
        }
        return false;
    }

    public boolean updateStock(String id, int newQuantity){
        for (Product product : products) {
            if (product.getId().equals(id)){
                product.setQuantity(newQuantity);
                return true;
            }
        }
        return false;
    }

    public Product findProduct(String id) {
        for (Product product : products) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        return null;
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products);
    }

}
