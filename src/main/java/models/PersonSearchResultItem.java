package models;

public class PersonSearchResultItem {
    private String contactLink;
    private String name;
    private String profession;
    private String location;
    private String additionalInfo;

    public PersonSearchResultItem(String contactLink, String name,
                                  String profession, String location, String additionalInfo) {
        this.contactLink = contactLink;
        this.name = name;
        this.profession = profession;
        this.location = location;
        this.additionalInfo = additionalInfo;
    }

    public String getContactLink() {
        return contactLink;
    }

    public String getName() {
        return name;
    }

    public String getProfession() {
        return profession;
    }

    public String getLocation() {
        return location;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }
}
