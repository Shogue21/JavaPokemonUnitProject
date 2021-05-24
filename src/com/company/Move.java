package com.company;

public class Move implements java.io.Serializable {
    private String name;
    private String type;
    private int power;
    private int PP;
    private int MaxPP;
    private int accuracy;

    public Move() {}

    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public int getMaxPP() {
        return MaxPP;
    }

    public void setMaxPP(int maxPP) {
        MaxPP = maxPP;
    }

    public int getPP() {
        return PP;
    }

    public void setPP(int PP) {
        this.PP = PP;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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



    public String toString() {
        return "Name: " + this.name;
    }
}
