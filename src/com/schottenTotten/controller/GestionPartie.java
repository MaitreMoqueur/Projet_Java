package com.schottenTotten.controller;

import java.util.List;

import com.schottenTotten.model.*;
import com.schottenTotten.view.ConsoleView;
import com.schottenTotten.ai.*;

public class GestionPartie {
    private final List<Joueur> liste_joueurs;
    private final List<Borne> liste_bornes;
    private final ConsoleView view;
    private boolean partieEnCours;
    private int numeroTour;

    public GestionPartie(List<Joueur> joueurs, List<Borne> bornes, ConsoleView view) {
        this.joueurs = joueurs;
        this.liuste_bornes = bornes;
        this.view = view;
        this.partieEnCours = true;
    }

    public void demarrerPartie() {
        view.afficherLancementPartie();
        while (partieEnCours) {
            for (Joueur joueur : liste_joueurs) {
                faireTourJoueur(joueur);
                Joueur gagnant = verifierConditionVictoire();
                if (gagnant != null) {
                    partieEnCours = false;
                    break;
                }
            }
            numeroTour++;
        }
        annoncerFinPartie();
    }

    private void faireTourJoueur(Joueur joueur) {
        if (joueur instanceof IA) {
            faireTourIA((IA) joueur);
        } else {
            faireTourJoueurReel(joueur);
        }
    }

    private void faireTourIA(IA joueurIA) {
        view.afficherIATour(joueurIA);
        //Jouer carte IA
        Carte carte = joueurIA.jouerCarte();
        view.afficherCarteJoueeSurBorne(carte, borne);
        //faire tour IA
        view.afficherBornesCaptureesParIA(bornescapturees);

        view.afficherFinTour(joueurIA);
    }

    private void faireTourJoueurReel(Joueur joueurReel) {
        view.afficherJoueurDebutTour(joueurReel, numeroTour);
        view.afficherEtatTour(numeroTour, joueurReel, liste_bornes);
        view.afficherquellecarteJouer();                                                                                                                                                                                                                                                                                                         
        joueurReel.jouerCarte();
        //afficher carte jouée dans jouer.carte
        boolean continuer = true;
        while (continuer) {
            view.afficherEtatTour(numeroTour, joueurReel, liste_bornes);
            view.afficherdemanderevendiquerborne();
            if (view.demanderSiRevendication()) {
                int borne = view.demanderBorne();
                view.revendiquerBorne(joueurReel, borne);
                //afficher message adequate dans revendiquerBorne
            } else {
                continuer = false;                                      
            }
        }

        joueurReel.piocherCarte();
        //afficher la cartepiochée dans piocher carte;
        view.afficherFinTour(joueurReel);
    }


    private void annoncerFinPartie() {
        view.afficherEcranVictoire();
        // relancer ?
        // fermer jeu
        // Menu 
    }

    public Joueur verifierConditionVictoire() {
        int bornesCapturéesJ1 = 0;
        int bornesCapturéesJ2 = 0;
        int bornesConsecutivesJ1 = 0;
        int bornesConsecutivesJ2 = 0;
        int maxBornesConsecutivesJ1 = 0;
        int maxBornesConsecutivesJ2 = 0;

        for (Borne borne : liste_bornes) {
            // Vérifie si la borne est capturée par le joueur 1
            if (borne.getEtat() == Borne.Etat.CAPTUREE_J1) {
                bornesCapturéesJ1++;
                bornesConsecutivesJ1++;
                maxBornesConsecutivesJ1 = Math.max(maxBornesConsecutivesJ1, bornesConsecutivesJ1);
                bornesConsecutivesJ2 = 0; // Réinitialise pour joueur 2
            } 
            // Vérifie si la borne est capturée par le joueur 2
            else if (borne.getEtat() == Borne.Etat.CAPTUREE_J2) {
                bornesCapturéesJ2++;
                bornesConsecutivesJ2++;
                maxBornesConsecutivesJ2 = Math.max(maxBornesConsecutivesJ2, bornesConsecutivesJ2);
                bornesConsecutivesJ1 = 0; // Réinitialise pour joueur 1
            } 
            // Aucun joueur ne capture la borne
            else {
                bornesConsecutivesJ1 = 0;
                bornesConsecutivesJ2 = 0;
            }
        }

        // Vérifie les conditions de victoire
        if (bornesCapturéesJ1 >= 5 || maxBornesConsecutivesJ1 >= 3) {
            view.afficherEcranVictoire(liste_joueurs, liste_joueurs.get(0), null, numeroTour);
            return liste_joueurs.get(0);
        }
        if (bornesCapturéesJ2 >= 5 || maxBornesConsecutivesJ2 >= 3) {
            view.afficherEcranVictoire(liste_joueurs, liste_joueurs.get(1), null, numeroTour);
            return liste_joueurs.get(1);
        }

        return null; // Aucun joueur ne remplit les conditions de victoire
    }
}
