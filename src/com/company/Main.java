package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<Move> allMoves = new ArrayList<>();
    static ArrayList<Pokemon> allPokemon = new ArrayList<>();
    static ArrayList<Pokemon> userTeam = new ArrayList<>();
    static ArrayList<Pokemon> comTeam = new ArrayList<>();

    static Boolean leeching = false;
    static Pokemon currentUserPokemon;
    static Pokemon currentComPokemon;

    static void assignRandomMoves(Pokemon pokemon) {
        while (pokemon.moveList.size() != 4) {
            int randomIndex = (int) Math.floor(Math.random() * allMoves.size());
            Move randomMove = allMoves.get(randomIndex);
            if ((randomMove.type.equalsIgnoreCase(pokemon.type) || randomMove.type.equalsIgnoreCase("normal")) && ! pokemon.moveList.contains(randomMove)) {
                pokemon.moveList.add(randomMove);
            }
        }
    }

    static double checkEffectiveness(String attacker, String defender) {
        switch (attacker) {
            case "fire":
                switch (defender) {
                    case "fire":
                    case "water":
                        return 0.5;
                    case "grass":
                        return 2.0;
                    case "normal":
                        return 1.0;
                }
            case "water":
                switch (defender) {
                    case "fire":
                        return 2.0;
                    case "grass":
                    case "water":
                        return 0.5;
                    case "normal":
                        return 1.0;
                }
            case "grass":
                switch (defender) {
                    case "fire":
                    case "grass":
                        return 0.5;
                    case "water":
                        return 2.0;
                    case "normal":
                        return 1.0;
                }
        }
        return 1.0;
    }

    static void dealDamage(Pokemon pokemon, Move move, String attacker) {
        if (move.name.equalsIgnoreCase("leech seed")) {
            leeching = true;
        } else {
            if (attacker.equalsIgnoreCase("computer")) {
                pokemon.health -= ((12 * move.power * pokemon.attack / pokemon.defense) / 50) * checkEffectiveness(currentComPokemon.type, currentUserPokemon.type);
            } else if (attacker.equalsIgnoreCase("user")) {
                pokemon.health -= ((12 * move.power * pokemon.attack / pokemon.defense) / 50) * checkEffectiveness(currentUserPokemon.type, currentComPokemon.type);
            }
        }
        if (leeching) {
            pokemon.health -= pokemon.health * .0625;
        }
        move.PP -= 1;
        if (pokemon.health <= 0) {
            pokemon.health = 0;
        }
    }

    static void displayAllPokemon() {
        for (Pokemon pokemon : allPokemon) {
            System.out.println(pokemon.name);
        }
    }

    static void createUserTeam() {
        while (userTeam.size() < 6) {
            Scanner keyboard = new Scanner(System.in);
            System.out.print("What pokemon are you adding to your team?\n> ");
            String pokemonName = keyboard.next();
            keyboard.nextLine();
            for (Pokemon pokemon : allPokemon) {
                if (pokemon.name.equalsIgnoreCase(pokemonName) && !userTeam.contains(pokemon)) {
                    userTeam.add(pokemon);
                }
            }
        }
    }

    static void createComTeam() {
        while (comTeam.size() < 6) {
            int randomIndex = (int) Math.floor(Math.random() * allPokemon.size());
            Pokemon randomPokemon = allPokemon.get(randomIndex);
            if (! userTeam.contains(randomPokemon)) {
                comTeam.add(randomPokemon);
            }
        }
    }

    static void updateScreen() {
        System.out.printf("\t\t\t\t%s\n\t\t\t\t%s/%s\n", currentComPokemon.name, currentComPokemon.health, currentComPokemon.maxHealth);
        System.out.printf("\n\n\n%s\n%s/%s\n", currentUserPokemon.name, currentUserPokemon.health, currentUserPokemon.maxHealth);
    }

    static void swapUserPokemon() {
        Scanner keyboard = new Scanner(System.in);
        boolean validPokemon = false;
        while (true) {
            System.out.print("What pokemon are you swapping to?\n> ");
            String pokemonToSwapTo = keyboard.next();
            keyboard.nextLine();
            for (Pokemon pokemon : userTeam) {
                if (pokemon.name.equalsIgnoreCase(pokemonToSwapTo) && !currentUserPokemon.name.equalsIgnoreCase(pokemonToSwapTo) && pokemon.health != 0) {
                    currentUserPokemon = pokemon;
                    validPokemon = true;
                    break;
                }
            }
            if (validPokemon) {
                break;
            }
            System.out.println("You can not swap to that pokemon! Try another.");
        }
    }

    static void swapComPokemon() {
        while (true) {
            System.out.println("Boo");
            int randomIndex = (int) Math.floor(Math.random() * comTeam.size());
            Pokemon randomPokemon = comTeam.get(randomIndex);
            if (randomPokemon.health != 0) {
                currentComPokemon = randomPokemon;
                break;
            }
        }
    }

    static Boolean postTurnsCheck(ArrayList<Pokemon> team) {
        return team.stream().anyMatch(p->p.health>0);
    }

    public static void main(String[] args) {

        Move razorLeaf = new Move("Razor Leaf", "Grass", 55, 25, 95);
        allMoves.add(razorLeaf);
        Move leechSeed = new Move("Leech Seed", "Grass", 0, 10, 90);
        allMoves.add(leechSeed);
        Move absorb = new Move("Absorb", "Grass", 20, 25, 100);
        allMoves.add(absorb);
        Move leafBlade = new Move("Leaf Blade", "Grass", 90, 15, 100);
        allMoves.add(leafBlade);

        Move cut = new Move("Cut", "Normal", 50, 30, 95);
        allMoves.add(cut);
        Move scratch = new Move("Scratch", "Normal", 30, 35, 100);
        allMoves.add(scratch);
        Move quickAttack = new Move("Quick Attack", "Normal", 40, 30, 100);
        allMoves.add(quickAttack);

        Move ember = new Move("Ember", "Fire", 20, 25, 100);
        allMoves.add(ember);
        Move flamethrower = new Move("Flamethrower", "Fire", 90, 15, 100);
        allMoves.add(flamethrower);
        Move flameWheel = new Move("Flame Wheel", "Fire", 60, 25, 100);
        allMoves.add(flameWheel);
        Move inferno = new Move("Inferno", "Fire", 100, 5, 50);
        allMoves.add(inferno);

        Move waterGun = new Move("Water Gun", "Water", 40, 25, 100);
        allMoves.add(waterGun);
        Move muddyWater = new Move("Muddy Water", "Water", 90, 10, 85);
        allMoves.add(muddyWater);
        Move surf = new Move("Surf", "Water", 90, 15, 100);
        allMoves.add(surf);
        Move bubbleBeam = new Move("Bubble Beam", "Water", 65, 20, 100);
        allMoves.add(bubbleBeam);


        Pokemon treeko = new Pokemon("Treeko", "Grass");
        assignRandomMoves(treeko);
        allPokemon.add(treeko);

        Pokemon turtwig = new Pokemon("Turtwig", "Grass");
        assignRandomMoves(turtwig);
        allPokemon.add(turtwig);

        Pokemon bulbasaur = new Pokemon("Bulbasaur", "Grass");
        assignRandomMoves(bulbasaur);
        allPokemon.add(bulbasaur);

        Pokemon chikorita = new Pokemon("Chikorita", "Grass");
        assignRandomMoves(chikorita);
        allPokemon.add(chikorita);

        Pokemon torchic = new Pokemon("Torchic", "Fire");
        assignRandomMoves(torchic);
        allPokemon.add(torchic);

        Pokemon chimchar = new Pokemon("Chimchar", "Fire");
        assignRandomMoves(chimchar);
        allPokemon.add(chimchar);

        Pokemon charmander = new Pokemon("Charmander", "Fire");
        assignRandomMoves(charmander);
        allPokemon.add(charmander);

        Pokemon cyndaquil = new Pokemon("Cyndaquil", "Fire");
        assignRandomMoves(cyndaquil);
        allPokemon.add(cyndaquil);

        Pokemon mudkip = new Pokemon("Mudkip", "Water");
        assignRandomMoves(mudkip);
        allPokemon.add(mudkip);

        Pokemon squirtle = new Pokemon ("Squirtle", "Water");
        assignRandomMoves(squirtle);
        allPokemon.add(squirtle);

        Pokemon piplup = new Pokemon("Piplup", "Water");
        assignRandomMoves(piplup);
        allPokemon.add(piplup);

        Pokemon totodile = new Pokemon("Totodile", "Water");
        assignRandomMoves(totodile);
        allPokemon.add(totodile);

        Scanner keyboard = new Scanner(System.in);
        System.out.println("Choose 6 Pokemon to be on your team.");
        displayAllPokemon();

        createUserTeam();
        currentUserPokemon = userTeam.get(0);
        for (Pokemon pokemon: userTeam) {
            System.out.println(pokemon);
        }
        createComTeam();
        currentComPokemon = comTeam.get(0);

        updateScreen();
        while (true) {
            System.out.print("What would you like to do?\n>Fight\t>Swap Pokemon\n> ");
            String input = keyboard.next();
            keyboard.nextLine();
            if (input.equalsIgnoreCase("fight")) {
                currentUserPokemon.showMoveSet();
                System.out.print("What move are you using?\n> ");
                String moveToUse = keyboard.nextLine();
                dealDamage(currentComPokemon, currentUserPokemon.useMove(moveToUse), "computer");
                if (! postTurnsCheck(comTeam)) {
                    System.out.println("Computer is out of usable pokemon!");
                    System.out.println("You win!");
                    break;
                }
                if (currentComPokemon.health == 0) {
                    System.out.printf("%s fainted!", currentComPokemon.name);
                    swapComPokemon();
                }
                updateScreen();
            } else if (input.equalsIgnoreCase("swap")) {
                swapUserPokemon();
            }
            dealDamage(currentUserPokemon, currentComPokemon.useRandomMove(), "user");
            if (! postTurnsCheck(userTeam)) {
                System.out.println("User is out of usable pokemon!");
                System.out.println("User blacked out!");
                break;
            }
            if (currentUserPokemon.health == 0) {
                System.out.printf("%s fainted! Choose another pokemon!\n", currentUserPokemon.name);
                swapUserPokemon();
            }
            updateScreen();
            if (input.equalsIgnoreCase("quit")) {
                break;
            }
        }
    }
}