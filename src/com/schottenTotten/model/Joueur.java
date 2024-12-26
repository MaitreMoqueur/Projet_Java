package com.schottenTotten.model;

import com.schottenTotten.model.Borne;
import com.schottenTotten.model.Carte;
import java.util.List;
import java.util.Arrays;

public class Joueur {
    protected String pseudo;
    protected int score = 0;
    protected Hand main;
    protected List<Integer> bornes_gagnees ;

    public Joueur(String pseudo, Pioche pioche) {
        this.pseudo = pseudo;
        this.main = new Hand(pioche);

        for (int i = 0; i < 6; i++) {
            this.main.piocherCarte();
        }
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

    public List<Integer> getBornes() {
        return this.bornes_gagnees;
    }

    public void victoire() {
        this.score++;
    }



    public boolean revendiquerBorne(Borne borne, Joueur joueur) {
        boolean gagnee = borne.revendiquer(joueur);
        if (gagnee == true) {
            bornes_gagnees.add(borne.id_borne);
        }
        return gagnee;
    }

    public Carte jouerCarte(int position_carte, Borne Borne) {
        this.main.nombre_cartes--;
        Borne.ajouterCarte(this, this.getMain().getCarte(position_carte));
        return this.main.cartes.remove(position_carte);
    }

}

