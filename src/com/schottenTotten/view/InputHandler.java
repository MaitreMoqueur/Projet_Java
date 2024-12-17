package com.schottenTotten.view;

import java.util.Scanner;

public class InputHandler {

    public int recupererEntierUtilisateur() {
        Scanner scanner = new Scanner(System.in);
        int valeur = -1;
        boolean saisieValide = false;

        while (!saisieValide) {
            if (scanner.hasNextInt()) {
                valeur = scanner.nextInt();
                saisieValide = true;
            } else {
                System.out.println("❌ Erreur : Veuillez entrer un nombre entier valide.");
                scanner.next();
            }
        }

        return valeur;
    }
}
