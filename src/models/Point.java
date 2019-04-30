package models;

public class Point {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double distanceTo(Point endPoint) {
        return Math.sqrt(Math.pow(((double) this.x - (double) endPoint.x), 2) + Math.pow(((double) this.y - (double) endPoint.y), 2));
    }

    @Override
    public String toString() {
        return "Point {" +
                "x = " + x +
                ", y = " + y +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || (this.getClass() != obj.getClass())) {
            return false;
        }

        Point other = (Point) obj;
        return (this.x == other.x) && (this.y == other.y);
    }
}
