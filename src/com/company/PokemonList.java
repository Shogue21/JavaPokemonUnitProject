package com.company;

import java.util.ArrayList;

public class PokemonList implements java.io.Serializable {
    private ArrayList<Pokemon> pokemon = new ArrayList<>();
    private MoveList moveList = new MoveList();

    public MoveList getMoveList() {
        return moveList;
    }

    public void setMoveList(MoveList moveList) {
        this.moveList = moveList;
    }

    public ArrayList<Pokemon> getPokemon() {
        return pokemon;
    }

    public void setPokemon(ArrayList<Pokemon> pokemon) {
        this.pokemon = pokemon;
    }

    void assignRandomMoves(Pokemon pokemon) {
        while (pokemon.getMoveList().size() != 4) {
            int randomIndex = (int) Math.floor(Math.random() * moveList.getMoves().size());
            Move randomMove = moveList.getMoves().get(randomIndex);
            Move newMove = new Move(randomMove.getName(), randomMove.getType(), randomMove.getPower(), randomMove.getMaxPP(), randomMove.getAccuracy());
            if ((newMove.getType().equalsIgnoreCase(pokemon.getType()) || newMove.getType().equalsIgnoreCase("normal")) && pokemon.getMoveList().stream().noneMatch(p->p.getName().equalsIgnoreCase(newMove.getName()))) {
                pokemon.getMoveList().add(newMove);
            }
        }
    }

    public PokemonList() {
        Pokemon treeko = new Pokemon("Treeko", "Grass");
        assignRandomMoves(treeko);
        pokemon.add(treeko);

        Pokemon turtwig = new Pokemon("Turtwig", "Grass");
        assignRandomMoves(turtwig);
        pokemon.add(turtwig);

        Pokemon bulbasaur = new Pokemon("Bulbasaur", "Grass");
        assignRandomMoves(bulbasaur);
        pokemon.add(bulbasaur);

        Pokemon chikorita = new Pokemon("Chikorita", "Grass");
        assignRandomMoves(chikorita);
        pokemon.add(chikorita);

        Pokemon torchic = new Pokemon("Torchic", "Fire");
        assignRandomMoves(torchic);
        pokemon.add(torchic);

        Pokemon chimchar = new Pokemon("Chimchar", "Fire");
        assignRandomMoves(chimchar);
        pokemon.add(chimchar);

        Pokemon charmander = new Pokemon("Charmander", "Fire");
        assignRandomMoves(charmander);
        pokemon.add(charmander);

        Pokemon cyndaquil = new Pokemon("Cyndaquil", "Fire");
        assignRandomMoves(cyndaquil);
        pokemon.add(cyndaquil);

        Pokemon mudkip = new Pokemon("Mudkip", "Water");
        assignRandomMoves(mudkip);
        pokemon.add(mudkip);

        Pokemon squirtle = new Pokemon ("Squirtle", "Water");
        assignRandomMoves(squirtle);
        pokemon.add(squirtle);

        Pokemon piplup = new Pokemon("Piplup", "Water");
        assignRandomMoves(piplup);
        pokemon.add(piplup);

        Pokemon totodile = new Pokemon("Totodile", "Water");
        assignRandomMoves(totodile);
        pokemon.add(totodile);
    }
}
