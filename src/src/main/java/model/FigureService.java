package model;

import java.util.List;

public interface FigureService  {

List<FigureData> findAll();

FigureData findFigureData(int id);

List<FigureData> findFigureDataByList(int id);

void insertFigureData(FigureData figureData);

void deleteFigureData(int id);

void updateFigureData(int id, FigureData figureData);

}
