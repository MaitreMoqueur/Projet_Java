package com.schottenTotten.model;

import java.util.ArrayList;
import java.util.List;

public class GestionBornes {
    private List<Borne> bornes;

    public GestionBornes(Joueur joueur1, Joueur joueur2) {
        this.bornes = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            bornes.add(new Borne(joueur1, joueur2));
        }
    }

    public void jouerCarte(int indexBorne, Joueur joueur, String carte) {
        if (indexBorne < 0 || indexBorne >= bornes.size()) {
            System.out.println("Index de borne invalide !");
            return;
        }
        bornes.get(indexBorne).jouerCarte(joueur, carte);
    }

    public boolean verifierConditionsVictoire(Joueur joueur) {
        int bornesRevendiquees = 0;

        for (Borne borne : bornes) {
            if (borne.verifierConditions(joueur)) {
                bornesRevendiquees++;
            }
        }

        return bornesRevendiquees >= 5; // Exemple : gagner avec 5 bornes revendiquées
    }

    public void afficherBornes() {
        for (int i = 0; i < bornes.size(); i++) {
            System.out.println("Borne " + (i + 1) + " :");
            bornes.get(i).afficherEtat();
            System.out.println();
        }
    }
}
