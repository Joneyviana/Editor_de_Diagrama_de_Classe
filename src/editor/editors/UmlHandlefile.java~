package editor.editors;

import java.io.File;
import java.io.IOException;

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
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
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
	private static final ResourceSet RESOURCE_SET = new ResourceSetImpl();
	public static  File outputFile;
    
	public UmlHandlefile(){

	 Model sampleModel = UMLFactory.eINSTANCE.createModel();
	    sampleModel.setName( "Sample Model" );
	org.eclipse.uml2.uml.Class classe =  sampleModel.createOwnedClass("buceta", true);
	org.eclipse.uml2.uml.Class classe1 =  sampleModel.createOwnedClass("piru", true);
	Property prop =  classe.createOwnedAttribute("gdgdf", new TypeImpl() {
	
    });
	prop.setType(classe1);
	//pack.createOwnedClass("vegeta", true);
   
    URI outputUri = URI.createFileURI( outputFile.getAbsolutePath() );
    XMIResource resource = new XMIResourceImpl(outputUri);
    resource.setID(classe,"ereterer" );
    resource.setID(classe1,"peixinho" );
    resource.setModified(true);
    resource.setXMIVersion("20110701");
    resource.getContents().add( sampleModel );
    
    try {
		resource.save( null );
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

 }
 }
