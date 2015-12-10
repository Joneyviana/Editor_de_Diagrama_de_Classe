package Graphics;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tracker;


import Graphics.AssociationsDesign.Linha;
import UML.DrawWillBeSavedInUml;

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
		
		int redimensionamento =0;
		if (DrawListener.atributos_nomes.size()>=2){
			redimensionamento = ((DrawListener.atributos_nomes.size() -2)*19) +6;
		}
		if ((arg0.x>= DrawListener.width-6)||(arg0.x<=6)){
			if (((arg0.x>= DrawListener.width-3)||(arg0.x<=3))&&(pressionando==false)){
			
			
			}		
			else {
				DrawListener.setCursor(busyCursor);
				if (pressionando){
				  
				       
			        Tracker tracker = new Tracker(DrawListener.getParent(), SWT.RESIZE|SWT.LEFT|SWT.RIGHT);
			    	tracker.setRectangles(new Rectangle[] { new Rectangle(DrawListener.x-1 ,DrawListener.y-1, DrawListener.width+1, DrawListener.height+1+redimensionamento), });
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
					
					}		
					else {
						DrawListener.setCursor(aumentacursor);
				if (pressionando){
					
					       
				        Tracker tracker = new Tracker(DrawListener.getParent(), SWT.RESIZE|SWT.DOWN|SWT.UP|SWT.Resize);
				    	tracker.setRectangles(new Rectangle[] { new Rectangle(DrawListener.x-1 ,DrawListener.y-1, DrawListener.width+1, DrawListener.height+1+redimensionamento), });
				    	tracker.setCursor(aumentacursor);
				    	tracker.open();
				    	
				    	
				    	
				        DrawListener.height = tracker.getRectangles()[0].height-redimensionamento ;
				    	DrawListener.y= tracker.getRectangles()[0].y;
				    	DrawListener.redraw();
				}
					}
					}
				    	else {
				
				    		DrawListener.setCursor(null);
		
		if (pressionando){ 
			Tracker tracker = new Tracker(DrawListener.getParent(), SWT.NONE);
	        tracker.setRectangles(new Rectangle[] { new Rectangle( DrawListener.x-1 ,DrawListener.y-1, DrawListener.width+1, DrawListener.height+1+redimensionamento), });
	        DrawListener.setCursor(null);
	        tracker.open();
	        Point PointOfRetangle = new Point(tracker.getRectangles()[0].x, tracker.getRectangles()[0].y);
	        atualizar_associationsXfim(DrawListener.linhas_fim,PointOfRetangle, DrawListener.getLocation());
	        atualizar_associationsXInicio(DrawListener.linhas_inicio,PointOfRetangle , DrawListener.getLocation());
	        
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
				DrawListener.text.addListener(SWT.DefaultSelection, new Listener() {
					
					@Override
					public void handleEvent(Event arg0) {
						
						if (DrawListener.text !=null){
							DrawListener.string = DrawListener.text.getText();  
							
							DrawListener.text.dispose();
							DrawListener.text = null ;
						}
						    if(DrawListener instanceof DrawWillBeSavedInUml){
						    	((DrawWillBeSavedInUml) DrawListener).setPropertysofClass();
						    }
					
				}
					
				});
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
		
	}

	@Override
	public void mouseUp(MouseEvent arg0) {
		pressionando = false ;
		
	}
private void atualizar_associationsXInicio(ArrayList<Linha> linhas , Point dimensao , Point dimensao_inicial ){
	 
	 
	 
	 for(Linha linha : linhas){
		 Point ponto = linha.difference_points(dimensao, dimensao_inicial);
			linha.soma_points(linha.ponto , ponto);
		
	}	
	
	DrawListener.getParent().redraw();
}
private void atualizar_associationsXfim(ArrayList<Linha> linhas , Point dimensao , Point dimensao_incial){
	 
	 
	 Point ponto = Linha.difference_points(dimensao ,  dimensao_incial);
	 for(Linha linha : linhas){
		
		linha.soma_points(linha.ponto_fim , ponto);
		
	}	
	
	DrawListener.getParent().redraw();
}
}
