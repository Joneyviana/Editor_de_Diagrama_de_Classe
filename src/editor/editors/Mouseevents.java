package editor.editors;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tracker;

public class Mouseevents implements MouseListener, MouseMoveListener{
public DrawComposite DrawListener ;
private Cursor busyCursor;
private boolean pressionando ; 
private Cursor aumentacursor;
private LineComposite but;
public 	Mouseevents(DrawComposite comp){
	DrawListener  = comp ;
	busyCursor = new Cursor(Display.getCurrent(), SWT.CURSOR_SIZEWE);
	aumentacursor = new Cursor(Display.getCurrent(), SWT.CURSOR_SIZENS);
}	
	@Override
    public void mouseMove(MouseEvent arg0) {
		if ((arg0.x>= DrawListener.width-6)||(arg0.x<=6)){
			if (((arg0.x>= DrawListener.width-3)||(arg0.x<=3))&&(pressionando==false)){
			((Tela)DrawListener.getParent()).inicio_associacao = DrawListener;
			}		
			else {
				DrawListener.setCursor(busyCursor);
				if (pressionando){
				  
				       
			        Tracker tracker = new Tracker(DrawListener.getParent(), SWT.RESIZE|SWT.LEFT|SWT.RIGHT);
			    	tracker.setRectangles(new Rectangle[] { new Rectangle(DrawListener.x-1, DrawListener.y-1, DrawListener.width+1,DrawListener.height+1), });
			    	tracker.setCursor(busyCursor);
			    	tracker.open();
			    	
			    	DrawListener.width = tracker.getRectangles()[0].width;
			    	DrawListener.x = tracker.getRectangles()[0].x;
			    	
			    	
			    	DrawListener.redraw();
				
		}
			}
		}
			
		else{
			if ((arg0.y>= DrawListener.height-6)||(arg0.y<=6)){
				if (((arg0.y>= DrawListener.height-3)||(arg0.y<=3))&&(pressionando==false)){
					((Tela)DrawListener.getParent()).inicio_associacao = DrawListener;
					}		
					else {
						DrawListener.setCursor(aumentacursor);
				if (pressionando){
					
					       
				        Tracker tracker = new Tracker(DrawListener.getParent(), SWT.RESIZE|SWT.DOWN|SWT.UP|SWT.Resize);
				    	tracker.setRectangles(new Rectangle[] { new Rectangle(DrawListener.x-1, DrawListener.y-1, DrawListener.width+1,DrawListener.height+1), });
				    	tracker.setCursor(aumentacursor);
				    	tracker.open();
				    	
				    	DrawListener.height = tracker.getRectangles()[0].height ;
				    	DrawListener.y= tracker.getRectangles()[0].y;
				    	
				    	
				    	DrawListener.redraw();
				}
					}
					}
				    	else {
				
				    		DrawListener.setCursor(null);
		
		if (pressionando){ 
			Tracker tracker = new Tracker(DrawListener.getParent(), SWT.NONE);
	        tracker.setRectangles(new Rectangle[] { new Rectangle( DrawListener.getLocation().x-1 ,DrawListener.getLocation().y-1, DrawListener.width+1, DrawListener.height+1), });
	        DrawListener.setCursor(null);
	        tracker.open();
		    
	        DrawListener.setLocation(tracker.getRectangles()[0].x,tracker.getRectangles()[0].y);
	        DrawListener.x = tracker.getRectangles()[0].x ;
	        DrawListener.y = tracker.getRectangles()[0].y;
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
	public void mouseDoubleClick(MouseEvent arg0) {
		  DrawListener.redraw();
			if ((arg0.x>= 10)&&(arg0.y<=25)){
				DrawListener.text = new Text(DrawListener,SWT.SINGLE);
				DrawListener.text.setSize(DrawListener.width-4, 20);
				DrawListener.text.setLocation(2,3);
				DrawListener.text.setSelection(DrawListener.text.getText().length());
				DrawListener.text.setFocus();
			   
			}
		
	}

	@Override
	public void mouseDown(MouseEvent arg0) {
		if (arg0.button == 1){
			pressionando= true ;
			}
			if (DrawListener.text !=null){
				DrawListener.string = DrawListener.text.getText();  
				
				DrawListener.text.dispose();
				DrawListener.text = null ;
			}
			    if(DrawListener instanceof DrawWillBeSavedInUml){
			    	((DrawWillBeSavedInUml) DrawListener).setPropertysofClass();
			    }
		if (arg0.y>=28){
			if (but !=null)
			    but.dispose();
			but = new LineComposite(DrawListener.getParent(), SWT.NONE);
            
			 but.definir_ponto( DrawListener.x-120, DrawListener.y +arg0.y -80);  
		}
	}

	@Override
	public void mouseUp(MouseEvent arg0) {
		pressionando = false ;
		
	}

}
