package q3;

public class Result {
    private final double startingPoint;
    private final double delta;
    private final int steps;
    private final double maxValue;

    public double getStartingPoint() {
        return startingPoint;
    }

    public double getDelta() {
        return delta;
    }

    public int getSteps() {
        return steps;
    }

    public double getMaxValue() {
        return maxValue;
    }

    Result (double startingPoint, double delta, int steps, double maxValue) {
        this.startingPoint = startingPoint;
        this.delta = delta;
        this.steps = steps;
        this.maxValue = maxValue;
    }
}
