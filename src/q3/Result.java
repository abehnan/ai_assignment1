package q3;

public class Result {
    private final double startingPoint;
    private final double delta;
    private final int steps;
    private final double y;

    public double getX() {
        return x;
    }

    private final double x;

    public double getStartingPoint() {
        return startingPoint;
    }

    public double getDelta() {
        return delta;
    }

    public int getSteps() {
        return steps;
    }

    public double getY() {
        return y;
    }

    Result (double startingPoint, double delta, int steps, double y, double x) {
        this.startingPoint = startingPoint;
        this.delta = delta;
        this.steps = steps;
        this.y = y;
        this.x = x;
    }
}
