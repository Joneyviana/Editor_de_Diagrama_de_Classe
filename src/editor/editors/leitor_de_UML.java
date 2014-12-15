package editor.editors;

import java.io.File;
import java.io.IOException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.Property;
public class leitor_de_UML {
	private static final ResourceSet RESOURCE_SET = new ResourceSetImpl();
	public static  File outputFile;
	
	private  URI outputUri ;
	private XMIResource resource ;
public leitor_de_UML(Tela canvas){
	outputUri =  URI.createFileURI( outputFile.getAbsolutePath() );
	resource = new XMIResourceImpl(outputUri);
    
	try {
		
		resource.load(null);
		UmlHandlefile.resource = resource ;
		MultiPageEditor.uml.init();

		EObject root =    resource.getContents().get(0);
     //root.eContents().get(0).
     int x = 50 ;
     int y = 50 ;
     
     for (EObject element :root.eContents()){
	 if( element.getClass().getSimpleName().equals("ClassImpl")){
	 retangulo ret = 	new retangulo(canvas, SWT.NONE);
	ret.o = element ;
	 
	
	 element.eClass();
	 for ( EObject ele :element.eContents()) {
		System.out.print(ele.getClass().getSimpleName());
		 if (ele.getClass().getSimpleName().equals("PropertyImpl")){
			Property pro = (Property) ele;
			ret.ajustesize();
			ret.atributos_nomes.add(resource.getID(pro.getType())+":"+pro.getName());
		}
	}
	 ret.definir_ponto(x, y);	
	 x  = x+ 130 ;
	 if( x  >=600) {
		 x = 50;
		 y =y + 130 ;
		 
	 }
	 }
	 }
	 } catch (IOException e) {
		 MultiPageEditor.uml.init();
		 // TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
