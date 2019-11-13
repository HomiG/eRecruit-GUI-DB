package JavaFX.TableObject;


import javafx.beans.property.SimpleStringProperty;

public class EtairiaObject {


    public String getName() {
        return name.get();
    }

    public String getAfm() {
        return afm.get();
    }

    public String getDoy() {
        return doy.get();
    }

    public String getPhone() {
        return phone.get();
    }

    public String getStreet() {
        return street.get();
    }

    public String getNum() {
        return num.get();
    }

    public String getCity() {
        return city.get();
    }

    public String getCountry() {
        return country.get();
    }

    private SimpleStringProperty name;
    private SimpleStringProperty afm;
    private SimpleStringProperty doy;
    private SimpleStringProperty phone;

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public void setStreet(String street) {
        this.street.set(street);
    }

    public void setNum(String num) {
        this.num.set(num);
    }

    public void setCity(String city) {
        this.city.set(city);
    }

    public void setCountry(String country) {
        this.country.set(country);
    }

    private SimpleStringProperty street;
    private SimpleStringProperty num;
    private SimpleStringProperty city;
    private SimpleStringProperty country;


    public EtairiaObject(String name, String afm, String doy, String phone, String street, String num, String city, String country) {
        this.name = new SimpleStringProperty(name);
        this.afm = new SimpleStringProperty(afm);;
        this.doy = new SimpleStringProperty(doy);;
        this.phone = new SimpleStringProperty(phone);;
        this.street = new SimpleStringProperty(street);;
        this.num = new SimpleStringProperty(num);;
        this.city = new SimpleStringProperty(city);;
        this.country = new SimpleStringProperty(country);;
    }
}
