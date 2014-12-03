package editor.editors;

import org.eclipse.swt.graphics.GC;

public class heranca  implements Style{
	
	@Override
	public void addfeature(GC gc, linha l) {
		int x = l.ponto_fim.x;
		int y = l.ponto_fim.y;
		float seno = l.getseno();
		float cosseno = l.getcosseno();
		gc.drawLine( (int) (x-8*seno), (int) (y+8*cosseno),(int) (x+8*cosseno), (int) (y+10*seno));
	
		gc.drawLine((int) (x+8*seno), (int) (y-8*cosseno),(int) (x+8*cosseno), (int) (y+10*seno));
		gc.drawLine((int) (x-8*seno), (int) (y+8*cosseno),(int) (x+8*seno),(int) (y-8*cosseno));
		
	   
	}

}
