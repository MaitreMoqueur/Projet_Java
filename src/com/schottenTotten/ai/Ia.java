package com.schottenTotten.ai;

import java.util.List;
import java.util.Arrays;
import com.shottenTotten.model;

public class IA {
    private int difficulte; // 0 = Facile, 1 = Moyen, 2 = Difficile

    public IA(int difficulte) {
        this.difficulte = difficulte;
    }

    public int getDifficulte() {
        return difficulte;
    }

    @Override
    public jouerCarte() {
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