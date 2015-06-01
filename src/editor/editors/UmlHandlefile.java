package editor.editors;

import java.awt.List;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import monitor.SimpleReadFile;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.impl.EAttributeImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Factory;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.DirectedRelationship;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.ParameterableElement;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Relationship;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.VisibilityKind;
import org.eclipse.uml2.uml.AggregationKind;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.internal.impl.ClassImpl;
import org.eclipse.uml2.uml.internal.impl.ClassifierImpl;
import org.eclipse.uml2.uml.internal.impl.TypeImpl;
import org.eclipse.uml2.uml.internal.impl.RelationshipImpl;
import org.eclipse.uml2.uml.internal.impl.AssociationImpl;
import org.eclipse.uml2.uml.resource.UMLResource;



public class UmlHandlefile {
	
	public ArrayList<EObject> classes = new ArrayList<>();
	
	public static XMIResource resource ;
	public Model sampleModel ;

	private URI outputUri;

	private ArrayList<Type> dat = new ArrayList<>() ;

	private Object StringUtils;

	public void init(){
		 if (resource==null){
			  
			 sampleModel =  UMLFactory.eINSTANCE.createModel();
			
			 outputUri =  URI.createFileURI( leitor_de_UML.outputFile.getAbsolutePath() );
				resource = new XMIResourceImpl(outputUri);	
			   
			
			   
			   
				resource.getContents().add( sampleModel );  
		       
		 }else{
		    		
		    	sampleModel = (Model) resource.getContents().get(0);
		        
		    }
	  	
		 
			
	}
	
	private void save(){
   
    	try {
    		resource.save( null );
    		InputStream  stream = openContentStream();
    		MultiPageEditor.file.setContents(stream,true, true, null);
    		
		} catch (IOException | CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	 }
 
private InputStream openContentStream() throws IOException {
		// TODO Auto-generated method stub
	String contents =
			new SimpleReadFile(leitor_de_UML.outputFile.getAbsolutePath()).getText();
		return new ByteArrayInputStream(contents.getBytes());
	}

public EObject addclasse(String str){
    if (str.equals("class")){
      str = str +classes.size();
    }
    	classes.add( (Class) sampleModel.createOwnedClass(str, false));
   
    Integer indice = classes.size();
    while(resource.getIDToEObjectMap().containsKey(indice.toString())){
  
    	indice = indice +1 ;
    }
    System.out.println("olla só isso aqui"+resource.getID(classes.get(classes.size()-1)));
    resource.setID(classes.get(classes.size()-1),indice.toString() );
   
   
    save();
  return classes.get(classes.size()-1);
}
public void removeclasse(EObject o ){
   EcoreUtil.delete(o);
   save();
}
public void addProperty(String text , EObject o ){
	
	Property prop = ((Class) o).createOwnedAttribute(text.substring(text.indexOf(":")+1,text.length() ), handletype(text));
	 
	if (text.contains("+"))
	   prop.setVisibility(VisibilityKind.PUBLIC_LITERAL);
	else {
		if(text.contains("-")){
			prop.setVisibility(VisibilityKind.PRIVATE_LITERAL);	
		}
		else {
			if(text.contains("#")){
				prop.setVisibility(VisibilityKind.PROTECTED_LITERAL);	
			}
		}
	}
	save();
	
    
}
public Generalization addGeneration(EObject o,String text){
	Generalization gene = ((Class) o).createGeneralization((Classifier) handletype(text));
	
	save();
	return gene;
	
}
public Association addAssociation(EObject o,String text){

    Type tipo = handletype(text);
    if (tipo !=null){
	Association asso = ((Class) o).createAssociation(true,AggregationKind.get(AggregationKind.NONE),text, 1, 1, tipo  , false,AggregationKind.get(AggregationKind.NONE), "",1, 1);
    
	save();
    return asso;
    }
  return null;    
}
 public Type handletype(String text){
	
	for ( Type element : dat) {
		System.out.println("ID "+resource.getID(element));
		//System.out.println("text" +text.substring(0, text.indexOf(":")));
		if (resource.getID(element).equals(text))
			
			return element;
		if((text.contains(":"))&&(resource.getID(element).equals(text.substring(0, text.indexOf(":"))))){
			return element ;
		}
	  
	}
	 ClassImpl ca = new ClassImpl() {
		};
	
		dat.add( ca);
		
	resource.getContents().add(dat.get(dat.size()-1));

	if (text.contains(":")== true){
	resource.setID(dat.get(dat.size()-1), text.substring(1, text.indexOf(":")));

	}
	else {
		resource.setID(dat.get(dat.size()-1), text);
		

	}
		return dat.get(dat.size()-1);
	
}
  public void setName(String text , EObject o){
	 ((ClassImpl) classes.get(classes.indexOf(o))).setName(text);
     save();
  }
  public void createoperation(EObject o,String text){
	  String name = text.substring(0, text.indexOf("("));
	  String resto = text.substring(name.length()+1, text.indexOf(")"));
	 ListadeParametros lista = new ListadeParametros(resto,this);
	
	((Class) o).createOwnedOperation(name,lista.getlistname(), lista.getlistTypes());
	 save();
  }
}