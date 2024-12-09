package com.schottenTotten.view;

import com.schottenTotten.model.Joueur;

import java.util.List;
import java.util.Scanner;

public class ConsoleView {
    private final Scanner scanner;

    public ConsoleView() {
        this.scanner = new Scanner(System.in);
    }

    public void afficherTourJoueur(Joueur joueur) {
        System.out.println("\n=== Tour de " + joueur.getNom() + " ===");
    }

    public void afficherMessage(String message) {
        System.out.println(message);
    }

    public boolean demanderSiRevendication() {
        System.out.print("Voulez-vous tenter de revendiquer une borne ? (oui/non) : ");
        String choix = scanner.nextLine().trim().toLowerCase();
        return choix.equals("oui");
    }

    public int demanderBorne() {
        return demanderEntree("Entrez le numéro de la borne (1-9) : ", 1, 9);
    }

    public String demanderCarteAJouer() {
        System.out.print("Entrez la carte à jouer : ");
        return scanner.nextLine();
    }

    public void afficherVictoire(String nomGagnant) {
        System.out.println("\n=== Victoire ===");
        System.out.println("Le joueur " + nomGagnant + " a gagné !");
    }

    public void afficherFinPartie(List<Joueur> joueurs) {
        System.out.println("\n=== Fin de la partie ===");
        for (Joueur joueur : joueurs) {
            System.out.println(joueur.getNom() + " : " + joueur.getScore() + " points");
        }
        System.out.println("Merci d'avoir joué !");
    }

    private int demanderEntree(String prompt, int min, int max) {
        int choix;
        do {
            System.out.print(prompt);
            while (!scanner.hasNextInt()) {
                System.out.println("Entrée invalide. Veuillez entrer un chiffre.");
                scanner.next(); // Nettoie l'entrée non valide
            }
            choix = scanner.nextInt();
            scanner.nextLine(); // Consomme le retour à la ligne
            if (choix < min || choix > max) {
                System.out.println("Choix invalide. Veuillez réessayer.");
            }
        } while (choix < min || choix > max);
        return choix;
    }
}
