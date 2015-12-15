package Graphics.AssociationsDesign;

import org.eclipse.swt.graphics.GC;

import Graphics.PageDiagrams;
import Graphics.retangulo;

public abstract class Style {

protected static Ponto po= new Ponto() ;
Linha l = null;

public abstract void addfeature(GC gc ,Linha l) ;


public retangulo verfificaretangulo(PageDiagrams p,Ponto ponto){
	for(retangulo ret :p.rets){
		if (ret !=null){
		    
			if(Linha.ispontodentrodequadrado(ponto, ret.x, ret.y, ret.width, ret.height+ret.redimensionamento_attributos+ret.redimensionamento_metodos)){
			
			return ret;
		}}
	}
  return null ;
}
public  Linha verificarLinha(PageDiagrams p , Ponto ponto){
	for(Linha linha :p.Menu){
	  if(linha.ispointontheline(ponto)){
		  return linha ;
	  }

	}
    return null;
}
}
