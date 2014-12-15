package editor.editors;

import org.eclipse.swt.graphics.Point;

public class linha {

public Ponto ponto ;

public Ponto ponto_fim ;

public  Style style_linha;

public boolean ispontilhado = false;

public  void setstyle(Style style){
 
	style_linha = style ;	
    
}
public static boolean ispontodentrodequadrado(Ponto p ,int x , int y  ,int  width , int height ){
	if ((p.x>=x)&&(p.y>=y)&&(p.x<=x+width)&&(p.y<=y+height))
	return true ;
	else 
		return false ;
}

public float gethipotenusa(){
	return (float) Math.sqrt(Math.pow(Math.abs(ponto_fim.x - ponto.x),2)+ Math.pow(Math.abs(ponto_fim.y - ponto.y),2));
}
public float getseno(){
	return ((ponto_fim.y-ponto.y)/gethipotenusa());
}
public float getcosseno(){
	return ((ponto_fim.x-ponto.x)/gethipotenusa());
}


}
