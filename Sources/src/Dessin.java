import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;


public class Dessin extends JPanel implements MouseListener,MouseMotionListener{
	
	private static final long serialVersionUID = -1748210361401957132L;
	private ArrayList<Figure> liste;
	private Color c;
	private String nomFigure;
	private int xMouse,yMouse;
	private Figure actualFig;
	
	private Outil actualInstru;
	private Outil gomme = new Outil("Gomme");
	private Outil crayon = new Outil("Crayon");
	
	public String mode;
	private BufferedImage image;
	private int nbRetour;
	private String typeForme;
	
	public Dessin() {
		super();
		c = Color.BLACK;
		nomFigure = "Rectangle";
		liste = new ArrayList<Figure>();
		nbRetour = 10;
		mode = "Trace";
		typeForme = "Remplis";
		setCrayon();
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	
	public void setC(Color c) {
		this.c = c;
	}
	
	public void setNomFigure(String nomFigure) {
		this.nomFigure = nomFigure;
	}
	
	public ArrayList<Figure> getListe() {
		return liste;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(Color.WHITE);
		g.drawImage(image, 0, 0, null);
		for(Figure f : liste)
		{
			f.draw(g);
		}
		if(actualFig != null)
			actualFig.draw(g);
		
	}

	@Override
	public String toString() {
		return "Dessin [liste=" + liste + ", c=" + c + ", nomFigure=" + nomFigure + "]";
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {

		int xCurrent = arg0.getX();
		int yCurrent = arg0.getY();
		int deplacementX = Math.abs(xMouse- xCurrent);
		int deplacementY = Math.abs(yMouse- yCurrent);
		
//		System.out.println("Déplacement X = "+deplacementX+"\nDéplacement Y = "+deplacementY);
		
		if(mode == "Forme")
			actualFig = createFigure(xCurrent, yCurrent, deplacementX, deplacementY);
		
		else if(mode == "Instrument d'ecriture")
		{
			switch(actualInstru.getNom()) 
			{
			case "Crayon":
				actualFig = traceur(new Point(xCurrent,yCurrent));
				break;
			case "Gomme":
				actualFig = gomme(new Point(xCurrent,yCurrent));
				break;
			}
		}
			
			
		repaint();
	}
	
	private Figure gomme(Point p) {
		Cercle cer = new Cercle(p.getX(),p.getY(),Color.WHITE);
		cer.setRayon(gomme.getTaille());
		liste.add(cer);
		return cer;
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("J'ai appuyé");
		xMouse = arg0.getX();
		yMouse = arg0.getY();
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("J'ai relâché");
		int xCurrent = arg0.getX();
		int yCurrent = arg0.getY();
		int deplacementX = Math.abs(xMouse- xCurrent);
		int deplacementY = Math.abs(yMouse- yCurrent);
		System.out.println("Déplacement X = "+deplacementX+"\nDéplacement Y = "+deplacementY);
		if(mode == "Forme") {
		Figure f = createFigure(xCurrent, yCurrent, deplacementX, deplacementY);
		addFigure(f);
		}
		repaint();
		
	}
	
	public Figure createFigure(int xCurrent, int yCurrent, int deplacementX, int deplacementY) {
		Figure f;
		switch(nomFigure) {
		
			case "Rectangle":
				Rectangle rect;
				if(xCurrent > xMouse) {
					if(yCurrent > yMouse)
						rect = new Rectangle(xMouse,yMouse,c);
					else
						rect = new Rectangle(xMouse,yMouse-deplacementY,c);
				}
				else {
					if(yCurrent > yMouse)
						rect = new Rectangle(xCurrent,yCurrent-deplacementY,c);
					else
						rect = new Rectangle(xCurrent,yCurrent,c);					
				}
				rect.setLongueur(deplacementY);
				rect.setLargeur(deplacementX);
				f = rect;
			break;
			
			case "Ellipse":
				Ellipse ell;
				if(xCurrent > xMouse) {
					if(yCurrent > yMouse)
						ell = new Ellipse(xMouse,yMouse,c);
					else
						ell = new Ellipse(xMouse,yMouse-deplacementY,c);
				}
				else {
					if(yCurrent > yMouse)
						ell = new Ellipse(xCurrent,yCurrent-deplacementY,c);
					else
						ell = new Ellipse(xCurrent,yCurrent,c);
				}
				ell.setGrand(deplacementX);
				ell.setPetit(deplacementY);
				f = ell;
			break;
			
			case "Carre":
				Carre car;
				if(xCurrent > xMouse) {
					if(yCurrent > yMouse) {
						System.out.println("Bas droite");
						car = new Carre(xMouse,yMouse,c);
					}
						
					else {
						System.out.println("Haut droite");
						car = new Carre(xMouse,yMouse,c);
						car.setP(new Point(xMouse,yMouse - (int)(deplacementX+deplacementY)/2));
					}
						
				}
				else {
					if(yCurrent > yMouse) {
						System.out.println("Bas gauche");
						car = new Carre(xMouse,yMouse,c);
						car.setP(new Point(xMouse-(int)(deplacementX+deplacementY)/2,yMouse));
						
					}
						
					else {
						System.out.println("Haut gauche");
						car = new Carre(xMouse,yMouse,c);	
						car.setP(new Point(xMouse-(int)(deplacementX+deplacementY)/2,yMouse - (int)(deplacementX+deplacementY)/2));
					}
						
				}
				car.setCote((int)(deplacementX+deplacementY)/2);
				f = car;
			break;
			
			case "Cercle":
				Cercle cer;
				if(xCurrent > xMouse) {
					if(yCurrent > yMouse)
						cer = new Cercle(xMouse,yMouse,c);
					else {
						cer = new Cercle(xMouse,yMouse,c);
						cer.setP(new Point(xMouse,yMouse - (int)(deplacementX+deplacementY)/2));
					}
						
				}
				else {
					if(yCurrent > yMouse) {
						cer = new Cercle(xCurrent,yMouse,c);
						cer.setP(new Point(xMouse-(int)(deplacementX+deplacementY)/2,yMouse));
					}
						
					else {
						cer = new Cercle(xCurrent,yMouse,c);	
						cer.setP(new Point(xMouse-(int)(deplacementX+deplacementY)/2,yMouse - (int)(deplacementX+deplacementY)/2));
					}
						
				}
				cer.setRayon((int)(deplacementX+deplacementY)/2);
				f = cer;
			break;
			
			default:
				Rectangle rect1;
				if(xCurrent > xMouse) {
					if(yCurrent > yMouse)
						rect1 = new Rectangle(xMouse,yMouse,c);
					else
						rect1 = new Rectangle(xMouse,yMouse-deplacementY,c);
				}
				else {
					if(yCurrent > yMouse)
						rect1 = new Rectangle(xCurrent,yCurrent-deplacementY,c);
					else
						rect1 = new Rectangle(xCurrent,yCurrent,c);					
				}
				rect1.setLongueur(deplacementY);
				rect1.setLargeur(deplacementX);
				f = rect1;
			break;
		
		}
		f.definiRemplissage(typeForme);
		return f;
	}
	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("Cliqué en X = "+arg0.getX()+" Y = "+arg0.getY());
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void addFigure(Figure f) {
		// TODO Auto-generated method stub
		liste.add(f);
	}
	
	public void nouveauDessin() {
		liste.clear();
		actualFig = null;
		repaint();
	}
	public void setNbRetour(String s) {
		switch(s) {
		case "x1":
			nbRetour = 1;
			break;
		case "x5":
			nbRetour = 5;
			break;
		case "x10":
			nbRetour = 10;
			break;
		case "x20":
			nbRetour = 20;
			break;
		case "x50":
			nbRetour = 50;
			break;
		case "x100":
			nbRetour = 100;
			break;
		}
	}
	
	public void eraseLastFigure() {
		
		if(liste.size() >= nbRetour) {
			for(int i = 0; i < nbRetour; i++) {
				liste.remove(liste.size()-1);
			}
		}
		else
			liste.clear();
		actualFig = null;
		if(liste.size() > 1)
		System.out.println("Il reste encore "+liste.size()+" Figures");
		else if(liste.size() == 1)
			System.out.println("Il reste encore "+liste.size()+" Figure");
		else
			System.out.println("Il n'y a plus de figures");
		repaint();
	}
	public Figure traceur(Point p) {
		
		Cercle trace = new Cercle(crayon.getTaille(),p);
		trace.setColor(c);
		liste.add(trace);
		return trace;
	}
	public void setForme() {
		
		mode = "Forme" ;
	}
	public void setCrayon() {
	
		setInstrumentEcriture();
		actualInstru = crayon;
		
	}
	public void setGomme() {
		
		setInstrumentEcriture();
		actualInstru = gomme;
	}
	public void setInstrumentEcriture() {
		
		mode = "Instrument d'ecriture" ;
		
	}

	public void modifieTailleCrayon(JSlider slider) {
		crayon.setTaille(slider.getValue());
	}
	public void modifieTailleGomme(JSlider slider) {
		gomme.setTaille(slider.getValue());
	}
	
	public int getTailleCrayon() {
		return crayon.getTaille();
	}
	public int getTailleGomme() {
		return gomme.getTaille();
	}
	
	public void definiTypeForme(String s) {
		if(s == "Plein" || s == "Vide")
			typeForme = s;
		else
			typeForme = "Plein";
	}
	
	public void openImage(File f)
	{
		try{image = ImageIO.read(f);}catch (Exception e1) {}
		repaint();
		
	}
}
