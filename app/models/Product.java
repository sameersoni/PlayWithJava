package models;

import play.data.validation.Constraints;
import java.util.ArrayList;
import java.util.List;

public class Product {

    private static List<Product> products;
    @Constraints.Required
    public String ean;
    @Constraints.Required
    public String name;
    public String description;

    public Product() {}

    public Product(String ean, String name, String description){
        this.ean = ean;
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("%s - %s", ean, name);
    }

    static {
        products = new ArrayList<>();
        products.add(new Product("1111111111111", "Paperclips 1",
                "Paperclips description 1"));
        products.add(new Product("2222222222222", "Paperclips 2",
                "Paperclips description "));
        products.add(new Product("3333333333333", "Paperclips 3",
                "Paperclips description 3"));
        products.add(new Product("4444444444444", "Paperclips 4",
                "Paperclips description 4"));
        products.add(new Product("5555555555555", "Paperclips 5",
                "Paperclips description 5"));
    }

    public static List<Product> findAll() {
        return new ArrayList<>(products);
    }

    public static Product findByEan(String ean){
        for (Product candidate :  products) {
            if (candidate.ean.equals(ean)) {
                return candidate;
            }
        }
        return null;
    }

    public static List<Product> findByName(String term) {
        final List<Product> results = new ArrayList<>();
        for (Product candidate : products) {
            if (candidate.name.toLowerCase().equals(term)) {
                results.add(candidate);
            }
        }
        return results;
    }

    public static boolean remove(Product product) {
        return products.remove(product);
    }

    public void save(){
        products.remove(findByEan(this.ean));
        products.add(this);
    }
}
