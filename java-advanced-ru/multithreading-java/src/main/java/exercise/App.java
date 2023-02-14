package exercise;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

class App {
    private static final Logger LOGGER = Logger.getLogger("AppLogger");

    // BEGIN
    public static Map<String, Integer> getMinMax(int[] numbers) {
        MaxThread maxThread = new MaxThread(numbers);
        MinThread minThread = new MinThread(numbers);
        maxThread.start();
        LOGGER.log(Level.INFO, "MaxThread started");
        minThread.start();
        LOGGER.log(Level.INFO, "MinThread started");

        try {
            maxThread.join();
            LOGGER.log(Level.INFO, "MaxThread finished");
            minThread.join();
            LOGGER.log(Level.INFO, "MinThread finished");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return Map.of("min", minThread.getMin(), "max", maxThread.getMax());
    }
    // END
}
