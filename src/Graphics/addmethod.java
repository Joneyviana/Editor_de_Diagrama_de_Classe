package Graphics;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;


public class addmethod implements SelectionListener{

	private retangulo ret;
	private Text text;

	public addmethod(retangulo ret){
		this.ret = ret ;
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
					text.dispose();
				
			}
		});
		text.setLocation(2,ret.getSize().y - 3);
		
		
		
		
		text.setFocus();
		
	}

}
