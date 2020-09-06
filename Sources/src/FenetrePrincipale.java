import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.FileInputStream;


public class FenetrePrincipale extends JFrame implements ActionListener{
	
	
	private static final long serialVersionUID = -5545949522749538721L; 
	
	private Dessin monDessin = new Dessin();
	
	private JFrame widgetForme = new JFrame();
	private JFrame widgetColor = new JFrame();
	private JFrame widgetOutil = new JFrame();
	private JFrame widgetRetour = new JFrame();

	  
	public FenetrePrincipale(String titre, int largeur, int hauteur) {
		// Base de la fenêtre
		super(titre);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(largeur, hauteur);
		
		// Création du Menu
		JMenuBar menuBar = buildMenuBar();
		
		setJMenuBar(menuBar);
		
		// Contenaire
		Container ctpan = this.getContentPane();
		ctpan.add(monDessin, "Center");
		
		// Panel principal
		JPanel panneauTools = new JPanel();
		panneauTools.setLayout(new GridLayout(2,1));
		
		// Panel Color && Actions
		JPanel palette = buildPalette();
		
		JPanel panneauAction = buildPanelAction();
		
		// Ajout des 2 Panel au panel sud
		panneauTools.add(palette);
		
		panneauTools.add(panneauAction);
		
		// Ajout du panel sud au container
		ctpan.add(panneauTools,"South");
		
		// Icone du logiciel
		setIconToJFrame(this);
		
		// On affiche la fenêtre
		setVisible(true);
		
	}
	
	private void changeAllForegroundColorButton(JButton[] tab) {
		for(JButton current : tab)
			current.setForeground(Color.GRAY);
	}
	
	private void addButtonsToJPanel(JPanel jp, JButton[] tab) {
		for(JButton current : tab)
			jp.add(current);
	}
	
