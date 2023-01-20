package exercise.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import exercise.CityNotFoundException;
import exercise.HttpClient;
import exercise.model.City;
import exercise.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class WeatherService {

    @Autowired
    CityRepository cityRepository;

    // Клиент
    HttpClient client;

    // При создании класса сервиса клиент передаётся снаружи
    // В теории это позволит заменить клиент без изменения самого сервиса
    WeatherService(HttpClient client) {
        this.client = client;
    }

    // BEGIN
    public Map<String, String> getFullWeather(Long id) {
        String url = "http://weather/api/v2/cities/";

        City city = cityRepository.findById(id)
                .orElseThrow(() -> new CityNotFoundException("City not found, id:" + id));

        String fullWeather = client.get(url + city.getName());

        try {
            return new ObjectMapper().readValue(fullWeather, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Unexpected error");
        }
    }
    // END
}
