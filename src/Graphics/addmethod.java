package Graphics;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

import UML.UmlHandlefile;


public class addmethod implements SelectionListener{

	private retangulo ret;
	private Text text;
	private UmlHandlefile uml;

	public addmethod(retangulo ret , UmlHandlefile uml){
		this.ret = ret ;
	    this.uml = uml;
	}
	@Override
	public void widgetDefaultSelected(SelectionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void widgetSelected(SelectionEvent arg0) {
	    ret.space_new_metodo  = 25;
	    ret.redraw();
		text  = new Text(this.ret, SWT.SINGLE);
		text.setSize(ret.width-4, 20);
		text.addListener(SWT.DefaultSelection, new Listener() {
			
			@Override
			public void handleEvent(Event arg0) {
				if (text.getText().isEmpty()==false)
					ret.metodos.add(text.getText()); 				
					uml.createoperation(ret.o, text.getText());
				    text.dispose();
				    ret.space_new_metodo = 0;
				    ret.redraw();
				
			}
		});
		text.setLocation(2,ret.getSize().y - 3);
		
		
		
		
		text.setFocus();
		
	}

}
