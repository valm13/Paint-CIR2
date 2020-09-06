import java.awt.Color;
import java.awt.Graphics;

public class Ellipse extends Figure{
	protected int petit,grand;

	public Ellipse(int newPetit, int newGrand,Point p)
	{
		super(p);
		petit = newPetit;
		grand = newGrand;
		this.remplissage = "Plein";
		
	}
	
	public Ellipse(int px, int py,Color co)
	{
		super(new Point(px,py),co);
		petit = 0;
		grand = 0;
		this.remplissage = "Plein";
	}
	
	public void setP(Point p) {
		this.orig = p;
	}
	
	public int getPetit() {
		return petit;
	}
	
	public void setPetit(int petit) {
		this.petit = petit;
	}
	
	public void setGrand(int grand) {
		this.grand = grand;
	}
	
	public int getGrand() {
		return grand;
	}
	
	public double getPerimetre() {
		return (double)(Math.PI * (grand+ petit));
	}
	
	public double getSurface() {
		return (double)(Math.PI * grand * petit / 4);
	}
	
	public void setBoudingBox(int hauteurBB, int largeurBB)
	{
		grand = hauteurBB;
		petit = largeurBB;
	}
	
	public void draw(Graphics g)
	{
		g.setColor(c);
		switch(this.remplissage) {
		case "Vide":
			g.drawOval(orig.getX(), orig.getY(), grand, petit);
			break;
		case "Plein":
			g.fillOval(orig.getX(), orig.getY(), grand, petit);
			break;
		default:
			g.fillOval(orig.getX(), orig.getY(), grand, petit);
			break;
		}
	}
}
