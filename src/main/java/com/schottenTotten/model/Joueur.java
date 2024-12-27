package main.java.com.schottenTotten.model;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

public class Joueur {
    protected String pseudo;
    protected int score = 0;
    protected Hand main;
    protected List<Integer> bornes_gagnees ;
    protected int CarteTactiqueplayed  ;
    protected boolean jokerPlayed ;

    public Joueur(String pseudo, Pioche pioche) {
        this.pseudo = pseudo;
        this.CarteTactiqueplayed  = 0;
        this.main = new Hand(pioche);
        this.jokerPlayed = false;
        this.bornes_gagnees = new ArrayList<>();
    }

    public int getCarteTactiquePlayed() {
        return this.CarteTactiqueplayed;
    }
    
    public void incrementerCarteTactiquePlayed() {
        this.CarteTactiqueplayed++;
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

    public boolean isJokerPlayed() {
        return jokerPlayed;
    }

    public void setJokerPlayed(boolean jokerPlayed) {
        this.jokerPlayed = jokerPlayed;
    }

    public boolean revendiquerBorne(Borne borne, Joueur joueur) {
        boolean gagnee = borne.revendiquer(joueur);
        if (gagnee == true) {
            this.bornes_gagnees.add(borne.id_borne);
        }
        return gagnee;
    }

    public Carte jouerCarte(int position_carte, Borne Borne) {
        if (this.main.getNombreCartes() == 0) {
            throw new IllegalStateException("Aucune carte en main.");
        }
        this.main.nombre_cartes--;
        return this.main.cartes.remove(position_carte);
    }

}