/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaproject;

/**
 *
 * @author utkus
 */
public class Product {
    private String productName;
    private String productType;
    private int productId;
    private int stock ;
    private int price;
    private String imageId;
    private boolean change;

    Product(int id_, String name_, String type_, int price_, int stock_, String imageId_) {
        
        this.productName = name_;
        this.productType = type_;
        this.price = price_;
        this.stock = stock_;
        this.productId = id_;
        this.imageId = imageId_;
        
        
    }
    
    Product(int id_, String name_, String type_, int price_, int stock_) {
        
        this.productName = name_;
        this.productType = type_;
        this.price = price_;
        this.stock = stock_;
        this.productId = id_;
       
        
    }
    
    

    Product() {
        
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }
    
    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
   

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    int getStock(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
