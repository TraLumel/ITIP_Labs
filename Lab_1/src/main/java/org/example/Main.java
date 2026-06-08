package org.example;

import com.fasterxml.jackson.databind.ObjectMapper; // Импорт для работы с JSON
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    public static void main(String[] args) {
        final Logger logger = LoggerFactory.getLogger(Main.class);
        String filePath = "src/main/resources/task_1.txt";
        TopWords analyzer = new TopWords();

        logger.info("Приложение запущено и использует логгер");

        try {
            logger.info("Начало работы: анализ текста");

            // Подсчета слов
            List<Map.Entry<String, Integer>> topWordsList = analyzer.getWordFrequencies(filePath);
            int count = Math.min(10, topWordsList.size());

            for (int i = 0; i < count; i++) {
                Map.Entry<String, Integer> entry = topWordsList.get(i);
                logger.info("{}. {} - {} раз(а)", (i + 1), entry.getKey(), entry.getValue());
            }

            // --- ПУНКТ 5 (Сериализация в JSON) ---
            logger.info("Начало работы с JSON");

            // Создаем объект-преобразователь
            ObjectMapper mapper = new ObjectMapper();

            // Создаем объект пользователя
            User user = new User("Студент", 19);

            // Превращаем объект в JSON-строку
            String jsonString = mapper.writeValueAsString(user);

            // Выводим результат через логгер
            logger.info("Результат сериализации объекта User: {}", jsonString);


        } catch (Exception e) {
            logger.error("Произошла ошибка: ", e);
        }

        logger.info("Конец работы");
    }
}