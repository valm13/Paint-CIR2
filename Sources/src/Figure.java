import java.awt.Color;
import java.awt.Graphics;

public abstract class Figure {
	protected Point orig;
	protected Color c;
	protected String remplissage;
	
	public Figure(Point p){
		orig = p;
	}
	
	public Figure(Point p, Color co){
		orig = p;
		c = co;
	}
	
	public Figure(int x, int y){
		orig = new Point(x,y);
	}
	
	public Figure(){
		this(0,0);
	}

	@Override
	public String toString() {
		return "Point d'origine (X;Y) : (" + orig.getX() + ";"+ orig.getY()+")";
	}
	
	public void definiRemplissage(String s) {
		if(s == "Plein" || s == "Vide")
			remplissage = s;
		else
			remplissage = "Plein";
	}
	
	public abstract void setBoudingBox(int hauteurBB, int largeurBB);
	public void setColor(Color c) {
		this.c = c;
	}
	public abstract void draw(Graphics g);
	public abstract double getPerimetre();
	public abstract double getSurface();
}
