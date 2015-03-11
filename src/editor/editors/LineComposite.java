package editor.editors;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;

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
	private static int x_diagram =0;
	private static int y_diagram= 0  ;
	private ArrayList<String> namepacote ;
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

public void definir_ponto(int x , int y,ArrayList<String> str){
	this.x = x ;
	  this.y = y ;
	  namepacote = str ;
	 
	   
	  
	 
	   
	   setBounds(x, y, x+130, y+110);
	   setFocus();	  
       setVisible(true);
       
       
       this.addPaintListener(this);
}


	
	
public void checkSubclass(){
	
}

@Override
public void paintControl(PaintEvent arg0) {
	x_diagram = 0 ;
    y_diagram += 0 ;
    String piru = "lamparaão";
    ArrayList<String> drible = new ArrayList();
    arg0.gc.setForeground(arg0.display.getSystemColor(SWT.COLOR_BLACK));
	arg0.gc.setLineAttributes(new LineAttributes(1));
     this.setBackground(new Color(arg0.display,230,230,230 ));
	 
     ArrayList<HashMap<String, Matcher>> lista_de_retangulos = diagrams.getInstance().pacotes.get(namepacote);
	 
	 for (String pirus : namepacote){
		 x_diagram += 80 ;
	     y_diagram += 10 ;
	     
	     AreaDraw area = new AreaDraw(0, 10, 80, 100, 6, 0);
	   
	     
	     new DrawRectangle(arg0, area, pirus);
 
	 }}
}
