import java.awt.Point;

public class PairPoint {
    private Point pointOne;
    private Point pointTwo;
    private double distance;

    public PairPoint(PairPoint pairPoint) {
        this.pointOne = pairPoint.pointOne;
        this.pointTwo = pairPoint.pointTwo;
        this.distance = pairPoint.distance;
    }

    public PairPoint() {

    }

    public Point getPointOne() {
        return this.pointOne;
    }

    public void setPointOne(Point point) {
        this.pointOne = point;
    }

    public Point getPointTwo() {
        return this.pointTwo;
    }

    public void setPointTwo(Point point) {
        this.pointTwo = point;
    }

    public double getDistance() {
        return this.distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

}
