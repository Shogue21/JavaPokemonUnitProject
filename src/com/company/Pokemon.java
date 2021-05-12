package com.company;

import java.util.ArrayList;

public class Pokemon {
    public String name;
    public String type;
    public int health;
    public int maxHealth;
    public int attack;
    public int defense;
    public ArrayList<Move> moveList = new ArrayList<>();

    public Pokemon(String initName, String initType) {
        name = initName;
        type = initType;
        this.generateRandomStats();
    }
    public void showMoveSet() {
        for (Move i: moveList) {
            System.out.printf("%s\n\tType: %s\n\tPower: %s\n\tPP: %s/%s\n\tAccuracy: %s\n\n", i.name, i.type, i.power, i.PP, i.MaxPP, i.accuracy);
        }
    }

    public Move useMove(String moveName) {
        Move usedMove = null;
        for (Move move: moveList) {
            if (move.name.equalsIgnoreCase(moveName)) {
                usedMove = move;
            }
        }
        return usedMove;
    }

    public Move useRandomMove() {
        int randomIndex = (int) Math.floor(Math.random() * moveList.size());
        Move randomMove = moveList.get(randomIndex);
        System.out.printf("%s used %s!\n", this.name, randomMove.name);
        return randomMove;
    }

    public void showPokemonInfo() {
        System.out.println("Pokemon: " + this.name);
        System.out.println("Health: " + this.health + "/" + this.maxHealth);
        System.out.println("Attack: " + this.attack);
        System.out.println("Defense: " + this.defense);
        this.showMoveSet();
    }

    public void generateRandomStats() {
        maxHealth = (int) Math.max(50, (Math.random() * (100 - 50) + 50));
        health = maxHealth;
        attack = (int) Math.max(20, (Math.random() * (40 - 20) + 20));
        defense = (int) Math.max(20, (Math.random() * (40 - 20) + 20));
    }
    public String toString() {
        return String.format("Pokemon: %s\n\tType: %s\n\tHealth: %s/%s\n\tAttack: %s\n\tDefense: %s\n", this.name, this.type, this.health, this.maxHealth, this.attack, this.defense);
    }
}
