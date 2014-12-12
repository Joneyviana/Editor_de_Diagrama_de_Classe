package editor.editors;

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
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Factory;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.uml2.uml.AggregationKind;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Comment;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.DirectedRelationship;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.ParameterableElement;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Relationship;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.StringExpression;
import org.eclipse.uml2.uml.TemplateParameter;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.Usage;
import org.eclipse.uml2.uml.VisibilityKind;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.internal.impl.ClassifierImpl;
import org.eclipse.uml2.uml.internal.impl.TypeImpl;
import org.eclipse.uml2.uml.resource.UMLResource;
import org.eclipse.uml2.uml.Package;

public class UmlHandlefile {
	
	private ArrayList<EObject> classes = new ArrayList<>();
	
	public static XMIResource resource ;
	private Model sampleModel ;

	private URI outputUri;

	public void init(){
		 if (resource==null){
			 System.out.print("sdgfsdfsdfsdfsdfsdfdsdf");   
			 sampleModel =  UMLFactory.eINSTANCE.createModel();
			 outputUri =  URI.createFileURI( leitor_de_UML.outputFile.getAbsolutePath() );
				resource = new XMIResourceImpl(outputUri);	
			 resource.getContents().add( sampleModel );  
		    }else{
		    	 System.out.print("eywrweuryyyuuiu");	
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

public EObject addclasse(){
    classes.add( sampleModel.createOwnedClass("Class"+classes.size(), false));
    
    Integer indice = classes.size();
    while(resource.getIDToEObjectMap().containsKey(indice.toString())){
    	System.out.print("sera que entra");
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
}
