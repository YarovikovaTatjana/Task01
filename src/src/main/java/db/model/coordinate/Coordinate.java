package db.model.coordinate;


import java.util.Comparator;

public class Coordinate {
    private double x;
    private double y;

    public  Coordinate(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Coordinate() {
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public static final Comparator<Coordinate> COMPARE_BY_XY = new Comparator<Coordinate>() {


        @Override
        public int compare(Coordinate o1, Coordinate o2) {
            double angle1 = Math.atan2(o1.getY(), o1.getX());
            double angle2 = Math.atan2(o2.getY(), o2.getX());

            if (angle1 < angle2) return 1;
            else if (angle2 < angle1) return -1;
            return 0;
        }


    };
}
