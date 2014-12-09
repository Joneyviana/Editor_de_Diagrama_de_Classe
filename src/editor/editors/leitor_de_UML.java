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

public class leitor_de_UML {
	private static final ResourceSet RESOURCE_SET = new ResourceSetImpl();
	public static  File outputFile;
	
	private  URI outputUri ;
	private XMIResource resource ;
public leitor_de_UML(Canvas canvas){
	outputUri =  URI.createFileURI( outputFile.getAbsolutePath() );
	resource = new XMIResourceImpl(outputUri);
    
	try {
		
		resource.load(null);
		UmlHandlefile.resource = resource ;
		MultiPageEditor.uml.init();
		System.out.println("arquivo"+outputFile.toString());
		EObject root =    resource.getContents().get(0);
     //root.eContents().get(0).
     int x = 50 ;
     int y = 50 ;
     System.out.print("ajuda pai");
     for (EObject element :root.eContents()){
	 if( element.getClass().getSimpleName().equals("ClassImpl")){
	 retangulo ret = 	new retangulo(canvas, SWT.NONE);
	 System.out.print("ajuda pai");
	 ret.definir_ponto(x, y);	
	 x  = x+ 130 ;
	 if( x  >=600) {
		 x = 50;
		 y =y + 130 ;
		 
	 }
	 element.eClass();
	 }
	 }
	 } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
