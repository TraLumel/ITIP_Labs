package org.example;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("Программа начала работу.");

        // Чтение паспорта сборки
        try (var is = Main.class.getClassLoader().getResourceAsStream("build-passport.properties")) {
            if (is != null) {
                // Используем InputStreamReader для явного указания кодировки UTF-8
                try (InputStreamReader reader = new InputStreamReader(is, StandardCharsets.UTF_8)) {
                    Properties props = new Properties();
                    props.load(reader);
                    logger.info("--- Информация о сборке ---");
                    logger.info("Собрал: {}", props.getProperty("build.user"));
                    logger.info("ОС: {}", props.getProperty("build.os"));
                    logger.info("Версия Java: {}", props.getProperty("build.java.version"));
                    logger.info("Время сборки: {}", props.getProperty("build.time"));
                    logger.info("Сообщение: {}", props.getProperty("build.message"));
                    logger.info("---------------------------");
                }
            } else {
                logger.warn("Файл build-passport.properties не найден!");
            }
        } catch (Exception e) {
            logger.error("Ошибка при чтении паспорта сборки: ", e);
        }

        System.out.println("Добро пожаловать в обработчик строк!");
        System.out.print("Пожалуйста, введите любую строку: ");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        // Используем метод из Apache Commons Lang3
        String reversed = StringUtils.reverse(input);

        logger.info("Перевернутая строка: {}", reversed);

        logger.info("Пользователь ввел строку: '{}'. Результат: '{}'", input, reversed);
        logger.info("Программа успешно завершила работу.");
    }
}
