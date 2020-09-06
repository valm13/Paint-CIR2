import java.awt.Color;
import java.awt.Graphics;

public class Rectangle extends Figure{
	protected int longueur,largeur;

	public Rectangle(int lon, int lar,Point p)
	{
		super(p);
		longueur = lon;
		largeur = lar;
		this.remplissage = "Plein";
		
	}
	
	public Rectangle(int px, int py,Color co)
	{
		super(new Point(px,py),co);
		longueur = 0;
		largeur = 0;
		this.remplissage = "Plein";
	}
	
	public void setP(Point p) {
		this.orig = p;
	}

	public void setLongueur(int longueur) {
		this.longueur = longueur;
	}

	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}
	public Point getP() {
		return orig;
	}
	public int getLongueur() {
		return longueur;
	}
	public int getLargeur() {
		return largeur;
	}
	public double getPerimetre() {
		return (double)(2*(longueur + largeur));
	}
	public double getSurface() {
		return (double)(longueur*largeur);
	}
	public String toString() {
		System.out.println(super.toString());
		String chaine = new String();
		if( largeur != 0 && longueur != 0)
		{
			for(int i = 0; i < largeur+1; i++)
			{
				if( i == 0 || i == largeur)
				{
					chaine +="+";
					for(int j = 0; j < longueur; j++)
					{
						chaine+="--";
					}
					chaine +="+\n";
				}
				if( i != largeur) {
					chaine +="|";
					for(int j = 0; j < longueur; j++)
					{
						chaine+="  ";
					}
					chaine +="|\n";
				}
				
			}
			return chaine;
		}
		else
		{
			return "Le rectangle doit avoir une longueur et une largeur différente de 0 !";
		}
		
	}
	
	public void setBoudingBox(int hauteurBB, int largeurBB)
	{
		longueur = hauteurBB;
		largeur = largeurBB;
	}
	
	public void draw(Graphics g)
	{
		g.setColor(c);
		switch(this.remplissage) {
		case "Vide":
			g.drawRect(orig.getX(), orig.getY(), largeur, longueur);
			break;
		case "Plein":
			g.fillRect(orig.getX(), orig.getY(), largeur, longueur);
			break;
		default:
			g.fillRect(orig.getX(), orig.getY(), largeur, longueur);
			break;
		}		
	}
}
