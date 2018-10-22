package roomies.donationtracker;

public class Item {
    private String type; //enum later
    private float cost;
    private String donationDate;
    private String donationLocation;
    private String name;

    public Item (String name, String type, float cost, String donationDate, String donationLocation) {
        this.name = name;
        this.type = type;
        this.cost = cost;
        this.donationDate = donationDate;
        this.donationLocation = donationLocation;
    }
    public String getItemName() {
        return this.name;
    }

    public String getItemType() {
        return this.type;
    }

    public float getItemCost() {
        return this.cost;
    }

    public String getItemDonateDate() {
        return this.donationDate;
    }

    public String getItemDonateLocation() {
        return this.donationLocation;
    }

    public void setItemName(String name) {
        this.name = name;
    }

    public void setItemType(String type) {
        this.type = type;
    }

    public void setItemCost(float cost) {
        this.cost = cost;
    }

    public void setItemDonateDate(String date) {
        this.donationDate = date;
    }

    public void setItemDonateLocation(String loc) {
        this.donationLocation = loc;
    }
}
