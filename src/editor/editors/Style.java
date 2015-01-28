package editor.editors;

import org.eclipse.swt.graphics.GC;

public abstract class Style {

protected static Ponto po= new Ponto() ;
linha l = null;

public abstract void addfeature(GC gc ,linha l) ;


public boolean verfificaretangulo(PageDiagrams p){
	for(retangulo ret :p.rets){
		
		if(linha.ispontodentrodequadrado(po, ret.x, ret.y, ret.width, ret.height)){
			System.out.println("Flaviaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
			p.canvas.setFim_associacao(ret);
		    return true;
		}
	}
  return false ;
}
}
