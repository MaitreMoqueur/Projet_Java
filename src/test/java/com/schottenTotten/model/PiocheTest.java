package com.schottenTotten.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PiocheTest {

    private Pioche pioche;

    @BeforeEach
    public void setUp() {
        pioche = new Pioche();
    }

    @Test
    public void testConstructor() {
        assertEquals(54, pioche.getNombreDeCartes(), "La pioche doit contenir 54 cartes au départ.");
    }

    @Test
    public void testTirerCarte() {
        assertEquals(54, pioche.getNombreDeCartes(), "La pioche doit contenir 54 cartes au départ.");

        Carte carteTiree = pioche.tirerCarte();
        assertNotNull(carteTiree, "La carte tirée ne doit pas être nulle.");
        assertEquals(53, pioche.getNombreDeCartes(), "La pioche doit contenir 53 cartes après avoir tiré une carte.");
    }

    @Test
    public void testTirerCarteQuandVide() {
        for (int i = 0; i < 54; i++) {
            pioche.tirerCarte();
        }

        assertEquals(0, pioche.getNombreDeCartes(), "La pioche doit contenir 0 cartes après avoir tiré toutes les cartes.");

        Carte carteTiree = pioche.tirerCarte();
        assertNull(carteTiree, "La carte tirée doit être nulle lorsque la pioche est vide.");
    }

    @Test
    public void testGetNombreDeCartes() {
        assertEquals(54, pioche.getNombreDeCartes(), "La pioche doit contenir 54 cartes au départ.");

        pioche.tirerCarte();
        assertEquals(53, pioche.getNombreDeCartes(), "Le nombre de cartes restantes doit être 53 après avoir tiré une carte.");
    }
}
