package com.schottenTotten.view;

import java.util.Scanner;

public class Menu {
    public static void afficherMenuPrincipal() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("=== Schotten-Totten ===");
            System.out.println("1. Jouer");
            System.out.println("2. Réglages");
            System.out.println("3. Historique");
            System.out.println("4. Crédits");
            System.out.println("5. Quitter");
            System.out.print("Choisissez une option : ");

            String choix = scanner.nextLine();
            switch (choix) {
                case "1":
                    System.out.println("Lancement du jeu...");
                    // lancer une partie

                    break;
                case "2":
                    System.out.println("Les réglages sont parfaits, inutile de les modifier !");
                    break;
                case "3":
                    System.out.println("Historique désactivé !");
                    break;
                case "4":
                    System.out.println("Créé par Mocquery Philippe & Flo !");
                    break;
                case "5":
                    System.out.println("Au revoir !");
                    scanner.close();
                    return;
                default:
                    System.out.println("Option invalide. Réessayez.");
            }
        }
    }
}
