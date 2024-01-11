package com.example.a12manmorriswithjava.Classes;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Arena arena = new Arena();
        Player player1 = new Player(Taw.BLUE, arena);
        Player player2 = new Player(Taw.RED, arena);
        Map<Taw, Player> players = new HashMap<>();
        players.put(player1.getTawType(), player1);
        players.put(player2.getTawType(), player2);
        arena.setPlayers(players);
        while (player1.getTawsOnHand() > 0 && player2.getTawsOnHand() > 0) {
            arena.setGameMode(1);
            System.out.printf("EASY: Suggested node: %s%n", arena.bestNode());
            arena.setGameMode(2);
            System.out.printf("Medium: Suggested node: %s%n", arena.bestNode());
            arena.setGameMode(3);
            System.out.printf("Hard: Suggested node: %s%n", arena.bestNode());
//            if (arena.getTurn() == Taw.BLUE) {
//                player1.putTaw(intInput("Player1: Please select one of the nodes to put shey ichina: "));
//            } else {
//                player2.putTaw(intInput("Player2: Please select one of the nodes to put shey ichina: "));
//            }
//            System.out.printf("""
//                    Player1: %s
//                    taws on hand: %s
//                    taws catched: %s
//                    %n""", System.identityHashCode(player1), player1.getTawsOnHand(), player1.getCatchedTaws());
//            System.out.printf("""
//                    Player2: %s
//                    taws on hand: %s
//                    taws catched: %s
//                    %n""", System.identityHashCode(player2), player2.getTawsOnHand(), player2.getCatchedTaws());
//            input("Press enter to continue");
//            arena.printArena();
        }

    }

    static String input() {
        return scanner.nextLine();
    }

    private static String input(String message) {
        System.out.print(message);
        return input();
    }

    private static int intInput() {
        try {
            return Integer.parseInt(input());
        } catch (Exception e) {
            System.out.println("Invalid input!");
            return intInput();
        }
    }

    private static int intInput(String message) {
        System.out.print(message);
        return intInput();
    }
}
