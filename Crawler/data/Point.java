2
https://raw.githubusercontent.com/billsonnn/nitro-java/master/room/src/main/java/com/nitro/room/utils/Point.java
package com.nitro.room.utils;

public class Point {

    private int x;
    private int y;
    private int z;

    public Point() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    public Point(int x) {
        this.x = x;
        this.y = 0;
        this.z = 0;
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
        this.z = 0;
    }

    public Point(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point clone() {
        return new Point(this.x, this.y, this.z);
    }

    public Point assignPoint(Point point) {
        if(point != null) {
            this.x = point.x;
            this.y = point.y;
            this.z = point.z;
        }

        return this;
    }

    public Point addPoint(Point point) {
        Point clone = this.clone();

        if(point != null) {
            clone.setX((clone.getX() + point.getX()));
            clone.setY((clone.getY() + point.getY()));
            clone.setZ((clone.getZ() + point.getZ()));
        }

        return clone;
    }

    public Point subtractPoint(Point point) {
        Point clone = this.clone();

        if(point != null) {
            clone.setX((clone.getX() - point.getX()));
            clone.setY((clone.getY() - point.getY()));
            clone.setZ((clone.getZ() - point.getZ()));
        }

        return clone;
    }

    public Point getPoint(int direction) {
        return this.getPoint(direction, 1);
    }

    public Point getPoint(int direction, int offset) {
        Point clone = this.clone();

        direction = (direction % 8);

        switch(direction) {
            case Direction.NORTH:
                clone.setY((clone.getY() - offset));
                break;
            case Direction.NORTH_EAST:
                clone.setX((clone.getX() + offset));
                clone.setY((clone.getY() - offset));
                break;
            case Direction.EAST:
                clone.setX((clone.getX() + offset));
                break;
            case Direction.SOUTH_EAST:
                clone.setX((clone.getX() + offset));
                clone.setY((clone.getY() + offset));
                break;
            case Direction.SOUTH:
                clone.setY((clone.getY() + offset));
                break;
            case Direction.SOUTH_WEST:
                clone.setX((clone.getX() - offset));
                clone.setY((clone.getY() + offset));
                break;
            case Direction.WEST:
                clone.setX((clone.getX() - offset));
                break;
            case Direction.NORTH_WEST:
                clone.setX((clone.getX() - offset));
                clone.setY((clone.getY() - offset));
                break;
        }

        return clone;
    }

    public int getDistanceAround(Point point) {
        Point clone = this.clone();

        if(point != null) {
            clone.setX((clone.getX() - point.getX()));
            clone.setY((clone.getY() - point.getY()));
        }

        return ((clone.getX() * clone.getX()) + (clone.getY() * clone.getY()));
    }

    public boolean comparePoint(Point point) {
        if(point != null) {
            if((this.x == point.getX()) && (this.y == point.getY())) return true;
        }

        return false;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return this.z;
    }

    public void setZ(int z) {
        this.z = z;
    }
}