	private void buildPanelColor(Container ct) {
		// Déclaration
		JPanel gauche = new JPanel();
		gauche.setLayout(new GridLayout(2,2));
		
		JButton noir = new JButton("Noir");
		JButton rouge = new JButton("Rouge");
		JButton vert = new JButton("Vert");
		JButton bleu = new JButton("Bleu");
		JButton jaune = new JButton("Jaune");
		JButton rose = new JButton("Rose");
		JButton magenta = new JButton("Magenta");
		JButton orange = new JButton("Orange");
		JButton cyan = new JButton("Cyan");
		JButton gris = new JButton("Gris");

		// Background
		noir.setBackground(Color.BLACK);
		rouge.setBackground(Color.RED);
		vert.setBackground(Color.GREEN);
		bleu.setBackground(Color.BLUE);
		jaune.setBackground(Color.YELLOW);
		rose.setBackground(Color.PINK);
		magenta.setBackground(Color.MAGENTA);
		orange.setBackground(Color.ORANGE);
		cyan.setBackground(Color.CYAN);
		gris.setBackground(Color.DARK_GRAY);
		
		// Actions
		noir.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e)
		    {
		    	monDessin.setC(Color.BLACK);
		    	widgetColor.dispose();
		    }});
		rouge.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e)
		    {
		    	monDessin.setC(Color.RED);
		    	widgetColor.dispose();
		    }});
		vert.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e)
		    {
		    	monDessin.setC(Color.GREEN);
		    	widgetColor.dispose();
		    }});
		bleu.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e)
		    {
		    	monDessin.setC(Color.BLUE);
		    	widgetColor.dispose();
		    }});
		jaune.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e)
		    {
		    	monDessin.setC(Color.YELLOW);
		    	widgetColor.dispose();
		    }});
		rose.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e)
		    {
		    	monDessin.setC(Color.PINK);
		    	widgetColor.dispose();
		    }});
		magenta.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e)
		    {
		    	monDessin.setC(Color.MAGENTA);
		    	widgetColor.dispose();
		    }});
		orange.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e)
		    {
		    	monDessin.setC(Color.ORANGE);
		    	widgetColor.dispose();
		    }});
		cyan.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e)
		    {
		    	monDessin.setC(Color.CYAN);
		    	widgetColor.dispose();
		    }});
		gris.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e)
		    {
		    	monDessin.setC(Color.DARK_GRAY);
		    	widgetColor.dispose();
		    }});

		// On change la couleur du texte du bouton
		JButton[] tab = new JButton[] {noir,rouge,vert,bleu,jaune,rose,magenta,orange,cyan,gris};
		changeAllForegroundColorButton(tab);

		// On ajoute les boutons au JPanel
		addButtonsToJPanel(gauche,tab);
		
		
		
		JPanel droite = new JPanel();
		droite.setLayout(new GridLayout(1,2));
		
		JButton personalise = new JButton("Personnalisée");
		
		
	
		personalise.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e)
		    {
		    	monDessin.setC(JColorChooser.showDialog(
		    			widgetColor,
		    		      "Personalisé",
		    		      Color.BLACK));
		    	widgetColor.dispose();
		    		
		    }});
		droite.add(personalise);
		
		ct.add(gauche, "West");
		ct.add(droite, "Center");
	}
	
	private JPanel buildPanelRetour() {
		// Panel Forme
		JPanel result = new JPanel();
		result.setLayout(new GridLayout(2,2));
		
		JButton x1 = new JButton("x1");
		JButton x5 = new JButton("x5");
		JButton x10 = new JButton("x10");
		JButton x20 = new JButton("x20");
		JButton x50 = new JButton("x50");
		JButton x100 = new JButton("x100");
		JButton[] tab = new JButton[] {x1,x5,x10,x20,x50,x100};
		
		for(JButton current : tab) {
			current.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e)
			    {
			    	monDessin.setNbRetour(current.getText());
			    	monDessin.eraseLastFigure();
			    	
			    }});
		}

		addButtonsToJPanel(result,tab);
		return result;
	}

	private void buildPanelForme(Container ct) {
		// Gauche
		JPanel gauche = new JPanel();
		gauche.setLayout(new GridLayout(2,2));
		
		JButton cercle = new JButton("Cercle");
		JButton ellipse = new JButton("Ellipse");
		JButton carre = new JButton("Carre");
		JButton rectangle = new JButton("Rectangle");
		JButton[] tab = new JButton[] {ellipse,rectangle,cercle,carre};
		
		for(JButton current : tab) {
			current.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e)
			    {
			    	monDessin.setNomFigure(current.getText());
			    	monDessin.setForme();
			    	widgetForme.dispose();
			    }});
		}

		addButtonsToJPanel(gauche,tab);
		// Droite
		JPanel droite = new JPanel();
		droite.setLayout(new GridLayout(1,2));
		
		JButton vide = new JButton("Vide");
		JButton plein = new JButton("Remplis");
		JButton[] tab1 = new JButton[] {vide,plein};
		
		for(JButton current : tab1) {
			current.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e)
			    {
			    	monDessin.definiTypeForme(current.getText());
			    }});
		}

		addButtonsToJPanel(droite,tab1);
		
		ct.add(gauche, "West");
		ct.add(droite, "Center");
		
	}
	private void buildPanelOutils(Container ct) {
		
		// NORD
		JPanel nord = new JPanel();
		nord.setLayout(new GridLayout(1,2));
		
		JButton crayon = new JButton("Crayon");
		JButton gomme = new JButton("Gomme");
		
		  try {
			    Image img = ImageIO.read(new FileInputStream("icon/crayon.png"));
			    crayon.setIcon(new ImageIcon(img));
			  } catch (Exception ex) {
			    System.out.println(ex);
			  }
		  try {
			    Image img = ImageIO.read(new FileInputStream("icon/gomme.png"));
			    gomme.setIcon(new ImageIcon(img));
			  } catch (Exception ex) {
			    System.out.println(ex);
			  }
		  
		JButton[] tab = new JButton[] {crayon,gomme};
		
		
		crayon.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e)
		    {
		    	monDessin.setCrayon();
		    }});
		gomme.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e)
		    {
		    	monDessin.setGomme();
		    }});
		
		addButtonsToJPanel(nord,tab);
		
		// SUD
			 	JPanel size = new JPanel();
			 	size.setLayout(new GridLayout(1,2));
			 	
			 	JLabel cr = new JLabel();
			 	JLabel go = new JLabel();
			 	cr.setText(""+monDessin.getTailleCrayon());
			 	cr.setHorizontalAlignment(JLabel.CENTER);
			 	go.setHorizontalAlignment(JLabel.CENTER);
			 	go.setText(""+monDessin.getTailleGomme());
			 	size.add(cr);
			 	size.add(go);
			 	
		// CENTER
		JPanel sliders = new JPanel();
		sliders.setLayout(new GridLayout(1,2));
		
		JSlider slideCrayon = new JSlider();
	    slideCrayon.setMaximum(100);
	    slideCrayon.setMinimum(0);
	    slideCrayon.setValue(monDessin.getTailleCrayon());
	    slideCrayon.addChangeListener(new ChangeListener(){
	        public void stateChanged(ChangeEvent event){
	        	monDessin.modifieTailleCrayon((JSlider)event.getSource());
	        	monDessin.setCrayon();
	        	cr.setText(""+monDessin.getTailleCrayon());
	          }
	        
	        });
	    JSlider slideGomme = new JSlider();
		   
	    slideGomme.setMaximum(100);
	    slideGomme.setMinimum(0);
	    slideGomme.setValue(monDessin.getTailleGomme());
	    slideGomme.addChangeListener(new ChangeListener(){
	        public void stateChanged(ChangeEvent event){
	        	monDessin.modifieTailleGomme((JSlider)event.getSource());
	        	monDessin.setGomme();
	        	go.setText(""+monDessin.getTailleGomme());
	          }
	        
	        });
	    slideGomme.setMajorTickSpacing(10);
	    slideGomme.setPaintTicks(true);
	    slideGomme.setPaintLabels(true);

	    
	    slideCrayon.setMajorTickSpacing(10);
	    slideCrayon.setPaintTicks(true);
	    slideCrayon.setPaintLabels(true);
	    
	    sliders.add(slideCrayon);
	    sliders.add(slideGomme);

	    
	 	ct.add(size,"South");
		ct.add(sliders, "Center");
		ct.add(nord, "North");
	}
	
	private JPanel buildPanelAction() {
		// Panel Forme
		JPanel result = new JPanel();
		result.setLayout(new GridLayout(1,1));
		
		JButton retour = new JButton("Retour");
		JButton forme = new JButton("Forme");
		JButton outil = new JButton("Outils");

		retour.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e)
		    {
		    	if(widgetRetour.isVisible() == false) {
			    	widgetRetour = new JFrame();
			    	JPanel p = new JPanel();
			    	p = buildPanelRetour();
			    	widgetRetour.add(p);
			    	widgetRetour.setSize(500,200);
			    	widgetRetour.setLocation(0,0);
			    	setIconToJFrame(widgetRetour);
			    	widgetRetour.setVisible(true);
		    	}
		    }});
		forme.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e)
		    {
		    	if(widgetForme.isVisible() == false) {
		    		widgetForme = new JFrame();
			    	Container p = widgetForme.getContentPane();
					buildPanelForme(p);
					widgetForme.setSize(500,200);
					widgetForme.setLocation(0,0);
					setIconToJFrame(widgetForme);
					widgetForme.setVisible(true);
		    	}
		    	
				
		    }});
		
		outil.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e)
		    {
		    	if(widgetOutil.isVisible() == false) {
		    		widgetOutil = new JFrame();
			    	Container p = widgetOutil.getContentPane();
			    	buildPanelOutils(p);
			    	widgetOutil.setSize(500,200);
			    	widgetOutil.setLocation(0,0);
			    	setIconToJFrame(widgetOutil);
			    	widgetOutil.setVisible(true);
		    	}
		    }});
		

		
		JButton[] tab = new JButton[] {forme,outil,retour};
		addButtonsToJPanel(result,tab);
		
		return result;
				
	}

	private JMenuBar buildMenuBar() {
		JMenuBar result = new JMenuBar();

		JMenu m_fichier = new JMenu("Fichier");
		JMenu m_info = new JMenu("A propos");
		  // Items de Fichier
		JMenuItem mi_ouvrir = new JMenuItem("Ouvrir");
		mi_ouvrir.addActionListener(this);
		
		JMenuItem mi_nouveau = new JMenuItem("Nouveau");
		mi_nouveau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				monDessin.nouveauDessin();
			}
		});
		
		JMenuItem mi_enregistrer = new JMenuItem("Enregistrer");
		mi_enregistrer.addActionListener(this);
		
		JMenuItem mi_quitter = new JMenuItem("Quitter");
		mi_quitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		  // Items de A propos
		JMenuItem mi_auteurs = new JMenuItem("Auteurs");
		mi_auteurs.addActionListener(this);
		
		m_fichier.add(mi_ouvrir);
		m_fichier.addSeparator();
		m_fichier.add(mi_nouveau);
		m_fichier.addSeparator();
		m_fichier.add(mi_enregistrer);
		m_fichier.addSeparator();
		m_fichier.add(mi_quitter);
		
		m_info.add(mi_auteurs);
		
		result.add(m_fichier);
		result.add(m_info);
		
		return result;
	}
	
	private JPanel buildPalette() {
		JPanel result = new JPanel();
		result.setLayout(new GridLayout(1,1));
		
		JButton button = new JButton("Couleurs");
		
		button.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e)
		    {
		    	if(widgetColor.isVisible() == false) {
		    		widgetColor = new JFrame();
			    	Container p = widgetColor.getContentPane();
					buildPanelColor(p);
					widgetColor.setSize(550,100);
					widgetColor.setLocation(0,0);
					setIconToJFrame(widgetColor);
					widgetColor.setVisible(true);
		    	}
		    	
		    }});
		
		
		JButton[] tab = new JButton[] {button};
		addButtonsToJPanel(result,tab);
		
		return result;
	}
	
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		System.out.println("Clic sur : "+cmd);
		// Premier panneau
		
		
		if(cmd.equals("Auteurs")) {
			JOptionPane.showMessageDialog(this, 
			         "Crée par Valentin Magnan\nRéalisé dans le cadre du TP3 Java",
			         " Auteurs ",
			         JOptionPane.INFORMATION_MESSAGE);
		}
		
		else if(cmd.equals("Enregistrer")) {
			String nomFichier = JOptionPane.showInputDialog(this, "Nom du fichier", "Enregistrer", JOptionPane.OK_CANCEL_OPTION);
			if(nomFichier != null) {
				if(nomFichier.length() >= 1 ) {
					System.out.println(nomFichier+ " Enregistré !");
					BufferedImage bi = new BufferedImage(this.getSize().width, this.getSize().height, BufferedImage.TYPE_INT_ARGB); 
					Graphics g = bi.createGraphics();
					monDessin.paint(g);  //this == JComponent
					g.dispose();
					try{ImageIO.write(bi,"png",new File("draw/"+nomFichier+".png"));}catch (Exception e1) {}
				}
			}
		}
		
		else if(cmd.equals("Ouvrir")) {
			JFileChooser fc = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter(".png", "png", "img");
			fc.setFileFilter(filter);
			int returnVal = fc.showOpenDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				
	            File file = fc.getSelectedFile();
	            //This is where a real application would open the file.
	            System.out.println("Opening: " + file.getName() + ".");
	            monDessin.openImage(file);
	            
	        } else {
	        	System.out.println("Open command cancelled by user.");
	        }
		}	

	}	
	
	public void setIconToJFrame(JFrame j) {
		// Icone du logiciel
				try {
				    Image img = ImageIO.read(new FileInputStream("icon/logo.png"));
				    j.setIconImage(img);
				  } catch (Exception ex) {
				    System.out.println(ex);
				  }
	}
}
