/*

Auteur :
Johann Sourou ; Matricule : 20227958
Date de la dernière modification : 10-08-2003
Nom du programme : Flappy ghost
le Programme ci-dessus est celui du jeu Flappy Ghost utilisant la librairie JavaFX.
Le but du jeux est d'atteindre le score le plus élevé en évitant les obstacles sur le chemin.

*/
package application;

import java.time.Duration;

import java.util.LinkedList;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.shape.Circle;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import java.lang.Math;
import java.math.BigDecimal;
import javafx.geometry.*;


public class Main extends Application {
	
	@Override
    public void start(Stage primaryStage) {
        int w = 640, h = 440;

        // Création du premier Canvas pour l'animation du fond
        Pane root = new Pane();
        Scene scene = new Scene(root, w, h);
        Canvas canvasBackground = new Canvas(w, h);
        root.getChildren().add(canvasBackground);
        GraphicsContext contextBackground = canvasBackground.getGraphicsContext2D();
        Image image = new Image("c:/Users/souro/Downloads/fichiersFH/bg.png");
        contextBackground.drawImage(image, 350, 140);

        // Création du deuxième Canvas pour l'animation du personnage
        Canvas canvasCharacter = new Canvas(w, h);
        root.getChildren().add(canvasCharacter);
        GraphicsContext contextCharacter = canvasCharacter.getGraphicsContext2D();
        contextCharacter.setFill(Color.BLACK);
        
 
        HBox buttonBox = new HBox(10); // Espacement de 10 pixels entre les éléments
        buttonBox.setLayoutY(400);     
        buttonBox.setLayoutX(180);
        buttonBox.setPrefHeight(40);
        buttonBox.setAlignment(Pos.CENTER); // Alignement des éléments en bas et au centre
        

        Button bouttonPause = new Button("Pause");
        CheckBox checkBoxDebug = new CheckBox("Mode debug ");
        Text afficherScore = new Text(" Score : 0 ");
        
        bouttonPause.setFocusTraversable(false);
        checkBoxDebug.setFocusTraversable(false);

        // Création des VBox pour chaque élément
        VBox pauseBox = new VBox(bouttonPause);
        pauseBox.setAlignment(Pos.CENTER);

        VBox checkBoxBox = new VBox(checkBoxDebug);
        checkBoxBox.setAlignment(Pos.CENTER);

        VBox textBox = new VBox(afficherScore);
        textBox.setAlignment(Pos.CENTER);
        
        Separator verticalSeparator1 = new Separator();
        verticalSeparator1.setOrientation(Orientation.VERTICAL);

        Separator verticalSeparator2 = new Separator();
        verticalSeparator2.setOrientation(Orientation.VERTICAL);

        // Ajout des VBox au HBox
        buttonBox.getChildren().addAll(pauseBox,verticalSeparator1,checkBoxBox,verticalSeparator2,textBox);
        root.getChildren().add(buttonBox);

       //Les enregistreurs stockent des valeurs précises dans chaque Animation timer a chaque instant
       //Ces informations peuvent être utilisées  pour réalisés des évènements dans deux Animations distinctes.
        
        Enregistreur xGhost = new Enregistreur();
        Enregistreur yGhost = new Enregistreur();
        Enregistreur pauseEnregistreur = new Enregistreur();
        Enregistreur debugEnregistreur = new Enregistreur();

              
        
        AnimationTimer backgroundTimer = new AnimationTimer() {
        	
            //(Code pour l'animation du fond ici)
    		public double x = 0, y = 0;
    		double vitesseObs = 1.2;
    		int scoreActuel = 0;
    		
    		public LinkedList<Obstacle> toutObstacle = new LinkedList<Obstacle>() ;
    		boolean debug = false;
    		boolean pause = false;
    	   
           
            private long updateTimeObsacle = System.nanoTime(); //change toutes les 3 de secondes
            private long updateTime = System.nanoTime(); // change toutes les 1/100 secondes 
            private long lastUpdateTime4 = System.nanoTime();  //change toutes les 6 de secondes(chaque 2 obtacles)
               	    
            
    		@Override
    		 public void handle(long now) {
    			
    			  double lastXGhost = xGhost.dernierePosition();
    			  double lastYGhost = yGhost.dernierePosition();
    			  double rayonGhost = 30;

    			
    			long elapsedTimeObsacle = now - updateTimeObsacle;
    			long elapsedTime = now - updateTime;
    			long elapsedTime4 = now - lastUpdateTime4;

                double elapsedSecondsObstacle = elapsedTimeObsacle * 1e-9;
                double elapsedSeconds = elapsedTime * 1e-9;
                double elapsedSeconds4 = elapsedTime4 * 1e-9;
  
                
                

              if(elapsedSeconds >= 1.0/100 && !pause) {
             	  x -= 2;                                         // Incrémentation de x toutes les (1/100) seconde                                         
                  if(x <= -image.getWidth()) {                    //permet de faire défiler l'image
               	   x = 0;
                  }
            	  
                    contextBackground.clearRect(0, 0, w, h);
         	        contextBackground.drawImage(image, x , y);
         	        contextBackground.drawImage(image, x + image.getWidth(), y);
         	        
         	       bouttonPause.setOnAction(event -> {       
         	    	    pause =! pause;	    	    
         	    	});        
         	       
            	  
            	  
                for(Obstacle obstacle : toutObstacle) {
    			obstacle.x -= vitesseObs;
    			obstacle.angleDeg += 5;
    			obstacle.timeMouvement++;
    			      
    			    if(obstacle.type == 1) {
    			        		//rien
    			      }
    			    else if(obstacle.type == 2) {
	    
     			       	double variation =  Math.sin(Math.toRadians(obstacle.angleDeg));  
    			       	double ordonneSin = 640*(obstacle.tierEcran)*0.2 + (100*((variation - 1)/2));	//déplace les objet de type 2
    			   		obstacle.y = ordonneSin;
    			        		
    			       }
    			    else if(obstacle.type == 3){   			
    			        if(obstacle.timeMouvement == 20) {
    			        	obstacle.abscisseObsQantique = (Math.random()- 0.5)*60;
    			   			obstacle.ordonneObsQantique = (Math.random()- 0.5)*60;  //déplace les objets de type 3
    		     			obstacle.x -= obstacle.abscisseObsQantique;
      		       			obstacle.y -= obstacle.ordonneObsQantique;
    			       		obstacle.timeMouvement = 0;
    			        			}	
    			        		} 			        		
    	                                    	}        	
    			       
    			        	updateTime = now;  
    			        }
    			        
    			        if(elapsedSeconds4 >= 6.0) {
    			        	vitesseObs += 0.15;
    			        	lastUpdateTime4 = now;  
    			        }           
  
            if (elapsedSecondsObstacle>= 3 && !pause) {          //Crée un nouvel obstacle toutes les 3 secondes 
        Obstacle obs = new Obstacle(((int) (Math.random()*10))%3 + 1 , 20 + Math.random()*70, Math.random()*(image.getHeight()-50) );
               
        obs.tierEcran =((int) (Math.random()*10))%3 + 1;    //spécifie sur quel portion de l'ecran le mouvement sinusoidale se deroule                                                             // depend du modulo
        obs.image = new Image("c:/Users/souro/Downloads/fichiersFH/obstacles/" + (int) (Math.random()*27) + ".png");
        toutObstacle.add(obs);
        updateTimeObsacle = now; 
      
         
            }
            
            
     for(Obstacle obstacle : toutObstacle ) {
    	  	 
    	 double distance = Math.sqrt( Math.pow((lastXGhost - obstacle.x),2) + Math.pow((lastYGhost - obstacle.y),2) ) ;
 		 double sommeDesRayons = (obstacle.taille)/2 + rayonGhost;
 		 
 		if(lastXGhost > obstacle.x && !obstacle.obstacleDepasse) {
			 scoreActuel++;
			 obstacle.obstacleDepasse = true;
		 }
 		 	 
 		checkBoxDebug.setOnAction(event -> {
 		    if (checkBoxDebug.isSelected()) {
 		        debug = true;
 		    } else {
 		    	 debug = false;
 		        
 		    }
 		});
    				
    	 if((distance < sommeDesRayons) && debug) {			   
        contextBackground.setFill(Color.RED); // Change la couleur de la boule en rouge (vous pouvez utiliser n'importe quelle couleur)
       	} else if ((distance > sommeDesRayons) && debug){
   	 	contextBackground.setFill(Color.YELLOW); // Rétablit la couleur jaune par défaut
    		} else if ((distance < sommeDesRayons) && (!debug)) {
    			
       			 x = 0;
    			 y = 0;
        		 vitesseObs = 1.2;
        		 scoreActuel = 0;
        	     toutObstacle = new LinkedList<Obstacle>() ;
        	     debug = false;
        	    	
    			System.out.println("PERDU !");
    		}
    	 
    	 
    	 if(debug) {
    		 contextBackground.fillOval(obstacle.x - (obstacle.taille/2), obstacle.y - (obstacle.taille/2) ,obstacle.taille, obstacle.taille);
    		 debugEnregistreur.ajouterPosition(0);
      } else { 
    	  contextBackground.drawImage(obstacle.image,obstacle.x - (obstacle.taille/2), obstacle.y - (obstacle.taille/2) ,obstacle.taille, obstacle.taille);
    	  debugEnregistreur.ajouterPosition(1);
    	  }

 	 
     }	
  
  
     afficherScore.setText(" Score : " + scoreActuel*5 );
     
     if(pause) {
    	 bouttonPause.setText("Resume");
    	 pauseEnregistreur.ajouterPosition(0);
    	 
     }
     else {
    	 bouttonPause.setText("Pause");
    	 pauseEnregistreur.ajouterPosition(1);

     }   
     
     
    		}        
    	
        };
        backgroundTimer.start();

        AnimationTimer characterTimer = new AnimationTimer() {
            // ... ( Le code pour l'animation du fantôme ici)
        	private long lastTime = 0;
			private double x = image.getWidth()/2 , y = 0;
			private double gravite = 500,vitesse = 100;
			private double ajustGravite = 515;
			boolean pause = false;
			boolean debug = false;

		
			@Override
			public void handle(long now) {
			// Temps en sec = 10^(-9) * temps en nanosec
			double deltaTime = (now - lastTime)* 1e-9;
			
			if(pauseEnregistreur.dernierePosition() == 0 ) {  //la derniere position de pauseEnregistruer est soit 1 ou 0 
				pause = true;                                // il varie chaque fois qu'on apuie sur le bouton pause
			}else {
				pause = false;            //change la valeur de pause l'orsque le boutton pause est appuyer
			}
			
			if(debugEnregistreur.dernierePosition() == 0 ) {   //fonctionne de la même manière que pause
				debug = true;                               
			}else {
				debug  = false;   
			}
			
			
			if(vitesse<300 && !pause) {		
				vitesse += (deltaTime - (int) deltaTime)*gravite;  //augumente la vitesse si elle est inférieur à 300
			}else if (vitesse >300 && !pause){		
				vitesse = 300;	
			}
			
			if(gravite <= ajustGravite && !pause) {              
				gravite += (deltaTime - (int) deltaTime)*80000;  //gravite prend la valeur de ajustgravite 
				gravite = ajustGravite;
		
			}
		
			
		if(!pause) {	
			
	    y += (deltaTime - (int) deltaTime)*vitesse;
		ajustGravite += (deltaTime - (int) deltaTime)*(15);  //la gravite evolue de 15pixel par seconde^2
		
		     if(y+30 >= image.getHeight()) { 
		    	 y = image.getHeight() - 31;     //la balle rebondi si elle touche le bas de l'écran
		    	 vitesse -= 500;
		    	
		    	 
		     }else if((y)- 15 < 0){		    	 //la balle se téléporte plus bas si elle touche le haut de l'écran
		    	 y += 16;
	     
		}
		
			contextCharacter.clearRect(0, 0, w, h);
			
			
		if(!debug) {	
			contextCharacter.drawImage(new Image("C://Users/souro/Downloads/fichiersFH/ghost.png"),x-30, y-30,60,60);
		}else {
			contextCharacter.fillOval(x-30, y-30,60,60);
		}

			lastTime = now;
	


			scene.setOnKeyPressed(event -> {
			    switch (event.getCode()) { 
			        case SPACE:
			        	if(!pause) {
			        vitesse = (-300);  }  
			        	
				default:
					break;
					
			    }
			});
			
		xGhost.ajouterPosition(x-30);
		yGhost.ajouterPosition(y-30);
			
	
		}
			}
			  
        };
        characterTimer.start();

        primaryStage.setTitle("Flappy ghost");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
	
	

    public static void main(String[] args) {
        launch(args);
    }
}
		
