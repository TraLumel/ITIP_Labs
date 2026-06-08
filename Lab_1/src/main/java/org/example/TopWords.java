package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class TopWords {

    public List<Map.Entry<String, Integer>> getWordFrequencies(String filePath) {
        File file = new File(filePath);
        Map<String, Integer> wordCount = new HashMap<>();

        try (Scanner scanner = new Scanner(file, StandardCharsets.UTF_8)) {
            while (scanner.hasNext()) {
                String word = scanner.next().toLowerCase().replaceAll("[^a-z]", "");
                if (word.isEmpty()) {
                    continue;
                }
                wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Файл не найден: " + filePath);
            return Collections.emptyList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<Map.Entry<String, Integer>> list = new ArrayList<>(wordCount.entrySet());

        // Сортировка по убыванию частоты
        list.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));

        return list;
    }
}