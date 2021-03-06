package model.figure;

public enum TypeFigure {
    Triangle ("Треугольник"),
    Circle ("Окружность"),
    Rectangle ("Четырехугольник"),
    Polygon ("Многоугольник");

    private String title;

    TypeFigure(String title) {
        this.title = title;
    }

   @Override
    public String toString() {
        return "Фигура: " +  title;
    }
}

