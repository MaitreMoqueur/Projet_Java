package main.java.com.schottenTotten.model;

import java.util.List;
import java.util.Arrays;

public class Joueur {
    protected String pseudo;
    protected int score = 0;
    protected Hand main;
    protected List<Integer> bornes_gagnees ;
    protected int carte_tactique_jouée ;

    public Joueur(String pseudo, Pioche pioche) {
        this.pseudo = pseudo;
        this.carte_tactique_jouée = 0;
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
        Carte carte_a_jouer = this.getMain().getCarte(position_carte);
        //if (carte_a_jouer instanceof CarteTactique) {
        //    this.carte_tactique_jouée ++;
        //    carte_a_jouer.appliquerEffet(this, Borne);
        //}
        //else {
            Borne.ajouterCarte(this,carte_a_jouer);
        //}
        return this.main.cartes.remove(position_carte);
    }

}

