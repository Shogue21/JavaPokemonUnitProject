package com.company;

import java.util.ArrayList;

public class MoveList implements java.io.Serializable {
    private ArrayList<Move> moves = new ArrayList<>();

    public ArrayList<Move> getMoves() {
        return moves;
    }

    public void setMoves(ArrayList<Move> moves) {
        this.moves = moves;
    }

    public MoveList(){
        Move razorLeaf = new Move("Razor Leaf", "Grass", 55, 25, 95);
        moves.add(razorLeaf);
        Move leechSeed = new Move("Vine Whip", "Grass", 45, 25, 100);
        moves.add(leechSeed);
        Move absorb = new Move("Absorb", "Grass", 20, 25, 100);
        moves.add(absorb);
        Move leafBlade = new Move("Leaf Blade", "Grass", 90, 15, 100);
        moves.add(leafBlade);

        Move cut = new Move("Cut", "Normal", 50, 30, 95);
        moves.add(cut);
        Move scratch = new Move("Scratch", "Normal", 30, 35, 100);
        moves.add(scratch);
        Move quickAttack = new Move("Quick Attack", "Normal", 40, 30, 100);
        moves.add(quickAttack);

        Move ember = new Move("Ember", "Fire", 20, 25, 100);
        moves.add(ember);
        Move flamethrower = new Move("Flamethrower", "Fire", 90, 15, 100);
        moves.add(flamethrower);
        Move flameWheel = new Move("Flame Wheel", "Fire", 60, 25, 100);
        moves.add(flameWheel);
        Move inferno = new Move("Inferno", "Fire", 100, 5, 50);
        moves.add(inferno);

        Move waterGun = new Move("Water Gun", "Water", 40, 25, 100);
        moves.add(waterGun);
        Move muddyWater = new Move("Muddy Water", "Water", 90, 10, 85);
        moves.add(muddyWater);
        Move surf = new Move("Surf", "Water", 90, 15, 100);
        moves.add(surf);
        Move bubbleBeam = new Move("Bubble Beam", "Water", 65, 20, 100);
        moves.add(bubbleBeam);
    }
}
