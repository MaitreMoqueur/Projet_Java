package com.schottenTotten.view;

import com.schottenTotten.model.Borne;
import com.schottenTotten.model.Carte;
import com.schottenTotten.model.Joueur;

import java.util.List;

import java.util.Scanner;

public class InputHandler {

    public static int recupererEntierUtilisateur() {
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
//FIX mOI
    public static int inputinlist(List<Integer> list){
        boolean reponse_valide = false;
        int valeur = -1;

        while(!reponse_valide){
            int input = recupererEntierUtilisateur();
            if (input == 0) { // NON
                reponse_valide = true;
                valeur = 0; // 0 indique qu'aucune borne n'est revendiquée
            } else if (list.contains(input)) { // Vérifie si la borne est dans la liste
                reponse_valide = true;
                valeur = input; // Renvoie la borne choisie
            } else { // Erreur
                System.out.println("❌ Erreur : La borne choisie n'est pas valide. Veuillez réessayer.");
            }
        }
       return valeur;
    }

    
    public static int demanderEntree(int min, int max) {
        Scanner scanner = new Scanner(System.in);
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

}
