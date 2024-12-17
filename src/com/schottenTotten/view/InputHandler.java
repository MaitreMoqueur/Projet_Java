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

    public int demanderSiRevendication(){
        boolean reponse_valide = false;
        int valeur = -1;

        while(!reponse_valide){
            switch (recupererEntierUtilisateur()) {
                case 0: // OUI
                    reponse_valide = true;
                    valeur = 1;
                    break;
                case 1: // NON
                    reponse_valide = true;
                    valeur = 0;
                    break;
                default: // Erreur
                    System.out.println("❌ Erreur : Veuillez entrer une reponse valide.");
                    break;
            }
        }
        return valeur;
    }

    public int jouerCarteReel(Joueur Joueur){
        view.afficherquellecarteJouer();
        recupererEntierUtilisateur();

          
    }


}
