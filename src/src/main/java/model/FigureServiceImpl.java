package model;

import db.model.figure.Figure;
import db.repositories.FigureRepositoryCustom;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FigureServiceImpl implements FigureService {
List<FigureData> figureDataList = new ArrayList<>();
List<FigureData> selectedFigureDataList;


    public FigureServiceImpl() {
        ArrayList<Figure> figures = FigureRepositoryCustom.findAll();
        Collections.sort(figures,Figure.COMPARE_BY_ID);
        for (Figure figure: figures) {
            figureDataList.add(new FigureData(figure));
        }
    }

    @Override
    public List<FigureData> findAll() {
        return figureDataList;
    }

    @Override
    public FigureData findFigureData(int id) {
        Figure figure = FigureRepositoryCustom.findFigureById(id);
        return new FigureData(figure);
    }

    @Override
    public List<FigureData> findFigureDataByList(int id) {
        Figure figure = FigureRepositoryCustom.findFigureById(id);
        selectedFigureDataList = new ArrayList<>();
        selectedFigureDataList.add(new FigureData(figure));
        return selectedFigureDataList;
    }

    @Override
    public void insertFigureData(FigureData figureData) {
        Figure figure = figureData.transformByFigure();
        FigureRepositoryCustom.insert(figure);
    }

    @Override
    public void deleteFigureData(int id) {
        int sizeCollection = FigureRepositoryCustom.findCountDoc();
        FigureRepositoryCustom.delete(id);

        if ((id+1)<sizeCollection){
            FigureRepositoryCustom.updateId(sizeCollection-1,id);
            Figure.decremetCount();
        }

    }

    @Override
    public void updateFigureData(int id, FigureData figureData) {
        Figure figure = figureData.transformByFigure();
        FigureRepositoryCustom.update(id,figure);

    }


}
