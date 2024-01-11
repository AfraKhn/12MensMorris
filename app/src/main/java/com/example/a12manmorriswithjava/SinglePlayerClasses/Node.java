package com.example.a12manmorriswithjava.SinglePlayerClasses;

public class Node {

    private Taw taw;
    private Node right;
    private Node left;
    private Node up;
    private Node down;
    private Node upRight;
    private Node upLeft;
    private Node downRight;
    private Node downLeft;

    public Taw getTaw() {
        return taw;
    }

    public void setTaw(Taw taw) {
        this.taw = taw;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        if (right.left == null)
            right.left = this;
        this.right = right;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        if (left.right == null)
            left.right = this;
        this.left = left;
    }

    public Node getUp() {
        return up;
    }

    public void setUp(Node up) {
        if (up.down == null)
            up.down = this;
        this.up = up;
    }

    public Node getDown() {
        return down;
    }

    public void setDown(Node down) {
        if (down.up == null)
            down.up = this;
        this.down = down;
    }

    public Node getUpRight() {
        return upRight;
    }

    public void setUpRight(Node upRight) {
        if (upRight.downLeft == null)
            upRight.downLeft = this;
        this.upRight = upRight;
    }

    public Node getUpLeft() {
        return upLeft;
    }

    public void setUpLeft(Node upLeft) {
        if (upLeft.downRight == null)
            upLeft.downRight = this;
        this.upLeft = upLeft;
    }

    public Node getDownRight() {
        return downRight;
    }

    public void setDownRight(Node downRight) {
        if (downRight.upLeft == null)
            downRight.upLeft = this;
        this.downRight = downRight;
    }

    public Node getDownLeft() {
        return downLeft;
    }

    public void setDownLeft(Node downLeft) {
        if (downLeft.upRight == null)
            downLeft.upRight = this;
        this.downLeft = downLeft;
    }
}
