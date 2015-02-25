package editor.editors;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EClassImpl;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLMapImpl;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.internal.impl.AssociationImpl;
import org.eclipse.uml2.uml.internal.impl.ClassImpl;
import org.eclipse.uml2.uml.internal.impl.ClassifierImpl;
import org.eclipse.uml2.uml.internal.impl.TypeImpl;

public class leitor_de_UML {
	private static final ResourceSet RESOURCE_SET = new ResourceSetImpl();
	public static  File outputFile;
	private HashMap<String, retangulo> retangulos = new HashMap<>();
	private  URI outputUri ;
	private XMIResource resource ;
	private EPackage ePackage;
public leitor_de_UML(Tela canvas){
	outputUri =  URI.createFileURI( outputFile.getAbsolutePath() );
	resource = new XMIResourceImpl(outputUri);
	PageDiagrams page = new PageDiagrams(canvas);
	try {
		
		resource.load(null);
		UmlHandlefile.resource = resource ;
		MultiPageEditor.uml.init();

		EObject root =    resource.getContents().get(0);
     //root.eContents().get(0).
     int x = 50 ;
     int y = 50 ;
     for (EObject elemento :root.eContents()){
    	 System.out.print(elemento.getClass().getSimpleName());
    	 if( elemento.getClass().getSimpleName().equals("ClassImpl")){
	 retangulo ret = 	new retangulo(canvas, SWT.NONE);
	 ret.string = ((ClassImpl) elemento).getName();
	 ret.o = elemento ;
	 ret.definir_ponto(x, y);	
	 retangulos.put(ret.string, ret);
	 PageDiagrams.rets.add(ret);
	 x  = x+ 130 ;
	 if( x  >=600) {
		 x = 50;
		 y =y + 130 ;
		 
	 }
    	 } }
     for (EObject element :root.eContents()){
    	 System.out.print(element.getClass().getSimpleName());
    	 if( element.getClass().getSimpleName().equals("ClassImpl")){
	     retangulo ret = retangulos.get(((ClassImpl) element).getName());
        boolean isassociation  = false ;
	
	 for ( EObject ele :element.eContents()) {
		System.out.print(ele.getClass().getSimpleName());
		isassociation = false ; 
		if (ele.getClass().getSimpleName().equals("PropertyImpl")){
			Property pro = (Property) ele;
		
			if (retangulos.containsKey(pro.getName())){
			    retangulo ret1 = retangulos.get(pro.getName());
				   
				   Ponto p = new Ponto();
				   Ponto p1 = new Ponto();
				   linha l = new linha();
				   l.setstyle(new AssociacaoSimples());
				   l.ponto = p;
				     l.ponto_fim = p1 ;	
				    l.ponto_fim.x  = ret1.x;
				    l.ponto_fim.y =  ret1.y +ret1.height/2 ;
				    l.ponto.x = ret.x +ret.width;
				    l.ponto.y = ret.y +ret.height/2;
				    ret.linhas_inicio.add(l);
				   ret1.linhas_fim.add(l);
				   System.out.print("Quantas linhas poxa");
				   page.Menu.add(l);
			       canvas.redraw();
			       isassociation =true ;
			   }
			if (isassociation==false){
				 ret.ajustesize();
				System.out.println("eu te amo satanas "+ret.position);
				ret.atributos_nomes.add(resource.getID(pro.getType())+":"+pro.getName());
			}
			}	
			
		}
	
   	 
    	 }
    	 
    	 MultiPageEditor.uml.classes.add(element);
	 }
    	
    	
     
     } catch (IOException e) {
    	 UmlHandlefile.resource = null ;
    	 MultiPageEditor.uml.init();
		
		 // TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
