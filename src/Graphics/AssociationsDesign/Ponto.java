package Graphics.AssociationsDesign;

public class Ponto {

public int x ;
public int y;

public boolean equals(Object obj){
	 
	if ((this.x == ((Ponto)obj).x)&&(this.y ==((Ponto)obj).y)){
	   
		return true;
	}
		else
		return false;
}
}
