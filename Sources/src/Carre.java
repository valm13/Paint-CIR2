import java.awt.Color;

public class Carre extends Rectangle{
	public Carre(int cote,Point p)
	{
		super(cote,cote,p);
	}
	
	public Carre(int px, int py,Color co)
	{
		super(px, py, co);
	}
	
	public void setLongueur(int cote)
	{
		setCote(cote);
	}
	
	public void setLargeur(int cote)
	{
		setCote(cote);
	}
	public void setCote(int cote)
	{
		longueur = cote;
		largeur = cote;
	}
}
