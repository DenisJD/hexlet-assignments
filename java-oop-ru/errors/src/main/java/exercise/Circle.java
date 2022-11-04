package exercise;

// BEGIN
public class Circle {
    private final Point point;
    private final int radius;

    public Circle(Point pPoint, int pRadius) {
        this.point = pPoint;
        this.radius = pRadius;
    }

    public int getRadius() {
        return radius;
    }

    public double getSquare() throws NegativeRadiusException {
        if (radius < 0) {
            throw new NegativeRadiusException("Radius must not be negative");
        }
        return Math.PI * radius * radius;
    }
}
// END
