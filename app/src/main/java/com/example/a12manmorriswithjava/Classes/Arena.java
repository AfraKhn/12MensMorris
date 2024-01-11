package com.example.a12manmorriswithjava.Classes;

import java.util.*;

public class Arena implements Cloneable {

    private static final Integer[] best = {3, 5, 18, 20};
    private static final Integer[] better = {0, 2, 6, 8, 15, 17, 21, 23};
    private static final Integer[] average = {4, 10, 13, 19};

    private Node[] nodes;
    private Taw turn;
    private Map<Taw, Player> players;
    private int gameMode;

    public Arena() {
        nodes = new Node[24];
        for (int i = 0; i < 24; i++) {
            nodes[i] = new Node();
        }
        nodes[0].setRight(nodes[1]);
        nodes[0].setDown(nodes[9]);
        nodes[0].setDownRight(nodes[3]);
        nodes[1].setRight(nodes[2]);
        nodes[1].setDown(nodes[4]);
        nodes[2].setDownLeft(nodes[5]);
        nodes[2].setDown(nodes[14]);
        nodes[3].setRight(nodes[4]);
        nodes[3].setDownRight(nodes[6]);
        nodes[3].setDown(nodes[10]);
        nodes[4].setDown(nodes[7]);
        nodes[4].setRight(nodes[5]);
        nodes[5].setDownLeft(nodes[8]);
        nodes[5].setDown(nodes[13]);
        nodes[6].setRight(nodes[7]);
        nodes[6].setDown(nodes[11]);
        nodes[7].setRight(nodes[8]);
        nodes[8].setDown(nodes[12]);
        nodes[9].setRight(nodes[10]);
        nodes[9].setDown(nodes[21]);
        nodes[10].setRight(nodes[11]);
        nodes[10].setDown(nodes[18]);
        nodes[11].setDown(nodes[15]);
        nodes[12].setRight(nodes[13]);
        nodes[12].setDown(nodes[17]);
        nodes[13].setDown(nodes[20]);
        nodes[13].setRight(nodes[14]);
        nodes[14].setDown(nodes[23]);
        nodes[15].setRight(nodes[16]);
        nodes[15].setDownLeft(nodes[18]);
        nodes[16].setRight(nodes[17]);
        nodes[16].setDown(nodes[19]);
        nodes[17].setDownRight(nodes[20]);
        nodes[18].setDownLeft(nodes[21]);
        nodes[18].setRight(nodes[19]);
        nodes[19].setDown(nodes[22]);
        nodes[19].setRight(nodes[20]);
        nodes[20].setDownRight(nodes[23]);
        nodes[21].setRight(nodes[22]);
        nodes[22].setRight(nodes[23]);
        turn = Taw.BLUE;
        players = new HashMap<>();
    }

    public void setGameMode(int gameMode) {
        this.gameMode = gameMode;
    }

    public int getGameMode() {
        return gameMode;
    }

    public void setPlayers(Map<Taw, Player> players) {
        this.players = players;
    }

    public Map<Taw, Player> getPlayers() {
        return players;
    }

    public void setPlayer(Taw taw, Player player) {
        players.put(taw, player);
    }

    public Node[] getNodes() {
        return nodes;
    }

    public void setNodes(Node[] nodes) {
        this.nodes = nodes;
    }

    public int indexOf(Node node) {
        if (node == null)
            return -1;
        for (int i = 0; i < 24; i++) {
            if (nodes[i] == node)
                return i;
        }
        return -1;
    }

    // A method to put a taw on the board
    public boolean putTaw(Taw taw, int index) {
        try {
            boolean checkit = true;
            Node node = nodes[index];
            if (node.getTaw() != null) {
                // Returning false because we can't put a taw on an unempty node.
                checkit = false;
                return false;
            }

            if (checkit) {
                Player m = players.get(taw);
                m.putTaw(index, true);
            }
            // Checking if the selected node is empty or not.
            // Putting taw on its node.
            node.setTaw(taw);
            if (checkScored(index)) {
                Player player = players.get(taw);
                Player opponent = players.get(taw == Taw.RED ? Taw.BLUE : Taw.RED);
                player.setCatchedTaws(player.getCatchedTaws() + 1);
                opponent.setTawsOnHand(opponent.getTawsOnHand());
                changeTurn();
            } else {

                changeTurn();
            }
            return true;
        } catch (IndexOutOfBoundsException e) {
            // if index is not in bounds then it couldn't put the taw on that place so it returns false.
            return false;
        }
    }

