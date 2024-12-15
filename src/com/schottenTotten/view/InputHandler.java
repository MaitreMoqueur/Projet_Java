package com.schottenTotten.view;

import java.util.Scanner;

public class InputHandler {

    /**
     * Récupère une valeur entière saisie par l'utilisateur.
     *
     * @param prompt Le message à afficher à l'utilisateur.
     * @return La valeur entière saisie par l'utilisateur.
     */
    public int recupererEntierUtilisateur(String prompt) {
        Scanner scanner = new Scanner(System.in);
        int valeur = -1;
        boolean saisieValide = false;

        while (!saisieValide) {
            System.out.print(prompt + " : ");
            if (scanner.hasNextInt()) {
                valeur = scanner.nextInt();
                saisieValide = true;
            } else {
                System.out.println("❌ Erreur : Veuillez entrer un nombre entier valide.");
                scanner.next(); // Nettoie l'entrée incorrecte
            }
        }

        return valeur;
    }
}
