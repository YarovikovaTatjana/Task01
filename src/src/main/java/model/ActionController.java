package model;


import db.model.coordinate.Coordinate;
import db.model.figure.Figure;
import db.model.figure.TypeFigure;
import factory.FigureFactory;
import helper.FigureCreateHelper;
import org.zkoss.chart.Charts;
import org.zkoss.chart.Legend;
import org.zkoss.chart.PlotLine;
import org.zkoss.chart.model.DefaultXYModel;
import org.zkoss.chart.model.DefaultXYZModel;
import org.zkoss.chart.model.XYModel;
import org.zkoss.chart.model.XYZModel;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.*;

import java.util.ArrayList;
import java.util.Set;

public class ActionController extends SelectorComposer<Window> {

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

    @Wire
    Charts chartCircle;
    @Wire
    Charts chartNotCircle;


    private ListModelList<FigureData> dataModel = new ListModelList<>();
    private FigureService figureService = new FigureServiceImpl();
    private FigureData selected;
    private ArrayList<Coordinate> addCoordinates = new ArrayList<>();
    private XYZModel circleModel;
    private XYModel lineModel;
    private PlotLine plotLine;



    @Override
    public void doAfterCompose(Window comp) throws Exception {
        super.doAfterCompose(comp);
        figureListbox.setModel(dataModel);
        extractedAll();

    }



    private void extractedAll() {
        setModelAllCircle();
        chartCircle.setModel(circleModel);
        setModelAllLine();
        chartNotCircle.setModel(lineModel);
        plotLine = new PlotLine();
        plotLine.setValue(0);
        plotLine.setWidth(1);
        plotLine.setColor("#808080");
        chartCircle.getYAxis().addPlotLine(plotLine);
        chartNotCircle.getYAxis().addPlotLine(plotLine);
        Legend legendCircle = chartCircle.getLegend();
        legendCircle.setLayout("vertical");
        legendCircle.setAlign("right");
        legendCircle.setVerticalAlign("middle");
        legendCircle.setBorderWidth(0);
        Legend legendNotCircle = chartNotCircle.getLegend();
        legendNotCircle.setLayout("vertical");
        legendNotCircle.setAlign("right");
        legendNotCircle.setVerticalAlign("middle");
        legendNotCircle.setBorderWidth(0);
        chartCircle.setVisible(true);
        chartNotCircle.setVisible(true);
    }

    private void extractedSelect() {
        extractedAll();
        if (selected.getTypeFigure()==TypeFigure.Circle){
            circleModel.clear();
            setModelCircle(selected);
            chartCircle.setModel(circleModel);
            if (!chartCircle.isVisible()) chartCircle.setVisible(true);
            if (chartNotCircle.isVisible()) chartNotCircle.setVisible(false);
        }
        else{
            lineModel.clear();
            setModelLine(selected);
            chartNotCircle.setModel(lineModel);
            if (!chartNotCircle.isVisible()) chartNotCircle.setVisible(true);
            if (chartCircle.isVisible()) chartCircle.setVisible(false);
        }


    }


    @Listen("onClick = #searchButton; onOK = window")
    public void search() {
        try {
            dataModel.clear();
            int index = indexBox.getValue();
            index--;
            selected = figureService.findFigureData(index);
            dataModel.add(selected);
            typeLabel.setValue(selected.getType());
            descriptionLabel.setValue(selected.getDescription());
            clearLabel();
            extractedSelect();
           }
           catch (Exception e){
               dataModel.clear();
               dataModel.addAll(figureService.findAll());
               selected = dataModel.get(0);
               typeLabel.setValue(selected.getType());
               descriptionLabel.setValue(selected.getDescription());
               clearLabel();
               extractedSelect();
           }
    }

    private void clearLabel(){
        turnBox.setValue(null);
        sizeBox.setValue(null);
        XBox.setValue(null);
        YBox.setValue(null);
        typeNewLabel.setValue(null);
        descriptionNewLabel.setValue(null);
    }


