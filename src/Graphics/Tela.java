package Graphics;

import java.util.ArrayList;


import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.GCData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;

import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;


import Domain.Attribute;
import Graphics.AssociationsDesign.AssociacaoSimples;
import Graphics.AssociationsDesign.Ponto;
import Graphics.AssociationsDesign.Style;
import Graphics.AssociationsDesign.heranca;
import Graphics.AssociationsDesign.Linha;
import UML.DrawWillBeSavedInUml;
import UML.UmlHandlefile;


public class Tela extends Canvas implements Draw{
	public  Image image;  
	public Composite pai ;
	public DrawWillBeSavedInUml inicio_associacao;
	private DrawWillBeSavedInUml fim_associacao;
	public UmlHandlefile uml ;
	
	public Style style;
	public PageDiagrams page ;
	public RGB rgb = new RGB(100, 200, 255);
	public Tela(Composite parent, int style) {
		super(parent, style);
		this.style = new AssociacaoSimples() ;
		// TODO Auto-generated constructor stub
	}


	public void setFim_associacao(DrawWillBeSavedInUml fim_associacao) {
		this.fim_associacao = fim_associacao;
	    
	    if (fim_associacao !=null){
	    	int tamanho = inicio_associacao.linhas_inicio.size()-1;
	    	Linha line =inicio_associacao.linhas_inicio.get(tamanho);
	    	
	    	if( line.style_linha.getClass().getName()=="Graphics.AssociationsDesign.AssociacaoSimples"){
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
public Linha setarumalinha(Style style){
	Linha line = new Linha();
	line.setstyle(style);
	line.ponto = new Ponto();
	line.ponto_fim =  new Ponto();
  return line ;
}
public Linha setarumalinha(){
	Linha line = new Linha();
	line.setstyle(style);
	line.ponto = new Ponto();
	line.ponto_fim =  new Ponto();
  return line ;
}
public Linha criar_uma_linha_na_tela(retangulo ret ,retangulo ret_destino, Style style){
	Linha line = setarumalinha(style);
	 inicio_associacao = ret ;
	 inicio_associacao.linhas_inicio.add(line);
	 
    
	 
line.ponto.x = ret.x +ret.width/2;
line.ponto.y = ret.y +ret.height/2;
line.ponto_fim.x = ret_destino.x + ret_destino.width/2;
line.ponto_fim.y = ret_destino.y + ret_destino.height/2;
page.Menu.add(line);
 return line;
}


@Override
public void setRGB(RGB rgb) {
	this.rgb = rgb ;
	page.default_color = new Color(new Device() {
		
		@Override
		public long internal_new_GC(GCData arg0) {
			// TODO Auto-generated method stub
			return 0;
		}
		
		@Override
		public void internal_dispose_GC(long arg0, GCData arg1) {
			// TODO Auto-generated method stub
			
		}
	}, rgb);
	for(retangulo ret : page.rets){
		ret.backgroundcolor = page.default_color;
		ret.redraw();
	}
}
}
