package exercise;

// BEGIN
public final class Flat implements Home {

    private final double area;

    private final double balconyArea;

    private final int floor;

    Flat(double pArea, double pBalconyArea, int pFloor) {
        this.area = pArea;
        this.balconyArea = pBalconyArea;
        this.floor = pFloor;
    }

    @Override
    public String toString() {
        return "Квартира площадью " + getArea() + " метров на " + floor + " этаже";
    }

    @Override
    public double getArea() {
        return area + balconyArea;
    }
}
// END
