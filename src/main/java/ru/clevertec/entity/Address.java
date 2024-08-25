package ru.clevertec.entity;

import ru.clevertec.utils.jsonDeserializer.annotations.JsonField;

public class Address {
    @JsonField("city")
    private String city;

    public Address(){}

    public Address(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                '}';
    }
}