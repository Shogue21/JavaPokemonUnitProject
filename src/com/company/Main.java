package com.company;

import java.util.ArrayList;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    static String dburl = "jdbc:sqlite:src/PokemonDB.db";
    static Connection conn;

    static PokemonList pokemonList = new PokemonList();
    static ArrayList<Pokemon> userTeam = new ArrayList<>();
    static ArrayList<Pokemon> comTeam = new ArrayList<>();

    static Pokemon currentUserPokemon;
    static Pokemon currentComPokemon;

    //Database Methods
    private static void connect() {
        try {
            conn = DriverManager.getConnection(dburl);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    private static void save() {
        try {
            connect();
            int userCount = 1;
            for (Pokemon pokemon: userTeam) {
                var statement = conn.prepareStatement("INSERT or REPLACE INTO Pokemon ('ID', 'Name', 'Type', 'Health', 'MaxHealth', 'Attack', 'Defense', 'Speed', 'Team', 'Current') VALUES (?, ?, ?, ?, ?, ?, ?, ?, 'User', ?)");
                statement.setInt(1, userCount);
                statement.setString(2, pokemon.getName());
                statement.setString(3, pokemon.getType());
                statement.setInt(4, pokemon.getHealth());
                statement.setInt(5, pokemon.getMaxHealth());
                statement.setInt(6, pokemon.getAttack());
                statement.setInt(7, pokemon.getDefense());
                statement.setInt(8, pokemon.getSpeed());
                statement.setBoolean(9, pokemon.getName().equalsIgnoreCase(currentUserPokemon.getName()));
                statement.execute();
                userCount += 1;
                for (Move move: pokemon.getMoveList()) {
                    var statement2 = conn.prepareStatement("INSERT or REPLACE INTO Moves ('Name', 'Type', 'PP', 'MaxPP', 'Power', 'Accuracy', 'Pokemon') VALUES (?, ?, ?, ?, ?, ?, ?)");
                    statement2.setString(1, move.getName());
                    statement2.setString(2, move.getType());
                    statement2.setInt(3, move.getPP());
                    statement2.setInt(4, move.getMaxPP());
                    statement2.setInt(5, move.getPower());
                    statement2.setInt(6, move.getAccuracy());
                    statement2.setString(7, pokemon.getName());
                    statement2.execute();
                }
            }
            int comCount = 7;
            for (Pokemon pokemon: comTeam) {
                var statement3 = conn.prepareStatement("INSERT or REPLACE INTO Pokemon ('ID', 'Name', 'Type', 'Health', 'MaxHealth', 'Attack', 'Defense', 'Speed', 'Team', 'Current') VALUES (?, ?, ?, ?, ?, ?, ?, ?, 'Com', ?)");
                statement3.setInt(1, comCount);
                statement3.setString(2, pokemon.getName());
                statement3.setString(3, pokemon.getType());
                statement3.setInt(4, pokemon.getHealth());
                statement3.setInt(5, pokemon.getMaxHealth());
                statement3.setInt(6, pokemon.getAttack());
                statement3.setInt(7, pokemon.getDefense());
                statement3.setInt(8, pokemon.getSpeed());
                statement3.setBoolean(9, pokemon.getName().equalsIgnoreCase(currentComPokemon.getName()));
                statement3.execute();
                comCount += 1;
                for (Move move: pokemon.getMoveList()) {
                    var statement4 = conn.prepareStatement("INSERT or REPLACE INTO Moves ('Name', 'Type', 'PP', 'MaxPP', 'Power', 'Accuracy', 'Pokemon') VALUES (?, ?, ?, ?, ?, ?, ?)");
                    statement4.setString(1, move.getName());
                    statement4.setString(2, move.getType());
                    statement4.setInt(3, move.getPP());
                    statement4.setInt(4, move.getMaxPP());
                    statement4.setInt(5, move.getPower());
                    statement4.setInt(6, move.getAccuracy());
                    statement4.setString(7, pokemon.getName());
                    statement4.execute();
                }
            }
        } catch (SQLException s) {
            s.printStackTrace();
        }
    }

    private static void loadSave() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Load save state?");
        String load = keyboard.next();
        if (load.equalsIgnoreCase("yes")) {
            try {
            connect();
            var statement = conn.prepareStatement("SELECT * FROM Pokemon WHERE Team = 'User'");
            var dbUserTeam = statement.executeQuery();
            while (dbUserTeam.next()) {
                Pokemon pokemonToAdd = new Pokemon(dbUserTeam.getString("Name"), dbUserTeam.getString("Type"), dbUserTeam.getInt("Health"), dbUserTeam.getInt("MaxHealth"), dbUserTeam.getInt("Attack"), dbUserTeam.getInt("Defense"), dbUserTeam.getInt("Speed"));
                userTeam.add(pokemonToAdd);
                if (dbUserTeam.getBoolean("Current")) {
                    currentUserPokemon = pokemonToAdd;
                }
            }
            for (Pokemon pokemon: userTeam) {
                var statement2 = conn.prepareStatement("SELECT  * FROM Moves WHERE Pokemon = ?");
                statement2.setString(1, pokemon.getName());
                var dbUserMoves = statement2.executeQuery();
                while (dbUserMoves.next()) {
                    pokemon.getMoveList().add(new Move(dbUserMoves.getString("Name"), dbUserMoves.getString("Type"), dbUserMoves.getInt("Power"), dbUserMoves.getInt("PP"), dbUserMoves.getInt("MaxPP"), dbUserMoves.getInt("Accuracy")));
                }
            }
            var statement3 = conn.prepareStatement("SELECT * FROM Pokemon WHERE Team = 'Com'");
            var dbComTeam = statement3.executeQuery();
            while (dbComTeam.next()) {
                Pokemon pokemonToAdd = new Pokemon(dbComTeam.getString("Name"), dbComTeam.getString("Type"), dbComTeam.getInt("Health"), dbComTeam.getInt("MaxHealth"), dbComTeam.getInt("Attack"), dbComTeam.getInt("Defense"), dbComTeam.getInt("Speed"));
                comTeam.add(pokemonToAdd);
                if (dbComTeam.getBoolean("Current")) {
                    currentComPokemon = pokemonToAdd;
                }
            }
            for (Pokemon pokemon: comTeam) {
                var statement4 = conn.prepareStatement("SELECT * FROM Moves WHERE Pokemon = ?");
                statement4.setString(1, pokemon.getName());
                var dbComMoves = statement4.executeQuery();
                while (dbComMoves.next()) {
                    pokemon.getMoveList().add(new Move(dbComMoves.getString("Name"), dbComMoves.getString("Type"), dbComMoves.getInt("Power"), dbComMoves.getInt("PP"), dbComMoves.getInt("MaxPP"), dbComMoves.getInt("Accuracy")));
                }
            }
                System.out.println("Welcome back!");
            } catch (SQLException s) {
                s.printStackTrace();
            }
        }
    }

    // Display all Pokemon to choose from
    static void displayAllPokemon() {
        for (Pokemon pokemon : pokemonList.getPokemon()) {
            System.out.println(pokemon.getName());
        }
    }

    // Create teams
    static void createUserTeam() {
        while (userTeam.size() < 6) {
            Scanner keyboard = new Scanner(System.in);
            System.out.print("What pokemon are you adding to your team?\n> ");
            String pokemonName = keyboard.next();
            keyboard.nextLine();
            for (Pokemon pokemon : pokemonList.getPokemon()) {
                if (pokemon.getName().equalsIgnoreCase(pokemonName) && !userTeam.contains(pokemon)) {
                    userTeam.add(pokemon);
                }
            }
        }
    }

    static void createComTeam() {
        while (comTeam.size() < 6) {
            int randomIndex = (int) Math.floor(Math.random() * pokemonList.getPokemon().size());
            Pokemon randomPokemon = pokemonList.getPokemon().get(randomIndex);
            if (! userTeam.contains(randomPokemon) && !comTeam.contains(randomPokemon)) {
                comTeam.add(randomPokemon);
            }
        }
    }

    // Swapping Pokemon
    static void swapUserPokemon() {
        Scanner keyboard = new Scanner(System.in);
        boolean validPokemon = false;
            System.out.println("----------------------------------------");
            System.out.printf("|   %s   |   %s  |  %s  |\n", userTeam.get(0).getName(), userTeam.get(1).getName(), userTeam.get(2).getName());
            System.out.printf("|   %s/%s    |    %s/%s   |    %s/%s   |\n", userTeam.get(0).getHealth(), userTeam.get(0).getMaxHealth(), userTeam.get(1).getHealth(), userTeam.get(1).getMaxHealth(), userTeam.get(2).getHealth(), userTeam.get(2).getMaxHealth());
            System.out.println("----------------------------------------");
            System.out.printf("|   %s   |   %s  |  %s  |\n", userTeam.get(3).getName(), userTeam.get(4).getName(), userTeam.get(5).getName());
            System.out.printf("|   %s/%s    |    %s/%s   |    %s/%s   |\n", userTeam.get(3).getHealth(), userTeam.get(3).getMaxHealth(), userTeam.get(4).getHealth(), userTeam.get(4).getMaxHealth(), userTeam.get(5).getHealth(), userTeam.get(5).getMaxHealth());
            System.out.println("----------------------------------------");

        while (true) {
            System.out.print("What pokemon are you swapping to?\n> ");
            String pokemonToSwapTo = keyboard.next();
            keyboard.nextLine();
            for (Pokemon pokemon : userTeam) {
                if (pokemon.getName().equalsIgnoreCase(pokemonToSwapTo) && !currentUserPokemon.getName().equalsIgnoreCase(pokemonToSwapTo) && pokemon.getHealth() != 0) {
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
            int randomIndex = (int) Math.floor(Math.random() * comTeam.size());
            Pokemon randomPokemon = comTeam.get(randomIndex);
            if (randomPokemon.getHealth() != 0) {
                currentComPokemon = randomPokemon;
                break;
            }
        }
    }

    // Battle Methods
    static double checkEffectiveness(String attacker, String defender) {
        switch (attacker.toLowerCase()) {
            case "fire":
                switch (defender.toLowerCase()) {
                    case "fire":
                    case "water":
                        return 0.5;
                    case "grass":
                        return 2.0;
                    case "normal":
                    case "ground":
                    case "flying":
                    case "electric":
                        return 1.0;
                }
            case "water":
                switch (defender.toLowerCase()) {
                    case "fire":
                    case "ground":
                        return 2.0;
                    case "grass":
                    case "water":
                        return 0.5;
                    case "normal":
                    case "electric":
                    case "flying":
                        return 1.0;
                }
            case "grass":
                switch (defender.toLowerCase()) {
                    case "fire":
                    case "grass":
                    case "flying":
                        return 0.5;
                    case "water":
                    case "ground":
                        return 2.0;
                    case "normal":
                    case "electric":
                        return 1.0;
                }
            case "electric":
                switch (defender.toLowerCase()) {
                    case "electric":
                    case "grass":
                        return 0.5;
                    case "ground":
                        return 0.0;
                    case "flying":
                    case "water":
                        return 2.0;
                    case "fire":
                        return 1.0;
                }
            case "flying":
                switch (defender.toLowerCase()) {
                    case "electric":
                        return 0.5;
                    case "fire":
                    case "water":
                    case "ground":
                    case "flying":
                        return 1.0;
                    case "grass":
                        return 2.0;
                }
            case "ground":
                switch (defender.toLowerCase()) {
                    case "water":
                    case "ground":
                        return 1.0;
                    case "flying":
                        return 0;
                    case "grass":
                        return 0.5;
                    case "fire":
                    case "electric":
                        return 2.0;
                }
        }
        return 1.0;
    }

    static Move chooseMove() {
        ArrayList<Move> moveList = currentUserPokemon.getMoveList();
        System.out.println("------------------------------------");
        System.out.printf("|   %s   |    %s   |\n", moveList.get(0).getName(), moveList.get(1).getName());
        System.out.printf("|  %s/%s     |   %s/%s       |\n", moveList.get(0).getPP(), moveList.get(0).getMaxPP(), moveList.get(1).getPP(), moveList.get(1).getMaxPP());
        System.out.println("------------------------------------");
        System.out.printf("|   %s   |    %s    |\n", moveList.get(2).getName(), moveList.get(3).getName());
        System.out.printf("|      %s/%s      |      %s/%s    |\n", moveList.get(2).getPP(), moveList.get(2).getMaxPP(), moveList.get(3).getPP(), moveList.get(3).getMaxPP());
        System.out.println("------------------------------------");
        while (true) {
            Scanner keyboard = new Scanner(System.in);
            System.out.print("What move are you using?\n> ");
            String moveToUse = keyboard.nextLine();
            for (Move move: moveList) {
                if (move.getName().equalsIgnoreCase(moveToUse) && move.getPP() != 0) {
                    return move;
                }
            }
        }
    }

    static void dealDamage(Pokemon pokemon, Move move, String attacker) {
        double crit = 1.0;
        double stab = 1.0;
        if (Math.random() * 100 < 15) {
            switch (attacker) {
                case "user":
                    System.out.println(currentUserPokemon.getName() + " got a critical hit!");
                case "computer":
                    System.out.println(currentComPokemon.getName() + " got a critical hit!");
            }
            crit = 2;
        }
        if (move.getType().equalsIgnoreCase(pokemon.getType())) {
            stab = 1.5;
        }
        if (Math.random() * 100 < move.getAccuracy()) {
            if (attacker.equalsIgnoreCase("computer")) {
                pokemon.setHealth((int) (pokemon.getHealth() - ((12 * move.getPower() * currentComPokemon.getAttack() / pokemon.getDefense()) / 50) * checkEffectiveness(currentComPokemon.getType(), currentUserPokemon.getType()) * crit * stab));
            } else if (attacker.equalsIgnoreCase("user")) {
                pokemon.setHealth((int) (pokemon.getHealth() - ((12 * move.getPower() * currentUserPokemon.getAttack() / pokemon.getDefense()) / 50) * checkEffectiveness(currentUserPokemon.getType(), currentComPokemon.getType()) * crit * stab));
            }
        } else {
            if (attacker.equalsIgnoreCase("user")) {
                System.out.printf("%s's attack missed!\n", currentUserPokemon.getName());
            } else {
                System.out.printf("%s's attack missed!\n", currentComPokemon.getName());
            }
        }
        move.setPP(Math.max(0, move.getPP() - 1));
        if (pokemon.getHealth() <= 0) {
            pokemon.setHealth(0);
        }
    }

    // Main Fight
    static void fight() {
        Scanner keyboard = new Scanner(System.in);
        Move moveToUse = null;
        while (true) {
            String input;
            while (true) {
                System.out.print("What would you like to do?\n>Fight\t>Swap Pokemon\n> ");
                input = keyboard.next();
                keyboard.nextLine();
                if (input.equalsIgnoreCase("fight") || input.equalsIgnoreCase("swap") || input.equalsIgnoreCase("quit")) {
                    break;
                } else {
                    System.out.println("Please choose a valid option!");
                }
            }
            if (input.equalsIgnoreCase("fight")) {
                moveToUse = chooseMove();
                if (currentUserPokemon.getSpeed() >= currentComPokemon.getSpeed()) {
                    dealDamage(currentComPokemon, moveToUse, "user");
                }
                if (!postTurnsCheck(comTeam)) {
                    updateScreen();
                    System.out.println("Computer is out of usable pokemon!");
                    System.out.println("You win!");
                    break;
                }
                if (currentComPokemon.getHealth() == 0) {
                    System.out.printf("\n%s fainted!\n", currentComPokemon.getName());
                    swapComPokemon();
                    updateScreen();
                    continue;
                }
                updateScreen();
            } else if (input.equalsIgnoreCase("swap")) {
                swapUserPokemon();
                updateScreen();
            } else if (input.equalsIgnoreCase("quit")) {
                save();
                break;
            }
            dealDamage(currentUserPokemon, currentComPokemon.useRandomMove(), "computer");
            if (!postTurnsCheck(userTeam)) {
                updateScreen();
                System.out.println("User is out of usable pokemon!");
                System.out.println("User blacked out!");
                break;
            }
            if (currentUserPokemon.getHealth() == 0) {
                System.out.printf("%s fainted! Choose another pokemon!\n", currentUserPokemon.getName());
                swapUserPokemon();
                continue;
            }
            updateScreen();
            if (currentComPokemon.getSpeed() > currentUserPokemon.getSpeed() && ! input.equalsIgnoreCase("swap")) {
                dealDamage(currentComPokemon, moveToUse, "user");
                if (!postTurnsCheck(comTeam)) {
                    updateScreen();
                    System.out.println("Computer is out of usable pokemon!");
                    System.out.println("You win!");
                    break;
                }
                if (currentComPokemon.getHealth() == 0) {
                    System.out.printf("\n%s fainted!\n", currentComPokemon.getName());
                    swapComPokemon();
                    updateScreen();
                    continue;
                }
                updateScreen();
            }
                moveToUse = null;
        }
    }

    static Boolean postTurnsCheck(ArrayList<Pokemon> team) {
        return team.stream().anyMatch(p->p.getHealth()>0);
    }

    static void updateScreen() {
        System.out.printf("\t\t\t\t%s\n\t\t\t\t%s/%s\n", currentComPokemon.getName(), currentComPokemon.getHealth(), currentComPokemon.getMaxHealth());
        System.out.printf("\n\n\n%s\n%s/%s\n", currentUserPokemon.getName(), currentUserPokemon.getHealth(), currentUserPokemon.getMaxHealth());
    }

    public static void main(String[] args) {
        //Main Program
        loadSave();
        if ((comTeam.size() < 6) || (userTeam.size() < 6)) {
            System.out.println("Choose 6 Pokemon to be on your team.");
            displayAllPokemon();

            createUserTeam();
            currentUserPokemon = userTeam.get(0);
            createComTeam();
            currentComPokemon = comTeam.get(0);
        }
        updateScreen();
        fight();
    }
}
