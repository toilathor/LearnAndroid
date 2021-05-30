package com.lqt.duynguyenhairsalon.Model;

public class ProductDuyNguyenHairSalon {
    private int ID_Product;
    private String Name_Product;
    private int Price_Produce;
    private int Amount_Produce;
    private int ID_Species_Product;

    public ProductDuyNguyenHairSalon(int ID_Product, String name_Product, int price_Produce, int amount_Produce, int ID_Species_Product) {
        this.ID_Product = ID_Product;
        Name_Product = name_Product;
        Price_Produce = price_Produce;
        Amount_Produce = amount_Produce;
        this.ID_Species_Product = ID_Species_Product;
    }

    public int getID_Product() {
        return ID_Product;
    }

    public void setID_Product(int ID_Product) {
        this.ID_Product = ID_Product;
    }

    public String getName_Product() {
        return Name_Product;
    }

    public void setName_Product(String name_Product) {
        Name_Product = name_Product;
    }

    public int getPrice_Produce() {
        return Price_Produce;
    }

    public void setPrice_Produce(int price_Produce) {
        Price_Produce = price_Produce;
    }

    public int getAmount_Produce() {
        return Amount_Produce;
    }

    public void setAmount_Produce(int amount_Produce) {
        Amount_Produce = amount_Produce;
    }

    public int getID_Species_Product() {
        return ID_Species_Product;
    }

    public void setID_Species_Product(int ID_Species_Product) {
        this.ID_Species_Product = ID_Species_Product;
    }
}
