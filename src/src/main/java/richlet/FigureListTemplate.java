package richlet;


import model.FigureData;
import org.zkoss.xel.VariableResolver;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.Composer;
import org.zkoss.zk.ui.util.Template;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;

import java.util.Map;

class FigureListTemplate implements Template {

	public Component[] create(Component parent, Component insertBefore,
                              VariableResolver resolver, Composer composer){

		FigureData figure = (FigureData) resolver.resolveVariable("each");
		Listitem listitem = new Listitem();
		listitem.appendChild(new Listcell(figure.getTypeFigure().toString()));
		listitem.appendChild(new Listcell(figure.getDescription()));

		//append to the parent
		if (insertBefore ==null || parent != insertBefore.getParent()){
			parent.appendChild(listitem);
		}else{
			parent.insertBefore(listitem, insertBefore);
		}

		Component[] components = new Component[1];
		components [0] = listitem;

		return components;
	}

	@Override
	public Map<String, Object> getParameters() {
		//it's used for data binding.
		//we don't use it in this example.
		return null;
	}
}