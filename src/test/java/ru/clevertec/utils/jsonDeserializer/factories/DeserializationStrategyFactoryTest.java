package ru.clevertec.utils.jsonDeserializer.factories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.clevertec.utils.jsonDeserializer.Person;
import ru.clevertec.utils.jsonDeserializer.strategies.DeserializationStrategy;
import ru.clevertec.utils.jsonDeserializer.strategies.DeserializationStrategyImpl.*;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

@DisplayName("Deserialization Strategy Factory tests")
public class DeserializationStrategyFactoryTest {

    @Test
    @DisplayName("Test strategy for String field")
    public void testGetStrategyForString() throws NoSuchFieldException {
        Field field = TestClass.class.getDeclaredField("stringField");
        DeserializationStrategy strategy = DeserializationStrategyFactory.getStrategy(field);
        assertInstanceOf(StringDeserializationStrategy.class, strategy);
    }

    @Test
    @DisplayName("Test strategy for int field")
    public void testGetStrategyForInt() throws NoSuchFieldException {
        Field field = TestClass.class.getDeclaredField("intField");
        DeserializationStrategy strategy = DeserializationStrategyFactory.getStrategy(field);
        assertInstanceOf(IntegerDeserializationStrategy.class, strategy);
    }

    @Test
    @DisplayName("Test strategy for Integer field")
    public void testGetStrategyForInteger() throws NoSuchFieldException {
        Field field = TestClass.class.getDeclaredField("integerField");
        DeserializationStrategy strategy = DeserializationStrategyFactory.getStrategy(field);
        assertInstanceOf(IntegerDeserializationStrategy.class, strategy);
    }

    @Test
    @DisplayName("Test strategy for boolean field")
    public void testGetStrategyForBoolean() throws NoSuchFieldException {
        Field field = TestClass.class.getDeclaredField("booleanField");
        DeserializationStrategy strategy = DeserializationStrategyFactory.getStrategy(field);
        assertInstanceOf(BooleanDeserializationStrategy.class, strategy);
    }

    @Test
    @DisplayName("Test strategy for Boolean field")
    public void testGetStrategyForBooleanClass() throws NoSuchFieldException {
        Field field = TestClass.class.getDeclaredField("booleanClassField");
        DeserializationStrategy strategy = DeserializationStrategyFactory.getStrategy(field);
        assertInstanceOf(BooleanDeserializationStrategy.class, strategy);
    }

    @Test
    @DisplayName("Test strategy for List field")
    public void testGetStrategyForList() throws NoSuchFieldException {
        Field field = TestClass.class.getDeclaredField("listField");
        DeserializationStrategy strategy = DeserializationStrategyFactory.getStrategy(field);
        assertInstanceOf(ListDeserializationStrategy.class, strategy);
    }

    @Test
    @DisplayName("Test strategy for Object field")
    public void testGetStrategyForObject() throws NoSuchFieldException {
        Field field = TestClass.class.getDeclaredField("personField");
        DeserializationStrategy strategy = DeserializationStrategyFactory.getStrategy(field);
        assertInstanceOf(ObjectDeserializationStrategy.class, strategy);
    }

    private static class TestClass {
        private String stringField;
        private int intField;
        private Integer integerField;
        private boolean booleanField;
        private Boolean booleanClassField;
        private List<String> listField;
        private Person personField;
    }
}