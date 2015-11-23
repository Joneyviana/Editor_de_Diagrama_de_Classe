package Graphics;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.ColorDialog;


public class TrocarCor implements SelectionListener{

private retangulo ret;

public TrocarCor(retangulo ret){
	this.ret = ret ;
}


@Override
public void widgetDefaultSelected(SelectionEvent arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void widgetSelected(SelectionEvent arg0) {
	ColorDialog dialog = new ColorDialog(ret.getShell());
 	RGB numero =  dialog.open();
    ret.rgb = numero;
   
}
}