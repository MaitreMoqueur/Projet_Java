package com.schottenTotten.model;

import java.util.ArrayList;
import java.util.List;

public class Borne {
    private int id;
    private List<Carte> cartesJoueur1;
    private List<Carte> cartesJoueur2;

    public Borne(int id) {
        this.id = id;
        this.cartesJoueur1 = new ArrayList<>();
        this.cartesJoueur2 = new ArrayList<>();
    }

    public void ajouterCarte(int joueur, Carte carte) {
        if (joueur == 1) {
            cartesJoueur1.add(carte);
        } else if (joueur == 2) {
            cartesJoueur2.add(carte);
        } else {
            throw new IllegalArgumentException("Numéro de joueur invalide");
        }
    }

    public boolean estRevendiquee() {
        // Logique pour déterminer si la borne est revendiquée
        return false; // Exemple de placeholder
    }

    @Override
    public String toString() {
        return "Borne " + id + " : Joueur 1 -> " + cartesJoueur1 + ", Joueur 2 -> " + cartesJoueur2;
    }
}
