package Graphics;


import java.util.ArrayList;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import Graphics.AssociationsDesign.Linha;


public class DrawComposite extends Composite {

	public String string;
	public ArrayList<Text> textos = new ArrayList<>();
	public ArrayList<String> atributos_nomes = new  ArrayList<>();	
	public ArrayList<String> metodos = new ArrayList<>(); 
	public ArrayList<Linha> linhas_inicio = new  ArrayList<>();
	 public ArrayList<Linha> linhas_fim = new  ArrayList<>();
	  public int width =80;
		 public int height = 100;
	     public int redimensionamento ;
	     public int x = 0;
	     public int y = 0 ;
	  public Text text;
	 public DrawComposite(Composite parent, int style) {
		
		super(parent, style);
        
	
	}
  public void ajustartextos(){
	  
  }
}
