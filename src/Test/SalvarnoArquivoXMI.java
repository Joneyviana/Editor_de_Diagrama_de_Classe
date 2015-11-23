package Test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import junit.framework.AssertionFailedError;


import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Property;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import UML.UmlHandlefile;

public class SalvarnoArquivoXMI {

	private  UmlHandlefile uml;
	private  Document doc;
	private  EObject classeuml;
	private  File input;
	private Class Minhaclasse;
	
	

	@Before
	public  void setUp() throws Exception {
		input = new File("/home/joney/workspace/codereader/src/sample/teste.uml");
		uml = new UmlHandlefile(input, null);
	    uml.init();
	    classeuml = uml.addclasse("minhaclasse");
	    Minhaclasse = ((Class)classeuml);
	}
	

	@AfterClass
	public static void tearDown() throws Exception {
		RandomAccessFile arquivo = 
		        new RandomAccessFile("/home/joney/workspace/codereader/src/sample/teste.uml", "rw");
	            arquivo.setLength(0);
	}


	@Test
	public void DeveEscreverATagDaClasse() throws IOException{
		
		assertEquals(1 ,uml.classes.size());
		assertEquals("minhaclasse" , Minhaclasse.getName());	
		assertNotNull(classeuml.eContainer());
	}
	@Test
	public void DeveAdicionarUmaPropriedade() throws IOException {
	
		uml.addProperty("+name:String",classeuml );
		Property propriedade = Minhaclasse.getOwnedAttribute("name", uml.handletype("String"));
		 assertEquals(1 ,Minhaclasse.getOwnedAttributes().size());
		assertEquals("name" ,propriedade.getName());	
		assertEquals(uml.handletype("String") , propriedade.getType());
	}

	@Test
	public void DeveAdicionarUmMetodo() throws IOException {
	
		uml.createoperation(classeuml , "+getname()");
		uml.createoperation(classeuml , "setname()"); 
		uml.createoperation(classeuml , "drawline(String:x)"); 
		
	     EList<Operation> metodos = Minhaclasse.getOwnedOperations();
		 assertEquals(3 , metodos.size());
		assertEquals("getname" , metodos.get(0).getName());	
		assertEquals("setname" , metodos.get(1).getName());	
		assertEquals("drawline" , metodos.get(2).getName());	
	}

	@Test
	public void DeveDeletarAClasse() throws IOException {
	
		uml.removeclasse(classeuml);
		assertNull(classeuml.eContainer());
		 
	}
	@Test
	public void DeveDeletarUmapropriedade() throws IOException {
		//adicionar
		uml.addProperty("name:String",classeuml );
		
		assertEquals(1 , Minhaclasse.getOwnedAttributes().size());
		//deletar
		uml.DeletarPropriedade(classeuml, "name");             
		assertEquals(0 , Minhaclasse.getOwnedAttributes().size());
	}
	@Test
	public void DeveDeletarMetodo() throws IOException {
		//adicionar
		uml.createoperation(classeuml,"getname()");
	    Operation ope = Minhaclasse.getOwnedOperation("getname", null, null);
		assertEquals("getname" , ope.getName());
		//deletar
		uml.DeletarMetodo(classeuml, "getname",null , null);             
		assertEquals(0 , Minhaclasse.getOwnedOperations().size());
	}
}


