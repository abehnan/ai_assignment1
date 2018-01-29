package q3;

import java.util.ArrayList;


public class Main {

    private static double Y(double x) {
        return Math.sin(x * x / 2.0) / (Math.log(x + 4) / Math.log(2.0));
    }

    private static ArrayList<Result> hillClimbing() {

        double startingPoint, delta, x, leftX, rightX, currentValue, leftNeighbour, rightNeighbour, leftGap, rightGap;
        double threshold = Double.MIN_VALUE;
        int steps;
        ArrayList<Result> results = new ArrayList<>(100);

        for (startingPoint = 0; startingPoint <= 10; startingPoint++) {
            for (delta = 0.01; delta <= 0.1; delta = delta + 0.01) {
                x = startingPoint;
                steps = 0;
                while (true) {
                    currentValue = Y(x);
                    leftX = x - delta;
                    rightX = x + delta;
                    if (leftX < 0.0) leftX = 0.0;
                    if (rightX > 10.0) rightX = 10.0;
                    leftNeighbour = Y(leftX);
                    rightNeighbour = Y(rightX);
                    leftGap = Math.abs(leftNeighbour - currentValue);
                    rightGap = Math.abs(rightNeighbour - currentValue);

                    // debug
//                    System.out.println("currentValue: " + currentValue);
//                    System.out.println("leftNeighbour: " + leftNeighbour);
//                    System.out.println("rightNeighbour: " + rightNeighbour);
//                    System.out.println("step: " + ++steps + "\n");

                    if ((leftGap <= threshold && rightGap <= threshold) || (currentValue >= leftNeighbour && currentValue >= rightNeighbour)) {
                        results.add(new Result(startingPoint, delta, steps, currentValue, x));
                        break;
                    }
                    else if (leftNeighbour > rightNeighbour && leftNeighbour > currentValue)
                        x = leftX;
                    else if (rightNeighbour >= leftNeighbour && rightNeighbour > currentValue)
                        x = rightX;


                    steps++;

                }
            }
        }
        return results;
    }

    public static void main(String[] args) {

        ArrayList<Result> results = hillClimbing();
        for (Result result : results) {
            System.out.println("starting point: " + result.getStartingPoint());
            System.out.println("delta: " + result.getDelta());
            System.out.println("steps: " + result.getSteps());
            System.out.println("y: " + result.getY());
            System.out.println("x: " + result.getX() + "\n");
        }
    }
}
