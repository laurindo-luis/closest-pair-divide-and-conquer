import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Point> points = new ArrayList<>();

        points.add(new Point(12, 4));
        points.add(new Point(9, 1));
        points.add(new Point(8, 0));
        points.add(new Point(6, 7));
        points.add(new Point(7, 2));
        points.add(new Point(1, 9));
        points.add(new Point(15, 19));
        points.add(new Point(2, 1));

        PairPoint pairPoint = Algorithms.distanceMinClosestPairDivideAndConquer(points);

        System.out.println(String.format("Point One: %s, \nPoint Two: %s, \nMinimum distance: %s",
                pairPoint.getPointOne(),
                pairPoint.getPointTwo(),
                pairPoint.getDistance())
        );

    }
}
