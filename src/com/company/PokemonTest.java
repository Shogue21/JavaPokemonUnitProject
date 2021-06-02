package com.company;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PokemonTest {
    Pokemon pokemon = new Pokemon("Totodile", "Water", 80, 80, 30, 30, 50);

    @Test
    void createPokemonTest() {
        assertEquals("Totodile", pokemon.getName(), "Pokemon name did not match expected name.");
        assertEquals("Water", pokemon.getType(), "Pokemon type did not match expected type.");
        assertEquals(80, pokemon.getHealth(), "Pokemon health did not match expected value.");
        assertEquals(80, pokemon.getMaxHealth(), "Pokemon max health did not match expected value.");
        assertEquals(30, pokemon.getAttack(), "Pokemon attack did not match expected value.");
        assertEquals(30, pokemon.getDefense(), "Pokemon defense did not match expected value.");
        assertEquals(50, pokemon.getSpeed(), "Pokemon speed did not match expected value.");
    }

    @Test
    void chooseRandomMoveTest() {
        assertTrue(pokemon.getMoveList().contains(pokemon.useRandomMove()));
    }
}
