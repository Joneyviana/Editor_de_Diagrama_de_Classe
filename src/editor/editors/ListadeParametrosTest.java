package editor.editors;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Type;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ListadeParametrosTest {

	private ListadeParametros lista;

	@Before
	public void setUp() throws Exception {
	
	   lista = new ListadeParametros("numero:int , valor:double , letra:char" , new UmlHandlefile());
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testparametros() {
		assertArrayEquals(lista.parametros , new String[]{"numero:int","valor:double","letra:char"});
	}
	@Test
	public void testparametrostype() {
		assertEquals(lista.getype(0) , "int");

} 
	@Test
	public void testparametrosname() {
		assertEquals(lista.getname(0) , "numero");

} 
	
	@Test
	public void testparametrosnamelista() {
		 
		assertEquals(lista.getlistname().get(0) , "numero");
		
		assertEquals(lista.getlistname().get(1) , "valor");
	  
       
	}  
	@Test
	public void testparametrotypeslista() {
	
		assertEquals(lista.getlistTypes().get(1) ,lista.uml.handletype("valor") );
	}
	
}