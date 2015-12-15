package Graphics;

import java.util.ArrayList;
import Sample.*;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.SWT;


import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import org.eclipse.swt.graphics.Device;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GCData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.LineAttributes;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;

import org.eclipse.swt.layout.GridLayout;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;

import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.ScrollBar;
import org.eclipse.swt.widgets.Scrollable;
import org.eclipse.swt.widgets.Shell;

import org.eclipse.swt.graphics.*;

import Graphics.AssociationsDesign.Agregracao;
import Graphics.AssociationsDesign.AssociacaoSimples;
import Graphics.AssociationsDesign.Composicao;
import Graphics.AssociationsDesign.Ponto;
import Graphics.AssociationsDesign.heranca;
import Graphics.AssociationsDesign.Linha;
import Sample.Flowchart;




public class PageDiagrams {

public  Tela  canvas;
 
private ArrayList <Ponto> risco = new ArrayList<>();
public  ArrayList <Linha> Menu = new ArrayList<>();
public  ArrayList<retangulo> rets = new ArrayList<>();
private Ponto posicao_direita_inicio;
private boolean pressionado;

public Display display;
private PageDiagrams page ;
private AssociacaoSimples assoc;
private Device device;

public Color default_color;

private Color Color;


public PageDiagrams( final Tela canvas){
	
	 this.canvas = canvas;
	GridLayout layout = new GridLayout();
	canvas.setLayoutData(layout);
	device = new Device() {
		
		@Override
		public long internal_new_GC(GCData arg0) {
			// TODO Auto-generated method stub
			return 0;
		}
		
		@Override
		public void internal_dispose_GC(long arg0, GCData arg1) {
			// TODO Auto-generated method stub
			
		}
	};
	default_color = new Color(device,canvas.rgb);
	Color =  new Color(device,new RGB(10,10 ,10));
	layout.numColumns = 2;
	page = this;
	
	  
			canvas.addPaintListener(new PaintListener() {
		         
				
				

				
				
		         
		          public void paintControl(PaintEvent e) {
		        	e.gc.setLineAttributes(new LineAttributes(2));
		        	
		        	
		        	e.gc.setBackground(e.display.getSystemColor(SWT.COLOR_BLACK));
		            e.gc.setForeground(e.display.getSystemColor(SWT.COLOR_BLACK));
		            
		            display = e.display;
		            canvas.image = new Image(canvas.getDisplay(), canvas.getBounds());
		           e.gc.copyArea(canvas.image, 0, 0);
		           
		            for (Linha line : Menu){
		            	    
		            	if (line.style_linha==null){
			        		 e.gc.setLineStyle(SWT.LINE_DASH);
			        	 }
		            	else {
		            		e.gc.setLineStyle(SWT.LINE_SOLID);
		            	}
		            	
		             if (line.ponto.equals(line.ponto_fim)==false){
		      
		            	 e.gc.drawLine( (int) (line.ponto.x - 8*line.getcosseno()),(int) (line.ponto.y-10*line.getseno()), line.ponto_fim.x,line.ponto_fim.y );
		            	 if (line.style_linha==null){
		            		 
		            		 assoc = new AssociacaoSimples();
		            	     assoc.addfeature(e.gc, line);
		            	    
		            	 }
		            	 else {
		            	 line.style_linha.addfeature(e.gc, line);
		            	 
		            	 }
		            	 }
		           
		            }
			
		}
	
	
});
Listener listener = new Listener() {
   
	int count = 0;
   	public void handleEvent(Event e) {
      
       	count +=1;
       	if ((pressionado == true)&&(count%4==0)){
       	   if(canvas.inicio_associacao!=null){
       		 
       	   Ponto ponto = new Ponto();	
       	ponto.x = e.x ;
       	ponto.y = e.y ;
           risco.add(ponto);
       	if (Menu.isEmpty()==false){
           Menu.get(Menu.size()-1).ponto.x = risco.get(0).x;
       	Menu.get(Menu.size()-1).ponto.y = risco.get(0).y;
       	Menu.get(Menu.size()-1).ponto_fim.x =risco.get(risco.size()-1).x;
       	Menu.get(Menu.size()-1).ponto_fim.y = risco.get(risco.size()-1).y;
       	canvas.redraw();
       
       	}
       	   }
       	   }
       	else {
       		
       		
       	}
       }
       };
   canvas.addListener(SWT.MouseDown, new	Listener() {
		
		

		@Override
		public void handleEvent(Event arg0) {
			 
			  Linha line = canvas.setarumalinha();
			risco.clear();
			Menu.add(line);
			if(arg0.button == 3){
				
			    posicao_direita_inicio = new Ponto() ;
				posicao_direita_inicio.x = arg0.x ;
			    posicao_direita_inicio.y = arg0.y ;
			    line.style_linha.verificarLinha(page, posicao_direita_inicio);
			}
			else{    	
			 Ponto ponto = new Ponto();
			 ponto.x = arg0.x;
			 ponto.y = arg0.y;
			 retangulo ret = line.style_linha.verfificaretangulo(page,ponto);
			 
			 if (ret!=null){
				 canvas.inicio_associacao = ret ; 
				 
				 ret.linhas_inicio.add(line);
			 }
			
				 
			
			 pressionado = true ;
			
			}
		}
	});
   canvas.addListener(SWT.MouseMove, listener);
   
   canvas.addListener(SWT.MouseUp, new Listener() {
		
		@Override
		public void handleEvent(Event arg0) {
			pressionado = false ;
			
			if (Menu.get(Menu.size()-1).style_linha==null){
				retangulo ret = assoc.verfificaretangulo(page,Menu.get(Menu.size()-1).ponto_fim);
				if(ret==null){
			
				Menu.remove(Menu.size()-1);
			 canvas.redraw();
			}
				else {
					//ret.linhas_fim.add(Menu.get(Menu.size()-1));
					
				}
					canvas.setFim_associacao(ret);}
			else{ 
				
				retangulo ret = Menu.get(Menu.size()-1).style_linha.verfificaretangulo(page,Menu.get(Menu.size()-1).ponto_fim);
			if(ret==null){
		
			Menu.remove(Menu.size()-1);
		 canvas.redraw();
		
		}
			else {
				//ret.linhas_fim.add(Menu.get(Menu.size()-1));
				 
			}
				canvas.setFim_associacao(ret);}
			canvas.inicio_associacao = null;
		}
	});
  
	
    
    
	  Menu popupMenu = new Menu(canvas);
	  
	  MenuItem newItem = new MenuItem(popupMenu, SWT.NONE);
	    newItem.setText("Class");
	    newItem.addSelectionListener(new SelectionListener() {
			
		















	

		
			@Override
			public void widgetSelected(SelectionEvent arg0) {
			
						retangulo ret = new retangulo(canvas, SWT.NONE );
                           EObject o = canvas.uml.addclasse("class");                    
                            
                   		ret.o = o;                    
                             ret.definir_ponto(posicao_direita_inicio.x, posicao_direita_inicio.y,null);
                           ret.backgroundcolor = default_color;
                             rets.add(ret);
                           
                           
                      	 
						
			};
				
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				
				
			}
		});
	
