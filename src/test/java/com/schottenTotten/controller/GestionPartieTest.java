package main.java.com.schottenTotten.controller;

import main.java.com.schottenTotten.model.*;
import main.java.com.schottenTotten.view.ConsoleView;
import main.java.com.schottenTotten.ai.IA;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GestionPartieTest {
    private GestionPartie gestionPartie;
    private List<Joueur> joueurs;
    private List<Borne> bornes;
    private ConsoleView view;
    private Pioche pioche;

    @BeforeEach
    public void setUp() {
        view = mock(ConsoleView.class);

        pioche = new Pioche();

        Joueur joueur1 = new Joueur("Joueur1", pioche);
        IA joueurIA = new IA(1, "IA Facile", pioche);
        joueurs = new ArrayList<>();
        joueurs.add(joueur1);
        joueurs.add(joueurIA);

        bornes = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            bornes.add(new Borne("Joueur1", "IA", i));
        }

        gestionPartie = new GestionPartie(joueurs, bornes, view);
    }

    @Test
    public void testDemarrerPartie_VictoireJoueur1() {
        for (int i = 0; i < 5; i++) {
            bornes.get(i).revendiquer(joueurs.get(0));
        }

        gestionPartie.demarrerPartie(pioche);

        verify(view).afficherLancementPartie();
        verify(view).afficherEcranVictoire(joueurs.get(0), joueurs.get(1), 3);
        assertFalse(gestionPartie.verifierConditionVictoire() == null);
    }

    @Test
    public void testDemarrerPartie_VictoireIA() {
        bornes.get(0).revendiquer(joueurs.get(1));
        bornes.get(1).revendiquer(joueurs.get(1));
        bornes.get(2).revendiquer(joueurs.get(1));

        gestionPartie.demarrerPartie(pioche);

        verify(view).afficherLancementPartie();
        verify(view).afficherEcranVictoire(joueurs.get(1), joueurs.get(0), 3);
        assertFalse(gestionPartie.verifierConditionVictoire() == null);
    }

    @Test
    public void testFaireTourJoueurReel() {
        Joueur joueur = joueurs.get(0);
        joueur.remplirMain();

        InputHandler mockInputHandler = mock(InputHandler.class);
        when(mockInputHandler.demanderEntree(anyInt(), anyInt())).thenReturn(1);
        when(mockInputHandler.inputinlist(anyList())).thenReturn(0);

        gestionPartie.demarrerPartie(pioche);

        verify(view).afficherJoueurDebutTour(joueur, 0);
        verify(view).afficherEtatTour(0, joueur, joueurs, bornes);
        verify(view).afficherFinTour(joueur);
    }

    @Test
    public void testFaireTourIA() {
        IA joueurIA = (IA) joueurs.get(1);
        joueurIA.remplirMain();

        List<Integer> bornesJouables = gestionPartie.getlistbornesjouable(bornes, joueurIA);
        bornes.get(0).ajouterCarte(joueurIA, joueurIA.getMain().getCarte(0));
        bornes.get(0).ajouterCarte(joueurIA, joueurIA.getMain().getCarte(1));
        bornes.get(0).ajouterCarte(joueurIA, joueurIA.getMain().getCarte(2));

        gestionPartie.demarrerPartie(pioche);

        verify(view).afficherIATour(joueurIA);
        verify(view).afficherFinTour(joueurIA);
    }

    @Test
    public void testVerifierConditionVictoire_AucuneVictoire() {
        Joueur gagnant = gestionPartie.verifierConditionVictoire();
        assertNull(gagnant, "Aucun joueur ne doit être déclaré gagnant.");
    }

    @Test
    public void testVerifierConditionVictoire_Joueur1Gagne() {
        for (int i = 0; i < 5; i++) {
            bornes.get(i).revendiquer(joueurs.get(0));
        }

        Joueur gagnant = gestionPartie.verifierConditionVictoire();
        assertEquals(joueurs.get(0), gagnant);
    }

    @Test
    public void testVerifierConditionVictoire_IAGagne() {
        for (int i = 0; i < 3; i++) {
            bornes.get(i).revendiquer(joueurs.get(1));
        }

        Joueur gagnant = gestionPartie.verifierConditionVictoire();
        assertEquals(joueurs.get(1), gagnant);
    }
}