    public boolean check_Move(int index, int target) {
        boolean hasRelation = nodes[index].getUpLeft() == nodes[target];
        hasRelation = hasRelation || nodes[index].getUp() == nodes[target];
        hasRelation = hasRelation || nodes[index].getUpRight() == nodes[target];
        hasRelation = hasRelation || nodes[index].getLeft() == nodes[target];
        hasRelation = hasRelation || nodes[index].getRight() == nodes[target];
        hasRelation = hasRelation || nodes[index].getDownLeft() == nodes[target];
        hasRelation = hasRelation || nodes[index].getDown() == nodes[target];
        hasRelation = hasRelation || nodes[index].getDownRight() == nodes[target];


        return hasRelation;


    }


    // A method to move a taw on the board
    //TODO implement taw deleting when we gain score
    public boolean move(int index, int target) {
        if (nodes[target].getTaw() != null)
            return false;

        boolean hasRelation = nodes[index].getUpLeft() == nodes[target];
        hasRelation = hasRelation || nodes[index].getUp() == nodes[target];
        hasRelation = hasRelation || nodes[index].getUpRight() == nodes[target];
        hasRelation = hasRelation || nodes[index].getLeft() == nodes[target];
        hasRelation = hasRelation || nodes[index].getRight() == nodes[target];
        hasRelation = hasRelation || nodes[index].getDownLeft() == nodes[target];
        hasRelation = hasRelation || nodes[index].getDown() == nodes[target];
        hasRelation = hasRelation || nodes[index].getDownRight() == nodes[target];

        if (!hasRelation)
            return false;
        // move
        nodes[target].setTaw(nodes[index].getTaw());
        nodes[index].setTaw(null);
        changeTurn();
        return true;
    }

    // move for fly
    public boolean fly(int index, int target) {
        if (nodes[index].getTaw() != turn || nodes[target].getTaw() != null)
            return false;
        nodes[target].setTaw(nodes[index].getTaw());
        nodes[index].setTaw(null);
        changeTurn();
        return true;
    }

    //del taw of index
    public boolean del(int index) {
        if (nodes[index] != null) {
            nodes[index].setTaw(null);
            return true;
        }
        return false;
    }

    // A method to check if node is score or not.
    public boolean checkScored(Node node) {
        int index;
        if ((index = indexOf(node)) != -1)
            return checkScored(index);
        return false;
    }

