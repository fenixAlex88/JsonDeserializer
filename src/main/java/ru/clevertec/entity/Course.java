package ru.clevertec.entity;

import ru.clevertec.utils.jsonDeserializer.annotations.JsonField;

public class Course {
    @JsonField("title")
    private String title;

    public Course(){}

    public Course(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Course{" +
                "title='" + title + '\'' +
                '}';
    }
}