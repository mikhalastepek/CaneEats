package edu.bus.bte324.canehub.infoclasses;

public class AddressInfo {
    private final String street;
    private final String line1Address;
    private final String line2Address;
    private final String zipCode;

    private final String state;

    public String getStreet() {
        return street;
    }

    public String getLine1Address() {
        return line1Address;
    }

    public String getLine2Address() {
        return line2Address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    private final String city;

    public AddressInfo(String street, String line1Address, String line2Address, String state, String city, String zipCode) {
        this.street = street;
        this.line1Address = line1Address;
        this.line2Address = line2Address;
        this.zipCode = zipCode;
        this.state = state;
        this.city = city;
    }

    @Override
    public String toString() {
        return "AddressInfo{" +
                "street='" + street + '\'' +
                ", line1Address='" + line1Address + '\'' +
                ", line2Address='" + line2Address + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

    public String toStringForFile() {
        return line1Address +
                "," + street + "," +line2Address +", " + city +", " + state+
        ", " + zipCode
                 ;
    }
}
