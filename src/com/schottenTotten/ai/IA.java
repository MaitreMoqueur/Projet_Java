package com.schottenTotten.ai;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import com.schottenTotten.model.*;

// La classe IA hérite de Joueur
public class IA extends Joueur {
    private int difficulte; // 0 = Facile, 1 = Moyen, 2 = Difficile

    // Constructeur IA
    public IA(int difficulte, String nom, Pioche pioche) {
        super(nom, pioche); // Appel au constructeur de Joueur
        this.difficulte = difficulte;
    }

    // Getter pour la difficulté
    public int getDifficulte() {
        return difficulte;
    }

    // Méthode pour jouer une carte en fonction de la difficulté
    public List<Integer> jouerCarteIA(List<Borne> bornes) {
        if (this.main.getNombreCartes() == 0) {
            throw new IllegalStateException("Aucune carte en main.");
        }
    
        switch (difficulte) {
            case 0:
                return jouerTourFacile(bornes);
            case 1:
                return jouerTourMoyen(bornes);
            case 2:
                return jouerTourDifficile(bornes);
            default:
                throw new IllegalStateException("Niveau de difficulté inconnu : " + difficulte);
        }
    }

    private List<Integer> jouerTourFacile(List<Borne> bornes) {
        Random random = new Random();
        int positionCarte = random.nextInt(this.main.getNombreCartes());
        int positionBorne = random.nextInt(bornes.size());
        return List.of(positionCarte, positionBorne);
    }
    
    private List<Integer> jouerTourMoyen(List<Borne> bornes) {
        // Logique pour niveau moyen à implémenter
        // Exemple : stratégie semi-aléatoire avec évaluation simplifiée des bornes
        // return List.of(positionCarte, positionBorne);
        throw new UnsupportedOperationException("Niveau moyen non encore implémenté.");
    }
    
    private List<Integer> jouerTourDifficile(List<Borne> bornes) {
        // Logique pour niveau difficile à implémenter
        // Exemple : stratégie avancée en fonction de l'état des bornes
        // return List.of(positionCarte, positionBorne);
        throw new UnsupportedOperationException("Niveau difficile non encore implémenté.");
    }   

    public List<Integer> revendiquerBorneIA(List<Borne> listeBornes) {
        List<Integer> bornesCapturees = new ArrayList<>();
    
        for (int i = 0; i < listeBornes.size(); i++) {
            Borne borne = listeBornes.get(i);
    
            // Tente de revendiquer la borne
            boolean captureReussie = borne.revendiquer(this);
            if (captureReussie) {
                bornesCapturees.add(i); // Ajoute l'index de la borne capturée
            }
        }
    
        return bornesCapturees; // Retourne la liste des indices des bornes capturées
    }
    

}

