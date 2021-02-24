package model;


import db.model.coordinate.Coordinate;
import db.model.figure.Figure;
import factory.FigureFactory;
import helper.FigureCreateHelper;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.*;

import java.util.ArrayList;
import java.util.Set;

public class ActionController extends SelectorComposer<Component> {

    private static final long serialVersionUID = 1L;


    @Wire
    private Intbox indexBox;
    @Wire
    private Listbox figureListbox;
    @Wire
    private Label typeLabel;
    @Wire
    private Label statusLabel;
    @Wire
    private Label descriptionLabel;
    @Wire
    private Label typeNewLabel;
    @Wire
    private Label descriptionNewLabel;
    @Wire
    private Doublebox turnBox;
    @Wire
    private Doublebox sizeBox;
    @Wire
    private Image figureImage;
    @Wire
    private Image allFigureImage;
    @Wire
    private Image tempFigureImage;
    @Wire
    private Intbox XBox;
    @Wire
    private Intbox YBox;
    @Wire
    private Intbox XCoordinateBox;
    @Wire
    private Intbox YCoordinateBox;
    @Wire
    private Label coordinateLabel;
    @Wire
    private Label typeInsetLabel;
    @Wire
    private Label descriptionInsertLabel;
    @Wire
    private Label statusInsertLabel;



    private ListModelList<FigureData> dataModel = new ListModelList<>();

    private FigureService figureService = new FigureServiceImpl();
    private FigureData selected;
    private ArrayList<Coordinate> addCoordinates = new ArrayList<>();


    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        figureListbox.setModel(dataModel);
        ImageFigureData data = new ImageFigureData(selected);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                data.start(new Stage(), "figures.png");
            }
        });

        try {
            data.stop();
        } catch (Exception ex) {

        }

    }

    @Listen("onClick = #searchButton; onOK = window")
    public void search() {
        try {
            int index = indexBox.getValue();
            index--;
            dataModel.clear();
            selected = figureService.findFigureData(index);
            dataModel.add(selected);
            typeLabel.setValue(selected.getType());
           // allFigureImage.setSrc("figures.png");
            setSrcFigureImage("figure " + selected.getIndex() +".png");
            figureImage.setSrc(selected.getImageFigure());
            descriptionLabel.setValue(selected.getDescription());
            turnBox.setValue(null);
            sizeBox.setValue(null);
            XBox.setValue(null);
            YBox.setValue(null);
            typeNewLabel.setValue(null);
            descriptionNewLabel.setValue(null);
            tempFigureImage.setSrc(null);
           }
           catch (Exception e){
               dataModel.addAll(figureService.findAll());
               selected = dataModel.get(0);
               setSrcFigureImage("figure " + selected.getIndex() +".png");
               figureImage.setSrc(selected.getImageFigure());
               typeLabel.setValue(selected.getType());
               descriptionLabel.setValue(selected.getDescription());

           }
    }

    private void setSrcFigureImage(String path) {
        ImageFigureData data = new ImageFigureData(selected);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                data.start(new Stage(), path);
            }
        });
        try {
            data.stop();
        } catch (Exception ex) {
        }


    }



    @Listen("onClick = #sizeButton")
    public void sizeController() {
        if (selected==null) search();
        double size;
        try {
            size = sizeBox.getValue();
        }
        catch (Exception e){
            size=1;
        }
        selected.transform(size);
        dataModel.clear();
        dataModel.add(selected);
        setSrcFigureImage("sizeTempFigure " + selected.getIndex() +".png");
        tempFigureImage.setSrc(selected.getSizeTempImageFigure());
        typeNewLabel.setValue(selected.getType());
        descriptionNewLabel.setValue("Новое описание " + selected.getDescription());

    }

    @Listen("onClick = #clearButton")
    public void clearController() {
        search();
        typeNewLabel.setValue(selected.getType());
        descriptionNewLabel.setValue(selected.getDescription());
        turnBox.setValue(null);
        sizeBox.setValue(null);
        XBox.setValue(null);
        YBox.setValue(null);
    }

    @Listen("onClick = #updateButton")
    public void updateController() {
        if (selected!=null){
            figureService.updateFigureData(selected.index,selected);
        }
        typeLabel.setValue(selected.getType());
        descriptionLabel.setValue(selected.getDescription());
        statusLabel.setValue("Информация обновлена");
        search();

    }

    @Listen("onClick = #deleteButton")
    public void deleteController() {
        if (selected!=null){
            figureService.deleteFigureData(selected.index);
            statusLabel.setValue("Фигура удалена");
            figureService = new FigureServiceImpl();
            search();
        }
    }

    @Listen("onClick = #addCoordinateButton")
    public void AddCoordinates() {
       try {
            int x = XCoordinateBox.getValue();
            int y = YCoordinateBox.getValue();
            addCoordinates.add(new Coordinate(x,y));
            XCoordinateBox.setValue(null);
            YCoordinateBox.setValue(null);
            if (addCoordinates.size()==1) coordinateLabel.setValue("Координата добавлена, требуется добавить еще как минимум одну координату");
           else coordinateLabel.setValue("Координата добавлена");
        }
        catch (Exception e){
          coordinateLabel.setValue("Координата не добавлена");
        }



    }

    @Listen("onClick = #insertButton")
    public void insertController() {
        FigureData data;
        if (addCoordinates.size()<2) statusInsertLabel.setValue("Фигура не была добавлена");
        else{
        try {
            FigureFactory factory = FigureCreateHelper.getFigureFactory(addCoordinates);
            Figure figure = factory.createFigure(addCoordinates);
            data = new FigureData(figure);

            figureService.insertFigureData(data);
            statusInsertLabel.setValue("Фигура добавлена");
            addCoordinates=new ArrayList<>();
            figureService = new FigureServiceImpl();
            dataModel.clear();
            typeInsetLabel.setValue(data.getType());
            descriptionInsertLabel.setValue(data.getDescription());
        }
        catch (Exception e){

        }
        }


    }

    @Listen("onClick = #turnButton")
    public void turnController() {
        if (selected==null) search();
        double angle;
        try {
            angle =turnBox.getValue();
        }
        catch (Exception e){
            angle=0;
        }
        selected.turn(angle);
        dataModel.clear();
        dataModel.add(selected);
        setSrcFigureImage("turnTempFigure " + selected.getIndex() +".png");
        tempFigureImage.setSrc(selected.getTurnTempImageFigure());
        typeNewLabel.setValue(selected.getType());
        descriptionNewLabel.setValue("Новое описание " + selected.getDescription());
    }

    @Listen("onClick = #moveButton")
    public void moveController() {
        if (selected==null) search();
        int x;
        int y;
        try {
            x = XBox.getValue();
        }
        catch (Exception e){
            x=0;
        }
        try {
            y = YBox.getValue();
        }
        catch (Exception e){
            y=0;
        }
        selected.move(x,y);
        dataModel.clear();
        dataModel.add(selected);
        setSrcFigureImage("moveTempFigure " + selected.getIndex() +".png");
        tempFigureImage.setSrc(selected.getMoveTempImageFigure());
        typeNewLabel.setValue(selected.getType());
        descriptionNewLabel.setValue("Новое описание " + selected.getDescription());
    }

    @Listen("onSelect = #figureListbox")
    public void showDetail() {
        Set<FigureData> selection = dataModel.getSelection();
        selected = selection.iterator().next();
        setSrcFigureImage("figure " + selected.getIndex() +".png");
        figureImage.setSrc(selected.getImageFigure());
        typeLabel.setValue(selected.getType());
        descriptionLabel.setValue(selected.getDescription());
    }



}
