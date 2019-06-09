package co.com.ceiba.mobile.pruebadeingreso.rest.result;

public class AddressResult {

    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private GeoResult geo;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSuite() {
        return suite;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public GeoResult getGeo() {
        return geo;
    }

    public void setGeo(GeoResult geo) {
        this.geo = geo;
    }
}