    private void setModelAllLine (){
        lineModel = new DefaultXYModel();
        lineModel.setAutoSort(false);
        for (FigureData data : figureService.findAll()) {
            if (data.getTypeFigure()!=TypeFigure.Circle) {
                double firstX = data.getCoordinates().get(0).getX();
                double firstY = data.getCoordinates().get(0).getY();
                String series = data.getType() + " " + (data.getID() + 1);
                for (int i = 0; i < data.getCoordinates().size(); i++) {
                    lineModel.addValue(series, data.getCoordinates().get(i).getX(), data.getCoordinates().get(i).getY());
                    if (i == (data.getCoordinates().size()-1))  lineModel.addValue(series, firstX, firstY);
                }
            }
        }
        }

    private void setModelLine (FigureData data){
        lineModel = new DefaultXYModel();
        lineModel.setAutoSort(false);
        double firstX = data.getCoordinates().get(0).getX();
        double firstY = data.getCoordinates().get(0).getY();
        String series = data.getType() + " " + (data.getID() + 1);
        for (int i = 0; i < data.getCoordinates().size(); i++) {
            lineModel.addValue(series, data.getCoordinates().get(i).getX(), data.getCoordinates().get(i).getY());
            if (i == (data.getCoordinates().size()-1))  lineModel.addValue(series, firstX, firstY);
        }

    }


    private void setModelCircle(FigureData data){
        circleModel = new DefaultXYZModel();
        circleModel.addValue(data.getType() + " " + (data.getID() + 1), data.getCentre().getX(), data.getCentre().getY(), (data.getRadius() * 2));
    }


    private void setModelAllCircle(){
       circleModel = new DefaultXYZModel();
        for (FigureData data : figureService.findAll()) {
            if (data.getTypeFigure()==TypeFigure.Circle) {
                circleModel.addValue(data.getType() + " " + (data.getID() + 1), data.getCentre().getX(), data.getCentre().getY(), (data.getRadius() * 2));
            }
        }

    }


/* Отрисовка с помощью canvas - уже неактуально
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
*/
    @Listen("onClick = #clearButton")
    public void clearController() {
        search();
        typeNewLabel.setValue(selected.getType());
        descriptionNewLabel.setValue(selected.getDescription());
    }

    @Listen("onClick = #updateButton")
    public void updateController() {
        if (selected!=null){
            figureService.updateFigureData(selected.index,selected);
        }
        typeLabel.setValue(selected.getType());
        descriptionLabel.setValue(selected.getDescription());
        statusLabel.setValue("Информация обновлена");
        clearLabel();
        extractedAll();

    }

    @Listen("onClick = #deleteButton")
    public void deleteController() {
        if (selected!=null){
            figureService.deleteFigureData(selected.index);
            statusLabel.setValue("Фигура удалена");
            figureService = new FigureServiceImpl();
            clearLabel();
            extractedAll();
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
            extractedAll();
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
        typeNewLabel.setValue(selected.getType());
        descriptionNewLabel.setValue("Новое описание " + selected.getDescription());
        extractedSelect();
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
        typeNewLabel.setValue(selected.getType());
        descriptionNewLabel.setValue("Новое описание " + selected.getDescription());
        extractedSelect();
    }

    @Listen("onClick = #sizeButton")
    public void sizeController() {
        if (selected==null) search();
        double size;
        try {
            size =sizeBox.getValue();
        }
        catch (Exception e){
            size=1;
        }
        selected.transform(size);
        dataModel.clear();
        dataModel.add(selected);
        typeNewLabel.setValue(selected.getType());
        descriptionNewLabel.setValue("Новое описание " + selected.getDescription());
        extractedSelect();
    }

    @Listen("onSelect = #figureListbox")
    public void showDetail() {
        Set<FigureData> selection = dataModel.getSelection();
        selected = selection.iterator().next();
        typeLabel.setValue(selected.getType());
        descriptionLabel.setValue(selected.getDescription());
        extractedSelect();
    }



}
