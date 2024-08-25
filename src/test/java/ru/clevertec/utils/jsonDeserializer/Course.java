package ru.clevertec.utils.jsonDeserializer;

import ru.clevertec.utils.jsonDeserializer.annotations.JsonField;

public class Course {
    @JsonField("title")
    private String title;

    public String getTitle() {
        return title;
    }
}