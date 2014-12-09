package editor.editors;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;


public class Composicao implements Style {

	@Override
	public void addfeature(GC gc, linha l) {
		int x = l.ponto_fim.x;
		int y = l.ponto_fim.y;
		float seno = l.getseno();
		float cosseno = l.getcosseno();
		
		int[] array = {(int) ((x-8*seno)+10*cosseno), (int) ((y+8*cosseno)+10*seno),(int) (x+20*cosseno), (int) (y+20*seno)
				,(int) ((x+8*seno)+10*cosseno), (int) ((y-8*cosseno)+10*seno)	, x , y	
		};
		gc.fillPolygon(array);
		
		
		
	}

}
