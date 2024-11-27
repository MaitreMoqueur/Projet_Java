package com.schottenTotten.controller;

import com.schottenTotten.model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Jeu {
    private List<Carte> deck;
    private Joueur joueur1;
    private Joueur joueur2;
    private List<Borne> bornes;

    public Jeu(String nomJoueur1, String nomJoueur2) {
        this.deck = creerDeck();
        Collections.shuffle(deck);

        this.joueur1 = new Joueur(nomJoueur1, true);
        this.joueur2 = new Joueur(nomJoueur2, true);

        this.bornes = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            bornes.add(new Borne(i));
        }

        distribuerCartes();
    }

    private List<Carte> creerDeck() {
        String[] couleurs = {"rouge", "bleu", "vert", "jaune", "violet", "orange"};
        List<Carte> deck = new ArrayList<>();
        for (String couleur : couleurs) {
            for (int valeur = 1; valeur <= 9; valeur++) {
                deck.add(new Carte(couleur, valeur));
            }
        }
        return deck;
    }

    private void distribuerCartes() {
        for (int i = 0; i < 6; i++) {
            joueur1.ajouterCarte(deck.remove(0));
            joueur2.ajouterCarte(deck.remove(0));
        }
    }

    public void afficherEtatJeu() {
        System.out.println("Joueur 1 : " + joueur1.getMain());
        System.out.println("Joueur 2 : " + joueur2.getMain());
        for (Borne borne : bornes) {
            System.out.println(borne);
        }
    }
}
