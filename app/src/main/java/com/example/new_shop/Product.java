package com.example.new_shop;
public class Product {
    private long id;
    private String imagePath;
    private String productName;
    private String productPrice;
    private String productQuantity;
    private String description;
    private String quantitysold;
    private String quantityremaining;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(String productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getQuantityremaining() {
        return quantityremaining;
    }

    public void setQuantityremaining(String quantityremaining) {
        this.quantityremaining = quantityremaining;
    }

    public String getQuantitysold() {
        return quantitysold;
    }

    public void setQuantitysold(String quantitysold) {
        this.quantitysold = quantitysold;
    }

}
