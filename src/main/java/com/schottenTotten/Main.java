package main.java.com.schottenTotten;

import main.java.com.schottenTotten.controller.GestionPartie;
import main.java.com.schottenTotten.model.*;
import main.java.com.schottenTotten.ai.IA;
import main.java.com.schottenTotten.view.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Étape 1 : Créer le menu principal et la vue
        MenuPrincipal menu = new MenuPrincipal();
        ConsoleView view = new ConsoleView();
        boolean continuerProgramme = true;
        boolean relanceRapide = false;

        int modeDeJeu = -1;
        int difficulteIA1 = -1;
        int difficulteIA2 = -1;
        Variante variante = null; // Utilisation de l'énumération Variante

        while (continuerProgramme) {
            if (!relanceRapide) {
                // Étape 2 : Gérer le menu (configuration des paramètres du jeu)
                if (!menu.gererMenu()) {
                    ConsoleView.afficherfermeturejeu();
                    return; // Fin du programme si l'utilisateur quitte le menu
                }

                // Récupérer les paramètres du menu principal
                modeDeJeu = menu.getModeDeJeu();
                difficulteIA1 = menu.getDifficulteIA();
                difficulteIA2 = menu.getDifficulteIA2();
                variante = menu.getVariante(); // Retourne un Variante
            }

            // Étape 3 : Initialiser les joueurs et les composants du jeu
            List<Joueur> joueurs = new ArrayList<>();
            List<Borne> listeBornes = new ArrayList<>();
            Pioche pioche = new Pioche();

            Pioche piocheTactique = null;
            if (variante != Variante.CLASSIQUE) {
                piocheTactique = new Pioche(true); // Pioche pour les cartes tactiques
            }

            switch (modeDeJeu) {
                case 1: // Spectateur (IA vs IA)
                    joueurs.add(new IA(difficulteIA1, "IA1", pioche));
                    joueurs.add(new IA(difficulteIA2, "IA2", pioche));
                    break;
                case 2: // Joueur vs IA
                    joueurs.add(new Joueur("Joueur", pioche));
                    joueurs.add(new IA(difficulteIA1, "IA", pioche));
                    break;
                case 3: // Joueur vs Joueur
                    joueurs.add(new Joueur("Joueur 1", pioche));
                    joueurs.add(new Joueur("Joueur 2", pioche));
                    break;
                default:
                    System.out.println("Mode de jeu invalide !");
                    return;
            }

            String pseudoJ1 = joueurs.get(0).getPseudo();
            String pseudoJ2 = joueurs.get(1).getPseudo();

            for (int i = 0; i < 9; i++) { // Créez les 9 bornes nécessaires
                listeBornes.add(new Borne(pseudoJ1, pseudoJ2, i));
            }

            // Étape 4 : Lancer la gestion de la partie
            GestionPartie gestionPartie = new GestionPartie(joueurs, listeBornes, view, variante);
            gestionPartie.demarrerPartie(pioche, piocheTactique);

            // Étape 5 : Gestion du menu de fin de partie
            ConsoleView.afficherMenuFin();
            int choix = InputHandler.demanderEntree(0, 2);

            switch (choix) {
                case 0: // Relance rapide
                    relanceRapide = true;
                    break;
                case 1: // Retour au menu principal
                    relanceRapide = false;
                    break;
                case 2: // Quitter
                    continuerProgramme = false;
                    break;
                default:
                    System.out.println("Choix invalide. Retour au menu principal.");
                    relanceRapide = false;
            }
        }

        ConsoleView.afficherfermeturejeu();
    }
}
