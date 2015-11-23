package Graphics;


import org.eclipse.swt.widgets.Composite;


public class Quadro_inicial extends Composite{
    public Tela filho ;
	public Quadro_inicial(Composite parent, int style) {
		super(parent, style);
		filho = new Tela(parent, style);
        filho.pai = this;
	}
}
