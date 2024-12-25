package com.schottenTotten.view;

import com.schottenTotten.model.Borne;
import com.schottenTotten.model.Carte;
import com.schottenTotten.model.Joueur;

import java.util.List;

public class ConsoleView {

    public void nettoyerConsole() {
        System.out.print("\033[H\033[2J"); // ANSI escape codes : repositionne le curseur et efface l'écran
        System.out.flush(); 
    }

    public void afficherEtatTour(int numeroTour, Joueur joueurActuel, List<Borne> bornes) {
        nettoyerConsole();

        System.out.println("Tour n°" + numeroTour + "   -   " + joueurActuel.getPseudo() + " à vous de jouer");
        System.out.println("==============================\n");

        System.out.println("Cartes jouées sur les bornes adverses :");
        for (int i = 0; i < bornes.size(); i++) {
            //FIX
            System.out.println("Borne " + (i + 1) + " - Cartes adverses : " + bornes.get(i).getList(joueurActuel));
        }
    
        System.out.println("\nCartes alliées jouées sur les bornes :");
        for (int i = 0; i < bornes.size(); i++) {
            System.out.println("Borne " + (i + 1) + " - Cartes alliées : " + bornes.get(i).getList(joueurActuel));
        }

        System.out.println("\nMain du joueur :");
        List<Carte> main = joueurActuel.getMain().getCartes();
        for (int i = 0; i < main.size(); i++) {
            System.out.println((i + 1) + ". " + main.get(i));
        }
    }

    public void afficherquellecarteJouer(){
        System.out.println("=====================");
        System.out.print("Quelle carte souhaitez-vous jouer ? ");
    }

    //FIX
    public void afficherdemanderevendiquerborne(){
        System.out.println("=====================");
        System.out.print("Shouaitez vous revendiquer une bornes ? ");
        System.out.println("Liste des bornes revendicables :");
        //for (int index : bornesRevendicables) {
        //    System.out.println(index + 1 + ". Borne " + (index + 1));
        //}
        System.out.println("0. Non");
        System.out.print("Votre choix : ");       
    }

    public void afficherCarteJoueeSurBorne(Carte carte, int borneIndex) {
        nettoyerConsole();
        System.out.println("=================");
        System.out.println("Carte " + carte + " jouée sur Borne " + (borneIndex + 1));
        System.out.println("=================");
    }

    public void afficherCaptureBorne(Joueur joueur, int borneIndex) {
        nettoyerConsole(); // Nettoie l'affichage précédent
        System.out.println("===========================");
        System.out.println("🎉 Borne " + (borneIndex + 1) + " capturée par " + joueur.getPseudo() + " ! 🎉");
        System.out.println("===========================");
    }

    public void afficherFinTour(Joueur joueur) {
        nettoyerConsole(); // Nettoie l'affichage précédent
        System.out.println("===========================");
        System.out.println("🕒 Le tour de " + joueur.getPseudo() + " est terminé.");
        System.out.println("Préparation du tour suivant...");
        System.out.println("===========================");
    }

    public void afficherIATour(Joueur ia) {
        nettoyerConsole(); // Nettoie l'affichage précédent
        System.out.println("===========================");
        System.out.println("🤖 " + ia.getPseudo() + " est en train de jouer...");
        System.out.println("Veuillez patienter.");
        System.out.println("===========================");

    }

    public void afficherIARevendication(Joueur ia, int borneIndex, boolean succes) {
        nettoyerConsole();
        System.out.println("===========================");
        if (succes) {
            System.out.println("🤖 " + ia.getPseudo() + " a revendiqué avec succès la Borne " + (borneIndex + 1) + " !");
        } else {
            System.out.println("🤖 " + ia.getPseudo() + " a tenté de revendiquer la Borne " + (borneIndex + 1) + ", mais a échoué.");
        }
        System.out.println("===========================");
    }

    public void afficherEcranVictoire(List<Joueur> joueurs, Joueur joueurActuel, Joueur vainqueur, int variante) {
        nettoyerConsole(); // Nettoie l'affichage précédent

        switch (variante) {
            case 1: // Variante 1 : Vous avez gagné
                afficherVictoireJoueur(joueurActuel);
                break;
            case 2: // Variante 2 : Vous avez perdu
                afficherDefaiteJoueur(joueurActuel, vainqueur);
                break;
            case 3: // Variante 3 : Joueur X a gagné
                afficherVictoireAutre(vainqueur, joueurs);
                break;
            default:
                System.out.println("Variante inconnue. Aucun affichage de victoire.");
        }
    }

