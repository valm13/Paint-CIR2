
public class Outil {

	protected int taille;
	protected String nom;
	
	public Outil() {
		taille = 10;
	}
	public Outil(String s) {
		this();
		nom = s;
	}
	public void setTaille(int nvTaille) {
		taille = nvTaille;
	}
	public void diminueTaille() {
		if(taille > 1) taille--;		
	}
	public void augmenteTaille() {
		if(taille < 100) taille++;		
	}
	public int getTaille() {
		return taille;	
	}
	public String getNom() {
		return nom;	
	}
	public void setNom(String s) {
		nom = s;	
	}

}
