package com.company;

import lombok.*;

import java.util.ArrayList;

public @Data class MoveList implements java.io.Serializable {
    private ArrayList<Move> moves = new ArrayList<>();

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

        Move spark = new Move("Spark", "Electric", 65, 20, 100);
        moves.add(spark);
        Move thunder = new Move("Thunder", "Electric", 110, 10, 70);
        moves.add(thunder);
        Move thundershock = new Move("Thunder Shock", "Electric", 40, 30, 100);
        moves.add(thundershock);
        Move discharge = new Move("Discharge", "Electric", 80, 15, 100);
        moves.add(discharge);

        Move earthquake = new Move("Earthquake", "Ground", 100, 10, 100);
        moves.add(earthquake);
        Move mudshot = new Move("Mud Shot", "Ground", 55, 15, 95);
        moves.add(mudshot);
        Move mudbomb = new Move("Mud Bomb", "Ground", 65, 10, 85);
        moves.add(mudbomb);
        Move earthpower = new Move("Earth Power", "Ground", 90, 10, 100);
        moves.add(earthpower);

        Move acrobatics = new Move("Acrobatics", "Flying", 55, 15, 100);
        moves.add(acrobatics);
        Move airslash = new Move("Air Slash", "Flying", 75, 15, 95);
        moves.add(airslash);
        Move gust = new Move("Gust", "Flying", 40, 35, 100);
        moves.add(gust);
        Move hurricane = new Move("Hurricane", "Flying", 110, 10, 70);
        moves.add(hurricane);
    }
}
