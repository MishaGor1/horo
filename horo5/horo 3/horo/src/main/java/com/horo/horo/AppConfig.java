package com.horo.horo;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.lightcouch.CouchDbClient;
import org.lightcouch.CouchDbProperties;

@Configuration
public class AppConfig {

    // Создание бина RestTemplate
    @Bean
    public RestTemplate restTemplate() {
        // RestTemplate используется для выполнения HTTP-запросов к внешним сервисам
        return new RestTemplate();
    }

    // Создание бина CouchDbClient
    @Bean
    public CouchDbClient couchDbClient() {
        // Настройка свойств для подключения к базе данных CouchDB
        CouchDbProperties properties = new CouchDbProperties()
                .setDbName("horoscope") // Имя базы данных
                .setCreateDbIfNotExist(true) // Создать базу данных, если она не существует
                .setProtocol("http") // Протокол подключения
                .setHost("127.0.0.1") // Хост CouchDB
                .setPort(5984) // Порт CouchDB
                .setUsername("admin") // Имя пользователя для аутентификации
                .setPassword("1"); // Пароль для аутентификации

        // Создание и возврат клиента CouchDB с указанными свойствами
        return new CouchDbClient(properties);
    }
}