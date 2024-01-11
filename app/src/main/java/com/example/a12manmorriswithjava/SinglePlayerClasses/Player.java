package com.example.a12manmorriswithjava.SinglePlayerClasses;

public class Player implements Cloneable {
    
    private final Taw tawType;
    private int tawsOnHand;
    private int catchedTaws;
    private Arena arena;

    public Player(Taw tawType, Arena arena) {
        this.tawType = tawType;
        this.arena = arena;
        tawsOnHand = 12;
        catchedTaws = 0;
    }

    public Taw getTawType() {
        return tawType;
    }

    public int getTawsOnHand() {
        return tawsOnHand;
    }

    public int getCatchedTaws() {
        return catchedTaws;
    }

    public void setCatchedTaws(int catchedTaws) {
        this.catchedTaws = catchedTaws;
    }

    public void setTawsOnHand(int tawsOnHand) {
        this.tawsOnHand = tawsOnHand;
    }

    public Arena getArena() {
        return arena;
    }

    // putting a taw on the board
    public boolean putTaw(int index) {
        if (tawsOnHand > 0) {
            boolean hasPut = arena.putTaw(tawType, index);
            if (hasPut)
                tawsOnHand--;
            return hasPut;
        }
        return false;
    }

    public boolean moveTaw(int index, int target) {
        Player opponent = arena.getPlayers().get(tawType == Taw.BLUE ? Taw.RED : Taw.BLUE);
        boolean response;
        if (opponent.getCatchedTaws() < 9) {
            response = arena.move(index, target);
        } else {
            response = arena.fly(index, target);
        }
        return response;
    }

    public void setArena(Arena arena) {
        this.arena = arena;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