    public boolean checkScored(int index) {
        boolean scored = false;
        int[] nodes = {0, 2, 3, 5, 6, 8, 15, 17, 18, 20, 21, 23};
        // Checking if the node is on the corners
        if (searchInt(nodes, index) != -1) {
            // The taw is on the corners.
            nodes = new int[]{0, 3, 6, 17, 20, 23};
            if (searchInt(nodes, index) != -1) {
                // The node is on the \ order of game board.
                if (this.nodes[index].getUpLeft() != null && this.nodes[index].getDownRight() != null) {
                    scored = scored || this.nodes[index].getUpLeft().getTaw() == this.nodes[index].getTaw()
                            && this.nodes[index].getTaw() == this.nodes[index].getDownRight().getTaw();
                } else if (this.nodes[index].getUpLeft() != null) {
                    scored = scored || this.nodes[index].getTaw() == this.nodes[index].getUpLeft().getTaw()
                            && this.nodes[index].getTaw() == this.nodes[index].getUpLeft().getUpLeft().getTaw();
                } else if (this.nodes[index].getDownRight() != null) {
                    scored = scored || this.nodes[index].getTaw() == this.nodes[index].getDownRight().getTaw()
                            && this.nodes[index].getTaw() == this.nodes[index].getDownRight().getDownRight().getTaw();
                }
                nodes = new int[]{0, 3, 6};
                if (searchInt(nodes, index) != -1) {
                    // This means node has neighbour on its right and down
                    scored = scored || (this.nodes[index].getTaw() == this.nodes[index].getRight().getTaw()
                            && this.nodes[index].getTaw() == this.nodes[index].getRight().getRight().getTaw())
                            || (this.nodes[index].getTaw() == this.nodes[index].getDown().getTaw()
                            && this.nodes[index].getTaw() == this.nodes[index].getDown().getDown().getTaw());
                } else {
                    // This means node has neighbour on its left and up
                    scored = scored || (this.nodes[index].getTaw() == this.nodes[index].getLeft().getTaw()
                            && this.nodes[index].getTaw() == this.nodes[index].getLeft().getLeft().getTaw())
                            || (this.nodes[index].getTaw() == this.nodes[index].getUp().getTaw()
                            && this.nodes[index].getTaw() == this.nodes[index].getUp().getUp().getTaw());

                }
            } else {
                // The node is on the / order of game board.
                if (this.nodes[index].getUpRight() != null && this.nodes[index].getDownLeft() != null) {
                    scored = scored || this.nodes[index].getUpRight().getTaw() == this.nodes[index].getTaw()
                            && this.nodes[index].getTaw() == this.nodes[index].getDownLeft().getTaw();
                } else if (this.nodes[index].getUpRight() != null) {
                    scored = scored || this.nodes[index].getTaw() == this.nodes[index].getUpRight().getTaw()
                            && this.nodes[index].getTaw() == this.nodes[index].getUpRight().getUpRight().getTaw();
                } else if (this.nodes[index].getDownLeft() != null) {
                    scored = scored || this.nodes[index].getTaw() == this.nodes[index].getDownLeft().getTaw()
                            && this.nodes[index].getTaw() == this.nodes[index].getDownLeft().getDownLeft().getTaw();
                }
                nodes = new int[]{2, 5, 8};
                if (searchInt(nodes, index) != -1) {
                    // This means node has neighbour on its down and left.
                    scored = scored || (this.nodes[index].getTaw() == this.nodes[index].getLeft().getTaw()
                            && this.nodes[index].getTaw() == this.nodes[index].getLeft().getLeft().getTaw())
                            || (this.nodes[index].getTaw() == this.nodes[index].getDown().getTaw()
                            && this.nodes[index].getTaw() == this.nodes[index].getDown().getDown().getTaw());
                } else {
                    // This means node has neighbour on its up and right.
                    scored = scored || (this.nodes[index].getTaw() == this.nodes[index].getRight().getTaw()
                            && this.nodes[index].getTaw() == this.nodes[index].getRight().getRight().getTaw())
                            || (this.nodes[index].getTaw() == this.nodes[index].getUp().getTaw()
                            && this.nodes[index].getTaw() == this.nodes[index].getUp().getUp().getTaw());
                }
            }
        } else {
            // The taw is not on the corners.
            nodes = new int[]{1, 4, 7, 16, 19, 22};
            if (searchInt(nodes, index) != -1) {
                // The node is on the | order of game board.
                scored = scored || (this.nodes[index].getTaw() == this.nodes[index].getRight().getTaw()
                        && this.nodes[index].getTaw() == this.nodes[index].getLeft().getTaw());
                if (this.nodes[index].getUp() != null && this.nodes[index].getDown() != null)
                    scored = scored || (this.nodes[index].getTaw() == this.nodes[index].getUp().getTaw()
                            && this.nodes[index].getTaw() == this.nodes[index].getDown().getTaw());
                else if (this.nodes[index].getUp() != null)
                    scored = scored || (this.nodes[index].getTaw() == this.nodes[index].getUp().getTaw()
                            && this.nodes[index].getTaw() == this.nodes[index].getUp().getUp().getTaw());
                else if (this.nodes[index].getDown() != null)
                    scored = scored || (this.nodes[index].getTaw() == this.nodes[index].getDown().getTaw()
                            && this.nodes[index].getTaw() == this.nodes[index].getDown().getDown().getTaw());
            } else {
                // The node is on the -- order of game board.
                scored = scored || (this.nodes[index].getTaw() == this.nodes[index].getUp().getTaw()
                        && this.nodes[index].getTaw() == this.nodes[index].getDown().getTaw());
                if (this.nodes[index].getRight() != null && this.nodes[index].getLeft() != null)
                    scored = scored || (this.nodes[index].getTaw() == this.nodes[index].getRight().getTaw()
                            && this.nodes[index].getTaw() == this.nodes[index].getLeft().getTaw());
                else if (this.nodes[index].getRight() != null)
                    scored = scored || (this.nodes[index].getTaw() == this.nodes[index].getRight().getTaw()
                            && this.nodes[index].getTaw() == this.nodes[index].getRight().getRight().getTaw());
                else if (this.nodes[index].getLeft() != null)
                    scored = scored || (this.nodes[index].getTaw() == this.nodes[index].getLeft().getTaw()
                            && this.nodes[index].getTaw() == this.nodes[index].getLeft().getLeft().getTaw());
            }
        }

        return scored;
    }

