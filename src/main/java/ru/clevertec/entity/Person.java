package ru.clevertec.entity;

import ru.clevertec.utils.jsonDeserializer.annotations.JsonField;

import java.util.List;

public class Person {
    @JsonField("name")
    private String name;
    private String sername;
    @JsonField("age")
    private int age;
    @JsonField("isStudent")
    private boolean isStudent;
    @JsonField("address")
    private Address address;
    @JsonField("courses")
    private List<Course> courses;

    public Person() {}

    public Person(String name, int age, boolean isStudent, Address address, List<Course> courses) {
        this.name = name;
        this.age = age;
        this.isStudent = isStudent;
        this.address = address;
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", isStudent=" + isStudent +
                ", address=" + address +
                ", courses=" + courses +
                '}';
    }
}