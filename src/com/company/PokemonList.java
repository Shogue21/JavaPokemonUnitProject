package com.company;

import lombok.*;

import java.util.ArrayList;

public @Data class PokemonList implements java.io.Serializable {
    private ArrayList<Pokemon> pokemon = new ArrayList<>();


    public PokemonList() {
        Pokemon treeko = new Pokemon("Treeko", "Grass");
        pokemon.add(treeko);

        Pokemon turtwig = new Pokemon("Turtwig", "Grass");
        pokemon.add(turtwig);

        Pokemon bulbasaur = new Pokemon("Bulbasaur", "Grass");
        pokemon.add(bulbasaur);

        Pokemon chikorita = new Pokemon("Chikorita", "Grass");
        pokemon.add(chikorita);

        Pokemon torchic = new Pokemon("Torchic", "Fire");
        pokemon.add(torchic);

        Pokemon chimchar = new Pokemon("Chimchar", "Fire");
        pokemon.add(chimchar);

        Pokemon charmander = new Pokemon("Charmander", "Fire");
        pokemon.add(charmander);

        Pokemon cyndaquil = new Pokemon("Cyndaquil", "Fire");
        pokemon.add(cyndaquil);

        Pokemon mudkip = new Pokemon("Mudkip", "Water");
        pokemon.add(mudkip);

        Pokemon squirtle = new Pokemon ("Squirtle", "Water");
        pokemon.add(squirtle);

        Pokemon piplup = new Pokemon("Piplup", "Water");
        pokemon.add(piplup);

        Pokemon totodile = new Pokemon("Totodile", "Water");
        pokemon.add(totodile);

        Pokemon pikachu = new Pokemon("Pikachu", "Electric");
        pokemon.add(pikachu);

        Pokemon jolteon = new Pokemon("Jolteon", "Electric");
        pokemon.add(pikachu);

        Pokemon electabuzz = new Pokemon("Electabuzz", "Electric");
        pokemon.add(electabuzz);

        Pokemon luxray = new Pokemon("Luxray", "Electric");
        pokemon.add(luxray);

        Pokemon diglet = new Pokemon("Diglet", "Ground");
        pokemon.add(diglet);

        Pokemon drilbur = new Pokemon("Drilbur", "Ground");
        pokemon.add(drilbur);

        Pokemon phanphy = new Pokemon("Phanphy", "Ground");
        pokemon.add(phanphy);

        Pokemon sandshrew = new Pokemon("Sandshrew", "Ground");
        pokemon.add(sandshrew);

        Pokemon starly = new Pokemon("Starly", "Flying");
        pokemon.add(starly);

        Pokemon doduo = new Pokemon("Doduo", "Flying");
        pokemon.add(doduo);

        Pokemon pidgeotto = new Pokemon("Pidgeotto", "Flying");
        pokemon.add(pidgeotto);

        Pokemon spearow = new Pokemon("Spearow", "Flying");
        pokemon.add(spearow);
    }
}
