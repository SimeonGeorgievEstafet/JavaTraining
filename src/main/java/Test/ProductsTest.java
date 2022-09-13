package Test;

import Helpers.ProductsHelper;
import POJO.Product;

public class ProductsTest {
    public static void main(String[] args) {
        ProductsHelper ph = new ProductsHelper();
        Product product = ph.CreateProduct();
        ph.save(product);
        ph.getByID(18);
        ph.delete(18);
        ph.update(ph.CreateProduct(),3);

    }
}
