package models;

/**
 * Created by vinee_000 on 20-01-2018.
 */

public class Asset {
    private String category;
    private String assetType;
    private double wattage;
    private int quantity;
    private double totalWattage;

    public Asset(String category, String assetType, double wattage, int quantity, double totalWattage) {
        this.category = category;
        this.assetType = assetType;
        this.wattage = wattage;
        this.quantity = quantity;
        this.totalWattage = totalWattage;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public double getWattage() {
        return wattage;
    }

    public void setWattage(double wattage) {
        this.wattage = wattage;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalWattage() {
        return totalWattage;
    }

    public void setTotalWattage(double totalWattage) {
        this.totalWattage = totalWattage;
    }
}
