package richlet;

import model.FigureData;
import model.FigureService;
import model.FigureServiceImpl;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.GenericRichlet;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.RichletConfig;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zul.*;
import org.zkoss.zul.ext.Selectable;

import java.util.List;
import java.util.Set;

public class SearchRichlet extends GenericRichlet {

	private FigureService figureService = new FigureServiceImpl();

	@Override
	public void service(Page page) throws Exception {
//		if ("/search/admin".equals(page.getRequestPath())){
//			//build admin UI
//		}else{
//			//build normal UI
//		}
		//build the user interface
		Component rootComponent = buildUserInterface();
		rootComponent.setPage(page);
	}
	
	private Component buildUserInterface(){

		//build search area
		final Intbox keywordBox = new Intbox();
		Button searchButton = new Button("Search");
		searchButton.setImage("/img/search.png");
		
		Hbox searchArea = new Hbox();
		searchArea.setAlign("center");
		searchArea.appendChild(new Label("Index:"));
		searchArea.appendChild(keywordBox);
		searchArea.appendChild(searchButton);
		
		
		//build Car List Area
		Listhead listhead = new Listhead();
		listhead.appendChild(new Listheader("Description"));
		listhead.appendChild(new Listheader("Type of figure"));

		final Listbox figureDataListbox = new Listbox();
		figureDataListbox.setHeight("160px");
		figureDataListbox.setEmptyMessage("No figure found in the result");
		figureDataListbox.setItemRenderer(new FigureRenderer());
		figureDataListbox.appendChild(listhead);
		
		//build Detail Area

		final Label typeLabel = new Label();
		final Label descriptionLabel = new Label();
		Vbox vbox = new Vbox();
		vbox.appendChild(typeLabel);
		vbox.appendChild(descriptionLabel);
		
		final Image imageFigure = new Image();
		imageFigure.setWidth("250px");
		Hbox detailArea = new Hbox();
		detailArea.setStyle("margin-top:20px");
		detailArea.appendChild(imageFigure);
		detailArea.appendChild(vbox);
		
		//add event listeners
		searchButton.addEventListener(Events.ON_CLICK, new EventListener<Event>() {
			//search
			@Override
			public void onEvent(Event event) throws Exception {
				Integer keyword = keywordBox.getValue();
				List<FigureData> result = figureService.findFigureDataByList(keyword);
				figureDataListbox.setModel(new ListModelList<FigureData>(result));
			}
			
		});

		figureDataListbox.addEventListener(Events.ON_SELECT, new EventListener<SelectEvent>() {
			//show selected item's detail
			@Override
			public void onEvent(SelectEvent event) throws Exception {
				//get selection from listbox's model
				Set<FigureData> selection = ((Selectable<FigureData>)figureDataListbox.getModel()).getSelection();
				if (selection!=null && !selection.isEmpty()){
					FigureData selected = selection.iterator().next();
					imageFigure.setSrc(selected.getImageFigure());
					typeLabel.setValue(selected.getTypeFigure().toString());
					descriptionLabel.setValue(selected.getDescription());
				}
			}
		});
		
		Window window = new Window();
		window.setTitle("Search");
		window.setWidth("600px");
		window.setBorder("normal");
		window.appendChild(searchArea);
		window.appendChild(figureDataListbox);
		window.appendChild(detailArea);
		
		return window;
	}
	

	@Override
	public void init(RichletConfig config) {
		//initialize resources, e.g. get initial parameters
	}
	
	@Override
	public void destroy() {
		//destroy resources
	}
}

class FigureRenderer implements ListitemRenderer<FigureData> {

	@Override
	public void render(Listitem listitem, FigureData figure, int index) throws Exception {
		listitem.appendChild(new Listcell(figure.getDescription()));
		listitem.appendChild(new Listcell(figure.getTypeFigure().toString()));

	}
}

