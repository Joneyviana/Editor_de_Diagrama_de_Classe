package editor.editors;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public class DrawWillBeSavedInUml extends DrawComposite{

public EObject o;
public DrawWillBeSavedInUml(Composite parent, int style) {
		super(parent, style);
		
	}

public void setPropertysofClass(){
	for (Text text : textos){
		atributos_nomes.add(text.getText());
		MultiPageEditor.uml.addProperty(text.getText(),o);
		text.dispose();
	}
    MultiPageEditor.uml.setName(string, o); 
    textos.clear();
    redraw();
}
}