    private int searchInt(int[] integers, int target) {
        int min = 0;
        int max = integers.length - 1;
        while (min < max) {
            int mid = (max + min) / 2;
            if (integers[mid] == target)
                return mid;
            else if (integers[mid] < target)
                min = mid + 1;
            else if (integers[mid] > target)
                max = mid - 1;
        }
        return integers[max] == target ? max : -1;
    }

    // Changing turn
    public void changeTurn() {
        if (turn == Taw.RED)
            turn = Taw.BLUE;
        else
            turn = Taw.RED;
    }

    public Taw getTurn() {
        return turn;
    }
/*
    public void printArena(){
        String template = """
                {%s} ==================== {%s} ===================== {%s}
                 || \\\\                     ||                      // ||
                 ||  \\\\                    ||                     //  ||
                 ||   \\\\                   ||                    //   ||
                 ||    \\\\                  ||                   //    ||
                 ||     \\\\                 ||                  //     ||
                 ||     {%s} ============ {%s} ============= {%s}     ||
                 ||      |\\\\               ||                //|      ||
                 ||      ||\\\\              ||               //||      ||
                 ||      || \\\\             ||              // ||      ||
                 ||      ||  \\\\            ||             //  ||      ||
                 ||      ||   \\\\           ||            //   ||      ||
                 ||      ||   {%s} ====== {%s} ======= {%s}   ||      ||
                 ||      ||    ||                       ||    ||      ||
                 ||      ||    ||                       ||    ||      ||
                 ||      ||    ||                       ||    ||      ||
                {%s}===={%s}=={%s}                     {%s}=={%s}===={%s}
                 ||      ||    ||                       ||    ||      ||
                 ||      ||    ||                       ||    ||      ||
                 ||      ||    ||                       ||    ||      ||
                 ||      ||   {%s} ====== {%s} ======= {%s}   ||      ||
                 ||      ||   //           ||            \\\\   ||      ||
                 ||      ||  //            ||             \\\\  ||      ||
                 ||      || //             ||              \\\\ ||      ||
                 ||      ||//              ||               \\\\||      ||
                 ||      |//               ||                \\\\|      ||
                 ||     {%s} ============ {%s} ============= {%s}     ||
                 ||     //                 ||                  \\\\     ||
                 ||    //                  ||                   \\\\    ||
                 ||   //                   ||                    \\\\   ||
                 ||  //                    ||                     \\\\  ||
                 || //                     ||                      \\\\ ||
                {%s} ==================== {%s} ===================== {%s}
                """;
        String[] values = new String[nodes.length];
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i].getTaw()==null)
                values[i]="  ";
            else if (nodes[i].getTaw()==Taw.BLUE)
                values[i]="BB";
            else
                values[i]="RR";
        }
        System.out.printf(template,values);
    }*/

