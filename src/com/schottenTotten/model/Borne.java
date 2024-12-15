package com.schottenTotten.model;

import java.util.ArrayList;
import java.util.List;

public class Borne {
    public enum Etat {
        LIBRE,
        REVENDIQUEE_J1,
        REVENDIQUEE_J2,
    }

    private List<Carte> cartesJoueur1;
    private List<Carte> cartesJoueur2;
    private String pseudoJ1;
    private String pseudoJ2;
    private String dernier_joueur;

    private Etat etat;

    public Borne(String pseudoJ1, String pseudoJ2) {
        this.cartesJoueur1 = new ArrayList<>();
        this.cartesJoueur2 = new ArrayList<>();
        this.pseudoJ1 = pseudoJ1;
        this.pseudoJ2 = pseudoJ2;
        this.etat = Etat.LIBRE;
    }
/// METHODE AJOUTER CARTE A CONDENSER AVEC JOUER CARTE ?

    public void ajouterCarte(Joueur joueur, Carte carte) {
        if (etat == Etat.LIBRE) {
            if (joueur.pseudo == this.pseudoJ1) {
                if (cartesJoueur1.size() < 3) {
                    cartesJoueur1.add(carte);
                }
                /*else {
                    System.out.println("Il y a déjà 3 cartes jouées, impossible d'en rajouter une.");
                }*/
            } 
            else {
                if (cartesJoueur2.size() < 3) {
                    cartesJoueur2.add(carte);
                }
                /*else {
                    System.out.println("Il y a déjà 3 cartes jouées, impossible d'en rajouter une.");
                }*/
            }
            this.dernier_joueur = joueur.pseudo;
        }
        /*else {
            System.out.println("Cette borne a déjà été revendiquée.");
        }*/
    }

    public void verifierConditions(Joueur joueur) {
        /*if ((cartesJoueur1.size() >= 3) || (cartesJoueur2.size() >= 3)) {
            


        }
        /*else {
            System.out.println("L'un des deux joueurs n'a pas posé 3 cartes sur la borne.");
        }*/
        



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
