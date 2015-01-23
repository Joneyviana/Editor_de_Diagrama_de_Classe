package editor.editors;



import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;
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
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.GCData;
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

public class retangulo extends DrawWillBeSavedInUml implements PaintListener, SelectionListener {
   
   public retangulo ret ; 
   public static retangulo entrou_em= null ;
   private int position =28;
	public int redimensionamento =0;
	
	public retangulo(Composite parent, int style) {
	
		super(parent, style);
        string = "class";
	}

	
	
  public void definir_ponto(int x1 ,int y1 ){
	  this.x = x1 ;
	  this.y = y1 ;
	  ret = this ;
	 
	  
	   setLocation(x, y);
	   setSize(width, height);
	   setFocus();
	   Menus menus = new Menus();
	   Menu popupMenu = new Menu(this);
	   menus.addMenuitems(new String[]{"Public","Private","Protected","delete classe"},popupMenu ); 
	 
	    Menu lineMenu  = new Menu(popupMenu);
	    Menu Protected = new Menu(popupMenu);
	    Menu Private = new Menu(popupMenu);
	    menus.items.get(0).setMenu(lineMenu);
	    menus.items.get(1).setMenu(Private);;
	    menus.items.get(2).setMenu(Protected);
	    menus.addMenuitems(new String[]{"+int","+float","+String","+Long","+boolean"}, lineMenu);
	    menus.addMenuitems(new String[]{"#int","#float","#String","#Long","#boolean"}, Protected);
	    menus.addMenuitems(new String[]{"-int","-float","-String","-Long","-boolean"}, Private);
	    menus.items.get(3).addSelectionListener(new SelectionListener() {
			
			

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				MultiPageEditor.uml.removeclasse(o);
	            ret.dispose();
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	    menus.addselelectionlistenerMultiplo(new int[]{4, 5,6,7,8,9,10,11,12,13,14,15},this);	

  this.setMenu(popupMenu);
	  Mouseevents mouse  = new Mouseevents(this);
      addPaintListener(this);
	  addMouseListener(mouse);
	  addMouseMoveListener(mouse);
      this.addListener(SWT.MouseHover, new Listener() {
		
		@Override
		public void handleEvent(Event arg0) {
			System.out.print("hover");
			entrou_em = ret ;
			
		}
	});
  this.addListener(SWT.MouseExit, new Listener() {
	
	@Override
	public void handleEvent(Event arg0) {
		entrou_em = null ;
		
	}
});
  }
public void checkSubclass(){
	
}



@Override
public void paintControl(PaintEvent arg0) {
	AreaDraw area = new AreaDraw(0,0,width,height,1,redimensionamento);
	new DrawRectangle(arg0 ,area ,string , atributos_nomes);
	
	    int count = 28 ;
	    for(String str:atributos_nomes){
		   ajustar_largura(str);
	    	arg0.gc.drawText(str, 5, (int)(height *0.03+count));
	       count+=20;
	    
	    }
	    
	    setLocation(x,y);
		setSize(width, height);

	
	
}


@Override
public void widgetDefaultSelected(SelectionEvent arg0) {
	// TODO Auto-generated method stub
	
}



@Override
public void widgetSelected(SelectionEvent arg0) {
    arg0.getSource().toString();
	Text text  = new Text(this, SWT.SINGLE);
	text.setSize(width-4, 20);
	text.setLocation(2,position);
	ajustesize();
	
	String str = arg0.getSource().toString();
	text.setText(str.substring(10,str.length()-1 )+ ":");
	text.setSelection(text.getText().length());
	textos.add(text);
	text.setFocus();
}
public void ajustesize(){
position +=20;
	
	if (position>=height/1.6+redimensionamento){
		redimensionamento = redimensionamento  + 7;
	    height += redimensionamento;
	}
}
public void  ajustar_largura(String text){
	if(text.length()*8 >= width)
	    width = text.length()*8;
}
}

