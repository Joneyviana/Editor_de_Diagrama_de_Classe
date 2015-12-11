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
		if(Linha.ispontodentrodequadrado(ponto, ret.getLocation().x, ret.getLocation().y, ret.getSize().x, ret.getSize().y)){
			
			return ret;
		}}
	}
  return null ;
}
}
