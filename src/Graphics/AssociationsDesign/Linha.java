package Graphics.AssociationsDesign;



import org.eclipse.swt.graphics.Point;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Generalization;


public class Linha {

public Ponto ponto ;

public Ponto ponto_fim ;

public  Style style_linha;
public Association asso;
public Generalization gene;
public boolean ispontilhado = false;

public  float variacao_em_x(){
	return ponto_fim.x - ponto.x;
	
}

private float variacao_em_y(){
	return ponto_fim.y - ponto.y;
}



public  void setstyle(Style style){
 
	style_linha = style ;	
    
}
private float getInclinacaoDaReta(){
	
	System.out.println("variação em x:"+variacao_em_x());
	System.out.println("variação em Y:"+variacao_em_y());
	
		return variacao_em_y()/variacao_em_x();
	

	
}
private int y(float x){
	return   (int) ((getInclinacaoDaReta()*(x -ponto.x))+ ponto.y);
	
}
public boolean ispointontheline(Ponto p){
	
	System.out.println("pontoy: "+p.y +"  resultado: "+y(p.x)   );
	
	if((p.y<=ponto.y)||(p.y<=ponto_fim.y)){
		
		if (6>= Math.abs(p.y-(y(p.x)))){
			return true;
		}
	  return false;
	}
	return false;
}
public static boolean ispontodentrodequadrado(Ponto p ,int x , int y  ,int  width , int height ){
	if ((p.x>=x-6)&&(p.y>=y-6)&&(p.x<=x+width+6)&&(p.y<=y+height+6))
	return true ;
	else 
		return false ;
}
public void change_ponto_fim(Point p ){
	ponto_fim.x = p.x;
	ponto_fim.y = p.y;
}

public static Point difference_points(Point ponto, Point subtractor){
	Point newpoint = new Point(ponto.x,ponto.y);
	newpoint.x = ponto.x - subtractor.x;
	newpoint.y = ponto.y - subtractor.y;
	return newpoint;
	
}
public Ponto soma_points(Ponto ponto, Point soma){
	ponto.x = ponto.x + soma.x;
	ponto.y = ponto.y + soma.y;
	return ponto;
	
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