    // Finding best node
    public int bestNode() {

        try {
            Arena arena = (Arena) clone();
            Player player1 = (Player) arena.getPlayers().get(Taw.BLUE).clone();
            Player player2 = (Player) arena.getPlayers().get(Taw.RED).clone();
            player1.setArena(arena);
            player2.setArena(arena);
            Map<Taw, Player> players = new HashMap<>();
            players.put(Taw.BLUE, player1);
            players.put(Taw.RED, player2);
            arena.setPlayers(players);

            Map<Integer, Double> statistics = new HashMap<>();
            switch (gameMode) {
                case 1:
                    examine1(arena, statistics);
                    break;
                case 2:
                    examine2(arena, statistics);
                    break;
                case 3:
                    examine3(arena, statistics);
                    break;
            }
            System.out.println("statistics = " + statistics);
            double max = Double.MAX_VALUE * (-1);
            List<Integer> indexes = new ArrayList<>();
            for (int index : statistics.keySet()) {
                if (statistics.get(index) > max) {
                    indexes.clear();
                    indexes.add(index);
                    max = statistics.get(index);
                } else if (statistics.get(index) == max) {
                    indexes.add(index);
                }
            }
            System.out.println("max = " + max);
            System.out.println("indexes = " + indexes);
            List<Integer> result;
            if ((result = intersection(indexes, Arrays.asList(best))).size() != 0) {
                System.out.println("result = " + result);
                System.out.println();
                return result.get((int) (Math.random() * result.size()));
            } else if ((result = intersection(indexes, Arrays.asList(better))).size() != 0) {
                System.out.println("result = " + result);
                System.out.println();
                return result.get((int) (Math.random() * result.size()));
            } else if ((result = intersection(indexes, Arrays.asList(average))).size() != 0) {
                System.out.println("result = " + result);
                System.out.println();
                return result.get((int) (Math.random() * result.size()));
            } else {
                System.out.println("No result look at the index");
                System.out.println();
                return indexes.get((int) (Math.random() * result.size()));
            }
        } catch (CloneNotSupportedException e) {
            return -1;
        }
    }

    private int analyze(Arena arena, Taw type) {
        int[] horizontal = {0, 3, 6, 9, 12, 15, 18, 21};
        int[] vertical = {0, 3, 6, 1, 16, 8, 5, 2};
        int[] slash = {0, 17};
        int[] backSlash = {2, 15};
        int score = 0;
        for (int index : horizontal) {
            Taw[] taws = {arena.nodes[index].getTaw(), arena.nodes[index].getRight().getTaw(),
                    arena.nodes[index].getRight().getRight().getTaw()};
            score += analyzeLine(taws, type);
        }
        for (int index : vertical) {
            Taw[] taws = {arena.nodes[index].getTaw(), arena.nodes[index].getDown().getTaw(),
                    arena.nodes[index].getDown().getDown().getTaw()};
            score += analyzeLine(taws, type);
        }
        for (int index : slash) {
            Taw[] taws = {arena.nodes[index].getTaw(), arena.nodes[index].getDownRight().getTaw(),
                    arena.nodes[index].getDownRight().getDownRight().getTaw()};
            score += analyzeLine(taws, type);
        }
        for (int index : backSlash) {
            Taw[] taws = {arena.nodes[index].getTaw(), arena.nodes[index].getDownLeft().getTaw(),
                    arena.nodes[index].getDownLeft().getDownLeft().getTaw()};
            score += analyzeLine(taws, type);
        }
        return score;
    }

