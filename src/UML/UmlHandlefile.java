package UML;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import monitor.SimpleReadFile;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;

import org.eclipse.emf.ecore.EObject;


import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;

import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.Model;

import org.eclipse.uml2.uml.Property;

import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.VisibilityKind;
import org.eclipse.uml2.uml.AggregationKind;


import java.util.ArrayList;



import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;

import org.eclipse.uml2.uml.internal.impl.ClassImpl;






public class UmlHandlefile {
	
	public ArrayList<EObject> classes = new ArrayList<>();
	
	public XMIResource resource ;
	public Model sampleModel ;

	private URI outputUri;

	public ArrayList<Type> dat = new ArrayList<>() ;

	

	

	private File fileread;

	private IFile filewrite;

	public UmlHandlefile(File fileread ,IFile filewrite) {
	this.fileread = fileread;
	this.filewrite = filewrite;
	}

	public void init(){
		 if (resource==null){
			  
			 sampleModel =  UMLFactory.eINSTANCE.createModel();
			
			 outputUri =  URI.createFileURI(fileread.getAbsolutePath() );
				resource = new XMIResourceImpl(outputUri);	
			   
			
			   
			   
				resource.getContents().add( sampleModel );  
		       
		 }else{
		    		
		    	sampleModel = (Model) resource.getContents().get(0);
		        
		    }
	  	
		 
			
	}
	
	public void save(){
   
    	try {
    		resource.save( null );
    		InputStream  stream = openContentStream();
    		if(filewrite != null)
    		filewrite.setContents(stream,true, true, null);
    		
		} catch (IOException | CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	 }
 
public InputStream openContentStream() throws IOException {
		// TODO Auto-generated method stub
	String contents =
			new SimpleReadFile(fileread.getAbsolutePath()).getText();
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
   
    resource.setID(classes.get(classes.size()-1),indice.toString() );
   
   
    save();
  return classes.get(classes.size()-1);
}
public void removeclasse(EObject o ){
   
	EcoreUtil.delete(o);
   save();
}
public String getNameOfProperty(String name , String marcador){
	VisibilityKind visibilidade = getVisibility(name);
	  if (visibilidade!=null){
	       name = name.substring(1, name.indexOf(marcador));
	  }
	  else {
		   name = name.substring(0, name.indexOf(marcador));
	  }
  return name;
}

public void addProperty(String text , EObject o ){
     Property prop = ((Class) o).createOwnedAttribute(getNameOfProperty(text,":"), handletype(text));
	 prop.setVisibility(getVisibility(text)) ;
	
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
		
	
		if (resource.getID(element).equals(text))
			
			return element;
		if((text.contains(":"))&&(resource.getID(element).equals(text.substring(text.indexOf(":")+1, text.length())))){
			return element ;
		}
	  
	}
	 ClassImpl ca = new ClassImpl() {
		};
	
		dat.add( ca);
		
	resource.getContents().add(dat.get(dat.size()-1));

	if (text.contains(":")== true){
	resource.setID(dat.get(dat.size()-1),text.substring(text.indexOf(":")+1, text.length()));

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
	  String name = getNameOfProperty(text, "(");
	  
	  String resto = text.substring(name.length()+1, text.indexOf(")"));
	  Operation ope ;
	  if (resto.contains(":")){
	     ListadeParametros lista = new ListadeParametros(resto,this);
	     ope = ((Class) o).createOwnedOperation(name,lista.getlistname(), lista.getlistTypes());
	 }
	  else {
	     ope = ((Class) o).createOwnedOperation(name,null, null);
	  }
	  ope.setVisibility(getVisibility(name));
	save();
  }

  public VisibilityKind getVisibility(String text){
	VisibilityKind visibilidade = null ; 
	if (text.contains("+"))
		   visibilidade = VisibilityKind.PUBLIC_LITERAL;
		else {
			if(text.contains("-")){
				visibilidade = VisibilityKind.PRIVATE_LITERAL;	
			}
			else {
				if(text.contains("#")){
					visibilidade = VisibilityKind.PROTECTED_LITERAL;	
				}
			}
		} 
	return visibilidade;
}
public void DeletarPropriedade(EObject classeuml , String text){
	Property pro = ((Class)classeuml).getOwnedAttribute(text, null);
	if (pro != null){
	
	removeclasse(pro);
	}			
} 

public void DeletarMetodo(EObject classeuml , String  name , EList<String> ParemeterName , EList<Type> ParemeterType){
	            Operation ope = ((Class)classeuml).getOperation(name, ParemeterName , ParemeterType);
				if (ope != null){
	            removeclasse(ope);
}
				} 


}
