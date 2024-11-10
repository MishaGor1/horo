package com.horo.horo;

import org.lightcouch.CouchDbClient;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class HoroscopeService {

    /*Этот класс является сервисом, который отвечает за получение и сохранение данных гороскопов.*/

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CouchDbClient db;

    /*
      Метод для получения и сохранения данных гороскопа для нескольких знаков зодиака.
     */
    public void fetchAndSaveHoroscopes(List<String> signs, String type, String day) {
        for (String sign : signs) {
            fetchAndSaveHoroscope(sign, type, day);
        }
    }

    /*
      Метод для получения и сохранения данных гороскопа для одного знака зодиака.
     */

    public void fetchAndSaveHoroscope(String sign, String type, String day) {
        String url = String.format("https://any.ge/horoscope/api/?sign=%s&type=%s&day=%s&lang=en", sign, type, day);

        /*   отправляет HTTP GET запрос
        к указанному URL и возвращает ответ в виде массива объектов Horoscope.*/
        ResponseEntity<Horoscope[]> response = restTemplate.getForEntity(url, Horoscope[].class);


        Horoscope[] horoscopes = response.getBody();

        if (horoscopes != null && horoscopes.length > 0) {
            for (Horoscope horoscope : horoscopes) {
                db.save(horoscope);
                System.out.println("Данные гороскопа сохранены: " + horoscope);
            }
        } else {
            System.out.println("Не удалось получить данные гороскопа для знака: " + sign);
        }
    }
}