    private void afficherVictoireJoueur(Joueur joueurActuel) {
        System.out.println("==================================");
        System.out.println("🏆 FÉLICITATIONS ! 🏆");
        System.out.println("🎉 Vous avez gagné, " + joueurActuel.getPseudo() + " !");
        System.out.println("Score final : " + joueurActuel.getScore() + " points");
        System.out.println("==================================");
    }

    private void afficherDefaiteJoueur(Joueur joueurActuel, Joueur vainqueur) {
        System.out.println("==================================");
        System.out.println("😢 Vous avez perdu...");
        System.out.println("Le joueur " + vainqueur.getPseudo() + " remporte la victoire avec " + vainqueur.getScore() + " points.");
        System.out.println("Votre score : " + joueurActuel.getScore() + " points.");
        System.out.println("==================================");
    }

    private void afficherVictoireAutre(Joueur vainqueur, List<Joueur> joueurs) {
        System.out.println("==================================");
        System.out.println("🏆 VICTOIRE 🏆");
        System.out.println("🎖️ Le joueur " + vainqueur.getPseudo() + " a gagné avec " + vainqueur.getScore() + " points !");
        System.out.println("==================================");

        System.out.println("\nRésumé des scores :");
        for (Joueur joueur : joueurs) {
            System.out.println(joueur.getPseudo() + " : " + joueur.getScore() + " points");
        }
        System.out.println("\nMerci d'avoir joué !");
    }

            /**
     * Affiche une séquence dynamique pour le lancement de la partie.
     */
    public void afficherLancementPartie() {
        nettoyerConsole(); // Nettoie l'affichage précédent

        // Étape 1 : Préparation de la partie
        afficherMessageAvecPause("🔧 Préparation de la partie...", 1000);

        // Étape 2 : Placement des bornes
        afficherMessageAvecPause("📍 Placement des bornes...", 1000);

        // Étape 3 : Création de la pioche
        afficherMessageAvecPause("🎴 Création de la pioche...", 1000);

        // Étape 4 : Distribution des cartes
        afficherMessageAvecPause("🃏 Distribution des cartes...", 1000);

        // Étape 5 : Affûtage des épées
        afficherMessageAvecPause("⚔️ Affûtage des épées...", 1000);

        // Message final
        nettoyerConsole();
        System.out.println("===========================");
        System.out.println("🎮 La partie va commencer !");
        System.out.println("===========================");
        try {
            Thread.sleep(1500); // Pause finale pour que le message reste un instant
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void afficherMessagesansPause(String message) {
        System.out.println("===========================");
        System.out.println(message);
        System.out.println("===========================");

    }


    private void afficherMessageAvecPause(String message, int pauseDurée) {
        nettoyerConsole();
        System.out.println("===========================");
        System.out.println(message);
        System.out.println("===========================");
        try {
            Thread.sleep(pauseDurée); // Pause pour laisser le message affiché
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void afficherJoueurDebutTour(Joueur joueur, int numeroTour) {
        nettoyerConsole(); // Nettoie l'affichage précédent
        System.out.println("===========================");
        System.out.println("🕒 Début du tour n°" + numeroTour);
        System.out.println("🎮 C'est au tour de : " + joueur.getPseudo());
        System.out.println("===========================");
        try {
            Thread.sleep(2000); // Pause pour laisser le joueur lire le message
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    public void afficherBornesCaptureesParIA(List<Integer> bornesRevendiquees) {
        nettoyerConsole(); // Nettoie l'affichage précédent

        int decalage = 0; // Gère le décalage des "===" à chaque itération
        for (int borneIndex : bornesRevendiquees) {
            // Affiche les bordures décalées
            for (int i = 0; i < decalage; i++) {
                System.out.print(" ");
            }
            System.out.println("===========================");

            // Affiche le message de capture pour cette borne
            for (int i = 0; i < decalage; i++) {
                System.out.print(" ");
            }
            System.out.println("⚔️ Borne " + (borneIndex + 1) + " capturée par l'IA !");
            
            // Attente avant la prochaine itération
            try {
                Thread.sleep(1500); // Pause de 1.5 seconde
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            // Augmente le décalage pour le prochain affichage
            decalage += 2;
        }

        // Dernière bordure pour fermer l'affichage
        for (int i = 0; i < decalage; i++) {
            System.out.print(" ");
        }
        System.out.println("===========================");
    }

    public void afficherfermeturejeu() {
        System.out.println("===========================");
        System.out.println("Merci d'avoir joué & à bientot ! ");
        System.out.println("===========================");
    }

    
}
