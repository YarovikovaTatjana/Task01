package db.model.figure;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import db.model.coordinate.Coordinate;
import interfaces.IMovable;
import interfaces.ITransformable;
import interfaces.ITurnable;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.text.DecimalFormat;
import java.util.ArrayList;

@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, property="type")
@JsonSubTypes({
        @JsonSubTypes.Type(value= Circle.class, name= "Circle"),
        @JsonSubTypes.Type(value = Triangle.class, name = "Triangle"),
        @JsonSubTypes.Type(value =Rectangle.class, name = "Rectangle"),
        @JsonSubTypes.Type(value =Polygon.class, name = "Polygon")
})

@Document(collection="figures")
public abstract class Figure implements ITurnable, IMovable, ITransformable {
  @JsonIgnore   @Transient
    private static final long serialVersionUid = 1L;
    private  int id;
    ArrayList <Coordinate> coordinates;
    TypeFigure typeFigure;



    @JsonIgnore     @Transient
    DecimalFormat decimalFormat = new DecimalFormat("#.###");

  public Figure(ArrayList<Coordinate> coordinates) {
        this.coordinates = coordinates;
    }

    public Figure() {
    }

    public Figure (int id, ArrayList<Coordinate> coordinates, TypeFigure typeFigure){
      this.id = id;
      this.coordinates = coordinates;
      this.typeFigure = typeFigure;
    }

    public int getId() {
        return id;
    }


    public TypeFigure getTypeFigure() {
        return typeFigure;
    }


    public double calculateArea(){
    double sum1=0;
    double sum2=0;
    for (int i = 0; i <coordinates.size()-1 ; i++) {
        sum1=sum1+coordinates.get(i).getX()*coordinates.get(i+1).getY();
        sum2=sum2+coordinates.get(i).getY()*coordinates.get(i+1).getX();
    }

        return Math.abs(sum1-sum2)/2.0;
}

    @Override
    public void move(int distanceX, int distanceY) {
        for (Coordinate coordinate : coordinates) {
            double x = coordinate.getX() + distanceX;
            double y = coordinate.getY() + distanceY;
            coordinate.setX(x);
            coordinate.setY(y);
        }
    }

    public ArrayList<Coordinate> getCoordinates() {
        return coordinates;
    }

    public String receiveCoordinatesToString(){
        StringBuilder tempCoordinates  = new StringBuilder();
        for (int i = 0; i < coordinates.size(); i++) {
            tempCoordinates.append(coordinates.get(i).getX()).append(" ").append(coordinates.get(i).getY());
            if (i<coordinates.size()-1) tempCoordinates.append(";");
        }

        return tempCoordinates.toString().replace(".", ",");
    }

    double calculateLine(Coordinate coordinate1, Coordinate coordinate2){
    return (Math.pow(Math.pow(coordinate2.getX()-coordinate1.getX(),2)+Math.pow(coordinate2.getY()-coordinate1.getY(),2),0.5));
    }

    @Override
    public void turn(double angle) {
        double radians = Math.toRadians(angle);
        for (Coordinate coordinate : coordinates) {
            double x = (coordinate.getX()*Math.cos(radians) - coordinate.getY() * Math.sin(radians));
            double y = (coordinate.getX()*Math.sin(radians) + coordinate.getY() * Math.cos(radians));
            coordinate.setX(x);
            coordinate.setY(y);
        }

    }
    public abstract String receiveName();

  public String receiveType(){
      return this.getClass().getSimpleName();
  }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < coordinates.size(); i++) {
            result.append("Координаты вершины № ").append(i + 1).append(": x=").append(decimalFormat.format(coordinates.get(i).getX())).append("; y=").append(decimalFormat.format(coordinates.get(i).getY())).append(".\n");
        }
        return result.toString();
    }
}
