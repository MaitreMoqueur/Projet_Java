package com.schottenTotten;

import com.schottenTotten.controller.GestionPartie;
import com.schottenTotten.model.Joueur;
import com.schottenTotten.model.JoueurReel;
import com.schottenTotten.model.IA;
import com.schottenTotten.model.Pioche;
import com.schottenTotten.view.MenuPrincipal;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Étape 1 : Créer le menu principal
        MenuPrincipal menu = new MenuPrincipal();

        // Étape 2 : Gérer le menu (configuration des paramètres du jeu)
        if (!menu.gererMenu()) {
            System.out.println("Merci d'avoir joué ! À bientôt !");
            return; // Fin du programme si l'utilisateur quitte le menu
        }

        // Étape 3 : Initialiser les joueurs et les composants du jeu
        List<Joueur> joueurs = new ArrayList<>();
        Pioche pioche = new Pioche();

        int modeDeJeu = menu.getModeDeJeu();
        int difficulteIA1 = menu.getDifficulteIA();
        int difficulteIA2 = menu.getDifficulteIA2();

        switch (modeDeJeu) {
            case 0: // Spectateur (IA vs IA)
                joueurs.add(new IA("IA1", pioche, difficulteIA1));
                joueurs.add(new IA("IA2", pioche, difficulteIA2));
                break;
            case 1: // Joueur vs IA
                joueurs.add(new JoueurReel("Joueur", pioche));
                joueurs.add(new IA("IA", pioche, difficulteIA1));
                break;
            case 2: // Joueur vs Joueur
                joueurs.add(new JoueurReel("Joueur 1", pioche));
                joueurs.add(new JoueurReel("Joueur 2", pioche));
                break;
            default:
                System.out.println("Mode de jeu invalide !");
                return;
        }

        // Étape 4 : Lancer la gestion de la partie
        GestionPartie gestionPartie = new GestionPartie(joueurs, pioche);
        gestionPartie.gererPartie();

        // Étape 5 : Fin de la partie
        System.out.println("La partie est terminée !");
    }
}
