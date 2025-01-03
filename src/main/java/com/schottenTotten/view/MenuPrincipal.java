package main.java.com.schottenTotten.view;

import java.util.Scanner;

import main.java.com.schottenTotten.model.Variante;

import java.util.List;

public class MenuPrincipal {
    private boolean settingsChoisis;
    private int modeDeJeu;       // 1 = Spectateur, 2 = Joueur vs IA, 3 = Joueur vs Joueur
    private int difficulteIA;    // 1 = Facile, 2 = Moyen, 3 = Difficile
    private int difficulteIA2;   // Difficulté de la deuxième IA, utilisée uniquement pour le mode Spectateur
    private Variante variante;        // 1 = Classique, 2 = Tactique
    private String pseudoJ1;
    private String pseudoJ2;
    Scanner scanner = new Scanner(System.in);
    
    public MenuPrincipal() {
        this.settingsChoisis = false; 
        this.modeDeJeu = -1;          
        this.difficulteIA = -1;       
        this.variante = Variante.CLASSIQUE;
        this.pseudoJ1 = "";
        this.pseudoJ2 = "";

    }

    public boolean gererMenu() {
        boolean continuerProgramme = true;

        while (continuerProgramme) {
            ConsoleView.nettoyerConsole();
            afficherMenu();
            System.out.print("Votre choix : ");
            int choix = scanner.nextInt();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Le sommeil a été interrompu !");
            }
            
            ConsoleView.nettoyerConsole();

            switch (choix) {
                case 1:
                    if (settingsChoisis) {
                        System.out.println("Lancement de la partie...");
                        return true;
                    } else {
                        System.out.println("Vous devez d'abord configurer les paramètres du jeu !");
                        System.out.println("Appuyer sur n'importe quelle touche pour revenir au menu");
                        if (scanner.hasNextLine()) {
                            scanner.nextLine();
                        }
                        scanner.nextLine();                       
                    }
                    break;
                case 2:
                    configurerSettings(scanner);
                    break;

                case 3:
                    afficherRegles();
                    break;
                case 4:
                    afficherCredits();
                    break;
                case 5:
                    System.out.println("Merci d'avoir joué ! À bientôt !");
                    continuerProgramme = false;
                    return false;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
                    System.out.println("Appuyer sur n'importe quelle touche pour revenir au menu");
                    if (scanner.hasNextLine()) {
                        scanner.nextLine();
                    }
                    scanner.nextLine();
            }
        }
        scanner.close();
        return false;
    }

    private void afficherMenu() {
        System.out.println("\n=== Menu Principal ===");
        System.out.println("1. Jouer une partie");
        System.out.println("2. Configurer les paramètres");
        System.out.println("3. Voir les règles");
        System.out.println("4. Crédits");
        System.out.println("5. Quitter");
        System.out.println("======================");
    }

    private void afficherRegles() {
        System.out.println("\n=== Règles du Jeu ===");
        System.out.println("Le but du jeu est de remporter un maximum de bornes.");
        System.out.println("Jouez vos cartes en formant des combinaisons gagnantes.");
        System.out.println("Le premier joueur à remporter 5 bornes ou 3 bornes consécutives gagne !");
        System.out.println("=====================");
        System.out.println("Appuyer sur n'importe quelle touche pour revenir au menu");
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
        scanner.nextLine();
    }

    private void afficherCredits() {
        System.out.println("\n=== Crédits ===");
        System.out.println("Développé par : Philippe MOCQUERY & Florent MONANGE");
        System.out.println("=================");
        System.out.println("Appuyer sur n'importe quelle touche pour revenir au menu");
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
        scanner.nextLine();
    }

    private void configurerSettings(Scanner scanner) {
        System.out.println("\n=== Configuration des paramètres ===");

        // Choix du mode de jeu
        System.out.println("Choisissez le mode de jeu :");
        System.out.println("1. Spectateur (IA vs IA)");
        System.out.println("2. Joueur vs IA");
        System.out.println("3. Joueur vs Joueur");
        modeDeJeu = demanderEntree(scanner, 1, 3);
        ConsoleView.nettoyerConsole();

        // Choix de la difficulté si IA est impliquée
        if (modeDeJeu == 1) {
            System.out.println("Choisissez la difficulté de la première IA :");
            System.out.println("1. Facile (aléatoire)");
            System.out.println("2. Moyen (début de réflexion)");
            difficulteIA = demanderEntree(scanner, 1, 2);
            ConsoleView.nettoyerConsole();

            System.out.println("Choisissez la difficulté de la deuxième IA :");
            System.out.println("1. Facile (aléatoire)");
            System.out.println("2. Moyen (début de réflexion)");
            difficulteIA2 = demanderEntree(scanner, 1, 2);
            ConsoleView.nettoyerConsole();

        } else if (modeDeJeu == 2) {
            System.out.println("Choisissez la difficulté de l'IA :");
            System.out.println("1. Facile (aléatoire)");
            System.out.println("2. Moyen (début de réflexion)");
            difficulteIA = demanderEntree(scanner, 1, 2);
            difficulteIA2 = -1; // Pas de deuxième IA
            ConsoleView.nettoyerConsole();
        } else {
            difficulteIA = -1; // Pas d'IA
            difficulteIA2 = -1; // Pas de deuxième IA
        }

        // Choix de la variante
        System.out.println("Choisissez la variante :");
        System.out.println("1. Classique");
        System.out.println("2. Tactique (non implémentée)");
        System.out.println("3. Expert (non implémentée)");
        int choix = demanderEntree(scanner, 1, 3);
        switch (choix) {
            case 1:
                variante = Variante.CLASSIQUE;
                break;
            case 2:
                variante = Variante.CLASSIQUE; //Pour rester sur la variante classique
                break;
            case 3:
                variante = Variante.CLASSIQUE; //Pour rester sur la variante classique
                break;
        }
        ConsoleView.nettoyerConsole();

        // Choix des pseudos 
        pseudoJ1 = demanderPseudo(scanner, "Joueur 1");
        pseudoJ2 = demanderPseudo(scanner, "Joueur 2");

        settingsChoisis = true; // Les paramètres sont configurés
        System.out.println("Les paramètres du jeu ont été configurés avec succès !");
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Le sommeil a été interrompu !");
        }
    }

    private int demanderEntree(Scanner scanner, int min, int max) {
        int choix;
        do {
            System.out.print("Votre choix (" + min + "-" + max + ") : ");
            while (!scanner.hasNextInt()) {
                System.out.println("Entrée invalide. Veuillez entrer un chiffre.");
                scanner.next();
            }
            choix = scanner.nextInt();
            if (choix < min || choix > max) {
                System.out.println("Choix invalide. Veuillez réessayer.");
            }
        } while (choix < min || choix > max);
        return choix;
    }
    
    public static String demanderPseudo(Scanner scanner, String nomJoueur) {
        String pseudo;
        boolean entreeValide = false;

        do {
            System.out.println("Choisissez le pseudo de " + nomJoueur + " : ");
            pseudo = scanner.nextLine().trim();

            if (pseudo.isEmpty()) {
                System.out.println("❌ Erreur : Le pseudo ne peut pas être vide. Veuillez réessayer.");
            } else {
                entreeValide = true;
            }
        } while (!entreeValide);

        return pseudo;
    }

    public int getModeDeJeu() {
        return modeDeJeu;
    }

    public int getDifficulteIA() {
        return difficulteIA;
    }

    public Variante getVariante() {
        return variante;
    }

    public int getDifficulteIA2() {
        return difficulteIA2;
    }
    public String getPseudoJ1() {
        return pseudoJ1;
    }
    public String getPseudoJ2() {
        return pseudoJ2;
    }
}
