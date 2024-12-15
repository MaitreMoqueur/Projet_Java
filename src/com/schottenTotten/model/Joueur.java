package com.schottenTotten.model;

import java.util.List;
import java.util.Arrays;

abstract class Joueur {
    protected String pseudo;
    protected int score = 0;
    protected Hand main;

    public Joueur(String nom, Pioche pioche) {
        this.pseudo = nom;
        this.main = new Hand(pioche);
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

    public jouerCarte(int position_carte) {
        this.main.nombre_cartes--;
        return this.main.cartes.remove(cartes[i-1]);
    }

    public void revendiquerBorne(Borne borne) {
        Borne.revendiquer(pseudo);
    }
    
}

class JoueurReel extends Joueur {
    public JoueurReel() {

    }
}