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
}
