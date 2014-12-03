package editor.editors;

import org.eclipse.swt.graphics.Point;

public class linha {

public Ponto ponto ;

public Ponto ponto_fim ;

public  Style style_linha;


public  void setstyle(Style style){
 
	style_linha = style ;	
    
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
