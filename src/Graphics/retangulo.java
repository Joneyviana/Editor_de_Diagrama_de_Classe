package Graphics;



import java.util.ArrayList;
import java.util.HashMap;



import org.eclipse.swt.SWT;

import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;

import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.GCData;

import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import org.eclipse.swt.widgets.Text;
import org.eclipse.uml2.uml.Operation;


import Domain.Representação_de_classe;
import Graphics.AssociationsDesign.Linha;
import UML.DrawWillBeSavedInUml;
import UML.ListadeParametros;


public class retangulo extends DrawWillBeSavedInUml implements PaintListener, SelectionListener , Draw {
   
   public retangulo ret ; 
   public retangulo entrou_em= null ;
   int position =28;
	public int redimensionamento_attributos =0;
	
	
	private int count;
	public int space_new_property = 0;
	public int space_new_metodo=0 ; 
	public RGB rgb ;
	public Representação_de_classe classe ;
	private Device device;
	public Color backgroundcolor = null;
	private Composite pai;
	private List attributos;
	private List operations;
	public int redimensionamento_metodos;
	private MenuItem adicionarpropriedade;
	private Menu propriedademenu;
	private Menu metodomenu;
	private MenuItem adicionarmetodo;
	private MenuItem deletarmetodo;
	public retangulo(Composite parent, int style) {
	
		super(parent, style);
        pai = parent ;
		string = "class";
		  
	
	}

	
	
