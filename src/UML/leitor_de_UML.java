package UML;

import java.beans.Visibility;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.xmi.XMIResource;

import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;

import org.eclipse.swt.SWT;

import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.VisibilityKind;

import org.eclipse.uml2.uml.Type;

import org.eclipse.uml2.uml.Property;

import org.eclipse.uml2.uml.internal.impl.ClassImpl;

import Domain.Representação_de_classe;
import Graphics.PageDiagrams;
import Graphics.Tela;
import Graphics.diagrams;
import Graphics.retangulo;
import Graphics.AssociationsDesign.AssociacaoSimples;
import Graphics.AssociationsDesign.Style;
import Graphics.AssociationsDesign.heranca;
import Graphics.AssociationsDesign.Linha;




public class leitor_de_UML {
	
	public  File outputfile;
	private HashMap<String, retangulo> retangulos = new HashMap<>();
	private  URI outputUri ;
	private XMIResource resource ;
	
	public  UmlHandlefile uml ;
	private PageDiagrams page;
	private Tela canvas;
	private boolean isassociation;
	
	public leitor_de_UML(Tela canvas, IFile file){
	outputfile = new File(file.getLocationURI());
    outputUri =  URI.createFileURI( outputfile.getAbsolutePath() );
	uml = new UmlHandlefile(outputfile , file);
	resource = new XMIResourceImpl(outputUri);
	this.canvas = canvas ;
	canvas.uml = uml;
	
	page = new PageDiagrams(canvas);
	canvas.page = page ;
	try {
		
		resource.load(null);
		uml.resource = resource ;
		uml.init();

		EObject root =    resource.getContents().get(0);
     //root.eContents().get(0).
     int x = 50 ;
     int y = 50 ;
     for (EObject elemento :root.eContents()){
    	 
    	 if( elemento.getClass().getSimpleName().equals("ClassImpl")){
	 retangulo ret = 	new retangulo(canvas, SWT.NONE);
	 ret.string = ((ClassImpl) elemento).getName();
	 ret.o = elemento ;
	 ret.definir_ponto(x, y,null);	
	 retangulos.put(ret.string, ret);
	 page.rets.add(ret);
	 x  = x+ 130 ;
	 if( x  >=600) {
		 x = 50;
		 y =y + 130 ;
		 
	 }
    	 } }
     for (EObject element :root.eContents()){
    	 
    	 if( element.getClass().getSimpleName().equals("ClassImpl")){
	     retangulo ret = retangulos.get(((ClassImpl) element).getName());
       isassociation  = false ;
	
	 for ( EObject ele :element.eContents()) {
		
		isassociation = false ; 
		
		if (ele.getClass().getSimpleName().equals("PropertyImpl")){
			
			Property pro = (Property) ele;
			
			getRelation(pro.getName() , ret ,pro.getType(), new AssociacaoSimples(),pro.getVisibility());
			
			}	
			

		if (ele.getClass().getSimpleName().equals("GeneralizationImpl")){
			
			Generalization get = (Generalization) ele;
			
			
			getRelation(canvas.uml.resource.getID(((Class)get.getGeneral())) , ret ,null,new heranca(),null);
			
			}		
		if (ele.getClass().getSimpleName().equals("OperationImpl")){
	        Operation ope = (Operation) ele ;   
			String parameters = "";
	        for(Parameter named: ope.getOwnedParameters()){
				parameters += named.getName()+":"+resource.getID(named.getType())+",";
			}
			if (parameters.isEmpty()==false){
	        ret.metodos.add(VisibilidadeService.ConverterParaPadraoDiagrama(ope.getVisibility().getName())+ope.getName()+"("+parameters.substring(0,parameters.length()-1)+")");
			}
			else {
				ret.metodos.add(VisibilidadeService.ConverterParaPadraoDiagrama(ope.getVisibility().getName())+ope.getName()+"()");
			}
			}
	 }
	
   	 
    	 }
    	 
    	uml.classes.add(element);
	 }
    	
    	
     
     } catch (IOException e) {
    	 uml.resource = null ;
    	 uml.init();
		diagrams dia = diagrams.getInstance();
		int county = 0;
		int countx = 0;
		if (dia.pacote_incial.equals("")==false){
		ArrayList<String> names = new ArrayList<>();
		for (Representação_de_classe classe:dia.Key(dia.pacote_incial)){
			retangulo ret1 = new retangulo(canvas, SWT.NONE);
		    
		  
		    ret1.definir_ponto(100+countx, 100+county,classe);
		    EObject o =uml.addclasse(classe.name);                    
            ret1.o = o;  
		    names.add(classe.name);
		    page.rets.add(ret1);	   
			   
		    if ((county %500 == 0)&&(county!=0)){
		    	county+= 100;
		    	countx = 0 ;
		    }
		    countx+=100;
		}
		canvas.desenhar_associations(names);
		
		e.printStackTrace();
	}
     }
     }
public void getRelation(String name , retangulo ret , Type type, Style style , VisibilityKind visibilidade){
	if (retangulos.containsKey(name)){
	    retangulo ret1 = retangulos.get(name);
		  Linha l = canvas.criar_uma_linha_na_tela(ret, ret1, style);
		   ret1.linhas_fim.add(l);
		    page.Menu.add(l);
	       canvas.redraw();
	       isassociation =true ;
	   }
	if (isassociation==false){
		
	     if (visibilidade!= null){
		ret.atributos_nomes.add(VisibilidadeService.ConverterParaPadraoDiagrama(visibilidade.getName()) + name+":"+resource.getID(type));
	     }
	     } 
}
}
