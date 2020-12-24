package db.model.coordinate;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.springframework.data.mongodb.core.mapping.Document;

@JsonAutoDetect @Document
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
}