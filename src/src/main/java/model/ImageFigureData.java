package model;

import javafx.application.Application;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;

import javax.swing.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

//Вспомогательный класс для отрисовки с помощью canvas - уже неактуально. Но оставляю для примера

public class ImageFigureData extends Application {
    FigureData figureData;
   FigureServiceImpl figureService;

    public ImageFigureData(FigureData figureData) {
        this.figureData = figureData;
        try{
            initToolkit(); }
        catch (InterruptedException ex){

        }
        this.figureService = new FigureServiceImpl();
    }

    public static void main(String[] args) {
        launch(args);
    }


    public void start(Stage primaryStage, String path) {
      // Метод в классе FigureData закомментирован, т.к. уже не используется
      //  figureData.fillFigure(path);
    }



    @Override
    public void start(Stage primaryStage) {

    }


    public static void initToolkit() throws InterruptedException
    {
        final CountDownLatch latch = new CountDownLatch(1);
        SwingUtilities.invokeLater(() -> {
            new JFXPanel(); // initializes JavaFX environment
            latch.countDown();
        });

        if (!latch.await(5L, TimeUnit.SECONDS))
            throw new ExceptionInInitializerError();
    }





}
