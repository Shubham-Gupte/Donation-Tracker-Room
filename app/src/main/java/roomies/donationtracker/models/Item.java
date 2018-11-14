package roomies.donationtracker.models;

public class Item {
    private String type; //enum later
    private double cost;
    private String donationDate;
    private String donationLocation;
    private String name;

    /**
     * Creates a new item
     * @param name name of item
     * @param type type of item
     * @param cost cost of item
     * @param donationDate date on donation of item
     * @param donationLocation where the item was donated
     */
    public Item (String name, String type, double cost, String donationDate, String donationLocation) {
        this.name = name;
        this.type = type;
        this.cost = cost;
        this.donationDate = donationDate;
        this.donationLocation = donationLocation;
    }

    /**
     * Returns the cost of an ite,
     * @return the cost
     */
    public double getCost() {
        return cost;
    }

    /**
     * Sets the cost of an item
     * @param cost the cost to set
     */
    public void setCost(double cost) {
        this.cost = cost;
    }

    /**
     * Gets the type of item
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type of Item
     * @param type the new type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the donation date
     * @return donation date
     */
    public String getDonationDate() {
        return donationDate;
    }

    /**
     * Sets the donation Date of an item
     * @param donationDate donation date
     */
    public void setDonationDate(String donationDate) {
        if (!donationDate.matches("\\d{2}/\\d{2}/\\d{4}")) {
            throw new IllegalArgumentException("Date has to match the patter 00/00/0000");
        }
        this.donationDate = donationDate;
    }

    /**
     * Gets the location of a donation
     * @return the location
     */
    public String getDonationLocation() {
        return donationLocation;
    }

    /**
     * Sets the location of a donation
     * @param donationLocation the location
     */
    public void setDonationLocation(String donationLocation) {
        this.donationLocation = donationLocation;
    }

    /**
     * Gets the location name
     * @return location name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of an item
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

}
