package q3;

public class Result {
    private final double startingPoint;
    private final double delta;
    private final int steps;
    private final double y;
    private final double x;
    private final double coolingFactor;
    private final double temperature;



    Result (double startingPoint, double delta, int steps, double y, double x) {
        this.startingPoint = startingPoint;
        this.delta = delta;
        this.steps = steps;
        this.y = y;
        this.x = x;
        this.coolingFactor = -1.0;
        this.temperature = -1.0;
    }

    Result(double startingPoint, double delta, int steps, double y, double x, double coolingFactor, double temperature) {
        this.startingPoint = startingPoint;
        this.delta = delta;
        this.steps = steps;
        this.y = y;
        this.x = x;
        this.coolingFactor = coolingFactor;
        this.temperature = temperature;
    }

    public double getCoolingFactor() {
        return coolingFactor;

    }

    public double getTemperature() {
        return temperature;
    }


    public double getX() {
        return x;
    }

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


}
