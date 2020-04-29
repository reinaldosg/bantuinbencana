package dsc.machung.bantuanbencana.Model;

public class DisasterModel {
    String id;
    String disasterPhoto;
    String disasterTitle;
    String location;
    String disasterType;
    String donationMethod;
    String disasterDate;
    String description;

    public String getDonationMethod() {
        return donationMethod;
    }

    public void setDonationMethod(String donationMethod) {
        this.donationMethod = donationMethod;
    }

    public String getDisasterPhoto() {
        return disasterPhoto;
    }

    public void setDisasterPhoto(String disasterPhoto) {
        this.disasterPhoto = disasterPhoto;
    }

    public String getDisasterTitle() {
        return disasterTitle;
    }

    public void setDisasterTitle(String disasterTitle) {
        this.disasterTitle = disasterTitle;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDisasterType() {
        return disasterType;
    }

    public void setDisasterType(String disasterType) {
        this.disasterType = disasterType;
    }

    public String getDisasterDate() {
        return disasterDate;
    }

    public void setDisasterDate(String disasterDate) {
        this.disasterDate = disasterDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