    private int analyzeLine(Taw[] line, Taw type) {
        Taw opponent = type == Taw.RED ? Taw.BLUE : Taw.RED;
        byte numberOfMine = 0, numberOfOpponents = 0;
        for (Taw taw : line) {
            if (taw == type)
                numberOfMine++;
            else if (taw == opponent)
                numberOfOpponents++;
        }
        if (numberOfMine == 0 && numberOfOpponents == 0)
            return 0;
        if (numberOfMine == 0 && numberOfOpponents == 1)
            return -10;
        if (numberOfMine == 0 && numberOfOpponents == 2)
            return -100;
        if (numberOfMine == 0 && numberOfOpponents == 3)
            return -500;
        if (numberOfMine == 1 && numberOfOpponents == 0)
            return 5;
        if (numberOfMine == 1 && numberOfOpponents == 1)
            return 20;
        if (numberOfMine == 1 && numberOfOpponents == 2)
            return 70;
        if (numberOfMine == 2 && numberOfOpponents == 0)
            return 100;
        if (numberOfMine == 2 && numberOfOpponents == 1)
            return 30;
        if (numberOfMine == 3 && numberOfOpponents == 0)
            return 500;
        return 0;
    }

    public void examine1(Arena arena, Map<Integer, Double> statistics) {
        Taw turn = arena.turn;
        for (int i = 0; i < 24; i++) {
            if (arena.putTaw(arena.getTurn(), i)) {
                statistics.put(i, (double) analyze(arena, turn));
                arena.returnToLastState(i);
            }
        }
    }

    public void examine2(Arena arena, Map<Integer, Double> statistics) {
        Taw turn = arena.turn;
        for (int i1 = 0; i1 < 24; i1++) {
            double sum = 0;
            int gameRounds = 0;
            if (arena.putTaw(arena.getTurn(), i1)) {
                for (int i2 = 0; i2 < 24; i2++) {
                    if (arena.putTaw(arena.getTurn(), i2)) {
                        for (int i3 = 0; i3 < 24; i3++) {
                            if (arena.putTaw(arena.getTurn(), i3)) {
                                sum += analyze(arena, turn);
                                gameRounds++;
                                arena.returnToLastState(i3);
                            } else if (arena.getPlayers().get(arena.getTurn()).getTawsOnHand() == 0) {
                                sum += analyze(arena, turn);
                                gameRounds++;
                            }
                        }
                        arena.returnToLastState(i2);
                    } else if (arena.getPlayers().get(arena.getTurn()).getTawsOnHand() == 0) {
                        sum += analyze(arena, turn);
                        gameRounds++;
                    }
                }
                arena.returnToLastState(i1);
                statistics.put(i1, sum / gameRounds);
            }
        }
    }

    public void examine3(Arena arena, Map<Integer, Double> statistics) {
        Taw turn = arena.turn;
        for (int i1 = 0; i1 < 24; i1++) {
            double sum = 0;
            int gameRounds = 0;
            if (arena.putTaw(arena.getTurn(), i1)) {
                for (int i2 = 0; i2 < 24; i2++) {
                    if (arena.putTaw(arena.getTurn(), i2)) {
                        for (int i3 = 0; i3 < 24; i3++) {
                            if (arena.putTaw(arena.getTurn(), i3)) {
                                for (int i4 = 0; i4 < 24; i4++) {
                                    if (arena.putTaw(arena.getTurn(), i4)) {
                                        for (int i5 = 0; i5 < 24; i5++) {
                                            if (arena.putTaw(arena.getTurn(), i5)) {
                                                sum += analyze(arena, turn);
                                                gameRounds++;
                                                arena.returnToLastState(i5);
                                            } else if (arena.getPlayers().get(arena.getTurn()).getTawsOnHand() == 0) {
                                                sum += analyze(arena, turn);
                                                gameRounds++;
                                            }
                                        }
                                        arena.returnToLastState(i4);
                                    } else if (arena.getPlayers().get(arena.getTurn()).getTawsOnHand() == 0) {
                                        sum += analyze(arena, turn);
                                        gameRounds++;
                                    }
                                }
                                arena.returnToLastState(i3);
                            } else if (arena.getPlayers().get(arena.getTurn()).getTawsOnHand() == 0) {
                                sum += analyze(arena, turn);
                                gameRounds++;
                            }
                        }
                        arena.returnToLastState(i2);
                    } else if (arena.getPlayers().get(arena.getTurn()).getTawsOnHand() == 0) {
                        sum += analyze(arena, turn);
                        gameRounds++;
                    }
                }
                arena.returnToLastState(i1);
                statistics.put(i1, sum / gameRounds);
            }
        }
    }

