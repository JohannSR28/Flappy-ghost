package application;

import javafx.scene.image.Image;

public class Obstacle {
	
	public int type = 0;
	public double taille;
	public double x = 640;
	public double y;
	
	public double angleDeg = 0;   //paramètres qui permettent de déplacer les objets de type 2 sinusoidales
    public double tierEcran;
    
    
   public double timeMouvement = 0;
   public double abscisseObsQantique = 0; //paramètres qui permettent de déplacer les objets de type 3 quantiques
   public double ordonneObsQantique = 0; 
   
   public Image image;
   public boolean obstacleDepasse = false; //boolean qui indique si l'objet à déja été dépassé
	
Obstacle(int type, double taille, double y) {
		
		this.type = type;
		this.taille = taille;
		this.y = y;

		
	}
	

}
