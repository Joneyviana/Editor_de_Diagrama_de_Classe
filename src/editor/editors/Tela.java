package editor.editors;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.actions.SimpleWildcardTester;

public class Tela extends Composite{
	public  Image image;  
	public Composite pai ;
	public DrawWillBeSavedInUml inicio_associacao;
	private DrawWillBeSavedInUml fim_associacao;
	
	public LineComposite little_painel ;
	public Style style;
	public Tela(Composite parent, int style) {
		super(parent, style);
		this.style = new AssociacaoSimples() ;
		// TODO Auto-generated constructor stub
	}


	public void setFim_associacao(DrawWillBeSavedInUml fim_associacao) {
		this.fim_associacao = fim_associacao;
	    
	    if (fim_associacao !=null){
	    	inicio_associacao.linhas_inicio.get(inicio_associacao.linhas_inicio.size()-1).asso = MultiPageEditor.uml.addAssociation(inicio_associacao.o,fim_associacao.string) ;  
	    	fim_associacao.linhas_fim.add(inicio_associacao.linhas_inicio.get(inicio_associacao.linhas_inicio.size()-1));
	    }
	    }

 public void desenhar_associations(ArrayList<String> names){
	 for( retangulo ret :  PageDiagrams.rets) {
		 System.out.print("porra stantafrf"+ ret.classe);
		 if (ret.classe.atributos!=null){
		 for (Attribute attr :ret.classe.atributos){
			   ret.atributos_nomes.add(attr.visibility+attr.type+":"+attr.name);
			  MultiPageEditor.uml.addProperty(ret.atributos_nomes.get(ret.atributos_nomes.size()-1) , ret.o ) ;
		      System.out.println("que name é esse " + attr.name);
			 if (names.contains(attr.type)){
			retangulo ret_destino =	 PageDiagrams.rets.get(names.indexOf(attr.type));
			linha line = setarumalinha();
			 line.ponto.x = ret.x +ret.width/2;
			 line.ponto.y = ret.y +ret.height/2;
			 line.ponto_fim.x = ret_destino.x + ret_destino.width/2;
			 line.ponto_fim.y = ret_destino.y + ret_destino.height/2;
			 PageDiagrams.Menu.add(line);
			 
			 }
		 }
		 }
		 }
  redraw();
 }
public linha setarumalinha(){
	linha line = new linha();
	line.setstyle(style);
	line.ponto = new Ponto();
	line.ponto_fim =  new Ponto();
  return line ;
}
}