    private List<Integer> intersection(List<Integer> first, List<Integer> second) {
        List<Integer> result = new ArrayList<>();
        for (int firstItem : first) {
            for (int secondItem : second) {
                if (firstItem == secondItem) {
                    result.add(firstItem);
                    break;
                }
            }
        }
        return result;
    }

    private void returnToLastState(int index) {
        if (!this.checkScored(index))
            this.changeTurn();
        this.del(index);
    }

    public int[] bestmovement() {
        Map<int[], Double> statistics = new HashMap<>();
        try {
            Arena arena = (Arena) clone();
            Player player1 = (Player) arena.getPlayers().get(Taw.BLUE).clone();
            Player player2 = (Player) arena.getPlayers().get(Taw.RED).clone();
            Map<Taw, Player> players = new HashMap<>();
            players.put(Taw.BLUE, player1);
            players.put(Taw.RED, player2);
            arena.setPlayers(players);
            for (int i = 0; i < 24; i++) {
                moveSimulator(arena, statistics, i);
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    private void moveSimulator(Arena arena, Map<int[], Double> statistics, int index) {
        for (int i = 0; i < 8; i++) {
            int target = -1;
            switch (i) {
                case 0: {
                    if (arena.nodes[index].getUp() != null && arena.nodes[index].getUp().getTaw() == null) {
                        target = arena.indexOf(arena.nodes[index].getUp());
                    }
                }
                break;
                case 1: {
                    if (arena.nodes[index].getUpRight() != null && arena.nodes[index].getUpRight().getTaw() == null) {
                        target = arena.indexOf(arena.nodes[index].getUpRight());
                    }
                }
                break;
                case 2: {
                    if (arena.nodes[index].getRight() != null && arena.nodes[index].getRight().getTaw() == null) {
                        target = arena.indexOf(arena.nodes[index].getRight());
                    }
                }
                break;
                case 3: {
                    if (arena.nodes[index].getDownRight() != null && arena.nodes[index].getDownRight().getTaw() == null) {
                        target = arena.indexOf(arena.nodes[index].getDownRight());
                    }
                }
                break;
                case 4: {
                    if (arena.nodes[index].getDown() != null && arena.nodes[index].getDown().getTaw() == null) {
                        target = arena.indexOf(arena.nodes[index].getDown());
                    }
                }
                break;
                case 5: {
                    if (arena.nodes[index].getDownLeft() != null && arena.nodes[index].getDownLeft().getTaw() == null) {
                        target = arena.indexOf(arena.nodes[index].getDownLeft());
                    }
                }
                break;
                case 6: {
                    if (arena.nodes[index].getLeft() != null && arena.nodes[index].getLeft().getTaw() == null) {
                        target = arena.indexOf(arena.nodes[index].getLeft());
                    }
                }
                break;
                case 7: {
                    if (arena.nodes[index].getUpLeft() != null && arena.nodes[index].getUpLeft().getTaw() == null) {
                        target = arena.indexOf(arena.nodes[index].getUpLeft());
                    }
                }
                break;
            }
        }
    }
}
/*
Cherto pert tu move
    // relations
        // -------------
        // | t=0 | t=1 | t=2 |
        // -------------
        // | t=3 |index| t=4 |
        // -------------
        // | t=5 | t=6 | t=7 |
        // -------------

        //checking if node of index has relation with node of target
//        boolean hasRelation = switch (target) {
//            case 0 -> nodes[index].getUpLeft() == nodes[target];
//            case 1 -> nodes[index].getUp() == nodes[target];
//            case 2 -> nodes[index].getUpRight() == nodes[target];
//            case 3 -> nodes[index].getLeft() == nodes[target];
//            case 4 -> nodes[index].getRight() == nodes[target];
//            case 5 -> nodes[index].getDownLeft() == nodes[target];
//            case 6 -> nodes[index].getDown() == nodes[target];
//            case 7 -> nodes[index].getDownRight() == nodes[target];
//            default -> false;
//        };

        //check can move or not (when target node is full, we cant move)
 */