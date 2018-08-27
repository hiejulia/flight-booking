package src.main.java.com.project.flightbooking.model;




// Address - town, city, country , zip
@Embeddable
public class Address implements Comparable<Address>, Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "town")
    protected String town;

    @Column(name = "city")
    protected String city;

    @Column(name = "country",
            nullable = false)
    protected String country;

    @Column(name = "zip_code",
            nullable = false)
    protected String zipCode;

    @Override
    public int compareTo(Address otherAddress) {
        return country.compareTo(otherAddress.country)
                + zipCode.compareTo(otherAddress.zipCode);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.country);
        hash = 29 * hash + Objects.hashCode(this.zipCode);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Address other = (Address) obj;
        if (!Objects.equals(this.country, other.country)) {
            return false;
        }
        return Objects.equals(this.zipCode, other.zipCode);
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return "Address{" + "town=" + town + ", city=" + city + ", country=" + country + ", zipCode=" + zipCode + '}';
    }

}