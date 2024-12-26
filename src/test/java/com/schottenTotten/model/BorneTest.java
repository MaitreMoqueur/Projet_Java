package com.schottenTotten.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class BorneTest {

    private Joueur joueur1;
    private Joueur joueur2;
    private Pioche pioche;
    private Borne borne;

    @BeforeEach
    public void setUp() {
        pioche = new Pioche();
        joueur1 = new Joueur("Player1", pioche);
        joueur2 = new Joueur("Player2", pioche);
        borne = new Borne("Player1", "Player2", 1);
    }

    @Test
    public void testAjouterCarteJoueur1() {
        Carte carte = pioche.tirerCarte();
        assertNotNull(carte, "La carte tirée ne doit pas être nulle.");
        
        borne.ajouterCarte(joueur1, carte);
        assertTrue(borne.getList(joueur1).contains(carte), "La carte du joueur 1 doit être présente sur la borne.");
    }

    @Test
    public void testAjouterCarteJoueur2() {
        Carte carte = pioche.tirerCarte();
        assertNotNull(carte, "La carte tirée ne doit pas être nulle.");
        
        borne.ajouterCarte(joueur2, carte);
        assertTrue(borne.getList(joueur2).contains(carte), "La carte du joueur 2 doit être présente sur la borne.");
    }

    @Test
    public void testRevendiquerBorneJoueur1() {
        Carte carte1 = pioche.tirerCarte();
        Carte carte2 = pioche.tirerCarte();
        Carte carte3 = pioche.tirerCarte();

        borne.ajouterCarte(joueur1, carte1);
        borne.ajouterCarte(joueur1, carte2);
        borne.ajouterCarte(joueur1, carte3);

        Carte carte4 = pioche.tirerCarte();
        Carte carte5 = pioche.tirerCarte();
        Carte carte6 = pioche.tirerCarte();

        borne.ajouterCarte(joueur2, carte4);
        borne.ajouterCarte(joueur2, carte5);
        borne.ajouterCarte(joueur2, carte6);

        boolean revendication = borne.revendiquer(joueur1);

        assertTrue(revendication, "Le joueur 1 devrait pouvoir revendiquer la borne.");
        assertEquals(Borne.Etat.CAPTUREE_J1, borne.getEtat(), "L'état de la borne doit être CAPTUREE_J1.");
    }

    @Test
    public void testRevendiquerBorneJoueur2() {
        Carte carte1 = pioche.tirerCarte();
        Carte carte2 = pioche.tirerCarte();
        Carte carte3 = pioche.tirerCarte();

        borne.ajouterCarte(joueur1, carte1);
        borne.ajouterCarte(joueur1, carte2);
        borne.ajouterCarte(joueur1, carte3);

        Carte carte4 = pioche.tirerCarte();
        Carte carte5 = pioche.tirerCarte();
        Carte carte6 = pioche.tirerCarte();

        borne.ajouterCarte(joueur2, carte4);
        borne.ajouterCarte(joueur2, carte5);
        borne.ajouterCarte(joueur2, carte6);

        boolean revendication = borne.revendiquer(joueur2);

        assertTrue(revendication, "Le joueur 2 devrait pouvoir revendiquer la borne.");
        assertEquals(Borne.Etat.CAPTUREE_J2, borne.getEtat(), "L'état de la borne doit être CAPTUREE_J2.");
    }

    @Test
    public void testRevendiquerBornePasAssezDeCartes() {
        Carte carte1 = pioche.tirerCarte();
        Carte carte2 = pioche.tirerCarte();

        borne.ajouterCarte(joueur1, carte1);
        borne.ajouterCarte(joueur1, carte2);

        Carte carte3 = pioche.tirerCarte();
        Carte carte4 = pioche.tirerCarte();
        Carte carte5 = pioche.tirerCarte();

        borne.ajouterCarte(joueur2, carte3);
        borne.ajouterCarte(joueur2, carte4);
        borne.ajouterCarte(joueur2, carte5);

        boolean revendication = borne.revendiquer(joueur1);

        assertFalse(revendication, "Le joueur 1 ne devrait pas pouvoir revendiquer la borne.");
        assertEquals(Borne.Etat.LIBRE, borne.getEtat(), "L'état de la borne devrait être LIBRE.");
    }
}