		MenuItem refreshItem = new MenuItem(popupMenu, SWT.CASCADE);
	    refreshItem.setText("Line style");
	   
	    Menu lineMenu  = new Menu(popupMenu);
	    refreshItem.setMenu(lineMenu);
	    MenuItem mudarCorDiagrama  = new MenuItem(popupMenu,SWT.NONE );
	    mudarCorDiagrama.setText("Mudar cor das entidades");
	    TrocarCor cor = new TrocarCor(canvas);
	    mudarCorDiagrama.addSelectionListener(cor);
	    MenuItem shortcutItem = new MenuItem(lineMenu, SWT.NONE);
	    shortcutItem.setText("herança");
	    shortcutItem.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				canvas.style = new heranca();
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	    MenuItem iconItem = new MenuItem(lineMenu, SWT.NONE);
	   
	    iconItem.setText("Associação Simples");
	    iconItem.addSelectionListener(new SelectionListener() {
			
			

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				canvas.style = new AssociacaoSimples();
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	    
	    MenuItem agregacao = new MenuItem(lineMenu, SWT.NONE);
	    agregacao.setText("Agregação");
	   
	    agregacao.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				canvas.style = new Agregracao();
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	    MenuItem composicao = new MenuItem(lineMenu, SWT.NONE);
	    composicao.setText("Composição");
	    composicao.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				canvas.style = new Composicao();
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	    MenuItem dependencia = new MenuItem(lineMenu, SWT.NONE);
	    dependencia.setText("Dependencia");
	    dependencia.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				canvas.style = null ;
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	    MenuItem deleteItem = new MenuItem(popupMenu, SWT.NONE);
	    deleteItem.setText("Delete relationship");
        deleteItem.setEnabled(false); 
	    canvas.setMenu(popupMenu);
}








}

