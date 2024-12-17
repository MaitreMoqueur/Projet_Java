package com.schottenTotten.model;

import java.util.ArrayList;
import java.util.List;

public class Borne {
    public enum Etat {
        LIBRE,
        CAPTUREE_J1,
        CAPTUREE_J2
    }

    private List<Carte> cartesJoueur1;
    private List<Carte> cartesJoueur2;
    private String pseudoJ1;
    private String pseudoJ2;
    private String dernier_joueur;

    private Etat etat;

    public Borne(String pseudoJ1, String pseudoJ2) {
        cartesJoueur1 = new ArrayList<>();
        cartesJoueur2 = new ArrayList<>();
        this.pseudoJ1 = pseudoJ1;
        this.pseudoJ2 = pseudoJ2;
        etat = Etat.LIBRE;
    }

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
            dernier_joueur = joueur.pseudo;
        }
        /*else {
            System.out.println("Cette borne a déjà été revendiquée.");
        }*/
    }

    private boolean estSuiteCouleur(List<Carte> cartes) {
        return estCouleur(cartes) && estSuite(cartes);
    }

    private boolean estBrelan(List<Carte> cartes) {
        int valeur1 = cartes.get(0).getNumero();
        int valeur2 = cartes.get(1).getNumero();
        int valeur3 = cartes.get(2).getNumero();

        if ((valeur1 == valeur2) && (valeur2 == valeur3)) {
            return true;
        } 
        else {
            return false;
        }
    }

    private boolean estCouleur(List<Carte> cartes) {
        String couleur1 = cartes.get(0).getCouleur();
        String couleur2 = cartes.get(1).getCouleur();
        String couleur3 = cartes.get(2).getCouleur();

        if ((couleur1 == couleur2) && (couleur2 == couleur3)) {
            return true;
        } 
        else {
            return false;
        }
    }

    private boolean estSuite(List<Carte> cartes) {
        cartes.sort((c1, c2) -> Integer.compare(c1.getNumero(), c2.getNumero()));

        if ((cartes.get(0).getNumero() + 1 == cartes.get(1).getNumero()) && (cartes.get(1).getNumero() + 1 == cartes.get(2).getNumero())) {
            return true;
        } 
        else {
            return false;
        }
    }

    private int somme(List<Carte> cartes) {
        int valeur1 = cartes.get(0).getNumero();
        int valeur2 = cartes.get(1).getNumero();
        int valeur3 = cartes.get(2).getNumero();

        return valeur1 + valeur2 + valeur3;
    }

    private int evaluerCombo(List<Carte> cartes) {
        if (estSuiteCouleur(cartes)) {
            return 5;
        } 
        else if (estBrelan(cartes)) {
            return 4;
        } 
        else if (estCouleur(cartes)) {
            return 3;
        } 
        else if (estSuite(cartes)) {
            return 2;
        } 
        else {
            return 1;
        }
    }

    public void revendiquer(Joueur joueur) {
        if (cartesJoueur1.size() >= 3 && cartesJoueur2.size() >= 3) {
            int combo1 = evaluerCombo(cartesJoueur1);
            int combo2 = evaluerCombo(cartesJoueur2);
    
            if (combo1 > combo2 || (combo1 == combo2 && somme(cartesJoueur1) > somme(cartesJoueur2))) {
                etat = Etat.CAPTUREE_J1;
            } else if (combo2 > combo1 || (combo1 == combo2 && somme(cartesJoueur2) > somme(cartesJoueur1))) {
                etat = Etat.CAPTUREE_J2;
            } else {
                // En cas d'égalité parfaite, l'avant-dernier joueur gagne la borne
                if (dernier_joueur.equals(pseudoJ1)) {
                    etat = Etat.CAPTUREE_J2;
                } else {
                    etat = Etat.CAPTUREE_J1;
                }
            }
        } else {
            System.out.println("Les conditions ne sont pas remplies pour revendiquer cette borne.");
        }
    }
    

    public Etat getEtat() {
        return etat;
    }

    public List<Carte> getList(Joueur joueur) {
        if (joueur.pseudo == this.pseudoJ1) {
            return this.cartesJoueur1;
        }
        else {
            return this.cartesJoueur2;
        }
    }
}
