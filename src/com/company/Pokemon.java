package com.company;

import java.util.ArrayList;

public class Pokemon implements java.io.Serializable {
    private String name;
    private String type;
    private int health;
    private int maxHealth;
    private int attack;
    private int defense;
    private int speed;
    private ArrayList<Move> moveList = new ArrayList<>();


    public Pokemon() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public ArrayList<Move> getMoveList() {
        return moveList;
    }

    public void setMoveList(ArrayList<Move> moveList) {
        this.moveList = moveList;
    }

    public Pokemon(String initName, String initType) {
        name = initName;
        type = initType;
        this.generateRandomStats();
    }

    public Pokemon(String loadedName, String loadedType, int loadedHealth, int loadedMaxHealth, int loadedAttack, int loadedDefense, int loadedSpeed){
        name = loadedName;
        type = loadedType;
        health = loadedHealth;
        maxHealth = loadedMaxHealth;
        attack = loadedAttack;
        defense = loadedDefense;
        speed = loadedSpeed;
    }
    public void showMoveSet() {
        for (Move i: moveList) {
            System.out.printf("%s\n\tType: %s\n\tPower: %s\n\tPP: %s/%s\n\tAccuracy: %s\n\n", i.getName(), i.getType(), i.getPower(), i.getPP(), i.getMaxPP(), i.getAccuracy());
        }
    }

    public Move useRandomMove() {
        while (true) {
            int randomIndex = (int) Math.floor(Math.random() * moveList.size());
            Move randomMove = moveList.get(randomIndex);
            if (randomMove.getPP() != 0) {
                System.out.printf("%s used %s!\n", this.name, randomMove.getName());
                return randomMove;
            } else if (moveList.stream().allMatch(p->p.getPP()==0)) {
                return new Move("Struggle", "Normal", 20, 1000, 100);
            }
        }
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
        speed = (int) Math.max(20, (Math.random() * (40 - 20) + 20));
    }
    public String toString() {
        return String.format("Pokemon: %s\n\tType: %s\n\tHealth: %s/%s\n\tAttack: %s\n\tDefense: %s\n", this.name, this.type, this.health, this.maxHealth, this.attack, this.defense);
    }
}
