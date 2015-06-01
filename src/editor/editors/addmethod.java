package editor.editors;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Text;

public class addmethod implements SelectionListener{

	private retangulo ret;

	public addmethod(retangulo ret){
		this.ret = ret ;
	}
	@Override
	public void widgetDefaultSelected(SelectionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void widgetSelected(SelectionEvent arg0) {
		System.out.println("tres careeeeeeeeeeeeeeeeeeeeeeeeeeee");
		Text text  = new Text(this.ret, SWT.SINGLE);
		text.setSize(ret.width-4, 20);
		
		text.setLocation(2,(int) ((int) ((ret.metodos.size())*20)+(ret.height*0.65)));
		
		
		
		ret.textos.add(text);
		text.setFocus();
		
	}

}
