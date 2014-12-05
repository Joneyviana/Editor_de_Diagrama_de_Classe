package editor.editors;



import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.GestureEvent;
import org.eclipse.swt.events.GestureListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.LineAttributes;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tracker;

public class retangulo extends Composite implements PaintListener, MouseListener, MouseMoveListener, SelectionListener {
    private Composite composite ;
    private boolean pressionando ; 
    public int x =0;
	public  int y =0;
	public int width =80;
    public int height = 100;
	private Cursor busyCursor;
	public Text text;
	public Label label;
	private String string = "Class";
	public ArrayList<Text> textos = new ArrayList<>();
	private ArrayList<String> atributos_nomes = new  ArrayList<>();
	private int position =28;
	private Cursor aumentacursor;
	public retangulo(Composite parent, int style) {
	
		super(parent, style);
        composite = parent ;
	}

	
	
  public void definir_ponto(int x1 ,int y1 ){
	  this.x = x1 ;
	  this.y = y1 ;
	  UmlHandlefile u = new UmlHandlefile(); 
	  busyCursor = new Cursor(Display.getCurrent(), SWT.CURSOR_SIZEWE);
	   aumentacursor = new Cursor(Display.getCurrent(), SWT.CURSOR_SIZENS);
	   setLocation(x, y);
	   setSize(width, height);
	   setFocus();
	   Menus menus = new Menus();
	   Menu popupMenu = new Menu(this);
	   menus.addMenuitems(new String[]{"Public","Private","Protected"},popupMenu ); 
	 
	    Menu lineMenu  = new Menu(popupMenu);
	    menus.items.get(0).setMenu(lineMenu);
	    menus.items.get(1).setMenu(lineMenu);
	    menus.addMenuitems(new String[]{"int","float","String","Long"}, lineMenu);
		menus.addselelectionlistenerMultiplo(new int[]{3, 4,5,6},this);	

  this.setMenu(popupMenu);
	   addPaintListener(this);
	  addMouseListener(this);
	  addMouseMoveListener(this);
  }
public void checkSubclass(){
	
}



@Override
public void paintControl(PaintEvent arg0) {
	
	

	arg0.gc.setLineAttributes(new LineAttributes(3));
	arg0.gc.setBackground(arg0.display.getSystemColor(SWT.COLOR_YELLOW));
    arg0.gc.setForeground(arg0.display.getSystemColor(SWT.COLOR_BLACK));
	arg0.gc.drawRectangle(0, 0, width, height);
    arg0.gc.fillRectangle(1, 1, width-2, height-2);
    arg0.gc.drawLine(0, (int)(height *0.25),width, (int)(height *0.25));
    arg0.gc.drawLine(0, (int)(height *0.65),width, (int)(height *0.65));
    
    arg0.gc.drawText(string, (int) width/8, (int)(height *0.03));
    int count = 28 ;
    for(String str:atributos_nomes){
	   arg0.gc.drawText(str, 5, (int)(height *0.03+count));
       count+=17;
    }
    setLocation(x, y);
	setSize(width, height);
    //label = new Label(this, SWT.NONE);
    //label.setText("Class");
    
    //label.setBackground(arg0.gc.getBackground());
    //label.setLocation(0+10,3);
    //label.setSize(55, 20);
    
    //text = new Text(this, SWT.SINGLE);
    //text.setBackground(arg0.gc.getBackground()); 
    //text.setLocation(0+10,3);
 	
 // text.setSize(55, 20);
	
 /*text.addFocusListener(new FocusListener() {
	 	
		@Override
		public void focusLost(FocusEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void focusGained(FocusEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	});
	*/
	
	 
}



@Override
public void mouseDoubleClick(MouseEvent arg0) {
	
	
    redraw();
	if ((arg0.x>= 10)&&(arg0.y<=25)){
		text = new Text(this,SWT.SINGLE);
	    text.setSize(55, 20);
		text.setLocation(0+10,3);
		text.setFocus();
	    
	}
	}



@Override
public void mouseDown(MouseEvent arg0) {
	pressionando= true ;
	if (text !=null){
		string = text.getText();  
	    text.dispose();
	    text = null ;
	}
	    for (Text text : textos){
			atributos_nomes.add(text.getText());
		    text.dispose();
		}
		textos.clear();
	    redraw();
	
		
		
}
  


@Override
public void mouseUp(MouseEvent arg0) {
	
	
	pressionando = false ;

}

@Override
public void mouseMove(MouseEvent arg0) {
// if(getCursor().equals(busyCursor)){
	 
 //}
	if ((arg0.x>= width-5)||(arg0.x<=5)){
		this.setCursor(busyCursor);
		if (pressionando){
			
			       
		        Tracker tracker = new Tracker(composite, SWT.RESIZE|SWT.LEFT|SWT.RIGHT);
		    	tracker.setRectangles(new Rectangle[] { new Rectangle(x-1, y-1, width+1,height+1), });
		    	tracker.setCursor(busyCursor);
		    	tracker.open();
		    	
		        width = tracker.getRectangles()[0].width;
		    	x = tracker.getRectangles()[0].x;
		    	
		    	
		    	redraw();
			
	}
	}
		
	else{
		if ((arg0.y>= height-5)||(arg0.y<=5)){
			this.setCursor(aumentacursor);
			if (pressionando){
				
				       
			        Tracker tracker = new Tracker(composite, SWT.RESIZE|SWT.DOWN|SWT.UP);
			    	tracker.setRectangles(new Rectangle[] { new Rectangle(x-1, y-1, width+1,height+1), });
			    	tracker.setCursor(aumentacursor);
			    	tracker.open();
			    	
			        height = tracker.getRectangles()[0].height ;
			    	y= tracker.getRectangles()[0].y;
			    	
			    	
			    	redraw();
			}
		}
			    	else {
			
			this.setCursor(null);
	
	if (pressionando){ 
		Tracker tracker = new Tracker(composite, SWT.NONE);
        tracker.setRectangles(new Rectangle[] { new Rectangle( this.getLocation().x-1 ,this.getLocation().y-1, width+1, height+1), });
        this.setCursor(null);
        tracker.open();
	    
       this.setLocation(tracker.getRectangles()[0].x,tracker.getRectangles()[0].y);
	    x = tracker.getRectangles()[0].x ;
	    y = tracker.getRectangles()[0].y;
        tracker.addDisposeListener(new DisposeListener() {
			
			@Override
			public void widgetDisposed(DisposeEvent arg0) {
			
				
			}
		});
        
		}
		}
		}
			pressionando = false ;
	

}



@Override
public void widgetDefaultSelected(SelectionEvent arg0) {
	// TODO Auto-generated method stub
	
}



@Override
public void widgetSelected(SelectionEvent arg0) {
	System.out.print("Oque euto procurando:"+arg0.getSource());
	arg0.getSource().toString();
	Text text  = new Text(this, SWT.SINGLE);
	text.setSize(55, 20);
	text.setLocation(0+10,position);
	position +=20;
	String str = arg0.getSource().toString();
	text.setText(str.substring(10,str.length()-1 ));
	textos.add(text);
	text.setFocus();
}

}

