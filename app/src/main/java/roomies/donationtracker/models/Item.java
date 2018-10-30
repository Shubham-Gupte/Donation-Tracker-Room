package roomies.donationtracker.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Item implements Parcelable {
    private String type; //enum later
    private double cost;
    private String donationDate;
    private String donationLocation;
    private String name;

    public Item (String name, String type, double cost, String donationDate, String donationLocation) {
        this.name = name;
        this.type = type;
        this.cost = cost;
        this.donationDate = donationDate;
        this.donationLocation = donationLocation;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
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

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.type);
        dest.writeString(this.name);
        dest.writeString(this.donationLocation);
        dest.writeString(this.donationDate);
        dest.writeDouble(this.cost);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public Item(Parcel in){
        this.type = in.readString();
        this.name = in.readString();
        this.donationLocation =  in.readString();
        this.donationDate =  in.readString();
        this.cost =  in.readDouble();
    }

}
