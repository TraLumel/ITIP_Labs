package org.example.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("org.example")   // Сканируем все компоненты в пакете org.example
public class AppConfig {
}