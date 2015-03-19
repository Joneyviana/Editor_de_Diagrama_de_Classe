package editor.editors;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.actions.SimpleWildcardTester;

public class Tela extends Composite{
	public  Image image;  
	public Composite pai ;
	public DrawWillBeSavedInUml inicio_associacao;
	private DrawWillBeSavedInUml fim_associacao;
	public LineComposite little_painel ;
	public Tela(Composite parent, int style) {
		super(parent, style);
		// TODO Auto-generated constructor stub
	}


	public void setFim_associacao(DrawWillBeSavedInUml fim_associacao) {
		this.fim_associacao = fim_associacao;
	    
	    if (fim_associacao !=null){
	    	inicio_associacao.linhas_inicio.get(inicio_associacao.linhas_inicio.size()-1).asso = MultiPageEditor.uml.addAssociation(inicio_associacao.o,fim_associacao.string) ;  
	    	fim_associacao.linhas_fim.add(inicio_associacao.linhas_inicio.get(inicio_associacao.linhas_inicio.size()-1));
	    }
	    }



}
