import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Algorithms {

    //OK
    public static PairPoint distanceMinClosestPairBruteForce(List<Point> points) {
        int numberOfPoints = points.size();
        double valueDistanceMin = 99999;

        PairPoint pairPoint = new PairPoint();

        for(int i = 0; i < numberOfPoints - 1; i++) {
            for(int j = i+1; j < numberOfPoints; j++) {
                double distance = points.get(i).distance(points.get(j));
                if (valueDistanceMin > distance) {
                    valueDistanceMin = distance;

                    pairPoint.setPointOne(points.get(i));
                    pairPoint.setPointTwo(points.get(j));
                    pairPoint.setDistance(valueDistanceMin);
                }
            }
        }
        return pairPoint;
    }

    //OK
    public static PairPoint distanceMinClosestPairDivideAndConquer(List<Point> points) {
        List<Point> p = points;
        List<Point> q = new ArrayList<>();

        //Criando o conjunto q
        points.forEach(point -> {
           q.add(new Point(point));
        });

        int n = points.size();

        mergeSortPoints(p, 0, n-1, 'x'); //Odenar com base no eixo X
        mergeSortPoints(q, 0, n-1, 'y'); //Ordenar com base no eixo Y

         /*
          Recebe uma lista P ordenada com base no eixo X e uma lista
          Q ordenada com base no eixo Y
          P == Q
        */
        return efficientClosestPair(p, q);
    }

    //OK
    private static PairPoint efficientClosestPair(List<Point> p, List<Point> q) {
        //Pl e Ql tem o mesmo tamanho e Pr, Qr tem o mesmo tamanho
        if(p.size() <= 3) {
            return distanceMinClosestPairBruteForce(p);
        } else {
            List<Point> pointsLeftP = new ArrayList<>();
            List<Point> pointsLeftQ = new ArrayList<>();
            List<Point> pointsRightP = new ArrayList<>();
            List<Point> pointsRightQ = new ArrayList<>();

            for(int i = 0; i < p.size() / 2; i++) {
                pointsLeftP.add(p.get(i));
            }
            for(int i = 0; i < q.size() / 2; i++) {
                pointsLeftQ.add(q.get(i));
            }
            for(int i = (p.size() / 2); i < p.size(); i++) {
                pointsRightP.add(p.get(i));
            }
            for(int i = (q.size() / 2); i < q.size(); i++) {
                pointsRightQ.add(q.get(i));
            }

            PairPoint pairPointLeft = efficientClosestPair(pointsLeftP, pointsLeftQ);
            PairPoint pairPointRight = efficientClosestPair(pointsRightP, pointsRightQ);

            PairPoint minimumDistance;
            if(pairPointLeft.getDistance() < pairPointRight.getDistance()) {
                minimumDistance = pairPointLeft;
            } else {
                minimumDistance = pairPointRight;
            }

            double selectedAxisValueX = p.get((p.size() / 2) - 1).x;

            List<Point> selectedPoints = new ArrayList<>();
            q.forEach(point -> {
                if(modulo(point.x - selectedAxisValueX) < minimumDistance.getDistance()) {
                    selectedPoints.add(point);
                }
            });

            double dminsq = Math.pow(minimumDistance.getDistance(), 2);

            for(int i = 0; i < selectedPoints.size() - 1; i++) {
                int k = i + 1;

                double distancePointsAxisY = Math.pow(selectedPoints.get(k).getY() - selectedPoints.get(i).getY(), 2);
                while (k < selectedPoints.size() && distancePointsAxisY < dminsq) {
                    double distance = Math.pow(selectedPoints.get(k).getX() - selectedPoints.get(i).getX(), 2) +
                            Math.pow(selectedPoints.get(k).getY() - selectedPoints.get(i).getY(), 2);

                        if(distance < dminsq) {
                            dminsq = distance;
                            minimumDistance.setPointOne(selectedPoints.get(i));
                            minimumDistance.setPointTwo(selectedPoints.get(k));
                            minimumDistance.setDistance(dminsq);
                        }

                    k++;
                }
            }
            minimumDistance.setDistance(Math.sqrt(dminsq));
            return minimumDistance;
        }
    }

    //OK
    private static List<Point> mergeSortPoints(List<Point> points, int begin, int end, char baseAxis) {
        if(begin < end) {
            int quite = (begin + end) / 2;
            mergeSortPoints(points, begin, quite, baseAxis);
            mergeSortPoints(points, quite+1, end, baseAxis);
            merge(points, begin, quite, end, baseAxis);
        }
        return points;
    }

    //OK
    private static void merge(List<Point> points, int begin, int quite, int end, char baseAxis) {
        int n1 = (quite - begin) + 1;
        int n2 = end - quite;

        List<Point> pointsLeft = new ArrayList<>();
        for(int i = 0; i < n1; i++) {
            pointsLeft.add(points.get(begin+i));
        }
        List<Point> pointsRight = new ArrayList<>();
        for(int i = 0; i < n2; i++) {
            pointsRight.add(points.get(quite+i+1));
        }
        pointsLeft.add(new Point(9999, 9999));
        pointsRight.add(new Point(9999, 9999));

        int i = 0;
        int j = 0;
        for(int k = begin; k <= end; k++) {
            Point pointLeft = pointsLeft.get(i);
            Point pointRight = pointsRight.get(j);

            if(baseAxis == 'x') {
                if(pointLeft.getX() <= pointRight.getX()) {
                    points.set(k, pointLeft);
                    i++;
                } else {
                    points.set(k, pointRight);
                    j++;
                }
            } else {
                if(pointLeft.getY() <= pointRight.getY()) {
                    points.set(k, pointLeft);
                    i++;
                } else {
                    points.set(k, pointRight);
                    j++;
                }
            }
        }
    }

    //OK
    private static double modulo(double x) {
        if(x < 0) {
            x *= -1;
        }
        return x;
    }
}
