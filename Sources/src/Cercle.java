import java.awt.Color;

public class Cercle extends Ellipse{
	
	public Cercle(int rayon,Point p) {
		super(rayon,rayon,p);
	}
	
	public Cercle(int px, int py,Color co)
	{
		super(px, py, co);
	}
	
	public void setRayon(int ray) {
		this.petit = ray;
		this.grand = ray;
	}
	
	public void setPetit(int ray) {
		setRayon(ray);
	}
	
	public void setGrand(int ray) {
		setRayon(ray);
	}
	
	public double getSurface() {
		return (double)(Math.PI*petit*petit);
	}
	
	public double getPerimetre() {
		return (double)(2*Math.PI*petit);
	}
}
