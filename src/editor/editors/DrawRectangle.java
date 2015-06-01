package editor.editors;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.GCData;
import org.eclipse.swt.graphics.LineAttributes;

public class DrawRectangle {

public 	DrawRectangle(PaintEvent evt,AreaDraw area, retangulo ret, String str){
	int scale = area.scale_reducao;
	

	evt.gc.setLineAttributes(new LineAttributes(4/scale));
	
    if((ret==null)||(ret.backgroundcolor==null)){
    	 evt.gc.setBackground(evt.display.getSystemColor(SWT.COLOR_YELLOW));
    }
    else {
    	evt.gc.setBackground(ret.backgroundcolor);
    }
    evt.gc.setForeground(evt.display.getSystemColor(SWT.COLOR_BLACK));
    evt.gc.drawRectangle(area.x/scale, area.y/scale, (area.width-1)/scale, ((area.height-1)+area.redimensionamento)/scale);
    evt.gc.fillRectangle((area.x+2)/scale, (area.y+2)/scale, (area.width-4)/scale, ((area.height-4)+area.redimensionamento)/scale);
    
    evt.gc.drawLine(area.x/scale, area.y/scale + 25/scale,(area.width/scale)+(area.x/scale), area.y/scale + 25/scale);
    evt.gc.drawLine(area.x/scale, (int)((((area.height *0.65)+area.redimensionamento))/scale)+area.y/scale,(area.width/scale)+(area.x/scale), (int)((((area.height *0.65)+area.redimensionamento))/scale)+area.y/scale);
    FontData fo = new FontData("helvetica", 11/scale, SWT.BOLD);
	
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
    System.out.print("misericordia Deus me ajuda pelo amor de Deus");
    evt.gc.drawText(str , (int) area.x/scale+(area.width/8)/scale, area.y/scale+ 3/scale);

}
}