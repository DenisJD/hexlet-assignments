package exercise;

import exercise.daytimes.Day;
import exercise.daytimes.Daytime;
import exercise.daytimes.Evening;
import exercise.daytimes.Morning;
import exercise.daytimes.Night;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class MyApplicationConfig {

    @Bean
    public Daytime getDayTime() {
        LocalDateTime currentTime = LocalDateTime.now();
        int currentHour = currentTime.getHour();
        if (currentHour >= 6 && currentHour < 12) {
            return new Morning();
        }
        if (currentHour >= 12 && currentHour < 18) {
            return new Day();
        }
        if (currentHour >= 18 && currentHour < 23) {
            return new Evening();
        }
        return new Night();
    }
}
// END
