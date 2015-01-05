package editor.editors;



import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
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
import org.eclipse.swt.graphics.Region;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;

public class LineComposite extends Button implements PaintListener{

	private Region reg;
	private int x;
	private int y;
	private int x1;
	private int y1;
	private Tela tela;
	private int height = 100 ;
	private int width= 80;
	private Cursor busyCursor;
	private Cursor aumentacursor;
	public LineComposite(Tela parent, int style) {
		super(parent, style);
		  tela = parent ;
	}

public void definir_ponto(int x , int y,int x1 , int y1){
	this.x = x1 ;
	  this.y = y1 ;
	  System.out.println("Entrouu no Line Composite");
	 
	   
	  
	 
	   //setSize(reg.getBounds().width, reg.getBounds().height);
	   setBounds(352, 0, 161, 79);
	   setFocus();	  
       setVisible(true);
       this.addPaintListener(this);
       
}

@Override
public void paintControl(PaintEvent arg0) {
	System.out.println("entrou cacete");
	arg0.gc.drawImage(tela.image, 0, 0, tela.getSize().x, tela.getSize().y, 0, 0,148,99);
}	
public void checkSubclass(){
	
}

}
