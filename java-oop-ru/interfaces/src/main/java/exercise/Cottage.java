package exercise;

// BEGIN
public final class Cottage implements Home {

    private final double area;

    private final int floorCount;

    Cottage(double pArea, int pFloorCount) {
        this.area = pArea;
        this.floorCount = pFloorCount;
    }

    @Override
    public String toString() {
        return floorCount + " этажный коттедж площадью " + area + " метров";
    }

    @Override
    public double getArea() {
        return area;
    }
}
// END
