package ru.clevertec.utils.jsonDeserializer.utils;

import java.util.*;

public class JsonParser {

    public static Map<String, String> parseJsonObject(String json) {
        String jsonString = getObjectJsonString(json);
        Map<String, String> result = new HashMap<>();
        Deque<Character> stack = new ArrayDeque<>();
        boolean inQuotes = false;
        StringBuilder currentField = new StringBuilder();
        StringBuilder keyBuilder = new StringBuilder();

        for (char c : jsonString.toCharArray()) {
            inQuotes = updateQuotesAndStack(stack, inQuotes, c);

            if (c == ':' && keyBuilder.isEmpty() && !inQuotes) {
                keyBuilder.append(currentField.toString().trim().replace("\"", ""));
                currentField.setLength(0);
            } else if (c == ',' && stack.isEmpty() && !inQuotes) {
                result.put(keyBuilder.toString(), currentField.toString().trim());
                keyBuilder.setLength(0);
                currentField.setLength(0);
            } else {
                currentField.append(c);
            }
        }

        if (!keyBuilder.isEmpty()) {
            result.put(keyBuilder.toString(), currentField.toString().trim());
        }

        return result;
    }

    public static List<String> parseJsonArray(String json) {
        String jsonString = getArrayJsonString(json);
        List<String> items = new ArrayList<>();
        Deque<Character> stack = new ArrayDeque<>();
        boolean inQuotes = false;
        StringBuilder currentItem = new StringBuilder();

        for (char c : jsonString.toCharArray()) {
            inQuotes = updateQuotesAndStack(stack, inQuotes, c);

            if (c == ',' && stack.isEmpty() && !inQuotes) {
                items.add(currentItem.toString().trim());
                currentItem.setLength(0);
            } else {
                currentItem.append(c);
            }
        }

        if (!currentItem.isEmpty()) {
            items.add(currentItem.toString().trim());
        }

        return items;
    }

    private static boolean updateQuotesAndStack(Deque<Character> stack, boolean inQuotes, char c) {
        if (c == '\"') {
            inQuotes = !inQuotes;
        }

        if (!inQuotes) {
            if (c == '{' || c == '[') {
                stack.push(c);
            } else if (c == '}' || c == ']') {
                stack.pop();
            }
        }
        return inQuotes;
    }

    private static String cleanJsonString(String jsonString) {
        return Optional.ofNullable(jsonString)
                .map(str -> str.replaceAll("\n", "").trim())
                .orElseThrow(() -> new IllegalArgumentException("Invalid JSON string"));
    }

    private static String getObjectJsonString(String jsonString) {
        return Optional.of(cleanJsonString(jsonString))
                .filter(str -> str.startsWith("{") && str.endsWith("}"))
                .map(str -> str.substring(1, str.length() - 1).trim())
                .orElseThrow(() -> new IllegalArgumentException("Invalid JSON object"));
    }

    private static String getArrayJsonString(String jsonString) {
        return Optional.of(cleanJsonString(jsonString))
                .filter(str -> str.startsWith("[") && str.endsWith("]"))
                .map(str -> str.substring(1, str.length() - 1).trim())
                .orElseThrow(() -> new IllegalArgumentException("Invalid JSON array"));
    }
}