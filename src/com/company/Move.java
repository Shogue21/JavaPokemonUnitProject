package com.company;

public class Move {
    String name;
    String type;
    int power;
    int PP;
    int MaxPP;
    int accuracy;

    public Move(String initName, String initType, int initPower, int initPP, int initAcc) {
        name = initName;
        type = initType;
        power = initPower;
        MaxPP = initPP;
        PP = MaxPP;
        accuracy = initAcc;
    }
}
