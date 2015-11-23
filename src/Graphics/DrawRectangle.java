package Graphics;



import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.GCData;
import org.eclipse.swt.graphics.LineAttributes;



public class DrawRectangle {

public 	DrawRectangle(PaintEvent evt, retangulo ret, String str){
	
	

	evt.gc.setLineAttributes(new LineAttributes(4));
	
    if((ret==null)||(ret.backgroundcolor==null)){
    	 evt.gc.setBackground(evt.display.getSystemColor(SWT.COLOR_YELLOW));
    }
    else {
    	evt.gc.setBackground(ret.backgroundcolor);
    }
    evt.gc.setForeground(evt.display.getSystemColor(SWT.COLOR_BLACK));
    evt.gc.drawRectangle(0 ,  0 , (ret.width-1), ((ret.height-1)+ret.redimensionamento_attributos + ret.redimensionamento_metodos));
    evt.gc.fillRectangle(2, 2, (ret.width-4), ((ret.height-4)+ret.redimensionamento_attributos + ret.redimensionamento_metodos));
    
    evt.gc.drawLine(0 , 25, ret.width, 25);
    evt.gc.drawLine(0, (int)(((ret.height *0.65)+ret.redimensionamento_attributos)),ret.width, (int)(ret.height *0.65)+ret.redimensionamento_attributos);
    FontData fo = new FontData("helvetica", 11, SWT.BOLD);
	
    evt.gc.setFont(new Font(new Device() {
		
		@Override
		public long internal_new_GC(GCData arg0) {
			// TODO Auto-generated method stub
			return 0;
		}
		
		@Override
		public void internal_dispose_GC(long arg0, GCData arg1) {
			// TODO Auto-generated method stub
			
		}
	},fo ));
    
    evt.gc.drawText(str , (int) +(ret.width/8), 3);
    
}
}