package com.schottenTotten.model;

import java.util.Random;

public class IA extends Joueur {
    private int difficulte;

    public IA(String nom, int difficulte) {
        super(nom, false); // false -> pas un joueur humain
        this.difficulte = difficulte;
    }

    @Override
    public Carte jouerCarte() {
        Random rand = new Random();
        int indexCarte = rand.nextInt(this.getMain().size());
        return this.jouerCarte(indexCarte);
    }
}
