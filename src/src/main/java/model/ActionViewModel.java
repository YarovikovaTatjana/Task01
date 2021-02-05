package model;

import org.zkoss.bind.annotation.Command;
import org.zkoss.zul.ListModelList;

import java.util.List;

/**
 * Suggest using ZK {@link org.zkoss.zul.ListModel} implementation as a component's model.
 */
public class ActionViewModel {
	
	private Integer index;
	private FigureService figureService;   //this could be any of your service class
	private ListModelList<FigureData> dataList;
	private FigureData selectedFigureData;

	public ActionViewModel() {
		this.figureService = new FigureServiceImpl();
		this.dataList = figureService.findAll();
	}

	@Command
	public void search(){
		dataList.clear();
		selectedFigureData = figureService.findFigureData(index);
		dataList.add(selectedFigureData);
	}

	@Command
	public void searchAll(){
		dataList.clear();
		dataList.addAll(figureService.findAll());
	}



	public void setIndex(Integer index) {
		this.index = index;
	}

	public Integer getIndex() {
		return index;
	}

	public List<FigureData> getFigureList(){
		return dataList;
	}

	public void setSelectedFigure(FigureData selectedFigure) {
		this.selectedFigureData = selectedFigure;
	}

	public FigureData getSelectedFigure() {
		return selectedFigureData;
	}




}
