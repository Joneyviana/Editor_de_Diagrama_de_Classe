package editor.editors;



import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.GCData;
import org.eclipse.swt.graphics.LineAttributes;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.Region;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;

public class LineComposite extends DrawComposite implements PaintListener{
    public ArrayList<retangulo> listDraws;private int x;
	private int y;
	private int x1;
	private int y1;
	private Composite tela;
	private int height = 100 ;
	private int width= 80;
	private Cursor busyCursor;
	private Cursor aumentacursor;
	public LineComposite(Composite parent, int style) {
		super(parent, style);
		  tela = parent ;
		  Region region = new Region();
		    region.add(x,y,x+100,y+70);
	    region.add(new int[] {  x+100,y+55 ,x+95,y+70,x+120,y+80});
	    this.setRegion(region);
	}

public void definir_ponto(int x , int y){
	this.x = x ;
	  this.y = y ;
	 
	 
	   
	  
	 
	   
	   setBounds(x, y, x+130, y+110);
	   setFocus();	  
       setVisible(true);
       
       
       this.addPaintListener(this);
}


	
	
public void checkSubclass(){
	
}

@Override
public void paintControl(PaintEvent arg0) {
	 System.out.println("Entrouu no Line Composite");
	arg0.gc.setForeground(arg0.display.getSystemColor(SWT.COLOR_BLACK));
	arg0.gc.setLineAttributes(new LineAttributes(1));
     this.setBackground(new Color(arg0.display,230,230,230 ));
	for (retangulo ret : PageDiagrams.rets){
	AreaDraw area = new AreaDraw(ret.x,ret.y,ret.width,ret.height,6,0);
	new DrawRectangle(arg0 ,area ,"class" , atributos_nomes);
	}
	}

}
