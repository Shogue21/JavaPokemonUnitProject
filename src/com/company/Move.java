package com.company;

import lombok.*;

public @Data class Move implements java.io.Serializable {
    private String name;
    private String type;
    private int power;
    private int PP;
    private int MaxPP;
    private int accuracy;

    public Move() {}

    public Move(String initName, String initType, int initPower, int initPP, int initAcc) {
        name = initName;
        type = initType;
        power = initPower;
        MaxPP = initPP;
        PP = MaxPP;
        accuracy = initAcc;
    }
    public Move(String loadedName, String loadedType, int loadedPower, int loadedPP, int loadedMaxPP, int loadedAcc) {
        name = loadedName;
        type = loadedType;
        power = loadedPower;
        MaxPP = loadedMaxPP;
        PP = loadedPP;
        accuracy = loadedAcc;
    }


}
