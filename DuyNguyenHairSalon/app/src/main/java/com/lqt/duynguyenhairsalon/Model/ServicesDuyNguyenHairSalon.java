package com.lqt.duynguyenhairsalon.Model;

import java.io.Serializable;

public class ServicesDuyNguyenHairSalon implements Serializable {
    private String nameService;
    private String descriptionService;
    private boolean isSelected;
    private int priceService;

    public ServicesDuyNguyenHairSalon(String nameService, String descriptionService, boolean isSelected, int priceService) {
        this.nameService = nameService;
        this.descriptionService = descriptionService;
        this.isSelected = isSelected;
        this.priceService = priceService;
    }

    public String getNameService() {
        return nameService;
    }

    public void setNameService(String nameService) {
        this.nameService = nameService;
    }

    public String getDescriptionService() {
        return descriptionService;
    }

    public void setDescriptionService(String descriptionService) {
        this.descriptionService = descriptionService;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public int getPriceService() {
        return priceService;
    }

    public void setPriceService(int priceService) {
        this.priceService = priceService;
    }
}
