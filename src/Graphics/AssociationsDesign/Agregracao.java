package Graphics.AssociationsDesign;



import org.eclipse.swt.graphics.GC;




public class Agregracao extends Style{

	@Override
	public void addfeature(GC gc, Linha l) {
		int x = l.ponto_fim.x;
		int y = l.ponto_fim.y;
		float seno = l.getseno();
		float cosseno = l.getcosseno();
		po.x = (int) (x+20*cosseno);
		po.y =(int) (y+20*seno);
		gc.drawLine( (int) ((x-8*seno)+10*cosseno), (int) ((y+8*cosseno)+10*seno),(int) (x+20*cosseno), (int) (y+20*seno));
	   
		gc.drawLine((int) ((x+8*seno)+10*cosseno), (int) ((y-8*cosseno)+10*seno),(int) (x+20*cosseno), (int) (y+20*seno));
		gc.drawLine((int) ((x-8*seno)+10*cosseno), (int) ((y+8*cosseno)+10*seno),x,y);
		gc.drawLine((int) ((x+8*seno)+10*cosseno), (int) ((y-8*cosseno)+10*seno),x,y);
		
	}

}
