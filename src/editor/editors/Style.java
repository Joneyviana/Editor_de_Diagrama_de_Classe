package editor.editors;

import org.eclipse.swt.graphics.GC;

public abstract class Style {

protected static Ponto po= new Ponto() ;
linha l = null;

public abstract void addfeature(GC gc ,linha l) ;


public retangulo verfificaretangulo(PageDiagrams p,Ponto ponto){
	for(retangulo ret :p.rets){
		
		if(linha.ispontodentrodequadrado(ponto, ret.x, ret.y, ret.width, ret.height)){
			System.out.println("sacanagemmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm");
			return ret;
		}
	}
  return null ;
}
}
