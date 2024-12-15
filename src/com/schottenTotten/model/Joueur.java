package com.schottenTotten.model;

import java.util.List;
import java.util.Arrays;

abstract class Joueur {
    protected String pseudo;
    protected int score = 0;
    protected Hand main;

    public Joueur(String pseudo, Hand main) {
        this.pseudo = pseudo;
        this.main = main;
    }

    public String getPseudo() {
        return this.pseudo;
    }

    public int getScore() {
        return this.score;
    }

    public Hand getMain() {
        return this.main;
    }

    public void victoire() {
        this.score++;
    }

    public abstract Carte jouerCarte(int position_carte);

    public abstract void revendiquerBorne(Borne borne, Joueur joueur);
    
}

class JoueurReel extends Joueur {
    public JoueurReel(String nom, Pioche pioche) {
        super(nom, new Hand(pioche));
    }

    @Override
    public Carte jouerCarte(int position_carte) {
        this.main.nombre_cartes--;
        return this.main.cartes.remove(position_carte);
    }

    @Override
    public void revendiquerBorne(Borne borne, Joueur joueur) {
        borne.revendiquer(joueur);
    }
}