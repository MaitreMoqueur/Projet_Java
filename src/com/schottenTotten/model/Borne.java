package com.schottenTotten.model;

import java.util.ArrayList;
import java.util.List;

public class Borne {
    public enum Etat {
        LIBRE,
        REVENDIQUE_J1,
        REVENDIQUE_J2,
        BLOQUE
    }

    private Joueur joueur1;
    private Joueur joueur2;
    private List<String> cartesJoueur1;
    private List<String> cartesJoueur2;
    private Etat etat;

    public Borne(Joueur joueur1, Joueur joueur2) {
        this.joueur1 = joueur1;
        this.joueur2 = joueur2;
        this.cartesJoueur1 = new ArrayList<>();
        this.cartesJoueur2 = new ArrayList<>();
        this.etat = Etat.LIBRE;
    }

    public void jouerCarte(Joueur joueur, String carte) {
        if (etat != Etat.LIBRE) {
            System.out.println("Cette borne a déjà été revendiquée.");
            return;
        }

        if (joueur.equals(joueur1)) {
            cartesJoueur1.add(carte);
        } else if (joueur.equals(joueur2)) {
            cartesJoueur2.add(carte);
        } else {
            System.out.println("Joueur inconnu !");
        }
    }

    public boolean verifierConditions(Joueur joueur) {
        // Logique personnalisée pour vérifier les combinaisons gagnantes
        if (cartesJoueur1.size() >= 3) {
            etat = Etat.REVENDIQUE_J1;
            return joueur.equals(joueur1);
        } else if (cartesJoueur2.size() >= 3) {
            etat = Etat.REVENDIQUE_J2;
            return joueur.equals(joueur2);
        }
        return false;
    }

    public Etat getEtat() {
        return etat;
    }

    public void afficherEtat() {
        System.out.println("Cartes Joueur 1 : " + cartesJoueur1);
        System.out.println("Cartes Joueur 2 : " + cartesJoueur2);
        System.out.println("État : " + etat);
    }
}
