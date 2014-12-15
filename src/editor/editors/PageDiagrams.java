package editor.editors;

import java.util.ArrayList;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.LineAttributes;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.ui.part.FileEditorInput;

public class PageDiagrams {

public  Tela  canvas;
private Style style;
private ArrayList <Ponto> risco = new ArrayList<>();
private ArrayList <linha> Menu = new ArrayList<>();
public ArrayList<retangulo> rets = new ArrayList<>();
private Ponto posicao_direita_inicio;
private boolean pressionado;
public UmlHandlefile uml = MultiPageEditor.uml; 
public Display display;
private PageDiagrams page ;
private AssociacaoSimples assoc;
public PageDiagrams( final Tela canvas){
	style = new Composicao() ;
	 this.canvas = canvas;
	GridLayout layout = new GridLayout();
	canvas.setLayoutData(layout);
	layout.numColumns = 2;
	page = this;
	int count = 0;
	  
			canvas.addPaintListener(new PaintListener() {
		          private PaintEvent eventodesenho;
				
				private Object black;

				
				
		         
		          public void paintControl(PaintEvent e) {
		        	e.gc.setLineAttributes(new LineAttributes(3));
		        	e.gc.textExtent("fdf");
		            
		        	e.gc.setBackground(e.display.getSystemColor(SWT.COLOR_BLACK));
		            e.gc.setForeground(e.display.getSystemColor(SWT.COLOR_BLACK));
		            
		            display = e.display;
		          
		      
		           
		            for (linha line : Menu){
		            	    
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
			
			linha line = new linha();
			line.setstyle(style);
			line.ponto = new Ponto();
			line.ponto_fim =  new Ponto();
			risco.clear();
			Menu.add(line);
			if(arg0.button == 3){
				System.out.print("e de direito");
			    posicao_direita_inicio = new Ponto() ;
				posicao_direita_inicio.x = arg0.x ;
			    posicao_direita_inicio.y = arg0.y ;
			}
			else{    	
			pressionado = true ;
			
			}
		}
	});
   canvas.addListener(SWT.MouseMove, listener);
   
   canvas.addListener(SWT.MouseUp, new Listener() {
		
		@Override
		public void handleEvent(Event arg0) {
			pressionado = false ;
			canvas.inicio_associacao = null;
			if (Menu.get(Menu.size()-1).style_linha==null)
			assoc.verfificaretangulo(page);
			else 
				Menu.get(Menu.size()-1).style_linha.verfificaretangulo(page);
		}
	});
  
	
    
    
	  Menu popupMenu = new Menu(canvas);
	  
	  MenuItem newItem = new MenuItem(popupMenu, SWT.NONE);
	    newItem.setText("Class");
	    newItem.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
			
						retangulo ret = new retangulo(canvas,SWT.NONE );
                           EObject o = uml.addclasse();                    
                           
                           ret.o = o;                    
                           rets.add(ret);
                           ret.definir_ponto(posicao_direita_inicio.x, posicao_direita_inicio.y);
						     
                       
                       
						int x1 = canvas.getSize().x;
						int y1 = canvas.getSize().y ;
						
						canvas.pack();
					    canvas.setSize(x1, y1);
						
						canvas.layout(true);
						canvas.redraw();
						
						
						
				};
				
						
						
						
						
			
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				
				
			}
		});
	
			
		
			
			
				
		
	    MenuItem refreshItem = new MenuItem(popupMenu, SWT.CASCADE);
	    refreshItem.setText("Line style");
	   
	    Menu lineMenu  = new Menu(popupMenu);
	    refreshItem.setMenu(lineMenu);

	    MenuItem shortcutItem = new MenuItem(lineMenu, SWT.NONE);
	    shortcutItem.setText("herança");
	    shortcutItem.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				style = new heranca();
				
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
				style = new AssociacaoSimples();
				
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
				style = new Agregracao();
				
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
				style = new Composicao();
				
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
				style = null ;
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	    MenuItem deleteItem = new MenuItem(popupMenu, SWT.NONE);
	    deleteItem.setText("Delete");

	    canvas.setMenu(popupMenu);
}
}

