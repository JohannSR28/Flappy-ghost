package application;

import java.util.LinkedList;

public class Enregistreur {
    private LinkedList<Double> tableau = new LinkedList<>();

    public void ajouterPosition(double position) {
        tableau.add(position);
    }

    public double dernierePosition() {
        if (!tableau.isEmpty()) {
            return tableau.getLast();
        }
        return 0.0; // Valeur par défaut si le tableau est vide
    }
}
