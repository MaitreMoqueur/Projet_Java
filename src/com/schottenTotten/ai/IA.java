package com.schottenTotten.ai;

import java.util.List;
import java.util.Random;
import com.schottenTotten.model.*;

// La classe IA hérite de Joueur
public class IA extends Joueur {
    private int difficulte; // 0 = Facile, 1 = Moyen, 2 = Difficile

    // Constructeur IA
    public IA(int difficulte, String nom, Pioche pioche) {
        super(nom, new Hand(pioche)); // Appel au constructeur de Joueur
        this.difficulte = difficulte;
    }

    // Getter pour la difficulté
    public int getDifficulte() {
        return difficulte;
    }

    // Méthode pour jouer une carte en fonction de la difficulté
    @Override
    public Carte jouerCarte() {
        if (this.main.nombre_cartes == 0) {
            throw new IllegalStateException("Aucune carte en main.");
        }

        // Gestion des comportements par niveau de difficulté
        if (difficulte == 0) { // Niveau facile : choix aléatoire
            Random random = new Random();
            int positionCarte = random.nextInt(this.main.nombre_cartes);
            this.main.nombre_cartes--;
            return this.main.cartes.remove(position_cart);
        } else if (difficulte == 1) {
            this.main.nombre_cartes--;
            return this.main.cartes.remove(0);
            this.main.nombre_cartes--;
        } else if (difficulte == 2) {
            return this.main.cartes.remove(0);
        } else {
            throw new IllegalStateException("Niveau de difficulté inconnu : " + difficulte);
        }
    }
}

