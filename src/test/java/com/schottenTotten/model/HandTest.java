package com.schottenTotten.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HandTest {
    private Hand main;
    private Pioche pioche;

    @BeforeEach
    public void setUp() {
        pioche = new Pioche();
        main = new Hand(pioche);
    }

    @Test
    public void testConstructor() {
        assertEquals(6, main.getNombreCartes(), "La main doit contenir 6 cartes au départ.");
    }

    @Test
    public void testPiocherCarte() {
        int initialSize = main.getNombreCartes();
        main.piocherCarte(pioche);
        assertEquals(initialSize + 1, main.getNombreCartes(), "La main doit contenir une carte de plus après avoir pioché.");
    }

    @Test
    public void testRetirerCarte() {
        Carte carteRetiree = main.retirerCarte(0);

        assertEquals(5, main.getNombreCartes(), "La main doit contenir une carte de moins après avoir retiré une carte.");
        
        assertNotNull(carteRetiree, "La carte retirée ne doit pas être nulle.");
    }

    @Test
    public void testGetCarteValide() {
        Carte carte = main.getCarte(0);
        assertNotNull(carte, "La carte obtenue ne doit pas être nulle.");
    }

    @Test
    public void testGetCarteInvalide() {
        assertThrows(IndexOutOfBoundsException.class, () -> main.getCarte(10), "Un index invalide doit provoquer une exception.");
    }

    @Test
    public void testRetirerCarteInvalide() {
        assertThrows(IndexOutOfBoundsException.class, () -> main.retirerCarte(10), "Un index invalide doit provoquer une exception.");
    }

}
