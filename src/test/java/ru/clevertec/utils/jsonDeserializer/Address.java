package ru.clevertec.utils.jsonDeserializer;

import ru.clevertec.utils.jsonDeserializer.annotations.JsonField;

public class Address {
    @JsonField("city")
    private String city;

    public String getCity() {
        return city;
    }
}