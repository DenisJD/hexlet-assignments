package exercise;

import exercise.daytimes.Daytime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// BEGIN
@RestController
public class WelcomeController {

    @Autowired
    Daytime getDayTime;

    @Autowired
    Meal meal;

    @GetMapping(path = "/daytime")
    public String getAnswer() {
        String dayTimeName = getDayTime.getName();
        return "It is " + dayTimeName + " now. Enjoy your " + meal.getMealForDaytime(dayTimeName);
    }
}
// END
