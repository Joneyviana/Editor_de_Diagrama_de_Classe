package Graphics;



import java.util.ArrayList;


import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;

import org.eclipse.swt.graphics.Color;


import org.eclipse.swt.graphics.LineAttributes;

import org.eclipse.swt.graphics.Region;

import org.eclipse.swt.widgets.Composite;

import Domain.Representação_de_classe;




public class LineComposite extends DrawComposite implements PaintListener , MouseListener{
    public ArrayList<retangulo> listDraws;private int x;
	private int y;

	private Tela tela;
	private static int x_diagram =0;
	private static int y_diagram= 0  ;
	
	private ArrayList<String> names = new ArrayList<>();
	private ArrayList<Representação_de_classe> lista_de_retangulos;
	private PageDiagrams page;
	public LineComposite(Composite parent, int style) {
		super(parent, style);
		  tela = (Tela) parent ;
		  Region region = new Region();
		    region.add(x,y,x+100,y+70);
	    region.add(new int[] {  x+100,y+55 ,x+95,y+70,x+120,y+80});
	    this.setRegion(region);
	}

public void definir_ponto(int x , int y , String str){
	this.x = x ;
	  this.y = y ;
	lista_de_retangulos = diagrams.getInstance().Key(str); ;
	 
	   
	  
	 
	   
	   setBounds(x, y, x+130, y+110);
	   setFocus();	  
       setVisible(true);
       
       this.addMouseListener(this);
       this.addPaintListener(this);
}


	
	
public void checkSubclass(){
	
}

@Override
public void paintControl(PaintEvent arg0) {
	x_diagram = 0 ;
    y_diagram += 0 ;
    
    
    arg0.gc.setForeground(arg0.display.getSystemColor(SWT.COLOR_BLACK));
	arg0.gc.setLineAttributes(new LineAttributes(1));
     this.setBackground(new Color(arg0.display,230,230,230 ));
	 
     
	 
	 for (Representação_de_classe classe: lista_de_retangulos){
		 x_diagram += 80 ;
	     y_diagram += 10 ;
	     
	     //AreaDraw area = new AreaDraw(x_diagram,y_diagram, 80, 100, 6, 0);
	   
	     
	     //new DrawRectangle(arg0, area,null ,  classe.name);
 
	 }}
@Override
public void mouseUp(MouseEvent arg0) {
    Quadro_inicial patern = (Quadro_inicial) tela.pai;
    
    
    Tela canvas = new Tela(patern.getParent() , SWT.NONE);
    this.setParent(canvas);
    page = new PageDiagrams(canvas);
    int countx = 0;
    int county = 0;
   
    for(Representação_de_classe str : lista_de_retangulos){
    int[] x_y =	instanciar_retangulo(str ,countx ,county);
    countx = x_y[0];
    county = x_y[1];
    }
    patern.filho = canvas;
    //tela.dispose();
    tela.desenhar_associations(names);
    patern.redraw();

}

@Override
public void mouseDoubleClick(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void mouseDown(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}
public int[] instanciar_retangulo(Representação_de_classe str ,int x , int y){
	int [] x_y = new int[2];
	x_y[0] = x;
	x_y[1] = y ;
	retangulo ret = new retangulo(tela , SWT.NONE);
    ret.definir_ponto(100+x_y[0], 100+x_y[1],str);
   
    EObject o = tela.uml.addclasse(str.name);                    
    page.rets.add(ret);
    names.add(str.name);
    ret.o = o;
    if ((x_y[1] %500 == 0)&&(x_y[1]!=0)){
    	x_y[1]+= 100;
    	x_y[0] = 0 ;
    }
    x_y[0]+=100;
    
    return x_y;    
}
   

}