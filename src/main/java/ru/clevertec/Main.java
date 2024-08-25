package ru.clevertec;

import ru.clevertec.entity.Address;
import ru.clevertec.entity.Course;
import ru.clevertec.entity.Person;
import ru.clevertec.utils.jsonDeserializer.JsonDeserializer;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        String jsonString = """
                {
                    "name": "John",
                    "age": 30,
                    "isStudent": true,
                    "address": {
                        "city": "New York"
                    },
                    "courses": [
                        {
                            "title": "Math"
                        },
                        {
                            "title": "Science"
                        }
                    ]
                }
                """;
        Address address = new Address("Minsk");
        List<Course> courses = List.of(new Course("Science"));
        Person person = new Person("Alex", 30, false, address, courses);
        System.out.println(person);
        System.out.println(JsonDeserializer.deserialize(jsonString, Person.class));
    }
}


