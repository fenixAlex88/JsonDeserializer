package ru.clevertec.utils.jsonDeserializer;

import ru.clevertec.utils.jsonDeserializer.annotations.JsonField;

import java.util.List;

public class Person {
    @JsonField("name")
    private String name;
    @JsonField("age")
    private int age;
    @JsonField("isStudent")
    public boolean isStudent;
    @JsonField("address")
    private Address address;
    @JsonField("courses")
    private List<Course> courses;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public boolean isStudent() {
        return isStudent;
    }

    public Address getAddress() {
        return address;
    }

    public List<Course> getCourses() {
        return courses;
    }
}