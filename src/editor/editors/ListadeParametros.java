package editor.editors;

import java.util.List;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLFactory;

public class ListadeParametros {


public String[] parametros;
public UmlHandlefile uml;
 
public ListadeParametros(String str , UmlHandlefile uml){
	this.uml = uml;
    
    parametros = str.replace(" ", "").replace("(" ,"").replace(")","").split(",");
	
}

public String getype(int i) {
	String statement = parametros[i];
	return statement.substring(statement.indexOf(":")+1, statement.length());
}

public String getname(int i) {
	String statement = parametros[i];
	return statement.substring(0, statement.indexOf(":"));
}

public EList<String> getlistname() {
	EList<String> names = new BasicEList<String>();
	for (int i =0; i<parametros.length; i++){
		
		names.add(getname(i));
	}
	return names;
}

public EList<Type> getlistTypes() {
	EList<Type> types = new BasicEList<Type>();
for (int i =0; i<parametros.length; i++){
		
		types.add(uml.handletype(getype(i)));
	}
	return types;
}



}
