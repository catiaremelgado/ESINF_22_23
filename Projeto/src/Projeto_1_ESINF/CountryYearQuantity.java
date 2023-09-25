package Projeto_1_ESINF;

public class CountryYearQuantity {

    private String country;
    private int year, quantity;

    public CountryYearQuantity(String country, int year, int quantity) {
        this.country = country;
        this.year = year;
        this.quantity = quantity;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
