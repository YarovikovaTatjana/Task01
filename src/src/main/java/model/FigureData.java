package model;


import db.model.coordinate.Coordinate;
import db.model.figure.Circle;
import db.model.figure.Figure;
import db.model.figure.TypeFigure;
import factory.FigureFactory;
import helper.FigureCreateHelper;
import interfaces.IMovable;
import interfaces.ITransformable;
import interfaces.ITurnable;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class FigureData implements IMovable, ITurnable, ITransformable {
    int index;
    TypeFigure typeFigure;
    ArrayList<Coordinate> coordinates;
    double radius;
    String description;
    String type;
    String imageFigure = "figure.png";



    public FigureData(Figure figure) {
        this.index = figure.getId();
        this.typeFigure = figure.getTypeFigure();
        this.coordinates = figure.getCoordinates();
        this.description = figure.toString();
        this.type = figure.getTypeFigure().getTitle();
        if (figure instanceof Circle) {
            Circle circle = (Circle) figure;
            this.radius = circle.getRadius();
        }
       // fillFigure();
    }

    public Figure transformByFigure(){
        FigureFactory factory = FigureCreateHelper.getFigureFactory(typeFigure);
        return factory.createFigure(index,coordinates,typeFigure);
    }



    public TypeFigure getTypeFigure() {
        return typeFigure;
    }

    public ArrayList<Coordinate> getCoordinates() {
        return coordinates;
    }

    public String getDescription() {
        return description;
    }

    public String getType(){
        return typeFigure.getTitle();
    }

    public void fillFigure(){
        Canvas canvas = new Canvas(100,100);
        GraphicsContext context = canvas.getGraphicsContext2D();
        if (typeFigure==TypeFigure.Circle){
            double x = coordinates.get(0).getX();
            double y = coordinates.get(0).getY();
            context.fillOval(x,y,radius,radius);
        }
        else{
            int count = coordinates.size();
            double[] xPoints = new double[count];
            double[] yPoints = new double[count];
            for (int i = 0; i <count ; i++) {
                xPoints[i] = coordinates.get(i).getX();
                yPoints[i] = coordinates.get(i).getY();
            }
            context.fillPolygon(xPoints,yPoints,count);
        }
        WritableImage image = canvas.snapshot(null,null);
        File file = new File(imageFigure);
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public String getImageFigure() {
        return imageFigure;
    }

    @Override
    public void move(int distanceX, int distanceY) {
        Figure figure = transformByFigure();
        figure.move(distanceX,distanceY);
        this.coordinates=figure.getCoordinates();
        this.description = figure.toString();
        if (typeFigure==TypeFigure.Circle){
            Circle circle = (Circle) figure;
            this.radius =  circle.getRadius();
        }
       // fillFigure();
    }

    @Override
    public void transform(double size) {
        Figure figure = transformByFigure();
        figure.transform(size);
        this.coordinates=figure.getCoordinates();
        this.description = figure.toString();
        if (typeFigure==TypeFigure.Circle){
            Circle circle = (Circle) figure;
            this.radius =  circle.getRadius();
        }
       // fillFigure();

    }

    @Override
    public void turn(double angle) {
        Figure figure = transformByFigure();
        figure.turn(angle);
        this.coordinates=figure.getCoordinates();
        this.description = figure.toString();
        if (typeFigure==TypeFigure.Circle){
            Circle circle = (Circle) figure;
            this.radius =  circle.getRadius();
        }
       // fillFigure();

    }

    @Override
    public String toString() {
        String result = typeFigure.toString() + ". Фигура под номером №" + (index+1);

        return  result;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((coordinates == null) ? 0 : coordinates.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof FigureData))
            return false;
        FigureData other = (FigureData) obj;
        if (coordinates == null) {
            if (other.coordinates != null)
                return false;
        } else if (!coordinates.equals(other.coordinates))
            return false;
        return true;
    }

}
