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

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDonationDate() {
        return donationDate;
    }

    public void setDonationDate(String donationDate) {
        this.donationDate = donationDate;
    }

    public String getDonationLocation() {
        return donationLocation;
    }

    public void setDonationLocation(String donationLocation) {
        this.donationLocation = donationLocation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
