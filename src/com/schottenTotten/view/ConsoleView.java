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

    public void afficherBornes(List<Borne> bornes) {
        nettoyerConsole(); 
        System.out.println("=== Bornes ===");
        for (int i = 0; i < bornes.size(); i++) {
            System.out.println("Borne " + (i + 1) + " :");
            bornes.get(i).afficherEtat();
        }
    }

    public void afficherMain(Joueur joueur) {
        nettoyerConsole();
        System.out.println("=== Main de " + joueur.getPseudo() + " ===");
        joueur.getMain().getCartes().forEach(System.out::println);
    }

    public void afficherScore(List<Joueur> joueurs) {
        nettoyerConsole();
        System.out.println("=== Scores ===");
        for (Joueur joueur : joueurs) {
            System.out.println(joueur.getPseudo() + " : " + joueur.getScore() + " points");
        }
    }

    public void afficherMessage(String message) {
        nettoyerConsole();
        System.out.println(message);
    }

    public void afficherTourJoueur(Joueur joueur) {
        nettoyerConsole();
        System.out.println("\n=== Tour de " + joueur.getPseudo() + " ===");
    }

    public void afficherCarteJouee(Joueur joueur, Carte carte) {
        System.out.println(joueur.getPseudo() + " a joué : " + carte);
    }

    public void afficherRevendication(Joueur joueur, int borneIndex, boolean success) {
        if (success) {
            System.out.println(joueur.getPseudo() + " a revendiqué la borne " + (borneIndex + 1) + " avec succès !");
        } else {
            System.out.println(joueur.getPseudo() + " a échoué à revendiquer la borne " + (borneIndex + 1) + ".");
        }
    }

    public void afficherVictoire(Joueur vainqueur) {
        nettoyerConsole();
        System.out.println("=== Victoire ===");
        System.out.println("Le joueur " + vainqueur.getPseudo() + " a gagné !");
    }

    public void afficherResultatsFinaux(List<Joueur> joueurs) {
        nettoyerConsole();
        System.out.println("=== Résultats finaux ===");
        for (Joueur joueur : joueurs) {
            System.out.println(joueur.getPseudo() + " : " + joueur.getScore() + " points");
        }
        System.out.println("Merci d'avoir joué !");
    }
}
