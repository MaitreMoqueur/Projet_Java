package com.schottenTotten.model;

import java.util.ArrayList;
import java.util.List;

public class FabriqueCarte {
    private static final String[] COULEURS = {"Rouge", "Bleu", "Vert", "Jaune", "Violet", "Orange"};

    public static List<CarteClan> creerCartesClan() {
        List<CarteClan> cartes = new ArrayList<>();
        for (String couleur : COULEURS) {
            for (int valeur = 1; valeur <= 9; valeur++) {
                cartes.add(new CarteClan(valeur, couleur));
            }
        }
        return cartes;
    }

    public static List<CarteTactique> creerCartesTactiques() {
        List<CarteTactique> cartes = new ArrayList<>();
        cartes.add(new CarteTactique("Espion", "Jouer une carte supplémentaire"));
        cartes.add(new CarteTactique("Chef", "Remplacer une carte déjà posée"));
        cartes.add(new CarteTactique("Renfort", "Ajoute +2 à la valeur d'une carte"));
        cartes.add(new CarteTactique("Espionnage", "Voir la main de l'adversaire"));
        cartes.add(new CarteTactique("Blocage", "Empêche la revendication d'une muraille pour 1 tour"));
        cartes.add(new CarteTactique("Double Attaque", "Permet de poser 2 cartes"));
        cartes.add(new CarteTactique("Sabotage", "Supprime une carte de l'adversaire"));
        cartes.add(new CarteTactique("Muraille Renforcée", "Double les points d'une muraille"));
        cartes.add(new CarteTactique("Pioche Forcée", "L'adversaire pioche une carte en moins"));
        cartes.add(new CarteTactique("Stratégie", "Permet de changer une carte de place"));
        return cartes;
    }
}



public abstract class Carte {
    private String nom;

    public Carte(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    @Override
    public String toString() {
        return nom;
    }
}


public class CarteClan extends Carte {
    private int valeur;
    private String couleur;

    public CarteClan(int valeur, String couleur) {
        super("Clan " + couleur + " " + valeur);
        this.valeur = valeur;
        this.couleur = couleur;
    }

    public int getValeur() {
        return valeur;
    }

    public String getCouleur() {
        return couleur;
    }
}


public class CarteTactique extends Carte {
    private String effet;

    public CarteTactique(String nom, String effet) {
        super(nom);
        this.effet = effet;
    }

    public String getEffet() {
        return effet;
    }

    @Override
    public String toString() {
        return super.toString() + " (Effet: " + effet + ")";
    }
}

