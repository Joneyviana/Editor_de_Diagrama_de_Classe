package Graphics;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionListener;

import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
public class Menus {



public ArrayList<MenuItem> items = new ArrayList<>();

public void addselelectionlistenerMultiplo(int []posicoes,SelectionListener selection){
	for(int posicao :posicoes){
		items.get(posicao).addSelectionListener(selection);
	}
}

public void addMenuitems(String [] texts, Menu menu){
	 for (String text:texts){
		 MenuItem item = new MenuItem(menu, SWT.CASCADE);
	     item.setText(text);
	     items.add(item);
	 }
}
}
