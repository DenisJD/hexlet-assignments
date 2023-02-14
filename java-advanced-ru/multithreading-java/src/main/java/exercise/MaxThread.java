package exercise;

// BEGIN
public class MaxThread extends Thread {

    private int max = 0;

    private final int[] numbers;

    public MaxThread(int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public void run() {
        for (int number : numbers) {
            if (number > max) {
                max = number;
            }
        }
    }

    public int getMax() {
        return max;
    }
}
// END
