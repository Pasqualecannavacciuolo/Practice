package com.automobile;

public class Auto {
    private String brand, nationality;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Auto(String brand, String nationality) {
        this.brand = brand;
        this.nationality = nationality;
    }
}
