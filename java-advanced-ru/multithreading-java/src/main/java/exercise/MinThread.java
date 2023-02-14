package exercise;

// BEGIN
public class MinThread extends Thread {

    private int min = 0;

    private final int[] numbers;

    public MinThread(int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public void run() {
        for (int number : numbers) {
            if (number < min) {
                min = number;
            }
        }
    }

    public int getMin() {
        return min;
    }
}
// END
