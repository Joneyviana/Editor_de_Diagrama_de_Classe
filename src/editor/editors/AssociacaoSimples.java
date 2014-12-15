package editor.editors;


import org.eclipse.swt.graphics.GC;
public class AssociacaoSimples extends Style{
	public linha l;


	
	@Override
	public void addfeature(GC gc, linha l) {
		
		
		int x = l.ponto_fim.x;
		int y = l.ponto_fim.y;
		float seno = l.getseno();
		float cosseno = l.getcosseno();
		
		po.x =(int) (x+8*cosseno);
		po.y = (int) (y+10*seno);
		gc.drawLine( (int) (x-8*seno), (int) (y+8*cosseno),(int) (x+8*cosseno), (int) (y+10*seno));
		
		gc.drawLine((int) (x+8*seno), (int) (y-8*cosseno),(int) (x+8*cosseno), (int) (y+10*seno));
	}




}
