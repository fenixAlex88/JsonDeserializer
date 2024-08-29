package ru.clevertec.utils.jsonDeserializer.factories;

import ru.clevertec.utils.jsonDeserializer.strategies.*;
import ru.clevertec.utils.jsonDeserializer.strategies.DeserializationStrategyImpl.*;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.function.Supplier;

public class DeserializationStrategyFactory {
    private static final Map<String, Supplier<DeserializationStrategy>> STRATEGY_MAP;
    static {
        STRATEGY_MAP = Map.of(
                "String", StringDeserializationStrategy::new,
                "int", IntegerDeserializationStrategy::new,
                "Integer", IntegerDeserializationStrategy::new,
                "boolean", BooleanDeserializationStrategy::new,
                "Boolean", BooleanDeserializationStrategy::new,
                "List", ListDeserializationStrategy::new
        );
    }

    private DeserializationStrategyFactory() {}

    public static DeserializationStrategy getStrategy(Field field) {
        String typeName = field.getType().getSimpleName();

        return STRATEGY_MAP.entrySet().stream()
                .filter(entry -> entry.getKey().equals(typeName))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse(ObjectDeserializationStrategy::new)
                .get();
    }
}
