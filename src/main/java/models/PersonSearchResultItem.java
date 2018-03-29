package models;

public class PersonSearchResultItem {
    private String name;
    private String company;
    private String location;
    private String contactLink;

    public PersonSearchResultItem(String name, String company, String location, String contactLink) {
        this.name = name;
        this.company = company;
        this.location = location;
        this.contactLink = contactLink;
    }

    public String getContactLink() {
        return contactLink;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getCompany() {
        return company;
    }
}
