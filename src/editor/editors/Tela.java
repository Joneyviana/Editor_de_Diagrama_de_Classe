package editor.editors;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

public class Tela extends Composite{
	public  Image image;  
	
	public retangulo inicio_associacao;
	private retangulo fim_associacao;
	
	public Tela(Composite parent, int style) {
		super(parent, style);
		// TODO Auto-generated constructor stub
	}


	public void setFim_associacao(retangulo fim_associacao) {
		this.fim_associacao = fim_associacao;
	    System.out.println("name_class "+fim_associacao.string);
	    
	}



}
