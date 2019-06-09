package co.com.ceiba.mobile.pruebadeingreso.rest.result;

public class UsersResult {

    private int id;
    private String name;
    private String username;
    private String email;
    private AddressResult address;
    private String phone;
    private String website;
    private CompanyResult company;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AddressResult getAddress() {
        return address;
    }

    public void setAddress(AddressResult address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public CompanyResult getCompany() {
        return company;
    }

    public void setCompany(CompanyResult company) {
        this.company = company;
    }
}
