# Flappy Ghost

**Auteur :** Johann Sourou  
**Date de la dernière modification :** 10-08-2023  
**Nom du programme :** Flappy Ghost  

## Description du Projet

Flappy Ghost est un jeu développé en Java en utilisant la bibliothèque JavaFX. Le but du jeu est d'atteindre le score le plus élevé possible en évitant les obstacles sur le chemin. Le joueur contrôle un fantôme qui doit naviguer à travers des obstacles qui apparaissent périodiquement à l'écran.

## Fonctionnalités

- Contrôle du fantôme avec la barre d'espace pour le faire sauter.
- Animation fluide avec gestion du fond, des obstacles, et du fantôme.
- Mode pause pour interrompre le jeu à tout moment.
- Mode debug pour afficher des informations supplémentaires.
- Obstacles avec différents comportements (stationnaire, mouvement sinusoïdal, et mouvement quantique).
- Affichage en temps réel du score du joueur.

## Technologies Utilisées

- **Java**
- **JavaFX**

## Instructions d'Installation

1. **Pré-requis :**
   - Java Development Kit (JDK) version 8 ou plus récent.
   - JavaFX SDK (généralement inclus dans le JDK à partir de la version 11).

2. **Cloner le projet :**

   ```bash
   git clone https://github.com/votre-utilisateur/flappy-ghost.git
   ```

3. **Compiler le projet :**

   Si vous utilisez un IDE comme IntelliJ IDEA ou Eclipse, ouvrez simplement le projet et exécutez le fichier `Main.java`.

   Si vous préférez utiliser la ligne de commande :

   ```bash
   cd flappy-ghost
   javac -cp . --module-path /path/to/javafx/lib --add-modules javafx.controls application/Main.java
   ```

4. **Exécuter le projet :**

   ```bash
   java -cp . --module-path /path/to/javafx/lib --add-modules javafx.controls application.Main
   ```

## Comment Jouer

- Utilisez la barre d'espace pour faire sauter le fantôme.
- Évitez les obstacles qui apparaissent sur votre chemin.
- Appuyez sur le bouton "Pause" pour mettre le jeu en pause.
- Activez le "Mode Debug" avec la case à cocher pour voir des détails supplémentaires sur les collisions.

## Structure du Code

- **Main.java :** Contient la logique principale du jeu, y compris l'affichage, les contrôles du fantôme, et la gestion des obstacles.
- **Obstacle.java :** (Non inclus ici) Implémente la classe d'obstacles qui apparaissent sur l'écran.
- **Enregistreur.java :** (Non inclus ici) Gère les états de pause et de debug pour les animations.

## Ressources

Les images et autres ressources doivent être placées dans le chemin indiqué dans le code, ou modifiées selon vos préférences.

- `bg.png` : Image d'arrière-plan.
- `ghost.png` : Image du fantôme contrôlé par le joueur.
- `obstacles/` : Répertoire contenant les images des obstacles.

## Problèmes Connus

- La difficulté du jeu augmente progressivement avec le temps.
- Les collisions peuvent être délicates à gérer en mode debug.

## Licence

Ce projet est sous licence MIT. Vous êtes libre de l'utiliser, le modifier et le distribuer comme bon vous semble.
