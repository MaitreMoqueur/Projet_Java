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

class IA {
    private int difficulte; // 0 = Facile, 1 = Moyen, 2 = Difficile

    public IA(int difficulte) {
        this.difficulte = difficulte;
    }

    public int getDifficulte() {
        return difficulte;
    }

    public void setDifficulte(int difficulte) {
        if (difficulte >= 0 && difficulte <= 2) {
            this.difficulte = difficulte;
        } else {
            throw new IllegalArgumentException("Difficulté invalide : " + difficulte);
        }
    }
}