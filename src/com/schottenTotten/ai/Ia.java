package com.schottenTotten.ai;

import java.util.List;
import java.util.Arrays;
import com.shottenTotten.model.*;

class IA extends Joueur {
    private int difficulte; // 0 = Facile, 1 = Moyen, 2 = Difficile

    public IA(int difficulte, String nom, Pioche pioche) {
        this.difficulte = difficulte;
        super(nom, new Hand(pioche));
    }

    public int getDifficulte() {
        return difficulte;
    }

    @Override
    public Carte jouerCarte() {
        if (difficulte == 0) {
            if (this.main.nombre_cartes > 0) {
                Random random = new Random();
                int position_cart = random.nextInt(this.main.nombre_cartes);
                this.main.nombre_cartes--;
                return this.main.cartes.remove(position_cart);
            } 
            else {
                throw new IllegalStateException("Aucune carte en main.");
            }
        }
        else if (difficulte == 1) {
            // Moyen : logique de jeu à implémenter ici
            // Exemple de pseudo-code :
            // - Analyser les cartes disponibles
            // - Choisir la meilleure carte selon une stratégie prédéfinie
            // - Jouer cette carte
        }
    }
}