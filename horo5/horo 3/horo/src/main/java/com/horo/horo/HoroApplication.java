package com.horo.horo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class HoroApplication {

	@Autowired
	private HoroscopeService horoscopeService;

	public static void main(String[] args) {

		/*Этот метод запускает приложение Spring Boot,
		инициализирует Spring контекст и автоматически настраивает все компоненты.*/
		ApplicationContext context = SpringApplication.run(HoroApplication.class, args);

		/*Получает бин HoroscopeService из контекста Spring, чтобы использовать его методы.*/
		HoroscopeService horoscopeService = context.getBean(HoroscopeService.class);

		List<String> signs = Arrays.asList("aries", "taurus", "gemini", "cancer", "leo", "virgo", "libra", "scorpio", "sagittarius", "capricorn", "aquarius", "pisces");
		horoscopeService.fetchAndSaveHoroscopes(signs, "daily", "today");
	}
}

