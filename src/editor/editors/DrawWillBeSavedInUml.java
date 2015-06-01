package editor.editors;

import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public class DrawWillBeSavedInUml extends DrawComposite{
public Tela tela ;
public EObject o;
ArrayList<String> propriedades = new ArrayList<>();
public DrawWillBeSavedInUml(Composite parent, int style) {
		super(parent, style);
		tela = (Tela) parent ;
	}

public void setPropertysofClass(){
	for (Text text : textos){
		if (text.getText().contains("(")){
			metodos.add(text.getText());
			tela.uml.createoperation(o, text.getText());
		}
		else {
			atributos_nomes.add(text.getText());
		    tela.uml.addProperty(text.getText(),o);
		}
		text.dispose();
	  
		}
	
	
	tela.uml.setName(string, o); 
    textos.clear();
    redraw();
}
}
