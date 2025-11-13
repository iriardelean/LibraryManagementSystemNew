package com.example.librarymanagementsystemnew.service;

import java.util.List;
import java.util.function.Function;

public class SequentialIdGenerator {

    public static <T> String getNextId(List<T> entities, Function<T, String> idExtractor, String prefix) {

        int maxId = entities.stream()
                .map(idExtractor)
                .filter(id -> id != null && id.startsWith(prefix))
                .map(id -> id.substring(prefix.length()))
                .mapToInt(numStr -> {
                    try {
                        return Integer.parseInt(numStr);
                    } catch (NumberFormatException e) {
                        return 0;
                    }
                })
                .max()
                .orElse(0);

        return prefix + (maxId + 1);
    }
}