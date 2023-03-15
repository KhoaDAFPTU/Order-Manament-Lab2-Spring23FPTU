/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

/**
 *
 * @author DLCT
 */
public class Product {
    private String productId;
    private String productName;
    private String unit;
    private String origin;
    private double price;

    public Product() {
    }
    
    public Product(String productId, String productName, String unit, String origin, double price) {
        this.productId = productId;
        this.productName = productName;
        this.unit = unit;
        this.origin = origin;
        this.price = price;
    }

    public Product(String productId) {
        this.productId = productId;
    }
    
     @Override
    public boolean equals(Object obj) {
        Product p = (Product) obj;
        return this.productId.equalsIgnoreCase(p.productId);
    }

    @Override
    public String toString() {
        return productId + "," + productName + "," + unit + "," + origin + "," + price;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
            
}