  public void definir_ponto(int x1 ,int y1 ,Representação_de_classe classe ){
	  this.x = x1 ;
	  this.y = y1 ;
	  ret = this ;
	  this.classe = classe ;
	  
	  FontData fo = new FontData("helvetica", 11, SWT.BOLD);
	  attributos = new List(ret,  SWT.V_SCROLL
	            | SWT.H_SCROLL);
	  operations = new List(ret,  SWT.V_SCROLL
	            | SWT.H_SCROLL);
	  this.device = new Device() {
			
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
	  attributos.setFont(new Font(device, fo));
	  operations.setFont(new Font(device, fo));
	  operations.getVerticalBar().setEnabled(false);
	  attributos.getVerticalBar().setEnabled(false);
	  if (classe !=null){
	  
	  
	  string = classe.name ;
	  }
	   
	  
	  setLocation(x, y);
	   setSize(width, height);
	   setFocus();
	   metodomenu = new Menu(operations);
	   addmethod add = new addmethod(this, tela.uml);
	   operations.setMenu(metodomenu);
	   adicionarmetodo = new MenuItem(metodomenu, SWT.CASCADE); 
	   adicionarmetodo.setText("adicionar metodo");
	   adicionarmetodo.addSelectionListener(add);
	   deletarmetodo = new MenuItem(metodomenu, SWT.CASCADE); 
	   deletarmetodo.setText("deletar metodo");
	   deletarmetodo.addSelectionListener(new SelectionListener() {
		
		@Override
		public void widgetSelected(SelectionEvent arg0) {
			String operation = operations.getItem(operations.getSelectionIndex());
		    metodos.remove(operation);	
		    String name = tela.uml.getNameOfProperty(operation, "(");
		    System.out.println("sera deletado");
		    String resto = operation.substring(name.length()+1, operation.indexOf(")"));
			if (resto.contains(":")){
			   ListadeParametros lista = new ListadeParametros(resto,tela.uml);
		       System.out.println("sera deletado");
			   tela.uml.DeletarMetodo(ret.o, name, lista.getlistname(), lista.getlistTypes());
			}
			else {
				tela.uml.DeletarMetodo(ret.o, name, null, null);
			}
		  redraw();
		}
		@Override
		public void widgetDefaultSelected(SelectionEvent arg0) {
			
			
		}
	});
	   propriedademenu = new Menu(attributos);
	   attributos.setMenu(propriedademenu);
	   adicionarpropriedade = new MenuItem(propriedademenu, SWT.CASCADE);
	   adicionarpropriedade.setText("adicionar attributo");
	   adicionarpropriedade.addSelectionListener(this);
	   MenuItem deletarpropriedade = new MenuItem(propriedademenu, SWT.CASCADE);
	   deletarpropriedade.setText("deletar attributo");
	   deletarpropriedade.addSelectionListener(new SelectionListener() {
	  	
		@Override
		public void widgetSelected(SelectionEvent arg0) {
			String attributo = attributos.getItem(attributos.getSelectionIndex());
			atributos_nomes.remove(attributo);
			String name;
			name = tela.uml.getNameOfProperty(attributo, ":");
			tela.uml.DeletarPropriedade(o,name);
			redraw();
		}
		
		@Override
		public void widgetDefaultSelected(SelectionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	});
	   Menus menus = new Menus();
	   Menu popupMenu = new Menu(this);
	   menus.addMenuitems(new String[]{"delete classe","mudar cor"},popupMenu ); 
	   
	    
	    
	    menus.items.get(0).addSelectionListener(new SelectionListener() {
			
			

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				tela.uml.removeclasse(o);
	            for(Linha li : linhas_inicio){
	            	tela.page.Menu.remove(li);
	            	if (li.asso!=null)
	            	tela.uml.removeclasse(li.asso);
	            	
	            }
	            for(Linha li : linhas_fim){
	            	tela.page.Menu.remove(li);
	            	if (li.asso!=null)
	            	tela.uml.removeclasse(li.asso);
	            	
	            }
	            getParent().redraw();
	            ret.dispose();
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	    	
        
         TrocarCor cor = new TrocarCor(ret);
         menus.items.get(1).addSelectionListener(cor);
         this.setMenu(popupMenu);
	  Mouseevents mouse  = new Mouseevents(this);
      addPaintListener(this);
	  addMouseListener(mouse);
	  addMouseMoveListener(mouse);
      this.addListener(SWT.MouseHover, new Listener() {
		
		@Override
		public void handleEvent(Event arg0) {
		
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
	redimensionamento_metodos = redimensionar(metodos, operations);
	redimensionamento_attributos = redimensionar(atributos_nomes,attributos);
	redimensionamento_attributos += space_new_property;
	redimensionamento_metodos += space_new_metodo;
	if (rgb!=null){
	backgroundcolor = new Color(device ,rgb);
	}
	
	new DrawRectangle(arg0 ,ret,string);
	    
	    count = 25 ;
	    
	    operations.removeAll();
	    operations.setVisible(true);
	    int second_line = (int)(height *0.65)+redimensionamento_attributos;
	    operations.setLocation((int) (width*0.05) , second_line + 5  );
	    operations.setSize((int) (width*0.9) ,((height+redimensionamento_attributos + redimensionamento_metodos -5)-second_line-3)-space_new_metodo);
	    attributos.removeAll();
	    
	    attributos.setLocation((int) (width*0.05) , (int)(height *0.03+count));
	    attributos.setSize((int) (width*0.9) ,  (second_line-(int)(height *0.06+count)-2)-space_new_property);
	    attributos.setVisible(true);
	    for(String str:atributos_nomes){
		   ajustar_largura(str);
	    	
		    
		      attributos.add(str);
	    		}
	    operations.setForeground(arg0.display.getSystemColor(SWT.COLOR_BLACK));
	    attributos.setForeground(arg0.display.getSystemColor(SWT.COLOR_BLACK)) ; 	
	   
	
	    	attributos.setBackground(ret.backgroundcolor);
	        operations.setBackground(ret.backgroundcolor);  
	    count = 5 ;
          for(String str : metodos){
        	  ajustar_largura(str);
        	  operations.add(str);
          }
	    setLocation(x,y);
		setSize(width, height+redimensionamento_attributos + redimensionamento_metodos);
		
	
	
}


@Override
public void widgetDefaultSelected(SelectionEvent arg0) {
	// TODO Auto-generated method stub
	
}
@Override
public void widgetSelected(SelectionEvent arg0) {
    arg0.getSource().toString();
    space_new_property = 25;
    ret.redraw();
    final Text text  = new Text(this, SWT.SINGLE);
	text.setSize(width-4, 20);
	text.addListener(SWT.DefaultSelection, new Listener() {
		
		@Override
		public void handleEvent(Event arg0) {
			if (text.getText().isEmpty()==false)
				ret.atributos_nomes.add(text.getText()); 				
				tela.uml.addProperty(text.getText(), ret.o);
			    text.dispose();
			
		}
	});
	if (atributos_nomes.size()!=0){
	text.setLocation(2,((atributos_nomes.size()+1)*20)+25);
	}
	else {
		text.setLocation(2,((2)*20)+25);
	}
	String str = arg0.getSource().toString();

	text.setSelection(text.getText().length());
	textos.add(text);
	text.setFocus();
}

public void  ajustar_largura(String text){
	if(text.length()*7 >= width)
	    width = text.length()*7;
       if(width>220){
    	   width = 220;
       }
}
public int redimensionar(ArrayList<String> lista, List listview){
	int redimensionamento = 0;
	if (lista.size()>6){
		redimensionamento = (lista.size() -2)*7 +6;
	    listview.getVerticalBar().setEnabled(true);
	}
	else {
		listview.getVerticalBar().setEnabled(false);
		if(lista.size()>=1){
			redimensionamento= (lista.size() -1)*25 +6;
			
		
		}
		
	}
   if (redimensionamento>170){
	   redimensionamento = 170;
   }
	return redimensionamento ;
}



@Override
public void setRGB(RGB rgb) {
	this.rgb = rgb;
	
}

}

