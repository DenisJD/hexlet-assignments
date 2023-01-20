package exercise.controller;

import exercise.model.City;
import exercise.repository.CityRepository;
import exercise.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
public class CityController {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private WeatherService weatherService;

    // BEGIN
    @GetMapping(path = "/cities/{id}")
    public Map<String, String> getCity(@PathVariable long id) {
        return weatherService.getFullWeather(id);
    }

    @GetMapping(path = "/search")
    public List<Map<String, String>> getCities(@RequestParam(name = "name", required = false) String name) {
        List<City> cities = name == null ?
                cityRepository.findAllByOrderByNameAsc() :
                cityRepository.findAllCityByNameContainingIgnoreCase(name);

        return cities.stream()
                .map(x -> {
                    Map<String, String> fullWeather = weatherService.getFullWeather(x.getId());
                    Map<String, String> result = new HashMap<>();
                    result.put("temperature", fullWeather.get("temperature"));
                    result.put("name", x.getName());
                    return result;
                })
                .sorted(Comparator.comparing(x -> x.get("name")))
                .collect(Collectors.toList());
    }
    // END
}
