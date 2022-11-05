package exercise;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

// BEGIN
class App {
    public static void save(Path path, Car car) throws JsonProcessingException {
        Path fullPath = path.toAbsolutePath().normalize();
        String content = car.serialize();
        try {
            Files.writeString(fullPath, content, StandardOpenOption.WRITE);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Car extract(Path path) throws IOException {
        Path fullPath = path.toAbsolutePath().normalize();
        String content = "";
        try {
            content = Files.readString(fullPath);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return Car.unserialize(content);
    }
}
// END
