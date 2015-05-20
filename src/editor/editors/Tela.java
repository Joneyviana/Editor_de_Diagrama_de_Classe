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
	public UmlHandlefile uml ;
	public LineComposite little_painel ;
	public Style style;
	public PageDiagrams page ;
	public Tela(Composite parent, int style) {
		super(parent, style);
		this.style = new AssociacaoSimples() ;
		// TODO Auto-generated constructor stub
	}


	public void setFim_associacao(DrawWillBeSavedInUml fim_associacao) {
		this.fim_associacao = fim_associacao;
	    
	    if (fim_associacao !=null){
	    	int tamanho = inicio_associacao.linhas_inicio.size()-1;
	    	linha line =inicio_associacao.linhas_inicio.get(tamanho);
	    	System.out.println("nome da classe" +line.style_linha.getClass().getName());
	    	if( line.style_linha.getClass().getName()=="editor.editors.AssociacaoSimples"){
	    	line.asso = uml.addAssociation(inicio_associacao.o,fim_associacao.string) ;  
	    	fim_associacao.linhas_fim.add(line);
	    	}
	    	else {
	    		line.gene = uml.addGeneration(inicio_associacao.o, fim_associacao.string) ;	
	    		fim_associacao.linhas_fim.add(line);
	    	}
	    	}
	    }

 public void desenhar_associations(ArrayList<String> names){
	 for( retangulo ret :  page.rets) {
		 if((ret.classe.Pai.equals("")==false)&&(names.contains(ret.classe.Pai))){
		
			 retangulo ret_destino =	 page.rets.get(names.indexOf(ret.classe.Pai));
			 criar_uma_linha_na_tela( ret ,ret_destino, new heranca());
			 setFim_associacao(ret_destino);
		 }
		 if (ret.classe.atributos!=null){
		 for (Attribute attr :ret.classe.atributos){
			 if (attr.type.equals("class")==false){  
			 ret.atributos_nomes.add(attr.visibility+attr.type+":"+attr.name);
			  uml.addProperty(ret.atributos_nomes.get(ret.atributos_nomes.size()-1) , ret.o ) ;
		      System.out.println("que name é esse " + attr.name);
			 }
		      if (names.contains(attr.type)){
			retangulo ret_destino =	 page.rets.get(names.indexOf(attr.type));
			 criar_uma_linha_na_tela( ret ,ret_destino, new AssociacaoSimples());
			 setFim_associacao(ret_destino);
			
			 
			 }
		 }
		 }
		 }
  redraw();
 }
public linha setarumalinha(Style style){
	linha line = new linha();
	line.setstyle(style);
	line.ponto = new Ponto();
	line.ponto_fim =  new Ponto();
  return line ;
}
public linha setarumalinha(){
	linha line = new linha();
	line.setstyle(style);
	line.ponto = new Ponto();
	line.ponto_fim =  new Ponto();
  return line ;
}
public linha criar_uma_linha_na_tela(retangulo ret ,retangulo ret_destino, Style style){
	linha line = setarumalinha(style);
	 inicio_associacao = ret ;
	 inicio_associacao.linhas_inicio.add(line);
	 
    
	 
line.ponto.x = ret.x +ret.width/2;
line.ponto.y = ret.y +ret.height/2;
line.ponto_fim.x = ret_destino.x + ret_destino.width/2;
line.ponto_fim.y = ret_destino.y + ret_destino.height/2;
page.Menu.add(line);
 return line;
}
}
